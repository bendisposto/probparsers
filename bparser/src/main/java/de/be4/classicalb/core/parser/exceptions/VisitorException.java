package de.be4.classicalb.core.parser.exceptions;

import de.be4.classicalb.core.parser.node.Node;

@SuppressWarnings("serial")
public class VisitorException extends RuntimeException {

	private final Node node;
	private final String message;

	public VisitorException(final Node node, final String message) {
		this.node = node;
		this.message = message;
	}

	public Node getNode() {
		return this.node;
	}

	public String getMessage() {
		return this.message;
	}
}
