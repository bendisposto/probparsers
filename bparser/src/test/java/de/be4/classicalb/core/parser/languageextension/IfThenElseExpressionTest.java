package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;

public class IfThenElseExpressionTest {

	private BParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@Test
	public void testIfThenElseExpression1() throws Exception {
		final String testMachine = "#EXPRESSION (IF x < 3 THEN 5 ELSE 17 END)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AIfThenElseExpression(ALessPredicate(AIdentifierExpression([x]),AIntegerExpression(3))AIntegerExpression(5)AIntegerExpression(17))))",
				result);
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}

}
