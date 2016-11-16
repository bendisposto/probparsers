package de.be4.classicalb.core.rules.tranformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
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
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.POperationAttribute;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

/*
 * This class checks that all extensions for the rules language are used in a correct way
 */
public class RulesMachineVisitor extends DepthFirstAdapter {
	@SuppressWarnings("unused") // TODO file name must match machine name
	private final String fileName;
	protected final Map<ARuleOperation, Rule> rulesMap = new HashMap<>();
	private final Map<AComputationOperation, Computation> computationMap = new HashMap<>();
	private final Map<AFunctionOperation, FunctionOperation> functionMap = new HashMap<>();
	protected final ArrayList<CheckException> errorList = new ArrayList<>();
	protected final Hashtable<Node, Node> ruleAssignmentTable = new Hashtable<>();

	private AbstractOperation currentOperation;

	public RulesMachineVisitor(String fileName) {
		this.fileName = fileName;
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

	private void setParent(Node parent, Node value) {
		ruleAssignmentTable.put(parent, value);
	}

	@Override
	public void inAMachineHeader(AMachineHeader node) {
		if (node.getParameters().size() > 0) {
			errorList.add(new CheckException("A RULES_MACHINE must not have any machine parameters", node));
		}

	}

	private void visitOperationAttributes(final LinkedList<POperationAttribute> attributes) {
		// set operation attributes
		for (POperationAttribute pOperationAttribute : attributes) {
			if (pOperationAttribute instanceof APredicateAttributeOperationAttribute) {
				APredicateAttributeOperationAttribute attr = (APredicateAttributeOperationAttribute) pOperationAttribute;
				PPredicate predicate = attr.getPredicate();
				if (attr.getName().getText().equals(RuleGrammar.ACTIVATION)) {
					if (currentOperation.getActivationPredicate() == null) {
						currentOperation.setActivationPredicate(predicate);
					} else {
						errorList.add(new CheckException("ACTIVATED clause of rule operation is used more than once",
								pOperationAttribute));
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
				case RuleGrammar.DEPENDS_ON_RULE: {
					if (currentOperation.getActivationPredicate() != null) {
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
					currentOperation.setDependsOnRules(list);
					break;
				}
				case RuleGrammar.DEPENDS_ON_COMPUTATION: {
					if (currentOperation.getDependsOnComputationList() != null) {
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
					currentOperation.setDependsOnComputations(list);
					break;
				}
				case RuleGrammar.RULEID: {
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
				case RuleGrammar.ERROR_TYPES: {
					if (currentOperation instanceof Rule) {
						final Rule rule = (Rule) currentOperation;
						if (arguments.size() == 1 && arguments.get(0) instanceof AIntegerExpression) {
							rule.setErrrorTypes((AIntegerExpression) arguments.get(0));
							rule.setRuleId((AIdentifierExpression) arguments.get(0));
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
		currentOperation = new Rule(node.getRuleName());
		rulesMap.put(node, (Rule) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getRuleBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void inAComputationOperation(AComputationOperation node) {
		currentOperation = new Computation(node.getName());
		computationMap.put(node, (Computation) currentOperation);
		visitOperationAttributes(node.getAttributes());
		node.getBody().apply(this);
		currentOperation = null;
	}

	@Override
	public void inAFunctionOperation(AFunctionOperation node) {
		currentOperation = new FunctionOperation(node.getName());
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
		if (currentOperation != null && currentOperation instanceof Computation) {
			((Computation) currentOperation).addReadVariable(node);
		}
		outAIdentifierExpression(node);
	}

	@Override
	public void defaultOut(Node node) {
		if (isInRule() && ruleAssignmentTable.containsKey(node)) {
			Node value = ruleAssignmentTable.get(node);
			if (node.parent() != null) {
				setParent(node.parent(), value);
			}
		}
	}

	@Override
	public void outAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RuleGrammar.RULE_SUCCESS: {
			if (!isInRule()) {
				errorList.add(new CheckException("RULE_SUCCESS used outside of a RULE operation", node));
			}
			if (node.getArguments().size() > 0) {
				errorList.add(new CheckException("RULE_SUCCESS must not have an argument.", node));
			}

			ruleAssignmentTable.put(node, node);
			defaultOut(node);
			return;
		}
		case RuleGrammar.RULE_FAIL: {
			if (!isInRule()) {
				errorList.add(new CheckException("RULE_FAIL used outside of a RULE operation", node));
			}
			ruleAssignmentTable.put(node, node);
			if (node.getArguments().size() > 0) {
				((Rule) currentOperation).setHasCounterExamples();
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
		ruleAssignmentTable.put(node, node);
		((Rule) currentOperation).setHasCounterExamples();
		defaultOut(node);
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
		if (!isInRule()) {
			errorList.add(new CheckException("RULE_FORALL used outside of a RULE operation", node));
		}
		ruleAssignmentTable.put(node, node);
		((Rule) currentOperation).setHasCounterExamples();
		defaultOut(node);
	}
}
