package de.be4.classicalb.core.parser.analysis.transforming.rules;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.ACaseSubstitution;
import de.be4.classicalb.core.parser.node.AChoiceSubstitution;
import de.be4.classicalb.core.parser.node.AConstantDependenciesPredicate;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AIfSubstitution;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.AParallelSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class RulesMachineVisitor extends DepthFirstAdapter {
	@SuppressWarnings("unused") // TODO file name must match machine name
	private final String fileName;
	protected final ArrayList<CheckException> errorlist = new ArrayList<>();
	protected final Hashtable<Node, Node> ruleAssignmentTable = new Hashtable<>();
	protected final LinkedHashSet<TIdentifierLiteral> rulesWithCounterExamples = new LinkedHashSet<>();

	private ARuleOperation currentRuleOperation = null;

	public RulesMachineVisitor(String fileName) {
		this.fileName = fileName;
	}

	public boolean hasCounterExamples(final TIdentifierLiteral ruleIdentifier) {
		return rulesWithCounterExamples.contains(ruleIdentifier);
	}

	private boolean isInRule() {
		return currentRuleOperation != null;
	}

	private void addCurrentRuleToCounterExampleRules() {
		rulesWithCounterExamples.add(currentRuleOperation.getRuleName());
	}

	public void inAChoiceSubstitution(AChoiceSubstitution node) {
		if (isInRule()) {
			errorlist.add(new CheckException("A CHOICE substitution is not allowed in rule operations", node));
		}
	}

	public void inACaseSubstitution(ACaseSubstitution node) {
		if (isInRule()) {
			errorlist.add(new CheckException("A CASE substitution is not allowed in rule operations", node));
		}
	}

	private void setParent(Node parent, Node value) {
		if (ruleAssignmentTable.containsKey(parent)) {
			if (parent instanceof ASequenceSubstitution || parent instanceof AParallelSubstitution) {
				errorlist.add(new CheckException("Result value of rule operation is assigned more than once", value));
			}
		} else {
			ruleAssignmentTable.put(parent, value);
		}
	}

	private boolean resultIsSet(Node node) {
		return ruleAssignmentTable.containsKey(node);
	}

	@Override
	public void inAMachineHeader(AMachineHeader node) {
		if (node.getParameters().size() > 0) {
			errorlist.add(new CheckException("A RULES_MACHINE must not have any machine parameters", node));
		}

	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		currentRuleOperation = node;
		node.getRuleBody().apply(this);
		currentRuleOperation = null;
		if (!ruleAssignmentTable.containsKey(node)) {
			errorlist.add(new CheckException("No result value assigned in rule operation", node));
		}
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
	public void outAIfSubstitution(AIfSubstitution node) {
		if (isInRule()) {
			if (resultIsSet(node.getThen())) {
				if (node.getElse() == null) {
					errorlist.add(new CheckException(
							"There must be an ELSE branch if a result value is set in the THEN branch", node));
				} else if (!resultIsSet(node.getElse())) {
					errorlist.add(
							new CheckException("Result value is set in the THEN branch but not in ELSE branch", node));
				}

				final LinkedList<PSubstitution> elsifSubstitutions = node.getElsifSubstitutions();
				for (PSubstitution pSubstitution : elsifSubstitutions) {
					if (!resultIsSet(pSubstitution)) {
						errorlist.add(new CheckException(
								"Result value is set in the THEN branch but not in ELSIF branch", pSubstitution));
					}
				}
			} else {
				// no result set in THEN
				if (node.getElse() != null && resultIsSet(node.getElse())) {
					errorlist.add(new CheckException(
							"Result value is not set in the THEN branch but set in the ELSE branch", node));

				}
				final LinkedList<PSubstitution> elsifSubstitutions = node.getElsifSubstitutions();
				for (PSubstitution pSubstitution : elsifSubstitutions) {
					if (resultIsSet(pSubstitution)) {
						errorlist.add(new CheckException(
								"Result value is not set in the THEN branch but set in ELSIF branch", pSubstitution));
					}
				}

			}
			defaultOut(node);
		}

	}

	@Override
	public void outAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RuleGrammar.RULE_SUCCESS: {
			if (!isInRule()) {
				errorlist.add(new CheckException("RULE_SUCCESS used outside of a RULE operation", node));
			}
			if (node.getExpression() != null) {
				errorlist.add(new CheckException("RULE_SUCCESS must not have an argument.", node));
			}

			ruleAssignmentTable.put(node, node);
			defaultOut(node);
			return;
		}
		case RuleGrammar.RULE_FAIL: {
			if (!isInRule()) {
				errorlist.add(new CheckException("RULE_FAIL used outside of a RULE operation", node));
			}
			ruleAssignmentTable.put(node, node);
			if (node.getExpression() != null) {
				addCurrentRuleToCounterExampleRules();
			}
			defaultOut(node);
			return;
		}
		default:
			throw new RuntimeException("should not happen: " + operatorName);
		}
	}

	@Override
	public void outAConstantDependenciesPredicate(AConstantDependenciesPredicate node) {

		if (!isInRule()) {
			errorlist.add(new CheckException("CONSTANT_DEPENDENCIES used outside of a RULE operation", node));
		}
		node.getCondition().apply(this);
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
		if (!isInRule()) {
			errorlist.add(new CheckException("RULE_FORALL used outside of a RULE operation", node));
		}
		ruleAssignmentTable.put(node, node);
		addCurrentRuleToCounterExampleRules();
		defaultOut(node);
	}
}
