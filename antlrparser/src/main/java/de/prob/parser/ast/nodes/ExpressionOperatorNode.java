package de.prob.parser.ast.nodes;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.Iterator;
import java.util.List;

public class ExpressionOperatorNode extends ExprNode
		implements OperatorNode<ExpressionOperatorNode.ExpressionOperator> {

	public enum ExpressionOperator {

		// arithmetic
		NATURAL, NATURAL1, INTEGER, INT, NAT, NAT1, MININT, MAXINT//
		, BOOL, TRUE, FALSE, POWER_OF //
		, PLUS, MINUS, MULT, DIVIDE, MOD, INTERVAL//
		, UNARY_MINUS//
		, MAX, MIN//
		// set operators
		, SET_ENUMERATION, EMPTY_SET, SET_SUBTRACTION, UNION, INTERSECTION//
		, GENERALIZED_UNION, GENERALIZED_INTER//
		// relations
		, DOMAIN, RANGE, CARTESIAN_PRODUCT//
		, CARD, DOMAIN_RESTRICTION, OVERWRITE_RELATION, DIRECT_PRODUCT//
		, DOMAIN_SUBTRACTION, RANGE_RESTRICTION, RANGE_SUBTRATION//
		, INVERSE_RELATION, SET_RELATION
		// function
		, FUNCTION_CALL
		// sequence operators
		, FIRST, LAST, FRONT, TAIL, CONC, SEQ_ENUMERATION, EMPTY_SEQUENCE//
		, CONCAT, INSERT_FRONT, INSERT_TAIL, RESTRICT_FRONT, RESTRICT_TAIL//
		, SEQ, SEQ1, ISEQ, ISEQ1
		// special
		, COUPLE
	}

	private List<ExprNode> expressionNodes;

	private ExpressionOperator operator;

	public ExpressionOperatorNode(ParserRuleContext parseTree, List<ExprNode> expressionNodes,
			ExpressionOperator operator) {
		// used for set enumeration, e.g. {1,2,3}
		super(parseTree);
		this.expressionNodes = expressionNodes;
		this.operator = operator;
	}

	@Override
	public ExpressionOperator getOperator() {
		return operator;
	}

	@Override
	public void setOperator(ExpressionOperator operator) {
		this.operator = operator;
	}

	public List<ExprNode> getExpressionNodes() {
		return expressionNodes;
	}

	public int getArity() {
		return expressionNodes.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.operator.name());
		Iterator<ExprNode> iter = expressionNodes.iterator();
		if (iter.hasNext()) {
			sb.append("(");
			while (iter.hasNext()) {
				sb.append(iter.next().toString());
				if (iter.hasNext()) {
					sb.append(",");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}

	public void setExpressionList(List<ExprNode> list) {
		this.expressionNodes = list;
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		ExpressionOperatorNode that = (ExpressionOperatorNode) other;
		return this.operator.equals(that.operator) && NodeUtil.equalAst(this.expressionNodes, that.expressionNodes);
	}

}
