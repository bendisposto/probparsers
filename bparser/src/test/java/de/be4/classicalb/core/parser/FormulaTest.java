package de.be4.classicalb.core.parser;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;

public class FormulaTest {

	@Test
	public void testFomulaExpression() throws Exception {
		final String formula = "#FORMULA 3 + y";
		BParser.parse(formula);
	}

	@Test
	public void testFomulaPredicate() throws Exception {
		final String formula = "#FORMULA 3 = y";
		BParser.parse(formula);
	}

	@Test(expected = BCompoundException.class)
	public void testBuggyFomulaExpression() throws Exception {
		final String formula = "#FORMULA 3 + y - ";
		BParser.parse(formula);
	}

	@Test(expected = BCompoundException.class)
	public void testBuggyFomulaPredicate() throws Exception {
		final String formula = "#FORMULA 3 = ";
		BParser.parse(formula);
	}

}
