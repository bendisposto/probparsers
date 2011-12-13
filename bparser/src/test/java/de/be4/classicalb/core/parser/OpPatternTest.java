package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
public class OpPatternTest {
	BParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new BParser();
	}

	@Test
	public void testNoArgs() throws BException {
		checkParser("no arguments", "operation1", "operation1");
	}

	@Test
	public void testSimpleArgument() throws BException {
		checkParser("simple argument", "operation1(5)",
				"operation1ADefArgpattern(AIntegerExpression(5))");
	}

	@Test
	public void testSimpleArguments() throws BException {
		checkParser(
				"simple argument",
				"operation1(5,7)",
				"operation1ADefArgpattern(AIntegerExpression(5))ADefArgpattern(AIntegerExpression(7))");
	}

	@Test
	public void testEmptyArguments() throws BException {
		checkParser("simple argument", "operation1(5,_)",
				"operation1ADefArgpattern(AIntegerExpression(5))AUndefArgpattern()");
	}

	private void checkParser(final String description, final String oppattern,
			final String expected) throws BException {
		final Start ast = parser.parse(BParser.OPERATION_PATTERN_PREFIX
				+ oppattern, false);
		final Ast2String ast2String = new Ast2String();
		ast.apply(ast2String);
		final String parsed = ast2String.toString();
		assertEquals(description, "Start(AOppatternParseUnit(" + expected
				+ "))", parsed);
	}
}
