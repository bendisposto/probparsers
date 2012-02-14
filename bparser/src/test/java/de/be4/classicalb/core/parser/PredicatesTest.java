package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;

public class PredicatesTest {

	private static final String START_PREDICATE = "Start(APredicateParseUnit(";
	private static final String END_PREDICATE = "))";

	private BParser parser = new BParser("testcase");

	@Test
	public void testAndOrPrio() throws Exception {
		final String testMachine1 = "#PREDICATE 1=1 or 1=1 & 1=2";
		final String result1 = getTreeAsString(testMachine1);
		final String testMachine2 = "#PREDICATE (1=1 or 1=1) & 1=2";
		final String result2 = getTreeAsString(testMachine2);

		assertEquals(result1, result2);
	}

	@Test
	public void testParallelBelongs() throws Exception {
		final String testMachine = "#PREDICATE x : ID & y : ID";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(APredicateParseUnit(AConjunctPredicate(ABelongPredicate(AIdentifierExpression([x]),AIdentifierExpression([ID])),ABelongPredicate(AIdentifierExpression([y]),AIdentifierExpression([ID])))))",
				result);
	}

	@Test
	public void testParallelBelongs2() {
		final String testMachine = "#PREDICATE x,y : ID";
		try {
			getTreeAsString(testMachine);
			fail("Expected ParseException missing");
		} catch (final Exception e) {
			// IGNORE, is expected
		}
	}

	@Test
	public void testInvariant1() throws Exception {
		final String testMachine = "#PREDICATE hasread : READER <-> BOOK & reading : READER >+> COPY & (reading ; copyof) /\\ hasread = {}";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(APredicateParseUnit(AConjunctPredicate(AConjunctPredicate(ABelongPredicate(AIdentifierExpression([hasread]),ARelationsExpression(AIdentifierExpression([READER]),AIdentifierExpression([BOOK]))),ABelongPredicate(AIdentifierExpression([reading]),APartialInjectionExpression(AIdentifierExpression([READER]),AIdentifierExpression([COPY])))),AEqualPredicate(AIntersectionExpression(ACompositionExpression(AIdentifierExpression([reading]),AIdentifierExpression([copyof])),AIdentifierExpression([hasread])),AEmptySetExpression()))))",
				result);
	}

	@Test
	public void testUniversalQuantification() throws Exception {
		final String testMachine = "#PREDICATE ! a,b. (a=b => a/=b )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AUniversalQuantificationPredicate([AIdentifierExpression([a]),AIdentifierExpression([b])],AImplicationPredicate(AEqualPredicate(AIdentifierExpression([a]),AIdentifierExpression([b])),AUnequalPredicate(AIdentifierExpression([a]),AIdentifierExpression([b]))))))",
				result);
	}

	@Test
	public void testUniversalQuantificationCouple1() throws Exception {
		final String testMachine = "#PREDICATE ! (a,b). (a=b => a/=b )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AUniversalQuantificationPredicate([AIdentifierExpression([a]),AIdentifierExpression([b])],AImplicationPredicate(AEqualPredicate(AIdentifierExpression([a]),AIdentifierExpression([b])),AUnequalPredicate(AIdentifierExpression([a]),AIdentifierExpression([b]))))))",
				result);
	}

	@Test
	public void testUniversalQuantificationCouple2() throws Exception {
		final String testMachine = "#PREDICATE !(row,minrow,col,col2,mincol,mincol2).\n    (row:INDEX & minrow:INDEX & col:INDEX & col2:INDEX & mincol:INDEX & mincol2:INDEX &\n      (col /=col2 or mincol /= mincol2) => \n        (Squares(row,col)(minrow,mincol) /= Squares(row,col2)(minrow,mincol2))\n   )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AUniversalQuantificationPredicate([AIdentifierExpression([row]),AIdentifierExpression([minrow]),AIdentifierExpression([col]),AIdentifierExpression([col2]),AIdentifierExpression([mincol]),AIdentifierExpression([mincol2])],AImplicationPredicate(AConjunctPredicate(AConjunctPredicate(AConjunctPredicate(AConjunctPredicate(AConjunctPredicate(AConjunctPredicate(ABelongPredicate(AIdentifierExpression([row]),AIdentifierExpression([INDEX])),ABelongPredicate(AIdentifierExpression([minrow]),AIdentifierExpression([INDEX]))),ABelongPredicate(AIdentifierExpression([col]),AIdentifierExpression([INDEX]))),ABelongPredicate(AIdentifierExpression([col2]),AIdentifierExpression([INDEX]))),ABelongPredicate(AIdentifierExpression([mincol]),AIdentifierExpression([INDEX]))),ABelongPredicate(AIdentifierExpression([mincol2]),AIdentifierExpression([INDEX]))),ADisjunctPredicate(AUnequalPredicate(AIdentifierExpression([col]),AIdentifierExpression([col2])),AUnequalPredicate(AIdentifierExpression([mincol]),AIdentifierExpression([mincol2])))),AUnequalPredicate(AFunctionExpression(AFunctionExpression(AIdentifierExpression([Squares]),[AIdentifierExpression([row]),AIdentifierExpression([col])]),[AIdentifierExpression([minrow]),AIdentifierExpression([mincol])]),AFunctionExpression(AFunctionExpression(AIdentifierExpression([Squares]),[AIdentifierExpression([row]),AIdentifierExpression([col2])]),[AIdentifierExpression([minrow]),AIdentifierExpression([mincol2])]))))))",
				result);
	}

	@Test
	public void testCoupleInExistentialQuantification1() throws Exception {
		final String testMachine = "#PREDICATE # ((b,c,d)). ( b > c)";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AExistentialQuantificationPredicate([AIdentifierExpression([b]),AIdentifierExpression([c]),AIdentifierExpression([d])],AGreaterPredicate(AIdentifierExpression([b]),AIdentifierExpression([c])))))",
				result);
	}

	@Test
	public void testCoupleInUniversalQuantification1() throws Exception {
		final String testMachine = "#PREDICATE ! ((b,c,d)). ( b > c)";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AUniversalQuantificationPredicate([AIdentifierExpression([b]),AIdentifierExpression([c]),AIdentifierExpression([d])],AGreaterPredicate(AIdentifierExpression([b]),AIdentifierExpression([c])))))",
				result);
	}

	@Test
	public void testExampleThesis1() throws Exception {
		final String testMachine = "#PREDICATE 4 < 5 & 6 >= 7";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AConjunctPredicate(ALessPredicate(AIntegerExpression(4),AIntegerExpression(5)),AGreaterEqualPredicate(AIntegerExpression(6),AIntegerExpression(7)))))",
				result);
	}

	@Test
	public void testMultiCompositions() throws Exception {
		final String testMachine = "#PREDICATE (p~;F;p) = G";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AEqualPredicate(ACompositionExpression(ACompositionExpression(AReverseExpression(AIdentifierExpression([p])),AIdentifierExpression([F])),AIdentifierExpression([p])),AIdentifierExpression([G]))))",
				result);
	}

	@Test
	public void testWithComposition() throws Exception {
		final String testMachine = "#PREDICATE (dom(ff ; (gg~)) <: dom(ff))";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AIncludePredicate(ADomainExpression(ACompositionExpression(AIdentifierExpression([ff]),AReverseExpression(AIdentifierExpression([gg])))),ADomainExpression(AIdentifierExpression([ff])))))",
				result);
	}

	@Test
	public void testEqualVsImplication() throws Exception {
		final String testMachine = "#PREDICATE 1=2 <=> 3=4 => 5=6";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AImplicationPredicate(AEquivalencePredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(2)),AEqualPredicate(AIntegerExpression(3),AIntegerExpression(4))),AEqualPredicate(AIntegerExpression(5),AIntegerExpression(6)))))",
				result);
	}

	@Test
	public void testBFalse() throws BException {
		parser.getOptions().restrictProverExpressions = true;
		try {
			getPredicateAsString("bfalse");
			fail("exception expected");
		} catch (BException e) {
			assertTrue(e.getCause() instanceof CheckException);
		}

		parser.getOptions().restrictProverExpressions = false;
		final String actual = getPredicateAsString("bfalse");
		final String expected = "AFalsePredicate()";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonIdentifiersInQuantification() {
		final String testMachine = "#PREDICATE ! a,5. (a=5 => a/=5 )";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception");
		} catch (final BException e) {
			final CheckException cause = (CheckException) e.getCause();
			assertEquals(1, cause.getNodes().length);
			assertNotNull(cause.getNodes()[0]);
		}
	}

	@Test
	public void testSubstitutionInPredicate() throws BException {
		final String testMachine = "#PREDICATE (a>5) & [b:=a](b<10)";
		parser.getOptions().restrictProverExpressions = false;
        final String astString = getTreeAsString(testMachine);
        assertEquals("Start(APredicateParseUnit(AConjunctPredicate(AGreaterPredicate(AIdentifierExpression([a]),AIntegerExpression(5)),ASubstitutionPredicate(AAssignSubstitution([AIdentifierExpression([b])],[AIdentifierExpression([a])])ALessPredicate(AIdentifierExpression([b]),AIntegerExpression(10))))))", astString);
	}

	@Test
	public void testNoPredicateSubstitutionsInNormalMode() {
		final String testMachine = "#PREDICATE ! a,5. (a=5 => a/=5 )";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception");
		} catch (final BException e) {
			final CheckException cause = (CheckException) e.getCause();
			assertEquals(1, cause.getNodes().length);
			assertNotNull(cause.getNodes()[0]);
		}
	}
	
	private String getPredicateAsString(final String expression)
			throws BException {
		final String machine = "#PREDICATE " + expression;
		final String astString = getTreeAsString(machine);
		assertTrue(astString.startsWith(START_PREDICATE));
		assertTrue(astString.endsWith(END_PREDICATE));
		return astString.substring(START_PREDICATE.length(), astString.length()
				- END_PREDICATE.length());
	}

	private String getTreeAsString(final String testMachine) throws BException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}
}
