package de.be4.classicalb.core.parser;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.prolog.output.StructuredPrologOutput;

public class FastReadTransformerComplexTest  {

	private static final char O = (char) 1;
	private static final char Z = (char) 0;
	private StructuredPrologOutput
		spo = new StructuredPrologOutput();
	
	@Test
	public void testWrite() { // a(['G',f([]),[[w]]]).
		spo.openTerm("a").openList();
		spo.printAtom("G");
		spo.openTerm("f").openList().closeList().closeTerm();
		spo.openList().openList().printAtom("w").closeList().closeList();
		spo.closeList().closeTerm();
		spo.fullstop();
		String expected = "DSa" + Z + O + "[AG" + Z + "[Sf" + Z +O+ "][[[Aw" + Z
				+ "]]]";
		check(expected);
	}
	private void check(String expected) {
		String actual = new FastReadTransformer(spo).write();
		assertEquals(expected, actual);
	}
}
