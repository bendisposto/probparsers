package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;
import static de.be4.classicalb.core.parser.rules.ASTBuilder.*;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.analysis.transforming.DefinitionInjector;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.*;
import de.be4.classicalb.core.parser.util.NodeCloner;
import de.be4.classicalb.core.parser.util.Utils;

public class RulesTransformation extends DepthFirstAdapter {

	public static final String RULE_FAIL = "FAIL";
	public static final String RULE_SUCCESS = "SUCCESS";
	public static final String RULE_NOT_CHECKED = "NOT_CHECKED";
	public static final String RULE_DISABLED = "DISABLED";
	// public static final String RULE_BLOCKED = "RULE_BLOCKED";

	public static final String COMPUTATION_EXECUTED = "EXECUTED";
	public static final String COMPUTATION_NOT_EXECUTED = "NOT_EXECUTED";
	public static final String COMPUTATION_DISABLED = "COMPUTATION_DISABLED";
	// public static final String COMPUTATION_BLOCKED = "COMPUTATION_BLOCKED";

	public static final String RULE_RESULT_OUTPUT_PARAMETER_NAME = "$RESULT";
	public static final String RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME = "$COUNTEREXAMPLES";
	public static final String RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX = "_Counterexamples";

	private static final String FORCE = "FORCE";
	private static final String STRING_APPEND = "STRING_APPEND";
	private static final String CHOOSE = "CHOOSE";
	private static final String TO_STRING = "TO_STRING";

	private final IDefinitions definitions;
	private final Start start;
	private final RulesMachineChecker rulesMachineVisitor;
	private final ArrayList<CheckException> errorList = new ArrayList<>();
	private ArrayList<String> ruleNames = new ArrayList<>();
	private ArrayList<TIdentifierLiteral> ruleOperationLiteralList = new ArrayList<>();
	private ArrayList<TIdentifierLiteral> computationLiteralList = new ArrayList<>();

	private List<AIdentifierExpression> variablesList = new ArrayList<>();
	private List<PPredicate> invariantList = new ArrayList<>();
	private List<PSubstitution> initialisationList = new ArrayList<>();

	private RuleOperation currentRule;
	private Map<String, AbstractOperation> allOperations;

	// used to provide unique identifiers for generated variables of FOR loops
	private int nestedForLoopCount = 0;

	// some operations may be deleted by they are replaced other operations
	private final HashSet<String> operationsToBeDeleted = new HashSet<>();

	/**
	 * @param start
	 *            The root node of the abstract syntax tree.
	 * @param bParser
	 *            The parser of the rules machine.
	 * @param rulesMachineChecker
	 *            the rules machine checker which has already analyzed the rules
	 *            machine
	 * @param allOperations
	 *            The list of all operation in the project. This parameter is
	 *            needed in order to detect invalid reference to operations
	 *            which does not exist. Note, that such checks can not be done
	 *            by the {@link RulesMachineChecker} because they need more than
	 *            the machine scope. For example, it is checked that the first
	 *            argument of the GET_RULE_COUNTEREXAMPLES operator is an
	 *            existing rule operation which may be located in another rules
	 *            machine.
	 * 
	 */
	public RulesTransformation(Start start, BParser bParser, RulesMachineChecker rulesMachineChecker,
			Map<String, AbstractOperation> allOperations) {
		this.start = start;
		this.definitions = bParser.getDefinitions();
		this.rulesMachineVisitor = rulesMachineChecker;
		this.allOperations = allOperations;

		for (AbstractOperation operation : allOperations.values()) {
			if (null != operation.getReplacesIdentifier()) {
				AIdentifierExpression idExpr = operation.getReplacesIdentifier();
				String opName = Utils.getAIdentifierAsString(idExpr);
				operationsToBeDeleted.add(opName);
			}
		}
	}

