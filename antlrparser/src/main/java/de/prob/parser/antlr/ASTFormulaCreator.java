package de.prob.parser.antlr;

import static files.BParser.AND;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode.PredicateOperator;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode.PredOperatorExprArgs;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode.ListOperator;
import de.prob.parser.ast.nodes.substitution.SubstitutionIdentifierCallNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import files.BParser;
import files.BParserBaseVisitor;
import files.BParser.AndOrListContext;
import files.BParser.BooleanValueContext;
import files.BParser.ExpressionContext;
import files.BParser.Expression_listContext;
import files.BParser.Identifier_or_function_or_recordContext;
import files.BParser.PredicateContext;
import files.BParser.Predicate_atomicContext;
import files.BParser.SubstitutionContext;
import files.BParser.Substitution_l1Context;

public class ASTFormulaCreator extends BParserBaseVisitor<Node> {

	@Override
	public Node visitChildren(RuleNode node) {
		throw new RuntimeException("Not implemented: " + node.getClass().getSimpleName());
	}

	// Predicates

	public List<PredicateNode> createPredicateNodeList(PredicateNode... predicateArguments) {
		List<PredicateNode> list = new ArrayList<>();
		for (PredicateNode predicateNode : predicateArguments) {
			list.add(predicateNode);
		}
		return list;
	}

	@Override
	public PredicateNode visitAndOrList(AndOrListContext ctx) {
		List<Predicate_atomicContext> terms = ctx.terms;
		List<Token> operators = ctx.operators;
		PredicateNode temp = (PredicateNode) ctx.terms.get(0).accept(this);
		for (int i = 0; i < operators.size(); i++) {
			Predicate_atomicContext rightContext = terms.get(i + 1);
			PredicateNode right = (PredicateNode) rightContext.accept(this);
			PredicateOperator op = ctx.operators.get(i).getType() == AND ? PredicateOperator.AND : PredicateOperator.OR;
			temp = new PredicateOperatorNode(Util.createSourceCodePosition(ctx), op,
					createPredicateNodeList(temp, right));
		}
		return temp;
	}

	@Override
	public Node visitPredicateP30Next(BParser.PredicateP30NextContext ctx) {
		return ctx.predicate_p40().accept(this);
	}

	@Override
	public Node visitPredicateP40Next(BParser.PredicateP40NextContext ctx) {
		return ctx.predicate_atomic().accept(this);
	}

	@Override
	public Node visitPredicateBinExpression(BParser.PredicateBinExpressionContext ctx) {
		ExprNode left = (ExprNode) ctx.left.accept(this);
		ExprNode right = (ExprNode) ctx.right.accept(this);
		PredOperatorExprArgs op = null;
		int type = ctx.predicate_expression_operator().operator.getType();
		switch (type) {
		case BParser.EQUAL:
			op = PredOperatorExprArgs.EQUAL;
			break;
		case BParser.NOT_EQUAL:
			op = PredOperatorExprArgs.NOT_EQUAL;
			break;
		case BParser.ELEMENT_OF:
			op = PredOperatorExprArgs.ELEMENT_OF;
			break;
		case BParser.COLON:
			op = PredOperatorExprArgs.ELEMENT_OF;
			break;
		case BParser.LESS_EQUAL:
			op = PredOperatorExprArgs.LESS_EQUAL;
			break;
		case BParser.LESS:
			op = PredOperatorExprArgs.LESS;
			break;
		case BParser.GREATER_EQUAL:
			op = PredOperatorExprArgs.GREATER_EQUAL;
			break;
		case BParser.GREATER:
			op = PredOperatorExprArgs.GREATER;
			break;
		case BParser.NOT_BELONGING:
			op = PredOperatorExprArgs.NOT_BELONGING;
			break;
		case BParser.INCLUSION:
			op = PredOperatorExprArgs.INCLUSION;
			break;
		case BParser.STRICT_INCLUSION:
			op = PredOperatorExprArgs.STRICT_INCLUSION;
			break;
		case BParser.NON_INCLUSION:
			op = PredOperatorExprArgs.NON_INCLUSION;
			break;
		case BParser.STRICT_NON_INCLUSION:
			op = PredOperatorExprArgs.STRICT_NON_INCLUSION;
			break;

		default:
			throw new RuntimeException();
		}

		return new PredicateOperatorWithExprArgsNode(Util.createSourceCodePosition(ctx), op,
				createExprNodeList(left, right));
	}

	// Expression

