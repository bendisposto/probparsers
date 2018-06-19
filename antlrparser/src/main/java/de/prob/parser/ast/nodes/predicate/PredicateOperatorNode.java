package de.prob.parser.ast.nodes.predicate;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.OperatorNode;

import java.util.Iterator;
import java.util.List;

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

}
