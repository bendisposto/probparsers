package de.prob.typechecker;

import org.junit.Test;

import de.prob.typechecker.exceptions.TypeErrorException;

public class TypeErrosTest {
	
	@Test (expected = TypeErrorException.class)
	public void test1() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k : k \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void test2() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES {k} : {k} \n"
				+ "END";
		new TestTypechecker(machine);
	}
	
	@Test (expected = TypeErrorException.class)
	public void test3() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES {k} : {{k}} \n"
				+ "END";
		new TestTypechecker(machine);
	}
}
