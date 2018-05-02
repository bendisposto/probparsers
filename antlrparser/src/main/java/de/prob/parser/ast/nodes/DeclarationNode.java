package de.prob.parser.ast.nodes;


import de.prob.parser.ast.SourceCodePosition;

public class DeclarationNode extends TypedNode {

	private final String name;

	public DeclarationNode(SourceCodePosition sourceCodePosition, String name) {
		super(sourceCodePosition);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
