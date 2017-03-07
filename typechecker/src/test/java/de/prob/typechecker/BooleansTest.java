package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class BooleansTest {
	
	@Test
	public void testTrue() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = TRUE \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("BOOL", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testTrueException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES 1 = TRUE \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testFalse() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = FALSE \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("BOOL", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testFalseException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES 1 = FALSE \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testBool() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = BOOL \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("POW(BOOL)", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testBoolException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES 1 = BOOL \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testConvertPredicate() throws Exception {
		String machine = "MACHINE test\n" + "CONSTANTS k \n"
				+ "PROPERTIES k = bool(1=1) \n" + "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("BOOL", t.constants.get("k").toString());
	}
	
	@Test (expected = TypeErrorException.class)
	public void testConvertPredicateException() throws Exception {
		String machine = "MACHINE test\n" 
				+ "PROPERTIES 1 = bool(1=1) \n" + "END";
		new TestTypechecker(machine);
	}
	
	
}
