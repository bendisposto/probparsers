package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public class IdentifierExprNode extends ExprNode {

	final String name;
	private DeclarationNode declarationNode;

	public IdentifierExprNode(SourceCodePosition sourceCodePosition, String name) {
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

		IdentifierExprNode that = (IdentifierExprNode) other;
		return this.name.equals(that.name) && this.declarationNode.equalAst(that.declarationNode);
	}
}
