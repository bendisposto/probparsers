package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;
import util.Ast2String;

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
	public void testParallelMember() throws Exception {
		final String testMachine = "#PREDICATE x : ID & y : ID";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(APredicateParseUnit(AConjunctPredicate(AMemberPredicate(AIdentifierExpression([x]),AIdentifierExpression([ID])),AMemberPredicate(AIdentifierExpression([y]),AIdentifierExpression([ID])))))",
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
				"Start(APredicateParseUnit(AConjunctPredicate(AConjunctPredicate(AMemberPredicate(AIdentifierExpression([hasread]),ARelationsExpression(AIdentifierExpression([READER]),AIdentifierExpression([BOOK]))),AMemberPredicate(AIdentifierExpression([reading]),APartialInjectionExpression(AIdentifierExpression([READER]),AIdentifierExpression([COPY])))),AEqualPredicate(AIntersectionExpression(ACompositionExpression(AIdentifierExpression([reading]),AIdentifierExpression([copyof])),AIdentifierExpression([hasread])),AEmptySetExpression()))))",
				result);
	}

	@Test
	public void testForall() throws Exception {
		final String testMachine = "#PREDICATE ! a,b. (a=b => a/=b )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AForallPredicate([AIdentifierExpression([a]),AIdentifierExpression([b])],AImplicationPredicate(AEqualPredicate(AIdentifierExpression([a]),AIdentifierExpression([b])),ANotEqualPredicate(AIdentifierExpression([a]),AIdentifierExpression([b]))))))",
				result);
	}

	@Test
	public void testForallCouple1() throws Exception {
		final String testMachine = "#PREDICATE ! (a,b). (1=1 )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AForallPredicate([AIdentifierExpression([a]),AIdentifierExpression([b])],AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)))))",
				result);
	}

	@Test(expected = BCompoundException.class)
	public void testTooManyparentheses() throws Exception {
		final String testMachine = "#PREDICATE # ((b,c,d)). ( b > c)";
		getTreeAsString(testMachine);
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
				"Start(APredicateParseUnit(ASubsetPredicate(ADomainExpression(ACompositionExpression(AIdentifierExpression([ff]),AReverseExpression(AIdentifierExpression([gg])))),ADomainExpression(AIdentifierExpression([ff])))))",
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
	public void testEqualVsImplicationFormula() throws Exception {
		final String testMachine = "#FORMULA 1=2 <=> 3=4 => 5=6";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AImplicationPredicate(AEquivalencePredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(2)),AEqualPredicate(AIntegerExpression(3),AIntegerExpression(4))),AEqualPredicate(AIntegerExpression(5),AIntegerExpression(6)))))",
				result);
	}

	@Test
	public void testBFalse() throws BCompoundException {
// we now allow bfalse always
// 		parser.getOptions().setRestrictProverExpressions(true);
// 		try {
// 			getPredicateAsString("bfalse");
// 			fail("exception expected");
// 		} catch (BCompoundException e) {
// 			assertTrue(e.getFirstException().getCause() instanceof CheckException);
// 		}
// 
// 		parser.getOptions().setRestrictProverExpressions(false);
		final String actual = getPredicateAsString("bfalse");
		final String expected = "AFalsityPredicate()";
		assertEquals(expected, actual);
	}

	@Test
	public void testBTrue() throws BCompoundException {
		final String actual = getPredicateAsString("btrue");
		final String expected = "ATruthPredicate()";
		assertEquals(expected, actual);
	}

	@Test
	public void testNonIdentifiersInQuantification() {
		final String testMachine = "#PREDICATE ! a,5. (a=5 => a/=5 )";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception");
		} catch (final BCompoundException e) {
			assertTrue(e.getFirstException().getCause() instanceof BParseException);
		}
	}

	@Test
	public void testNonIdentifiersInQuantificationFormula() {
		final String testMachine = "#FORMULA ! a,5. (a=5 => a/=5 )";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception");
		} catch (final BCompoundException e) {
			assertTrue(e.getFirstException().getCause() instanceof BParseException);
		}
	}

	@Test
	public void testSubstitutionInPredicate() throws BCompoundException {
		final String testMachine = "#PREDICATE (a>5) & [b:=a](b<10)";
		parser.getOptions().setRestrictProverExpressions(false);
		final String astString = getTreeAsString(testMachine);
		assertEquals(
				"Start(APredicateParseUnit(AConjunctPredicate(AGreaterPredicate(AIdentifierExpression([a]),AIntegerExpression(5)),ASubstitutionPredicate(AAssignSubstitution([AIdentifierExpression([b])],[AIdentifierExpression([a])])ALessPredicate(AIdentifierExpression([b]),AIntegerExpression(10))))))",
				astString);
	}

	@Test
	public void testNoPredicateSubstitutionsInNormalMode() {
		final String testMachine = "#PREDICATE ! a,5. (a=5 => a/=5 )";
		try {
			getTreeAsString(testMachine);
			fail("Expected exception");
		} catch (final BCompoundException e) {
			assertTrue(e.getFirstException().getCause() instanceof BParseException);
		}
	}

	private String getPredicateAsString(final String expression) throws BCompoundException {
		final String machine = "#PREDICATE " + expression;
		final String astString = getTreeAsString(machine);
		assertTrue(astString.startsWith(START_PREDICATE));
		assertTrue(astString.endsWith(END_PREDICATE));
		return astString.substring(START_PREDICATE.length(), astString.length() - END_PREDICATE.length());
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}
}
