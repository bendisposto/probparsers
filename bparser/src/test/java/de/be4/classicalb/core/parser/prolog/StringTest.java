package de.be4.classicalb.core.parser.prolog;

import static org.junit.Assert.*;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import util.Helpers;

public class StringTest {

	@Test
	public void testFile() {
		String file = "src/test/resources/strings/StringIncludingQuotes.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("'a\"b'"));
	}

	@Test
	public void testString() {
		final String testMachine = "MACHINE Test PROPERTIES \"a\\\"b\" = \"a\" END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		assertTrue(result.contains("'a\"b'"));
	}

	@Test
	public void testNewlineInSingleLineString() {
		final String testMachine = "MACHINE Test PROPERTIES k = \" \n \" END";
		System.out.println(testMachine);
		try {
			Helpers.getMachineAsPrologTerm(testMachine);
			fail("Should raise a parser exception");
		} catch (RuntimeException e) {
			BCompoundException cause = (BCompoundException) e.getCause();
			String message = cause.getFirstException().getMessage();
			assertTrue(message.contains("Unknown token: \""));
			System.out.println(message);
		}
	}

	@Test
	public void testDoubleBackslash() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \\ ''' = ''' \\\\ ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("[properties(4,equal(5,string(6,' \\\\ '),string(7,' \\\\ ')))]"));
	}

	@Test
	public void testNewline() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \\n ''' = ''' \n ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("[properties(4,equal(5,string(6,' \\n '),string(7,' \\n ')))]"));
	}

	@Test
	public void testTab() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \\t ''' = ''' \t ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("string(6,' \\11\\ '),string(7,' \\11\\ '))"));
	}

	@Test
	public void testCarriageReturn() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \\r ''' = ''' \r ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("equal(5,string(6,' \\15\\ '),string(7,' \\15\\ '))"));
	}

	@Test
	public void testSignleQuote() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \' ''' = ''' ' ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("equal(5,string(6,' \\' '),string(7,' \\' '))"));
	}

	@Test
	public void testDoubleQuote() {
		final String testMachine = "MACHINE Test PROPERTIES ''' \\\" ''' = ''' \" ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("equal(5,string(6,' \" '),string(7,' \" '))"));
	}

	@Test
	public void testMultiLineString() {
		final String testMachine = "MACHINE Test PROPERTIES k = ''' adfa \"a\" ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("' adfa \"a\" '"));
	}

	@Test
	public void testMultiLineString2() {
		final String testMachine = "MACHINE Test PROPERTIES k = ''' adfa \"a ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("' adfa \"a '"));
	}
}
