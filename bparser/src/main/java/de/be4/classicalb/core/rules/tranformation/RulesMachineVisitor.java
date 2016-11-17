package de.be4.classicalb.core.rules.tranformation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.extensions.RulesGrammar;
import de.be4.classicalb.core.parser.node.ACaseSubstitution;
import de.be4.classicalb.core.parser.node.AChoiceSubstitution;
import de.be4.classicalb.core.parser.node.AComputationOperation;
import de.be4.classicalb.core.parser.node.ADefineSubstitution;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AFunctionOperation;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AOperationAttribute;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.APredicateAttributeOperationAttribute;
import de.be4.classicalb.core.parser.node.ARuleAnySubMessageSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.POperationAttribute;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.util.Utils;
import de.be4.classicalb.core.rules.project.Reference;

/*
 * This class checks that all extensions for the rules language are used in a correct way
 */
public class RulesMachineVisitor extends DepthFirstAdapter {
	private final String fileName;
	private String machineName;
	protected final Map<ARuleOperation, Rule> rulesMap = new HashMap<>();
	protected final Map<AComputationOperation, Computation> computationMap = new HashMap<>();
	protected final Map<AFunctionOperation, FunctionOperation> functionMap = new HashMap<>();
	protected final ArrayList<CheckException> errorList = new ArrayList<>();
	private final List<Reference> machineReferences;

	private AbstractOperation currentOperation;

	public RulesMachineVisitor(String fileName, List<Reference> machineReferences) {
		this.fileName = fileName;
		this.machineReferences = machineReferences;
	}

	public List<AbstractOperation> getOperations() {
		List<AbstractOperation> list = new ArrayList<>();
		list.addAll(rulesMap.values());
		list.addAll(computationMap.values());
		list.addAll(functionMap.values());
		return list;
	}

	private boolean isInRule() {
		return currentOperation != null;
	}

	public FunctionOperation getFunctionOperation(AFunctionOperation funcOp) {
		return this.functionMap.get(funcOp);
	}

	public void inAChoiceSubstitution(AChoiceSubstitution node) {
		if (isInRule()) {
			errorList.add(new CheckException("A CHOICE substitution is not allowed in rule operations", node));
		}
	}

	public void inACaseSubstitution(ACaseSubstitution node) {
		if (isInRule()) {
			errorList.add(new CheckException("A CASE substitution is not allowed in rule operations", node));
		}
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

		if (fileName != null) {
			File f = new File(fileName);
			final String fileNameWithOutExtension = Utils.getFileWithoutExtension(f.getName());
			if (!nameLiteral.getText().equals(fileNameWithOutExtension)) {
				errorList.add(new CheckException("RULES_MACHINE name must match the file name: " + this.machineName
						+ " vs " + fileNameWithOutExtension, node));
			}
		}

	}

