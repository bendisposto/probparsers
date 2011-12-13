package de.be4.classicalb.core.parser;

import static de.be4.classicalb.core.parser.FastReadTransformer.ZERO;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.prolog.output.StructuredPrologOutput;

public class FastReadTransformerTest  {

	private StructuredPrologOutput spo = new StructuredPrologOutput();
	
	@Test
	public void testSingleNumber() {
		spo.printNumber(42);
		spo.fullstop();
		String expected = "DI42" + ZERO;
		check(expected);
	}


	@Test
	public void testSingleAtom1() {
		spo.openTerm("a").closeTerm();
		spo.fullstop();
		String expected = "DAa" + ZERO;
		check(expected);
	}

	@Test
	public void testSingleAtom3() {
		spo.openTerm("C").closeTerm();
		spo.fullstop();
		String expected = "DAC" + ZERO;
		check(expected);
	}

	@Test
	public void testSimpleFunctor2() {
		spo.openTerm("C").printAtom("a").closeTerm();
		spo.fullstop();
		String expected ="DSC" + ZERO+(char)1+"Aa"+ZERO; 
		check(expected);
	}

	@Test
	public void testSingleVariable() {
		spo.printVariable("Foo");
		spo.fullstop();
		String expected = "D_0" + ZERO;
		check(expected);
	}

	@Test
	public void testSimpleFunctor() { // a(b)
		spo.openTerm("a").printAtom("b").closeTerm();
		spo.fullstop();
		String expected = "DSa" + ZERO + ((char) 1) + "Ab" + ZERO;
		check(expected);
	}

	@Test
	public void testSingleAtom2() {
		spo.printAtom("a");
		spo.fullstop();
		String expected = "DAa" + ZERO;
		check(expected);
	}

	@Test
	public void testEmptyList() {
		spo.openList().closeList();
		spo.fullstop();
		String expected = "D]";
		check(expected);
	}

	@Test
	public void testOneAtomList() {
		spo.openList().printAtom("C").closeList();
		spo.fullstop();
		String expected = "D[AC" + ZERO + "]";
		check(expected);
	}
	
	private void check(String expected) {
		String actual = new FastReadTransformer(spo).write();
		assertEquals(expected, actual);
	}

}
