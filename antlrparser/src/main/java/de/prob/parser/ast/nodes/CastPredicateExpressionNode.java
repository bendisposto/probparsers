package de.prob.parser.ast.nodes;


import de.prob.parser.ast.SourceCodePosition;

public class CastPredicateExpressionNode extends ExprNode {
	private PredicateNode predicate;

	public CastPredicateExpressionNode(SourceCodePosition sourceCodePosition, PredicateNode predicate) {
		super(sourceCodePosition);
		this.predicate = predicate;
	}

	public PredicateNode getPredicate() {
		return predicate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("bool(");
		sb.append(predicate.toString());
		sb.append(")");
		return sb.toString();
	}

	public void setArg(PredicateNode arg) {
		this.predicate = arg;
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other)
				&& this.predicate.equalAst(((CastPredicateExpressionNode) other).predicate);
	}
}
