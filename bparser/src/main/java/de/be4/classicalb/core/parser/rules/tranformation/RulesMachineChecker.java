package de.be4.classicalb.core.parser.rules.tranformation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.AChoiceSubstitution;
import de.be4.classicalb.core.parser.node.AComputationOperation;
import de.be4.classicalb.core.parser.node.ADefineSubstitution;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AFunctionOperation;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AOperationAttribute;
import de.be4.classicalb.core.parser.node.AOperationCallSubstitution;
import de.be4.classicalb.core.parser.node.AOperatorExpression;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.APredicateAttributeOperationAttribute;
import de.be4.classicalb.core.parser.node.ARuleAnySubMessageSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AUsesMachineClause;
import de.be4.classicalb.core.parser.node.AVarSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.POperationAttribute;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.rules.project.RulesMachineReference;

/*
 * This class checks that all extensions for the rules language are used in a correct way
 */
public class RulesMachineChecker extends DepthFirstAdapter {
	private final String fileName;
	private String machineName;
	private final File file;
	protected final Map<ARuleOperation, RuleOperation> rulesMap = new HashMap<>();
	protected final Map<AComputationOperation, Computation> computationMap = new HashMap<>();
	protected final Map<AFunctionOperation, FunctionOperation> functionMap = new HashMap<>();
	protected final ArrayList<CheckException> errorList = new ArrayList<>();
	protected final HashSet<AIdentifierExpression> assignedVariables = new HashSet<>();
	private final HashSet<String> localVariablesScope = new HashSet<>();
	private final List<RulesMachineReference> machineReferences;

	private AbstractOperation currentOperation;
	private final Start start;

	public RulesMachineChecker(final File file, final String fileName, List<RulesMachineReference> machineReferences,
			Start start) {
		this.file = file;
		this.fileName = fileName;
		this.machineReferences = machineReferences;
		this.start = start;
	}

	public void runChecks() throws BCompoundException {
		start.apply(this);
		if (errorList.size() > 0) {
			final List<BException> bExceptionList = new ArrayList<>();
			final String filePath = file == null ? "UnknownFile" : file.getAbsolutePath();
			for (CheckException checkException : errorList) {
				final BException bException = new BException(filePath, checkException);
				bExceptionList.add(bException);
			}
			throw new BCompoundException(bExceptionList);
		}
	}

	public String getFileName() {
		return this.fileName;
	}

	public List<AbstractOperation> getOperations() {
		List<AbstractOperation> list = new ArrayList<>();
		list.addAll(rulesMap.values());
		list.addAll(computationMap.values());
		list.addAll(functionMap.values());
		return list;
	}

	private boolean isInRule() {
		return currentOperation != null && currentOperation instanceof RuleOperation;
	}

	public FunctionOperation getFunctionOperation(AFunctionOperation funcOp) {
		return this.functionMap.get(funcOp);
	}

	@Override
	public void inAMachineHeader(AMachineHeader node) {
		if (node.getParameters().size() > 0) {
			errorList.add(new CheckException("A RULES_MACHINE must not have any machine parameters", node));
		}
		LinkedList<TIdentifierLiteral> nameList = node.getName();
		if (nameList.size() > 1) {
			errorList.add(new CheckException("Renaming of a RULES_MACHINE name is not allowed.", node));
		}
		final TIdentifierLiteral nameLiteral = nameList.get(0);
		this.machineName = nameLiteral.getText();
	}

