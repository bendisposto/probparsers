package de.prob.parser.ast.nodes.substitution;

import java.util.Set;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.Node;

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
