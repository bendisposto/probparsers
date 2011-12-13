package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAddExpression;
import de.be4.classicalb.core.parser.node.AExpressionParseUnit;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIfSubstitution;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.ASequenceSubstitution;
import de.be4.classicalb.core.parser.node.ASetExtensionExpression;
import de.be4.classicalb.core.parser.node.ASkipSubstitution;
import de.be4.classicalb.core.parser.node.ASubstitutionParseUnit;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.PSubstitution;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIntegerLiteral;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;

public class SourcePositionsTest {

	private BParser parser;

	@Test
	public void testTokenAsPositionedNode() throws Exception {
		final String testMachine = "#EXPRESSION xx + 5";
		final Start result = getAst(testMachine);
		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result
				.getPParseUnit();
		final AAddExpression addExpression = (AAddExpression) exprParseUnit
				.getExpression();
		final AIntegerExpression intExpression = (AIntegerExpression) addExpression
				.getRight();

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
		final SourcePositions positions = parser.getSourcePositions();

		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result
				.getPParseUnit();
		assertEquals(1, positions.getBeginColumn(exprParseUnit));
		assertEquals(1, positions.getBeginLine(exprParseUnit));
		assertEquals(1, positions.getEndLine(exprParseUnit));
		assertEquals(testMachine.length(),
				positions.getEndColumn(exprParseUnit));

		final AAddExpression addExpression = (AAddExpression) exprParseUnit
				.getExpression();
		assertEquals(13, positions.getBeginColumn(addExpression));
		assertEquals(1, positions.getBeginLine(addExpression));
		assertEquals(1, positions.getEndLine(addExpression));
		assertEquals(testMachine.length(),
				positions.getEndColumn(addExpression));

		final AIdentifierExpression varExpression = (AIdentifierExpression) addExpression
				.getLeft();
		assertEquals(13, positions.getBeginColumn(varExpression));
		assertEquals(1, positions.getBeginLine(varExpression));
		assertEquals(1, positions.getEndLine(varExpression));
		assertEquals(14, positions.getEndColumn(varExpression));

		final AIntegerExpression intExpression = (AIntegerExpression) addExpression
				.getRight();
		assertEquals(18, positions.getBeginColumn(intExpression));
		assertEquals(1, positions.getBeginLine(intExpression));
		assertEquals(1, positions.getEndLine(intExpression));
		assertEquals(testMachine.length(),
				positions.getEndColumn(intExpression));
	}

	@Test
	public void testSequenceSubst() throws Exception {
		final String testMachine = "#SUBSTITUTION skip; x:=5; skip";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();

		final ASubstitutionParseUnit substParseUnit = (ASubstitutionParseUnit) result
				.getPParseUnit();
		assertEquals(1, positions.getBeginColumn(substParseUnit));
		assertEquals(1, positions.getBeginLine(substParseUnit));
		assertEquals(1, positions.getEndLine(substParseUnit));
		assertEquals(testMachine.length(),
				positions.getEndColumn(substParseUnit));

		final ASequenceSubstitution sequenceSubst = (ASequenceSubstitution) substParseUnit
				.getSubstitution();
		assertEquals(15, positions.getBeginColumn(sequenceSubst));
		assertEquals(1, positions.getBeginLine(sequenceSubst));
		assertEquals(1, positions.getEndLine(sequenceSubst));
		assertEquals(testMachine.length(),
				positions.getEndColumn(sequenceSubst));

		final PSubstitution subst1 = sequenceSubst.getSubstitutions().get(0);
		assertEquals(15, positions.getBeginColumn(subst1));
		assertEquals(1, positions.getBeginLine(subst1));
		assertEquals(1, positions.getEndLine(subst1));
		assertEquals(18, positions.getEndColumn(subst1));

		final PSubstitution subst2 = sequenceSubst.getSubstitutions().get(1);
		assertEquals(21, positions.getBeginColumn(subst2));
		assertEquals(1, positions.getBeginLine(subst2));
		assertEquals(1, positions.getEndLine(subst2));
		assertEquals(24, positions.getEndColumn(subst2));

		final PSubstitution subst3 = sequenceSubst.getSubstitutions().get(2);
		assertEquals(27, positions.getBeginColumn(subst3));
		assertEquals(1, positions.getBeginLine(subst3));
		assertEquals(1, positions.getEndLine(subst3));
		assertEquals(testMachine.length(), positions.getEndColumn(subst3));
	}

