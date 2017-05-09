package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

	public static String GOAL = "GOAL";
	final HashMap<String, AbstractOperation> allOperations;
	final RulesParseUnit mainModel;
	final HashMap<String, RuleGoalAssumption> rulesGoalAssumptions = new HashMap<>();

	public RulesMachineRunConfiguration(IModel mainModel, HashMap<String, AbstractOperation> allOperations) {
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
			if (name.equals("GOAL")) {
				RulesInGoalFinder rulesInGoalFinder = new RulesInGoalFinder();
				node.getRhs().apply(rulesInGoalFinder);
			}
		}

	}

	class RulesInGoalFinder extends DepthFirstAdapter {
		public void caseAOperatorExpression(AOperatorExpression node) {
			final String operatorName = node.getName().getText();
			if (operatorName.equals(RulesGrammar.GET_RULE_COUNTEREXAMPLES)) {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(node.getIdentifiers().get(0));
				ruleGoalAssumption.setCheckedForCounterexamples();
			}
		}

		private RuleGoalAssumption getRuleCoverage(PExpression pExpression) {
			AIdentifierExpression identifier = (AIdentifierExpression) pExpression;
			String ruleName = Utils.getTIdentifierListAsString(identifier.getIdentifier());
			RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(ruleName);
			return ruleGoalAssumption;
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
			final List<PExpression> arguments = new ArrayList<PExpression>(node.getIdentifiers());
			final String operatorName = node.getName().getText();
			switch (operatorName) {
			case RulesGrammar.SUCCEEDED_RULE: {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(arguments.get(0));
				ruleGoalAssumption.setSuccessCompletelyTested();
				return;
			}
			case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE: {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(arguments.get(0));
				AIntegerExpression intExpr = (AIntegerExpression) arguments.get(1);
				String text = intExpr.getLiteral().getText();
				int errorType = Integer.parseInt(text);
				ruleGoalAssumption.addErrorTypeAssumedToSucceed(errorType);
				return;
			}
			case RulesGrammar.FAILED_RULE: {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(arguments.get(0));
				ruleGoalAssumption.setFailCompletelyTested();
				return;
			}
			case RulesGrammar.FAILED_RULE_ERROR_TYPE: {
				RuleGoalAssumption ruleGoalAssumption = getRuleCoverage(arguments.get(0));
				AIntegerExpression intExpr = (AIntegerExpression) arguments.get(1);
				String text = intExpr.getLiteral().getText();
				int errorType = Integer.parseInt(text);
				ruleGoalAssumption.addErrorTypeAssumedToFail(errorType);
				return;
			}
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

		public HashSet<Integer> getErrorTypesAssumedToFail() {
			return new HashSet<>(errorTypesAssumedToFail);
		}

		public HashSet<Integer> getErrorTypesAssumedToSucceed() {
			return new HashSet<>(errorTypesAssumedToSucceed);
		}

		public boolean isCheckedForCounterexamples() {
			return this.checkedForCounterexamples;

		}

	}

}
