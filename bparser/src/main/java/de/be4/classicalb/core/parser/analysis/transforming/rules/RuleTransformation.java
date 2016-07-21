package de.be4.classicalb.core.parser.analysis.transforming.rules;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.analysis.transforming.DefinitionInjector;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.ACardExpression;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.AConstantDependenciesPredicate;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.AEmptySetExpression;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AExpectCounterexampleSubstitution;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AForLoopSubstitution;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AForallSubSubstitution;
import de.be4.classicalb.core.parser.node.AGreaterPredicate;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIfSubstitution;
import de.be4.classicalb.core.parser.node.AIfThenElseExpression;
import de.be4.classicalb.core.parser.node.AImplicationPredicate;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AMemberPredicate;
import de.be4.classicalb.core.parser.node.AMinusOrSetSubtractExpression;
import de.be4.classicalb.core.parser.node.ANaturalSetExpression;
import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.AOperation;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.AParallelSubstitution;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ASelectSubstitution;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.AStringSetExpression;
import de.be4.classicalb.core.parser.node.ATotalFunctionExpression;
import de.be4.classicalb.core.parser.node.AUnionExpression;
import de.be4.classicalb.core.parser.node.AVarSubstitution;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.AWhileSubstitution;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.be4.classicalb.core.parser.node.TStringLiteral;

public class RuleTransformation extends DepthFirstAdapter {

	public static final String RULE_FAIL = "FAIL";
	public static final String RULE_SUCCESS = "SUCCESS";
	public static final String RULE_NOT_CHECKED = "NOT_CHECKED";
	public static final String RULE_DISABLED = "DISABLED";
	public static final String RULE_RESULT_OUTPUT_PARAMETER_NAME = "#RESULT";
	public static final String RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME = "#COUNTEREXAMPLE";
	public static final String RULE_COUNTER_EXAMPLE_SET = "#CT_SET";
	public static final String RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX = "_Counterexamples";

	private final IDefinitions definitions;
	private final Start start;
	private final RulesMachineVisitor rulesMachineVisitor;
	private AAbstractMachineParseUnit abstractMachineParseUnit = null;
	private AVariablesMachineClause variablesMachineClause = null;
	private AInvariantMachineClause invariantMachineClause = null;
	private AInitialisationMachineClause initialisationMachineClause = null;
	private ArrayList<String> ruleNames = new ArrayList<>();
	private ArrayList<TIdentifierLiteral> ruleOperationLiteralList = new ArrayList<>();
	private Hashtable<TIdentifierLiteral, PPredicate> ruleConstantDependencies = new Hashtable<TIdentifierLiteral, PPredicate>();
	private TIdentifierLiteral currentRuleLiteral = null;

	public RuleTransformation(Start start, BParser bParser) {
		this.start = start;
		this.definitions = bParser.getDefinitions();
		this.rulesMachineVisitor = new RulesMachineVisitor();
	}

	public void runTransformation() throws CheckException {
		start.apply(rulesMachineVisitor);
		if (rulesMachineVisitor.errorlist.size() > 0) {
			throw rulesMachineVisitor.errorlist.get(0);
		}
		start.apply(this);
		DefinitionInjector.injectDefinitions(start, definitions);
	}

