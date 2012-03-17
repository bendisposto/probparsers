package de.be4.classicalb.core.parser.analysis.pragma.internal;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnknownPragmaTest {

	@Test
	public void test1() {
		UnknownPragma p = new UnknownPragma(new RawPragma(null, null, "label one two"), null, null, null, null, null);
		assertEquals("label", p.getPragmaName());
		assertEquals("one", p.getPragmaArguments().get(0));
		assertEquals("two", p.getPragmaArguments().get(1));
	}

	@Test
	public void test2() {
		UnknownPragma p = new UnknownPragma(new RawPragma(null, null, "   label        one   two"), null, null, null, null, null);
		assertEquals("label", p.getPragmaName());
		assertEquals("one", p.getPragmaArguments().get(0));
		assertEquals("two", p.getPragmaArguments().get(1));
	}

	@Test
	public void test3() {
		UnknownPragma p = new UnknownPragma(new RawPragma(null, null, "   label        one   two                 "), null, null, null, null, null);
		assertEquals("label", p.getPragmaName());
		assertEquals("one", p.getPragmaArguments().get(0));
		assertEquals("two", p.getPragmaArguments().get(1));
	}
	
	@Test
	public void test4() {
		UnknownPragma p = new UnknownPragma(new RawPragma(null, null, "   label     "), null, null, null, null, null);
		assertEquals("label", p.getPragmaName());
		assertTrue( p.getPragmaArguments().isEmpty());
	}

	@Test
	public void test5() {
		UnknownPragma p = new UnknownPragma(new RawPragma(null, null, "         "), null, null, null, null, null);
		assertEquals("empty", p.getPragmaName());
		assertTrue( p.getPragmaArguments().isEmpty());
	}

}
