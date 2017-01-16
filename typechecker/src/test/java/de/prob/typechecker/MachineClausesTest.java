package de.prob.typechecker;

import static org.junit.Assert.*;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class MachineClausesTest {

	@Test(expected = TypeErrorException.class)
	public void testUntypedConstant() throws Exception {
		String machine = "MACHINE test \n" + "CONSTANTS k\n"
				+ "PROPERTIES 1 = 1 \n" + "END";
		new TestTypechecker(machine);
	}

	@Test(expected = TypeErrorException.class)
	public void testUntypedVariable() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT 1 = 1 \n" + "INITIALISATION x := 1 \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testVariable() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT x  = 1 \n" + "INITIALISATION x := 1 \n" + "END";
		TestTypechecker t  = new TestTypechecker(machine);
		assertEquals("INTEGER", t.variables.get("x").toString());
	}

	@Test(expected = TypeErrorException.class)
	public void testUntypedParameter() throws Exception {
		String machine = "MACHINE test(a) \n" + "CONSTRAINTS 1 = 1 \n" + "END";
		new TestTypechecker(machine);
	}

	@Test
	public void testScalarParameter() throws Exception {
		String machine = "MACHINE test(a) \n" + "CONSTRAINTS a = 1 \n" + "END";
		TestTypechecker t  = new TestTypechecker(machine);
		assertEquals("INTEGER", t.parameters.get("a").toString());
	}

	@Test
	public void testParameter() throws Exception {
		String machine = "MACHINE test(A) \n" + "END";
		TestTypechecker t  = new TestTypechecker(machine);
		assertEquals("POW(A)", t.parameters.get("A").toString());
	}


	@Test (expected = TypeErrorException.class)
	public void testInitialisationError() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT x  = 1 \n" + "INITIALISATION x := TRUE \n" + "END";
		new TestTypechecker(machine);
	}
	
	@Test
	public void testInitialisation() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT x  = 1 \n" + "INITIALISATION x := 1 \n" + "END";
		new TestTypechecker(machine);
	}
	
	
	@Test
	public void testOperations() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT x  = 1 \n" + "INITIALISATION x := 1 \n" 
				+ "OPERATIONS foo = PRE 1 = 1 THEN x := 1 END \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testOperationsError() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x\n"
				+ "INVARIANT x  = 1 \n" + "INITIALISATION x := 1 \n" 
				+ "OPERATIONS foo = PRE 1 = TRUE THEN x := 1 END \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void testSubstitution() throws Exception {
		String machine = "MACHINE test \n" + "VARIABLES x,y \n"
				+ "INVARIANT x  = 1 & y = 1 \n" + "INITIALISATION x,y := 1,TRUE \n" 
				+ "END";
		new TestTypechecker(machine);
	}
	

}
