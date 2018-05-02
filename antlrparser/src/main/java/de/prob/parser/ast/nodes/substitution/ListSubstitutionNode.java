package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;

public abstract class ListSubstitutionNode extends SubstitutionNode {

	private List<SubstitutionNode> substitutions;

	public ListSubstitutionNode(SourceCodePosition sourceCodePosition, List<SubstitutionNode> substitutions) {
		super(sourceCodePosition);
		this.substitutions = substitutions;
	}

	public List<SubstitutionNode> getSubstitutions() {
		return substitutions;
	}

	public void setSubstitutions(List<SubstitutionNode> substitutions) {
		this.substitutions = substitutions;
	}
	
}
