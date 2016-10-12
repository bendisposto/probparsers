package de.be4.classicalb.core.parser.antlr;

import java.util.ArrayList;
import java.util.List;

import static files.BParser.*;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.be4.classicalb.core.parser.antlr.DefinitionsAnalyser.DefinitionType;
import de.be4.classicalb.core.parser.node.*;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePosition;
import files.*;
import files.BParser.Expression_in_parContext;
import files.BParser.Set_definitionContext;
import files.BParser.SubstitutionContext;

public class SableCCAstBuilder extends BParserBaseVisitor<Node> {
	private final DefinitionsAnalyser definitionsAnalyser;

	public SableCCAstBuilder(DefinitionsAnalyser definitionsAnalyser) {
		this.definitionsAnalyser = definitionsAnalyser;
	}

	@Override
	public Node visitStart(StartContext ctx) {
		PParseUnit parseUnit = (PParseUnit) ctx.parse_unit().accept(this);
		return new Start(parseUnit, new EOF()); // start does not position info
	}

	@Override
	protected Node aggregateResult(Node aggregate, Node nextResult) {
		return nextResult == null ? aggregate : nextResult;
	}

	@Override
	public Node visitExpressionParseUnit(ExpressionParseUnitContext ctx) {
		PExpression expression = (PExpression) ctx.expression().accept(this);
		return createPositionedNode(new AExpressionParseUnit(expression), ctx);
	}

	@Override
	public Node visitPredicateParseUnit(PredicateParseUnitContext ctx) {
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		return createPositionedNode(new APredicateParseUnit(predicate), ctx);
	}

	@Override
	public Node visitSubstitutionParseUnit(SubstitutionParseUnitContext ctx) {
		PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		return createPositionedNode(new ASubstitutionParseUnit(substitution), ctx);
	}

	@Override
	public Node visitFormulaParseUnit(FormulaParseUnitContext ctx) {
		Node formula = ctx.formula().accept(this);
		if (formula instanceof PPredicate) {
			return createPositionedNode(new APredicateParseUnit((PPredicate) formula), ctx);
		} else {
			return createPositionedNode(new AExpressionParseUnit((PExpression) formula), ctx);
		}
	}

	@Override
	public Node visitMachine(MachineContext ctx) {
		final AMachineHeader header = (AMachineHeader) ctx.machine_header().accept(this);
		final List<PMachineClause> list = new ArrayList<>();
		final List<Machine_clausesContext> clauses = ctx.clauses;
		for (Machine_clausesContext clause : clauses) {
			final PMachineClause clauseNode = (PMachineClause) clause.accept(this);
			list.add(clauseNode);
		}
		PMachineVariant variant;
		switch (ctx.variant.getType()) {
		case MACHINE:
			variant = createPositionedNode(new AMachineMachineVariant(), ctx.variant);
			break;
		case MODEL:
			variant = createPositionedNode(new AModelMachineVariant(), ctx.variant);
			break;
		case SYSTEM:
			variant = createPositionedNode(new ASystemMachineVariant(), ctx.variant);
			break;
		default:
			throw new RuntimeException("unexpected");
		}
		return createPositionedNode(new AAbstractMachineParseUnit(variant, header, list), ctx);
	}

	@Override
	public Node visitDefinitionClause(DefinitionClauseContext ctx) {
		final List<DefinitionContext> defContexts = ctx.defs;
		final List<PDefinition> definitionList = new ArrayList<>();
		for (DefinitionContext definitionContext : defContexts) {
			final PDefinition definition = (PDefinition) definitionContext.accept(this);
			definitionList.add(definition);
		}
		return createPositionedNode(new ADefinitionsMachineClause(definitionList), ctx);
	}

	private DefinitionType currentDefinitionType;

