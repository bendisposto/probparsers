package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public abstract class Node {
	private SourceCodePosition sourceCodePosition;
	private Node parent;

	public Node(SourceCodePosition sourceCodePosition) {
		this.sourceCodePosition = sourceCodePosition;
	}

	public SourceCodePosition getSourceCodePosition() {
		return this.sourceCodePosition;
	}

	public void setParent(Node newParent) {
		if (this.parent != null) {
			this.parent.removeChild(this);
		}
		this.parent = newParent;
	}

	public Node getParent() {
		return this.parent;
	}

	public void removeChild(Node child) {

	}

}
