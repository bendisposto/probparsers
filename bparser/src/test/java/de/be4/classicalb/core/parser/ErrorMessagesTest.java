package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.Start;

public class ErrorMessagesTest {

	
	@Test
	public void testKeywordAsIdentifierInSigma() throws Exception {
		final String testMachine = "#EXPRESSION \nSIGMA(a, left).(a= 1& left= 2| {1} )";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BException e) {
			BParseException e1 = (BParseException) e.getCause();
			assertTrue(e1.getToken().getText().equals("left"));
		}
	}
	
	@Test
	public void testKeywordAsIdentifierInConstantsClause() throws Exception {
		final String testMachine = "MACHINE Test CONSTANTS right PROPERTIES right = 1 END";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BException e) {
			System.out.println(e.getMessage());
			BParseException e1 = (BParseException) e.getCause();
			assertTrue(e1.getToken().getText().equals("right"));
		}
	}
	
	@Test
	public void testKeywordAsIdentifierInUnitDeclaration() throws Exception {
		final String testMachine = "MACHINE Test CONSTANTS /*@unit*/ right PROPERTIES right = 1 END";
		try {
			getTreeAsString(testMachine);
			fail("Invalid identifier not detected");
		} catch (BException e) {
			BParseException e1 = (BParseException) e.getCause();
			assertTrue(e1.getToken().getText().equals("right"));
		}
	}
	

	private String getTreeAsString(final String testMachine) throws BException {
		// System.out.println("Parsing \"" + testMachine + "\"");
		final BParser parser = new BParser("testcase");
		parser.getOptions().grammar = RuleGrammar.getInstance();
		final Start startNode = parser.parse(testMachine, false);
		
		//startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}
}
