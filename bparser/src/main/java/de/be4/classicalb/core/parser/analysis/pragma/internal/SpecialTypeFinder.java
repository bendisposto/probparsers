package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.Arrays;
import java.util.List;

import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;

public class SpecialTypeFinder {

	private final Node node;
	private final List<Class<? extends Node>> clazz;

	public SpecialTypeFinder(Node nearestRight, Class<? extends Node>... classes) {
		this.node = nearestRight;
		this.clazz = Arrays.asList(classes);
	}

	public Node find() {
		return find(this.node);
	}
	
	private Node find(Node n) {
		if (clazz.contains(n.getClass()) || n instanceof Start) {
			return n;
		} else
			return find(n.parent());
	}

}
