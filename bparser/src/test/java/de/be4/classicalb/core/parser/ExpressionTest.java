package de.be4.classicalb.core.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;
import util.Ast2String;

public class ExpressionTest {

	private static final String START_EXPRESSION = "Start(AExpressionParseUnit(";
	private static final String END_EXPRESSION = "))";

	private BParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@Test
	public void testPower1() throws Exception {
		final String testMachine = "#EXPRESSION 2**3**4";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(APowerOfExpression(AIntegerExpression(2),APowerOfExpression(AIntegerExpression(3),AIntegerExpression(4)))))",
				result);
	}

	@Test
	public void testPower2() throws Exception {
		final String testMachine = "#EXPRESSION 2**3~";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(APowerOfExpression(AIntegerExpression(2),AReverseExpression(AIntegerExpression(3)))))",
				result);
	}

	@Test
	public void testPred1() throws Exception {
		final String testMachine = "#EXPRESSION pred(x)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AFunctionExpression(APredecessorExpression(),[AIdentifierExpression([x])])))",
				result);
	}

	@Test
	public void testPred2() throws Exception {
		final String testMachine = "#EXPRESSION pred";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AExpressionParseUnit(APredecessorExpression()))", result);
	}

	@Test
	public void testSucc1() throws Exception {
		final String testMachine = "#EXPRESSION succ(x)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AFunctionExpression(ASuccessorExpression(),[AIdentifierExpression([x])])))",
				result);
	}

	@Test
	public void testSucc2() throws Exception {
		final String testMachine = "#EXPRESSION succ";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AExpressionParseUnit(ASuccessorExpression()))", result);
	}

	@Test
	public void testAddExpression() throws Exception {
		final String testMachine = "#EXPRESSION xx.yy + 5";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AAddExpression(AIdentifierExpression([xx,yy]),AIntegerExpression(5))))",
				result);
	}

	@Test
	public void testSubExpression() throws Exception {
		final String testMachine = "#EXPRESSION 3 - 5";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AMinusOrSetSubtractExpression(AIntegerExpression(3),AIntegerExpression(5))))",
				result);
	}

	@Test
	public void testCoupleExpression() throws Exception {
		final String testMachine = "#EXPRESSION (1, aa)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ACoupleExpression([AIntegerExpression(1),AIdentifierExpression([aa])])))",
				result);
	}

	@Test
	public void testCoupleExpression2() throws Exception {
		final String testMachine = "#EXPRESSION (1, aa, bb)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ACoupleExpression([AIntegerExpression(1),AIdentifierExpression([aa]),AIdentifierExpression([bb])])))",
				result);
	}

	@Test
	public void testQuantifiedUnionExpression() throws Exception {
		final String testMachine = "#EXPRESSION UNION x.y.(x=0 | x )";
		try {
			getTreeAsString(testMachine);
			fail("Invalid renaming of identifier not detected");
		} catch (BCompoundException e) {
		}

	}

	@Test
	public void testQuantifiedUnionExpression2() throws Exception {

		final String testMachine = "#EXPRESSION UNION x,y . (x=0 & y=x | (x,y) )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AExpressionParseUnit(AQuantifiedUnionExpression([AIdentifierExpression([x]),AIdentifierExpression([y])],AConjunctPredicate(AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(0)),AEqualPredicate(AIdentifierExpression([y]),AIdentifierExpression([x]))),ACoupleExpression([AIdentifierExpression([x]),AIdentifierExpression([y])]))))",
				result);
	}

	@Test
	public void testLambdaExpression() throws Exception {
		final String testMachine = "#EXPRESSION % x . (x=0 | x )";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AExpressionParseUnit(ALambdaExpression([AIdentifierExpression([x])],AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(0)),AIdentifierExpression([x]))))",
				result);
	}

	@Test(expected = BCompoundException.class)
	public void testLambdaExpression2() throws Exception {
		final String testMachine = "#EXPRESSION % x.y.z.(x.y.z=0 | x.y.z )";
		getTreeAsString(testMachine);
	}

	@Test
	public void testTotalRelationExpression() throws Exception {
		final String testMachine = "#EXPRESSION A <<-> B";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ATotalRelationExpression(AIdentifierExpression([A]),AIdentifierExpression([B]))))",
				result);
	}

	@Test
	public void testSurjectionRelationExpression() throws Exception {
		final String testMachine = "#EXPRESSION A <->> B";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ASurjectionRelationExpression(AIdentifierExpression([A]),AIdentifierExpression([B]))))",
				result);
	}

	@Test
	public void testTotalSurjectionRelationExpression() throws Exception {
		final String testMachine = "#EXPRESSION A <<->> B";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ATotalSurjectionRelationExpression(AIdentifierExpression([A]),AIdentifierExpression([B]))))",
				result);
	}

	@Test
	public void testFunction1() throws Exception {
		final String testMachine = "#EXPRESSION queues(co)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AFunctionExpression(AIdentifierExpression([queues]),[AIdentifierExpression([co])])))",
				result);
	}

	@Test
	public void testFunction2() throws Exception {
		final String testMachine = "#EXPRESSION (queues(co))(ii)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AFunctionExpression(AFunctionExpression(AIdentifierExpression([queues]),[AIdentifierExpression([co])]),[AIdentifierExpression([ii])])))",
				result);
	}

	@Test
	public void testString1() throws Exception {
		final String testMachine = "#EXPRESSION \"Hello World\"";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AExpressionParseUnit(AStringExpression(Hello World)))", result);
	}

	@Test
	public void testString2() throws Exception {
		final String testMachine = "#SUBSTITUTION text:=\"Hello World\"";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(ASubstitutionParseUnit(AAssignSubstitution([AIdentifierExpression([text])],[AStringExpression(Hello World)])))",
				result);
	}

	@Test
	public void testEmptySequence1() throws Exception {
		final String testMachine = "#EXPRESSION [ ]";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AExpressionParseUnit(AEmptySequenceExpression()))", result);
	}

	@Test
	public void testEmptySequence2() throws Exception {
		final String testMachine1 = "#EXPRESSION < >";
		final String testMachine2 = "#EXPRESSION <>";
		final String testMachine3 = "#EXPRESSION []";
		final String result1 = getTreeAsString(testMachine1);
		final String result2 = getTreeAsString(testMachine2);
		final String result3 = getTreeAsString(testMachine3);

		assertEquals("Start(AExpressionParseUnit(AEmptySequenceExpression()))", result1);
		assertEquals(result1, result2);
		assertEquals(result1, result3);
		assertEquals(result2, result3);
	}

	@Test
	public void testImage1() throws Exception {
		final String testMachine = "#EXPRESSION sex~[{woman}] - dom(husband)";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AMinusOrSetSubtractExpression(AImageExpression(AReverseExpression(AIdentifierExpression([sex])),ASetExtensionExpression([AIdentifierExpression([woman])])),ADomainExpression(AIdentifierExpression([husband])))))",
				result);
	}

	@Test
	public void testImage2() throws Exception {
		final String testMachine = "#EXPRESSION {a |-> b}[{a}]";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AImageExpression(ASetExtensionExpression([ACoupleExpression([AIdentifierExpression([a]),AIdentifierExpression([b])])]),ASetExtensionExpression([AIdentifierExpression([a])]))))",
				result);
	}

	@Test
	public void testImage3() throws Exception {
		final String testMachine = "#EXPRESSION {a |-> b, c |-> d} - {c |-> d}[{a}] - {b}";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AMinusOrSetSubtractExpression(AMinusOrSetSubtractExpression(ASetExtensionExpression([ACoupleExpression([AIdentifierExpression([a]),AIdentifierExpression([b])]),ACoupleExpression([AIdentifierExpression([c]),AIdentifierExpression([d])])]),AImageExpression(ASetExtensionExpression([ACoupleExpression([AIdentifierExpression([c]),AIdentifierExpression([d])])]),ASetExtensionExpression([AIdentifierExpression([a])]))),ASetExtensionExpression([AIdentifierExpression([b])]))))",
				result);
	}

	@Test
	public void testIntervalMinus() throws Exception {
		final String testMachine = "#EXPRESSION 1..5-1";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AIntervalExpression(AIntegerExpression(1),AMinusOrSetSubtractExpression(AIntegerExpression(5),AIntegerExpression(1)))))",
				result);
	}

	@Test
	public void testCoupleMinus() throws Exception {
		final String testMachine = "#EXPRESSION 1 |-> 5-1";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(ACoupleExpression([AIntegerExpression(1),AMinusOrSetSubtractExpression(AIntegerExpression(5),AIntegerExpression(1))])))",
				result);
	}

	@Test
	public void testPlusMinus() throws Exception {
		final String testMachine = "#EXPRESSION 1 + 5 - 3";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AMinusOrSetSubtractExpression(AAddExpression(AIntegerExpression(1),AIntegerExpression(5)),AIntegerExpression(3))))",
				result);
	}

	@Test
	public void testUnionMinus() throws Exception {
		final String testMachine = "#EXPRESSION s1 - {x} \\/ {y}";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AExpressionParseUnit(AUnionExpression(AMinusOrSetSubtractExpression(AIdentifierExpression([s1]),ASetExtensionExpression([AIdentifierExpression([x])])),ASetExtensionExpression([AIdentifierExpression([y])]))))",
				result);
	}

	@Test
	public void testConcat() throws Exception {
		final String result = getExpressionAsString("s^t");
		assertEquals("AConcatExpression(AIdentifierExpression([s]),AIdentifierExpression([t]))", result);
	}

	@Test
	public void testString() throws Exception {
		final String result = getExpressionAsString("\"test\"");
		assertEquals("AStringExpression(test)", result);
	}

	@Test
	public void testEmptyString() throws Exception {
		final String result = getExpressionAsString("\"\"");
		assertEquals("AStringExpression()", result);
	}

	@Test
	public void testComprehensionSets() throws BCompoundException {
		final String expected = "AComprehensionSetExpression([AIdentifierExpression([i])],AGreaterPredicate(AIdentifierExpression([i]),AIntegerExpression(0)))";
		final String standard = getExpressionAsString("{i|i>0}");
		assertEquals(expected, standard);
	}

	@Test
	public void testProverComprehensionSets() throws Exception {
		final String expression = "SET(i).(i>0)";

		parser.getOptions().restrictProverExpressions = false;
		final String expected = "AProverComprehensionSetExpression([AIdentifierExpression([i])],AGreaterPredicate(AIdentifierExpression([i]),AIntegerExpression(0)))";
		final String prover = getExpressionAsString(expression);
		assertEquals(expected, prover);

		parser.getOptions().restrictProverExpressions = true;
		try {
			getExpressionAsString(expression);
			fail("exception expected");
		} catch (BCompoundException e) {
			assertTrue(e.getCause() instanceof CheckException);
		}
	}

	@Test
	public void testRelationalImagePrio() throws BCompoundException {
		final String actual = getExpressionAsString("c~[s]*x");
		final String expected = "AMultOrCartExpression(AImageExpression(AReverseExpression(AIdentifierExpression([c])),AIdentifierExpression([s])),AIdentifierExpression([x]))";
		assertEquals(expected, actual);
	}

	@Test
	public void testLargeInteger() throws BCompoundException {
		final String actual = getExpressionAsString("922337203685477580756");
		final String expected = "AIntegerExpression(922337203685477580756)";
		assertEquals(expected, actual);
	}

	private String getExpressionAsString(final String expression) throws BCompoundException {
		final String machine = "#EXPRESSION " + expression;
		final String astString = getTreeAsString(machine);
		assertTrue(astString.startsWith(START_EXPRESSION));
		assertTrue(astString.endsWith(END_EXPRESSION));
		return astString.substring(START_EXPRESSION.length(), astString.length() - END_EXPRESSION.length());
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}
}
