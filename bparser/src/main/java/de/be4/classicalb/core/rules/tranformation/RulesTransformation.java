package de.be4.classicalb.core.rules.tranformation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.analysis.transforming.DefinitionInjector;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.VisitorException;
import de.be4.classicalb.core.parser.extensions.RulesGrammar;
import de.be4.classicalb.core.parser.node.*;
import de.be4.classicalb.core.parser.util.NodeCloner;
import de.be4.classicalb.core.rules.project.Reference;
import de.hhu.stups.sablecc.patch.PositionedNode;

public class RulesTransformation extends DepthFirstAdapter {

	public static final String RULE_FAIL = "FAIL";
	public static final String RULE_SUCCESS = "SUCCESS";
	public static final String RULE_NOT_CHECKED = "NOT_CHECKED";
	public static final String RULE_DISABLED = "DISABLED";

	public static final String COMPUTATION_EXECUTED = "EXECUTED";
	public static final String COMPUTATION_NOT_EXECUTED = "NOT_EXECUTED";
	public static final String COMPUTATION_DISABLED = "COMPUTATION_DISABLED";

	public static final String RULE_RESULT_OUTPUT_PARAMETER_NAME = "#RESULT";
	public static final String RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME = "#COUNTEREXAMPLES";
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
	private ArrayList<TIdentifierLiteral> computationLiteralList = new ArrayList<>();
	private TIdentifierLiteral currentRuleLiteral = null;

	private List<AIdentifierExpression> variablesList = new ArrayList<>();
	private List<PPredicate> invariantList = new ArrayList<>();
	private List<PSubstitution> initialisationList = new ArrayList<>();

	private Rule currentRule;

	public RulesTransformation(Start start, BParser bParser, List<Reference> machineReferences) {
		this.start = start;
		this.definitions = bParser.getDefinitions();
		this.rulesMachineVisitor = new RulesMachineVisitor(bParser.getFileName(), machineReferences);
	}

	public void runTransformation() throws CheckException, BException {
		start.apply(rulesMachineVisitor);
		if (rulesMachineVisitor.errorList.size() > 0) {
			throw rulesMachineVisitor.errorList.get(0);
		}
		try {
			start.apply(this);
		} catch (VisitorException e) {
			if (e.getException() instanceof CheckException) {
				throw (CheckException) e.getException();
			} else if (e.getException() instanceof BException) {
				throw (BException) e.getException();
			} else {
				new RuntimeException("Unexpected Exception: " + e.getMessage());
			}
		}

		DefinitionInjector.injectDefinitions(start, definitions);
	}

	public List<AbstractOperation> getOperations() {
		return this.rulesMachineVisitor.getOperations();
	}

	public List<String> getComputations() {
		List<String> list = new ArrayList<>();
		for (TIdentifierLiteral literal : computationLiteralList) {
			list.add(literal.getText());
		}
		return list;
	}

