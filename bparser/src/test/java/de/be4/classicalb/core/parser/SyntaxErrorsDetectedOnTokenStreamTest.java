package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.node.Start;

public class SyntaxErrorsDetectedOnTokenStreamTest {

	@Test
	public void checkForDuplicateSemicolon() throws Exception {
		String s = "MACHINE DuplicateSemicolon\nOPERATIONS\n Foo = BEGIN skip END;\n ;r <-- Get = BEGIN r := xx END\nEND";
		try {
			getTreeAsString(s);
			fail("Missing Semicolon was not detected");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("Two succeeding"));
		}
	}
	
	
	@Test
	public void checkForClauseAfterConjunction() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES\n 1=1 & VARIABLES";
		try {
			getTreeAsString(s);
			fail("& VARIABLES was not detected");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("& VARIABLES"));
		}
	}

	@Test
	public void checkForDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES\n 1=1 &\n &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			final BLexerException ex = (BLexerException) e.getCause();
			// checking the position of the second &
			assertEquals(4, ex.getLastLine());
			assertEquals(2, ex.getLastPos());
		}
	}
	
	@Test
	public void checkForCommentBetweenDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES 1=1 & /* comment */\n &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("& &"));
			final BLexerException ex = (BLexerException) e.getCause();
			// checking the position of the second &
			assertEquals(3, ex.getLastLine());
			assertEquals(2, ex.getLastPos());
		}
	}
	
	@Test
	public void checkForSingleLineCommentBetweenDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES 1=1 & // comment 1 comment \n &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	@Test
	public void checkForDublicateAndInDefinitionsClause() throws Exception {
		String s = "MACHINE Definitions\nDEFINITIONS\n foo == 1=1 && 2=2  \nEND";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[3,14]"));
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	@Test
	public void checkForDublicateAndInDefinitionsClause2() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS\n foo == \n \n 1=1 \n&    & 2=2  \nEND";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			// there is no token available, hence the position is in the text
			assertTrue(e.getMessage().contains("[6,6]"));
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	
	@Test
	public void checkForDublicateDefinitionClause() throws Exception {
		String s = "MACHINE Definitions \n DEFINITIONS\n foo == 1\n CONSTANTS k \n DEFINITIONS\n bar == 1  \nEND";
		try {
			getTreeAsString(s);
			fail("Duplicate 'DEFINITION' clause was not detected.");
		} catch (BCompoundException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("[5,2] Clause 'DEFINITIONS' is used more than once"));
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