	public void runTransformation() throws BCompoundException {
		start.apply(this);
		// TODO why and what kind of definitions do we inject here?
		DefinitionInjector.injectDefinitions(start, definitions);
		MissingPositionsAdder.injectPositions(start);
		if (!this.errorList.isEmpty()) {
			List<BException> list = new ArrayList<>();
			for (CheckException checkException : this.errorList) {
				list.add(new BException(this.rulesMachineVisitor.getFileName(), checkException));
			}
			throw new BCompoundException(list);
		}
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
		if (!variablesList.isEmpty()) {
			AAbstractMachineParseUnit abstractMachineParseUnit = finder.abstractMachineParseUnit;
			AInitialisationMachineClause initialisationMachineClause;
			AInvariantMachineClause invariantMachineClause;
			AVariablesMachineClause variablesMachineClause;
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

		@Override
		public void inAInvariantMachineClause(AInvariantMachineClause node) {
			invariantMachineClause = node;
		}

		@Override
		public void inAInitialisationMachineClause(AInitialisationMachineClause node) {
			initialisationMachineClause = node;
		}
	}

	@Override
	public void caseAComputationOperation(AComputationOperation node) {
		if (operationsToBeDeleted.contains(node.getName().getText())) {
			node.replaceBy(null);
			return;
		} else {
			super.caseAComputationOperation(node);
		}
	}

	@Override
	public void outAComputationOperation(AComputationOperation node) {
		final ComputationOperation computationOperation = this.rulesMachineVisitor.getComputationOperation(node);
		computationLiteralList.add(node.getName());
		if (operationsToBeDeleted.contains(node.getName().getText())) {
			node.replaceBy(null);
			return;
		}

		AOperation operation = new AOperation();
		AIdentifierExpression nameIdentifier;
		{
			// defining the operation name
			// the SableCC node AOperation.class requires a list of
			// TIdentifierLiterals as name
			final List<TIdentifierLiteral> operationNameList = new ArrayList<>();
			if (null != computationOperation.getReplacesIdentifier()) {

				// renaming the operation
				final TIdentifierLiteral first = computationOperation.getReplacesIdentifier().getIdentifier().getFirst();
				operationNameList.add((TIdentifierLiteral) cloneNode(first));
				nameIdentifier = cloneNode(computationOperation.getReplacesIdentifier());
			} else {
				operationNameList.add((TIdentifierLiteral) cloneNode(node.getName()));
				// TODO refactor
				nameIdentifier = cloneNode(new AIdentifierExpression(operationNameList));
			}
			operation.setOpName(operationNameList);
		}

		AEqualPredicate grd1 = new AEqualPredicate(cloneNode(nameIdentifier),
				new AStringExpression(new TStringLiteral(COMPUTATION_NOT_EXECUTED)));
		ASelectSubstitution select = new ASelectSubstitution();
		{
			// guard
			final List<PPredicate> selectConditionList = new ArrayList<>();
			selectConditionList.add(grd1);
			if (!computationOperation.getDependsOnRulesList().isEmpty()) {
				final List<PPredicate> dependsOnRulesPredicates = createPredicateList(
						computationOperation.getDependsOnRulesList(), RULE_SUCCESS);
				selectConditionList.addAll(dependsOnRulesPredicates);
			}
			if (!computationOperation.getDependsOnComputationList().isEmpty()) {
				final List<PPredicate> dependsOnComputationPredicates = createPredicateList(
						computationOperation.getDependsOnComputationList(), COMPUTATION_EXECUTED);
				selectConditionList.addAll(dependsOnComputationPredicates);
			}
			select.setCondition(createConjunction(selectConditionList));
		}
		{
			// substitution
			final ArrayList<PExpression> varList = new ArrayList<>();
			final ArrayList<PExpression> exprList = new ArrayList<>();
			varList.add(cloneNode(nameIdentifier));
			exprList.add(new AStringExpression(new TStringLiteral(COMPUTATION_EXECUTED)));
			AAssignSubstitution assign = new AAssignSubstitution(varList, exprList);
			select.setThen(new ASequenceSubstitution(createSubstitutionList(node.getBody(), assign)));
		}

		operation.setOperationBody(select);
		// replacing the computation by an ordinary operation
		node.replaceBy(operation);

		// create variables in VARIABLES clause
		variablesList.add(cloneNode(nameIdentifier));

		// create invariant in INVARIANT clause
		// Compute_foo : {"COMPUTATION_EXECUTED", "COMPUTATION_NOT_EXECUTED",
		// "COMPUTATION_DISABLED" }
		final List<PExpression> list = new ArrayList<>();
		list.add(createStringExpression(COMPUTATION_EXECUTED));
		list.add(createStringExpression(COMPUTATION_NOT_EXECUTED));
		list.add(createStringExpression(COMPUTATION_DISABLED));
		final ASetExtensionExpression set = new ASetExtensionExpression(list);
		final AMemberPredicate member = new AMemberPredicate(cloneNode(nameIdentifier), set);
		invariantList.add(member);

		PExpression value;
		if (computationOperation.getActivationPredicate() != null) {
			value = new AIfThenElseExpression(
					(PPredicate) NodeCloner.cloneNode(computationOperation.getActivationPredicate()),
					createStringExpression(COMPUTATION_NOT_EXECUTED), createStringExpression(COMPUTATION_DISABLED));
		} else {
			value = createStringExpression(COMPUTATION_NOT_EXECUTED);
		}
		// create substitution in INITIALISATION clause
		final AAssignSubstitution initSub = new AAssignSubstitution(createExpressionList(cloneNode(nameIdentifier)),
				createExpressionList(value));
		initialisationList.add(initSub);
	}

	@Override
	public void caseARuleOperation(ARuleOperation node) {
		if (operationsToBeDeleted.contains(this.rulesMachineVisitor.getRuleOperation(node).getName())) {
			node.replaceBy(null);
			return;
		} else {
			// transform rule operation
			super.caseARuleOperation(node);
		}
	}

	@Override
	public void inARuleOperation(ARuleOperation node) {
		// setting current rule
		this.currentRule = this.rulesMachineVisitor.getRuleOperation(node);
	}

	@Override
	public void outARuleOperation(ARuleOperation node) {
		final String ruleName = currentRule.getName();

		ruleOperationLiteralList.add(node.getRuleName());
		ruleNames.add(ruleName);

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
		final ASelectSubstitution select = new ASelectSubstitution();
		if (!currentRule.getDependsOnRulesList().isEmpty()) {
			final List<PPredicate> dependsOnRulesPredicates = createPredicateList(currentRule.getDependsOnRulesList(),
					RULE_SUCCESS);
			selectConditionList.addAll(dependsOnRulesPredicates);
		}
		if (!currentRule.getDependsOnComputationList().isEmpty()) {
			final List<PPredicate> dependsOnComputationPredicates = createPredicateList(
					currentRule.getDependsOnComputationList(), COMPUTATION_EXECUTED);
			selectConditionList.addAll(dependsOnComputationPredicates);
		}

		select.setCondition(createConjunction(selectConditionList));

		ArrayList<PSubstitution> subList = new ArrayList<>();
		subList.add(node.getRuleBody());
		// IF rule_Counterexamples = {} THEN RULE_SUCCESS ELSE RULE_FAIL END
		final String ctName = ruleName + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		currentRule.setCounterExampleVariableName(ctName);
		PPredicate ifCondition = new AEqualPredicate(createIdentifier(ctName), new AEmptySetExpression());
		PSubstitution ifFailBody = null;
		{
			// fail substitution: set rule to fail and print counterexamples
			ArrayList<PSubstitution> ifFailSubList = new ArrayList<>();
			ifFailSubList.add(createRuleFailAssignment(currentRule.getNameLiteral()));

			TDefLiteralSubstitution defLiteral = new TDefLiteralSubstitution("PRINT");
			List<PExpression> parameters = createExpressionList(createIdentifier(ctName));
			ADefinitionSubstitution printSub = new ADefinitionSubstitution(defLiteral, parameters);
			ifFailSubList.add(printSub);
			ifFailBody = new ASequenceSubstitution(ifFailSubList);
			this.addPrintSubDefinition();
		}

		AIfSubstitution ifSub = new AIfSubstitution(ifCondition,
				createRuleSuccessAssignment(currentRule.getNameLiteral()), new ArrayList<PSubstitution>(), ifFailBody);

		subList.add(ifSub);
		ASequenceSubstitution seq = new ASequenceSubstitution(subList);
		select.setThen(seq);

		// PrettyPrinter pp = new PrettyPrinter();
		// select.apply(pp);
		// System.out.println(pp.getPrettyPrint());

		ArrayList<PExpression> returnValues = new ArrayList<>();
		returnValues.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		returnValues.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		operation.setReturnValues(returnValues);
		operation.setOperationBody(select);

		node.replaceBy(operation); // replacing the rule operation by an
									// ordinary operation

		/*********************************************************/

		// VARIABLES
		variablesList.add(createAIdentifierExpression(node.getRuleName()));

		// INVARIANT
		List<PExpression> list = new ArrayList<>();
		list.add(createStringExpression(RULE_FAIL));
		list.add(createStringExpression(RULE_SUCCESS));
		list.add(createStringExpression(RULE_NOT_CHECKED));
		list.add(createStringExpression(RULE_DISABLED));
		ASetExtensionExpression set = new ASetExtensionExpression(list);
		AMemberPredicate member = createPositinedNode(
				new AMemberPredicate(createAIdentifierExpression(node.getRuleName()), set), node);

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
		invariantList.add(createPositinedNode(ctTypingPredicate, node));

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

	private ADefinitionExpression applyExternalFunction(String name, PExpression... expressions) {
		return new ADefinitionExpression(new TIdentifierLiteral(name), createExpressionList(expressions));
	}

	@Override
	public void outAOperatorExpression(AOperatorExpression node) {
		final String operatorName = node.getName().getText();
		final LinkedList<PExpression> parameters = node.getIdentifiers();
		switch (operatorName) {
		case RulesGrammar.STRING_FORMAT:
			translateStringFormatOperator(node, parameters);
			return;
		case RulesGrammar.STRING_CONCAT:
			translateStringConcatOperator(node, parameters);
			return;
		case RulesGrammar.GET_RULE_COUNTEREXAMPLES:
			translateGetRuleCounterExamplesOperator(node);
			return;
		default:
			throw new AssertionError("Unsupported operator " + operatorName);
		}
	}

	private void translateGetRuleCounterExamplesOperator(AOperatorExpression node) {
		final PExpression pExpression = node.getIdentifiers().get(0);
		final AIdentifierExpression id = (AIdentifierExpression) pExpression;
		final String ruleName = id.getIdentifier().get(0).getText();
		final AbstractOperation operation = allOperations.get(ruleName);
		if (operation == null || !(operation instanceof RuleOperation)) {
			errorList.add(new CheckException(
					String.format("'%s' does not match any rule visible to this machine.", ruleName), node));
			return;
		}
		final RuleOperation rule = (RuleOperation) operation;
		final String name = id.getIdentifier().get(0).getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		if (node.getIdentifiers().size() == 1) {
			final AIdentifierExpression ctVariable = createIdentifier(name, pExpression);
			final ARangeExpression range = createPositinedNode(new ARangeExpression(ctVariable), node);
			node.replaceBy(range);
		} else {
			PExpression funcCall = getSetOfErrorMessagesByErrorType(name, node.getIdentifiers().get(1),
					rule.getNumberOfErrorTypes());
			node.replaceBy(funcCall);
		}
		return;
	}

	private void translateStringConcatOperator(AOperatorExpression node, final LinkedList<PExpression> parameters) {
		PExpression temp = null;
		addExternalFunctionStringAppend();
		for (int i = parameters.size() - 2; i >= 0; i--) {
			if (temp == null) {
				temp = applyExternalFunction(STRING_APPEND, applyExternalFunction(TO_STRING, parameters.get(i)),
						applyExternalFunction(TO_STRING, parameters.get(i + 1)));
			} else {
				temp = applyExternalFunction(STRING_APPEND, applyExternalFunction(TO_STRING, parameters.get(i)), temp);
			}
		}
		node.replaceBy(temp);
		return;
	}

	private void translateStringFormatOperator(AOperatorExpression node, final LinkedList<PExpression> parameters) {
		final TIdentifierLiteral format = new TIdentifierLiteral("FORMAT_TO_STRING");
		format.setStartPos(node.getName().getStartPos());
		format.setEndPos(node.getName().getEndPos());
		PExpression stringValue = parameters.get(0);

		final List<PExpression> list = new ArrayList<>();
		list.add(stringValue);
		final List<PExpression> seqList = new ArrayList<>();
		for (int i = 1; i < parameters.size(); i++) {
			ADefinitionExpression toStringCall = new ADefinitionExpression(new TIdentifierLiteral(TO_STRING),
					createExpressionList(parameters.get(i)));
			seqList.add(toStringCall);
		}
		final ASequenceExtensionExpression seq = new ASequenceExtensionExpression(seqList);
		list.add(seq);
		final ADefinitionExpression def = new ADefinitionExpression(format, list);
		node.replaceBy(def);
		return;
	}

	@Override
	public void outAOperatorSubstitution(AOperatorSubstitution node) {
		final String operatorName = node.getName().getText();
		if (RulesGrammar.RULE_FAIL.equals(operatorName)) {
			final LinkedList<PExpression> arguments = node.getArguments();
			// arguments is never null because a list in sablecc is empty
			// if not provided
			if (arguments.size() == 1) {
				final PSubstitution assign = createCounterExampleSubstitution(1,
						createSetOfPExpression(arguments.get(0), node));
				node.replaceBy(assign);
				return;
			} else if (arguments.size() == 2) {
				PExpression pExpression = node.getArguments().get(0);
				if (pExpression instanceof AIntegerExpression) {
					AIntegerExpression intExpr = (AIntegerExpression) pExpression;
					final int errorIndex = Integer.parseInt(intExpr.getLiteral().getText());
					final PSubstitution assign = createCounterExampleSubstitution(errorIndex,
							createSetOfPExpression(arguments.get(1), node));
					node.replaceBy(assign);
					return;
				} else {
					throw new AssertionError();
				}
			}
		} else {
			throw new AssertionError();
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
		return new AAssignSubstitution(nameList, exprList);
	}

	private PSubstitution createConditionalFailAssignment(TIdentifierLiteral ruleLiteral) {
		final String ctName = ruleLiteral.getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		PPredicate ifCondition = new ANotEqualPredicate(createIdentifier(ctName), new AEmptySetExpression());
		return new AIfSubstitution(ifCondition, createRuleFailAssignment(currentRule.getNameLiteral()),
				new ArrayList<PSubstitution>(), null);
	}

	private AAssignSubstitution createRuleFailAssignment(final TIdentifierLiteral ruleLiteral) {
		/*
		 * rule1, out_rule1 := fail, fail
		 */
		final ArrayList<PExpression> nameList = new ArrayList<>();
		final ArrayList<PExpression> exprList = new ArrayList<>();
		nameList.add(createRuleIdentifier(ruleLiteral));
		nameList.add(createIdentifier(RULE_RESULT_OUTPUT_PARAMETER_NAME));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		exprList.add(new AStringExpression(new TStringLiteral(RULE_FAIL)));
		nameList.add(createIdentifier(RULE_COUNTEREXAMPLE_OUTPUT_PARAMETER_NAME));
		final String ctName = currentRule.getName() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		exprList.add(createIdentifier(ctName));
		return new AAssignSubstitution(nameList, exprList);
	}



	@Override
	public void outADefineSubstitution(ADefineSubstitution node) {
		variablesList.add(createIdentifier(node.getName().getText(), node.getName()));
		AMemberPredicate member = new AMemberPredicate(createRuleIdentifier(node.getName()), node.getType());
		invariantList.add(member);

		if (node.getDummyValue() != null) {
			initialisationList.add(createAssignNode(createRuleIdentifier(node.getName()), node.getDummyValue()));
		} else {
			initialisationList.add(createAssignNode(createRuleIdentifier(node.getName()), new AEmptySetExpression()));
		}

		PExpression value = null;
		if (node.getValue() instanceof ASymbolicLambdaExpression
				|| node.getValue() instanceof ASymbolicComprehensionSetExpression) {
			value = node.getValue();
		} else {
			// value = node.getValue();
			addForceDefinition();
			value = new ADefinitionExpression(new TIdentifierLiteral(FORCE), createExpressionList(node.getValue()));
			value.setStartPos(node.getValue().getStartPos());
			value.setEndPos(node.getValue().getEndPos());
		}

		PSubstitution assign = createAssignNode(createRuleIdentifier(node.getName()), value);
		node.replaceBy(assign);
	}

	private PSubstitution createCounterExampleSubstitution(int errorIndex, PExpression setOfCounterexamples) {
		final String ctName = currentRule.getName() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;

		final AUnionExpression union = new AUnionExpression(createIdentifier(ctName),
				createPositinedNode(new AMultOrCartExpression(
						new ASetExtensionExpression(createExpressionList(
								new AIntegerExpression(new TIntegerLiteral(Integer.toString(errorIndex))))),
						(PExpression) cloneNode(setOfCounterexamples)), setOfCounterexamples));
		AAssignSubstitution assign = new AAssignSubstitution(createExpressionList(createIdentifier(ctName)),
				createExpressionList(union));
		return createSequenceSubstitution(assign, createConditionalFailAssignment(currentRule.getNameLiteral()));
	}



	@Override
	public void outAFunctionOperation(AFunctionOperation node) {
		FunctionOperation func = rulesMachineVisitor.getFunctionOperation(node);

		final List<PPredicate> preConditionList = new ArrayList<>();
		if (func.getPreconditionPredicate() != null) {
			preConditionList.add(func.getPreconditionPredicate());
		}
		if (!func.getDependsOnRulesList().isEmpty()) {
			final List<PPredicate> dependsOnRulesPredicates = createPredicateList(func.getDependsOnRulesList(),
					RULE_SUCCESS);
			preConditionList.addAll(dependsOnRulesPredicates);
		}
		if (!func.getDependsOnComputationList().isEmpty()) {
			final List<PPredicate> dependsOnComputationPredicates = createPredicateList(
					func.getDependsOnComputationList(), COMPUTATION_EXECUTED);
			preConditionList.addAll(dependsOnComputationPredicates);
		}

		PSubstitution body = node.getBody();
		if (null != func.getPostconditionPredicate()) {
			AAssertionSubstitution assertSub = new AAssertionSubstitution(func.getPostconditionPredicate(),
					new ASkipSubstitution());
			body = createSequenceSubstitution(body, assertSub);
		}

		if (!preConditionList.isEmpty()) {
			body = new APreconditionSubstitution(createConjunction(preConditionList), body);
		}
		List<TIdentifierLiteral> nameList = new ArrayList<>();
		nameList.add(node.getName());
		AOperation operation = new AOperation(node.getReturnValues(), nameList, new ArrayList<>(node.getParameters()),
				body);
		node.replaceBy(operation);
	}

	private PExpression getSetOfErrorMessagesByErrorType(String name, PExpression errorTypeNode,
			int numberOfErrorTypes) {
		final ALambdaExpression lambda = new ALambdaExpression();
		final String lambdaIdentifier = "$x";
		lambda.setIdentifiers(createExpressionList(createIdentifier(lambdaIdentifier)));
		lambda.setPredicate(new AMemberPredicate(createIdentifier(lambdaIdentifier),
				new AIntervalExpression(createAIntegerExpression(1), createAIntegerExpression(numberOfErrorTypes))));
		lambda.setExpression(new AImageExpression(createIdentifier(name),
				createSetOfPExpression(createIdentifier(lambdaIdentifier))));
		return new AFunctionExpression(lambda, createExpressionList(errorTypeNode));
	}

	@Override
	public void outAOperatorPredicate(AOperatorPredicate node) {
		// currently all operator handle rule names
		final List<PExpression> arguments = new ArrayList<>(node.getIdentifiers());
		final String operatorName = node.getName().getText();
		final AIdentifierExpression ruleIdentifier = (AIdentifierExpression) arguments.get(0);
		final String ruleName = ruleIdentifier.getIdentifier().get(0).getText();
		AbstractOperation operation = allOperations.get(ruleName);
		if (operation == null || !(operation instanceof RuleOperation)) {
			errorList.add(new CheckException(
					String.format("'%s' does not match any rule visible to this machine.", ruleName), node));
			return;
		}
		final RuleOperation rule = (RuleOperation) operation;
		switch (operatorName) {
		case RulesGrammar.SUCCEEDED_RULE:
			replacePredicateOperator(node, arguments, RULE_SUCCESS);
			return;
		case RulesGrammar.SUCCEEDED_RULE_ERROR_TYPE:
			replaceSucceededRuleErrorTypeOperator(node, ruleName, rule);
			return;
		case RulesGrammar.FAILED_RULE:
			replacePredicateOperator(node, arguments, RULE_FAIL);
			return;
		case RulesGrammar.FAILED_RULE_ALL_ERROR_TYPES:
			replaceFailedRuleAllErrorTypesOperator(node, rule);
			return;
		case RulesGrammar.FAILED_RULE_ERROR_TYPE:
			replaceFailedRuleErrorTypeOperator(node, rule);
			return;
		case RulesGrammar.NOT_CHECKED_RULE:
			replacePredicateOperator(node, arguments, RULE_NOT_CHECKED);
			return;
		case RulesGrammar.DISABLED_RULE:
			replacePredicateOperator(node, arguments, RULE_DISABLED);
			return;
		default:
			throw new AssertionError("should not happen: " + operatorName);
		}
	}

	private void replaceSucceededRuleErrorTypeOperator(AOperatorPredicate node, final String ruleName,
			final RuleOperation rule) {
		String name = ruleName + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		PExpression funcCall = getSetOfErrorMessagesByErrorType(name, node.getIdentifiers().get(1),
				rule.getNumberOfErrorTypes());
		AEqualPredicate equal = new AEqualPredicate(funcCall, new AEmptySetExpression());
		node.replaceBy(equal);
		return;
	}

	private void replaceFailedRuleAllErrorTypesOperator(AOperatorPredicate node, final RuleOperation rule) {
		// dom(rule_cts) = 1..n
		String name = rule.getName() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		AEqualPredicate equal = new AEqualPredicate(new ADomainExpression(createIdentifier(name)),
				new AIntervalExpression(createAIntegerExpression(1),
						createAIntegerExpression(rule.getNumberOfErrorTypes())));
		node.replaceBy(equal);
		return;
	}

	private void replaceFailedRuleErrorTypeOperator(AOperatorPredicate node, final RuleOperation rule) {
		PExpression pExpression = node.getIdentifiers().get(0);
		AIdentifierExpression id = (AIdentifierExpression) pExpression;
		String name = id.getIdentifier().get(0).getText() + RULE_COUNTER_EXAMPLE_VARIABLE_SUFFIX;
		PExpression funcCall = getSetOfErrorMessagesByErrorType(name, node.getIdentifiers().get(1),
				rule.getNumberOfErrorTypes());
		ANotEqualPredicate notEqual = new ANotEqualPredicate(funcCall, new AEmptySetExpression());
		node.replaceBy(notEqual);
		return;
	}

	private List<PPredicate> createPredicateList(final List<AIdentifierExpression> identifiers, final String value) {
		final List<PPredicate> predList = new ArrayList<>();
		for (PExpression e : identifiers) {
			e = NodeCloner.cloneNode(e);
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

	private AIntegerExpression createAIntegerExpression(int i) {
		return new AIntegerExpression(new TIntegerLiteral(Integer.toString(i)));
	}

	@Override
	public void inAForLoopSubstitution(AForLoopSubstitution node) {
		nestedForLoopCount++;
	}

	@Override
	public void outAForLoopSubstitution(AForLoopSubstitution node) {
		/**
		 * FOR x IN set DO sub END
		 **/

		nestedForLoopCount--;
		final String localSetVariableName = "$SET" + nestedForLoopCount;

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
		PExpression chooseCall = new ADefinitionExpression(new TIdentifierLiteral(CHOOSE),
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())));
		PSubstitution assignX = new AAssignSubstitution(
				createExpressionList((PExpression) cloneNode(node.getIdentifier())), createExpressionList(chooseCall));

		// G_Set := G_Set \ {CHOOSE(G_Set)}
		PExpression chooseCall2 = new ADefinitionExpression(new TIdentifierLiteral(CHOOSE),
				createExpressionList(createIdentifier(localSetVariableName, node.getSet()))); // CHOOSE(G_Set)

		// G_Set \ {CHOOSE(G_Set)}
		PExpression rhs = new AMinusOrSetSubtractExpression(createIdentifier(localSetVariableName, node.getSet()),
				new ASetExtensionExpression(createExpressionList(chooseCall2)));

		// G_Set := G_Set \ {CHOOSE(G_Set)}
		PSubstitution assignSetVariable2 = new AAssignSubstitution(
				createExpressionList(createIdentifier(localSetVariableName, node.getSet())), createExpressionList(rhs));
		List<PSubstitution> var2List = new ArrayList<>();
		var2List.add(assignX);
		var2List.add(node.getDoSubst());
		var2List.add(assignSetVariable2);
		varSub2.setSubstitution(new ASequenceSubstitution(var2List));

		node.replaceBy(varSub);
	}

	@Override
	public void outARuleAnySubMessageSubstitution(ARuleAnySubMessageSubstitution node) {
		addForceDefinition();
		final PSubstitution newNode = createPositinedNode(createCounterExampleSubstitutions(node.getIdentifiers(),
				node.getWhere(), null, node.getMessage(), node.getErrorType()), node);
		node.replaceBy(newNode);
	}

	public PSubstitution createCounterExampleSubstitutions(final List<PExpression> identifiers,
			final PPredicate wherePredicate, final PPredicate expectPredicate, final PExpression message,
			final TIntegerLiteral errorTypeNode) {

		final AComprehensionSetExpression set = new AComprehensionSetExpression();
		{
			final List<PExpression> list = new ArrayList<>();
			for (PExpression id : identifiers) {
				PExpression clonedId = cloneNode(id);
				list.add(clonedId);
			}
			set.setIdentifiers(list);
			PPredicate condition = null;
			final PPredicate where = cloneNode(wherePredicate);
			if (expectPredicate != null) {
				final PPredicate expect = cloneNode(expectPredicate);
				condition = new AConjunctPredicate(where, new ANegationPredicate(expect));
			} else {
				condition = where;
			}
			set.setPredicates(condition);
		}
		addToStringDefinition();
		Integer errorType = null;
		if (errorTypeNode != null) {
			errorType = Integer.parseInt(errorTypeNode.getText());
		} else {
			// default value
			errorType = 1;
		}
		AVarSubstitution var = new AVarSubstitution();
		final String resultTuple = "$ResultTuple";
		final String resultStrings = "$ResultSrings";
		var.setIdentifiers(createExpressionList(createIdentifier(resultTuple), createIdentifier(resultStrings)));
		List<PSubstitution> subList = new ArrayList<>();
		{
			AAssignSubstitution assign = new AAssignSubstitution();
			assign.setLhsExpression(createExpressionList(createIdentifier(resultTuple)));
			assign.setRhsExpressions(createExpressionList(
					new ADefinitionExpression(new TIdentifierLiteral(FORCE), createExpressionList(set))));
			// assign.setRhsExpressions(createExpressionList(set));
			subList.add(assign);
		}
		{
			final AComprehensionSetExpression stringSet = new AComprehensionSetExpression();
			final String stringParam = "$String";
			stringSet.setIdentifiers(createExpressionList(createIdentifier(stringParam)));
			final List<PExpression> list = new ArrayList<>();
			final List<PExpression> list2 = new ArrayList<>();
			for (PExpression id : identifiers) {
				PExpression clonedId = cloneNode(id);
				list.add(clonedId);
				PExpression clonedId2 = cloneNode(id);
				list2.add(clonedId2);
			}
			final AExistsPredicate exists = new AExistsPredicate();
			exists.setIdentifiers(list);
			PExpression couple;
			if (list2.size() > 1) {
				couple = new ACoupleExpression(list2);
			} else {
				couple = list2.get(0);
			}
			AMemberPredicate member = new AMemberPredicate(couple, createIdentifier(resultTuple));
			AEqualPredicate equal = new AEqualPredicate(createIdentifier(stringParam), message);
			exists.setPredicate(new AConjunctPredicate(member, equal));
			stringSet.setPredicates(exists);
			AAssignSubstitution assign = new AAssignSubstitution();
			assign.setLhsExpression(createExpressionList(createIdentifier(resultStrings)));
			assign.setRhsExpressions(createExpressionList(stringSet));
			subList.add(assign);
		}

		PSubstitution counterExampleSubstitution = createCounterExampleSubstitution(errorType,
				createIdentifier(resultStrings));
		subList.add(counterExampleSubstitution);
		ASequenceSubstitution seqSub = new ASequenceSubstitution(subList);
		var.setSubstitution(seqSub);
		return var;
	}

	@Override
	public void outAForallSubMessageSubstitution(AForallSubMessageSubstitution node) {
		addForceDefinition();
		PSubstitution newNode = createPositinedNode(createCounterExampleSubstitutions(node.getIdentifiers(),
				node.getWhere(), node.getExpect(), node.getMessage(), node.getErrorType()), node);
		node.replaceBy(newNode);
	}

	@SuppressWarnings("unused")
	private void addStringAppendDefinition() {
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
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_STRING_APPEND"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(
				new AMultOrCartExpression(new AStringSetExpression(), new AStringSetExpression()),
				new AStringSetExpression()));
		try {
			definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}
	}

	private void addToStringDefinition() {
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
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition toStringTypeDef = new AExpressionDefinitionDefinition();
		toStringTypeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_TO_STRING"));
		toStringTypeDef.setParameters(createIdentifierList("X"));
		toStringTypeDef.setRhs(new ATotalFunctionExpression(createIdentifier("X"), new AStringSetExpression()));
		try {
			definitions.addDefinition(toStringTypeDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}
	}

	private void addChooseDefinition() {
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
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition chooseDefType = new AExpressionDefinitionDefinition();
		chooseDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_CHOOSE"));
		chooseDefType.setParameters(createIdentifierList("T"));
		chooseDefType.setRhs(
				new ATotalFunctionExpression(new APowSubsetExpression(createIdentifier("T")), createIdentifier("T")));
		try {
			definitions.addDefinition(chooseDefType, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}
	}

	private void addExternalFunctionStringAppend() {
		if (definitions.containsDefinition(STRING_APPEND)) {
			return;
		}
		AExpressionDefinitionDefinition typeDef = new AExpressionDefinitionDefinition();
		typeDef.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_STRING_APPEND"));
		typeDef.setParameters(new ArrayList<PExpression>());
		// EXTERNAL_FUNCTION_STRING_APPEND == (STRING*STRING) --> STRING;
		typeDef.setRhs(new ATotalFunctionExpression(
				new AMultOrCartExpression(new AStringSetExpression(), new AStringSetExpression()),
				new AStringSetExpression()));
		try {
			definitions.addDefinition(typeDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition def = new AExpressionDefinitionDefinition();
		def.setName(new TIdentifierLiteral(STRING_APPEND));
		def.setParameters(createIdentifierList("a", "b"));
		def.setRhs(createStringExpression("abc"));
		try {
			definitions.addDefinition(def, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}

	}

	private void addForceDefinition() {
		if (definitions.containsDefinition(FORCE)) {
			return;
		}

		// EXTERNAL_FUNCTION_FORCE(T) == T --> T;
		// FORCE(value) == value;
		// forces evaluation of symbolic set representations
		// usage FORCE( { x | x:1..100 & x mod 2 = 0 } )
		AExpressionDefinitionDefinition forceDef = new AExpressionDefinitionDefinition();
		forceDef.setName(new TIdentifierLiteral(FORCE));
		String value = "value";
		forceDef.setParameters(createIdentifierList(value));
		forceDef.setRhs(createIdentifier(value));
		try {
			definitions.addDefinition(forceDef, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition forceDefType = new AExpressionDefinitionDefinition();
		forceDefType.setName(new TIdentifierLiteral("EXTERNAL_FUNCTION_" + FORCE));
		forceDefType.setParameters(createIdentifierList("T"));
		forceDefType.setRhs(new ATotalFunctionExpression(createIdentifier("T"), createIdentifier("T")));
		try {
			definitions.addDefinition(forceDefType, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}
	}

	private void addPrintSubDefinition() {
		final String PRINT = "PRINT";
		if (definitions.containsDefinition(PRINT)) {
			return;
		}

		// PRINT(x) == skip;
		// EXTERNAL_SUBSTITUTION_PRINT(T) == T; /* declare as external for any
		// type T */
		ASubstitutionDefinitionDefinition printDef = new ASubstitutionDefinitionDefinition();
		printDef.setName(new TDefLiteralSubstitution(PRINT));
		printDef.setParameters(createIdentifierList("value"));
		printDef.setRhs(new ASkipSubstitution());
		try {
			definitions.addDefinition(printDef, IDefinitions.Type.Substitution);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}

		AExpressionDefinitionDefinition forceDefType = new AExpressionDefinitionDefinition();
		forceDefType.setName(new TIdentifierLiteral("EXTERNAL_SUBSTITUTION_" + PRINT));
		forceDefType.setParameters(createIdentifierList("T"));
		forceDefType.setRhs(createIdentifier("T"));
		try {
			definitions.addDefinition(forceDefType, IDefinitions.Type.Expression);
		} catch (CheckException | BException e) {
			throw new AssertionError(e);
		}
	}

	private List<PExpression> createIdentifierList(String... strings) {
		ArrayList<PExpression> list = new ArrayList<>();
		for (int i = 0; i < strings.length; i++) {
			list.add(createIdentifier(strings[i]));
		}
		return list;
	}

}
