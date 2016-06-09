package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class LetPredicateTest {

	private BParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@Test
	public void testSingleIdentifierLetPredicate() throws Exception {
		final String testMachine = "#PREDICATE (LET x BE x = 5 IN x < 7 END)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(APredicateParseUnit(ALetPredicatePredicate(AIdentifierExpression([x])AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(5))ALessPredicate(AIdentifierExpression([x]),AIntegerExpression(7)))))",
				result);
	}

	private String getTreeAsString(final String testMachine) throws BException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}

}
