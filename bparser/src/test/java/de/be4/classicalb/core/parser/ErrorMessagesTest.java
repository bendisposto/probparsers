package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.grammars.RulesGrammar;
import de.be4.classicalb.core.parser.node.Start;
import util.Ast2String;

public class ErrorMessagesTest {

	@Test
	public void testKeywordAsIdentifierInSigma() throws Exception {
		final String testMachine = "#EXPRESSION \nSIGMA(a, left).(a= 1& left= 2| {1} )";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BCompoundException e) {
			BParseException e1 = (BParseException) e.getFirstException().getCause();
			assertTrue(e1.getToken().getText().equals("left"));
		}
	}

	@Test
	public void testKeywordAsIdentifierInConstantsClause() throws Exception {
		final String testMachine = "MACHINE Test CONSTANTS right PROPERTIES right = 1 END";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			BParseException e1 = (BParseException) e.getFirstException().getCause();
			assertTrue(e1.getToken().getText().equals("right"));
		}
	}

	@Test
	public void testKeywordAsIdentifierInUnitDeclaration() throws Exception {
		final String testMachine = "MACHINE Test CONSTANTS /*@unit*/ right PROPERTIES right = 1 END";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BCompoundException e) {
			BParseException e1 = (BParseException) e.getFirstException().getCause();
			assertTrue(e1.getToken().getText().equals("right"));
		}
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		// System.out.println("Parsing \"" + testMachine + "\"");
		final BParser parser = new BParser("testcase");
		parser.getOptions().setGrammar(RulesGrammar.getInstance());
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}
}