	@Test
	public void testMultilineSubst() throws Exception {
		final String testMachine = "#SUBSTITUTION IF 1=1\n" + "THEN skip\n"
				+ "ELSE skip\n" + "END";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();

		final ASubstitutionParseUnit substParseUnit = (ASubstitutionParseUnit) result
				.getPParseUnit();
		assertEquals(1, positions.getBeginColumn(substParseUnit));
		assertEquals(1, positions.getBeginLine(substParseUnit));
		assertEquals(4, positions.getEndLine(substParseUnit));
		assertEquals(3, positions.getEndColumn(substParseUnit));

		final AIfSubstitution ifSubst = (AIfSubstitution) substParseUnit
				.getSubstitution();
		assertEquals(15, positions.getBeginColumn(ifSubst));
		assertEquals(1, positions.getBeginLine(ifSubst));
		assertEquals(4, positions.getEndLine(ifSubst));
		assertEquals(3, positions.getEndColumn(ifSubst));

		final ASkipSubstitution thenSubst = (ASkipSubstitution) ifSubst
				.getThen();
		assertEquals(6, positions.getBeginColumn(thenSubst));
		assertEquals(2, positions.getBeginLine(thenSubst));
		assertEquals(2, positions.getEndLine(thenSubst));
		assertEquals(9, positions.getEndColumn(thenSubst));

		final ASkipSubstitution elseSubst = (ASkipSubstitution) ifSubst
				.getElse();
		assertEquals(6, positions.getBeginColumn(elseSubst));
		assertEquals(3, positions.getBeginLine(elseSubst));
		assertEquals(3, positions.getEndLine(elseSubst));
		assertEquals(9, positions.getEndColumn(elseSubst));
	}

	@Test
	public void testComment1() throws Exception {
		final String testMachine = "#EXPRESSION xx /* comment */ + 5";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();

		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result
				.getPParseUnit();
		assertEquals(1, positions.getBeginColumn(exprParseUnit));
		assertEquals(1, positions.getBeginLine(exprParseUnit));
		assertEquals(1, positions.getEndLine(exprParseUnit));
		assertEquals(testMachine.length(),
				positions.getEndColumn(exprParseUnit));

		final AAddExpression addExpression = (AAddExpression) exprParseUnit
				.getExpression();
		assertEquals(13, positions.getBeginColumn(addExpression));
		assertEquals(1, positions.getBeginLine(addExpression));
		assertEquals(1, positions.getEndLine(addExpression));
		assertEquals(testMachine.length(),
				positions.getEndColumn(addExpression));

		final AIdentifierExpression varExpression = (AIdentifierExpression) addExpression
				.getLeft();
		assertEquals(13, positions.getBeginColumn(varExpression));
		assertEquals(1, positions.getBeginLine(varExpression));
		assertEquals(1, positions.getEndLine(varExpression));
		assertEquals(14, positions.getEndColumn(varExpression));

		final AIntegerExpression intExpression = (AIntegerExpression) addExpression
				.getRight();
		assertEquals(32, positions.getBeginColumn(intExpression));
		assertEquals(1, positions.getBeginLine(intExpression));
		assertEquals(1, positions.getEndLine(intExpression));
		assertEquals(testMachine.length(),
				positions.getEndColumn(intExpression));
	}

	@Test
	public void testComment2() throws Exception {
		final String testMachine = "#EXPRESSION xx /* comment1 \n comment2 */ + 5";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();

		final AExpressionParseUnit exprParseUnit = (AExpressionParseUnit) result
				.getPParseUnit();
		assertEquals(1, positions.getBeginColumn(exprParseUnit));
		assertEquals(1, positions.getBeginLine(exprParseUnit));
		assertEquals(2, positions.getEndLine(exprParseUnit));
		assertEquals(16, positions.getEndColumn(exprParseUnit));

		final AAddExpression addExpression = (AAddExpression) exprParseUnit
				.getExpression();
		assertEquals(13, positions.getBeginColumn(addExpression));
		assertEquals(1, positions.getBeginLine(addExpression));
		assertEquals(2, positions.getEndLine(addExpression));
		assertEquals(16, positions.getEndColumn(addExpression));

		final AIdentifierExpression varExpression = (AIdentifierExpression) addExpression
				.getLeft();
		assertEquals(13, positions.getBeginColumn(varExpression));
		assertEquals(1, positions.getBeginLine(varExpression));
		assertEquals(1, positions.getEndLine(varExpression));
		assertEquals(14, positions.getEndColumn(varExpression));

		final AIntegerExpression intExpression = (AIntegerExpression) addExpression
				.getRight();
		assertEquals(16, positions.getBeginColumn(intExpression));
		assertEquals(2, positions.getBeginLine(intExpression));
		assertEquals(2, positions.getEndLine(intExpression));
		assertEquals(16, positions.getEndColumn(intExpression));

	}

	@Test
	public void testNodeToString() throws Exception {
		final String testMachine = "#EXPRESSION {y}";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();

		final AExpressionParseUnit parseUnit = (AExpressionParseUnit) result
				.getPParseUnit();
		final ASetExtensionExpression expression = (ASetExtensionExpression) parseUnit
				.getExpression();

		assertEquals("{y}", positions.getNodeString(expression));
	}