	private void visitOperationAttributes(final LinkedList<POperationAttribute> attributes) {
		// set operation attributes
		for (POperationAttribute pOperationAttribute : attributes) {
			if (pOperationAttribute instanceof APredicateAttributeOperationAttribute) {
				APredicateAttributeOperationAttribute attr = (APredicateAttributeOperationAttribute) pOperationAttribute;
				PPredicate predicate = attr.getPredicate();
				if (attr.getName().getText().equals(RulesGrammar.ACTIVATION)) {
					if (currentOperation instanceof FunctionOperation) {
						errorList.add(new CheckException("ACTIVATED is not a valid attribute of a FUNCTION operation.",
								pOperationAttribute));
					} else {
						if (currentOperation.getActivationPredicate() == null) {
							currentOperation.setActivationPredicate(predicate);
						} else {
							errorList.add(new CheckException(
									"ACTIVATED clause of rule operation is used more than once", pOperationAttribute));
						}
					}
				} else {
					// PRECONDITION
					if (currentOperation instanceof FunctionOperation) {
						FunctionOperation func = (FunctionOperation) currentOperation;
						if (func.getPreconditionPredicate() == null) {
							func.setPreconditionPredicate(predicate);
						} else {
							errorList.add(new CheckException(
									"PRECONDITION clause of function operation is used more than once",
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
						errorList.add(
								new CheckException("DEPENDS_ON_RULE clause of rule operation is used more than once",
										pOperationAttribute));
					}
					List<AIdentifierExpression> list = new ArrayList<>();
					for (PExpression pExpression : arguments) {
						if (pExpression instanceof AIdentifierExpression) {
							list.add((AIdentifierExpression) pExpression);
						} else {
							errorList.add(new CheckException("Expected a list of identifiers behind DEPENDS_ON_RULES.",
									pOperationAttribute));
						}
					}
					currentOperation.addAllRuleDependencies(list);
					break;
				}
				case RulesGrammar.DEPENDS_ON_COMPUTATION: {
					if (currentOperation.getDependsOnComputationList().size() > 0) {
						errorList.add(new CheckException(
								"DEPENDS_ON_COMPUTATION clause of rule operation is used more than once",
								pOperationAttribute));
					}
					List<AIdentifierExpression> list = new ArrayList<>();
					for (PExpression pExpression : arguments) {
						if (pExpression instanceof AIdentifierExpression) {
							list.add((AIdentifierExpression) pExpression);
						} else {
							errorList.add(
									new CheckException("Expected a list of identifiers behind DEPENDS_ON_COMPUTATIONS.",
											pOperationAttribute));
						}
					}
					currentOperation.addAllComputationDependencies(list);
					break;
				}
				case RulesGrammar.RULEID: {
					{
						if (currentOperation instanceof Rule) {
							final Rule rule = (Rule) currentOperation;
							if (rule.getRuleIdString() != null) {
								errorList.add(new CheckException(
										"RULEID clause of rule operation is used more than once", pOperationAttribute));
							}
							if (arguments.size() == 1 && arguments.get(0) instanceof AIdentifierExpression) {
								rule.setRuleId((AIdentifierExpression) arguments.get(0));
							} else {
								errorList.add(new CheckException("Expected one identifier behind RULEID",
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
					if (currentOperation instanceof Rule) {
						final Rule rule = (Rule) currentOperation;
						if (arguments.size() == 1 && arguments.get(0) instanceof AIntegerExpression) {
							AIntegerExpression intExpr = (AIntegerExpression) arguments.get(0);
							rule.setErrrorTypes(intExpr);
						} else {
							errorList.add(new CheckException("Expected one integer behind ERROR_TYPES.",
									pOperationAttribute));
						}
					} else {
						errorList.add(new CheckException(
								"ERROR_TYPES is not an attribute of a FUNCTION or Computation operation",
								pOperationAttribute));
					}
					break;
				}

				default:
					throw new IllegalStateException("Unexpected operation attribute: " + name);
				}
			}
		}
	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		currentOperation = new Rule(node.getRuleName(), this.fileName, this.machineName, machineReferences);
		rulesMap.put(node, (Rule) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getRuleBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void inAComputationOperation(AComputationOperation node) {
		currentOperation = new Computation(node.getName(), this.fileName, this.machineName, machineReferences);
		computationMap.put(node, (Computation) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void inAFunctionOperation(AFunctionOperation node) {
		currentOperation = new FunctionOperation(node.getName(), this.fileName, this.machineName, machineReferences);
		functionMap.put(node, (FunctionOperation) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void caseADefineSubstitution(ADefineSubstitution node) {
		inADefineSubstitution(node);
		if (currentOperation != null && currentOperation instanceof Computation) {
			Computation compute = (Computation) currentOperation;
			try {
				compute.addDefineVariable(node.getName());
			} catch (CheckException e) {
				this.errorList.add(e);
			}
		}
		node.getName().apply(this);
		node.getType().apply(this);
		if (node.getDummyValue() != null) {
			node.getDummyValue().apply(this);
		}
		node.getValue().apply(this);
		outADefineSubstitution(node);
	}

	@Override
	public void caseAIdentifierExpression(AIdentifierExpression node) {
		inAIdentifierExpression(node);
		List<TIdentifierLiteral> copy = new ArrayList<TIdentifierLiteral>(node.getIdentifier());
		if (copy.size() > 1) {
			this.errorList.add(new CheckException("Variable renaming is not allowed.", node));
		}
		if (currentOperation != null) {
			currentOperation.addReadVariable(node);
		}
		outAIdentifierExpression(node);
	}

	@Override
	public void outAOperatorSubstitution(AOperatorSubstitution node) {
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
				errorList.add(new CheckException("RULE_FAIL at most two argument.", node));
			}

			defaultOut(node);
			return;
		}
		default:
			throw new RuntimeException("should not happen: " + operatorName);
		}
	}

	@Override
	public void outARuleAnySubMessageSubstitution(ARuleAnySubMessageSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_FORALL used outside of a RULE operation", node));
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
		if (node != null) {
			int errorType = Integer.parseInt(node.getText());
			Rule rule = (Rule) currentOperation;
			if (rule.getNumberOfErrorTypes() == null && errorType > 1) {
				errorList.add(new CheckException("Define the number of error types of the rule operation.", node));
			}
			if (rule.getNumberOfErrorTypes() != null && errorType > rule.getNumberOfErrorTypes()) {
				errorList.add(new CheckException(
						"The error type exceeded the number of error types specified for this rule operation.", node));
			}
		}
	}
}
