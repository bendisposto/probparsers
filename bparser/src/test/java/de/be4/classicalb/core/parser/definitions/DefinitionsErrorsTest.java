package de.be4.classicalb.core.parser.definitions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;
import util.Ast2String;

public class DefinitionsErrorsTest {

	@Test
	public void checkForInvalidSubstitution() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS \n foo == BEGIN\n x=1 END \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid substitution was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[4,3]"));
		}
	}

	@Test
	public void checkAtSymbolInDefinitions() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS \n foo == BEGIN\n @ END \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid substitution was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[4,2]"));
		}
	}

	@Test
	public void checkForInvalidExpression() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS \n foo == 1 + \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid definition was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("[4,1]"));
		}
	}
	
	@Test
	public void checkForErrorPositionInDefinitionWithMultilineComments() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS \n foo == 1=1\n /* \n comment\n comment2\n comment3 \n */\n&& 1=1 \nEND";
		System.out.println(s);
		try {
			getTreeAsString(s);
			fail("Invalid definition was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("[9,2]"));
		}
	}

	@Test
	public void checkForInvalidDefinition() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS \n foo == BEING x :=1 END \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid substitution was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[3,15]"));
			assertTrue(e.getMessage().contains("expecting end of definition"));
		}
	}

	@Test
	public void checkForInvalidFormula() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS\n foo == \n 1+; \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid formula was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[4,4]"));
		}
	}

	@Test
	public void checkForInvalidFormula2() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS\n foo == \n 1=; \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid formula was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[4,4]"));
		}
	}

	@Test
	public void checkForInvalidFormula3() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS\n foo(xx) == (xx : OBJECTS -->(1..card(OBJECTS))\n; \nEND";
		try {
			getTreeAsString(s);
			fail("Invalid formula was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[4,1]"));
			assertTrue(e.getMessage().contains("expecting: ')'"));
		}
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		// System.out.println();
		return string;
	}
}
