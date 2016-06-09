package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class LetExpressionTest {

	private BParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@Test
	public void testSingleIdentifierLetExpression() throws Exception {
		final String testMachine = "#EXPRESSION (LET x BE x = 5 IN x+1 END)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ALetExpressionExpression(AIdentifierExpression([x])AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(5))AAddExpression(AIdentifierExpression([x]),AIntegerExpression(1)))))",
				result);
	}

	@Test
	public void testMultipleIdentifiersLetExpression() throws Exception {
		final String testMachine = "#EXPRESSION (LET x, y BE x = 5 & y = 7 IN x+y END)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ALetExpressionExpression(AIdentifierExpression([x])AIdentifierExpression([y])AConjunctPredicate(AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(5)),AEqualPredicate(AIdentifierExpression([y]),AIntegerExpression(7)))AAddExpression(AIdentifierExpression([x]),AIdentifierExpression([y])))))",
				result);
	}

	private String getTreeAsString(final String testMachine) throws BException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}

}
