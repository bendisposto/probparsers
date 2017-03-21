package de.prob.typechecker;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FunctionsVsRelations {

	@Test
	public void testCardFunction() throws Exception {
		String machine = "MACHINE test\n"
				+ "CONSTANTS k \n"
				+ "PROPERTIES k =  %x.(x : {1} | 1) & card(k) = 1 \n"
				+ "END";
		TestTypechecker t =  new TestTypechecker(machine);
		assertEquals("FUNC(INTEGER,INTEGER)", t.constants.get("k").toString());
	}
}
