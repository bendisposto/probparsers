package de.be4.classicalb.core.parser.exceptions;

import org.junit.Test;

import util.Helpers;

public class TestMultipleExceptions {

	@Test
	public void testMultipleErrors() throws Exception {
		final String testMachine = "./src/test/resources/exceptions/MultipleErrors.mch";
		String result = Helpers.fullParsing(testMachine);
		System.out.println(result);
	}
}
