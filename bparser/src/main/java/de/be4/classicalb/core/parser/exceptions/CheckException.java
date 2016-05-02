package de.be4.classicalb.core.parser.exceptions;

import de.be4.classicalb.core.parser.node.Node;

@SuppressWarnings("serial")
public class CheckException extends Exception {
	private final Node[] nodes;

	public CheckException(final String message, final Node[] nodes) {
		super(message);
		this.nodes = nodes;
	}

	public CheckException(final String message, final Node node) {
		this(message, new Node[] { node });
	}

	/**
	 * Returns all {@link Node}s that are relevant for this exception. This can
	 * be a list of all nodes which caused this same {@link CheckException}. In
	 * other cases this can be the list of all nodes which caused this exception
	 * together, e.g. all clauses if multiple are present and only one is
	 * allowed.
	 * 
	 * @return The involved {@link Node} objects.
	 */
	public Node[] getNodes() {
		return nodes;
	}

	public Node getFirstNode() {
		return nodes[0];
	}
}