	@Override
	public void outStart(Start node) {
		ClauseFinder finder = new ClauseFinder();
		node.apply(finder);
		if (variablesList.size() > 0) {
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

			ArrayList<PSubstitution> initSubstitutionList = new ArrayList<>();
			if (initialisationMachineClause.getSubstitutions() != null) {
				initSubstitutionList.add(initialisationMachineClause.getSubstitutions());
			}

			invariantPredicateList.addAll(invariantList);
			initSubstitutionList.addAll(initialisationList);

			// VARIABLES
			variablesMachineClause.getIdentifiers().addAll(variablesList);
			// INVARIANT
			final PPredicate conjunction = createConjunction(invariantPredicateList);
			invariantMachineClause.setPredicates(conjunction);

			// INITIALISAION
			if (initSubstitutionList.size() == 1) {
				initialisationMachineClause.setSubstitutions(initSubstitutionList.get(0));
			} else {
				ASequenceSubstitution seqSubstitution = new ASequenceSubstitution(initialisationList);
				initialisationMachineClause.setSubstitutions(seqSubstitution);
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

	private static List<PSubstitution> createSubstitutionList(PSubstitution... pSubstitutions) {
		List<PSubstitution> list = new ArrayList<>();
		for (PSubstitution pSubstitution : pSubstitutions) {
			list.add(pSubstitution);
		}
		return list;
	}

	private static List<PExpression> createExpressionList(PExpression... pExpressions) {
		final List<PExpression> list = new ArrayList<>();
		for (int i = 0; i < pExpressions.length; i++) {
			PExpression node = (PExpression) cloneNode(pExpressions[i]);
			list.add(node);
		}
		return list;
	}

	private static AIdentifierExpression createIdentifier(String name) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		list.add(new TIdentifierLiteral(name));
		return new AIdentifierExpression(list);
	}

	private static AIdentifierExpression createIdentifier(String name, PositionedNode positionNode) {
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral literal = new TIdentifierLiteral(name);
		// literal.setStartPos(positionNode.getStartPos());
		// literal.setEndPos(positionNode.getEndPos());
		list.add(literal);
		AIdentifierExpression result = new AIdentifierExpression(list);
		result.setStartPos(positionNode.getStartPos());
		result.setEndPos(positionNode.getEndPos());
		return result;
	}

	private static AIdentifierExpression createAIdentifierExpression(TIdentifierLiteral identifierLiteral) {
		final String name = identifierLiteral.getText();
		ArrayList<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral literal = new TIdentifierLiteral(name);
		list.add(literal);
		AIdentifierExpression result = new AIdentifierExpression(list);
		result.setStartPos(identifierLiteral.getStartPos());
		result.setEndPos(identifierLiteral.getEndPos());
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
	public void caseAComputationOperation(AComputationOperation node) {
		final Computation computation = this.rulesMachineVisitor.computationMap.get(node);
		computationLiteralList.add(node.getName());
		final String computationName = node.getName().getText();
		node.getBody().apply(this);
		AEqualPredicate grd1 = new AEqualPredicate(createIdentifier(computationName),
				new AStringExpression(new TStringLiteral(COMPUTATION_NOT_EXECUTED)));
		ASelectSubstitution select = new ASelectSubstitution();
		final List<PPredicate> selectConditionList = new ArrayList<>();
		selectConditionList.add(grd1);
		if (computation.getDependsOnRulesList().size() > 0) {
			final List<PPredicate> dependsOnRulesPredicates = createConjunctionList(computation.getDependsOnRulesList(),
					RULE_SUCCESS);
			selectConditionList.addAll(dependsOnRulesPredicates);
		}
		if (computation.getDependsOnComputationList().size() > 0) {
			final List<PPredicate> dependsOnComputationPredicates = createConjunctionList(
					computation.getDependsOnComputationList(), COMPUTATION_EXECUTED);
			selectConditionList.addAll(dependsOnComputationPredicates);
		}
		select.setCondition(createConjunction(selectConditionList));

		final ArrayList<PExpression> varList = new ArrayList<>();
		final ArrayList<PExpression> exprList = new ArrayList<>();
		varList.add(createIdentifier(computationName));
		exprList.add(new AStringExpression(new TStringLiteral(COMPUTATION_EXECUTED)));
		AAssignSubstitution assign = new AAssignSubstitution(varList, exprList);

		select.setThen(new ASequenceSubstitution(createSubstitutionList(node.getBody(), assign)));
		AOperation operation = new AOperation();
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add((TIdentifierLiteral) cloneNode(node.getName()));
		operation.setOpName(nameList);
		operation.setOperationBody(select);
		// replacing the computation by an ordinary operation
		node.replaceBy(operation);

		// create variables in VARIABLES clause
		variablesList.add(createAIdentifierExpression(node.getName()));

		// create invariant in INVARIANT clause
		// Compute_foo : {"COMPUTATION_EXECUTED", "COMPUTATION_NOT_EXECUTED",
		// "COMPUTATION_DISABLED" }
		final List<PExpression> list = new ArrayList<>();
		list.add(createStringExpression(COMPUTATION_EXECUTED));
		list.add(createStringExpression(COMPUTATION_NOT_EXECUTED));
		list.add(createStringExpression(COMPUTATION_DISABLED));
		final ASetExtensionExpression set = new ASetExtensionExpression(list);
		final AMemberPredicate member = new AMemberPredicate(createAIdentifierExpression(node.getName()), set);
		invariantList.add(member);

		PExpression value;
		if (computation.getActivationPredicate() != null) {
			value = new AIfThenElseExpression((PPredicate) NodeCloner.cloneNode(computation.getActivationPredicate()),
					createStringExpression(COMPUTATION_NOT_EXECUTED), createStringExpression(COMPUTATION_DISABLED));
		} else {
			value = createStringExpression(COMPUTATION_NOT_EXECUTED);
		}
		// create substitution in INITIALISATION clause
		final AAssignSubstitution initSub = new AAssignSubstitution(
				createExpressionList(createAIdentifierExpression(node.getName())), createExpressionList(value));
		initialisationList.add(initSub);
	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		this.currentRule = this.rulesMachineVisitor.rulesMap.get(node);
		final String ruleName = currentRule.getName();
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
		final List<PPredicate> selectConditionList = new ArrayList<>();
		final AEqualPredicate grd1 = new AEqualPredicate(createIdentifier(ruleName),
				new AStringExpression(new TStringLiteral(RULE_NOT_CHECKED)));
		selectConditionList.add(grd1);
		// typing predicate: $COUNTEREXAMPLE : POW(INTEGER*STRING)
		final AMemberPredicate grd2 = new AMemberPredicate(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME),
				new APowSubsetExpression(
						new AMultOrCartExpression(new AIntegerSetExpression(), new AStringSetExpression())));
		selectConditionList.add(grd2);
		if (currentRule.getDependsOnRulesList().size() > 0) {
			final List<PPredicate> dependsOnRulesPredicates = createConjunctionList(currentRule.getDependsOnRulesList(),
					RULE_SUCCESS);
			selectConditionList.addAll(dependsOnRulesPredicates);
		}
		if (currentRule.getDependsOnComputationList().size() > 0) {
			final List<PPredicate> dependsOnComputationPredicates = createConjunctionList(
					currentRule.getDependsOnComputationList(), COMPUTATION_EXECUTED);
			selectConditionList.addAll(dependsOnComputationPredicates);
		}
		final ASelectSubstitution select = new ASelectSubstitution();
		select.setCondition(createConjunction(selectConditionList));

		ArrayList<PSubstitution> subList = new ArrayList<>();
		subList.add(node.getRuleBody());
		// IF rule_Counterexamples = {} THEN RULE_SUCCESS ELSE RULE_FAIL END
		final String ctName = currentRuleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		PPredicate ifCondition = new AEqualPredicate(createIdentifier(ctName), new AEmptySetExpression());
		AIfSubstitution ifSub = new AIfSubstitution(ifCondition,
				createRuleSuccessAssignment(currentRule.getNameLiteral()), new ArrayList<PSubstitution>(),
				createRuleFailAssignment(currentRule.getNameLiteral()));
		subList.add(ifSub);
		ASequenceSubstitution seq = new ASequenceSubstitution(subList);
		select.setThen(seq);

		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));

		returnValues.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		operation.setReturnValues(returnValues);
		operation.setOperationBody(select);

		node.replaceBy(operation); // replacing the rule operation by an
									// ordinary operation

		// VARIABLES
		variablesList.add(createAIdentifierExpression(node.getRuleName()));

		// INVARIANT
		List<PExpression> list = new ArrayList<>();
		list.add(createStringExpression(RULE_FAIL));
		list.add(createStringExpression(RULE_SUCCESS));
		list.add(createStringExpression(RULE_NOT_CHECKED));
		list.add(createStringExpression(RULE_DISABLED));
		ASetExtensionExpression set = new ASetExtensionExpression(list);
		AMemberPredicate member = new AMemberPredicate(createAIdentifierExpression(node.getRuleName()), set);
		invariantList.add(member);

		/*
		 * If there are no constant dependencies: rule := RULE_NOT_CHECKED
		 * Otherwise: rule := IF dependencies THEN RULE_NOT_CHECKED ELSE
		 * RULE_DISABLED END
		 */
		PExpression value;
		if (currentRule.getActivationPredicate() != null) {
			value = new AIfThenElseExpression((PPredicate) NodeCloner.cloneNode(currentRule.getActivationPredicate()),
					createStringExpression(RULE_NOT_CHECKED), createStringExpression(RULE_DISABLED));

		} else {
			value = createStringExpression(RULE_NOT_CHECKED);
		}
		final AAssignSubstitution initSub = new AAssignSubstitution(
				createExpressionList(createAIdentifierExpression(node.getRuleName())), createExpressionList(value));
		initialisationList.add(initSub);
		// VARIABLES ...
		variablesList.add(createIdentifier(ctName, node.getRuleName()));

		// INVARIANT rule1#counterexamples : POW(INTEGER*STRING)
		final AMemberPredicate ctTypingPredicate = new AMemberPredicate();
		ctTypingPredicate.setLeft(createIdentifier(ctName));
		ctTypingPredicate.setRight(new APowSubsetExpression(
				new AMultOrCartExpression(new ANaturalSetExpression(), new AStringSetExpression())));
		invariantList.add(ctTypingPredicate);

		// INITIALISATION rule1#counterexamples := {}
		final AAssignSubstitution assign = createAssignNode(createIdentifier(ctName, node.getRuleName()),
				new AEmptySetExpression());
		initialisationList.add(assign);

	}

