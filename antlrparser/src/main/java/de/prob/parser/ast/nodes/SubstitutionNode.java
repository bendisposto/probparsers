package de.prob.parser.ast.nodes;

import java.util.Set;

import de.prob.parser.ast.SourceCodePosition;

public abstract class SubstitutionNode extends Node {
	private Set<DeclarationNode> assignedVariables;

	public Set<DeclarationNode> getAssignedVariables() {
		return assignedVariables;
	}

	public SubstitutionNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

	public void setAssignedVariables(Set<DeclarationNode> assignedVariables) {
		this.assignedVariables = assignedVariables;
	}
}