	@Override
	public Node visitDefinition(DefinitionContext ctx) {
		final String definitionName = ctx.name.getText();
		final List<Token> parameterTokens = ctx.parameters;
		final List<PExpression> parameterList = new ArrayList<>();
		for (Token token : parameterTokens) {
			final PExpression param = createAIdentifierExpression(token);
			parameterList.add(param);
		}

		final DefinitionType type = definitionsAnalyser.getDefinitionType(definitionName);
		final FormulaContext formula = ctx.formula();
		currentDefinitionType = type;
		final Node rhs = formula.accept(this);
		currentDefinitionType = null;

		switch (type) {
		case SUBSTITUTION_DEFINITION:
			final TDefLiteralSubstitution subLiteral = new TDefLiteralSubstitution(definitionName, ctx.name.getLine(),
					ctx.name.getCharPositionInLine());
			return createPositionedNode(
					new ASubstitutionDefinitionDefinition(subLiteral, parameterList, (PSubstitution) rhs), ctx);
		case PREDICATE_DEFINITION:
			final TDefLiteralPredicate predLiteral = new TDefLiteralPredicate(definitionName, ctx.name.getLine(),
					ctx.name.getCharPositionInLine());
			return createPositionedNode(
					new APredicateDefinitionDefinition(predLiteral, parameterList, (PPredicate) rhs), ctx);
		case EXPRESSION_DEFINITION:
			final TIdentifierLiteral exprLiteral = new TIdentifierLiteral(definitionName, ctx.name.getLine(),
					ctx.name.getCharPositionInLine());
			return createPositionedNode(
					new AExpressionDefinitionDefinition(exprLiteral, parameterList, (PExpression) rhs), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitMachine_header(Machine_headerContext ctx) {
		final String name = ctx.name.getText();
		final List<TIdentifierLiteral> nameList = new ArrayList<TIdentifierLiteral>();
		nameList.add(new TIdentifierLiteral(name));

		final List<PExpression> parameterList = new ArrayList<>();
		Identifier_listContext param_list = ctx.parameter_list;
		if (param_list != null) {
			List<Token> idents = param_list.idents;
			for (Token param : idents) {
				parameterList.add(createAIdentifierExpression(param));
			}
		}
		return createPositionedNode(new AMachineHeader(nameList, parameterList), ctx);
	}

	@Override
	public Node visitDeclarationClause(DeclarationClauseContext ctx) {
		List<PExpression> list = new ArrayList<>();
		List<Token> idents = ctx.identifier_list().idents;
		for (Token token : idents) {
			list.add(createAIdentifierExpression(token));
		}
		switch (ctx.name.getType()) {
		case ABSTRACT_CONSTANTS:
			return createPositionedNode(new AAbstractConstantsMachineClause(list), ctx);
		case CONSTANTS:
		case CONCRETE_CONSTANTS:
			return createPositionedNode(new AConstantsMachineClause(list), ctx);
		case VARIABLES:
		case ABSTRACT_VARIABLES:
			return createPositionedNode(new AVariablesMachineClause(list), ctx);
		case CONCRETE_VARIABLES:
			return createPositionedNode(new AConcreteVariablesMachineClause(list), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitReferenceClause(ReferenceClauseContext ctx) {
		List<PExpression> list = new ArrayList<>();
		List<Composed_identifierContext> composedIdents = ctx.composed_identifier_list().idents;
		for (Composed_identifierContext composed_ident : composedIdents) {
			ComposedIdentifierContext c = (ComposedIdentifierContext) composed_ident;
			List<TerminalNode> identifier = c.Identifier();
			List<TIdentifierLiteral> tidents = new ArrayList<>();
			for (TerminalNode terminalNode : identifier) {

				tidents.add(new TIdentifierLiteral(terminalNode.getText()));
			}
			list.add(createPositionedNode(new AIdentifierExpression(tidents), composed_ident));
		}
		switch (ctx.name.getType()) {
		case SEES:
			return new ASeesMachineClause(list);
		case USES:
			return new AUsesMachineClause(list);
		case PROMOTES:
			return new APromotesMachineClause(list);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitInstanceClause(InstanceClauseContext ctx) {
		final List<PMachineReference> list = new ArrayList<>();
		final List<Machine_instantiationContext> instances = ctx.instances;
		for (Machine_instantiationContext machine_instantiationContext : instances) {
			AMachineReference instance = (AMachineReference) machine_instantiationContext.accept(this);
			list.add(instance);
		}
		switch (ctx.name.getType()) {
		case INCLUDES:
			return createPositionedNode(new AIncludesMachineClause(list), ctx);
		case EXTENDS:
			return createPositionedNode(new AExtendsMachineClause(list), ctx);
		case IMPORTS:
			return createPositionedNode(new AImportsMachineClause(list), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitMachine_instantiation(Machine_instantiationContext ctx) {
		ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.ident;
		List<TerminalNode> identifiers = subNode.Identifier();
		List<TIdentifierLiteral> list = new ArrayList<>();
		for (TerminalNode id : identifiers) {
			list.add(new TIdentifierLiteral(id.getText(), id.getSymbol().getLine(),
					id.getSymbol().getCharPositionInLine()));
		}

		List<ExpressionContext> expr_list = ctx.expression();
		List<PExpression> expressionList = new ArrayList<>();
		if (expr_list != null) {
			for (ExpressionContext expressionContext : expr_list) {
				PExpression expr = (PExpression) expressionContext.accept(this);
				expressionList.add(expr);
			}
		}
		return createPositionedNode(new AMachineReference(list, expressionList), ctx);
	}

	@Override
	public Node visitSetsClause(BParser.SetsClauseContext ctx) {
		final List<Set_definitionContext> set_definitions = ctx.set_definition();
		final List<PSet> list = new ArrayList<>();
		for (Set_definitionContext setContext : set_definitions) {
			list.add((PSet) setContext.accept(this));
		}
		return createPositionedNode(new ASetsMachineClause(list), ctx);
	}

	@Override
	public Node visitDeferredSet(BParser.DeferredSetContext ctx) {
		TerminalNode identifier = ctx.Identifier();
		List<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral ident = new TIdentifierLiteral(identifier.getText(), identifier.getSymbol().getLine(),
				identifier.getSymbol().getCharPositionInLine());
		list.add(ident);
		return createPositionedNode(new ADeferredSetSet(list), ctx);
	}

	@Override
	public Node visitEnumeratedSet(BParser.EnumeratedSetContext ctx) {
		final TerminalNode identifier = ctx.Identifier();
		final List<TIdentifierLiteral> list = new ArrayList<>();
		final TIdentifierLiteral ident = new TIdentifierLiteral(identifier.getText(), identifier.getSymbol().getLine(),
				identifier.getSymbol().getCharPositionInLine());
		list.add(ident);

		final List<PExpression> values = createPExpressionListFromIdentifierList(ctx.identifier_list());
		return createPositionedNode(new AEnumeratedSetSet(list, values), ctx);
	}

	@Override
	public Node visitInitialisationClause(InitialisationClauseContext ctx) {
		PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		return createPositionedNode(new AInitialisationMachineClause(substitution), ctx);
	}

	@Override
	public Node visitOperationsClause(OperationsClauseContext ctx) {
		final List<OperationContext> ops = ctx.ops;
		final List<POperation> operationList = new ArrayList<>();
		for (OperationContext operationContext : ops) {
			POperation operation = (POperation) operationContext.accept(this);
			operationList.add(operation);
		}
		return createPositionedNode(new AOperationsMachineClause(operationList), ctx);
	}

	@Override
	public Node visitOperation(OperationContext ctx) {
		final List<PExpression> returnValuesList = createPExpressionListFromIdentifierList(ctx.output);
		final List<PExpression> parameterList = createPExpressionListFromIdentifierList(ctx.parameters);
		final PSubstitution body = (PSubstitution) ctx.substitution().accept(this);
		final List<TIdentifierLiteral> opName = createTIdentifierLiteralList(ctx.Identifier());
		return createPositionedNode(new AOperation(returnValuesList, opName, parameterList, body), ctx);
	}

	/*
	 * Helper methods
	 */

	private List<PExpression> createPExpressionListFromIdentifierList(final Identifier_listContext identifierList) {
		final List<PExpression> list = new ArrayList<>();
		if (identifierList != null) {
			final List<Token> idents = identifierList.idents;
			for (Token param : idents) {
				list.add(createAIdentifierExpression(param));
			}
		}
		return list;
	}

	private PExpression createAIdentifierExpression(final Token token) {
		final List<TIdentifierLiteral> list = new ArrayList<>();
		final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(token.getText(), token.getLine(),
				token.getCharPositionInLine());
		list.add(tIdentifier);
		AIdentifierExpression pExpression = new AIdentifierExpression(list);
		// TODO position of a token
		pExpression.setStartPos(new SourcePosition(token.getLine(), token.getStartIndex()));
		pExpression.setEndPos(new SourcePosition(token.getLine(), token.getStopIndex()));
		return pExpression;
	}

	private List<TIdentifierLiteral> createTIdentifierLiteralList(final TerminalNode terminalNode) {
		final List<TIdentifierLiteral> list = new ArrayList<>();
		final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(terminalNode.getText(),
				terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine());
		list.add(tIdentifier);
		return list;
	}

	private <T extends PositionedNode> T createPositionedNode(T node, ParserRuleContext ctx) {
		final Token start = ctx.start;
		final Token end = ctx.stop;
		node.setStartPos(new SourcePosition(start.getLine(), start.getCharPositionInLine()));
		node.setEndPos(new SourcePosition(end.getLine(), end.getCharPositionInLine()));
		return node;
	}

	private <T extends PositionedNode> T createPositionedNode(T node, Token token) {
		final int tokenSize = token.getText().length();
		node.setStartPos(new SourcePosition(token.getLine(), token.getCharPositionInLine()));
		node.setEndPos(new SourcePosition(token.getLine(), token.getCharPositionInLine() + tokenSize));
		return node;
	}

	/*
	 * Substitutions
	 */
	@Override
	public Node visitSubstitutionBlock(BParser.SubstitutionBlockContext ctx) {
		final PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		return createPositionedNode(new ABlockSubstitution(substitution), ctx);
	}

	@Override
	public Node visitSubstitutionSkip(SubstitutionSkipContext ctx) {
		return createPositionedNode(new ASkipSubstitution(), ctx);
	}

	@Override
	public Node visitChoiceSubstitution(BParser.ChoiceSubstitutionContext ctx) {
		final List<SubstitutionContext> substitutions = ctx.substitution();
		final List<PSubstitution> list = new ArrayList<>();

		for (SubstitutionContext substitutionContext : substitutions) {
			PSubstitution sub = (PSubstitution) substitutionContext.accept(this);
			list.add(createPositionedNode(new AChoiceOrSubstitution(sub), substitutionContext));
		}
		return createPositionedNode(new AChoiceSubstitution(list), ctx);
	}

	@Override
	public Node visitConditionSubstitution(BParser.ConditionSubstitutionContext ctx) {
		final PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		final PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		switch (ctx.keyword.getType()) {
		case PRE:
			return createPositionedNode(new APreconditionSubstitution(predicate, substitution), ctx);
		case ASSERT:
			return createPositionedNode(new AAssertionSubstitution(predicate, substitution), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitSelectSubstitution(BParser.SelectSubstitutionContext ctx) {
		final PPredicate predicate = (PPredicate) ctx.pred.accept(this);
		final PSubstitution then = (PSubstitution) ctx.sub.accept(this);
		final PSubstitution elseSub = ctx.else_sub != null ? (PSubstitution) ctx.else_sub.accept(this) : null;
		final List<PSubstitution> whenSub = new ArrayList<>();
		for (int i = 0; i < ctx.when_pred.size(); i++) {
			final PPredicate pred = (PPredicate) ctx.when_pred.get(i).accept(this);
			final PSubstitution sub = (PSubstitution) ctx.when_sub.get(i).accept(this);
			ASelectWhenSubstitution aSelectWhenSubstitution = new ASelectWhenSubstitution(pred, sub);
			aSelectWhenSubstitution
					.setStartPos(new SourcePosition(pred.getStartPos().getLine(), pred.getStartPos().getPos()));
			aSelectWhenSubstitution
					.setStartPos(new SourcePosition(sub.getEndPos().getLine(), pred.getStartPos().getPos()));
			whenSub.add(aSelectWhenSubstitution);
		}
		return createPositionedNode(new ASelectSubstitution(predicate, then, whenSub, elseSub), ctx);
	}

	@Override
	public Node visitCaseSubstitution(BParser.CaseSubstitutionContext ctx) {
		final PExpression expression = (PExpression) ctx.expression().accept(this);
		final List<PExpression> either = createPExpressionList(ctx.either);
		final PSubstitution substitution = (PSubstitution) ctx.sub.accept(this);

		final PSubstitution elseSub = ctx.else_sub != null ? (PSubstitution) ctx.else_sub.accept(this) : null;
		final List<PSubstitution> caseOrs = new ArrayList<>();
		for (int i = 0; i < ctx.or_exprs.size(); i++) {
			List<PExpression> expressionList = createPExpressionList(ctx.or_exprs.get(i));
			PSubstitution sub = (PSubstitution) ctx.or_subs.get(i).accept(this);
			ACaseOrSubstitution caseOr = new ACaseOrSubstitution(expressionList, sub);
			PExpression first = expressionList.get(0);
			caseOr.setStartPos(new SourcePosition(first.getStartPos().getLine(), first.getStartPos().getPos()));
			caseOr.setEndPos(new SourcePosition(sub.getEndPos().getLine(), sub.getEndPos().getPos()));
			caseOrs.add(caseOr);
		}
		return createPositionedNode(new ACaseSubstitution(expression, either, substitution, caseOrs, elseSub), ctx);
	}

	@Override
	public Node visitAnySubstitution(BParser.AnySubstitutionContext ctx) {
		return createPositionedNode(
				new AAnySubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this), (PSubstitution) ctx.substitution().accept(this)),
				ctx);
	}

	@Override
	public Node visitLetSubstitution(BParser.LetSubstitutionContext ctx) {
		return createPositionedNode(
				new ALetSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this), (PSubstitution) ctx.substitution().accept(this)),
				ctx);
	}

	@Override
	public Node visitBecomesElementOfSubstitution(BParser.BecomesElementOfSubstitutionContext ctx) {
		return createPositionedNode(
				new ABecomesElementOfSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PExpression) ctx.expression().accept(this)),
				ctx);
	}

	@Override
	public Node visitBecomesSuchThatSubstitution(BParser.BecomesSuchThatSubstitutionContext ctx) {
		return createPositionedNode(
				new ABecomesSuchSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this)),
				ctx);
	}

	@Override
	public Node visitVarSubstitution(BParser.VarSubstitutionContext ctx) {
		return createPositionedNode(new AVarSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
				(PSubstitution) ctx.substitution().accept(this)), ctx);
	}

	@Override
	public Node visitWhileSubstitution(BParser.WhileSubstitutionContext ctx) {
		final PPredicate condition = (PPredicate) ctx.condition.accept(this);
		final PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		final PPredicate invariant = (PPredicate) ctx.substitution().accept(this);
		final PExpression variant = (PExpression) ctx.variant.accept(this);
		return createPositionedNode(new AWhileSubstitution(condition, substitution, invariant, variant), ctx);
	}

	@Override
	public Node visitSubstitutionCompositionOrParallel(SubstitutionCompositionOrParallelContext ctx) {
		final int type = ctx.operator.getType();
		List<PSubstitution> list = new ArrayList<>();
		PSubstitution left = (PSubstitution) ctx.left.accept(this);
		PSubstitution right = (PSubstitution) ctx.right.accept(this);
		list.add(left);
		list.add(right);
		switch (type) {
		case SEMICOLON:
			return createPositionedNode(new ASequenceSubstitution(list), ctx);
		case DOUBLE_VERTICAL_BAR:
			return createPositionedNode(new AParallelSubstitution(list), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitAssignSubstitution(AssignSubstitutionContext ctx) {
		List<Identifier_or_function_or_recordContext> identifier_or_function_or_records = ctx
				.identifier_or_function_or_record();
		List<PExpression> leftList = new ArrayList<>();
		for (Identifier_or_function_or_recordContext id : identifier_or_function_or_records) {
			PExpression pExpression = (PExpression) id.accept(this);
			leftList.add(pExpression);
		}
		List<PExpression> rightList = createPExpressionList(ctx.expression_list());
		return createPositionedNode(new AAssignSubstitution(leftList, rightList), ctx);
	}

	@Override
	public Node visitAssignRecordIdentifier(AssignRecordIdentifierContext ctx) {
		final Token identifier = ctx.name;
		final PExpression base = createAIdentifierExpression(identifier);
		final List<Token> attributes = ctx.attributes;
		Token fieldToken = attributes.get(0);
		PExpression fieldNode = createAIdentifierExpression(fieldToken);
		ARecordFieldExpression rec = new ARecordFieldExpression(base, fieldNode);
		rec.setStartPos(new SourcePosition(ctx.start.getLine(), ctx.start.getCharPositionInLine()));
		rec.setEndPos(new SourcePosition(fieldToken.getLine(), fieldToken.getStopIndex()));
		for (int i = 1; i < attributes.size(); i++) {
			fieldToken = attributes.get(i);
			fieldNode = createAIdentifierExpression(fieldToken);
			rec = new ARecordFieldExpression(rec, fieldNode);
			rec.setStartPos(new SourcePosition(ctx.start.getLine(), ctx.start.getCharPositionInLine()));
			rec.setEndPos(new SourcePosition(fieldToken.getLine(), fieldToken.getStopIndex()));
		}
		return rec;
	}

	@Override
	public Node visitAssignFunctionIdentifier(AssignFunctionIdentifierContext ctx) {
		Token name = ctx.name;
		final PExpression base = createAIdentifierExpression(name);
		List<PExpression> expressionList = createPExpressionList(ctx.arguments);
		return createPositionedNode(new AFunctionExpression(base, expressionList), ctx);
	}

	private List<PExpression> createPExpressionList(Expression_listContext expression_list) {
		final List<Expression_in_parContext> expressionContexts = expression_list.expression_in_par();
		final List<PExpression> expressionList = new ArrayList<>();
		for (Expression_in_parContext expressionContext : expressionContexts) {
			final PExpression expression = (PExpression) expressionContext.accept(this);
			expressionList.add(expression);
		}
		return expressionList;
	}

	@Override
	public Node visitAssignSingleIdentifier(AssignSingleIdentifierContext ctx) {
		final TerminalNode identifier = ctx.Identifier();
		return createAIdentifierExpression(identifier.getSymbol());
	}

	@Override
	public Node visitPredicateClause(PredicateClauseContext ctx) {
		PPredicate pred = (PPredicate) ctx.pred.accept(this);
		switch (ctx.name.getType()) {
		case INVARIANT:
			return createPositionedNode(new AInvariantMachineClause(pred), ctx);
		case PROPERTIES:
			return createPositionedNode(new APropertiesMachineClause(pred), ctx);
		case CONSTRAINTS:
			return createPositionedNode(new AConstraintsMachineClause(pred), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	/***************** predicates *********************/

	@Override
	public Node visitAndOrList(AndOrListContext ctx) {
		List<Predicate_atomicContext> terms = ctx.terms;
		List<Token> operators = ctx.operators;
		PPredicate temp = (PPredicate) ctx.terms.get(0).accept(this);
		for (int i = 0; i < operators.size(); i++) {
			Predicate_atomicContext rightContext = terms.get(i + 1);
			PPredicate right = (PPredicate) rightContext.accept(this);
			if (ctx.operators.get(i).getType() == AND) {
				temp = new AConjunctPredicate(temp, right);
			} else {
				temp = new ADisjunctPredicate(temp, right);
			}
			temp.setStartPos(new SourcePosition(ctx.start.getLine(), ctx.start.getCharPositionInLine()));
			temp.setEndPos(new SourcePosition(rightContext.stop.getLine(), rightContext.stop.getLine()));
		}
		return temp;
	}

//	@Override
//	public Node visitWeakestPreconditionPredicate(BParser.WeakestPreconditionPredicateContext ctx) {
//		List<PExpression> leftList = createPExpressionListFromIdentifierList(ctx.identifier_list());
//		List<PExpression> rightList = createPExpressionList(ctx.expression_list());
//		AAssignSubstitution sub = createPositionedNode(new AAssignSubstitution(leftList, rightList), ctx);
//		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
//		return new ASubstitutionPredicate(sub, predicate);
//	}

	@Override
	public Node visitPredicateNot(PredicateNotContext ctx) {
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		return createPositionedNode(new ANegationPredicate(predicate), ctx);
	}

	@Override
	public Node visitImplication(ImplicationContext ctx) {
		final PPredicate left = (PPredicate) ctx.left.accept(this);
		final PPredicate right = (PPredicate) ctx.right.accept(this);
		return createPositionedNode(new AImplicationPredicate(left, right), ctx);
	}

	@Override
	public Node visitPredicateBinExpression(PredicateBinExpressionContext ctx) {
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		switch (ctx.predicate_expression_operator().operator.getType()) {
		case EQUAL:
			return createPositionedNode(new AEqualPredicate(left, right), ctx);
		case ELEMENT_OF:
			return createPositionedNode(new AMemberPredicate(left, right), ctx);
		case INCLUSION:
			return createPositionedNode(new ASubsetPredicate(left, right), ctx);
		case STRICT_INCLUSION:
			return createPositionedNode(new ASubsetStrictPredicate(left, right), ctx);
		case NON_INCLUSION:
			return createPositionedNode(new ANotSubsetPredicate(left, right), ctx);
		case STRICT_NON_INCLUSION:
			return createPositionedNode(new ANotSubsetStrictPredicate(left, right), ctx);
		case NOT_EQUAL:
			return createPositionedNode(new ANotEqualPredicate(left, right), ctx);
		case NOT_BELONGING:
			return createPositionedNode(new ANotMemberPredicate(left, right), ctx);
		case LESS_EQUAL:
			return createPositionedNode(new ALessEqualPredicate(left, right), ctx);
		case LESS:
			return createPositionedNode(new ALessPredicate(left, right), ctx);
		case GREATER_EQUAL:
			return createPositionedNode(new AGreaterEqualPredicate(left, right), ctx);
		case GREATER:
			return createPositionedNode(new AGreaterPredicate(left, right), ctx);
		default:
			throw new RuntimeException("unexpected exception:");
		}
	}

	@Override
	public Node visitSubstitutionIdentifierCall(SubstitutionIdentifierCallContext ctx) {
		final ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		final List<PExpression> argumentList = createPExpressionList(ctx.expression_list());
		final List<TerminalNode> identifiers = subNode.Identifier();
		final TerminalNode id = identifiers.get(0);
		if (identifiers.size() == 1 && definitionsAnalyser.isDefinition(id.getText())) {

			TDefLiteralSubstitution predLiteral = new TDefLiteralSubstitution(id.getText(), id.getSymbol().getLine(),
					id.getSymbol().getCharPositionInLine());
			return new ADefinitionSubstitution(predLiteral, argumentList);
		} else {

			final List<TIdentifierLiteral> list = new ArrayList<>();
			for (TerminalNode terminalNode : identifiers) {
				final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(terminalNode.getText(),
						terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine());
				list.add(tIdentifier);
			}
			AOperationCallSubstitution opCall = new AOperationCallSubstitution(new ArrayList<PExpression>(), list,
					argumentList);
			return createPositionedNode(opCall, ctx);
		}

	}

	@Override
	public Node visitPredicateIdentifierCall(PredicateIdentifierCallContext ctx) {
		ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		List<TerminalNode> identifiers = subNode.Identifier();
		if (identifiers.size() > 1) {
			throw new RuntimeException("ComposedIdentifier as an predicate is currently not supported by the java AST");
		}
		TerminalNode id = identifiers.get(0);
		final List<ExpressionContext> arguments = ctx.arguments;
		if (definitionsAnalyser.isDefinition(id.getText())) {

			final List<PExpression> argumentList = new ArrayList<>();
			for (ExpressionContext argument : arguments) {
				PExpression exprNode = (PExpression) argument.accept(this);
				argumentList.add(exprNode);
			}
			TDefLiteralPredicate predLiteral = new TDefLiteralPredicate(id.getText(), id.getSymbol().getLine(),
					id.getSymbol().getCharPositionInLine());
			return new ADefinitionPredicate(predLiteral, argumentList);

		} else {
			if (arguments.size() != 0) {
				throw new RuntimeException("Predicate identifier should not have any arguments.");
			}
			return createPositionedNode(new APredicateIdentifierPredicate(new TPredicateIdentifier(id.getText(),
					id.getSymbol().getLine(), id.getSymbol().getCharPositionInLine())), ctx);
		}

	}

	@Override
	public Node visitExpressionIdentifier(ExpressionIdentifierContext ctx) {
		ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		List<TerminalNode> identifiers = subNode.Identifier();
		final String firstName = identifiers.get(0).getText();
		if (identifiers.size() == 1 && definitionsAnalyser.isDefinition(firstName)) {
			TerminalNode id = identifiers.get(0);
			DefinitionType type = definitionsAnalyser.getDefinitionType(firstName);
			switch (type) {
			case EXPRESSION_DEFINITION:
				TIdentifierLiteral identLiteral = new TIdentifierLiteral(id.getText(), id.getSymbol().getLine(),
						id.getSymbol().getCharPositionInLine());
				return createPositionedNode(new ADefinitionExpression(identLiteral, new ArrayList<PExpression>()), ctx);
			case PREDICATE_DEFINITION:
				TDefLiteralPredicate predLiteral = new TDefLiteralPredicate(id.getText(), id.getSymbol().getLine(),
						id.getSymbol().getCharPositionInLine());
				return createPositionedNode(new ADefinitionPredicate(predLiteral, new ArrayList<PExpression>()), ctx);
			case SUBSTITUTION_DEFINITION:
				TDefLiteralSubstitution subLiteral = new TDefLiteralSubstitution(id.getText(), id.getSymbol().getLine(),
						id.getSymbol().getCharPositionInLine());
				return createPositionedNode(new ADefinitionSubstitution(subLiteral, new ArrayList<PExpression>()), ctx);
			default:
				throw new RuntimeException("unexpected");
			}
		} else {
			List<TIdentifierLiteral> list = new ArrayList<>();
			for (TerminalNode id : identifiers) {
				list.add(new TIdentifierLiteral(id.getText(), id.getSymbol().getLine(),
						id.getSymbol().getCharPositionInLine()));
			}
			return createPositionedNode(new AIdentifierExpression(list), ctx);
		}
	}

	@Override
	public Node visitExpressionPrefixOperator(ExpressionPrefixOperatorContext ctx) {
		PExpression _expression_ = (PExpression) ctx.expr.accept(this);
		final int type = ctx.expression_prefix_operator().operator.getType();
		switch (type) {
		case BTREE:
			return createPositionedNode(new ABtreeExpression(_expression_), ctx);
		case CARD:
			return createPositionedNode(new ACardExpression(_expression_), ctx);
		case CLOSURE:
			return createPositionedNode(new AClosureExpression(_expression_), ctx);
		case CLOSURE1:
			return createPositionedNode(new AReflexiveClosureExpression(_expression_), ctx);
		case CONC:
			return createPositionedNode(new AGeneralConcatExpression(_expression_), ctx);
		case DOM:
			return createPositionedNode(new ADomainExpression(_expression_), ctx);
		case FIRST:
			return createPositionedNode(new AFirstExpression(_expression_), ctx);
		case FNC:
			return createPositionedNode(new ATransFunctionExpression(_expression_), ctx);
		case FRONT:
			return createPositionedNode(new AFrontExpression(_expression_), ctx);
		case ID:
			return createPositionedNode(new AIdentityExpression(_expression_), ctx);
		case INFIX:
			return createPositionedNode(new AInfixExpression(_expression_), ctx);
		case ISEQ:
			return createPositionedNode(new AIseqExpression(_expression_), ctx);
		case ISEQ1:
			return createPositionedNode(new AIseq1Expression(_expression_), ctx);
		case LAST:
			return createPositionedNode(new ALastExpression(_expression_), ctx);
		case LEFT:
			return createPositionedNode(new ALeftExpression(_expression_), ctx);
		case MAX:
			return createPositionedNode(new AMaxExpression(_expression_), ctx);
		case MIN:
			return createPositionedNode(new AMinExpression(_expression_), ctx);
		case MIRROR:
			return createPositionedNode(new AMirrorExpression(_expression_), ctx);
		case PERM:
			return createPositionedNode(new APermExpression(_expression_), ctx);
		case POSTFIX:
			return createPositionedNode(new APostfixExpression(_expression_), ctx);
		case POW:
			return createPositionedNode(new APowSubsetExpression(_expression_), ctx);
		case PREFIX:
			return createPositionedNode(new APrefixExpression(_expression_), ctx);
		case RAN:
			return createPositionedNode(new ARangeExpression(_expression_), ctx);
		case REL:
			return createPositionedNode(new ATransRelationExpression(_expression_), ctx);
		case REV:
			return createPositionedNode(new AReverseExpression(_expression_), ctx);
		case RIGHT:
			return createPositionedNode(new ARightExpression(_expression_), ctx);
		case SEQ:
			return createPositionedNode(new ASeqExpression(_expression_), ctx);
		case SEQ1:
			return createPositionedNode(new ASeq1Expression(_expression_), ctx);
		case SIZE:
			return createPositionedNode(new ASizeExpression(_expression_), ctx);
		case SIZET:
			return createPositionedNode(new ASizetExpression(_expression_), ctx);
		case SONS:
			return createPositionedNode(new ASonsExpression(_expression_), ctx);
		case TAIL:
			return createPositionedNode(new ATailExpression(_expression_), ctx);
		case TOP:
			return createPositionedNode(new ATopExpression(_expression_), ctx);
		case TREE:
			return createPositionedNode(new ATreeExpression(_expression_), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitExpression_keyword(Expression_keywordContext ctx) {
		final int type = ctx.operator.getType();
		switch (type) {
		case NATURAL:
			return createPositionedNode(new ANaturalSetExpression(), ctx);
		case NATURAL1:
			return createPositionedNode(new ANatural1SetExpression(), ctx);
		case NAT:
			return createPositionedNode(new ANatSetExpression(), ctx);
		case NAT1:
			return createPositionedNode(new ANat1SetExpression(), ctx);
		case INTEGER:
			return createPositionedNode(new AIntegerSetExpression(), ctx);
		case INT:
			return createPositionedNode(new AIntSetExpression(), ctx);
		case BOOL:
			return createPositionedNode(new ABoolSetExpression(), ctx);
		case PRED:
			return createPositionedNode(new APredecessorExpression(), ctx);
		case SUCC:
			return createPositionedNode(new ASuccessorExpression(), ctx);

		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitExpressionBinOperatorP125(ExpressionBinOperatorP125Context ctx) {
		PExpression left = (PExpression) ctx.left.accept(this);
		PExpression right = (PExpression) ctx.right.accept(this);
		final int type = ctx.expression_bin_operator_p125().operator.getType();
		switch (type) {
		case SET_RELATION:
			return createPositionedNode(new ARelationsExpression(left, right), ctx);
		case PARTIAL_FUNCTION:
			return createPositionedNode(new APartialFunctionExpression(left, right), ctx);
		case TOTAL_FUNCTION:
			return createPositionedNode(new ATotalFunctionExpression(left, right), ctx);
		case TOTAL_INJECTION:
			return createPositionedNode(new ATotalInjectionExpression(left, right), ctx);
		case PARTIAL_INJECTION:
			return createPositionedNode(new APartialInjectionExpression(left, right), ctx);
		case TOTAL_SURJECTION:
			return createPositionedNode(new ATotalSurjectionExpression(left, right), ctx);
		case PARTIAL_SURJECTION:
			return createPositionedNode(new APartialSurjectionExpression(left, right), ctx);
		case TOTAL_BIJECTION:
			return createPositionedNode(new ATotalBijectionExpression(left, right), ctx);
		case PARTIAL_BIJECTION:
			return createPositionedNode(new APartialBijectionExpression(left, right), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitReverseExpression(BParser.ReverseExpressionContext ctx) {
		return createPositionedNode(new AReverseExpression((PExpression) ctx.expression().accept(this)), ctx);
	}

	@Override
	public Node visitQuantifiedPredicate(BParser.QuantifiedPredicateContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(
				ctx.quantified_variables_list().identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		final int type = ctx.operator.getType();
		switch (type) {
		case FOR_ANY:
			return createPositionedNode(new AForallPredicate(identifierList, predicate), ctx);
		case EXITS:
			return createPositionedNode(new AExistsPredicate(identifierList, predicate), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitLambdaExpression(BParser.LambdaExpressionContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(
				ctx.quantified_variables_list().identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		PExpression expression = (PExpression) ctx.expression_in_par().accept(this);
		return createPositionedNode(new ALambdaExpression(identifierList, predicate, expression), ctx);
	}

	@Override
	public Node visitQuantifiedExpression(BParser.QuantifiedExpressionContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(ctx.identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		PExpression expression = (PExpression) ctx.expression_in_par().accept(this);
		final int type = ctx.operator.getType();
		switch (type) {
		case GENERALIZED_UNION:
			return createPositionedNode(new AQuantifiedUnionExpression(identifierList, predicate, expression), ctx);
		case GENERALIZED_INTER:
			return createPositionedNode(new AQuantifiedIntersectionExpression(identifierList, predicate, expression),
					ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitExpressionFunctionCall(ExpressionFunctionCallContext ctx) {
		final ExpressionContext function = ctx.function;
		final List<Expression_in_parContext> arguments = ctx.arguments;
		final List<PExpression> argumentList = new ArrayList<>();
		for (Expression_in_parContext argument : arguments) {
			argumentList.add((PExpression) argument.accept(this));
		}

		if (function instanceof ExpressionIdentifierContext) {
			ExpressionIdentifierContext identifierContext = (ExpressionIdentifierContext) function;
			ComposedIdentifierContext subNode = (ComposedIdentifierContext) identifierContext.composed_identifier();
			List<TerminalNode> identifiers = subNode.Identifier();
			final String firstName = identifiers.get(0).getText();
			if (identifiers.size() == 1 && definitionsAnalyser.isDefinition(firstName)) {
				TerminalNode id = identifiers.get(0);
				DefinitionType type = definitionsAnalyser.getDefinitionType(firstName);
				switch (type) {
				case EXPRESSION_DEFINITION:
					TIdentifierLiteral identLiteral = new TIdentifierLiteral(id.getText(), id.getSymbol().getLine(),
							id.getSymbol().getCharPositionInLine());
					return createPositionedNode(new ADefinitionExpression(identLiteral, argumentList), ctx);
				case PREDICATE_DEFINITION:
					TDefLiteralPredicate predLiteral = new TDefLiteralPredicate(id.getText(), id.getSymbol().getLine(),
							id.getSymbol().getCharPositionInLine());
					return createPositionedNode(new ADefinitionPredicate(predLiteral, argumentList), ctx);
				case SUBSTITUTION_DEFINITION:
					TDefLiteralSubstitution subLiteral = new TDefLiteralSubstitution(id.getText(),
							id.getSymbol().getLine(), id.getSymbol().getCharPositionInLine());
					return createPositionedNode(new ADefinitionSubstitution(subLiteral, argumentList), ctx);
				default:
					throw new RuntimeException("unexpected");
				}
			}
		} else {
			PExpression functionNode = (PExpression) function.accept(this);
			return createPositionedNode(new AFunctionExpression(functionNode, argumentList), ctx);
		}
		return visitChildren(ctx);
	}

	@Override
	public Node visitComposedIdentifier(ComposedIdentifierContext ctx) {
		throw new RuntimeException();
	}

	@Override
	public Node visitBinOperatorP160(BinOperatorP160Context ctx) {
		ExpressionOperatorP160Context expressionOperatorP160 = ctx.expressionOperatorP160();
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		switch (expressionOperatorP160.operator.getType()) {
		case OVERWRITE_RELATION:
			return createPositionedNode(new AOverwriteExpression(left, right), ctx);
		case DIRECT_PRODUCT:
			return createPositionedNode(new ADirectProductExpression(left, right), ctx);
		case CONCAT:
			return createPositionedNode(new AConcatExpression(left, right), ctx);
		case DOMAIN_RESTRICTION:
			return createPositionedNode(new ADomainRestrictionExpression(left, right), ctx);
		case DOMAIN_SUBSTRACTION:
			return createPositionedNode(new ADomainSubtractionExpression(left, right), ctx);
		case RANGE_RESTRICTION:
			return createPositionedNode(new ARangeRestrictionExpression(left, right), ctx);
		case RANGE_SUBSTRATION:
			return createPositionedNode(new ARangeSubtractionExpression(left, right), ctx);
		case INSERT_FRONT:
			return createPositionedNode(new AInsertFrontExpression(left, right), ctx);
		case INSERT_TAIL:
			return createPositionedNode(new AInsertTailExpression(left, right), ctx);
		case UNION:
			return createPositionedNode(new AUnionExpression(left, right), ctx);
		case INTERSECTION:
			return createPositionedNode(new AIntersectionExpression(left, right), ctx);
		case RESTRICT_FRONT:
			return createPositionedNode(new ARestrictFrontExpression(left, right), ctx);
		case RESTRICT_TAIL:
			return createPositionedNode(new ARestrictTailExpression(left, right), ctx);
		case MAPLET:
			return createPositionedNode(new ACoupleExpression(createPExpressionList(left, right)), ctx);
		default:
			throw new RuntimeException("unsupported operator");
		}
	}

	@Override
	public Node visitImageExpression(BParser.ImageExpressionContext ctx) {
		return createPositionedNode(
				new AImageExpression((PExpression) ctx.left.accept(this), (PExpression) ctx.right.accept(this)), ctx);
	}

	@Override
	public Node visitEmptySet(BParser.EmptySetContext ctx) {
		return createPositionedNode(new AEmptySetExpression(), ctx);
	}

	@Override
	public Node visitEmptySequence(BParser.EmptySequenceContext ctx) {
		return createPositionedNode(new AEmptySequenceExpression(), ctx);
	}

	@Override
	public Node visitBinOperator(BinOperatorContext ctx) {
		final String symbolicName = VOCABULARY.getSymbolicName(ctx.operator.getType());
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		switch (ctx.operator.getType()) {
		case MULT:
			return createPositionedNode(new AMultOrCartExpression(left, right), ctx);
		case DIVIDE:
			return createPositionedNode(new ADivExpression(left, right), ctx);
		case PLUS:
			return createPositionedNode(new AAddExpression(left, right), ctx);
		case MINUS:
			return createPositionedNode(new AMinusOrSetSubtractExpression(left, right), ctx);
		case INTERVAL:
			return createPositionedNode(new AIntervalExpression(left, right), ctx);
		case POWER_OF:
			return createPositionedNode(new APowerOfExpression(left, right), ctx);
		default:
			throw new RuntimeException("unsupported operator:" + symbolicName);
		}
	}

	@Override
	public Node visitPrimedIdentifierExpression(BParser.PrimedIdentifierExpressionContext ctx) {
		TIntegerLiteral grade = new TIntegerLiteral("0", ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
		return createPositionedNode(new APrimedIdentifierExpression(
				createTIdentifierListFromComposedIdentifierContext(ctx.composed_identifier()), grade), ctx);
	}

	private List<TIdentifierLiteral> createTIdentifierListFromComposedIdentifierContext(
			Composed_identifierContext ctx) {
		final ComposedIdentifierContext c = (ComposedIdentifierContext) ctx;
		final List<TIdentifierLiteral> list = new ArrayList<>();
		final List<TerminalNode> identifiers = c.Identifier();
		for (TerminalNode terminalNode : identifiers) {
			final TIdentifierLiteral tIdentifierLiteral = new TIdentifierLiteral(terminalNode.getText());
			tIdentifierLiteral.setLine(terminalNode.getSymbol().getLine());
			tIdentifierLiteral.setPos(terminalNode.getSymbol().getCharPositionInLine());
			list.add(tIdentifierLiteral);
		}
		return list;
	}

	@Override
	public Node visitCompositionOrParallelProduct(CompositionOrParallelProductContext ctx) {
		final String symbolicName = VOCABULARY.getSymbolicName(ctx.operator.getType());
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		switch (ctx.operator.getType()) {
		case SEMICOLON:
			return createPositionedNode(new ACompositionExpression(left, right), ctx);
		case DOUBLE_VERTICAL_BAR:
			return createPositionedNode(new AParallelProductExpression(left, right), ctx);
		default:
			throw new RuntimeException("unsupported operator:" + symbolicName);
		}
	}

	@Override
	public Node visitSetEnumeration(BParser.SetEnumerationContext ctx) {
		return createPositionedNode(new ASetExtensionExpression(createPExpressionList(ctx.expression_list())), ctx);
	}

	@Override
	public Node visitTuple(TupleContext ctx) {
		List<Expression_in_parContext> expressionContexts = ctx.expression_in_par();
		final List<PExpression> list = new ArrayList<>();
		for (Expression_in_parContext expressionContext : expressionContexts) {
			PExpression pExpression = (PExpression) expressionContext.accept(this);
			list.add(pExpression);
		}
		return createPositionedNode(new ACoupleExpression(list), ctx);
	}

	private List<PExpression> createPExpressionList(PExpression... pExpressions) {
		final List<PExpression> list = new ArrayList<>();
		for (PExpression pExpression : pExpressions) {
			list.add(pExpression);
		}
		return list;
	}

	@Override
	public Node visitNumber(NumberContext ctx) {
		String number = ctx.Number().getText();
		return new AIntegerExpression(
				new TIntegerLiteral(number, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine()));
	}

}
