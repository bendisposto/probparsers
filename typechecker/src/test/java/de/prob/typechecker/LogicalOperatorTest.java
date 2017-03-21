package de.prob.typechecker;

import org.junit.Test;


public class LogicalOperatorTest {

	@Test
	public void testAnd() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = 1 & 1 = 1  \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testOr() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = 1 or 1 = 1  \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testImplication() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = 1 => 1 = 1  \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testEquivalence() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES 1 = 1 <=> 1 = 1  \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testNot() throws Exception {
		String machine = "MACHINE test\n"
				+ "PROPERTIES not(1 = 1) \n"
				+ "END";
		new TestTypechecker(machine);
	}
}
