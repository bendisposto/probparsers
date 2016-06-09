/**
 * 
 */
package de.be4.classicalb.core.parser;

import static de.be4.classicalb.core.parser.analysis.ParseTestUtil.parseExpr;
import static de.be4.classicalb.core.parser.analysis.ParseTestUtil.parsePred;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;

/**
 * The English manual of AtelierB (version 1.8.6) has wrong priorities. This
 * test case tries to exploit differences between the correct French version and
 * the incorrect English.
 * 
 * @author plagge
 */
public class EnglishVsFrenchManual {

	@Test
	public void testImplicationVsEquivalence() throws BException {
		// <=> has 30 in the English, and 60 in the French version
		// => has 30 in both
		final String pred = "(z:g) => (x:g) <=> (y:g)";
		final String english = "((z:g) => (x:g)) <=> (y:g)";
		final String french = "(z:g) => ((x:g) <=> (y:g))";
		checkPred(pred, english, french);
	}

	@Test
	public void testEquivalenceVsAnd() throws Exception {
		// <=> has 30 in the English, and 60 in the French version
		// & has 40 in both
		final String pred = "(1=2) <=> (3=4) & (5=6)";
		final String english = "1=2 <=> (3=4 & 5=6)";
		final String french = "((1=2) <=> (3=4)) & (5=6)";
		checkPred(pred, english, french);
	}

	private void checkPred(final String pred, final String english,
			final String french) throws BException {
		final String parsedPred = parsePred(pred);
		final String parsedEnglish = parsePred(english);
		final String parsedFrench = parsePred(french);

		assertEquals(parsedFrench, parsedPred);
		assertFalse(parsedPred.equals(parsedEnglish));
	}

	@Test
	public void testOverrideExpression() throws BException {
		// <+ has 90 in the English, and 160 in the French version
		// <-> has 125 in both
		final String expr = "A <+ B <-> B";
		final String english = "A <+ (B <-> B)";
		final String french = "(A <+ B) <-> B";

		final String parsedExpr = parseExpr(expr);
		final String parsedEnglish = parseExpr(english);
		final String parsedFrench = parseExpr(french);

		assertEquals(parsedFrench, parsedExpr);
		assertFalse(parsedExpr.equals(parsedEnglish));
	}

}
