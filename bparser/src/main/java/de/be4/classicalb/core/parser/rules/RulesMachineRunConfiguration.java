package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AOperatorExpression;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.util.Utils;

/**
 * This class traverses the AST of the main machine of a RulesProject. Its
 * collects the configurations stored under the DEFINITIONS clause such as GOAL
 * and preferences.
 */
public class RulesMachineRunConfiguration {

	public static final String GOAL = "GOAL";
	final Map<String, AbstractOperation> allOperations;
	final RulesParseUnit mainModel;
	final Map<String, RuleGoalAssumption> rulesGoalAssumptions = new HashMap<>();

	public RulesMachineRunConfiguration(IModel mainModel, Map<String, AbstractOperation> allOperations) {
		this.mainModel = (RulesParseUnit) mainModel;
		this.allOperations = allOperations;
	}

	public void collect() {
		DefinitionsFinder definitionsFinder = new DefinitionsFinder();
		mainModel.getStart().apply(definitionsFinder);
	}

	public Set<RuleGoalAssumption> getRulesGoalAssumptions() {
		return new HashSet<>(this.rulesGoalAssumptions.values());
	}

	class DefinitionsFinder extends DepthFirstAdapter {
		@Override
		public void caseAPredicateDefinitionDefinition(APredicateDefinitionDefinition node) {
			final String name = node.getName().getText();
			if (GOAL.equals(name)) {
				RulesInGoalFinder rulesInGoalFinder = new RulesInGoalFinder();
				node.getRhs().apply(rulesInGoalFinder);
			}
		}

	}

	class RulesInGoalFinder extends DepthFirstAdapter {
		@Override
		public void caseAOperatorExpression(AOperatorExpression node) {
			final String operatorName = node.getName().getText();
			if (RulesGrammar.GET_RULE_COUNTEREXAMPLES.equals(operatorName)) {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(node.getIdentifiers().get(0));
				ruleGoalAssumption.setCheckedForCounterexamples();
			}
		}

		private RuleGoalAssumption getRuleCoverage(PExpression pExpression) {
			AIdentifierExpression identifier = (AIdentifierExpression) pExpression;
			String ruleName = Utils.getTIdentifierListAsString(identifier.getIdentifier());
			return getRuleCoverage(ruleName);
		}

		private RuleGoalAssumption getRuleCoverage(String ruleName) {
			if (rulesGoalAssumptions.containsKey(ruleName)) {
				return rulesGoalAssumptions.get(ruleName);
			} else {
				RuleGoalAssumption ruleGoalAssumption = new RuleGoalAssumption(ruleName,
						(RuleOperation) allOperations.get(ruleName));
				rulesGoalAssumptions.put(ruleName, ruleGoalAssumption);
				return ruleGoalAssumption;
			}
		}

		@Override
		public void caseAOperatorPredicate(AOperatorPredicate node) {
			final List<PExpression> arguments = new ArrayList<>(node.getIdentifiers());
			final String operatorName = node.getName().getText();
			switch (operatorName) {
			case RulesGrammar.SUCCEEDED_RULE:
				getRuleCoverage(arguments.get(0)).setSuccessCompletelyTested();
				return;
			case RulesGrammar.FAILED_RULE:
				getRuleCoverage(arguments.get(0)).setFailCompletelyTested();
				return;
			case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE:
			case RulesGrammar.FAILED_RULE_ERROR_TYPE:
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(arguments.get(0));
				AIntegerExpression intExpr = (AIntegerExpression) arguments.get(1);
				String text = intExpr.getLiteral().getText();
				int errorType = Integer.parseInt(text);
				if (RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE.equals(operatorName)) {
					getRuleCoverage(arguments.get(0)).addErrorTypeAssumedToSucceed(errorType);
				} else {
					ruleGoalAssumption.addErrorTypeAssumedToFail(errorType);
				}
				return;
			default:
				throw new AssertionError();
			}

		}

	}

	public class RuleGoalAssumption {
		final String name;
		final RuleOperation ruleOperation;
		final HashSet<Integer> errorTypesAssumedToFail = new HashSet<>();
		final HashSet<Integer> errorTypesAssumedToSucceed = new HashSet<>();
		boolean checkedForCounterexamples = false;

		public RuleGoalAssumption(String name, RuleOperation ruleOperation) {
			this.name = name;
			this.ruleOperation = ruleOperation;
		}

		public void setCheckedForCounterexamples() {
			this.checkedForCounterexamples = true;
		}

		public void setFailCompletelyTested() {
			Integer n = ruleOperation.getNumberOfErrorTypes();
			for (int i = 1; i <= n; i++) {
				errorTypesAssumedToFail.add(i);
			}
		}

		public void setSuccessCompletelyTested() {
			Integer n = ruleOperation.getNumberOfErrorTypes();
			for (int i = 1; i <= n; i++) {
				errorTypesAssumedToSucceed.add(i);
			}
		}

		public String getRuleName() {
			return this.name;
		}

		public RuleOperation getRuleOperation() {
			return this.ruleOperation;
		}

		public void addErrorTypeAssumedToFail(Integer i) {
			this.errorTypesAssumedToFail.add(i);
		}

		public void addErrorTypeAssumedToSucceed(Integer i) {
			this.errorTypesAssumedToSucceed.add(i);
		}

		public Set<Integer> getErrorTypesAssumedToFail() {
			return new HashSet<>(errorTypesAssumedToFail);
		}

		public Set<Integer> getErrorTypesAssumedToSucceed() {
			return new HashSet<>(errorTypesAssumedToSucceed);
		}

		public boolean isCheckedForCounterexamples() {
			return this.checkedForCounterexamples;

		}

	}

}
