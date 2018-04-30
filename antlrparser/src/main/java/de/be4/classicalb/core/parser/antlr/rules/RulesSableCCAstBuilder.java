package de.be4.classicalb.core.parser.antlr.rules;

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

	public RulesSableCCAstBuilder(RulesDefinitionAnalyser definitionsAnalyser) {
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
	public Node visitRuleComputationOperation(RuleComputationOperationContext ctx) {
		List<POperationAttribute> attributes = new ArrayList<>();
		for (Operation_attributesContext operation_attributesContext : ctx.operation_attributes()) {
			POperationAttribute attr = (POperationAttribute) operation_attributesContext.accept(this);
			attributes.add(attr);
		}
		PSubstitution ruleBody = (PSubstitution) ctx.substitution().accept(this);
		TIdentifierLiteral tIdentifierLiteral = createTIdentifierLiteral(ctx.IDENTIFIER());
		if (ctx.keyword.getType() == RULE) {
			return createPositionedNode(new ARuleOperation(tIdentifierLiteral, attributes, ruleBody), ctx);
		} else if (ctx.keyword.getType() == COMPUTATION) {
			return createPositionedNode(new AComputationOperation(tIdentifierLiteral, attributes, ruleBody), ctx);
		} else {
			throw new RuntimeException("Unexpected operation type: " + ctx.keyword.getText());
		}
	}

	@Override
	public Node visitRuleFailSubstitution(RuleFailSubstitutionContext ctx) {
		TKwSubstitutionOperator name = createPositionedToken(new TKwSubstitutionOperator(ctx.RULE_FAIL().getText()),
				ctx.RULE_FAIL().getSymbol());
		List<PExpression> arguments = new ArrayList<>();
		List<ExpressionContext> exprs = ctx.expression_list().exprs;
		for (ExpressionContext expressionContext : exprs) {
			PExpression pExpression = (PExpression) expressionContext.accept(this);
			arguments.add(pExpression);
		}
		AOperatorSubstitution opSub = new AOperatorSubstitution(name, arguments);
		return createPositionedNode(opSub, ctx);
	}

	@Override
	public Node visitFunctionOperation(FunctionOperationContext ctx) {
		List<POperationAttribute> attributes = new ArrayList<>();
		for (Operation_attributesContext operation_attributesContext : ctx.operation_attributes()) {
			POperationAttribute attr = (POperationAttribute) operation_attributesContext.accept(this);
			attributes.add(attr);
		}
		PSubstitution ruleBody = (PSubstitution) ctx.substitution().accept(this);
		TIdentifierLiteral tIdentifierLiteral = createTIdentifierLiteral(ctx.IDENTIFIER());

		List<PExpression> _returnValues_ = new ArrayList<>();
		if (null != ctx.return_values) {
			for (Token token : ctx.return_values.idents) {
				AIdentifierExpression aIdentifierExpression = createAIdentifierExpression(token);
				_returnValues_.add(aIdentifierExpression);
			}
		}
		List<PExpression> _parameters_ = new ArrayList<>();
		if (null != ctx.parameters) {
			for (Token token : ctx.parameters.idents) {
				AIdentifierExpression aIdentifierExpression = createAIdentifierExpression(token);
				_parameters_.add(aIdentifierExpression);
			}
		}
		AFunctionOperation aFunctionOperation = new AFunctionOperation(_returnValues_, tIdentifierLiteral, _parameters_,
				attributes, ruleBody);
		return createPositionedNode(aFunctionOperation, ctx);
	}

	@Override
	public Node visitDefineSubstitution(RulesGrammar.DefineSubstitutionContext ctx) {
		TIdentifierLiteral name = createTIdentifierLiteral(ctx.IDENTIFIER());
		PExpression type = (PExpression) ctx.type_expr.accept(this);
		PExpression dummyValue = null == ctx.dummy_expr ? null : (PExpression) ctx.dummy_expr.accept(this);
		PExpression value = (PExpression) ctx.value_expr.accept(this);
		return createPositionedNode(new ADefineSubstitution(name, type, dummyValue, value), ctx);
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
		ARuleFailSubSubstitution fail = new ARuleFailSubSubstitution(identifiers, where, errorType, message);
		return createPositionedNode(fail, ctx);
	}

	@Override
	public Node visitForLoopSubstitution(ForLoopSubstitutionContext ctx) {
		TerminalNode terminalNode = ctx.IDENTIFIER();
		PExpression identifier = createAIdentifierExpression(terminalNode);
		PExpression set = (PExpression) ctx.expression_in_par().accept(this);
		PSubstitution doSubst = (PSubstitution) ctx.substitution().accept(this);
		AForLoopSubstitution forLoop = new AForLoopSubstitution(createPExpressionList(identifier), set, doSubst);
		return createPositionedNode(forLoop, ctx);
	}

	@Override
	public Node visitPredicateOperator(PredicateOperatorContext ctx) {
		ArrayList<PExpression> pExpressionList = new ArrayList<>();
		for (ExpressionContext expressionContext : ctx.expression_list().exprs) {
			PExpression pExpression = (PExpression) expressionContext.accept(this);
			pExpressionList.add(pExpression);
		}
		TKwPredicateOperator tName = createPositionedToken(new TKwPredicateOperator(ctx.keyword.getText()),
				ctx.keyword);
		return createPositionedNode(new AOperatorPredicate(tName, pExpressionList), ctx);
	}

	@Override
	public Node visitExpressionOperator(ExpressionOperatorContext ctx) {
		ArrayList<PExpression> pExpressionList = new ArrayList<>();
		for (ExpressionContext expressionContext : ctx.expression_list().exprs) {
			PExpression pExpression = (PExpression) expressionContext.accept(this);
			pExpressionList.add(pExpression);
		}
		TKwExpressionOperator tName = createPositionedToken(new TKwExpressionOperator(ctx.keyword.getText()),
				ctx.keyword);
		return createPositionedNode(new AOperatorExpression(tName, pExpressionList), ctx);
	}
}
