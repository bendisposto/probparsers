package de.be4.classicalb.core.parser.analysis;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;

public class ParseTestUtil {
	public static String parsePred(final String input) throws BCompoundException {
		return parse(BParser.PREDICATE_PREFIX + " " + input);
	}

	public static String parseExpr(final String input) throws BCompoundException {
		return parse(BParser.EXPRESSION_PREFIX + " " + input);
	}

	private static String parse(final String input) throws BCompoundException {
		final BParser parser = new BParser();
		final Start ast = parser.parse(input, false);
		final Ast2String ast2String = new Ast2String();
		ast.apply(ast2String);
		return ast2String.toString();
	}

	public static String createTripleExpr(final String op1, final String op2) {
		return "(A " + op1 + " B " + op2 + " C)";
	}

	public static String createTripleExprLeft(final String op1, final String op2) {
		return "((A " + op1 + " B) " + op2 + " C)";
	}

	public static String createTripleExprRight(final String op1,
			final String op2) {
		return "(A " + op1 + " (B " + op2 + " C))";
	}
}
