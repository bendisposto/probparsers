package de.prob.parser.ast.nodes.predicate;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;

public class IdentifierPredicateNode extends PredicateNode {

	private final String name;
	private DeclarationNode declarationNode;

	public IdentifierPredicateNode(SourceCodePosition sourceCodePosition, String name) {
		super(sourceCodePosition);
		this.name = name;
	}

	public void setDeclarationNode(DeclarationNode declarationNode) {
		this.declarationNode = declarationNode;
	}

	public DeclarationNode getDeclarationNode() {
		return declarationNode;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}
}