	@Override
	public Node visitExpressionKeyword(BParser.ExpressionKeywordContext ctx) {
		int type = ctx.expression_keyword().operator.getType();
		ExpressionOperator op = null;
		switch (type) {
		case BParser.NATURAL:
			op = ExpressionOperator.NATURAL;
			break;
		case BParser.INTEGER:
			op = ExpressionOperator.INTEGER;
			break;
		case BParser.BOOL:
			op = ExpressionOperator.BOOL;
			break;

		default:
			throw new RuntimeException(ctx.expression_keyword().operator.getText());
		}
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), op);
	}

	@Override
	public ExprNode visitBinOperator(BParser.BinOperatorContext ctx) {
		ExprNode left = (ExprNode) ctx.left.accept(this);
		ExprNode right = (ExprNode) ctx.right.accept(this);
		ExpressionOperator op = null;
		int type = ctx.operator.getType();
		switch (type) {
		case BParser.PLUS:
			op = ExpressionOperator.PLUS;
			break;
		default:
			break;
		}
		/*-
		 * 
		map.put(BMoThParser.PLUS, ExpressionOperator.PLUS);
		map.put(BMoThParser.NATURAL, ExpressionOperator.NATURAL);
		map.put(BMoThParser.NATURAL1, ExpressionOperator.NATURAL1);
		map.put(BMoThParser.INTEGER, ExpressionOperator.INTEGER);
		map.put(BMoThParser.NAT, ExpressionOperator.NAT);
		map.put(BMoThParser.NAT1, ExpressionOperator.NAT1);
		map.put(BMoThParser.INT, ExpressionOperator.INT);
		map.put(BMoThParser.MININT, ExpressionOperator.MININT);
		map.put(BMoThParser.MAXINT, ExpressionOperator.MAXINT);
		
		map.put(BMoThParser.BOOL, ExpressionOperator.BOOL);
		map.put(BMoThParser.TRUE, ExpressionOperator.TRUE);
		map.put(BMoThParser.FALSE, ExpressionOperator.FALSE);
		map.put(BMoThParser.POWER_OF, ExpressionOperator.POWER_OF);
		map.put(BMoThParser.MULT, ExpressionOperator.MULT);
		map.put(BMoThParser.DIVIDE, ExpressionOperator.DIVIDE);
		map.put(BMoThParser.MOD, ExpressionOperator.MOD);
		map.put(BMoThParser.SET_SUBTRACTION, ExpressionOperator.SET_SUBTRACTION);
		map.put(BMoThParser.INTERVAL, ExpressionOperator.INTERVAL);
		map.put(BMoThParser.UNION, ExpressionOperator.UNION);
		map.put(BMoThParser.INTERSECTION, ExpressionOperator.INTERSECTION);
		map.put(BMoThParser.MAPLET, ExpressionOperator.COUPLE);
		map.put(BMoThParser.DOM, ExpressionOperator.DOMAIN);
		map.put(BMoThParser.RAN, ExpressionOperator.RANGE);
		
		map.put(BMoThParser.MIN, ExpressionOperator.MIN);
		map.put(BMoThParser.MAX, ExpressionOperator.MAX);
		
		// relations
		map.put(BMoThParser.OVERWRITE_RELATION, ExpressionOperator.OVERWRITE_RELATION);
		map.put(BMoThParser.DIRECT_PRODUCT, ExpressionOperator.DIRECT_PRODUCT);
		map.put(BMoThParser.CONCAT, ExpressionOperator.CONCAT);
		map.put(BMoThParser.DOMAIN_RESTRICTION, ExpressionOperator.DOMAIN_RESTRICTION);
		map.put(BMoThParser.DOMAIN_SUBTRACTION, ExpressionOperator.DOMAIN_SUBTRACTION);
		map.put(BMoThParser.RANGE_RESTRICTION, ExpressionOperator.RANGE_RESTRICTION);
		map.put(BMoThParser.RANGE_SUBTRACTION, ExpressionOperator.RANGE_SUBTRATION);
		map.put(BMoThParser.TILDE, ExpressionOperator.INVERSE_RELATION);
		map.put(BMoThParser.SET_RELATION, ExpressionOperator.SET_RELATION);
		
		// sets
		map.put(BMoThParser.GENERALIZED_UNION, ExpressionOperator.GENERALIZED_UNION);
		map.put(BMoThParser.GENERALIZED_INTER, ExpressionOperator.GENERALIZED_INTER);
		map.put(BMoThParser.CARD, ExpressionOperator.CARD);
		
		// sequence operators
		map.put(BMoThParser.FIRST, ExpressionOperator.FIRST);
		map.put(BMoThParser.LAST, ExpressionOperator.LAST);
		map.put(BMoThParser.FRONT, ExpressionOperator.FRONT);
		map.put(BMoThParser.TAIL, ExpressionOperator.TAIL);
		map.put(BMoThParser.CONC, ExpressionOperator.CONC);
		map.put(BMoThParser.INSERT_FRONT, ExpressionOperator.INSERT_FRONT);
		map.put(BMoThParser.INSERT_TAIL, ExpressionOperator.INSERT_TAIL);
		map.put(BMoThParser.RESTRICT_FRONT, ExpressionOperator.RESTRICT_FRONT);
		map.put(BMoThParser.RESTRICT_TAIL, ExpressionOperator.RESTRICT_TAIL);
		map.put(BMoThParser.SEQ, ExpressionOperator.SEQ);
		map.put(BMoThParser.SEQ1, ExpressionOperator.SEQ1);
		map.put(BMoThParser.ISEQ, ExpressionOperator.ISEQ);
		map.put(BMoThParser.ISEQ1, ExpressionOperator.ISEQ1);
		 */
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), createExprNodeList(left, right), op);
	}

	@Override
	public Node visitNumber(BParser.NumberContext ctx) {
		BigInteger value = new BigInteger(ctx.Number().getText());
		return new NumberNode(Util.createSourceCodePosition(ctx), value);
	}

	@Override
	public Node visitBooleanValue(BParser.BooleanValueContext ctx) {
		if (ctx.value.getText().equals("TRUE")) {
			return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), ExpressionOperator.TRUE);
		} else if (ctx.value.getText().equals("FALSE")) {
			return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), ExpressionOperator.FALSE);
		}
		return notReachable(ctx);
	}

	@Override
	public Node visitExpressionIdentifier(BParser.ExpressionIdentifierContext ctx) {
		return new IdentifierExprNode(Util.createSourceCodePosition(ctx), ctx.getText());
	}

	// Substitution
	@Override
	public SubstitutionNode visitAssignSubstitution(BParser.AssignSubstitutionContext ctx) {
		List<ExprNode> leftList = new ArrayList<>();
		for (Identifier_or_function_or_recordContext left : ctx.identifier_or_function_or_record()) {
			ExprNode leftNode = (ExprNode) left.accept(this);
			leftList.add(leftNode);
		}

		List<ExprNode> exprList = visitExpressionList(ctx.expression_list());

		return new AssignSubstitutionNode(Util.createSourceCodePosition(ctx), leftList, exprList);
	}

	@Override
	public Node visitSubstitutionList(BParser.SubstitutionListContext ctx) {

		List<Token> operators = ctx.operators;
		int token = operators.get(0).getType();
		// check that all tokens are of the same type
		for (int i = 1; i < operators.size(); i++) {
			if (token != operators.get(i).getType()) {
				throw new RuntimeException();
			}
		}

		List<SubstitutionNode> result = new ArrayList<>();
		for (Substitution_l1Context substitutionContext : ctx.substitution_l1()) {
			SubstitutionNode sub = (SubstitutionNode) substitutionContext.accept(this);
			result.add(sub);
		}

		ListOperator operator = null;
		if (token == BParser.SEMICOLON) {
			operator = ListOperator.Sequential;
		} else {
			operator = ListOperator.Parallel;
		}

		return new ListSubstitutionNode(Util.createSourceCodePosition(ctx), operator, result);
	}

	@Override
	public Node visitSubstitutionIdentifierCall(BParser.SubstitutionIdentifierCallContext ctx) {
		List<String> names = new ArrayList<>();
		for (TerminalNode tNode : ctx.composed_identifier().IDENTIFIER()) {
			names.add(tNode.getText());
		}

		List<ExprNode> arguments = ctx.expression_list() == null ? new ArrayList<>()
				: visitExpressionList(ctx.expression_list());
		return new SubstitutionIdentifierCallNode(Util.createSourceCodePosition(ctx), names, arguments);
	}

	@Override
	public Node visitSelectSubstitution(BParser.SelectSubstitutionContext ctx) {
		List<PredicateNode> predList = new ArrayList<>();
		for (PredicateContext predCtx : ctx.predicate()) {
			PredicateNode pred = (PredicateNode) predCtx.accept(this);
			predList.add(pred);
		}

		List<SubstitutionNode> subList = new ArrayList<>();
		for (SubstitutionContext subCtx : ctx.substitution()) {
			SubstitutionNode sub = (SubstitutionNode) subCtx.accept(this);
			subList.add(sub);
		}

		SubstitutionNode elseSubstitution = null;
		if (ctx.else_sub != null) {
			elseSubstitution = (SubstitutionNode) ctx.else_sub.accept(this);
		}
		return new IfOrSelectSubstitutionsNode(Util.createSourceCodePosition(ctx),
				IfOrSelectSubstitutionsNode.Operator.SELECT, predList, subList, elseSubstitution);
	}

	@Override
	public ExprNode visitAssignSingleIdentifier(BParser.AssignSingleIdentifierContext ctx) {
		return new IdentifierExprNode(Util.createSourceCodePosition(ctx), ctx.getText());
	}

	@Override
	public Node visitSubstitutionNextL1(BParser.SubstitutionNextL1Context ctx) {
		return ctx.substitution_l1().accept(this);
	}

	// Util
	private List<ExprNode> visitExpressionList(Expression_listContext expression_list) {
		List<ExprNode> list = new ArrayList<>();
		for (ExpressionContext eCtx : expression_list.exprs) {
			ExprNode expr = (ExprNode) eCtx.accept(this);
			list.add(expr);
		}
		return list;
	}

	public static List<ExprNode> createExprNodeList(ExprNode... nodes) {
		List<ExprNode> list = new ArrayList<>();
		for (ExprNode exprNode : nodes) {
			list.add(exprNode);
		}
		return list;
	}

	private Node notReachable(BooleanValueContext ctx) {

		return null;
	}
}
