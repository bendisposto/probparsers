package de.be4.classicalb.core.parser.aspects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.node.AAddExpression;
import de.be4.classicalb.core.parser.node.AExpressionParseUnit;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PositionAspectTest  {

	@Test
	public void testNodeSubclassOfPositionedNode() throws Exception {
		final String testMachine = "#EXPRESSION 1+2";
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);

		assertTrue(startNode instanceof PositionedNode);
	}

	@Test
	public void testHasGetMethods() throws Exception {
		final String testMachine = "#EXPRESSION 1+2";
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);

		startNode.getStartPos();
		startNode.getEndPos();
	}

	@Test
	public void testSimpleNode() throws Exception {
		final String testMachine = "#EXPRESSION x";
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);
		final PExpression expression = ((AExpressionParseUnit) startNode
				.getPParseUnit()).getExpression();

		final SourcePosition startPos = expression.getStartPos();
		final SourcePosition endPos = expression.getEndPos();

		assertNotNull(startNode);
		assertNotNull(endPos);

		assertEquals(1, startPos.getLine());
		assertEquals(13, startPos.getPos());
		assertEquals(1, endPos.getLine());
		assertEquals(14, endPos.getPos());
	}

	@Test
	public void testComposedNode() throws Exception {
		final String testMachine = "#EXPRESSION x+1";
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);

		// test top node
		final PExpression expression = ((AExpressionParseUnit) startNode
				.getPParseUnit()).getExpression();
		SourcePosition startPos = expression.getStartPos();
		SourcePosition endPos = expression.getEndPos();

		assertNotNull(startNode);
		assertNotNull(endPos);

		assertEquals(1, startPos.getLine());
		assertEquals(13, startPos.getPos());
		assertEquals(1, endPos.getLine());
		assertEquals(16, endPos.getPos());

		// test left child: 13-14
		final PExpression leftExpr = ((AAddExpression) expression).getLeft();
		startPos = leftExpr.getStartPos();
		endPos = leftExpr.getEndPos();

		assertNotNull(startNode);
		assertNotNull(endPos);

		assertEquals(1, startPos.getLine());
		assertEquals(13, startPos.getPos());
		assertEquals(1, endPos.getLine());
		assertEquals(14, endPos.getPos());

		// test right child: 15-16
		final PExpression rightExpr = ((AAddExpression) expression).getRight();
		startPos = rightExpr.getStartPos();
		endPos = rightExpr.getEndPos();

		assertNotNull(startNode);
		assertNotNull(endPos);

		assertEquals(1, startPos.getLine());
		assertEquals(15, startPos.getPos());
		assertEquals(1, endPos.getLine());
		assertEquals(16, endPos.getPos());
	}
}
