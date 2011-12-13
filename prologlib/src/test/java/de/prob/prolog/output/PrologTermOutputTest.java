package de.prob.prolog.output;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

public class PrologTermOutputTest {
	private IPrologTermOutput pout;
	private StringWriter swriter;

	@Before
	public void setUp() throws Exception {
		swriter = new StringWriter();
		pout = new PrologTermOutput(new PrintWriter(swriter), false);
	}

	private void assertOutput(final String expected) {
		pout.flush();
		assertEquals(expected, swriter.toString());
	}

	@Test
	public void testTerms() {
		pout.openTerm("func");
		pout.printAtom("atom");
		pout.openTerm("inner");
		pout.printNumber(4500);
		pout.printNumber(22);
		pout.closeTerm();
		pout.printAtom("atom2");
		pout.closeTerm();
		pout.fullstop();
		assertOutput("func(atom,inner(4500,22),atom2).\n");
	}

	@Test
	public void testEscape() {
		pout.printAtom("normal");
		pout.printAtom("camelStyle");
		pout.printAtom("with_underscore");
		pout.printAtom("UpperCase");
		pout.printAtom("_begin_with_underscore");
		pout.printAtom("22number");
		pout.openTerm("Functor");
		pout.printAtom("with white spaces");
		pout.closeTerm();
		assertOutput("normal,camelStyle,with_underscore,'UpperCase',"
				+ "'_begin_with_underscore','22number','Functor'('with white spaces')");
	}

	@Test
	public void testEscape2() {
		pout.printAtom("hallo\nwelt");
		pout.printAtom("back\\slash");
		pout.printAtom("\u00dcmlaute"); // U - Umlaut
		pout.printAtom(" donttrim ");
		pout.printAtom("apo'stroph");
		pout.printAtom("double\"quote");
		assertOutput("'hallo\\nwelt','back\\\\slash','\\334\\mlaute',' donttrim ',"
				+ "'apo\\'stroph','double\"quote'");
	}

	@Test
	public void testLists() {
		pout.openTerm("term");
		pout.openList();
		pout.printAtom("a");
		pout.printAtom("b");
		pout.openList();
		pout.printAtom("c");
		pout.closeList();
		pout.closeList();
		pout.openList();
		pout.closeList();
		pout.emptyList();
		pout.closeTerm();
		assertOutput("term([a,b,[c]],[],[])");
	}

	@Test
	public void testStrings() {
		pout.printString("simple");
		pout.printString("apo'stroph");
		pout.printString("double\"quote");
		assertOutput("\"simple\",\"apo'stroph\",\"double\\\"quote\"");
	}

	@Test
	public void testVariables() {
		pout.openTerm("bla");
		pout.printVariable("Var1");
		pout.printVariable("Var2WithCamel");
		pout.printVariable("Var_with_underscores");
		pout.printVariable("_beginning_with_underscore");
		pout.closeTerm();
		assertOutput("bla(Var1,Var2WithCamel,Var_with_underscores,_beginning_with_underscore)");
	}

	@Test
	public void testInvalidVariables() {
		try {
			pout.printVariable("lowerCase");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			// o.k.
		}

		try {
			pout.printVariable("Having whitespace");
			fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {
			// o.k.
		}
	}

	@Test
	public void testInvalidLists1() {
		try {
			pout.openList();
			pout.printAtom("test");
			pout.fullstop();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// ok
		}

	}

	@Test
	public void testInvalidLists2() {

		try {
			pout.closeList();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// ok
		}
	}

	@Test
	public void testInvalidTerms1() {
		try {
			pout.openTerm("test");
			pout.printAtom("test");
			pout.printAtom("test");
			pout.fullstop();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// ok
		}

	}

	@Test
	public void testInvalidTerms2() {

		try {
			pout.closeTerm();
			fail("IllegalStateException expected");
		} catch (IllegalStateException e) {
			// ok
		}
	}

	@Test
	public void testAccents() {
		pout.printAtom("h\u00e4ll\u00f3");
		assertOutput("'h\\344\\ll\\363\\'");
	}

	@Test
	public void testZeroArity() {
		pout.openTerm("test");
		pout.closeTerm();
		assertOutput("test");
	}

}
