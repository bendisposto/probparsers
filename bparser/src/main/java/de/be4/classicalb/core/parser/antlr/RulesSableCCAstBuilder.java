package de.be4.classicalb.core.parser.antlr;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import static de.be4.classicalb.core.parser.antlr.StaticSableCCAstBuilder.*;
import static files.RulesGrammar.*;

import de.be4.classicalb.core.parser.node.*;

import files.RulesGrammar;
import files.RulesGrammar.ExpressionContext;
import files.RulesGrammar.MachineContext;
import files.RulesGrammar.Machine_clauseContext;
import files.RulesGrammar.Operation_attributesContext;

public class RulesSableCCAstBuilder extends AbstractRulesSableCCAstBuilder {

	public RulesSableCCAstBuilder(DefinitionsAnalyser definitionsAnalyser) {
		super(definitionsAnalyser);
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
			variant = new AMachineMachineVariant();
			break;
		case RULES_MACHINE:
			variant = new AMachineMachineVariant();
			break;
		default:
			throw new RuntimeException("unexpected");
		}
		variant = createPositionedNode(variant, ctx.variant);
		return createPositionedNode(new AAbstractMachineParseUnit(variant, header, list), ctx);
	}

	@Override
	public Node visitRuleOperation(RuleOperationContext ctx) {
		TIdentifierLiteral ruleName = createTIdentifierLiteral(ctx.IDENTIFIER());
		List<POperationAttribute> attributes = new ArrayList<>();
		for (Operation_attributesContext operation_attributesContext : ctx.operation_attributes()) {
			POperationAttribute attr = (POperationAttribute) operation_attributesContext.accept(this);
			attributes.add(attr);
		}
		PSubstitution ruleBody = (PSubstitution) ctx.substitution().accept(this);
		ARuleOperation ruleOp = new ARuleOperation(ruleName, attributes, ruleBody);
		return createPositionedNode(ruleOp, ctx);
	}

	@Override
	public Node visitDependsOnAttribute(DependsOnAttributeContext ctx) {
		List<PExpression> arguments = new ArrayList<>();
		List<Token> idents = ctx.identifier_list().idents;
		for (Token token : idents) {
			AIdentifierExpression aIdentifierExpression = createAIdentifierExpression(token);
			arguments.add(aIdentifierExpression);
		}
		Token keyword = ctx.keyword;
		TKwAttributeIdentifier name = createPositionedToken(new TKwAttributeIdentifier(keyword.getText()), keyword);
		AOperationAttribute attr = new AOperationAttribute(name, arguments);
		return createPositionedNode(attr, ctx);
	}

	@Override
	public Node visitErrorTypesAttribute(RulesGrammar.ErrorTypesAttributeContext ctx) {
		List<PExpression> arguments = new ArrayList<>();
		TIntegerLiteral tIntegerLiteral = createTIntegerLiteral(ctx.Number());
		AIntegerExpression aIntExpression = createPositionedNode(new AIntegerExpression(tIntegerLiteral), ctx.Number());
		arguments.add(aIntExpression);
		Token keyword = ctx.keyword;
		TKwAttributeIdentifier name = createPositionedToken(new TKwAttributeIdentifier(keyword.getText()), keyword);
		AOperationAttribute attr = new AOperationAttribute(name, arguments);
		return createPositionedNode(attr, ctx);
	}

	@Override
	public Node visitExpressionAttribute(ExpressionAttributeContext ctx) {
		List<PExpression> arguments = new ArrayList<>();
		PExpression pExpression = (PExpression) ctx.expression().accept(this);
		arguments.add(pExpression);
		Token keyword = ctx.keyword;
		TKwAttributeIdentifier name = createPositionedToken(new TKwAttributeIdentifier(keyword.getText()), keyword);
		AOperationAttribute attr = new AOperationAttribute(name, arguments);
		return createPositionedNode(attr, ctx);
	}

	@Override
	public Node visitTagsAttribute(RulesGrammar.TagsAttributeContext ctx) {
		List<PExpression> arguments = new ArrayList<>();
		for (ExpressionContext expressionContext : ctx.expression_list().exprs) {
			PExpression pExpression = (PExpression) expressionContext.accept(this);
			arguments.add(pExpression);
		}
		Token keyword = ctx.keyword;
		TKwAttributeIdentifier name = createPositionedToken(new TKwAttributeIdentifier(keyword.getText()), keyword);
		AOperationAttribute attr = new AOperationAttribute(name, arguments);
		return createPositionedNode(attr, ctx);
	}

	@Override
	public Node visitPredicateAttribute(RulesGrammar.PredicateAttributeContext ctx) {
		Token keyword = ctx.keyword;
		TKwPredicateAttribute _name_ = createPositionedToken(new TKwPredicateAttribute(keyword.getText()), keyword);
		PPredicate _predicate_ = (PPredicate) ctx.predicate().accept(this);
		APredicateAttributeOperationAttribute predAttribute = new APredicateAttributeOperationAttribute(_name_,
				_predicate_);
		return createPositionedNode(predAttribute, ctx);

	}

	@Override
	public Node visitRuleForAllSubstitution(RuleForAllSubstitutionContext ctx) {
		final List<Token> idents = ctx.identifier_list().idents;
		final List<PExpression> identifiers = createAIdentifierExpressionList(idents);
		PPredicate where = (PPredicate) ctx.where_pred.accept(this);
		PPredicate expect = (PPredicate) ctx.expect_pred.accept(this);
		TIntegerLiteral errorType = ctx.Number() != null ? createTIntegerLiteral(ctx.Number()) : null;
		PExpression message = (PExpression) ctx.expression_in_par().accept(this);
		AForallSubMessageSubstitution forAll = new AForallSubMessageSubstitution(identifiers, where, expect, errorType,
				message);
		return createPositionedNode(forAll, ctx);
	}

	@Override
	public Node visitRuleAnySubstitution(RulesGrammar.RuleAnySubstitutionContext ctx) {
		final List<Token> idents = ctx.identifier_list().idents;
		final List<PExpression> identifiers = createAIdentifierExpressionList(idents);
		PPredicate where = (PPredicate) ctx.predicate().accept(this);
		TIntegerLiteral errorType = ctx.Number() != null ? createTIntegerLiteral(ctx.Number()) : null;
		PExpression message = (PExpression) ctx.expression_in_par().accept(this);
		ARuleAnySubMessageSubstitution forAll = new ARuleAnySubMessageSubstitution(identifiers, where, errorType,
				message);
		return createPositionedNode(forAll, ctx);
	}

	@Override
	public Node visitForLoopSubstitution(ForLoopSubstitutionContext ctx) {
		TerminalNode terminalNode = ctx.IDENTIFIER();
		PExpression identifier = createAIdentifierExpression(terminalNode);
		PExpression set = (PExpression) ctx.expression_in_par().accept(this);
		PSubstitution doSubst = (PSubstitution) ctx.substitution().accept(this);
		AForLoopSubstitution forLoop = new AForLoopSubstitution(identifier, set, doSubst);
		return createPositionedNode(forLoop, ctx);
	}

}
