package de.prob.parser.ast.nodes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.substitution.SingleAssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;

public class OperationNode extends Node {

	private final String name;
	private final List<DeclarationNode> outputParams;
	private final List<DeclarationNode> params;
	private SubstitutionNode substitution;

	public OperationNode(SourceCodePosition sourceCodePosition, String name, List<DeclarationNode> outputParamNodes,
			SubstitutionNode substitution, List<DeclarationNode> paramNodes) {
		super(sourceCodePosition);
		this.name = name;
		this.substitution = substitution;
		this.outputParams = outputParamNodes;
		this.params = paramNodes;
	}

	public String getName() {
		return name;
	}

	public SubstitutionNode getSubstitution() {
		return substitution;
	}

	public void setSubstitution(SubstitutionNode substitution) {
		this.substitution = substitution;
	}

	@Override
	public String toString() {
		if (substitution instanceof SingleAssignSubstitutionNode) {
			return name + " = BEGIN " + substitution + " END";
		} else {
			return name + " = " + substitution;
		}
	}

	public Set<DeclarationNode> getAssignedDeclarationNodes() {
		return new HashSet<>(this.substitution.getAssignedVariables());
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}
		OperationNode that = (OperationNode) other;
		return this.name.equals(that.name) && this.substitution.equalAst(that.substitution);
	}

	public List<DeclarationNode> getOutputParams() {
		return outputParams;
	}

	public List<DeclarationNode> getParams() {
		return params;
	}
}
