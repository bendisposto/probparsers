package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AAssertionSubstitution;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.ADependsOnRulesPredicate;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AForallSubSubstitution;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIfSubstitution;
import de.be4.classicalb.core.parser.node.AImplicationPredicate;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.AMemberPredicate;
import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.ANotEqualPredicate;
import de.be4.classicalb.core.parser.node.AOperation;
import de.be4.classicalb.core.parser.node.AParallelSubstitution;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.ARuleFailSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ARuleSuccessSubstitution;
import de.be4.classicalb.core.parser.node.ASelectSubstitution;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;

public class RuleTransformation extends DepthFirstAdapter {

	public static final String RULE_FAIL = "FAIL";
	public static final String RULE_SUCCESS = "SUCCESS";
	public static final String RULE_NOT_CHECKED = "NOT_CHECKED";

	AAbstractMachineParseUnit abstractMachineParseUnit = null;
	AVariablesMachineClause variablesMachineClause = null;
	AInvariantMachineClause invariantMachineClause = null;
	AInitialisationMachineClause initialisationMachineClause = null;
	ArrayList<String> ruleNames = new ArrayList<>();
	ArrayList<TIdentifierLiteral> ruleOperationLiteralList = new ArrayList<>();

	@Override
	public void outStart(Start node) {
		ClauseFinder finder = new ClauseFinder();
		node.apply(finder);
		if (ruleNames.size() > 0) {
			abstractMachineParseUnit = finder.abstractMachineParseUnit;
			if (finder.variablesMachineClause == null) {
				variablesMachineClause = new AVariablesMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(0,
						variablesMachineClause);
				invariantMachineClause = new AInvariantMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(1,
						invariantMachineClause);
				initialisationMachineClause = new AInitialisationMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(2,
						initialisationMachineClause);
			} else {
				variablesMachineClause = finder.variablesMachineClause;
				invariantMachineClause = finder.invariantMachineClause;
				initialisationMachineClause = finder.initialisationMachineClause;
			}

			ArrayList<PPredicate> predList = new ArrayList<>();
			if (invariantMachineClause.getPredicates() != null) {
				predList.add(invariantMachineClause.getPredicates());
			}
			ArrayList<PExpression> nameList = new ArrayList<>();
			ArrayList<PExpression> exprList = new ArrayList<>();

			for (TIdentifierLiteral ruleLiteral : ruleOperationLiteralList) {
				variablesMachineClause.getIdentifiers().add(
						createRuleIdentifier(ruleLiteral));

				AMemberPredicate member = new AMemberPredicate();
				member.setLeft(createRuleIdentifier(ruleLiteral));
				List<PExpression> list = new ArrayList<>();
				list.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
				list.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
				list.add(new AStringExpression(new TStringLiteral(
						RULE_NOT_CHECKED)));
				ASetExtensionExpression set = new ASetExtensionExpression(list);
				member.setRight(set);
				predList.add(member);

				nameList.add(createRuleIdentifier(ruleLiteral));
				exprList.add(new AStringExpression(new TStringLiteral(
						RULE_NOT_CHECKED)));
			}
			PPredicate conjunction = createConjunction(predList);
			invariantMachineClause.setPredicates(conjunction);

			AAssignSubstitution assign = new AAssignSubstitution(nameList,
					exprList);
			if (initialisationMachineClause.getSubstitutions() != null) {
				ArrayList<PSubstitution> subList = new ArrayList<>();
				subList.add(initialisationMachineClause.getSubstitutions());
				subList.add(assign);
				AParallelSubstitution parallelSub = new AParallelSubstitution(
						subList);
				initialisationMachineClause.getSubstitutions().replaceBy(
						parallelSub);
			} else {
				initialisationMachineClause.setSubstitutions(assign);
			}

		}

	}

