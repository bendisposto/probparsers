package de.prob.parser.ast.nodes;


import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.ExprNode;

public class DeferredSetNode extends ExprNode {

	private DeclarationNode declarationNode;
	private String name;

	public DeferredSetNode(SourceCodePosition sourceCodePosition, DeclarationNode declNode, String name) {
		super(sourceCodePosition);
		this.declarationNode = declNode;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public DeclarationNode getDeclarationNode() {
		return this.declarationNode;
	}

}