	private void visitOperationAttributes(final LinkedList<POperationAttribute> attributes) {
		// set operation attributes
		for (POperationAttribute pOperationAttribute : attributes) {
			if (pOperationAttribute instanceof APredicateAttributeOperationAttribute) {
				APredicateAttributeOperationAttribute attr = (APredicateAttributeOperationAttribute) pOperationAttribute;
				PPredicate predicate = attr.getPredicate();
				if (attr.getName().getText().equals(RulesGrammar.ACTIVATION)) {
					if (currentOperation instanceof FunctionOperation) {
						errorList.add(new CheckException("ACTIVATION is not a valid attribute of a FUNCTION operation.",
								pOperationAttribute));
					} else {
						if (currentOperation.getActivationPredicate() == null) {
							currentOperation.setActivationPredicate(predicate);
						} else {
							errorList.add(new CheckException("ACTIVATION clause is used more than once in operation '"
									+ currentOperation.getName() + "'.", pOperationAttribute));
						}
					}
				} else {
					// PRECONDITION
					if (currentOperation instanceof FunctionOperation) {
						FunctionOperation func = (FunctionOperation) currentOperation;
						if (func.getPreconditionPredicate() == null) {
							func.setPreconditionPredicate(predicate);
						} else {
							errorList.add(new CheckException("PRECONDITION clause is used more than once",
									pOperationAttribute));
						}
					} else {
						errorList.add(new CheckException(
								"PRECONDITION clause is not allowed for a RULE or COMPUTATION operation",
								pOperationAttribute));
					}

				}
			} else {
				AOperationAttribute attribute = (AOperationAttribute) pOperationAttribute;
				LinkedList<PExpression> arguments = attribute.getArguments();
				String name = attribute.getName().getText();
				switch (name) {
				case RulesGrammar.DEPENDS_ON_RULE: {
					if (currentOperation.getDependsOnRulesList().size() > 0) {
						errorList.add(new CheckException("DEPENDS_ON_RULE clause is used more than once",
								pOperationAttribute));
					}
					List<AIdentifierExpression> list = new ArrayList<>();
					for (PExpression pExpression : arguments) {
						if (pExpression instanceof AIdentifierExpression) {
							list.add((AIdentifierExpression) pExpression);
						} else {
							errorList.add(new CheckException("Expected a list of identifiers after DEPENDS_ON_RULE.",
									pOperationAttribute));
						}
					}
					currentOperation.addAllRuleDependencies(list);
					break;
				}
				case RulesGrammar.DEPENDS_ON_COMPUTATION: {
					if (currentOperation.getDependsOnComputationList().size() > 0) {
						errorList.add(new CheckException("DEPENDS_ON_COMPUTATION clause is used more than once",
								pOperationAttribute));
					}
					List<AIdentifierExpression> list = new ArrayList<>();
					for (PExpression pExpression : arguments) {
						if (pExpression instanceof AIdentifierExpression) {
							list.add((AIdentifierExpression) pExpression);
						} else {
							errorList.add(
									new CheckException("Expected a list of identifiers after DEPENDS_ON_COMPUTATION.",
											pOperationAttribute));
						}
					}
					currentOperation.addAllComputationDependencies(list);
					break;
				}
				case RulesGrammar.RULEID: {
					{
						if (currentOperation instanceof RuleOperation) {
							final RuleOperation rule = (RuleOperation) currentOperation;
							if (rule.getRuleIdString() != null) {
								errorList.add(new CheckException("RULEID attribute is used more than once.",
										pOperationAttribute));
							}
							if (arguments.size() == 1 && arguments.get(0) instanceof AIdentifierExpression) {
								rule.setRuleId((AIdentifierExpression) arguments.get(0));
							} else {
								errorList.add(new CheckException("Expected exactly one identifier behind RULEID",
										pOperationAttribute));
							}
						} else {
							errorList.add(new CheckException(
									"RULEID is not an attribute of a FUNCTION or Computation operation",
									pOperationAttribute));
						}
					}
					break;
				}
				case RulesGrammar.ERROR_TYPES: {
					if (currentOperation instanceof RuleOperation) {
						final RuleOperation rule = (RuleOperation) currentOperation;
						if (arguments.size() == 1 && arguments.get(0) instanceof AIntegerExpression) {
							AIntegerExpression intExpr = (AIntegerExpression) arguments.get(0);
							rule.setErrrorTypes(intExpr);
						} else {
							errorList.add(new CheckException("Expected exactly one integer after ERROR_TYPES.",
									pOperationAttribute));
						}
					} else {
						errorList.add(new CheckException(
								"ERROR_TYPES is not an attribute of a FUNCTION or Computation operation.",
								pOperationAttribute));
					}
					break;
				}
				default:
					throw new AssertionError("Unexpected operation attribute: " + name);
				}
			}
		}
	}

