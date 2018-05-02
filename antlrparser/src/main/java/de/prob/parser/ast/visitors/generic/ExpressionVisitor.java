package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;

public interface ExpressionVisitor {

	default void visitExprNode(ExprNode node) {
		if (node instanceof ExpressionOperatorNode) {
			visitExprOperatorNode((ExpressionOperatorNode) node);
		} else if (node instanceof IdentifierExprNode) {
			visitIdentifierExprNode((IdentifierExprNode) node);
		} else if (node instanceof NumberNode) {
			visitNumberNode((NumberNode) node);
		} else if (node instanceof QuantifiedExpressionNode) {
			visitQuantifiedExpressionNode((QuantifiedExpressionNode) node);
		} else if (node instanceof SetComprehensionNode) {
			visitSetComprehensionNode((SetComprehensionNode) node);
		} else if (node instanceof CastPredicateExpressionNode) {
			visitCastPredicateExpressionNode((CastPredicateExpressionNode) node);
		} else if (node instanceof IdentifierExprNode) {
			visitIdentifierExprNode((IdentifierExprNode) node);
		}else {
			throw new AssertionError(node.getClass());
		}
		
	}

	void visitExprOperatorNode(ExpressionOperatorNode node);

	void visitIdentifierExprNode(IdentifierExprNode node);

	void visitCastPredicateExpressionNode(CastPredicateExpressionNode node);

	void visitNumberNode(NumberNode node);

	void visitQuantifiedExpressionNode(QuantifiedExpressionNode node);

	void visitSetComprehensionNode(SetComprehensionNode node);

}
