package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AAssignSubstitution;
import de.be4.classicalb.core.parser.node.ACaseSubstitution;
import de.be4.classicalb.core.parser.node.AChoiceSubstitution;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.ADefinitionExpression;
import de.be4.classicalb.core.parser.node.ADependsOnRulesPredicate;
import de.be4.classicalb.core.parser.node.AEmptySetExpression;
import de.be4.classicalb.core.parser.node.AEqualPredicate;
import de.be4.classicalb.core.parser.node.AExpectCounterexampleSubstitution;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.AForallSubMessageSubstitution;
import de.be4.classicalb.core.parser.node.AForallSubSubstitution;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIfSubstitution;
import de.be4.classicalb.core.parser.node.AImplicationPredicate;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AMemberPredicate;
import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.AOperation;
import de.be4.classicalb.core.parser.node.AParallelSubstitution;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.APowSubsetExpression;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.ARuleFailSubstitution;
import de.be4.classicalb.core.parser.node.ARuleOperation;
import de.be4.classicalb.core.parser.node.ARuleSuccessSubstitution;
import de.be4.classicalb.core.parser.node.ASelectSubstitution;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.AStringSetExpression;
import de.be4.classicalb.core.parser.node.ATotalFunctionExpression;
import de.be4.classicalb.core.parser.node.AUnionExpression;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
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
	public static final String RULE_RESULT_OUTPUT_PARAMETER_NAME = "#RESULT";
	public static final String RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME = "#COUNTEREXAMPLE";
	public static final String RULE_EMPTY_MESSAGE = "";
	public static final String RULE_COUNTER_EXAMPLE_SET = "#CT_SET";
	public static final String RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX = "_Counterexamples";

	private final IDefinitions definitions;
	private final Start start;
	private AAbstractMachineParseUnit abstractMachineParseUnit = null;
	private AVariablesMachineClause variablesMachineClause = null;
	private AInvariantMachineClause invariantMachineClause = null;
	private AInitialisationMachineClause initialisationMachineClause = null;
	private ArrayList<String> ruleNames = new ArrayList<>();
	private ArrayList<TIdentifierLiteral> ruleOperationLiteralList = new ArrayList<>();

	public RuleTransformation(Start start, BParser bParser) {
		this.start = start;
		this.definitions = bParser.getDefinitions();
	}

	public void runTransformation() throws CheckException {
		CorrectRuleChecker correctRuleChecker = new CorrectRuleChecker();
		start.apply(correctRuleChecker);
		if (correctRuleChecker.errorlist.size() > 0) {
			throw correctRuleChecker.errorlist.get(0);
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

			ArrayList<PPredicate> invariantPredicateList = new ArrayList<>();
			if (invariantMachineClause.getPredicates() != null) {
				invariantPredicateList.add(invariantMachineClause
						.getPredicates());
			}
			ArrayList<PExpression> initialisationIdentifierList = new ArrayList<>();
			ArrayList<PExpression> initialisationExpressionList = new ArrayList<>();

			for (TIdentifierLiteral ruleLiteral : ruleOperationLiteralList) {
				// VARIABLES
				variablesMachineClause.getIdentifiers().add(
						createRuleIdentifier(ruleLiteral));
				String ctName = ruleLiteral.getText()
						+ RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
				variablesMachineClause.getIdentifiers().add(
						createIdentifier(ctName));

				AMemberPredicate member = new AMemberPredicate();
				member.setLeft(createRuleIdentifier(ruleLiteral));
				List<PExpression> list = new ArrayList<>();
				list.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
				list.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
				list.add(new AStringExpression(new TStringLiteral(
						RULE_NOT_CHECKED)));
				ASetExtensionExpression set = new ASetExtensionExpression(list);
				member.setRight(set);
				invariantPredicateList.add(member);

				initialisationIdentifierList
						.add(createRuleIdentifier(ruleLiteral));
				initialisationExpressionList.add(new AStringExpression(
						new TStringLiteral(RULE_NOT_CHECKED)));

				// rule1#counterexamples : POW(STRING)
				AMemberPredicate ctTypingPredicate = new AMemberPredicate();
				ctTypingPredicate.setLeft(createIdentifier(ctName));
				ctTypingPredicate.setRight(new APowSubsetExpression(
						new AStringSetExpression()));
				invariantPredicateList.add(ctTypingPredicate);

				// rule1#counterexamples := {}
				initialisationIdentifierList.add(createIdentifier(ctName));
				initialisationExpressionList.add(new AEmptySetExpression());

			}
			PPredicate conjunction = createConjunction(invariantPredicateList);
			invariantMachineClause.setPredicates(conjunction);

			AAssignSubstitution assign = new AAssignSubstitution(
					initialisationIdentifierList, initialisationExpressionList);
			if (initialisationMachineClause.getSubstitutions() != null) {
				ArrayList<PSubstitution> subList = new ArrayList<>();
				subList.add(initialisationMachineClause.getSubstitutions());
				subList.add(assign);
				AParallelSubstitution parallelSub = new AParallelSubstitution(
						subList);
				initialisationMachineClause.setSubstitutions(parallelSub);
			} else {
				initialisationMachineClause.setSubstitutions(assign);
			}

		}

	}

	private AIdentifierExpression createRuleIdentifier(
			TIdentifierLiteral ruleLiteral) {
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

		select.setThen(node.getRuleBody());
		// select.setThen(node.getRuleBody());

		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		returnValues
				.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
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
	public void caseARuleSuccessSubstitution(ARuleSuccessSubstitution node) {
		AAssignSubstitution assign = createRuleSuccessAssignment();
		node.replaceBy(assign);
	}

	private AAssignSubstitution createRuleSuccessAssignment() {
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(currentRuleLiteral));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		exprList.add(new AStringExpression(new TStringLiteral(
				RULE_EMPTY_MESSAGE)));
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
	}

	@Override
	public void caseARuleFailSubstitution(ARuleFailSubstitution node) {
		if (node.getExpression() != null) {
			final AAssignSubstitution assign = createRuleFailAssignment(
					node.getExpression(),
					createSetOfPExpression(node.getExpression()));
			node.replaceBy(assign);
		} else {
			final AAssignSubstitution assign = createRuleFailAssignment(
					new AStringExpression(
							new TStringLiteral(RULE_EMPTY_MESSAGE)),
					new AEmptySetExpression());
			node.replaceBy(assign);
		}

	}

	private PExpression createSetOfPExpression(PExpression expression) {
		final ArrayList<PExpression> list = new ArrayList<>();
		list.add((PExpression) cloneNode(expression));
		return new ASetExtensionExpression(list);
	}

	private AAssignSubstitution createRuleFailAssignment(
			PExpression resultValueAsString, PExpression setOfCounterexamples) {
		String ctName = currentRuleLiteral.getText()
				+ RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(currentRuleLiteral));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));

		if (resultValueAsString != null) {
			exprList.add(resultValueAsString);

			// set counter example variable
			nameList.add(createIdentifier(ctName));
			exprList.add((PExpression) cloneNode(setOfCounterexamples));
		} else {
			exprList.add(new AStringExpression(new TStringLiteral(
					RULE_EMPTY_MESSAGE)));
		}
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
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
			list1.add((PExpression) cloneNode(id));
			list2.add((PExpression) cloneNode(id));
		}

		final PPredicate where1 = (PPredicate) cloneNode(node.getWhere());
		final PPredicate where2 = (PPredicate) cloneNode(node.getWhere());
		final PPredicate expect1 = (PPredicate) cloneNode(node.getExpect());
		final PPredicate expect2 = (PPredicate) cloneNode(node.getExpect());

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

	@Override
	public void outAForallSubMessageSubstitution(
			AForallSubMessageSubstitution node) {

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
			union.setPredicates(new AConjunctPredicate(where,
					new ANegationPredicate(expect)));
			final List<PExpression> setElementsInUnionList = new ArrayList<PExpression>();
			setElementsInUnionList.add(node.getMessage());
			final ASetExtensionExpression set = new ASetExtensionExpression(
					setElementsInUnionList);
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

			u.setExpression(createSetOfPExpression((PExpression) ec
					.getMessage()));
			unionList.add(u);
		}

		PExpression all = union;
		for (PExpression other : unionList) {
			all = new AUnionExpression(all, other);
		}

		final AEqualPredicate letEqual = new AEqualPredicate(
				createIdentifier(RULE_COUNTER_EXAMPLE_SET), all);
		letSub.setPredicate(letEqual);

		final AEqualPredicate ifPred = new AEqualPredicate(
				createIdentifier(RULE_COUNTER_EXAMPLE_SET),
				new AEmptySetExpression());

		addToStringDefinition();

		ADefinitionExpression toStringCall = new ADefinitionExpression();
		toStringCall.setDefLiteral(new TIdentifierLiteral("TO_STRING"));
		ArrayList<PExpression> definitionParameters = new ArrayList<>();
		definitionParameters.add(createIdentifier(RULE_COUNTER_EXAMPLE_SET));
		toStringCall.setParameters(definitionParameters);
		final AIfSubstitution ifElseSub = new AIfSubstitution(ifPred,
				createRuleSuccessAssignment(), new ArrayList<PSubstitution>(),
				createRuleFailAssignment(toStringCall,
						createIdentifier(RULE_COUNTER_EXAMPLE_SET)));
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
		toStringTypeDef.setName(new TIdentifierLiteral(
				"EXTERNAL_FUNCTION_TO_STRING"));
		toStringTypeDef.setParameters(createIdentifierList("X"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(
				createIdentifier("X"), new AStringSetExpression()));
		definitions
				.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);

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

	class CorrectRuleChecker extends DepthFirstAdapter {
		ArrayList<CheckException> errorlist = new ArrayList<>();
		final Hashtable<Node, Node> ruleAssignmentTable = new Hashtable<>();
		private boolean inRule = false;

		@Override
		public void caseARuleOperation(ARuleOperation node) {
			inRule = true;
			node.getRuleBody().apply(this);
			inRule = false;
			if (!ruleAssignmentTable.containsKey(node)) {
				errorlist.add(new CheckException(
						"No result value assigned in rule operation", node));
			}
		}

		@Override
		public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
			// skip properties clause
		}
		
		public void inAChoiceSubstitution(AChoiceSubstitution node) {
			if (inRule) {
				errorlist
						.add(new CheckException(
								"A CHOICE substitution is not allowed in rule operations",
								node));
			}
		}

		public void inACaseSubstitution(ACaseSubstitution node) {
			if (inRule) {
				errorlist
						.add(new CheckException(
								"A CASE substitution is not allowed in rule operations",
								node));
			}
		}

		@Override
		public void defaultOut(Node node) {
			if (inRule && ruleAssignmentTable.containsKey(node)) {
				Node value = ruleAssignmentTable.get(node);
				if (node.parent() != null) {
					setParent(node.parent(), value);
				}
			}
		}

		private void setParent(Node parent, Node value) {
			if (ruleAssignmentTable.containsKey(parent)) {
				if (parent instanceof ASequenceSubstitution
						|| parent instanceof AParallelSubstitution) {
					errorlist
							.add(new CheckException(
									"Result value of rule operation is assigned more than once",
									value));
				}
			} else {
				ruleAssignmentTable.put(parent, value);
			}
		}

		private boolean resultIsSet(Node node) {
			return ruleAssignmentTable.containsKey(node);
		}

		public void outAIfSubstitution(AIfSubstitution node) {
			if (inRule) {
				if (resultIsSet(node.getThen())) {
					if (node.getElse() == null) {
						errorlist
								.add(new CheckException(
										"There must be an ELSE branch if a result value is set in the THEN branch",
										node));
					} else if (!resultIsSet(node.getElse())) {
						errorlist
								.add(new CheckException(
										"Result value is set in the THEN branch but not in ELSE branch",
										node));
					}

					final LinkedList<PSubstitution> elsifSubstitutions = node
							.getElsifSubstitutions();
					for (PSubstitution pSubstitution : elsifSubstitutions) {
						if (!resultIsSet(pSubstitution)) {
							errorlist
									.add(new CheckException(
											"Result value is set in the THEN branch but not in ELSIF branch",
											pSubstitution));
						}
					}
				} else {
					// no result set in THEN
					if (node.getElse() != null && resultIsSet(node.getElse())) {
						errorlist
								.add(new CheckException(
										"Result value is not set in the THEN branch but set in the ELSE branch",
										node));

					}
					final LinkedList<PSubstitution> elsifSubstitutions = node
							.getElsifSubstitutions();
					for (PSubstitution pSubstitution : elsifSubstitutions) {
						if (resultIsSet(pSubstitution)) {
							errorlist
									.add(new CheckException(
											"Result value is not set in the THEN branch but set in ELSIF branch",
											pSubstitution));
						}
					}

				}
				defaultOut(node);
			}

		}

		@Override
		public void outARuleFailSubstitution(ARuleFailSubstitution node) {
			if (!inRule) {
				errorlist.add(new CheckException(
						"RULE_FAIL used outside of a RULE operation", node));
			}
			ruleAssignmentTable.put(node, node);
			defaultOut(node);
		}

		@Override
		public void outARuleSuccessSubstitution(ARuleSuccessSubstitution node) {
			if (!inRule) {
				errorlist.add(new CheckException(
						"RULE_SUCCESS used outside of a RULE operation", node));
			}
			ruleAssignmentTable.put(node, node);
			defaultOut(node);
		}

		@Override
		public void outAForallSubMessageSubstitution(
				AForallSubMessageSubstitution node) {
			if (!inRule) {
				errorlist.add(new CheckException(
						"RULE_FORALL used outside of a RULE operation", node));
			}
			ruleAssignmentTable.put(node, node);
			defaultOut(node);
		}

	}
}
