package de.prob.parser.ast.nodes;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;

public class QuantifiedExpressionNode extends SetComprehensionNode {

	public enum QuantifiedExpressionOperator {
		QUANTIFIED_UNION, QUANTIFIED_INTER
	}

	private QuantifiedExpressionOperator operator;
	private ExprNode expressionNode;

	public ExprNode getExpressionNode() {
		return expressionNode;
	}

	public void setExpr(ExprNode expr) {
		this.expressionNode = expr;
	}

	public QuantifiedExpressionNode(ParseTree ctx, List<DeclarationNode> declarationList, PredicateNode predNode,
			ExprNode expressionNode, QuantifiedExpressionOperator operator2) {
		super(ctx, declarationList, predNode);
		this.expressionNode = expressionNode;
		this.operator = operator2;
	}

	public QuantifiedExpressionOperator getOperator() {
		return operator;
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		QuantifiedExpressionNode that = (QuantifiedExpressionNode) other;
		return this.operator.equals(that.operator) && getExpressionNode().equalAst(that.getExpressionNode())
				&& this.expressionNode.equalAst(that.expressionNode)
				&& NodeUtil.equalAst(getDeclarationList(), that.getDeclarationList());

	}
}
