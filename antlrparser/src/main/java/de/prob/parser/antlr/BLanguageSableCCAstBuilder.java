package de.prob.parser.antlr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.prob.parser.antlr.StaticSableCCAstBuilder.*;
import static files.BParser.*;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.be4.classicalb.core.parser.node.*;
import de.hhu.stups.sablecc.patch.SourcePosition;
import de.prob.parser.antlr.IDefinitions.*;
import files.BParserBaseVisitor;

public class BLanguageSableCCAstBuilder extends BParserBaseVisitor<Node> {
	private final DefinitionsAnalyser definitionsAnalyser;

	public BLanguageSableCCAstBuilder(DefinitionsAnalyser definitionsAnalyser) {
		this.definitionsAnalyser = definitionsAnalyser;
	}

	@Override
	public Node visitParseUnit(ParseUnitContext ctx) {
		PParseUnit parseUnit = (PParseUnit) ctx.parse_unit().accept(this);
		return new Start(parseUnit, new EOF());
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
	public Node visitDefinitionParseUnit(DefinitionParseUnitContext ctx) {
		PMachineClause definitionsClauses = (PMachineClause) ctx.definition_clause().accept(this);
		return createPositionedNode(new ADefinitionFileParseUnit(definitionsClauses), ctx);
	}

	@Override
	public Node visitMachineClauseParseUnit(MachineClauseParseUnitContext ctx) {
		PMachineClause machineClause = (PMachineClause) ctx.machine_clause().accept(this);
		return createPositionedNode(new AMachineClauseParseUnit(machineClause), ctx);
	}

	@Override
	public Node visitOpPatternParseUnit(OpPatternParseUnitContext ctx) {
		return ctx.operation_pattern().accept(this);
	}

	@Override
	public Node visitOpPattern(OpPatternContext ctx) {
		List<TIdentifierLiteral> list = createTIdentifierListFromComposedIdentifierContext(ctx.composed_identifier());
		List<PArgpattern> parameters = new ArrayList<>();
		for (Op_pattern_paramContext pArgpattern : ctx.parameter_list) {
			PArgpattern param = (PArgpattern) pArgpattern.accept(this);
			parameters.add(param);
		}
		return createPositionedNode(new AOppatternParseUnit(list, parameters), ctx);
	}

	@Override
	public Node visitOpPatternExpression(OpPatternExpressionContext ctx) {
		PExpression expression = (PExpression) ctx.expression_in_par().accept(this);
		return createPositionedNode(new ADefArgpattern(expression), ctx);
	}

	@Override
	public Node visitOpPatternUnderscore(OpPatternUnderscoreContext ctx) {
		return createPositionedNode(new AUndefArgpattern(), ctx);
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
		final List<Machine_clauseContext> clauses = ctx.clauses;
		for (Machine_clauseContext clause : clauses) {
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
	public Node visitImplementation(ImplementationContext ctx) {
		final AMachineHeader header = (AMachineHeader) ctx.machine_header().accept(this);
		final List<PMachineClause> list = new ArrayList<>();
		final List<Machine_clauseContext> clauses = ctx.clauses;
		for (Machine_clauseContext clause : clauses) {
			final PMachineClause clauseNode = (PMachineClause) clause.accept(this);
			list.add(clauseNode);
		}
		final TIdentifierLiteral identifierLiteral = new TIdentifierLiteral(ctx.identifier().getText(),
				ctx.identifier().getStart().getLine(), ctx.identifier().getStart().getCharPositionInLine());
		return createPositionedNode(new AImplementationMachineParseUnit(header, identifierLiteral, list), ctx);
	}

	@Override
	public Node visitRefinement(RefinementContext ctx) {
		final AMachineHeader header = (AMachineHeader) ctx.machine_header().accept(this);
		final List<PMachineClause> list = new ArrayList<>();
		final List<Machine_clauseContext> clauses = ctx.clauses;
		for (Machine_clauseContext clause : clauses) {
			final PMachineClause clauseNode = (PMachineClause) clause.accept(this);
			list.add(clauseNode);
		}
		final TIdentifierLiteral identifierLiteral = new TIdentifierLiteral(ctx.identifier().getText(),
				ctx.identifier().getStart().getLine(), ctx.identifier().getStart().getCharPositionInLine());
		return createPositionedNode(new ARefinementMachineParseUnit(header, identifierLiteral, list), ctx);
	}

	@Override
	public Node visitValuesClause(ValuesClauseContext ctx) {
		final List<PValuesEntry> entries = new ArrayList<>();
		for (int i = 0; i < ctx.idents.size(); i++) {
			Token token = ctx.idents.get(i);
			ExpressionContext expressionContext = ctx.exprs.get(i);
			PExpression valueExpr = (PExpression) expressionContext.accept(this);
			List<TIdentifierLiteral> identifierList = new ArrayList<>();
			TIdentifierLiteral tIdentifierLiteral = new TIdentifierLiteral(token.getText(), token.getLine(),
					token.getCharPositionInLine());
			identifierList.add(tIdentifierLiteral);
			AValuesEntry valueEntry = createPositionedNode(new AValuesEntry(identifierList, valueExpr), ctx);
			entries.add(valueEntry);
		}
		return createPositionedNode(new AValuesMachineClause(entries), ctx);
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
	public Node visitOrdinaryDefinition(OrdinaryDefinitionContext ctx) {
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
		Node node;
		if (type == DefinitionType.SUBSTITUTION_DEFINITION) {
			TDefLiteralSubstitution tDefLiteral = createPositionedToken(new TDefLiteralSubstitution(definitionName),
					ctx.name);
			node = new ASubstitutionDefinitionDefinition(tDefLiteral, parameterList, (PSubstitution) rhs);
		} else if (type == DefinitionType.PREDICATE_DEFINITION) {
			TDefLiteralPredicate tDefLiteral = createPositionedToken(new TDefLiteralPredicate(definitionName),
					ctx.name);
			node = new APredicateDefinitionDefinition(tDefLiteral, parameterList, (PPredicate) rhs);
		} else {
			TIdentifierLiteral tDefLiteral = createPositionedToken(new TIdentifierLiteral(definitionName), ctx.name);
			node = new AExpressionDefinitionDefinition(tDefLiteral, parameterList, (PExpression) rhs);
		}
		return createPositionedNode(node, ctx);
	}

	private Node createDefinitionCall(TerminalNode terminalNode, List<PExpression> argumentList,
			FormulaAmbiguousCallContext ctx) {

		String name = terminalNode.getText();
		DefinitionType type = definitionsAnalyser.getDefinitionType(name);
		int line = terminalNode.getSymbol().getLine();
		int pos = terminalNode.getSymbol().getCharPositionInLine();

		switch (type) {
		case EXPRESSION_DEFINITION:
			TIdentifierLiteral identLiteral = new TIdentifierLiteral(name, line, pos);
			return createPositionedNode(new ADefinitionExpression(identLiteral, argumentList), ctx);
		case PREDICATE_DEFINITION:
			TDefLiteralPredicate predLiteral = new TDefLiteralPredicate(name, line, pos);
			return createPositionedNode(new ADefinitionPredicate(predLiteral, argumentList), ctx);
		case SUBSTITUTION_DEFINITION:
			TDefLiteralSubstitution subLiteral = new TDefLiteralSubstitution(name, line, pos);
			return createPositionedNode(new ADefinitionSubstitution(subLiteral, argumentList), ctx);
		default:
			throw new RuntimeException("unexpected: " + type);
		}
	}

	@Override
	public Node visitFormulaAmbiguousCall(FormulaAmbiguousCallContext ctx) {
		List<PExpression> argumentList = null;
		if (ctx.expression_list() != null) {
			argumentList = createPExpressionList(ctx.expression_list());
		} else {
			argumentList = new ArrayList<>();
		}

		ComposedIdentifierContext composed_identifier = (ComposedIdentifierContext) ctx.composed_identifier();
		List<TerminalNode> tokens = composed_identifier.IDENTIFIER();
		String name = tokens.get(tokens.size() - 1).getText();
		if (tokens.size() == 1 && definitionsAnalyser.isDefinition(name)) {
			return createDefinitionCall(tokens.get(0), argumentList, ctx);
		} else {
			final List<TIdentifierLiteral> list = new ArrayList<>();
			for (TerminalNode terminalNode : tokens) {
				final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(terminalNode.getText(),
						terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine());
				list.add(tIdentifier);
			}
			AIdentifierExpression idExpr = createPositionedNode(new AIdentifierExpression(list), composed_identifier);
			if (currentDefinitionType == DefinitionType.SUBSTITUTION_DEFINITION) {
				return createPositionedNode(new AOpSubstitution(idExpr, argumentList), ctx);
			} else if (currentDefinitionType == DefinitionType.EXPRESSION_DEFINITION) {
				if (argumentList.size() == 0) {
					return idExpr;
				} else {
					return createPositionedNode(new AFunctionExpression(idExpr, argumentList), ctx);
				}
			} else {
				// if the type is unknown, we assume it is an expression
				if (argumentList.size() == 0) {
					return idExpr;
				} else {
					return createPositionedNode(new AFunctionExpression(idExpr, argumentList), ctx);
				}
			}
		}
	}

	@Override
	public Node visitDefinitionFile(DefinitionFileContext ctx) {
		String string = ctx.StringLiteral().getText();
		string = string.substring(1, string.length() - 1);
		final TStringLiteral stringLiteral = new TStringLiteral(string, ctx.getStart().getLine(),
				ctx.getStart().getCharPositionInLine());
		return createPositionedNode(new AFileDefinitionDefinition(stringLiteral), ctx);
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
	public PMachineClause visitAssertionClause(AssertionClauseContext ctx) {
		List<PPredicate> list = new ArrayList<>();
		for (PredicateContext predicateContext : ctx.preds) {
			PPredicate pred = (PPredicate) predicateContext.accept(this);
			list.add(pred);
		}
		return createPositionedNode(new AAssertionsMachineClause(list), ctx);
	}

	@Override
	public Node visitReferenceClause(ReferenceClauseContext ctx) {
		List<PExpression> list = new ArrayList<>();
		List<Composed_identifierContext> composedIdents = ctx.composed_identifier_list().idents;
		for (Composed_identifierContext composed_ident : composedIdents) {
			ComposedIdentifierContext c = (ComposedIdentifierContext) composed_ident;
			List<TerminalNode> identifier = c.IDENTIFIER();
			List<TIdentifierLiteral> tidents = new ArrayList<>();
			for (TerminalNode terminalNode : identifier) {

				tidents.add(new TIdentifierLiteral(terminalNode.getText()));
			}
			list.add(createPositionedNode(new AIdentifierExpression(tidents), composed_ident));
		}
		switch (ctx.name.getType()) {
		case SEES:
			return createPositionedNode(new ASeesMachineClause(list), ctx);
		case USES:
			return createPositionedNode(new AUsesMachineClause(list), ctx);
		case PROMOTES:
			return createPositionedNode(new APromotesMachineClause(list), ctx);
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
		List<TerminalNode> identifiers = subNode.IDENTIFIER();
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
	public Node visitSetsClause(SetsClauseContext ctx) {
		final List<Set_definitionContext> set_definitions = ctx.set_definition();
		final List<PSet> list = new ArrayList<>();
		for (Set_definitionContext setContext : set_definitions) {
			list.add((PSet) setContext.accept(this));
		}
		return createPositionedNode(new ASetsMachineClause(list), ctx);
	}

	@Override
	public Node visitDeferredSet(DeferredSetContext ctx) {
		TerminalNode identifier = ctx.IDENTIFIER();
		List<TIdentifierLiteral> list = new ArrayList<>();
		TIdentifierLiteral ident = new TIdentifierLiteral(identifier.getText(), identifier.getSymbol().getLine(),
				identifier.getSymbol().getCharPositionInLine());
		list.add(ident);
		return createPositionedNode(new ADeferredSetSet(list), ctx);
	}

	@Override
	public Node visitEnumeratedSet(EnumeratedSetContext ctx) {
		final TerminalNode identifier = ctx.IDENTIFIER();
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
	public Node visitBOperation(BOperationContext ctx) {
		final List<PExpression> returnValuesList = createPExpressionListFromIdentifierList(ctx.output);
		final List<PExpression> parameterList = createPExpressionListFromIdentifierList(ctx.parameters);
		final PSubstitution body = (PSubstitution) ctx.substitution().accept(this);
		final List<TIdentifierLiteral> opName = createTIdentifierLiteralList(ctx.IDENTIFIER());
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

	/*
	 * Substitutions
	 */
	@Override
	public Node visitSubstitutionBlock(SubstitutionBlockContext ctx) {
		final PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		return createPositionedNode(new ABlockSubstitution(substitution), ctx);
	}

	@Override
	public Node visitSubstitutionSkip(SubstitutionSkipContext ctx) {
		return createPositionedNode(new ASkipSubstitution(), ctx);
	}

	@Override
	public Node visitChoiceSubstitution(ChoiceSubstitutionContext ctx) {
		final List<SubstitutionContext> substitutions = ctx.substitution();
		final List<PSubstitution> list = new ArrayList<>();

		for (SubstitutionContext substitutionContext : substitutions) {
			PSubstitution sub = (PSubstitution) substitutionContext.accept(this);
			list.add(createPositionedNode(new AChoiceOrSubstitution(sub), substitutionContext));
		}
		return createPositionedNode(new AChoiceSubstitution(list), ctx);
	}

	@Override
	public Node visitConditionSubstitution(ConditionSubstitutionContext ctx) {
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
	public Node visitIfSubstitution(IfSubstitutionContext ctx) {
		PPredicate condition = (PPredicate) ctx.pred.accept(this);
		PSubstitution then = (PSubstitution) ctx.thenSub.accept(this);

		final List<PSubstitution> elseIfSubs = new ArrayList<>();
		List<PredicateContext> elsifPred = ctx.elsifPred;
		for (int i = 0; i < elsifPred.size(); i++) {
			PredicateContext predicateContext = elsifPred.get(i);
			SubstitutionContext substitutionContext = ctx.elsifSub.get(i);
			PPredicate pred = (PPredicate) predicateContext.accept(this);
			PSubstitution sub = (PSubstitution) substitutionContext.accept(this);
			AIfElsifSubstitution elseIf = createPositionedNode(new AIfElsifSubstitution(pred, sub),
					predicateContext.getStart(), substitutionContext.getStop());
			elseIfSubs.add(elseIf);
		}

		PSubstitution else_ = null;
		if (ctx.elseSub != null) {
			else_ = (PSubstitution) ctx.elseSub.accept(this);
		}
		return createPositionedNode(new AIfSubstitution(condition, then, elseIfSubs, else_), ctx);
	}

	@Override
	public Node visitSelectSubstitution(SelectSubstitutionContext ctx) {
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
			aSelectWhenSubstitution.setEndPos(new SourcePosition(sub.getEndPos().getLine(), pred.getEndPos().getPos()));
			whenSub.add(aSelectWhenSubstitution);
		}
		return createPositionedNode(new ASelectSubstitution(predicate, then, whenSub, elseSub), ctx);
	}

	@Override
	public Node visitCaseSubstitution(CaseSubstitutionContext ctx) {
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
	public Node visitAnySubstitution(AnySubstitutionContext ctx) {
		return createPositionedNode(
				new AAnySubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this), (PSubstitution) ctx.substitution().accept(this)),
				ctx);
	}

	@Override
	public Node visitLetSubstitution(LetSubstitutionContext ctx) {
		return createPositionedNode(
				new ALetSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this), (PSubstitution) ctx.substitution().accept(this)),
				ctx);
	}

	@Override
	public Node visitBecomesElementOfSubstitution(BecomesElementOfSubstitutionContext ctx) {
		return createPositionedNode(
				new ABecomesElementOfSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PExpression) ctx.expression().accept(this)),
				ctx);
	}

	@Override
	public Node visitBecomesSuchThatSubstitution(BecomesSuchThatSubstitutionContext ctx) {
		return createPositionedNode(
				new ABecomesSuchSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
						(PPredicate) ctx.predicate().accept(this)),
				ctx);
	}

	@Override
	public Node visitVarSubstitution(VarSubstitutionContext ctx) {
		return createPositionedNode(new AVarSubstitution(createPExpressionListFromIdentifierList(ctx.identifier_list()),
				(PSubstitution) ctx.substitution().accept(this)), ctx);
	}

	@Override
	public Node visitWhileSubstitution(WhileSubstitutionContext ctx) {
		final PPredicate condition = (PPredicate) ctx.condition.accept(this);
		final PSubstitution substitution = (PSubstitution) ctx.substitution().accept(this);
		final PPredicate invariant = (PPredicate) ctx.invariant.accept(this);
		final PExpression variant = (PExpression) ctx.variant.accept(this);
		return createPositionedNode(new AWhileSubstitution(condition, substitution, invariant, variant), ctx);
	}

	@Override
	public Node visitSubstitutionList(SubstitutionListContext ctx) {
		List<Token> operators = ctx.operators;
		List<PSubstitution> subList = new ArrayList<>();
		int type = operators.get(0).getType();
		PSubstitution first = (PSubstitution) ctx.subs.get(0).accept(this);
		subList.add(first);
		for (int i = 1; i < ctx.subs.size(); i++) {
			PSubstitution sub = (PSubstitution) ctx.subs.get(i).accept(this);
			subList.add(sub);
			if (i < operators.size() && operators.get(i).getType() != type) {
				SourcePosition start = new SourcePosition(subList.get(0).getStartPos().getLine(),
						subList.get(0).getStartPos().getPos());
				SourcePosition end = new SourcePosition(subList.get(subList.size() - 1).getStartPos().getLine(),
						subList.get(subList.size() - 1).getStartPos().getPos());
				if (type == SEMICOLON) {
					ASequenceSubstitution seqSub = new ASequenceSubstitution(subList);
					seqSub.setStartPos(start);
					seqSub.setEndPos(end);
					subList = new ArrayList<>();
					subList.add(seqSub);
				} else {
					AParallelSubstitution paSub = new AParallelSubstitution(subList);
					paSub.setStartPos(start);
					paSub.setEndPos(end);
					subList = new ArrayList<>();
					subList.add(paSub);
				}
				type = operators.get(i).getType();
			}
		}
		SourcePosition start = new SourcePosition(subList.get(0).getStartPos().getLine(),
				subList.get(0).getStartPos().getPos());
		SourcePosition end = new SourcePosition(subList.get(subList.size() - 1).getStartPos().getLine(),
				subList.get(subList.size() - 1).getStartPos().getPos());
		if (type == SEMICOLON) {
			ASequenceSubstitution seqSub = new ASequenceSubstitution(subList);
			seqSub.setStartPos(start);
			seqSub.setEndPos(end);
			return seqSub;
		} else {
			AParallelSubstitution paSub = new AParallelSubstitution(subList);
			paSub.setStartPos(start);
			paSub.setEndPos(end);
			return paSub;
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

		ARecordFieldExpression rec = createPositionedNode(new ARecordFieldExpression(base, fieldNode), ctx.start,
				fieldToken);
		for (int i = 1; i < attributes.size(); i++) {
			fieldToken = attributes.get(i);
			fieldNode = createAIdentifierExpression(fieldToken);
			rec = new ARecordFieldExpression(rec, fieldNode);
			createPositionedNode(new ARecordFieldExpression(rec, fieldNode), ctx.start, fieldToken);
		}
		return rec;
	}

	@Override
	public Node visitAssignFunctionIdentifier(AssignFunctionIdentifierContext ctx) {
		final List<Expression_listContext> argument_lists = ctx.argument_lists;
		PExpression func = createAIdentifierExpression(ctx.name);
		for (Expression_listContext expression_listContext : argument_lists) {
			List<PExpression> expressionList = createPExpressionList(expression_listContext);
			func = createPositionedNode(new AFunctionExpression(func, expressionList), ctx);
		}
		return func;
	}

	@Override
	public Node visitBoolCastExpression(BoolCastExpressionContext ctx) {
		final PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		return createPositionedNode(new AConvertBoolExpression(predicate), ctx);
	}

	private List<PExpression> createPExpressionList(Expression_listContext expression_list) {
		final List<ExpressionContext> expressionContexts = expression_list.exprs;
		final List<PExpression> expressionList = new ArrayList<>();
		for (ExpressionContext expressionContext : expressionContexts) {
			final PExpression expression = (PExpression) expressionContext.accept(this);
			expressionList.add(expression);
		}
		return expressionList;
	}

	@Override
	public Node visitAssignSingleIdentifier(AssignSingleIdentifierContext ctx) {
		final TerminalNode identifier = ctx.IDENTIFIER();
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
				temp = createPositionedNode(new AConjunctPredicate(temp, right), ctx.start, rightContext.stop);
			} else {
				temp = createPositionedNode(new ADisjunctPredicate(temp, right), ctx.start, rightContext.stop);
			}
		}
		return temp;
	}

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
	public PPredicate visitPredicateBinPredicateOperator(PredicateBinPredicateOperatorContext ctx) {
		final PPredicate left = (PPredicate) ctx.left.accept(this);
		final PPredicate right = (PPredicate) ctx.right.accept(this);
		switch (ctx.operator.getType()) {
		case EQUIVALENCE:
			return createPositionedNode(new AEquivalencePredicate(left, right), ctx);
		default:
			throw new RuntimeException("unexpected exception: " + ctx.operator.getText());
		}
	}

	@Override
	public Node visitPredicateBinExpression(PredicateBinExpressionContext ctx) {
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		switch (ctx.predicate_expression_operator().operator.getType()) {
		case EQUAL:
			return createPositionedNode(new AEqualPredicate(left, right), ctx);
		case ELEMENT_OF:
		case COLON:
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
			throw new RuntimeException("unexpected exception: " + ctx.predicate_expression_operator().getText());
		}
	}

	@Override
	public Node visitSubstitutionIdentifierCall(SubstitutionIdentifierCallContext ctx) {
		final ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		List<PExpression> argumentList = null;
		if (ctx.expression_list() != null) {
			argumentList = createPExpressionList(ctx.expression_list());
		} else {
			argumentList = new ArrayList<>();
		}
		final List<TerminalNode> identifiers = subNode.IDENTIFIER();
		final TerminalNode id = identifiers.get(0);
		if (identifiers.size() == 1 && definitionsAnalyser.isDefinition(id.getText())) {
			TDefLiteralSubstitution predLiteral = new TDefLiteralSubstitution(id.getText(), id.getSymbol().getLine(),
					id.getSymbol().getCharPositionInLine());
			return createPositionedNode(new ADefinitionSubstitution(predLiteral, argumentList), ctx);
		} else {
			final List<TIdentifierLiteral> list = new ArrayList<>();
			for (TerminalNode terminalNode : identifiers) {
				final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(terminalNode.getText(),
						terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine());
				list.add(tIdentifier);
			}
			AIdentifierExpression idExpr = createPositionedNode(new AIdentifierExpression(list), subNode);
			final AOpSubstitution opSubst = new AOpSubstitution(idExpr, argumentList);
			return createPositionedNode(opSubst, ctx);
		}

	}

	@Override
	public Node visitSubstitutionOperationCall(SubstitutionOperationCallContext ctx) {
		final ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		List<PExpression> argumentList = null;
		if (ctx.expression_list() != null) {
			argumentList = createPExpressionList(ctx.expression_list());
		} else {
			argumentList = new ArrayList<>();
		}
		final List<TerminalNode> identifiers = subNode.IDENTIFIER();
		final List<PExpression> output = createPExpressionListFromIdentifierList(ctx.output);
		final List<TIdentifierLiteral> list = new ArrayList<>();
		for (TerminalNode terminalNode : identifiers) {
			final TIdentifierLiteral tIdentifier = new TIdentifierLiteral(terminalNode.getText(),
					terminalNode.getSymbol().getLine(), terminalNode.getSymbol().getCharPositionInLine());
			list.add(tIdentifier);
		}
		final AOperationCallSubstitution opSubst = new AOperationCallSubstitution(output, list, argumentList);
		return createPositionedNode(opSubst, ctx);
	}

	@Override
	public Node visitPredicateIdentifierCall(PredicateIdentifierCallContext ctx) {
		ComposedIdentifierContext subNode = (ComposedIdentifierContext) ctx.composed_identifier();
		List<TerminalNode> identifiers = subNode.IDENTIFIER();
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
			return createPositionedNode(new ADefinitionPredicate(predLiteral, argumentList), ctx);

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
		List<TerminalNode> identifiers = subNode.IDENTIFIER();
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
				throw new RuntimeException("unexpected: " + type);
			}
		} else {
			List<TIdentifierLiteral> list = new ArrayList<>();
			for (TerminalNode id : identifiers) {
				list.add(new TIdentifierLiteral(id.getText(), id.getSymbol().getLine(),
						id.getSymbol().getCharPositionInLine() + 1));
			}
			AIdentifierExpression posNode = createPositionedNode(new AIdentifierExpression(list), ctx);
			return posNode;
		}
	}

