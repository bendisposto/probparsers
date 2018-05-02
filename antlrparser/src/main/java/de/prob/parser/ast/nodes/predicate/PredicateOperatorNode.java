package de.prob.parser.ast.nodes.predicate;

import java.util.Iterator;
import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;
import de.prob.parser.ast.nodes.OperatorNode;

public class PredicateOperatorNode extends PredicateNode
		implements OperatorNode<PredicateOperatorNode.PredicateOperator> {
	public enum PredicateOperator {
		AND, OR, IMPLIES, EQUIVALENCE, NOT, TRUE, FALSE
	}

	private List<PredicateNode> predicateArguments;
	private PredicateOperator operator;

	public PredicateOperatorNode(SourceCodePosition sourceCodePosition, PredicateOperator operator,
			List<PredicateNode> predicateArguments) {
		super(sourceCodePosition);
		this.predicateArguments = predicateArguments;
		this.operator = operator;
	}

	public List<PredicateNode> getPredicateArguments() {
		return predicateArguments;
	}

	@Override
	public PredicateOperator getOperator() {
		return operator;
	}

	@Override
	public void setOperator(PredicateOperator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.operator.name());
		Iterator<PredicateNode> iter = predicateArguments.iterator();
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

	public void setPredicateList(List<PredicateNode> list) {
		this.predicateArguments = list;
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		PredicateOperatorNode that = (PredicateOperatorNode) other;
		return this.operator.equals(that.operator)
				&& NodeUtil.equalAst(this.predicateArguments, that.predicateArguments);

	}
}