	@Override
	public void outStart(Start node) {
		ClauseFinder finder = new ClauseFinder();
		node.apply(finder);
		if (ruleNames.size() > 0) {
			abstractMachineParseUnit = finder.abstractMachineParseUnit;
			if (finder.variablesMachineClause == null) {
				variablesMachineClause = new AVariablesMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(0, variablesMachineClause);
				invariantMachineClause = new AInvariantMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(1, invariantMachineClause);
				initialisationMachineClause = new AInitialisationMachineClause();
				abstractMachineParseUnit.getMachineClauses().add(2, initialisationMachineClause);
			} else {
				variablesMachineClause = finder.variablesMachineClause;
				invariantMachineClause = finder.invariantMachineClause;
				initialisationMachineClause = finder.initialisationMachineClause;
			}

			ArrayList<PPredicate> invariantPredicateList = new ArrayList<>();
			if (invariantMachineClause.getPredicates() != null) {
				invariantPredicateList.add(invariantMachineClause.getPredicates());
			}
			ArrayList<PExpression> initialisationIdentifierList = new ArrayList<>();
			ArrayList<PExpression> initialisationExpressionList = new ArrayList<>();

			for (TIdentifierLiteral ruleLiteral : ruleOperationLiteralList) {
				// VARIABLES
				variablesMachineClause.getIdentifiers().add(createRuleIdentifier(ruleLiteral));

				AMemberPredicate member = new AMemberPredicate();
				member.setLeft(createRuleIdentifier(ruleLiteral));
				List<PExpression> list = new ArrayList<>();
				list.add(createStringExpression(RULE_FAIL));
				list.add(createStringExpression(RULE_SUCCESS));
				list.add(createStringExpression(RULE_NOT_CHECKED));
				list.add(createStringExpression(RULE_DISABLED));
				ASetExtensionExpression set = new ASetExtensionExpression(list);
				member.setRight(set);
				invariantPredicateList.add(member);

				/*
				 * If there are no constant dependencies: rule :=
				 * RULE_NOT_CHECKED Otherwise: rule := IF dependencies THEN
				 * RULE_NOT_CHECKED ELSE RULE_DISABLED END
				 */
				initialisationIdentifierList.add(createRuleIdentifier(ruleLiteral));
				if (ruleConstantDependencies.containsKey(ruleLiteral)) {
					AIfThenElseExpression ifThenElse = new AIfThenElseExpression(
							ruleConstantDependencies.get(ruleLiteral), createStringExpression(RULE_NOT_CHECKED),
							createStringExpression(RULE_DISABLED));
					initialisationExpressionList.add(ifThenElse);

				} else {
					initialisationExpressionList.add(createStringExpression(RULE_NOT_CHECKED));
				}

				if (rulesMachineVisitor.hasCounterExamples(ruleLiteral)) {
					// VARIABLES ...
					String ctName = ruleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
					variablesMachineClause.getIdentifiers().add(createIdentifier(ctName));

					// INVARIANT rule1#counterexamples : POW(STRING)
					AMemberPredicate ctTypingPredicate = new AMemberPredicate();
					ctTypingPredicate.setLeft(createIdentifier(ctName));
					ctTypingPredicate.setRight(new APowSubsetExpression(new AStringSetExpression()));
					invariantPredicateList.add(ctTypingPredicate);

					// INITIALISATION rule1#counterexamples := {}
					initialisationExpressionList.add(new AEmptySetExpression());
					initialisationIdentifierList.add(createIdentifier(ctName));
				}

			}
			PPredicate conjunction = createConjunction(invariantPredicateList);
			invariantMachineClause.setPredicates(conjunction);

			AAssignSubstitution assign = new AAssignSubstitution(initialisationIdentifierList,
					initialisationExpressionList);
			if (initialisationMachineClause.getSubstitutions() != null) {
				ArrayList<PSubstitution> subList = new ArrayList<>();
				subList.add(initialisationMachineClause.getSubstitutions());
				subList.add(assign);
				AParallelSubstitution parallelSub = new AParallelSubstitution(subList);
				initialisationMachineClause.setSubstitutions(parallelSub);
			} else {
				initialisationMachineClause.setSubstitutions(assign);
			}

		}

	}

	private AStringExpression createStringExpression(String string) {
		return new AStringExpression(new TStringLiteral(string));
	}

	private AIdentifierExpression createRuleIdentifier(TIdentifierLiteral ruleLiteral) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add((TIdentifierLiteral) cloneNode((ruleLiteral)));
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

	@SuppressWarnings("unused")
	private static List<PSubstitution> createSubstitutionList(PSubstitution pSubstitution) {
		List<PSubstitution> list = new ArrayList<>();
		list.add(pSubstitution);
		return list;
	}

	private static List<PExpression> createExpressionList(PExpression pExpression) {
		List<PExpression> list = new ArrayList<>();
		list.add(pExpression);
		return list;
	}

