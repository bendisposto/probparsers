package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class StringTest {

	
	@Test
	public void testString1() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = \"abc\" \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("STRING", t.constants.get("k").toString());
	}
	
	@Test
	public void testString2() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES \"abc\" = \"abc\" \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testStringException() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = \"abc\" \n" + "END";
		new TestTypechecker(machine);
	}
}
