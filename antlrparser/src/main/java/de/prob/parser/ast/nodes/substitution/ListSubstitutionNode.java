package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;

import java.util.List;

public class ListSubstitutionNode extends SubstitutionNode {

	private final ListOperator operator;
	private List<SubstitutionNode> substitutions;

	public enum ListOperator {
		Parallel, Sequential
	}

	public ListSubstitutionNode(SourceCodePosition sourceCodePosition, ListOperator operator,
			List<SubstitutionNode> substitutions) {
		super(sourceCodePosition);
		this.operator = operator;
		this.substitutions = substitutions;
		substitutions.forEach(node -> node.setParent(this));
	}

	public List<SubstitutionNode> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(List<SubstitutionNode> substitutions) {
		this.substitutions = substitutions;
	}

	public ListOperator getOperator() {
		return this.operator;
	}

}
