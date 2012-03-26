package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.be4.classicalb.core.parser.analysis.pragma.IClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.PragmaParser;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public  class PrefixClassifier implements IClassifier {

	protected PragmaParser[] parsers;
	protected final int[] inputSizes;
	private final Class<? extends Node> clazz;
	private final String input;

	
	public PrefixClassifier(String input, Class<? extends Node> clazz) {
		this.input = input;
		this.clazz = clazz;
		String[] split = input.split(System.getProperty("line.separator"));
		inputSizes = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			inputSizes[i] = split[i].length()+1;
		}
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
		if (start == null || start instanceof Start || start instanceof EOF || seek.isInstance(start)) return start;
		return seek(seek, start.parent());
	}

	
	
	
	public Node seek(UnknownPragma p, Start ast) {
		Node nearestRight = p.getNearestRight();
		int li = p.getEnd().getLine();
		int ci = p.getEnd().getPos();

		int pos = 0;
		for (int i = 0; i < li; i++) {
			pos += inputSizes[i];
		}
		pos += ci;

		char c = input.charAt(pos);
		while (li < input.length() && Character.isWhitespace(c)) {
			c = input.charAt(pos++); // find first non-whitespace character
		}

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