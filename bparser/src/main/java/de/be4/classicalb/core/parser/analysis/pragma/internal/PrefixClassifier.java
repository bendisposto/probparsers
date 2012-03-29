package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.IClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.PragmaParser;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PrefixClassifier implements IClassifier {

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	protected PragmaParser[] parsers;
	protected final int[] inputSizes;
	private final Class<? extends Node> clazz;
	private final String input;
	private List<String> warnings;

	public PrefixClassifier(String input, Class<? extends Node> clazz) {
		this.input = input;
		this.clazz = clazz;
		String[] split = input.split(LINE_SEPARATOR);
		inputSizes = new int[split.length];
		inputSizes[0] = 0;
		for (int i = 1; i < split.length; i++) {
			inputSizes[i] = inputSizes[i - 1] + split[i - 1].length()
					+ LINE_SEPARATOR.length();
			// System.out.println(inputSizes[i]);
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

	private Node seek(Class<? extends Node> seek, Node start) {
		if (start == null || start instanceof Start || start instanceof EOF
				|| seek.isInstance(start)) return start;
		return seek(seek, start.parent());
	}

	private boolean validExpIdentifier(char c) {
		return (Character.isJavaIdentifierPart(c));
	}

	public Node seek(UnknownPragma p, Start ast) {
		this.warnings = new ArrayList<String>();
		Node nearestRight = p.getNearestRight();

		if (nearestRight instanceof EOF) return nearestRight;

		int li = p.getEnd().getLine();
		int ci = p.getEnd().getPos();

		int pos = inputSizes[li - 1] + ci - 1;

		char c = input.charAt(pos);
		String next = "";
		boolean skip = false;
		if (pos < input.length() - 1) next = input.substring(pos, pos + 2);
		if (next.equals("/*")) skip = true;

		while (skip || (li < input.length() && Character.isWhitespace(c))) {
			next = "";
			c = input.charAt(pos); // find first non-whitespace character
			if (pos < input.length() - 1) next = input.substring(pos, pos + 2);
			pos++;
			if (next.equals("*/")) {
				skip = false;
				pos++;
				c = input.charAt(pos++);
			}
			if (next.equals("/*")) skip = true;
		}

		if (c == '(') {
			ParamFinder paremFinder = new ParamFinder(
					new SourcePosition(li, ci), getClazz());
			ast.apply(paremFinder);
			return paremFinder.getNode();
		}

		if (c == '%') {
			SpecialTypeFinder lambdaFinder = new SpecialTypeFinder(nearestRight,ALambdaExpression.class);
			return lambdaFinder.find();
		}
		
		if (c == '!') {
			SpecialTypeFinder universalFinder = new SpecialTypeFinder(nearestRight,AForallPredicate.class);
			return universalFinder.find();
		}
		if (c == '%') {
			SpecialTypeFinder existentialFinder = new SpecialTypeFinder(nearestRight,AExistsPredicate.class);
			return existentialFinder.find();
		}

		if (!validExpIdentifier(c)) {
			warnings.add("maybe_illplaced");
		}

		if (!getClazz().isInstance(nearestRight)) {
			return seek(getClazz(), nearestRight);
		}
		return nearestRight;
	}

	public List<String> getWarnings() {
		return Collections.unmodifiableList(warnings);
	}

}