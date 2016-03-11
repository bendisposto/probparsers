package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class SyntaxErrorsDetectedOnTokenStreamTest {

	@Test
	public void checkForDuplicateSemicolon() throws Exception {
		String s = "MACHINE MissingSemicolon\nSETS\nID={aa,bb}\nVARIABLES xx\nINVARIANT\nxx:ID\nINITIALISATION xx:=iv\nOPERATIONS\n Set(yy) = PRE yy:ID THEN xx:= yy END;\n ;r <-- Get = BEGIN r := xx END\nEND";
		try {
			getTreeAsString(s);
			fail("Missing Semicolon was not detected");
		} catch (BException e) {
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
		} catch (BException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("& VARIABLES"));
		}
	}

	@Test
	public void checkForDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES\n 1=1 & &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("[3,8]"));
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	@Test
	public void checkForCommentBetweenDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES 1=1 & /* comment */ &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	@Test
	public void checkForSingleLineCommentBetweenDuplicateAnd() throws Exception {
		String s = "MACHINE Definitions\nPROPERTIES 1=1 & // comment 1 comment \n &  2 = 2  END";
		try {
			getTreeAsString(s);
			fail("Duplicate & was not detected.");
		} catch (BException e) {
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
		} catch (BException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().contains("[3,14]"));
			assertTrue(e.getMessage().contains("& &"));
		}
	}
	
	
	private String getTreeAsString(final String testMachine) throws BException {
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