	@Override
	public Node visitExpressionPrefixOperator(ExpressionPrefixOperatorContext ctx) {
		PExpression expr = (PExpression) ctx.expr.accept(this);
		final int type = ctx.expression_prefix_operator().operator.getType();
		return createExpressionPrefixOperator(VOCABULARY.getSymbolicName(type), expr, ctx);
	}

	@Override
	public Node visitExpressionPrefixOperator2Args(ExpressionPrefixOperator2ArgsContext ctx) {
		final PExpression _expression1_ = (PExpression) ctx.expr1.accept(this);
		final PExpression _expression2_ = (PExpression) ctx.expr2.accept(this);
		final int type = ctx.expression_prefix_operator_2_args().operator.getType();
		return createExpressionPrefixOperator2Args(VOCABULARY.getSymbolicName(type), _expression1_, _expression2_, ctx);
	}

	@Override
	public Node visitExpression_keyword(Expression_keywordContext ctx) {
		final int type = ctx.operator.getType();
		return createExpression_keyword(VOCABULARY.getSymbolicName(type), ctx);
	}

	@Override
	public Node visitExpressionBinOperatorP125(ExpressionBinOperatorP125Context ctx) {
		PExpression left = (PExpression) ctx.left.accept(this);
		PExpression right = (PExpression) ctx.right.accept(this);
		final int type = ctx.expression_bin_operator_p125().operator.getType();
		return createExpressionOperator2Args(VOCABULARY.getSymbolicName(type), left, right, ctx);
	}

