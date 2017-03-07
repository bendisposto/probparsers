package de.be4.classicalb.core.parser.prolog;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import util.Helpers;

public class StringTest {

	@Test
	public void testFile() throws IOException, BException {
		String file = "src/test/resources/strings/StringIncludingQuotes.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("'a\"b'"));
	}

	@Test
	public void testString() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES \"a\\\"b\" = \"a\" END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		assertTrue(result.contains("'a\"b'"));
	}


	@Test
	public void testMultiLineString() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES k = ''' adfa \"a\" ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("' adfa \"a\" '"));
	}
	

	@Test
	public void testMultiLineString2() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES k = ''' adfa \"a ''' END";
		System.out.println(testMachine);
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("' adfa \"a '"));
	}
}
