package de.prob.parser.antlr;

import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode.PredicateOperator;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode.PredOperatorExprArgs;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode.ListOperator;
import de.prob.parser.ast.nodes.substitution.SkipSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.OperationCallSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import de.prob.parser.ast.nodes.substitution.WhileSubstitutionNode;
import files.BParser;
import files.BParser.AndOrListContext;
import files.BParser.BooleanValueContext;
import files.BParser.ExpressionContext;
import files.BParser.Expression_in_parContext;
import files.BParser.Expression_listContext;
import files.BParser.Identifier_or_function_or_recordContext;
import files.BParser.PredicateContext;
import files.BParser.Predicate_atomicContext;
import files.BParser.SubstitutionContext;
import files.BParser.Substitution_l1Context;
import files.BParserBaseVisitor;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaASTCreator extends BParserBaseVisitor<Node> {

	private static final Map<Integer, ExpressionOperator> exprOperatorMap = new HashMap<>();

	private static void addExprOperator(Integer key, ExpressionOperator operator) {
		if (exprOperatorMap.containsKey(key)) {
			throw new RuntimeException("Duplicate key: " + operator);
		}
		exprOperatorMap.put(key, operator);
	}

	static {
		// arithmetic
		addExprOperator(BParser.PLUS, ExpressionOperator.PLUS);
		addExprOperator(BParser.MINUS, ExpressionOperator.MINUS);
		addExprOperator(BParser.POWER_OF, ExpressionOperator.POWER_OF);
		addExprOperator(BParser.MULT, ExpressionOperator.MULT);
		addExprOperator(BParser.DIVIDE, ExpressionOperator.DIVIDE);
		addExprOperator(BParser.MOD, ExpressionOperator.MOD);
		addExprOperator(BParser.NATURAL, ExpressionOperator.NATURAL);
		addExprOperator(BParser.NATURAL1, ExpressionOperator.NATURAL1);
		addExprOperator(BParser.INTEGER, ExpressionOperator.INTEGER);
		addExprOperator(BParser.NAT, ExpressionOperator.NAT);
		addExprOperator(BParser.NAT1, ExpressionOperator.NAT1);
		addExprOperator(BParser.INT, ExpressionOperator.INT);
		addExprOperator(BParser.MININT, ExpressionOperator.MININT);
		addExprOperator(BParser.MAXINT, ExpressionOperator.MAXINT);
		addExprOperator(BParser.INTERVAL, ExpressionOperator.INTERVAL);

		addExprOperator(BParser.TRUE, ExpressionOperator.TRUE);
		addExprOperator(BParser.FALSE, ExpressionOperator.FALSE);
		addExprOperator(BParser.BOOL, ExpressionOperator.BOOL);

		// sets
		addExprOperator(BParser.POW, ExpressionOperator.POW);
		addExprOperator(BParser.CARD, ExpressionOperator.CARD);
		addExprOperator(BParser.INTERSECTION, ExpressionOperator.INTERSECTION);
		addExprOperator(BParser.UNION, ExpressionOperator.UNION);
		addExprOperator(BParser.SET_SUBTRACTION, ExpressionOperator.SET_SUBTRACTION);

		addExprOperator(BParser.MIN, ExpressionOperator.MIN);
		addExprOperator(BParser.MAX, ExpressionOperator.MAX);

		// couples
		addExprOperator(BParser.MAPLET, ExpressionOperator.COUPLE);

		// relations
		addExprOperator(BParser.DOM, ExpressionOperator.DOMAIN);
		addExprOperator(BParser.RAN, ExpressionOperator.RANGE);
		addExprOperator(BParser.OVERWRITE_RELATION, ExpressionOperator.OVERWRITE_RELATION);
		addExprOperator(BParser.DIRECT_PRODUCT, ExpressionOperator.DIRECT_PRODUCT);
		addExprOperator(BParser.CONCAT, ExpressionOperator.CONCAT);
		addExprOperator(BParser.DOMAIN_RESTRICTION, ExpressionOperator.DOMAIN_RESTRICTION);
		addExprOperator(BParser.DOMAIN_SUBSTRACTION, ExpressionOperator.DOMAIN_SUBTRACTION);
		addExprOperator(BParser.RANGE_RESTRICTION, ExpressionOperator.RANGE_RESTRICTION);
		addExprOperator(BParser.RANGE_SUBSTRATION, ExpressionOperator.RANGE_SUBTRATION);
		addExprOperator(BParser.TILDE, ExpressionOperator.INVERSE_RELATION);
		addExprOperator(BParser.SET_RELATION, ExpressionOperator.SET_RELATION);

		// sequence operators
		addExprOperator(BParser.FIRST, ExpressionOperator.FIRST);
		addExprOperator(BParser.LAST, ExpressionOperator.LAST);
		addExprOperator(BParser.FRONT, ExpressionOperator.FRONT);
		addExprOperator(BParser.TAIL, ExpressionOperator.TAIL);
		addExprOperator(BParser.CONC, ExpressionOperator.CONC);
		addExprOperator(BParser.INSERT_FRONT, ExpressionOperator.INSERT_FRONT);
		addExprOperator(BParser.INSERT_TAIL, ExpressionOperator.INSERT_TAIL);
		addExprOperator(BParser.RESTRICT_FRONT, ExpressionOperator.RESTRICT_FRONT);
		addExprOperator(BParser.RESTRICT_TAIL, ExpressionOperator.RESTRICT_TAIL);
		addExprOperator(BParser.SEQ, ExpressionOperator.SEQ);
		addExprOperator(BParser.SEQ1, ExpressionOperator.SEQ1);
		addExprOperator(BParser.ISEQ, ExpressionOperator.ISEQ);
		addExprOperator(BParser.ISEQ1, ExpressionOperator.ISEQ1);
	}

	private static final Map<Integer, PredOperatorExprArgs> predicateBinOperatorMap = new HashMap<>();

	private static void addPredicateOperator(Integer key, PredOperatorExprArgs operator) {
		if (predicateBinOperatorMap.containsKey(key)) {
			throw new RuntimeException("Duplicate entry. key: " + key + ", operator: " + operator);
		}
		predicateBinOperatorMap.put(key, operator);
	}

	static {
		addPredicateOperator(BParser.EQUAL, PredOperatorExprArgs.EQUAL);
		addPredicateOperator(BParser.NOT_EQUAL, PredOperatorExprArgs.NOT_EQUAL);
		addPredicateOperator(BParser.ELEMENT_OF, PredOperatorExprArgs.ELEMENT_OF);
		addPredicateOperator(BParser.COLON, PredOperatorExprArgs.ELEMENT_OF);
		addPredicateOperator(BParser.LESS_EQUAL, PredOperatorExprArgs.LESS_EQUAL);
		addPredicateOperator(BParser.LESS, PredOperatorExprArgs.LESS);
		addPredicateOperator(BParser.GREATER_EQUAL, PredOperatorExprArgs.GREATER_EQUAL);
		addPredicateOperator(BParser.GREATER, PredOperatorExprArgs.GREATER);
		addPredicateOperator(BParser.NOT_BELONGING, PredOperatorExprArgs.NOT_BELONGING);
		addPredicateOperator(BParser.INCLUSION, PredOperatorExprArgs.INCLUSION);
		addPredicateOperator(BParser.STRICT_INCLUSION, PredOperatorExprArgs.STRICT_INCLUSION);
		addPredicateOperator(BParser.NON_INCLUSION, PredOperatorExprArgs.NON_INCLUSION);
		addPredicateOperator(BParser.STRICT_NON_INCLUSION, PredOperatorExprArgs.STRICT_NON_INCLUSION);
	}

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
			PredicateOperator op = ctx.operators.get(i).getType() == BParser.AND ? PredicateOperator.AND
					: PredicateOperator.OR;
			temp = new PredicateOperatorNode(Util.createSourceCodePosition(ctx), op,
					createPredicateNodeList(temp, right));
		}
		return temp;
	}

	@Override
	public Node visitPredicateKeyword(BParser.PredicateKeywordContext ctx) {
		if (ctx.keyword == null) {
			throw new RuntimeException(ctx.keyword.getText());
		}
		int type = ctx.keyword.getType();
		PredicateOperator op = type == BParser.BTRUE ? PredicateOperator.TRUE : PredicateOperator.FALSE;
		List<PredicateNode> list = new ArrayList<>();
		return new PredicateOperatorNode(Util.createSourceCodePosition(ctx), op, list);
	}

	@Override
	public Node visitImplication(BParser.ImplicationContext ctx) {
		PredicateNode left = (PredicateNode) ctx.left.accept(this);
		PredicateNode right = (PredicateNode) ctx.left.accept(this);
		List<PredicateNode> list = new ArrayList<>();
		list.add(left);
		list.add(right);
		return new PredicateOperatorNode(Util.createSourceCodePosition(ctx), PredicateOperator.IMPLIES, list);
	}

	@Override
	public Node visitPredicateBinPredicateOperator(BParser.PredicateBinPredicateOperatorContext ctx) {
		PredicateNode left = (PredicateNode) ctx.left.accept(this);
		PredicateNode right = (PredicateNode) ctx.left.accept(this);
		List<PredicateNode> list = new ArrayList<>();
		list.add(left);
		list.add(right);
		return new PredicateOperatorNode(Util.createSourceCodePosition(ctx), PredicateOperator.EQUIVALENCE, list);
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
	public Node visitBinOperatorP160(BParser.BinOperatorP160Context ctx) {
		final ExprNode left = (ExprNode) ctx.left.accept(this);
		final ExprNode right = (ExprNode) ctx.right.accept(this);
		final int type = ctx.expressionOperatorP160().operator.getType();
		ExpressionOperator op = exprOperatorMap.get(type);
		if (op == null) {
			throw new RuntimeException("Not implemented: " + ctx.expressionOperatorP160().operator.getText());
		}
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), createExprNodeList(left, right), op);
	}

	@Override
	public Node visitExpressionFunctionCall(BParser.ExpressionFunctionCallContext ctx) {
		List<ExprNode> list = new ArrayList<>();
		final ExprNode func = (ExprNode) ctx.expression().accept(this);
		list.add(func);
		for (Expression_in_parContext arg : ctx.expression_in_par()) {
			final ExprNode argNode = (ExprNode) arg.accept(this);
			list.add(argNode);
		}
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), list, ExpressionOperator.FUNCTION_CALL);
	}

	@Override
	public Node visitImageExpression(BParser.ImageExpressionContext ctx) {
		List<ExprNode> list = new ArrayList<>();
		final ExprNode func = (ExprNode) ctx.expression().accept(this);
		list.add(func);
		ExprNode arg = (ExprNode) ctx.expression_in_par().accept(this);
		list.add(arg);
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), list,
				ExpressionOperator.RELATIONAL_IMAGE);
	}

	@Override
	public Node visitUnaryMinus(BParser.UnaryMinusContext ctx) {
		ExprNode expr = (ExprNode) ctx.expression().accept(this);
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), createExprNodeList(expr),
				ExpressionOperator.UNARY_MINUS);
	}

	@Override
	public Node visitParenthesis(BParser.ParenthesisContext ctx) {
		return ctx.expression_in_par().accept(this);
	}

	@Override
	public Node visitEmptySet(BParser.EmptySetContext ctx) {
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), new ArrayList<>(),
				ExpressionOperator.SET_ENUMERATION);
	}

	@Override
	public Node visitPredicateBinExpression(BParser.PredicateBinExpressionContext ctx) {
		ExprNode left = (ExprNode) ctx.left.accept(this);
		ExprNode right = (ExprNode) ctx.right.accept(this);

		int type = ctx.predicate_expression_operator().operator.getType();
		PredOperatorExprArgs op = predicateBinOperatorMap.get(type);
		if (op == null) {
			throw new RuntimeException();
		}
		return new PredicateOperatorWithExprArgsNode(Util.createSourceCodePosition(ctx), op,
				createExprNodeList(left, right));
	}

	// Expression

	@Override
	public Node visitExpressionKeyword(BParser.ExpressionKeywordContext ctx) {
		int type = ctx.expression_keyword().operator.getType();
		ExpressionOperator op = exprOperatorMap.get(type);
		if (op == null) {
			throw new RuntimeException(ctx.expression_keyword().operator.getText());
		}
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), op);
	}

	@Override
	public Node visitExpressionPrefixOperator(BParser.ExpressionPrefixOperatorContext ctx) {
		int type = ctx.expression_prefix_operator().operator.getType();
		ExpressionOperator op = exprOperatorMap.get(type);
		if (op == null) {
			throw new RuntimeException(ctx.expression_prefix_operator().operator.getText());
		}
		ExprNode argument = (ExprNode) ctx.expression_in_par().accept(this);
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), createExprNodeList(argument), op);
	}

	@Override
	public Node visitPredicateParenthesis(BParser.PredicateParenthesisContext ctx) {
		return ctx.predicate().accept(this);
	}

	@Override
	public Node visitExpressionInParNext(BParser.ExpressionInParNextContext ctx) {
		return ctx.expression().accept(this);
	}

	@Override
	public ExprNode visitBinOperator(BParser.BinOperatorContext ctx) {
		ExprNode left = (ExprNode) ctx.left.accept(this);
		ExprNode right = (ExprNode) ctx.right.accept(this);
		final int type = ctx.operator.getType();
		final ExpressionOperator op = exprOperatorMap.get(type);
		if (op == null) {
			throw new RuntimeException("Not implemented operator: " + ctx.operator.getText());
		}
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx), createExprNodeList(left, right), op);
	}

	@Override
	public Node visitSetEnumeration(BParser.SetEnumerationContext ctx) {
		return new ExpressionOperatorNode(Util.createSourceCodePosition(ctx),
				visitExpressionList(ctx.expression_list()), ExpressionOperator.SET_ENUMERATION);
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
	public Node visitSubstitutionIdentifierCall(BParser.SubstitutionIdentifierCallContext ctx) {
		List<String> names = new ArrayList<>();
		for (TerminalNode tNode : ctx.composed_identifier().IDENTIFIER()) {
			names.add(tNode.getText());
		}

		List<ExprNode> arguments = ctx.expression_list() == null ? new ArrayList<>()
				: visitExpressionList(ctx.expression_list());
		return new OperationCallSubstitutionNode(Util.createSourceCodePosition(ctx), names, arguments);
	}

	@Override
	public Node visitSubstitutionOperationCall(BParser.SubstitutionOperationCallContext ctx) {
		List<String> names = new ArrayList<>();
		for (TerminalNode tNode : ctx.composed_identifier().IDENTIFIER()) {
			names.add(tNode.getText());
		}
		List<ExprNode> arguments = ctx.expression_list() == null ? new ArrayList<>()
				: visitExpressionList(ctx.expression_list());

		List<ExprNode> output = new ArrayList<>();
		for (Token exprNode : ctx.identifier_list().idents) {
			String name = exprNode.getText();
			IdentifierExprNode identifierExprNode = new IdentifierExprNode(Util.createSourceCodePosition(exprNode),
					name);
			output.add(identifierExprNode);
		}

		return new OperationCallSubstitutionNode(Util.createSourceCodePosition(ctx), names, arguments, output);
	}

	@Override
	public Node visitSubstitutionBlock(BParser.SubstitutionBlockContext ctx) {
		return ctx.substitution().accept(this);
	}

	@Override
	public Node visitSubstitutionSkip(BParser.SubstitutionSkipContext ctx) {
		return new SkipSubstitutionNode(Util.createSourceCodePosition(ctx));
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
	public Node visitIfSubstitution(BParser.IfSubstitutionContext ctx) {
		List<PredicateNode> predList = new ArrayList<>();
		List<SubstitutionNode> subList = new ArrayList<>();

		PredicateNode firstPred = (PredicateNode) ctx.pred.accept(this);
		predList.add(firstPred);
		SubstitutionNode firstSub = (SubstitutionNode) ctx.thenSub.accept(this);
		subList.add(firstSub);

		for (PredicateContext predCtx : ctx.elsifPred) {
			PredicateNode pred = (PredicateNode) predCtx.accept(this);
			predList.add(pred);
		}

		for (SubstitutionContext subCtx : ctx.elsifSub) {
			SubstitutionNode sub = (SubstitutionNode) subCtx.accept(this);
			subList.add(sub);
		}

		SubstitutionNode elseSubstitution = null;
		if (ctx.elseSub != null) {
			elseSubstitution = (SubstitutionNode) ctx.elseSub.accept(this);
		}
		return new IfOrSelectSubstitutionsNode(Util.createSourceCodePosition(ctx),
				IfOrSelectSubstitutionsNode.Operator.IF, predList, subList, elseSubstitution);
	}

	@Override
	public Node visitWhileSubstitution(BParser.WhileSubstitutionContext ctx) {
		PredicateNode condition = (PredicateNode) ctx.condition.accept(this);
		SubstitutionNode body = (SubstitutionNode) ctx.substitution().accept(this);
		PredicateNode invariant = (PredicateNode) ctx.invariant.accept(this);
		ExprNode variant = (ExprNode) ctx.variant.accept(this);
		return new WhileSubstitutionNode(Util.createSourceCodePosition(ctx), condition, body, invariant, variant);
	}

	@Override
	public Node visitVarSubstitution(BParser.VarSubstitutionContext ctx) {
		throw new RuntimeException("implement me");
	}

	@Override
	public Node visitSelectSubstitution(BParser.SelectSubstitutionContext ctx) {
		List<PredicateNode> predList = new ArrayList<>();
		List<SubstitutionNode> subList = new ArrayList<>();

		PredicateNode firstPred = (PredicateNode) ctx.pred.accept(this);
		predList.add(firstPred);
		SubstitutionNode firstSub = (SubstitutionNode) ctx.sub.accept(this);
		subList.add(firstSub);

		for (PredicateContext predCtx : ctx.when_pred) {
			PredicateNode pred = (PredicateNode) predCtx.accept(this);
			predList.add(pred);
		}

		for (SubstitutionContext subCtx : ctx.when_sub) {
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

	@Override
	public Node visitConditionSubstitution(BParser.ConditionSubstitutionContext ctx) {
		return ctx.substitution().accept(this);
	}

}
