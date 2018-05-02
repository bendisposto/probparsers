package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public abstract class Node {
	private final SourceCodePosition sourceCodePosition;

	public Node(SourceCodePosition sourceCodePosition) {
		this.sourceCodePosition = sourceCodePosition;
	}

	public SourceCodePosition getSourceCodePosition() {
		return this.sourceCodePosition;
	}

}