	private AIdentifierExpression createRuleIdentifier(
			TIdentifierLiteral ruleLiteral) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add((TIdentifierLiteral) ruleLiteral.clone());
		return new AIdentifierExpression(list);
	}

	private PPredicate createConjunction(List<PPredicate> predList) {
		if (predList.size() == 0) {
			return predList.get(0);
		} else {
			PPredicate p = predList.get(0);
			for (int i = 1; i < predList.size(); i++) {
				p = new AConjunctPredicate(p, predList.get(i));
			}
			return p;
		}
	}

	private AIdentifierExpression createIdentifier(String name) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		return new AIdentifierExpression(list);
	}

	class ClauseFinder extends DepthFirstAdapter {
		AAbstractMachineParseUnit abstractMachineParseUnit = null;
		AVariablesMachineClause variablesMachineClause = null;
		AInvariantMachineClause invariantMachineClause = null;
		AInitialisationMachineClause initialisationMachineClause = null;

		@Override
		public void caseAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
			abstractMachineParseUnit = node;
		}

		@Override
		public void inAVariablesMachineClause(AVariablesMachineClause node) {
			variablesMachineClause = node;
		}

		public void inAInvariantMachineClause(AInvariantMachineClause node) {
			invariantMachineClause = node;
		}

		public void inAInitialisationMachineClause(
				AInitialisationMachineClause node) {
			initialisationMachineClause = node;
		}
	}

	private TIdentifierLiteral currentRuleLiteral = null; 

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		final String ruleName = node.getRuleName().getText();
		currentRuleLiteral = node.getRuleName();
		ruleOperationLiteralList.add(node.getRuleName());
		ruleNames.add(ruleName);
		node.getRuleName().apply(this);
		node.getRuleBody().apply(this);
		AOperation operation = new AOperation();
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add(node.getRuleName());
		operation.setOpName(nameList);

		// SELECT ruleName /= "NOT_CHECKED" THEN ... END
		AEqualPredicate equal = new AEqualPredicate(createIdentifier(ruleName),
				new AStringExpression(new TStringLiteral(RULE_NOT_CHECKED)));
		ASelectSubstitution select = new ASelectSubstitution();
		select.setCondition(equal);

		// ASSERT ruleName /= "NOT_CHECKED" THEN skip END
		ANotEqualPredicate notEqual = new ANotEqualPredicate(
				createIdentifier(ruleName), new AStringExpression(
						new TStringLiteral(RULE_NOT_CHECKED)));

		AEqualPredicate ifPred = new AEqualPredicate(
				createIdentifier(ruleName), new AStringExpression(
						new TStringLiteral(RULE_SUCCESS)));
		// new AIfSubstitution(_condition_, _then_, _elsifSubstitutions_,
		// _else_)
		List<PSubstitution> elsIf = new ArrayList<>();
		AIfSubstitution ifSub = new AIfSubstitution(
				ifPred,
				createAssignNode(createIdentifier("res"),
						new AStringExpression(new TStringLiteral(RULE_SUCCESS))),
				elsIf,
				createAssignNode(createIdentifier("res"),
						new AStringExpression(new TStringLiteral(RULE_FAIL))));
		AAssertionSubstitution assertSub = new AAssertionSubstitution(notEqual,
				ifSub);

		ArrayList<PSubstitution> subList = new ArrayList<>();
		subList.add(node.getRuleBody());
		subList.add(assertSub);
		ASequenceSubstitution seqSub = new ASequenceSubstitution(subList);

		select.setThen(seqSub);
		//select.setThen(node.getRuleBody());

		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier("res"));
		returnValues.add(createIdentifier("ce"));
		operation.setReturnValues(returnValues);
		operation.setOperationBody(select);

		node.replaceBy(operation); // replacing the rule operation by an
									// ordinary operation
	}

	private PSubstitution createAssignNode(PExpression id, PExpression value) {
		ArrayList<PExpression> nameList = new ArrayList<>();
		nameList.add(id);
		ArrayList<PExpression> exprList = new ArrayList<>();
		exprList.add(value);
		return new AAssignSubstitution(nameList, exprList);
	}

	@Override
	public void caseARuleSuccessSubstitution(ARuleSuccessSubstitution node) {
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(currentRuleLiteral));
		nameList.add(createIdentifier("ce"));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		exprList.add(new AStringExpression(new TStringLiteral("")));
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		node.replaceBy(assign);
	}

	@Override
	public void caseARuleFailSubstitution(ARuleFailSubstitution node) {
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(currentRuleLiteral));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		nameList.add(createIdentifier("ce"));
		exprList.add(node.getExpression());
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		node.replaceBy(assign);
	}

	@Override
	public void caseADependsOnRulesPredicate(ADependsOnRulesPredicate node) {
		List<PExpression> copy = new ArrayList<PExpression>(
				node.getIdentifiers());
		List<PPredicate> predList = new ArrayList<>();
		for (PExpression e : copy) {
			AEqualPredicate equal = new AEqualPredicate(e,
					new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
			predList.add(equal);
		}
		PPredicate conjunction = createConjunction(predList);
		node.replaceBy(conjunction);
	}

	@Override
	public void outAForallSubSubstitution(AForallSubSubstitution node) {
		/**
		 * FORALL x,y WHERE P(x,y) ASSERT F(x,y) THEN Substitution ELSE
		 * Substitution(x,y) END
		 * 
		 * 
		 * IF !x,y. ( P(x,y) => F(x,y) ) THEN Substitution ELSE ANY x,y WHERE
		 * P(x,y) & not(F(x,y)) THEN Substitution(x,y) END END
		 **/

		final List<PExpression> list1 = new ArrayList<PExpression>();
		final List<PExpression> list2 = new ArrayList<PExpression>();
		for (PExpression id : node.getIdentifiers()) {
			list1.add((PExpression) id.clone());
			list2.add((PExpression) id.clone());
		}

		final PPredicate where1 = (PPredicate) node.getWhere().clone();
		final PPredicate where2 = (PPredicate) node.getWhere().clone();
		final PPredicate expect1 = (PPredicate) node.getExpect().clone();
		final PPredicate expect2 = (PPredicate) node.getExpect().clone();

		final AForallPredicate forAllPred = new AForallPredicate(list1,
				new AImplicationPredicate(where1, expect1));

		final AAnySubstitution anySub = new AAnySubstitution(
				list2,
				new AConjunctPredicate(where2, new ANegationPredicate(expect2)),
				node.getElse());
		List<PSubstitution> subList = new ArrayList<>();

		final AIfSubstitution ifElseSub = new AIfSubstitution(forAllPred,
				node.getThen(), subList, anySub);
		node.replaceBy(ifElseSub);
	}

}