	private boolean containsRule(String name) {
		for (RuleOperation rule : this.rulesMap.values()) {
			if (name.equals(rule.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void inARuleOperation(ARuleOperation node) {
		currentOperation = new RuleOperation(node.getRuleName(), this.fileName, this.machineName, machineReferences);
		if (containsRule(currentOperation.getName())) {
			errorList.add(new CheckException("Duplicate operation name '" + currentOperation.getName() + "'.",
					node.getRuleName()));
		}
		rulesMap.put(node, (RuleOperation) currentOperation);
		visitOperationAttributes(node.getAttributes());
	}

	@Override
	public void outARuleOperation(ARuleOperation node) {
		currentOperation = null;
	}

	@Override
	public void inAComputationOperation(AComputationOperation node) {
		currentOperation = new Computation(node.getName(), this.fileName, this.machineName, machineReferences);
		computationMap.put(node, (Computation) currentOperation);
		visitOperationAttributes(node.getAttributes());
	}

	@Override
	public void outAComputationOperation(AComputationOperation node) {
		currentOperation = null;
	}

	@Override
	public void inAFunctionOperation(AFunctionOperation node) {
		currentOperation = new FunctionOperation(node.getName(), this.fileName, this.machineName, machineReferences);
		functionMap.put(node, (FunctionOperation) currentOperation);
		final HashSet<String> variables = new HashSet<>();
		LinkedList<PExpression> identifiers = node.getReturnValues();
		for (PExpression e : identifiers) {
			if (e instanceof AIdentifierExpression) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				String name = id.getIdentifier().get(0).getText();
				variables.add(name);
			} else {
				errorList.add(new CheckException("Each return value must be an identifier.", node));
			}
		}
		localVariablesScope.addAll(variables);
		visitOperationAttributes(node.getAttributes());
	}

	@Override
	public void outAFunctionOperation(AFunctionOperation node) {
		currentOperation = null;
	}

	@Override
	public void outADefineSubstitution(ADefineSubstitution node) {
		// the defined variable should not be used in the TYPE or the VALUE
		// section
		if (currentOperation != null && currentOperation instanceof Computation) {
			Computation compute = (Computation) currentOperation;
			try {
				compute.addDefineVariable(node.getName());
			} catch (CheckException e) {
				this.errorList.add(e);
			}
		}
	}

	@Override
	public void inAVarSubstitution(AVarSubstitution node) {
		final HashSet<String> variables = new HashSet<>();
		LinkedList<PExpression> identifiers = node.getIdentifiers();
		for (PExpression e : identifiers) {
			if (e instanceof AIdentifierExpression) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				String name = id.getIdentifier().get(0).getText();
				variables.add(name);
			} else {
				errorList.add(new CheckException("There must be a list of identifiers in VAR substitution.", node));
			}
		}
		localVariablesScope.addAll(variables);// TODO do not add strings here
	}

	@Override
	public void outAVarSubstitution(AVarSubstitution node) {
		final HashSet<String> variables = new HashSet<>();
		localVariablesScope.removeAll(variables);
	}

	public void inAOperatorExpression(AOperatorExpression node) {
		final String operatorName = node.getName().getText();
		final LinkedList<PExpression> parameters = node.getIdentifiers();
		switch (operatorName) {
		case RulesGrammar.STRING_FORMAT: {
			PExpression stringValue = parameters.get(0);
			if (stringValue instanceof AStringExpression) {
				AStringExpression string = (AStringExpression) stringValue;
				String content = string.getContent().getText();
				int count = (content.length() - content.replace("~w", "").length()) / 2;
				if (count != parameters.size() - 1) {
					this.errorList.add(new CheckException(
							"The number of arguments (" + (parameters.size() - 1)
									+ ") does not match the number of placeholders (" + count + ") in the string.",
							node));
				}
			}
			return;
		}
		case RulesGrammar.GET_RULE_COUNTEREXAMPLES: {
			// the grammar ensures at least one argument
			if (parameters.size() > 2) {
				this.errorList
						.add(new CheckException("Invalid number of arguments. Expected one or two arguments.", node));
			}
			PExpression pExpression = node.getIdentifiers().get(0);
			if (!(pExpression instanceof AIdentifierExpression)) {
				this.errorList.add(new CheckException(
						"The first argument of GET_RULE_COUNTEREXAMPLES must be an identifier.", node));
			}
			return;
		}
		default:
			throw new AssertionError("Unkown expression operator: " + operatorName);
		}
	}

	@Override
	public void inAAssignSubstitution(AAssignSubstitution node) {
		ArrayList<PExpression> righthand = new ArrayList<>(node.getRhsExpressions());
		for (PExpression pExpression : righthand) {
			pExpression.apply(this);
		}
		List<PExpression> copy = new ArrayList<PExpression>(node.getLhsExpression());
		checkThatIdentifiersAreLocalVariables(copy);
	}

	@Override
	public void inAOperationCallSubstitution(AOperationCallSubstitution node) {
		LinkedList<TIdentifierLiteral> opNameList = node.getOperation();
		if (opNameList.size() > 1) {
			errorList.add(new CheckException("Renaming of operation names is not allowed.", node));
		}
		List<PExpression> copy = new ArrayList<PExpression>(node.getResultIdentifiers());
		checkThatIdentifiersAreLocalVariables(copy);
		if (currentOperation != null) {
			currentOperation.addFunctionCall(opNameList.get(0));
		}
	}

	private void checkThatIdentifiersAreLocalVariables(List<PExpression> copy) {
		for (PExpression e : copy) {
			if (e instanceof AIdentifierExpression) {
				AIdentifierExpression id = (AIdentifierExpression) e;
				String name = id.getIdentifier().get(0).getText();
				if (!localVariablesScope.contains(name)) {
					errorList
							.add(new CheckException(
									"Identifier '" + name
											+ "' is not a local variable (VAR). Hence it can not be assigned here.",
									id));
				}
			} else {
				errorList.add(new CheckException(
						"There must be an identifier on the left side of the assign substitution. A function assignment 'f(1) := 1' is also not permitted.",
						e));
			}
		}
	}

	@Override
	public void caseAIdentifierExpression(AIdentifierExpression node) {
		inAIdentifierExpression(node);
		List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());
		if (copy.size() > 1) {
			this.errorList.add(new CheckException("Identifier renaming is not allowed in a RULES_MACHINE.", node));
		}
		if (currentOperation != null) {
			currentOperation.addReadVariable(node);
		}
		outAIdentifierExpression(node);
	}

	@Override
	public void outAOperatorPredicate(AOperatorPredicate node) {
		final List<PExpression> arguments = new ArrayList<PExpression>(node.getIdentifiers());
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RulesGrammar.SUCCEEDED_RULE:
			if (arguments.size() != 1) {
				this.errorList.add(new CheckException(
						"The SUCCEEDED_RULE predicate operator expects exactly one argument.", node));
			}
			return;
		case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE: {
			if (arguments.size() != 2) {
				this.errorList.add(new CheckException(
						"The SUCCEEDED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.", node));
			}
			PExpression pExpression = node.getIdentifiers().get(0);
			if (!(pExpression instanceof AIdentifierExpression)) {
				this.errorList.add(new CheckException(
						"The first argument of SUCCEEDED_RULE_ERROR_TYPE must be an identifier.", node));
			}
			return;
		}
		case RulesGrammar.FAILED_RULE:
			if (arguments.size() != 1) {
				this.errorList.add(
						new CheckException("The FAILED_RULE predicate operator expects exactly one argument.", node));
			}
			return;
		case RulesGrammar.FAILED_RULE_ERROR_TYPE: {
			if (arguments.size() != 2) {
				this.errorList.add(new CheckException(
						"The FAILED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.", node));
			}
			PExpression pExpression = node.getIdentifiers().get(0);
			if (!(pExpression instanceof AIdentifierExpression)) {
				this.errorList.add(new CheckException(
						"The first argument of FAILED_RULE_ERROR_TYPE must be an identifier.", node));
			}
			return;
		}
		case RulesGrammar.NOT_CHECKED_RULE:
			if (arguments.size() != 1) {
				this.errorList.add(new CheckException(
						"The NOT_CHECKED_RULE predicate operator expects exactly one argument.", node));
			}
			return;
		case RulesGrammar.DISABLED_RULE:
			if (arguments.size() != 1) {
				this.errorList.add(
						new CheckException("The DISABLED_RULE predicate operator expects exactly one argument.", node));
			}
			return;
		default:
			throw new AssertionError("Unkown predicate operator: " + operatorName);
		}
	}

