package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class SetsClauseTest {

	
	@Test
	public void testDeferredSet() throws Exception {
		String machine = "MACHINE test\n" 
				+ "SETS DEF \n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k : DEF \n" 
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("DEF", t.constants.get("k").toString());
	}
	
	@Ignore
	@Test
	public void testDeferredSet2() throws Exception {
		String machine = "MACHINE test(DEF)\n" 
				+ "CONSTANTS k \n"
				+ "PROPERTIES k : DEF \n" 
				+ "END";
		TestTypechecker t = new TestTypechecker(machine);
		assertEquals("DEF", t.constants.get("k").toString());
	}
	
}
