package de.be4.classicalb.core.parser.util;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Helpers;

public class PrettyPrinterTest {

	@Test
	public void testPrettyPrint() throws Exception {
		final String testMachine = "MACHINE Test VARIABLES x,y INVARIANT x : INTEGER & y : INTEGER INITIALISATION x:= 1 || y := 2 OPERATIONS foo = skip; bar = skip END";
		final String result1 = Helpers.getPrettyPrint(testMachine);
		final String result2 = Helpers.getPrettyPrint(result1);
		System.out.println(result1);
		assertEquals(result1, result2);
		
	}
}
