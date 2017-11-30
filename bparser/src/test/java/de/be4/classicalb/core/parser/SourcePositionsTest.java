package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAddExpression;
import de.be4.classicalb.core.parser.node.AExpressionParseUnit;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASubstitutionParseUnit;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.hhu.stups.sablecc.patch.PositionedNode;

public class SourcePositionsTest {

	private BParser parser;

	@Test
	public void testTokenAsPositionedNode() throws Exception {
		final String testMachine = "#EXPRESSION xx + 5";
		final Start result = getAst(testMachine);
		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result.getPParseUnit();
		final AAddExpression addExpression = (AAddExpression) exprParseUnit.getExpression();
		final AIntegerExpression intExpression = (AIntegerExpression) addExpression.getRight();

		assertTrue(intExpression instanceof PositionedNode);
		final PositionedNode intNode = (PositionedNode) intExpression;
		assertNotNull(intNode.getStartPos());
		assertNotNull(intNode.getEndPos());

		final TIntegerLiteral intLiteral = intExpression.getLiteral();

		assertTrue(intLiteral instanceof PositionedNode);
		final PositionedNode posNode = (PositionedNode) intLiteral;
		assertNotNull(posNode.getStartPos());
		assertNotNull(posNode.getEndPos());
	}

	@Test
	public void testAddExpression() throws Exception {
		final String testMachine = "#EXPRESSION xx + 5";
		final Start result = getAst(testMachine);

		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result.getPParseUnit();
		assertEquals(1, exprParseUnit.getStartPos().getLine());
		assertEquals(1, exprParseUnit.getStartPos().getPos());
		assertEquals(1, exprParseUnit.getEndPos().getLine());
		assertEquals(19, exprParseUnit.getEndPos().getPos());

		final AAddExpression addExpression = (AAddExpression) exprParseUnit.getExpression();
		assertEquals(1, addExpression.getStartPos().getLine());
		assertEquals(13, addExpression.getStartPos().getPos());
		assertEquals(1, addExpression.getEndPos().getLine());
		assertEquals(19, addExpression.getEndPos().getPos());

		final AIdentifierExpression varExpression = (AIdentifierExpression) addExpression.getLeft();
		assertEquals(1, varExpression.getStartPos().getLine());
		assertEquals(13, varExpression.getStartPos().getPos());
		assertEquals(1, varExpression.getEndPos().getLine());
		assertEquals(15, varExpression.getEndPos().getPos());

		final AIntegerExpression intExpression = (AIntegerExpression) addExpression.getRight();
		assertEquals(1, intExpression.getStartPos().getLine());
		assertEquals(18, intExpression.getStartPos().getPos());
		assertEquals(1, intExpression.getEndPos().getLine());
		assertEquals(19, intExpression.getEndPos().getPos());
	}

	@Test
	public void testSequenceSubst() throws Exception {
		final String testMachine = "#SUBSTITUTION skip; x:=5; skip";
		final Start result = getAst(testMachine);

		final ASubstitutionParseUnit substParseUnit = (ASubstitutionParseUnit) result.getPParseUnit();
		assertEquals(1, substParseUnit.getStartPos().getLine());
		assertEquals(1, substParseUnit.getStartPos().getPos());
		assertEquals(1, substParseUnit.getEndPos().getLine());
		assertEquals(testMachine.length() + 1, substParseUnit.getEndPos().getPos());

		final ASequenceSubstitution sequenceSubst = (ASequenceSubstitution) substParseUnit.getSubstitution();
		assertEquals(1, sequenceSubst.getStartPos().getLine());
		assertEquals(15, sequenceSubst.getStartPos().getPos());
		assertEquals(1, sequenceSubst.getEndPos().getLine());
		assertEquals(testMachine.length() + 1, sequenceSubst.getEndPos().getPos());
	}

	@Test
	public void testMultilineSubst() throws Exception {
		final String testMachine = "#SUBSTITUTION IF 1=1\n" + "THEN skip\n" + "ELSE skip\n" + "END";
		final Start result = getAst(testMachine);

		final ASubstitutionParseUnit substParseUnit = (ASubstitutionParseUnit) result.getPParseUnit();
		assertEquals(1, substParseUnit.getStartPos().getLine());
		assertEquals(1, substParseUnit.getStartPos().getPos());
		assertEquals(4, substParseUnit.getEndPos().getLine());
		assertEquals(4, substParseUnit.getEndPos().getPos());
	}

	@Test
	public void testComment1() throws Exception {
		final String testMachine = "#EXPRESSION xx /* comment */ + 5";
		final Start result = getAst(testMachine);

		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result.getPParseUnit();
		final AAddExpression addExpression = (AAddExpression) exprParseUnit.getExpression();
		final AIntegerExpression intExpression = (AIntegerExpression) addExpression.getRight();
		assertEquals(1, intExpression.getStartPos().getLine());
		assertEquals(32, intExpression.getStartPos().getPos());
		assertEquals(1, intExpression.getEndPos().getLine());
		assertEquals(testMachine.length() + 1, intExpression.getEndPos().getPos());
	}


	@Test
	public void testVariablesSourcePositions() throws Exception {
		final String testMachine = "MACHINE test\n" + "VARIABLES\n" + "  xx,\n" + "    yy\n"
				+ "INVARIANT xx:INT & yy:INT\n" + "INITIALISATION xx,yy:=0,0\n" + "END\n";
		final Start result = getAst(testMachine);
		final AAbstractMachineParseUnit machine = (AAbstractMachineParseUnit) result.getPParseUnit();

		AVariablesMachineClause variables = null;
		for (final PMachineClause clause : machine.getMachineClauses()) {
			if (clause instanceof AVariablesMachineClause) {
				variables = (AVariablesMachineClause) clause;
				break;
			}
		}
		if (variables == null) {
			fail("variables clause not found");
		}
		final LinkedList<PExpression> ids = variables.getIdentifiers();
		assertEquals(2, ids.size());
		final AIdentifierExpression x = (AIdentifierExpression) ids.get(0);
		final AIdentifierExpression y = (AIdentifierExpression) ids.get(1);

		// VARIABLES block
		assertEquals(2, variables.getStartPos().getLine());
		assertEquals(1, variables.getStartPos().getPos());
		assertEquals(4, variables.getEndPos().getLine());
		assertEquals(7, variables.getEndPos().getPos());
		

		// variable x declaration
		assertEquals(3, x.getStartPos().getLine());
		assertEquals(3, x.getStartPos().getPos());
		assertEquals(3, x.getEndPos().getLine());
		assertEquals(5, x.getEndPos().getPos());
		
		// variable y declaration
		assertEquals(4, y.getStartPos().getLine());
		assertEquals(5, y.getStartPos().getPos());
		assertEquals(4, y.getEndPos().getLine());
		assertEquals(7, y.getEndPos().getPos());
	}

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
	}

	private Start getAst(final String testMachine) throws BCompoundException {
		// System.out.println("Testing \"" + testMachine + "\"");
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		return startNode;
	}
}