	private static AIdentifierExpression createIdentifier(String name) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		return new AIdentifierExpression(list);
	}

	private static PExpression createIdentifier(String name, PExpression positionNode) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		AIdentifierExpression result = new AIdentifierExpression(list);
		result.setStartPos(positionNode.getStartPos());
		result.setEndPos(positionNode.getEndPos());
		return result;
	}

	class ClauseFinder extends DepthFirstAdapter {
		AAbstractMachineParseUnit abstractMachineParseUnit = null;
		AVariablesMachineClause variablesMachineClause = null;
		AInvariantMachineClause invariantMachineClause = null;
		AInitialisationMachineClause initialisationMachineClause = null;

		@Override
		public void inAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
			abstractMachineParseUnit = node;
		}

		@Override
		public void inAVariablesMachineClause(AVariablesMachineClause node) {
			variablesMachineClause = node;
		}

		public void inAInvariantMachineClause(AInvariantMachineClause node) {
			invariantMachineClause = node;
		}

		public void inAInitialisationMachineClause(AInitialisationMachineClause node) {
			initialisationMachineClause = node;
		}
	}

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
		nameList.add((TIdentifierLiteral) cloneNode(node.getRuleName()));
		operation.setOpName(nameList);

		// SELECT ruleName /= "NOT_CHECKED" & $COUNTEREXAMPLE : POW(STRING) THEN
		// ... END
		AEqualPredicate grd1 = new AEqualPredicate(createIdentifier(ruleName),
				new AStringExpression(new TStringLiteral(RULE_NOT_CHECKED)));
		ASelectSubstitution select = new ASelectSubstitution();

		rulesMachineVisitor.hasCounterExamples(null);
		rulesMachineVisitor.rulesWithCounterExamples.add(null);
		if (rulesMachineVisitor.hasCounterExamples(node.getRuleName())) {
			AMemberPredicate grd2 = new AMemberPredicate(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME),
					new APowSubsetExpression(new AStringSetExpression()));
			select.setCondition(new AConjunctPredicate(grd1, grd2));
		} else {
			select.setCondition(grd1);
		}

		select.setThen(node.getRuleBody());
		// select.setThen(node.getRuleBody());
		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));

		if (rulesMachineVisitor.hasCounterExamples(node.getRuleName())) {
			returnValues.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		}
		operation.setReturnValues(returnValues);
		operation.setOperationBody(select);

		node.replaceBy(operation); // replacing the rule operation by an
									// ordinary operation
	}

	@SuppressWarnings("unused")
	private PSubstitution createAssignNode(PExpression id, PExpression value) {
		ArrayList<PExpression> nameList = new ArrayList<>();
		nameList.add(id);
		ArrayList<PExpression> exprList = new ArrayList<>();
		exprList.add(value);
		return new AAssignSubstitution(nameList, exprList);
	}

	@Override
	public void caseAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RuleGrammar.RULE_SUCCESS: {
			AAssignSubstitution assign = createRuleSuccessAssignment();
			node.replaceBy(assign);
			return;
		}
		case RuleGrammar.RULE_FAIL: {
			if (node.getExpression() != null) {
				final AAssignSubstitution assign = createRuleFailAssignment(node.getExpression());
				node.replaceBy(assign);
			} else {
				final AAssignSubstitution assign = createRuleFailAssignment(new AEmptySetExpression());
				node.replaceBy(assign);
			}
			return;
		}
		default:
			throw new RuntimeException("should not happen");
		}
	}

	@Override
	public void caseAConstantDependenciesPredicate(AConstantDependenciesPredicate node) {
		PPredicate condition = node.getCondition();
		ruleConstantDependencies.put(currentRuleLiteral, (PPredicate) cloneNode(condition));
		node.replaceBy(condition);
		condition.apply(this);
	}

	private AAssignSubstitution createRuleSuccessAssignment() {
		final ArrayList<PExpression> nameList = new ArrayList<>();
		final ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(currentRuleLiteral));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));

		if (rulesMachineVisitor.hasCounterExamples(currentRuleLiteral)) {
			nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
			exprList.add(new AEmptySetExpression());
		}
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
	}

	private PExpression createSetOfPExpression(PExpression expression) {
		final ArrayList<PExpression> list = new ArrayList<>();
		list.add((PExpression) cloneNode(expression));
		return new ASetExtensionExpression(list);
	}

	private AAssignSubstitution createRuleFailAssignment(PExpression setOfCounterexamples) {
		String ctName = currentRuleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();

		nameList.add(createRuleIdentifier(currentRuleLiteral));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));

		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));

		if (rulesMachineVisitor.hasCounterExamples(currentRuleLiteral)) {
			nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
			exprList.add((PExpression) cloneNode(setOfCounterexamples));

			nameList.add(createIdentifier(ctName));
			exprList.add((PExpression) cloneNode(setOfCounterexamples));
		}

		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
	}

	@Override
	public void caseAOperatorPredicate(AOperatorPredicate node) {
		final List<PExpression> copy = new ArrayList<PExpression>(node.getIdentifiers());
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RuleGrammar.SUCCEEDED_RULES:
		case RuleGrammar.DEPENDS_ON_RULES:
			replacePredicateOperator(node, copy, RULE_SUCCESS);
			return;
		case RuleGrammar.FAILED_RULES:
			replacePredicateOperator(node, copy, RULE_FAIL);
			return;
		case RuleGrammar.NOT_CHECKED_RULES:
			replacePredicateOperator(node, copy, RULE_NOT_CHECKED);
			return;
		default:
			throw new RuntimeException("should not happen");
		}
	}

	private void replacePredicateOperator(final AOperatorPredicate node, List<PExpression> copy,
			final String ruleState) {
		final List<PPredicate> predList = new ArrayList<>();
		for (PExpression e : copy) {
			final AEqualPredicate equal = new AEqualPredicate(e, new AStringExpression(new TStringLiteral(ruleState)));
			equal.setStartPos(e.getStartPos());
			equal.setEndPos(e.getEndPos());
			predList.add(equal);
		}
		final PPredicate conjunction = createConjunction(predList);
		node.replaceBy(conjunction);
	}

	@Override
	public void outAForLoopSubstitution(AForLoopSubstitution node) {
		/**
		 * FOR x IN set DO sub END
		 **/

		final String localSetVariableName = "$SET";

		// G_Set := set
		final PSubstitution assignSetVariable = new AAssignSubstitution(
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())),
				createExpressionList((PExpression) cloneNode(node.getSet())));

		final AWhileSubstitution whileSub = new AWhileSubstitution();
		final List<PSubstitution> subList = new ArrayList<>();
		subList.add(assignSetVariable);
		subList.add(whileSub);
		final AVarSubstitution varSub = new AVarSubstitution(
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())),
				new ASequenceSubstitution(subList));

		// WHILE card(set) > 0
		final PPredicate whileCon = new AGreaterPredicate(
				new ACardExpression(createIdentifier(localSetVariableName, node.getSet())),
				new AIntegerExpression(new TIntegerLiteral("0")));
		whileSub.setCondition(whileCon);
		// INVARIANT card(set) : NATURAL
		final PPredicate whileInvariant = new AMemberPredicate(
				new ACardExpression(createIdentifier(localSetVariableName, node.getSet())),
				new ANaturalSetExpression());
		whileSub.setInvariant(whileInvariant);

		// VARIANT card(set)
		final PExpression whileVariant = new ACardExpression(createIdentifier(localSetVariableName, node.getSet()));
		whileSub.setVariant(whileVariant);

		// VAR x IN ...
		final AVarSubstitution varSub2 = new AVarSubstitution();
		whileSub.setDoSubst(varSub2);
		varSub2.setIdentifiers(createExpressionList((PExpression) cloneNode(node.getIdentifier())));

		// x := CHOOSE(set);
		addChooseDefinition();
		PExpression chooseCall = new ADefinitionExpression(new TIdentifierLiteral("CHOOSE"),
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())));
		PSubstitution assignX = new AAssignSubstitution(
				createExpressionList((PExpression) cloneNode(node.getIdentifier())), createExpressionList(chooseCall));

		// G_Set := G_Set \ {CHOOSE(G_Set)}

		PExpression chooseCall2 = new ADefinitionExpression(new TIdentifierLiteral("CHOOSE"),
				createExpressionList(createIdentifier(localSetVariableName, node.getSet()))); // CHOOSE(G_Set)

		PExpression rhs = new AMinusOrSetSubtractExpression(createIdentifier(localSetVariableName, node.getSet()),
				new ASetExtensionExpression(createExpressionList(chooseCall2))); // G_Set
																					// \
																					// {CHOOSE(G_Set)}

		PSubstitution assignSetVariable2 = new AAssignSubstitution(
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())), createExpressionList(rhs)); // G_Set
																															// :=
																															// G_Set
																															// \
																															// {CHOOSE(G_Set)}
		List<PSubstitution> var2List = new ArrayList<>();
		var2List.add(assignX);
		var2List.add(node.getDoSubst());
		var2List.add(assignSetVariable2);
		varSub2.setSubstitution(new ASequenceSubstitution(var2List));

		node.replaceBy(varSub);
	}

	@Override
	public void outAForallSubSubstitution(AForallSubSubstitution node) {
		/**
		 * FORALL x,y WHERE P(x,y) EXPECT F(x,y) THEN Substitution ELSE
		 * Substitution(x,y) END
		 * 
		 * 
		 * IF !x,y. ( P(x,y) => F(x,y) ) THEN Substitution ELSE ANY x,y WHERE
		 * P(x,y) & not(F(x,y)) THEN Substitution(x,y) END END
		 **/

		final List<PExpression> list1 = new ArrayList<PExpression>();
		final List<PExpression> list2 = new ArrayList<PExpression>();
		for (PExpression id : node.getIdentifiers()) {
			list1.add((PExpression) cloneNode(id));
			list2.add((PExpression) cloneNode(id));
		}

		final PPredicate where1 = (PPredicate) cloneNode(node.getWhere());
		final PPredicate where2 = (PPredicate) cloneNode(node.getWhere());
		final PPredicate expect1 = (PPredicate) cloneNode(node.getExpect());
		final PPredicate expect2 = (PPredicate) cloneNode(node.getExpect());

		final AForallPredicate forAllPred = new AForallPredicate(list1, new AImplicationPredicate(where1, expect1));

		final AAnySubstitution anySub = new AAnySubstitution(list2,
				new AConjunctPredicate(where2, new ANegationPredicate(expect2)), node.getElse());
		List<PSubstitution> subList = new ArrayList<>();

		final AIfSubstitution ifElseSub = new AIfSubstitution(forAllPred, node.getThen(), subList, anySub);
		node.replaceBy(ifElseSub);
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {

		final ALetSubstitution letSub = new ALetSubstitution();
		final List<PExpression> letList = new ArrayList<PExpression>();
		letList.add(createIdentifier(RULE_COUNTER_EXAMPLE_SET));
		letSub.setIdentifiers(letList);

		// mandatory union
		final AQuantifiedUnionExpression union = new AQuantifiedUnionExpression();
		{
			final List<PExpression> list = new ArrayList<PExpression>();
			for (PExpression id : node.getIdentifiers()) {
				PExpression clonedId = (PExpression) cloneNode(id);
				list.add(clonedId);
			}
			union.setIdentifiers(list);
			final PPredicate where = (PPredicate) cloneNode(node.getWhere());
			final PPredicate expect = (PPredicate) cloneNode(node.getExpect());
			union.setPredicates(new AConjunctPredicate(where, new ANegationPredicate(expect)));
			final List<PExpression> setElementsInUnionList = new ArrayList<PExpression>();
			setElementsInUnionList.add(node.getMessage());
			final ASetExtensionExpression set = new ASetExtensionExpression(setElementsInUnionList);
			union.setExpression(set);
		}
		List<PExpression> unionList = new ArrayList<>();
		for (Node n : node.getExpects()) {
			AExpectCounterexampleSubstitution ec = (AExpectCounterexampleSubstitution) n;
			final List<PExpression> list = new ArrayList<PExpression>();
			for (PExpression id : node.getIdentifiers()) {
				PExpression clonedId = (PExpression) cloneNode(id);
				list.add(clonedId);
			}
			final AQuantifiedUnionExpression u = new AQuantifiedUnionExpression();
			u.setIdentifiers(list);
			final PPredicate w = (PPredicate) cloneNode(node.getWhere());
			final PPredicate e = (PPredicate) cloneNode(ec.getExpect());
			u.setPredicates(new AConjunctPredicate(w, new ANegationPredicate(e)));
			final List<PExpression> elementsList = new ArrayList<PExpression>();
			elementsList.add(ec.getMessage());

			u.setExpression(createSetOfPExpression((PExpression) ec.getMessage()));
			unionList.add(u);
		}

		PExpression all = union;
		for (PExpression other : unionList) {
			all = new AUnionExpression(all, other);
		}

		final AEqualPredicate letEqual = new AEqualPredicate(createIdentifier(RULE_COUNTER_EXAMPLE_SET), all);
		letSub.setPredicate(letEqual);

		final AEqualPredicate ifPred = new AEqualPredicate(createIdentifier(RULE_COUNTER_EXAMPLE_SET),
				new AEmptySetExpression());

		addToStringDefinition();

		ADefinitionExpression toStringCall = new ADefinitionExpression();
		toStringCall.setDefLiteral(new TIdentifierLiteral("TO_STRING"));
		ArrayList<PExpression> definitionParameters = new ArrayList<>();
		definitionParameters.add(createIdentifier(RULE_COUNTER_EXAMPLE_SET));
		toStringCall.setParameters(definitionParameters);
		final AIfSubstitution ifElseSub = new AIfSubstitution(ifPred, createRuleSuccessAssignment(),
				new ArrayList<PSubstitution>(), createRuleFailAssignment(createIdentifier(RULE_COUNTER_EXAMPLE_SET)));
		letSub.setSubstitution(ifElseSub);

		node.replaceBy(letSub);
	}

	private void addToStringDefinition() {
		// TO_STRING(S) == "0";
		// EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
		AExpressionDefinitionDefinition toStringDef = new AExpressionDefinitionDefinition();
		toStringDef.setName(new TIdentifierLiteral("TO_STRING"));
		toStringDef.setParameters(createIdentifierList("S"));
		toStringDef.setRhs(new AStringExpression(new TStringLiteral("0")));
		definitions.addDefinition(toStringDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_TO_STRING"));
		toStringTypeDef.setParameters(createIdentifierList("X"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(createIdentifier("X"), new AStringSetExpression()));
		definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
	}

	private void addChooseDefinition() {
		// TO_STRING(S) == "0";
		// EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
		AExpressionDefinitionDefinition ChooseDef = new AExpressionDefinitionDefinition();
		ChooseDef.setName(new TIdentifierLiteral("CHOOSE"));
		ChooseDef.setParameters(createIdentifierList("X"));
		ChooseDef.setRhs(new AStringExpression(new TStringLiteral("a member of X")));
		definitions.addDefinition(ChooseDef, IDefinitions.Type.Expression);

		AExpressionDefinitionDefinition chooseDefType = new AExpressionDefinitionDefinition();
		chooseDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_CHOOSE"));
		chooseDefType.setParameters(createIdentifierList("T"));
		chooseDefType.setRhs(
				new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")), createIdentifier("T")));
		definitions.addDefinition(chooseDefType, IDefinitions.Type.Expression);
	}

	private List<PExpression> createIdentifierList(String string) {
		ArrayList<PExpression> list = new ArrayList<>();
		list.add(createIdentifier(string));
		return list;
	}

	@Override
	public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
		// skip properties clause
	}

}
