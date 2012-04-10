package de.prob.prolog.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PrologTermStringOutputTest {

	private void assertOutput(final IPrologTermOutput ptso,
			final String expected) {
		assertEquals(expected, ptso.toString());
	}

	@Test
	public void testTerms() {
		IPrologTermOutput ptso = new PrologTermStringOutput().openTerm("func")
				.printAtom("atom").openTerm("inner").printNumber(4500)
				.printNumber(22).closeTerm().printAtom("atom2").closeTerm()
				.fullstop();
		assertEquals("func(atom,inner(4500,22),atom2).", ptso.toString().trim());
	}

	@Test
	public void testEscape() {
		IPrologTermOutput ptso = new PrologTermStringOutput().printAtom(
				"normal").printAtom("camelStyle").printAtom("with_underscore")
				.printAtom("UpperCase").printAtom("_begin_with_underscore")
				.printAtom("22number").openTerm("Functor").printAtom(
						"with white spaces").closeTerm();
		assertOutput(
				ptso,
				"normal,camelStyle,with_underscore,'UpperCase',"
						+ "'_begin_with_underscore','22number','Functor'('with white spaces')");
	}

	@Test
	public void testEscape2() {
		IPrologTermOutput ptso = new PrologTermStringOutput().printAtom(
				"hallo\nwelt").printAtom("back\\slash").printAtom(
				"\u00dcmlaute").printAtom(" donttrim ").printAtom("apo'stroph")
				.printAtom("double\"quote");
		assertOutput(ptso,
				"'hallo\\nwelt','back\\\\slash','\\334\\mlaute',' donttrim ',"
						+ "'apo\\'stroph','double\"quote'");
	}

	@Test
	public void testLists() {
		IPrologTermOutput ptso = new PrologTermStringOutput().openTerm("term")
				.openList().printAtom("a").printAtom("b").openList().printAtom(
						"c").closeList().closeList().openList().closeList()
				.closeTerm();
		assertOutput(ptso, "term([a,b,[c]],[])");
	}

	@Test
	public void testStrings() {
		IPrologTermOutput ptso = new PrologTermStringOutput().printString(
				"simple").printString("apo'stroph")
				.printString("double\"quote");
		assertOutput(ptso, "\"simple\",\"apo'stroph\",\"double\\\"quote\"");
	}

	@Test
	public void testVariables() {
		IPrologTermOutput ptso = new PrologTermStringOutput().openTerm("bla")
				.printVariable("Var1").printVariable("Var2WithCamel")
				.printVariable("Var_with_underscores").printVariable(
						"_beginning_with_underscore").closeTerm();
		assertOutput(ptso,
				"bla(Var1,Var2WithCamel,Var_with_underscores,_beginning_with_underscore)");
	}

	@Test
	public void testInvalidVariables() {

		try {
			new PrologTermStringOutput().printVariable("lowerCase");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			// o.k.
		}

		try {
			new PrologTermStringOutput().printVariable("Having whitespace");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			// o.k.
		}
	}

	@Test
	public void testZeroArity() {
		IPrologTermOutput ptso = new PrologTermStringOutput().openTerm("test")
				.closeTerm();
		assertOutput(ptso, "test");
	}
}