	@Override
	public Node visitReverseExpression(ReverseExpressionContext ctx) {
		return createPositionedNode(new AReverseExpression((PExpression) ctx.expression().accept(this)), ctx);
	}

	@Override
	public Node visitQuantifiedPredicate(QuantifiedPredicateContext ctx) {
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
	public Node visitLambdaExpression(LambdaExpressionContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(
				ctx.quantified_variables_list().identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		PExpression expression = (PExpression) ctx.expression_in_par().accept(this);
		Node node;
		if (null == ctx.PRAGMA_SYMBOLIC()) {
			node = new ALambdaExpression(identifierList, predicate, expression);
		} else {
			node = new ASymbolicLambdaExpression(identifierList, predicate, expression);
		}
		return createPositionedNode(node, ctx);
	}

	@Override
	public Node visitQuantifiedExpression(QuantifiedExpressionContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(
				ctx.quantified_variables_list().identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		PExpression expression = (PExpression) ctx.expression_in_par().accept(this);
		final int type = ctx.operator.getType();
		switch (type) {
		case QUANTIFIED_UNION:
			return createPositionedNode(new AQuantifiedUnionExpression(identifierList, predicate, expression), ctx);
		case QUANTIFIED_INTER:
			return createPositionedNode(new AQuantifiedIntersectionExpression(identifierList, predicate, expression),
					ctx);
		case SIGMA:
			return createPositionedNode(new AGeneralSumExpression(identifierList, predicate, expression), ctx);
		case PI:
			return createPositionedNode(new AGeneralProductExpression(identifierList, predicate, expression), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitRecord(RecordContext ctx) {
		final int type = ctx.operator.getType();
		final List<PRecEntry> list = new ArrayList<>();
		for (Rec_entryContext rec_entryContext : ctx.entries) {
			PRecEntry entry = (PRecEntry) rec_entryContext.accept(this);
			list.add(entry);
		}
		switch (type) {
		case REC:
			return createPositionedNode(new ARecExpression(list), ctx);
		case STRUCT:
			return createPositionedNode(new AStructExpression(list), ctx);
		default:
			throw new RuntimeException("unexpected");
		}
	}

	@Override
	public Node visitRecordFieldAccess(RecordFieldAccessContext ctx) {
		final PExpression record = (PExpression) ctx.expression().accept(this);
		final PExpression field = (PExpression) ctx.identifier().accept(this);
		return createPositionedNode(new ARecordFieldExpression(record, field), ctx);
	}

	@Override
	public PRecEntry visitRec_entry(Rec_entryContext ctx) {
		IdentifierNodeContext identifier = (IdentifierNodeContext) ctx.identifier();
		TerminalNode terminalNode = identifier.IDENTIFIER();
		List<TIdentifierLiteral> list = createTIdentifierLiteralList(terminalNode);
		PExpression fieldExpr = createPositionedNode(new AIdentifierExpression(list), identifier);
		PExpression value = (PExpression) ctx.expression_in_par().accept(this);
		return createPositionedNode(new ARecEntry(fieldExpr, value), ctx);
	}

	@Override
	public Node visitQuantifiedSet(QuantifiedSetContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(
				ctx.quantified_variables_list().identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		return createPositionedNode(new AProverComprehensionSetExpression(identifierList, predicate), ctx);
	}

	@Override
	public Node visitSetComprehension(SetComprehensionContext ctx) {
		List<PExpression> identifierList = createPExpressionListFromIdentifierList(ctx.identifier_list());
		PPredicate predicate = (PPredicate) ctx.predicate().accept(this);
		return createPositionedNode(new AComprehensionSetExpression(identifierList, predicate), ctx);
	}

	@Override
	public Node visitPredicateKeyword(PredicateKeywordContext ctx) {
		switch (ctx.keyword.getType()) {
		case BTRUE:
			return createPositionedNode(new ATruthPredicate(), ctx);
		case BFALSE:
			return createPositionedNode(new AFalsityPredicate(), ctx);
		default:
			throw new RuntimeException("unsupported operator");
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
			List<TerminalNode> identifiers = subNode.IDENTIFIER();
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
		}
		PExpression functionNode = (PExpression) function.accept(this);
		return createPositionedNode(new AFunctionExpression(functionNode, argumentList), ctx);
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
		final int type = expressionOperatorP160.operator.getType();
		return createExpressionOperator2Args(VOCABULARY.getSymbolicName(type), left, right, ctx);
	}

	@Override
	public Node visitBinOperator(BinOperatorContext ctx) {
		final PExpression left = (PExpression) ctx.left.accept(this);
		final PExpression right = (PExpression) ctx.right.accept(this);
		final int type = ctx.operator.getType();
		return createExpressionOperator2Args(VOCABULARY.getSymbolicName(type), left, right, ctx);
	}

	@Override
	public Node visitImageExpression(ImageExpressionContext ctx) {
		return createPositionedNode(
				new AImageExpression((PExpression) ctx.left.accept(this), (PExpression) ctx.right.accept(this)), ctx);
	}

	@Override
	public Node visitEmptySet(EmptySetContext ctx) {
		return createPositionedNode(new AEmptySetExpression(), ctx);
	}

	@Override
	public Node visitEmptySequence(EmptySequenceContext ctx) {
		return createPositionedNode(new AEmptySequenceExpression(), ctx);
	}

	@Override
	public Node visitUnaryMinus(UnaryMinusContext ctx) {
		PExpression expression = (PExpression) ctx.expression().accept(this);
		return createPositionedNode(new AUnaryMinusExpression(expression), ctx);
	}

	@Override
	public Node visitLetExpression(LetExpressionContext ctx) {
		final List<PExpression> list = new ArrayList<>();
		for (Token token : ctx.identifier_list().idents) {
			list.add(createAIdentifierExpression(token));
		}
		final PPredicate assignment = (PPredicate) ctx.predicate().accept(this);
		final PExpression expr = (PExpression) ctx.expression_in_par().accept(this);
		return createPositionedNode(new ALetExpressionExpression(list, assignment, expr), ctx);
	}

	@Override
	public Node visitIfExpression(IfExpressionContext ctx) {
		final PPredicate condition = (PPredicate) ctx.predicate().accept(this);
		final PExpression then = (PExpression) ctx.expr1.accept(this);
		final PExpression elseExpr = (PExpression) ctx.expr2.accept(this);
		return createPositionedNode(new AIfThenElseExpression(condition, then, elseExpr), ctx);
	}

	@Override
	public Node visitPredicateIf(PredicateIfContext ctx) {
		final PPredicate condition = (PPredicate) ctx.conditionPred.accept(this);
		final PPredicate thenPred = (PPredicate) ctx.thenPred.accept(this);
		final PPredicate elsePred = (PPredicate) ctx.elsePred.accept(this);
		return createPositionedNode(new AIfPredicatePredicate(condition, thenPred, elsePred), ctx);
	}

	@Override
	public Node visitPredicateLet(PredicateLetContext ctx) {
		final List<PExpression> list = new ArrayList<>();
		for (Token token : ctx.identifier_list().idents) {
			list.add(createAIdentifierExpression(token));
		}
		final PPredicate assignment = (PPredicate) ctx.pred1.accept(this);
		final PPredicate expr = (PPredicate) ctx.pred2.accept(this);
		return createPositionedNode(new ALetPredicatePredicate(list, assignment, expr), ctx);
	}

	@Override
	public Node visitBooleanValue(BooleanValueContext ctx) {
		final String symbolicName = VOCABULARY.getSymbolicName(ctx.value.getType());
		switch (ctx.value.getType()) {
		case TRUE:
			return createPositionedNode(new ABooleanTrueExpression(), ctx);
		case FALSE:
			return createPositionedNode(new ABooleanFalseExpression(), ctx);
		default:
			throw new RuntimeException("unsupported operator:" + symbolicName);
		}
	}

	private static Map<Character, Character> stringReplacements = new HashMap<>();

	static {
		// replacements in strings '\' + ..
		// e.g. '\' + 'n' is replaced by '\n'
		stringReplacements.put('"', '"');
		stringReplacements.put('\'', '\'');
		stringReplacements.put('n', '\n');
		stringReplacements.put('r', '\r');
		stringReplacements.put('t', '\t');
		stringReplacements.put('\\', '\\');
	}

	@Override
	public Node visitString(StringContext ctx) {
		final String string = ctx.StringLiteral().getText();
		String literal = null;
		if (string.startsWith("\"")) {
			/// "foo"
			literal = string.substring(1, string.length() - 1);
		} else {
			literal = string.substring(3, string.length() - 3);
		}
		boolean backslashFound = false;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < literal.length(); i++) {
			char c = literal.charAt(i);
			if (backslashFound && stringReplacements.containsKey(c)) {
				buffer.setLength(buffer.length() - 1);
				buffer.append(stringReplacements.get(c));
				backslashFound = false;
				continue;
			}
			if (c == '\\') {
				backslashFound = true;
			}
			buffer.append(c);
		}

		final TStringLiteral stringLiteral = new TStringLiteral(buffer.toString(), ctx.getStart().getLine(),
				ctx.getStart().getCharPositionInLine());
		return createPositionedNode(new AStringExpression(stringLiteral), ctx);
	}

	@Override
	public Node visitPrimedIdentifierExpression(PrimedIdentifierExpressionContext ctx) {
		TIntegerLiteral grade = new TIntegerLiteral("0", ctx.stop.getLine(), ctx.stop.getCharPositionInLine());
		return createPositionedNode(new APrimedIdentifierExpression(
				createTIdentifierListFromComposedIdentifierContext(ctx.composed_identifier()), grade), ctx);
	}

	private List<TIdentifierLiteral> createTIdentifierListFromComposedIdentifierContext(
			Composed_identifierContext ctx) {
		final ComposedIdentifierContext c = (ComposedIdentifierContext) ctx;
		final List<TIdentifierLiteral> list = new ArrayList<>();
		final List<TerminalNode> identifiers = c.IDENTIFIER();
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
	public Node visitSetEnumeration(SetEnumerationContext ctx) {
		return createPositionedNode(new ASetExtensionExpression(createPExpressionList(ctx.expression_list())), ctx);
	}

	@Override
	public Node visitSequenceEnumeration(SequenceEnumerationContext ctx) {
		return createPositionedNode(new ASequenceExtensionExpression(createPExpressionList(ctx.expression_list())),
				ctx);
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

	@Override
	public Node visitNumber(NumberContext ctx) {
		TerminalNode number2 = ctx.Number();
		String number = ctx.Number().getText();
		return createPositionedNode(
				new AIntegerExpression(
						new TIntegerLiteral(number, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine())),
				ctx);
	}

	@Override
	public Node visitHexDigit(HexDigitContext ctx) {
		final String literal = ctx.HEX_LITERAL().getText().substring(2);
		int value = Integer.valueOf(literal, 16);
		TIntegerLiteral tIntegerLiteral = new TIntegerLiteral(Integer.toString(value),
				ctx.HEX_LITERAL().getSymbol().getLine(), ctx.HEX_LITERAL().getSymbol().getCharPositionInLine());

		return createPositionedNode(new AIntegerExpression(tIntegerLiteral), ctx);

	}

}
