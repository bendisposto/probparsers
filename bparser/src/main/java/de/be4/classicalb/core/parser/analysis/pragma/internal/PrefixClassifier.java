package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.be4.classicalb.core.parser.analysis.pragma.IClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.PragmaParser;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public  class PrefixClassifier implements IClassifier {

	protected PragmaParser[] parsers;
	protected final String[] input;
	private final Class<? extends Node> clazz;

	
	public PrefixClassifier(String input, Class<? extends Node> clazz) {
		this.clazz = clazz;
		this.input = input.split(System.getProperty("line.separator"));
	}

	public PragmaParser getParser(int i) {
		if (i < 0 || parsers == null || parsers.length <= i
				|| parsers[i] == null) {
			return new PragmaParser() {
				public String parse(String text) {
					return text;
				}
			};
		}
		return parsers[i];
	}

	protected Class<? extends Node> getClazz() {
		return clazz;
	}
	
	private Node seek(Class<? extends Node> seek,Node start) {
		if (start == null || seek.isInstance(start)) return start;
		return seek(seek, start.parent());
	}

	public Node seek(UnknownPragma p, Start ast) {
		int li = p.getEnd().getLine() - 1;
		int ci = p.getEnd().getPos();

		char c = input[li].charAt(ci);
		while (li < input.length && Character.isWhitespace(c)) {
			if (ci < input[li].length()) ci++;
			else {
				ci = 0;
				li++;
			}
			c = input[li].charAt(ci);
		}

		Node nearestRight = p.getNearestRight();
		if (c == '(') {
			ParamFinder paremFinder = new ParamFinder(new SourcePosition(
					li + 1, ci), getClazz());
			ast.apply(paremFinder);
			return paremFinder.getNode();
		} 
		if (!getClazz().isInstance(nearestRight)) {
			return seek(getClazz(), nearestRight);
		}
		return nearestRight;
	}

}