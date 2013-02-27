package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.IClassifier;
import de.be4.classicalb.core.parser.analysis.pragma.PragmaParser;
import de.be4.classicalb.core.parser.node.AComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.ADeferredSetSet;
import de.be4.classicalb.core.parser.node.AEmptySetExpression;
import de.be4.classicalb.core.parser.node.AEnumeratedSetSet;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PrefixClassifier implements IClassifier {

	protected PragmaParser[] parsers;
	protected final int[] inputSizes;
	private final String input;
	private List<String> warnings;
	private final List<Class<? extends Node>> classes;

	public PrefixClassifier(String input, Class<? extends Node>... classes) {
		this.input = input;
		this.classes = Arrays.asList(classes);
		String[] split = input.split("(?<=\n)");
		inputSizes = new int[split.length];
		inputSizes[0] = 0;
		for (int i = 1; i < split.length; i++) {
			inputSizes[i] = inputSizes[i - 1] + split[i - 1].length();
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

	private Node seek(Node start) {
		if (start == null || start instanceof Start || start instanceof EOF
				|| checkInstance(start))
			return start;
		return seek(start.parent());
	}

	private boolean validExpIdentifier(char c) {
		return (Character.isJavaIdentifierPart(c));
	}

	@SuppressWarnings("unchecked")
	public Node seek(UnknownPragma p, Start ast) {
		this.warnings = new ArrayList<String>();
		Node nearestRight = p.getNearestRight();

		if (nearestRight instanceof EOF)
			return nearestRight;

		int li = p.getEnd().getLine();
		int ci = p.getEnd().getPos();

		int pos = inputSizes[li - 1] + ci - 1;

		char c = input.charAt(pos);
		String next = "";
		boolean skip = false;
		if (pos < input.length() - 1)
			next = input.substring(pos, pos + 2);
		if (next.equals("/*"))
			skip = true;

		while (skip || (li < input.length() && Character.isWhitespace(c))) {
			next = "";
			c = input.charAt(pos); // find first non-whitespace character
			if (pos < input.length() - 1)
				next = input.substring(pos, pos + 2);
			pos++;
			if (next.equals("*/")) {
				skip = false;
				pos++;
				c = input.charAt(pos++);
			}
			if (next.equals("/*"))
				skip = true;
		}

		if (c == '(') {
			ParamFinder paremFinder = new ParamFinder(
					new SourcePosition(li, ci), classes);
			ast.apply(paremFinder);
			return paremFinder.getNode();
		}

		if (c == '%') {
			SpecialTypeFinder lambdaFinder = new SpecialTypeFinder(
					nearestRight, ALambdaExpression.class);
			return lambdaFinder.find();
		}

		if (c == '!') {
			SpecialTypeFinder universalFinder = new SpecialTypeFinder(
					nearestRight, AForallPredicate.class);
			return universalFinder.find();
		}
		if (c == '%') {
			SpecialTypeFinder existentialFinder = new SpecialTypeFinder(
					nearestRight, AExistsPredicate.class);
			return existentialFinder.find();
		}

		if (c == '{') {
			SpecialTypeFinder setFinder = new SpecialTypeFinder(nearestRight,
					AEnumeratedSetSet.class, ADeferredSetSet.class,
					ASetExtensionExpression.class,
					AComprehensionSetExpression.class,
					AEmptySetExpression.class);
			return setFinder.find();
		}

		if (!validExpIdentifier(c)) {
			warnings.add("maybe_illplaced");
		}

		if (!checkInstance(nearestRight)) {
			return seek(nearestRight);
		}

		return nearestRight;
	}

	private boolean checkInstance(Node n) {
		for (Class<? extends Node> c : classes) {
			if (c.isInstance(n))
				return true;
		}
		return false;
	}

	public List<String> getWarnings() {
		if (warnings == null)
			return Collections.emptyList();
		return Collections.unmodifiableList(warnings);
	}

}