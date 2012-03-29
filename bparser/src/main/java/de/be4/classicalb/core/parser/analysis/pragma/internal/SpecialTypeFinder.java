package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;

public class SpecialTypeFinder {

	private final Node node;
	private final Class<? extends Node> clazz;

	public SpecialTypeFinder(Node nearestRight, Class<? extends Node> clazz) {
		this.node = nearestRight;
		this.clazz = clazz;
	}

	public Node find() {
		return find(this.node);
	}
	
	private Node find(Node n) {
		if (clazz.isInstance(n) || n instanceof Start) {
			return n;
		} else
			return find(n.parent());
	}

}
