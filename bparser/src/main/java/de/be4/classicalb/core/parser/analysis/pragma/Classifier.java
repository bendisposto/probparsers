package de.be4.classicalb.core.parser.analysis.pragma;

import de.be4.classicalb.core.parser.node.Node;

public class Classifier {

	private final Class<? extends Node> seek;
	private final PragmaParser[] parsers;

	public Classifier(Class<? extends Node> seek, PragmaParser... parsers) {
		this.seek = seek;
		this.parsers = parsers;
	}

	public PragmaParser getParser(int i) {
		if (i < 0 || parsers.length <= i || parsers[i] == null) {
			return new PragmaParser() {
				public String parse(String text) {
					return text;
				}
			};
		}
		return parsers[i];
	}

	public Node seek(Node start) {
		if (start == null || seek.isInstance(start)) return start;
		return seek(start.parent());
	}
}