	private AAssignSubstitution createAssignNode(PExpression id, PExpression value) {
		ArrayList<PExpression> nameList = new ArrayList<>();
		nameList.add(id);
		ArrayList<PExpression> exprList = new ArrayList<>();
		exprList.add(value);
		return new AAssignSubstitution(nameList, exprList);
	}

	@Override
	public void caseAOperatorExpression(AOperatorExpression node) {
		final String operatorName = node.getName().getText();
		final LinkedList<PExpression> parameters = node.getIdentifiers();
		switch (operatorName) {
		case RulesGrammar.STRING_FORMAT: {
			final TIdentifierLiteral format = new TIdentifierLiteral("FORMAT_TO_STRING");
			format.setStartPos(node.getName().getStartPos());
			format.setEndPos(node.getName().getEndPos());

			PExpression stringValue = parameters.get(0);
			if (stringValue instanceof AStringExpression) {
				AStringExpression string = (AStringExpression) stringValue;
				String content = string.getContent().getText();
				int count = (content.length() - content.replace("~w", "").length()) / 2;
				if (count != parameters.size() - 1) {
					throw new VisitorException(new CheckException(
							"The number of arguments (" + (parameters.size() - 1)
									+ ") does not match the number of placeholders (" + count + " in the string.",
							node));
				}
			}

			final List<PExpression> list = new ArrayList<>();
			list.add(stringValue);
			final List<PExpression> seqList = new ArrayList<>();
			for (int i = 1; i < parameters.size(); i++) {
				ADefinitionExpression toStringCall = new ADefinitionExpression(new TIdentifierLiteral("TO_STRING"),
						createExpressionList(parameters.get(i)));
				seqList.add(toStringCall);
			}
			final ASequenceExtensionExpression seq = new ASequenceExtensionExpression(seqList);
			list.add(seq);
			final ADefinitionExpression def = new ADefinitionExpression(format, list);
			node.replaceBy(def);
			return;
		}
		default:
			throw new RuntimeException("should not happen");
		}
	}

