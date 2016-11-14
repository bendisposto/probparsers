package de.be4.classicalb.core.rules.tranformation;

import java.util.ArrayList;
import java.util.Hashtable;
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
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.*;
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
	private ArrayList<TIdentifierLiteral> computationLiteralList = new ArrayList<>();
	private Hashtable<TIdentifierLiteral, PPredicate> ruleConstantDependencies = new Hashtable<TIdentifierLiteral, PPredicate>();
	private TIdentifierLiteral currentRuleLiteral = null;
	private TIdentifierLiteral currentRuleId = null;

	private List<AIdentifierExpression> variablesList = new ArrayList<>();
	private List<PPredicate> invariantList = new ArrayList<>();
	private List<PSubstitution> initialisationList = new ArrayList<>();

	public RulesTransformation(Start start, BParser bParser) {
		this.start = start;
		this.definitions = bParser.getDefinitions();
		this.rulesMachineVisitor = new RulesMachineVisitor(bParser.getFileName());
	}

	public List<String> getRules() {
		List<String> list = new ArrayList<>();
		for (TIdentifierLiteral literal : ruleOperationLiteralList) {
			list.add(literal.getText());
		}
		return list;
	}

	public List<String> getComputations() {
		List<String> list = new ArrayList<>();
		for (TIdentifierLiteral literal : computationLiteralList) {
			list.add(literal.getText());
		}
		return list;
	}

	public void runTransformation() throws CheckException, BException {
		start.apply(rulesMachineVisitor);
		if (rulesMachineVisitor.errorlist.size() > 0) {
			throw rulesMachineVisitor.errorlist.get(0);
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
		computationLiteralList.add(node.getName());
		final String computationName = node.getName().getText();
		node.getBody().apply(this);
		AEqualPredicate grd1 = new AEqualPredicate(createIdentifier(computationName),
				new AStringExpression(new TStringLiteral(COMPUTATION_NOT_EXECUTED)));
		ASelectSubstitution select = new ASelectSubstitution();
		select.setCondition(grd1);

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

		// create substitution in INITIALISATION clause
		final AAssignSubstitution initSub = new AAssignSubstitution(
				createExpressionList(createAIdentifierExpression(node.getName())),
				createExpressionList(createStringExpression(COMPUTATION_NOT_EXECUTED)));
		initialisationList.add(initSub);
	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		final String ruleName = node.getRuleName().getText();
		currentRuleLiteral = node.getRuleName();
		currentRuleId = node.getRuleId();

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
		ArrayList<PSubstitution> subList = new ArrayList<>();
		subList.add(node.getRuleBody());
		// IF ruleName = "NOT_CHECKED" THEN RULE_SUCCESS END
		PPredicate ifCondition = new AEqualPredicate(createIdentifier(ruleName),
				new AStringExpression(new TStringLiteral(RULE_NOT_CHECKED)));
		AIfSubstitution ifSub = new AIfSubstitution(_condition_, _then_, _elsifSubstitutions_, _else_)
		ASequenceSubstitution seq = new ASequenceSubstitution(subList);
		select.setThen(node.getRuleBody());
		
		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));

		if (rulesMachineVisitor.hasCounterExamples(node.getRuleName())) {
			returnValues.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		}
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
		if (ruleConstantDependencies.containsKey(node.getRuleName())) {
			value = new AIfThenElseExpression(ruleConstantDependencies.get(node.getRuleName()),
					createStringExpression(RULE_NOT_CHECKED), createStringExpression(RULE_DISABLED));

		} else {
			value = createStringExpression(RULE_NOT_CHECKED);
		}
		final AAssignSubstitution initSub = new AAssignSubstitution(
				createExpressionList(createAIdentifierExpression(node.getRuleName())), createExpressionList(value));
		initialisationList.add(initSub);
		if (rulesMachineVisitor.hasCounterExamples(node.getRuleName())) {
			// VARIABLES ...
			final String ctName = ruleName + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
			variablesList.add(createIdentifier(ctName, node.getRuleName()));

			// INVARIANT rule1#counterexamples : POW(STRING)
			final AMemberPredicate ctTypingPredicate = new AMemberPredicate();
			ctTypingPredicate.setLeft(createIdentifier(ctName));
			ctTypingPredicate.setRight(new APowSubsetExpression(new AStringSetExpression()));
			invariantList.add(ctTypingPredicate);

			// INITIALISATION rule1#counterexamples := {}
			final AAssignSubstitution assign = createAssignNode(createIdentifier(ctName, node.getRuleName()),
					new AEmptySetExpression());
			initialisationList.add(assign);
		}

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
		case RuleGrammar.STRING_FORMAT: {
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
		case RuleGrammar.RULE_SUCCESS: {
			AAssignSubstitution assign = createRuleSuccessAssignment();
			node.replaceBy(assign);
			return;
		}
		case RuleGrammar.RULE_FAIL: {
			if (node.getExpression() != null) {
				final PSubstitution assign = createRuleFailSubstitution(1, node.getExpression());
				node.replaceBy(assign);
			} else {
				final PSubstitution assign = createRuleFailSubstitution(1, new AEmptySetExpression());
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

	private PSubstitution createRuleFailSubstitution(PExpression setOfCounterexamples) {
		final String ctName = currentRuleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		ArrayList<PExpression> nameList = new ArrayList<>();
		ArrayList<PExpression> exprList = new ArrayList<>();

		nameList.add(createRuleIdentifier(currentRuleLiteral));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));

		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		PSubstitution substitution = new AAssignSubstitution(nameList, exprList);

		if (rulesMachineVisitor.hasCounterExamples(currentRuleLiteral)) {

			ALetSubstitution let = new ALetSubstitution();
			let.setIdentifiers(createIdentifierList("#cts"));
			let.setPredicate(new AEqualPredicate(createIdentifier("#cts"), new AIntersectionExpression(
					new AStringSetExpression(), (PExpression) cloneNode(setOfCounterexamples))));
			if (currentRuleId != null) {
				addStringAppendDefinition();
				// UNION(s).(s : S| {STRING_APPEND("id: ", s)} }
				final PPredicate predicate = new AMemberPredicate(createIdentifier("s"), createIdentifier("#cts"));
				final PExpression expr = new ASetExtensionExpression(createExpressionList(
						new ADefinitionExpression(new TIdentifierLiteral("STRING_APPEND"), createExpressionList(
								createStringExpression(currentRuleId.getText() + ": "), createIdentifier("s")))));

				PExpression res = new AQuantifiedUnionExpression(createIdentifierList("s"), predicate, expr);

				ALetSubstitution let2 = new ALetSubstitution();
				let2.setIdentifiers(createIdentifierList("#cts2"));
				let2.setPredicate(new AEqualPredicate(createIdentifier("#cts2"), res));
				let2.setSubstitution(new AAssignSubstitution(
						createExpressionList(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME),
								createIdentifier(ctName)),
						createExpressionList(createIdentifier("#cts2"), createIdentifier("#cts2"))));
				let.setSubstitution(let2);

			} else {
				let.setSubstitution(new AAssignSubstitution(
						createExpressionList(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME),
								createIdentifier(ctName)),
						createExpressionList(createIdentifier("#cts"), createIdentifier("#cts"))));
			}

			List<PSubstitution> list = new ArrayList<>();
			list.add(substitution);
			list.add(let);
			substitution = new AParallelSubstitution(list);
		}

		return substitution;
	}

	@Override
	public void caseAFunctionOperation(AFunctionOperation node) {
		List<PExpression> copy = new ArrayList<PExpression>(node.getReturnValues());
		for (PExpression e : copy) {
			e.apply(this);
		}
		node.getName().apply(this);
		node.getBody().apply(this);
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add(node.getName());
		AOperation operation = new AOperation(node.getReturnValues(), nameList, node.getParameters(), node.getBody());
		node.replaceBy(operation);
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
		case RuleGrammar.DEPENDS_ON_COMPUTATION:
			replacePredicateOperator(node, copy, COMPUTATION_EXECUTED);
			return;
		default:
			throw new RuntimeException("should not happen: " + operatorName);
		}
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
				new ArrayList<PSubstitution>(),
				createRuleFailSubstitution(1, createIdentifier(RULE_COUNTER_EXAMPLE_SET)));
		letSub.setSubstitution(ifElseSub);

		node.replaceBy(letSub);
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
