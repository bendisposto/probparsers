package de.prob.parser.ast.nodes.predicate;

import java.util.Iterator;
import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.OperatorNode;
import de.prob.parser.ast.nodes.expression.ExprNode;

public class PredicateOperatorWithExprArgsNode extends PredicateNode
		implements OperatorNode<PredicateOperatorWithExprArgsNode.PredOperatorExprArgs> {

	public enum PredOperatorExprArgs {
		EQUAL, NOT_EQUAL, ELEMENT_OF, LESS_EQUAL, LESS, GREATER_EQUAL, GREATER//
		, NOT_BELONGING, INCLUSION, STRICT_INCLUSION, NON_INCLUSION, STRICT_NON_INCLUSION//
	}

	private List<ExprNode> expressionNodes;
	private PredOperatorExprArgs operator;

	public PredicateOperatorWithExprArgsNode(SourceCodePosition sourceCodePosition, PredOperatorExprArgs operator,
			List<ExprNode> expressionNodes) {
		super(sourceCodePosition);
		this.expressionNodes = expressionNodes;
		this.operator = operator;
	}

	@Override
	public PredOperatorExprArgs getOperator() {
		return operator;
	}

	@Override
	public void setOperator(PredOperatorExprArgs operator) {
		this.operator = operator;
	}

	public List<ExprNode> getExpressionNodes() {
		return expressionNodes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.operator.name()).append("(");
		Iterator<ExprNode> iter = expressionNodes.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next().toString());
			if (iter.hasNext()) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

	public void setArgumentsList(List<ExprNode> argumentList) {
		this.expressionNodes = argumentList;
	}

}