	@Override
	public void caseAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RulesGrammar.RULE_FAIL: {
			final LinkedList<PExpression> arguments = node.getArguments();
			// arguments is never null because a list in sablecc is empty
			// if not provided
			if (arguments.size() == 0) {
				throw new VisitorException(new CheckException("RULE_FAIL requires at least one argument.", node));
			} else if (arguments.size() == 1) {
				final PSubstitution assign = createCounterExampleSubstitution(1, arguments.get(0));
				node.replaceBy(assign);
				return;
			} else if (arguments.size() == 2) {
				PExpression pExpression = node.getArguments().get(0);
				if (pExpression instanceof AIntegerExpression) {
					AIntegerExpression intExpr = (AIntegerExpression) pExpression;
					final int errorIndex = Integer.parseInt(intExpr.getLiteral().getText());
					final PSubstitution assign = createCounterExampleSubstitution(errorIndex, arguments.get(1));
					node.replaceBy(assign);
					return;
				} else {
					throw new VisitorException(new CheckException(
							"Invalid value for the first argument of RULE_FAILL: Expectet an integer value.", node));
				}
			} else {
				throw new VisitorException(new CheckException("Invalid number of arguments for RULE_FAILL", node));
			}
		}
		default:
			throw new RuntimeException("should not happen");
		}
	}

	private AAssignSubstitution createRuleSuccessAssignment(final TIdentifierLiteral ruleLiteral) {
		final ArrayList<PExpression> nameList = new ArrayList<>();
		final ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(ruleLiteral));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_SUCCESS)));
		nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		exprList.add(new AEmptySetExpression());
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
	}

	private AAssignSubstitution createRuleFailAssignment(final TIdentifierLiteral ruleLiteral) {
		final ArrayList<PExpression> nameList = new ArrayList<>();
		final ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(ruleLiteral));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		final String ctName = currentRuleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		exprList.add(createIdentifier(ctName));
		AAssignSubstitution assign = new AAssignSubstitution(nameList, exprList);
		return assign;
	}

	private PExpression createSetOfPExpression(PExpression expression) {
		final ArrayList<PExpression> list = new ArrayList<>();
		list.add((PExpression) cloneNode(expression));
		return new ASetExtensionExpression(list);
	}

	@Override
	public void caseADefineSubstitution(ADefineSubstitution node) {

		variablesList.add(createIdentifier(node.getName().getText(), node.getName()));
		node.getType().apply(this);
		AMemberPredicate member = new AMemberPredicate(createRuleIdentifier(node.getName()), node.getType());
		invariantList.add(member);

		if (node.getDummyValue() != null) {
			node.getDummyValue().apply(this);
			initialisationList.add(createAssignNode(createRuleIdentifier(node.getName()), node.getDummyValue()));

		} else {
			// TODO store position information in empty set
			initialisationList.add(createAssignNode(createRuleIdentifier(node.getName()), new AEmptySetExpression()));
		}
		node.getValue().apply(this);
		PSubstitution assign = createAssignNode(createRuleIdentifier(node.getName()), node.getValue());
		node.replaceBy(assign);
	}

	private PSubstitution createCounterExampleSubstitution(int errorIndex, PExpression setOfCounterexamples) {
		final String ctName = currentRuleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		if (currentRule.getRuleIdString() != null) {
			// RULEID
			// rule1_Counterexamples:=rule1_Counterexamples\/{1}*UNION(s).(s:#CT_SET|{STRING_APPEND("id1:
			// ",s)})
			addStringAppendDefinition();
			final ALetSubstitution let = new ALetSubstitution();
			let.setIdentifiers(createExpressionList(createIdentifier("#cts")));
			let.setPredicate(
					new AEqualPredicate(createIdentifier("#cts"), (PExpression) cloneNode(setOfCounterexamples)));
			final PPredicate unionPredicate = new AMemberPredicate(createIdentifier("s"), createIdentifier("#cts"));
			final PExpression expr = new ASetExtensionExpression(createExpressionList(
					new ADefinitionExpression(new TIdentifierLiteral("STRING_APPEND"), createExpressionList(
							createStringExpression(currentRule.getRuleIdString() + ": "), createIdentifier("s")))));
			final PExpression quantifiedUnion = new AQuantifiedUnionExpression(createIdentifierList("s"),
					unionPredicate, expr);
			final PExpression res = new AMultOrCartExpression(
					new ASetExtensionExpression(
							createExpressionList(new AIntegerExpression(new TIntegerLiteral("" + errorIndex)))),
					quantifiedUnion);
			// LET cts2 BE cts2 = CTS \/ res IN ... END
			final AUnionExpression union = new AUnionExpression(createIdentifier(ctName), res);
			AAssignSubstitution assign = new AAssignSubstitution(createExpressionList(createIdentifier(ctName)),
					createExpressionList(union));
			let.setSubstitution(assign);
			return let;
		} else {
			// Without RULEID
			// LET #CT_SET BE #CT_SET=UNION(x).(x:1..3|{"fail"}) IN
			// IF #CT_SET/={} THEN
			// rule1_Counterexamples:=rule1_Counterexamples\/{1}*STRING/\#CT_SET
			// END END
			final AUnionExpression union = new AUnionExpression(createIdentifier(ctName),
					new AMultOrCartExpression(
							new ASetExtensionExpression(
									createExpressionList(new AIntegerExpression(new TIntegerLiteral("" + errorIndex)))),
							new AIntersectionExpression(new AStringSetExpression(),
									(PExpression) cloneNode(setOfCounterexamples))));
			return new AAssignSubstitution(createExpressionList(createIdentifier(ctName)), createExpressionList(union));
		}
	}

	@Override
	public void caseAFunctionOperation(AFunctionOperation node) {
		FunctionOperation func = rulesMachineVisitor.getFunctionOperation(node);
		List<PExpression> copy = new ArrayList<PExpression>(node.getReturnValues());
		for (PExpression e : copy) {
			e.apply(this);
		}
		node.getName().apply(this);
		node.getBody().apply(this);
		PSubstitution body = null;
		final List<PPredicate> preConditionList = new ArrayList<>();
		if (func.getPreconditionPredicate() != null) {
			preConditionList.add(func.getPreconditionPredicate());
		}
		if (func.getDependsOnRulesList().size() > 0) {
			final List<PPredicate> dependsOnRulesPredicates = createConjunctionList(func.getDependsOnRulesList(),
					RULE_SUCCESS);
			preConditionList.addAll(dependsOnRulesPredicates);
		}
		if (func.getDependsOnComputationList().size() > 0) {
			final List<PPredicate> dependsOnComputationPredicates = createConjunctionList(
					func.getDependsOnComputationList(), COMPUTATION_EXECUTED);
			preConditionList.addAll(dependsOnComputationPredicates);
		}
		if (preConditionList.size() > 0) {
			body = new APreconditionSubstitution(createConjunction(preConditionList), node.getBody());
		} else {
			body = node.getBody();
		}
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add(node.getName());
		AOperation operation = new AOperation(node.getReturnValues(), nameList, node.getParameters(), body);
		node.replaceBy(operation);
	}

	@Override
	public void caseAOperatorPredicate(AOperatorPredicate node) {
		final List<PExpression> arguments = new ArrayList<PExpression>(node.getIdentifiers());
		final String operatorName = node.getName().getText();
		switch (operatorName) {
		case RulesGrammar.SUCCEEDED_RULE:
			if (arguments.size() != 1) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected one argument.", node));
			}
			replacePredicateOperator(node, arguments, RULE_SUCCESS);
			return;
		case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE: {
			if (arguments.size() != 2) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected two arguments.", node));
			}
			PExpression pExpression = node.getIdentifiers().get(0);
			if (!(pExpression instanceof AIdentifierExpression)) {
				throw new VisitorException(new CheckException(
						"The first argument of SUCCEEDED_RULE_ERROR_TYPE must be an identifier.", node));
			}
			AIdentifierExpression id = (AIdentifierExpression) pExpression;
			String name = id.getIdentifier().get(0).getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
			ADomainRestrictionExpression domRes = new ADomainRestrictionExpression(
					createSetOfPExpression(node.getIdentifiers().get(1)), createIdentifier(name, pExpression));
			AEqualPredicate equal = new AEqualPredicate(domRes, new AEmptySetExpression());
			node.replaceBy(equal);
			return;
		}
		case RulesGrammar.FAILED_RULE:
			if (arguments.size() != 1) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected one argument.", node));
			}
			replacePredicateOperator(node, arguments, RULE_FAIL);
			return;
		case RulesGrammar.FAILED_RULE_ERROR_TYPE: {
			if (arguments.size() != 2) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected two arguments.", node));
			}
			PExpression pExpression = node.getIdentifiers().get(0);
			if (!(pExpression instanceof AIdentifierExpression)) {
				throw new VisitorException(new CheckException(
						"The first argument of FAILED_RULE_ERROR_TYPE must be an identifier.", node));
			}
			AIdentifierExpression id = (AIdentifierExpression) pExpression;
			String name = id.getIdentifier().get(0).getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
			ADomainRestrictionExpression domRes = new ADomainRestrictionExpression(
					createSetOfPExpression(node.getIdentifiers().get(1)), createIdentifier(name, pExpression));
			ANotEqualPredicate notEqual = new ANotEqualPredicate(domRes, new AEmptySetExpression());
			node.replaceBy(notEqual);
			return;
		}
		case RulesGrammar.NOT_CHECKED_RULE:
			if (arguments.size() != 1) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected one argument.", node));
			}
			replacePredicateOperator(node, arguments, RULE_NOT_CHECKED);
			return;
		case RulesGrammar.DISABLED_RULE:
			if (arguments.size() != 1) {
				throw new VisitorException(
						new CheckException("Invalid number of arguments. Expected one argument.", node));
			}
			replacePredicateOperator(node, arguments, RULE_DISABLED);
			return;
		default:
			throw new RuntimeException("should not happen: " + operatorName);
		}
	}

	private List<PPredicate> createConjunctionList(final List<AIdentifierExpression> identifiers, final String value) {
		final List<PPredicate> predList = new ArrayList<>();
		for (PExpression e : identifiers) {
			final AEqualPredicate equal = new AEqualPredicate(e, new AStringExpression(new TStringLiteral(value)));
			equal.setStartPos(e.getStartPos());
			equal.setEndPos(e.getEndPos());
			predList.add(equal);
		}
		return predList;
	}

	private void replacePredicateOperator(final AOperatorPredicate node, List<PExpression> copy,
			final String stringValue) {
		final List<PPredicate> predList = new ArrayList<>();
		for (PExpression e : copy) {
			final AEqualPredicate equal = new AEqualPredicate(e,
					new AStringExpression(new TStringLiteral(stringValue)));
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
	public void outARuleAnySubMessageSubstitution(ARuleAnySubMessageSubstitution node) {
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
			union.setPredicates(where);
			final List<PExpression> setElementsInUnionList = new ArrayList<PExpression>();
			setElementsInUnionList.add(node.getMessage());
			final ASetExtensionExpression set = new ASetExtensionExpression(setElementsInUnionList);
			union.setExpression(set);
		}

		addToStringDefinition();

		Integer errorType = null;
		if (node.getErrorType() != null) {
			errorType = Integer.parseInt(node.getErrorType().getText());
		} else {
			// default value
			errorType = 1;
		}
		PSubstitution counterExampleSubstitution = createCounterExampleSubstitution(errorType, union);
		node.replaceBy(counterExampleSubstitution);
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
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
		addToStringDefinition();

		Integer errorType = null;
		if (node.getErrorType() != null) {
			errorType = Integer.parseInt(node.getErrorType().getText());
		} else {
			// default value
			errorType = 1;
		}
		PSubstitution counterExampleSubstitution = createCounterExampleSubstitution(errorType, union);
		node.replaceBy(counterExampleSubstitution);
	}

	private void addStringAppendDefinition() {
		final String STRING_APPEND = "STRING_APPEND";
		if (definitions.containsDefinition(STRING_APPEND)) {
			return;
		}

		// STRING_APPEND(x,y) == "str";
		// EXTERNAL_FUNCTION_STRING_APPEND == (STRING*STRING) --> STRING;
		AExpressionDefinitionDefinition toStringDef = new AExpressionDefinitionDefinition();
		toStringDef.setName(new TIdentifierLiteral(STRING_APPEND));
		toStringDef.setParameters(createIdentifierList("x", "y"));
		toStringDef.setRhs(new AStringExpression(new TStringLiteral("str")));
		try {
			definitions.addDefinition(toStringDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_STRING_APPEND"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(
				new ACartesianProductExpression(new AStringSetExpression(), new AStringSetExpression()),
				new AStringSetExpression()));
		try {
			definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}
	}

	private void addToStringDefinition() {
		final String TO_STRING = "TO_STRING";
		if (definitions.containsDefinition(TO_STRING)) {
			return;
		}
		// TO_STRING(S) == "0";
		// EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
		AExpressionDefinitionDefinition toStringDef = new AExpressionDefinitionDefinition();
		toStringDef.setName(new TIdentifierLiteral(TO_STRING));
		toStringDef.setParameters(createIdentifierList("S"));
		toStringDef.setRhs(new AStringExpression(new TStringLiteral("0")));
		try {
			definitions.addDefinition(toStringDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_TO_STRING"));
		toStringTypeDef.setParameters(createIdentifierList("X"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(createIdentifier("X"), new AStringSetExpression()));
		try {
			definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}
	}

	private void addChooseDefinition() {
		final String CHOOSE = "CHOOSE";
		if (definitions.containsDefinition(CHOOSE)) {
			return;
		}
		// TO_STRING(S) == "0";
		// EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
		AExpressionDefinitionDefinition ChooseDef = new AExpressionDefinitionDefinition();
		ChooseDef.setName(new TIdentifierLiteral(CHOOSE));
		ChooseDef.setParameters(createIdentifierList("X"));
		ChooseDef.setRhs(new AStringExpression(new TStringLiteral("a member of X")));
		try {
			definitions.addDefinition(ChooseDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}

		AExpressionDefinitionDefinition chooseDefType = new AExpressionDefinitionDefinition();
		chooseDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_CHOOSE"));
		chooseDefType.setParameters(createIdentifierList("T"));
		chooseDefType.setRhs(
				new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")), createIdentifier("T")));
		try {
			definitions.addDefinition(chooseDefType, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new VisitorException(e);
		}
	}

	private List<PExpression> createIdentifierList(String... strings) {
		ArrayList<PExpression> list = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			list.add(createIdentifier(strings[i]));
		}
		return list;
	}

	@Override
	public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
		// skip properties clause
	}

}