	@Test
	public void testVariablesSourcePositions() throws Exception {
		final String testMachine = "MACHINE test\n" + "VARIABLES\n" + "  xx,\n"
				+ "    yy\n" + "INVARIANT xx:INT & yy:INT\n"
				+ "INITIALISATION xx,yy:=0,0\n" + "END\n";
		final Start result = getAst(testMachine);
		final SourcePositions positions = parser.getSourcePositions();
		final AAbstractMachineParseUnit machine = (AAbstractMachineParseUnit) result
				.getPParseUnit();

		AVariablesMachineClause variables = null;
		for (final PMachineClause clause : machine.getMachineClauses()) {
			if (clause instanceof AVariablesMachineClause) {
				variables = (AVariablesMachineClause) clause;
				break;
			}
		}
		assertNotNull(variables);
		final LinkedList<PExpression> ids = variables.getIdentifiers();
		assertEquals(2, ids.size());
		final AIdentifierExpression x = (AIdentifierExpression) ids.get(0);
		final AIdentifierExpression y = (AIdentifierExpression) ids.get(1);

		// VARIABLES block
		assertEquals(2, positions.getBeginLine(variables));
		assertEquals(1, positions.getBeginColumn(variables));
		assertEquals(4, positions.getEndLine(variables));
		assertEquals(6, positions.getEndColumn(variables));

		// variable x declaration
		assertEquals(3, positions.getBeginLine(x));
		assertEquals(3, positions.getBeginColumn(x));
		assertEquals(3, positions.getEndLine(x));
		assertEquals(4, positions.getEndColumn(x));

		// variable y declaration
		assertEquals(4, positions.getBeginLine(y));
		assertEquals(5, positions.getBeginColumn(y));
		assertEquals(4, positions.getEndLine(y));
		assertEquals(6, positions.getEndColumn(y));
	}

	@Test
	public void testGetTokenForPosition() throws Exception {
		parser.parse(
				"MACHINE TestMachine\n\nVARIABLES x\n\nINVARIANT x:NAT\nINITIALISATION x:=1\nEND",
				false);
		final SourcePositions positions = parser.getSourcePositions();

		// 1. token: "MACHINE"
		assertEquals(0, positions.getTokenforPosition(1, 1));
		assertEquals(0, positions.getTokenforPosition(1, 3));
		assertEquals(0, positions.getTokenforPosition(1, 7));

		// 2. token: space
		assertEquals(1, positions.getTokenforPosition(1, 8));

		// 3. token: "TestMachine"
		assertEquals(2, positions.getTokenforPosition(1, 9));
		assertEquals(2, positions.getTokenforPosition(1, 12));
		assertEquals(2, positions.getTokenforPosition(1, 19));

		// 4. token (including query beyond line length): linebreak
		assertEquals(3, positions.getTokenforPosition(1, 20));
		assertEquals(3, positions.getTokenforPosition(1, 25));

		// 5. token: linebreak
		assertEquals(4, positions.getTokenforPosition(2, 1));
		assertEquals(4, positions.getTokenforPosition(2, 5));

		// 6. token: "VARIABLES"
		assertEquals(5, positions.getTokenforPosition(3, 1));
		assertEquals(5, positions.getTokenforPosition(3, 8));
		assertEquals(5, positions.getTokenforPosition(3, 9));

		// 7. token: space
		assertEquals(6, positions.getTokenforPosition(3, 10));

		// 8. token: "x"
		assertEquals(7, positions.getTokenforPosition(3, 11));

		// 9. token: linebreak
		assertEquals(8, positions.getTokenforPosition(3, 12));
		assertEquals(8, positions.getTokenforPosition(3, 20));

		// 10. token: linebreak
		assertEquals(9, positions.getTokenforPosition(4, 1));
		assertEquals(9, positions.getTokenforPosition(4, 10));

		// 11. token: "INVARIANT"
		assertEquals(10, positions.getTokenforPosition(5, 1));
		assertEquals(10, positions.getTokenforPosition(5, 8));
		assertEquals(10, positions.getTokenforPosition(5, 9));

		// 17. token: "INITIALISATION"
		assertEquals(16, positions.getTokenforPosition(6, 1));
		assertEquals(16, positions.getTokenforPosition(6, 8));
		assertEquals(16, positions.getTokenforPosition(6, 14));

		// 23. token: "END"
		assertEquals(22, positions.getTokenforPosition(7, 1));
		assertEquals(22, positions.getTokenforPosition(7, 2));
		assertEquals(22, positions.getTokenforPosition(7, 3));

		// 12. token: EOF
		assertEquals(23, positions.getTokenforPosition(7, 4));
		assertEquals(23, positions.getTokenforPosition(7, 10));
		assertEquals(23, positions.getTokenforPosition(8, 99));

		// nonsense input
		assertEquals(0, positions.getTokenforPosition(-1, 10));
		assertEquals(1, positions.getTokenforPosition(1, 0));
		assertEquals(1, positions.getTokenforPosition(1, -1));
		assertEquals(0, positions.getTokenforPosition(0, -1));
	}

	@Before
	public void setUp() throws Exception {
		parser = new BParser("testcase");
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
	}

	private Start getAst(final String testMachine) throws BException {
		// System.out.println("Testing \"" + testMachine + "\"");
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		return startNode;
	}
}
