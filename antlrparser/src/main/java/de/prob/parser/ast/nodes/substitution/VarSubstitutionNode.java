package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;

import java.util.List;

public class VarSubstitutionNode extends SubstitutionNode {

	private List<DeclarationNode> localVariables;
	private SubstitutionNode body;

	public VarSubstitutionNode(SourceCodePosition sourceCodePosition, List<DeclarationNode> localIdentifiers,
			SubstitutionNode body) {
		super(sourceCodePosition);
		this.localVariables = localIdentifiers;
		this.body = body;
		this.localVariables.forEach(node -> node.setParent(this));
		this.body.setParent(this);
	}

	public List<DeclarationNode> getLocalIdentifiers() {
		return localVariables;
	}

	public SubstitutionNode getBody() {
		return body;
	}

	public void setSubstitution(SubstitutionNode substitutionNode) {
		this.body = substitutionNode;
	}

	@Override
	public String toString() {
		return "VAR " + localVariables + " THEN " + body + " END";
	}

}
