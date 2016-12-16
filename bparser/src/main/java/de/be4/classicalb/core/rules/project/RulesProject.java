package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.rules.tranformation.AbstractOperation;
import de.be4.classicalb.core.rules.tranformation.Computation;
import de.be4.classicalb.core.rules.tranformation.FunctionOperation;
import de.be4.classicalb.core.rules.tranformation.RuleOperation;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class RulesProject {
	private final File mainFile;
	final String MAIN_MACHINE_NAME = "__RULES_MACHINE_Main";
	final String COMPOSITION_MACHINE_NAME = "__RULES_MACHINE_Composition";
	private ParsingBehaviour parsingBehaviour;
	private final List<BException> bExceptionList = new ArrayList<>();
	private final HashMap<String, AbstractOperation> allOperations = new HashMap<>();
	protected final List<IModel> bModels = new ArrayList<>();
	protected final NodeIdAssignment nodeIds = new NodeIdAssignment();
	private HashMap<String, String> constantStringValues = new HashMap<>();

	public static int parseProject(final File mainFile, final ParsingBehaviour parsingBehaviour, final PrintStream out,
			final PrintStream err) {
		RulesProject project = new RulesProject(mainFile);
		project.setParsingBehaviour(parsingBehaviour);
		project.parseProject();
		project.flattenProject();
		return project.printPrologOutput(out, err);
	}

	public RulesProject(File mainFile) {
		this.mainFile = mainFile;
	}

	public List<BException> getBExceptionList() {
		for (IModel iModel : bModels) {
			if (iModel.hasError()) {
				this.bExceptionList.add(iModel.getBExeption().getFirstException());
			}
		}
		return bExceptionList;
	}

	public void setParsingBehaviour(final ParsingBehaviour parsingBehaviour) {
		this.parsingBehaviour = parsingBehaviour;
	}

	public HashMap<String, AbstractOperation> getOperationsMap() {
		return new HashMap<>(this.allOperations);
	}

	public void flattenProject() {
		if (this.bExceptionList.size() > 0) {
			return;
		}

		final BMachine compositionMachine = new BMachine(COMPOSITION_MACHINE_NAME,
				new File(COMPOSITION_MACHINE_NAME + ".mch"));
		compositionMachine.addExternalFunctions();
		MachineInjector injector = new MachineInjector(compositionMachine.getStart());
		final List<String> promotesList = new ArrayList<>();
		for (int i = 0; i < bModels.size(); i++) {
			RulesParseUnit rulesParseUnit = (RulesParseUnit) bModels.get(i);
			rulesParseUnit.translate(allOperations);
			if (!rulesParseUnit.hasError()) {
				final int fileNumber = i + 1;
				Start start = rulesParseUnit.getStart();
				nodeIds.assignIdentifiers(fileNumber, start);
			}
			List<AbstractOperation> operations = rulesParseUnit.getOperations();
			for (AbstractOperation abstractOperation : operations) {
				if (abstractOperation instanceof FunctionOperation) {
					continue;
				}
				promotesList.add(abstractOperation.getName());
			}
			Start otherStart = rulesParseUnit.getStart();
			injector.injectMachine(otherStart);
		}
		compositionMachine.setParsingBehaviour(this.parsingBehaviour);

		bModels.add(compositionMachine);
		BMachine mainMachine = new BMachine(MAIN_MACHINE_NAME, new File(MAIN_MACHINE_NAME + ".mch"));
		mainMachine.addPreferenceDefinition("SET_PREF_ALLOW_LOCAL_OPERATION_CALLS", true);
		mainMachine.addPreferenceDefinition("SET_PREF_TIME_OUT", 500000);
		mainMachine.addIncludesClause(COMPOSITION_MACHINE_NAME);
		mainMachine.addPromotesClause(promotesList);
		mainMachine.addPropertiesPredicates(this.constantStringValues);
		if (injector.getGoalDefinition() != null) {
			mainMachine.addDefinition(injector.getGoalDefinition());
		}
		bModels.add(mainMachine);
	}

	public void parseProject() {
		final IModel mainModel = parseMainFile();
		if (mainModel.hasError()) {
			final BCompoundException compound = mainModel.getBExeption();
			this.bExceptionList.addAll(compound.getExceptions());
		}
		bModels.add(mainModel);
		final LinkedList<Reference> fifo = new LinkedList<>(mainModel.getMachineReferences());
		while (!fifo.isEmpty()) {
			final Reference modelReference = fifo.pollFirst();
			if (isANewModel(modelReference)) {
				final IModel bModel = lookupReference(modelReference);
				if (bModel.hasError()) {
					final BCompoundException compound = bModel.getBExeption();
					this.bExceptionList.addAll(compound.getExceptions());
				}
				bModels.add(bModel);
				fifo.addAll(bModel.getMachineReferences());
			}
		}

		checkProject();
	}

	private void checkProject() {
		collectAllRules();
		checkDependencies();

		LinkedList<AbstractOperation> todoList = new LinkedList<>(allOperations.values());
		while (!todoList.isEmpty()) {
			AbstractOperation operation = todoList.poll();
			if (operation.getDependencies() == null) {
				findDependencies(operation, new ArrayList<AbstractOperation>());
			}
		}
		checkReadWrite();
	}

	private void checkDependencies() {
		for (AbstractOperation operation : allOperations.values()) {
			List<AIdentifierExpression> dependsOnComputationList = operation.getDependsOnComputationList();
			for (AIdentifierExpression aIdentifierExpression : dependsOnComputationList) {
				final String name = aIdentifierExpression.getIdentifier().get(0).getText();
				if (allOperations.containsKey(name)) {
					AbstractOperation abstractOperation = allOperations.get(name);
					if (!(abstractOperation instanceof Computation)) {
						this.bExceptionList.add(new BException(operation.getFileName(), new CheckException(
								"Identifier '" + name + "' is not a COMPUTATION.", aIdentifierExpression)));
					}
				} else {
					this.bExceptionList.add(new BException(operation.getFileName(),
							new CheckException("Unknown operation: '" + name + "'.", aIdentifierExpression)));
				}
			}
			List<AIdentifierExpression> dependsOnRulesList = operation.getDependsOnRulesList();
			for (AIdentifierExpression aIdentifierExpression : dependsOnRulesList) {
				final String name = aIdentifierExpression.getIdentifier().get(0).getText();
				if (allOperations.containsKey(name)) {
					AbstractOperation abstractOperation = allOperations.get(name);
					if (!(abstractOperation instanceof RuleOperation)) {
						this.bExceptionList.add(new BException(operation.getFileName(),
								new CheckException("Identifier '" + name + "' is not a RULE.", aIdentifierExpression)));
					}
				} else {
					this.bExceptionList.add(new BException(operation.getFileName(),
							new CheckException("Unknown operation: '" + name + "'.", aIdentifierExpression)));
				}
			}
		}
	}

	private void collectAllRules() {
		for (IModel iModel : bModels) {
			final RulesParseUnit unit = (RulesParseUnit) iModel;
			final List<AbstractOperation> operations = unit.getOperations();
			for (AbstractOperation abstractOperation : operations) {
				final String name = abstractOperation.getName();
				if (allOperations.containsKey(name)) {
					this.bExceptionList.add(new BException(abstractOperation.getFileName(), new CheckException(
							"Duplicate operation name: '" + name + "'.", abstractOperation.getNameLiteral())));
				}
				allOperations.put(name, abstractOperation);
			}
		}
	}

	private List<AbstractOperation> sortOperations(Collection<AbstractOperation> values) {
		HashMap<AbstractOperation, Set<AbstractOperation>> dependenciesMap = new HashMap<>();
		for (AbstractOperation abstractOperation : values) {
			if (!(abstractOperation instanceof FunctionOperation)) {
				dependenciesMap.put(abstractOperation, new HashSet<>(abstractOperation.getDependencies()));
			}
		}
		List<AbstractOperation> resultList = new ArrayList<>();
		List<AbstractOperation> todoList = new ArrayList<>(dependenciesMap.keySet());
		while (!todoList.isEmpty()) {
			for (AbstractOperation abstractOperation : new ArrayList<>(todoList)) {
				final Set<AbstractOperation> deps = dependenciesMap.get(abstractOperation);
				deps.removeAll(resultList);
				if (deps.isEmpty()) {
					resultList.add(abstractOperation);
					todoList.remove(abstractOperation);
				}
			}
		}
		return resultList;
	}

	private void checkReadWrite() {
		HashMap<String, Computation> allDefineVariables = new HashMap<>();
		for (AbstractOperation operation : allOperations.values()) {
			if (operation instanceof Computation) {
				Computation comp = (Computation) operation;
				for (String name : comp.getDefineVariables()) {
					allDefineVariables.put(name, comp);
				}
			}
		}

		for (AbstractOperation operation : allOperations.values()) {
			final HashSet<String> readVariables = operation.getReadVariables();
			readVariables.retainAll(allDefineVariables.keySet());

			final HashSet<String> variablesInScope = new HashSet<>();
			if (operation instanceof Computation) {
				variablesInScope.addAll(((Computation) operation).getDefineVariables());
			}
			for (AbstractOperation op : operation.getDependencies()) {
				if (op instanceof Computation) {
					variablesInScope.addAll(((Computation) op).getDefineVariables());
				}
			}
			readVariables.removeAll(variablesInScope);
			if (readVariables.size() > 0) {
				for (String varName : readVariables) {
					this.bExceptionList.add(new BException(operation.getFileName(),
							new CheckException(
									"Missing dependency to computation '" + allDefineVariables.get(varName)
											+ "' in order to use variable '" + varName + "'.",
									operation.getVariableReadByName(varName))));
				}
			}

		}

	}

	private Set<AbstractOperation> findDependencies(final AbstractOperation operation,
			final List<AbstractOperation> ancestors) {
		ancestors.add(operation);
		List<AIdentifierExpression> dependencies = new ArrayList<>();
		dependencies.addAll(operation.getDependsOnComputationList());
		dependencies.addAll(operation.getDependsOnRulesList());
		Set<AbstractOperation> operationsFound = new HashSet<>();
		// check for cycle
		boolean cycleDetected = checkForCycles(operation, dependencies, ancestors);
		if (cycleDetected) {
			operation.setDependencies(operationsFound);
			return operationsFound;
		}
		for (AIdentifierExpression aIdentifierExpression : dependencies) {
			String opName = aIdentifierExpression.getIdentifier().get(0).getText();
			List<String> machineReferences = operation.getMachineReferencesAsString();
			boolean opFound = false;
			for (AbstractOperation otherOperation : allOperations.values()) {
				if ((otherOperation.getMachineName().equals(operation.getMachineName())
						|| machineReferences.contains(otherOperation.getMachineName()))
						&& otherOperation.getName().equals(opName)) {
					AbstractOperation nextOperation = allOperations.get(opName);
					operationsFound.add(nextOperation);
					if (nextOperation.getDependencies() != null) {
						operationsFound.addAll(nextOperation.getDependencies());
					} else {
						Set<AbstractOperation> found = findDependencies(nextOperation, new ArrayList<>(ancestors));
						operationsFound.addAll(found);
					}
					opFound = true;
					break;
				}
			}
			if (opFound == false && allOperations.containsKey(opName)) {
				this.bExceptionList.add(new BException(operation.getFileName(),
						new CheckException("Operation '" + opName + "' is not visible in RULES_MACHINE '"
								+ operation.getMachineName() + "'.", aIdentifierExpression)));
			}
		}
		operation.setDependencies(operationsFound);
		return new HashSet<>(operationsFound);
	}

	@SuppressWarnings("unused")
	private Set<AbstractOperation> findFunctionDependencies(final AbstractOperation operation,
			final List<AbstractOperation> ancestors) {
		// TODO find function dependencies
		return null;
	}

	private boolean checkForCycles(AbstractOperation operation, List<AIdentifierExpression> list,
			List<AbstractOperation> ancestors) {
		List<String> ancestorsNames = new ArrayList<>();
		for (AbstractOperation op : ancestors) {
			String opName = op.getName();
			ancestorsNames.add(opName);
		}
		for (AIdentifierExpression id : list) {
			final String opName = id.getIdentifier().get(0).getText();
			if (ancestorsNames.contains(opName)) {
				StringBuilder sb = new StringBuilder();
				for (int index = ancestorsNames.indexOf(opName); index < ancestors.size(); index++) {
					final String name = ancestors.get(index).getName();
					sb.append(name);
					sb.append(" -> ");
				}
				sb.append(opName);
				this.bExceptionList.add(new BException(operation.getFileName(),
						new CheckException("Cyclic dependencies between operations: " + sb.toString(), id)));
				return true;
			}
		}
		return false;
	}

	private IModel lookupReference(Reference reference) {
		File file = reference.getFile();
		RulesParseUnit unit = new RulesParseUnit(reference.getName());
		unit.setParsingBehaviour(this.parsingBehaviour);
		unit.readMachineFromFile(file);
		unit.parse();
		return unit;
	}

	private IModel parseMainFile() {
		RulesParseUnit bParseUnit = new RulesParseUnit();
		bParseUnit.readMachineFromFile(mainFile);
		bParseUnit.setParsingBehaviour(this.parsingBehaviour);
		bParseUnit.parse();
		return bParseUnit;
	};

	protected boolean isANewModel(Reference reference) {
		for (IModel iModel : bModels) {
			if (iModel.getMachineName().equals(reference.getName())) {
				return false;
			}
		}
		return true;
	}

	public List<IModel> getBModels() {
		return bModels;
	}

	public void addModel(IModel model) {
		bModels.add(model);
	}

	public boolean projectHasErrors() {
		if (this.bExceptionList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public int printPrologOutput(final PrintStream out, final PrintStream err) {
		if (this.bExceptionList.size() > 0) {
			BCompoundException comp = new BCompoundException(bExceptionList);
			PrologExceptionPrinter.printException(err, comp, parsingBehaviour.useIndention, false);
			return -2;
		} else {
			final IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(out), false);
			printProjectAsPrologTerm(pout);
			pout.flush();
			return 0;
		}

	}

	public void printProjectAsPrologTerm(final PrintStream out) {
		PrologTermOutput prologTermOutput = new PrologTermOutput(new PrintWriter(out), false);
		for (IModel iModel : bModels) {
			iModel.printAsProlog(prologTermOutput, nodeIds);
		}
		out.flush();
	}

	public String getProjectAsPrologTerm() {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};
		final IPrologTermOutput pout = new PrologTermOutput(output, false);
		printProjectAsPrologTerm(pout);
		pout.flush();
		return output.toString();

	}

	public void printProjectAsPrologTerm(final IPrologTermOutput pout) {
		// parser version
		pout.openTerm("parser_version");
		pout.printAtom(BParser.getBuildRevision());
		pout.closeTerm();
		pout.fullstop();

		// machine
		pout.openTerm("classical_b");
		pout.printAtom(MAIN_MACHINE_NAME);
		pout.openList();

		for (IModel iModel : bModels) {
			pout.printAtom(iModel.getFile().getPath());
		}
		pout.closeList();
		pout.closeTerm();
		pout.fullstop();

		for (IModel iModel : bModels) {
			iModel.printAsProlog(pout, nodeIds);
		}
	}

	public void addConstantValue(String constant, String value) {
		this.constantStringValues.put(constant, value);
	}

}
