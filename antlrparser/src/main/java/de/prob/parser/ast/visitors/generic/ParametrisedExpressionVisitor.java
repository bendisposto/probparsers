package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;

public interface ParametrisedExpressionVisitor<R, P> {

	default R visitExprNode(ExprNode node, P expected) {
		if (node instanceof ExpressionOperatorNode) {
			return visitExprOperatorNode((ExpressionOperatorNode) node, expected);
		} else if (node instanceof IdentifierExprNode) {
			return visitIdentifierExprNode((IdentifierExprNode) node, expected);
		} else if (node instanceof NumberNode) {
			return visitNumberNode((NumberNode) node, expected);
		} else if (node instanceof QuantifiedExpressionNode) {
			return visitQuantifiedExpressionNode((QuantifiedExpressionNode) node, expected);
		} else if (node instanceof SetComprehensionNode) {
			return visitSetComprehensionNode((SetComprehensionNode) node, expected);
		} else if (node instanceof CastPredicateExpressionNode) {
			return visitCastPredicateExpressionNode((CastPredicateExpressionNode) node, expected);
		}
		throw new AssertionError();
	}

	R visitExprOperatorNode(ExpressionOperatorNode node, P expected);

	R visitIdentifierExprNode(IdentifierExprNode node, P expected);

	R visitCastPredicateExpressionNode(CastPredicateExpressionNode node, P expected);

	R visitNumberNode(NumberNode node, P expected);

	R visitQuantifiedExpressionNode(QuantifiedExpressionNode node, P expected);

	R visitSetComprehensionNode(SetComprehensionNode node, P expected);

}
