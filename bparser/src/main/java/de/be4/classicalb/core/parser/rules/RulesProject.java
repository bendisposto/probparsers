package de.be4.classicalb.core.parser.rules;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.util.Utils;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class RulesProject {
	private File mainFile;
	static final String MAIN_MACHINE_NAME = "__RULES_MACHINE_Main";
	static final String COMPOSITION_MACHINE_NAME = "__RULES_MACHINE_Composition";
	private ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
	private final List<BException> bExceptionList = new ArrayList<>();
	private final HashMap<String, AbstractOperation> allOperations = new HashMap<>();
	protected final List<IModel> bModels = new ArrayList<>();
	protected final NodeIdAssignment nodeIdAssignment = new NodeIdAssignment();
	private HashMap<String, String> constantStringValues = new HashMap<>();
	private RulesMachineRunConfiguration rulesMachineRunConfiguration;

	public static int parseProject(final File mainFile, final ParsingBehaviour parsingBehaviour, final PrintStream out,
			final PrintStream err) {
		RulesProject project = new RulesProject();
		project.setParsingBehaviour(parsingBehaviour);
		project.parseProject(mainFile);
		project.checkAndTranslateProject();
		return project.printPrologOutput(out, err);
	}

	public RulesProject() {
		// use the provided setter methods to parameterize the rules project
	}

	public void parseRulesMachines(String mainMachineAsString, String... referencedMachines) {
		final IModel mainMachine = parseRulesMachineFromString(mainMachineAsString);
		this.bModels.add(mainMachine);
		if (mainMachine.hasError()) {
			this.bExceptionList.addAll(mainMachine.getCompoundException().getBExceptions());
		}
		for (String referencedMachine : referencedMachines) {
			final IModel machine = parseRulesMachineFromString(referencedMachine);
			this.bModels.add(machine);
			if (machine.hasError()) {
				this.bExceptionList.addAll(machine.getCompoundException().getBExceptions());
			}
		}
	}

	private IModel parseRulesMachineFromString(String mainMachineAsString) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(mainMachineAsString);
		unit.parse();
		return unit;
	}

	public void parseProject(File mainFile) {
		this.mainFile = mainFile;
		RulesParseUnit mainModel = parseMainFile();
		if (mainModel.hasError()) {
			final BCompoundException compound = mainModel.getCompoundException();
			this.bExceptionList.addAll(compound.getBExceptions());
		}
		bModels.add(mainModel);
		final LinkedList<RulesMachineReference> fifo = new LinkedList<>(mainModel.getMachineReferences());
		while (!fifo.isEmpty()) {
			final RulesMachineReference modelReference = fifo.pollFirst();
			if (isANewModel(modelReference)) {
				final IModel bModel = parseRulesMachine(modelReference);
				if (bModel.hasError()) {
					this.bExceptionList.addAll(bModel.getCompoundException().getBExceptions());
				}
				bModels.add(bModel);
				fifo.addAll(bModel.getMachineReferences());
			}
		}
	}

	public void checkAndTranslateProject() {
		this.checkProject();
		this.flattenProject();
	}

	public List<BException> getBExceptionList() {
		return bExceptionList;
	}

	public void setParsingBehaviour(final ParsingBehaviour parsingBehaviour) {
		this.parsingBehaviour = parsingBehaviour;
	}

	public Map<String, AbstractOperation> getOperationsMap() {
		return new HashMap<>(this.allOperations);
	}

	private void flattenProject() {
		if (!this.bExceptionList.isEmpty()) {
			return;
		}
		extractConfigurationOfMainModel();
		final BMachine compositionMachine = new BMachine(COMPOSITION_MACHINE_NAME);
		compositionMachine.addExternalFunctions();
		MachineInjector injector = new MachineInjector(compositionMachine.getStart());
		final List<String> promotesList = new ArrayList<>();
		for (int i = 0; i < bModels.size(); i++) {
			RulesParseUnit rulesParseUnit = (RulesParseUnit) bModels.get(i);
			rulesParseUnit.translate(allOperations);
			if (!rulesParseUnit.hasError()) {
				final int fileNumber = i + 1;
				Start start = rulesParseUnit.getStart();
				nodeIdAssignment.assignIdentifiers(fileNumber, start);
			} else {
				this.bExceptionList.addAll(rulesParseUnit.getCompoundException().getBExceptions());
			}
			List<AbstractOperation> operations = rulesParseUnit.getOperations();
			for (AbstractOperation abstractOperation : operations) {
				if (abstractOperation instanceof ComputationOperation || abstractOperation instanceof RuleOperation) {
					if (null != abstractOperation.getReplacesIdentifier()) {
						// replacement operations are not add here because its
						// original rule will be added
						continue;
					}
					promotesList.add(abstractOperation.getName());
				}
			}
			Start otherStart = rulesParseUnit.getStart();
			injector.injectMachine(otherStart);
		}
		compositionMachine.setParsingBehaviour(this.parsingBehaviour);

		bModels.add(compositionMachine);
		BMachine mainMachine = new BMachine(MAIN_MACHINE_NAME);
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

	private void checkProject() {
		if (!this.bExceptionList.isEmpty()) {
			return;
		}
		collectAllOperations();
		checkDependencies();
		findTransitiveDependencies();
		checkReadWrite();
		checkFunctionDependencies();
		checkIdentifier();
		checkReferencedRuleOperations();
	}

	private void checkFunctionDependencies() {
		for (AbstractOperation abstractOperation : allOperations.values()) {
			final Set<AbstractOperation> transitiveDependencies = abstractOperation.getTransitiveDependencies();
			List<TIdentifierLiteral> functionCalls = abstractOperation.getFunctionCalls();
			for (TIdentifierLiteral tIdentifierLiteral : functionCalls) {
				final String functionName = tIdentifierLiteral.getText();
				if (!allOperations.containsKey(functionName)
						|| !(allOperations.get(functionName) instanceof FunctionOperation)) {
					this.bExceptionList.add(new BException(abstractOperation.getFileName(),
							new CheckException("Unknown FUNCTION name '" + functionName + "'", tIdentifierLiteral)));
					return;
				}
				final FunctionOperation functionOperation = (FunctionOperation) allOperations.get(functionName);
				Set<AbstractOperation> transDeps = new HashSet<>(functionOperation.getTransitiveDependencies());
				transDeps.removeAll(transitiveDependencies);
				if (!transDeps.isEmpty()) {
					StringBuilder sb = new StringBuilder();
					sb.append("Missing dependencies due to FUNCTION call: ");
					Iterator<AbstractOperation> iterator = transDeps.iterator();
					while (iterator.hasNext()) {
						AbstractOperation next = iterator.next();
						sb.append(next.getName());
						if (iterator.hasNext()) {
							sb.append(", ");
						}
					}
					this.bExceptionList.add(new BException(abstractOperation.getFileName(),
							new CheckException(sb.toString(), tIdentifierLiteral)));
				}
			}
		}
	}

	private void extractConfigurationOfMainModel() {
		this.rulesMachineRunConfiguration = new RulesMachineRunConfiguration(this.bModels.get(0), this.allOperations);
		rulesMachineRunConfiguration.collect();
	}

	private void collectAllOperations() {
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

	private void findTransitiveDependencies() {
		LinkedList<AbstractOperation> todoList = new LinkedList<>(allOperations.values());
		while (!todoList.isEmpty()) {
			AbstractOperation operation = todoList.poll();
			if (operation.getTransitiveDependencies() == null) {
				findDependencies(operation, new ArrayList<AbstractOperation>());
			}
		}
	}

	private void checkDependencies() {
		for (AbstractOperation operation : allOperations.values()) {
			List<AIdentifierExpression> dependsOnComputationList = operation.getDependsOnComputationList();
			for (AIdentifierExpression aIdentifierExpression : dependsOnComputationList) {
				final String name = aIdentifierExpression.getIdentifier().get(0).getText();
				if (allOperations.containsKey(name)) {
					AbstractOperation abstractOperation = allOperations.get(name);
					if (!(abstractOperation instanceof ComputationOperation)) {
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
						this.bExceptionList.add(new BException(operation.getFileName(), new CheckException(
								"Operation '" + name + "' is not a RULE operation.", aIdentifierExpression)));
					}
				} else {
					this.bExceptionList.add(new BException(operation.getFileName(),
							new CheckException("Unknown operation: '" + name + "'.", aIdentifierExpression)));
				}
			}
		}
	}

	// public List<AbstractOperation>
	// sortOperations(Collection<AbstractOperation> values) {
	// HashMap<AbstractOperation, Set<AbstractOperation>> dependenciesMap = new
	// HashMap<>();
	// for (AbstractOperation abstractOperation : values) {
	// if (!(abstractOperation instanceof FunctionOperation)) {
	// dependenciesMap.put(abstractOperation, new
	// HashSet<>(abstractOperation.getDependencies()));
	// }
	// }
	// List<AbstractOperation> resultList = new ArrayList<>();
	// List<AbstractOperation> todoList = new
	// ArrayList<>(dependenciesMap.keySet());
	// while (!todoList.isEmpty()) {
	// for (AbstractOperation abstractOperation : new ArrayList<>(todoList)) {
	// final Set<AbstractOperation> deps =
	// dependenciesMap.get(abstractOperation);
	// deps.removeAll(resultList);
	// if (deps.isEmpty()) {
	// resultList.add(abstractOperation);
	// todoList.remove(abstractOperation);
	// }
	// }
	// }
	// return resultList;
	// }

	private void checkReadWrite() {
		HashMap<String, ComputationOperation> allDefineVariables = new HashMap<>();
		for (AbstractOperation operation : allOperations.values()) {
			if (operation instanceof ComputationOperation) {
				ComputationOperation comp = (ComputationOperation) operation;
				for (String name : comp.getDefineVariables()) {
					allDefineVariables.put(name, comp);
				}
			}
		}

		for (AbstractOperation operation : allOperations.values()) {
			final Set<String> readVariables = operation.getReadVariables();
			readVariables.retainAll(allDefineVariables.keySet());

			final HashSet<String> variablesInScope = new HashSet<>();
			if (operation instanceof ComputationOperation) {
				variablesInScope.addAll(((ComputationOperation) operation).getDefineVariables());
			}
			for (AbstractOperation op : operation.getTransitiveDependencies()) {
				if (op instanceof ComputationOperation) {
					variablesInScope.addAll(((ComputationOperation) op).getDefineVariables());
				}
			}
			readVariables.removeAll(variablesInScope);
			if (!readVariables.isEmpty()) {
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

	private void checkIdentifier() {
		if (this.hasErrors()) {
			/*
			 * if there is already an error such as an parse error in one
			 * machine, it makes no sense to check for invalid identifiers
			 * because this all declarations of this machine are missing.
			 */
			return;
		}
		final HashMap<String, RulesParseUnit> map = new HashMap<>();
		for (IModel model : bModels) {
			RulesParseUnit parseUnit = (RulesParseUnit) model;
			map.put(parseUnit.getMachineName(), parseUnit);
		}
		for (IModel model : bModels) {
			RulesParseUnit parseUnit = (RulesParseUnit) model;
			HashSet<String> knownIdentifiers = new HashSet<>();
			List<RulesMachineReference> machineReferences = parseUnit.getMachineReferences();
			for (RulesMachineReference rulesMachineReference : machineReferences) {
				String referenceName = rulesMachineReference.getName();
				RulesParseUnit rulesParseUnit = map.get(referenceName);
				RulesMachineChecker checker = rulesParseUnit.getRulesMachineChecker();
				if (checker == null) {
					return;
				}
				knownIdentifiers.addAll(checker.getGlobalIdentifiers());
			}
			RulesMachineChecker checker = parseUnit.getRulesMachineChecker();
			Map<String, HashSet<Node>> unknownIdentifierMap = checker.getUnknownIdentifier();
			HashSet<String> unknownIdentifiers = new HashSet<>(unknownIdentifierMap.keySet());
			unknownIdentifiers.removeAll(knownIdentifiers);
			for (String name : unknownIdentifiers) {
				HashSet<Node> hashSet = unknownIdentifierMap.get(name);
				Node node = hashSet.iterator().next();
				this.bExceptionList.add(new BException(parseUnit.getPath(),
						new CheckException("Unknown identifier '" + name + "'.", node)));
			}
		}
	}

	public void checkReferencedRuleOperations() {
		if (!bExceptionList.isEmpty()) {
			return;
		}
		final HashMap<String, RulesParseUnit> map = new HashMap<>();
		for (IModel model : bModels) {
			RulesParseUnit parseUnit = (RulesParseUnit) model;
			map.put(parseUnit.getMachineName(), parseUnit);
		}
		for (IModel model : bModels) {
			if (model instanceof RulesParseUnit) {
				RulesParseUnit rulesParseUnit = (RulesParseUnit) model;
				Set<AIdentifierExpression> referencedRuleOperations = rulesParseUnit.getRulesMachineChecker()
						.getReferencedRuleOperations();
				final HashSet<String> knownRules = new HashSet<>();
				for (RuleOperation ruleOperation : rulesParseUnit.getRulesMachineChecker().getRuleOperations()) {
					knownRules.add(ruleOperation.getName());
				}
				for (RulesMachineReference rulesMachineReference : rulesParseUnit.getMachineReferences()) {
					String referenceName = rulesMachineReference.getName();
					RulesParseUnit otherParseUnit = map.get(referenceName);
					RulesMachineChecker checker = otherParseUnit.getRulesMachineChecker();
					if (checker == null) {
						return;
					}
					for (RuleOperation ruleOperation : checker.getRuleOperations()) {
						knownRules.add(ruleOperation.getName());
					}
				}

				for (AIdentifierExpression aIdentifierExpression : referencedRuleOperations) {
					String ruleName = Utils.getAIdentifierAsString(aIdentifierExpression);
					if (!knownRules.contains(ruleName)) {
						this.bExceptionList.add(new BException(rulesParseUnit.getPath(),
								new CheckException("Unknown rule '" + ruleName + "'.", aIdentifierExpression)));
					}
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
					if (nextOperation.getTransitiveDependencies() != null) {
						operationsFound.addAll(nextOperation.getTransitiveDependencies());
					} else {
						Set<AbstractOperation> found = findDependencies(nextOperation, new ArrayList<>(ancestors));
						operationsFound.addAll(found);
					}
					opFound = true;
					break;
				}
			}
			if (!opFound && allOperations.containsKey(opName)) {
				this.bExceptionList.add(new BException(operation.getFileName(),
						new CheckException("Operation '" + opName + "' is not visible in RULES_MACHINE '"
								+ operation.getMachineName() + "'.", aIdentifierExpression)));
			}
		}
		operation.setDependencies(operationsFound);
		return new HashSet<>(operationsFound);
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

	public RulesMachineRunConfiguration getRulesMachineRunConfiguration() {
		return this.rulesMachineRunConfiguration;
	}

	private IModel parseRulesMachine(RulesMachineReference reference) {
		File file = reference.getFile();
		RulesParseUnit unit = new RulesParseUnit(reference.getName());
		unit.setParsingBehaviour(this.parsingBehaviour);
		unit.readMachineFromFile(file);
		unit.parse();
		return unit;
	}

	private RulesParseUnit parseMainFile() {
		RulesParseUnit bParseUnit = new RulesParseUnit();
		bParseUnit.readMachineFromFile(mainFile);
		bParseUnit.setParsingBehaviour(this.parsingBehaviour);
		bParseUnit.parse();
		return bParseUnit;
	}

	protected boolean isANewModel(RulesMachineReference reference) {
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

	public boolean hasErrors() {
		return !this.bExceptionList.isEmpty();
	}

	public int printPrologOutput(final PrintStream out, final PrintStream err) {
		if (!this.bExceptionList.isEmpty()) {
			BCompoundException comp = new BCompoundException(bExceptionList);
			PrologExceptionPrinter.printException(err, comp, parsingBehaviour.isUseIndention(), false);
			return -2;
		} else {
			final IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(out), false);
			printProjectAsPrologTerm(pout);
			pout.flush();
			return 0;
		}

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
			pout.printAtom(iModel.getPath());
		}
		pout.closeList();
		pout.closeTerm();
		pout.fullstop();

		NodeIdAssignment nodeIdAssignment = parsingBehaviour.isAddLineNumbers() ? this.nodeIdAssignment
				: new NodeIdAssignment();

		for (IModel iModel : bModels) {
			iModel.printAsProlog(pout, nodeIdAssignment);
		}
	}

	public void addConstantValue(String constant, String value) {
		this.constantStringValues.put(constant, value);
	}

}
