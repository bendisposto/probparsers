package de.be4.classicalb.core.parser.exceptions;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Helpers;

public class LexerExceptionTest {

	@Test
	public void testLexerException() throws Exception {
		final String result = Helpers.fullParsing("src/test/resources/exceptions/LexerStringError.mch");
		System.out.println(result);
		assertTrue(result.contains("LexerStringError.mch"));
		assertTrue(result.contains("[3,12]"));
	}
}
