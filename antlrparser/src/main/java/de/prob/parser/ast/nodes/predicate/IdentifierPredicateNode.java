package de.prob.parser.ast.nodes.predicate;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

public class IdentifierPredicateNode extends PredicateNode {

	private final String name;
	private DeclarationNode declarationNode;

	public IdentifierPredicateNode(SourceCodePosition sourceCodePosition, String name) {
		super(sourceCodePosition);
		this.name = name;
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

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		IdentifierPredicateNode that = (IdentifierPredicateNode) other;
		return this.name.equals(that.name) && this.declarationNode.equalAst(that.declarationNode);
	}
}