	@Override
	public void inAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RulesGrammar.RULE_FAIL: {
			if (!isInRule()) {
				errorList.add(new CheckException("RULE_FAIL used outside of a RULE operation", node));
			}
			if (node.getArguments().size() == 0) {
				errorList.add(new CheckException("RULE_FAIL requires at least one argument.", node));
			}
			if (node.getArguments().size() == 2) {
				if (node.getArguments().get(0) instanceof AIntegerExpression) {
					AIntegerExpression intExpr = (AIntegerExpression) node.getArguments().get(0);
					checkErrorType(intExpr.getLiteral());
				} else {
					errorList.add(new CheckException("The first argument of RULE_FAIL must be an integer.", node));
				}
			} else if (node.getArguments().size() > 2) {
				errorList.add(new CheckException("RULE_FAIL has at most two argument.", node));
			}
			return;
		}
		default:
			throw new AssertionError("Unkown substitution operator: " + operatorName);
		}
	}

	@Override
	public void outARuleAnySubMessageSubstitution(ARuleAnySubMessageSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_ANY used outside of a RULE operation", node));
		}
		checkErrorType(node.getErrorType());
		defaultOut(node);
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_FORALL used outside of a RULE operation", node));
		}
		checkErrorType(node.getErrorType());
		defaultOut(node);
	}

	private void checkErrorType(TIntegerLiteral node) {
		if (node != null && currentOperation instanceof RuleOperation) {
			final RuleOperation rule = (RuleOperation) currentOperation;
			int errorType = Integer.parseInt(node.getText());
			if (rule.getNumberOfErrorTypes() == null) {
				errorList.add(new CheckException("Define the number of error types of the rule operation.", node));
			} else if (errorType > rule.getNumberOfErrorTypes()) {
				errorList.add(new CheckException(
						"The error type exceeded the number of error types specified for this rule operation.", node));
			} else if (errorType < 1) {
				errorList.add(new CheckException("The ERROR_TYPE must be a natural number greater than zero.", node));
			}
		}
	}

	@Override
	public void inAExpressionDefinitionDefinition(AExpressionDefinitionDefinition node) {
		final String name = node.getName().getText();
		if (name.equals("GOAL")) {
			errorList.add(new CheckException("The GOAL definition must be a predicate.", node));
		}
	}

	@Override
	public void inASubstitutionDefinitionDefinition(ASubstitutionDefinitionDefinition node) {
		final String name = node.getName().getText();
		if (name.equals("GOAL")) {
			errorList.add(new CheckException("The GOAL definition must be a predicate.", node));
		}
	}

	@Override
	public void inAChoiceSubstitution(AChoiceSubstitution node) {
		errorList.add(new CheckException("A CHOICE substitution is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseASeesMachineClause(ASeesMachineClause node) {
		errorList.add(new CheckException("The SEES clause is not allowed in a RULES_MACHINE.", node));
	}

	@Override
	public void caseAUsesMachineClause(AUsesMachineClause node) {
		errorList.add(new CheckException("The USES clause is not allowed in a RULES_MACHINE.", node));
	}

}
