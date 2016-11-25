package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.Start;

public class StructuralTest {

	@Test
	public void testEmptyMachine() throws Exception {
		final String testMachine = "MACHINE SimplyStructure END";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);

		final AAbstractMachineParseUnit machine = (AAbstractMachineParseUnit) startNode.getPParseUnit();

		final AMachineHeader header = (AMachineHeader) machine.getHeader();
		assertEquals("Machine name not as expected", "SimplyStructure", header.getName().get(0).getText());
		assertNotNull("Machine header parameter list is null", header.getParameters());
		assertTrue("More machine header parameters than expected", header.getParameters().size() == 0);

		final LinkedList<PMachineClause> machineClauses = machine.getMachineClauses();
		assertNotNull("Machine clause list is null", machineClauses);
		assertTrue("More machine clauses than expected", machineClauses.size() == 0);
	}

	@Test
	public void testShebang() throws Exception {
		final String testMachine = "#! /Users/leuschel/git_root/prob_prolog/probcli \n MACHINE SheBang \n END";
		final String result = getTreeAsString(testMachine);
		assertNotNull(result);
	}

	@Test(expected = BCompoundException.class)
	public void testWrongPositionedShebang() throws Exception {
		final String testMachine = "\n#! /Users/leuschel/git_root/prob_prolog/probcli \n MACHINE SheBang \n END";
		final String result = getTreeAsString(testMachine);
		assertNotNull(result);
	}

	@Test
	public void testWhiteSpaces() throws Exception {
		final String testMachine = "MACHINE \tSimplyStructure END";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AAbstractMachineParseUnit(AMachineHeader([SimplyStructure],[]),[]))", result);
	}

	@Test
	public void testIncludesClause() throws Exception {
		final String testMachine = "MACHINE SimplyStructure INCLUDES MachineA, MachineB (aa, bb, MAXINT, cc(dd)) END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([SimplyStructure],[]),[AIncludesMachineClause([AMachineReference([MachineA],[]),AMachineReference([MachineB],[AIdentifierExpression([aa]),AIdentifierExpression([bb]),AMaxIntExpression(),AFunctionExpression(AIdentifierExpression([cc]),[AIdentifierExpression([dd])])])])]))",
				result);
	}

	@Test
	public void testVariablesClause() throws Exception {
		final String testMachine = "MACHINE SimplyStructure\nVARIABLES aa, b, Cc\nINVARIANT aa : NAT & b : NAT & Cc : NAT\nINITIALISATION aa:=1 || b:=2 || c:=3\nEND";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);
		assertNotNull(startNode);

		// TODO more tests
	}

	@Test
	public void testConstantsClause() throws Exception {
		final String testMachine = "MACHINE SimplyStructure\nCONSTANTS dd, e, Ff\nPROPERTIES dd : BOOL\nEND";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);
		assertNotNull(startNode);

		// TODO more tests
	}

	@Test
	public void testSetsClause() throws Exception {
		final String testMachine = "MACHINE SimplyStructure SETS GGG; Hhh; JJ = {dada, dudu, TUTUT}; iII; kkk = {LLL} END";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);
		assertNotNull(startNode);

		// TODO more tests
	}

	@Test
	public void testClause1() throws Exception {
		final String testMachine = "MACHINE SimplyStructure END";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);
		assertNotNull(startNode);

		// TODO more tests
	}

	@Test
	public void testClause2() throws Exception {
		final String testMachine = "#MACHINECLAUSE VARIABLES xx, Ab, cD";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AMachineClauseParseUnit(AVariablesMachineClause([AIdentifierExpression([xx]),AIdentifierExpression([Ab]),AIdentifierExpression([cD])])))",
				result);

		final String testMachine2 = "#MACHINECLAUSE ABSTRACT_VARIABLES xx, Ab, cD";
		final String result2 = getTreeAsString(testMachine2);

		assertEquals(result, result2);
	}

	@Test
	public void testClause3() throws Exception {
		final String testMachine = "#MACHINECLAUSE INCLUDES MachineA, MachineB (aa, bb, MAXINT, cc(dd))";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(AMachineClauseParseUnit(AIncludesMachineClause([AMachineReference([MachineA],[]),AMachineReference([MachineB],[AIdentifierExpression([aa]),AIdentifierExpression([bb]),AMaxIntExpression(),AFunctionExpression(AIdentifierExpression([cc]),[AIdentifierExpression([dd])])])])))",
				result);
	}

	@Test
	public void testClausesStructure() throws Exception {
		final String testMachine = "MACHINE SimplyStructure\n" + "VARIABLES aa, b, Cc\n" + "INVARIANT aa : NAT\n"
				+ "INITIALISATION aa:=1\n" + "CONSTANTS dd, e, Ff\n" + "PROPERTIES dd : NAT\n"
				+ "SETS GGG; Hhh; JJ = {dada, dudu, TUTUT}; iII; kkk = {LLL}\nEND";

		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, true);
		assertNotNull(startNode);

		// TODO more tests
	}

	@Test
	public void testRefinement1() throws Exception {
		final String emptyMachine = "REFINEMENT RefinementMachine \nREFINES Machine \nEND";
		final String result = getTreeAsString(emptyMachine);

		assertEquals("Start(ARefinementMachineParseUnit(AMachineHeader([RefinementMachine],[]),Machine,[]))", result);
	}

	@Test
	public void testImplementation1() throws Exception {
		final String emptyMachine = "IMPLEMENTATION ImplMachine \nREFINES Machine \nEND";
		final String result = getTreeAsString(emptyMachine);

		assertEquals("Start(AImplementationMachineParseUnit(AMachineHeader([ImplMachine],[]),Machine,[]))", result);
	}

	@Test
	public void testUnclosedComment() {
		final String emptyMachine = "MACHINE ClassicalB\n SETS pp ; qq\n /* CONSTANTS ccc,ddd\n VARIABLES xxx,yyy\n OPERATIONS\n  op1 = BEGIN xxx := 1; v <-- op2(2) END;\n  op2 = ANY q WHERE q : NAT THEN yyy := ccc END\nEND";
		try {
			getTreeAsString(emptyMachine);
			fail("Expected exception was not thrown");
		} catch (final BCompoundException e) {
			final BLexerException ex = (BLexerException) e.getCause();
			// checking the start position of the comment
			assertEquals(3, ex.getLastLine());
			assertEquals(2, ex.getLastPos());
			assertTrue(e.getMessage().contains("Comment not closed."));
		}
	}

	@Test
	public void testList1() throws Exception {
		final String testMachine = "#SUBSTITUTION IF 1=1 THEN skip ELSIF 1=2 THEN skip ELSIF 1=3 THEN skip END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(ASubstitutionParseUnit(AIfSubstitution(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)),ASkipSubstitution(),[AIfElsifSubstitution(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(2)),ASkipSubstitution()),AIfElsifSubstitution(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(3)),ASkipSubstitution())],)))",
				result);
	}

	@Test
	public void testList2() throws Exception {
		final String testMachine = "#SUBSTITUTION a, b := 1, 2";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(ASubstitutionParseUnit(AAssignSubstitution([AIdentifierExpression([a]),AIdentifierExpression([b])],[AIntegerExpression(1),AIntegerExpression(2)])))",
				result);
	}

	@Test
	public void checkForMissingSemicolon() throws Exception {
		String s = "MACHINE MissingSemicolon\nOPERATIONS\n Foo=BEGIN skip END\n  BAR= BEGIN r := xx END\nEND";
		try {
			getTreeAsString(s);
			fail("Missing Semicolon was not detected");
		} catch (BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			Node node = cause.getNodes()[0];
			assertEquals(4, node.getStartPos().getLine());
			assertEquals(3, node.getStartPos().getPos());
			assertTrue(e.getMessage().contains("Semicolon missing"));
		}
	}

	@Test
	public void checkForInvalidSemicolon() throws Exception {
		String s = "MACHINE MissingSemicolon\nOPERATIONS\n Foo=BEGIN skip END\n;\nEND";
		try {
			getTreeAsString(s);
			fail("Invalid Semicolon was not detected");
		} catch (BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			Node node = cause.getNodes()[0];
			System.out.println(cause.getMessage());
			assertEquals(4, node.getStartPos().getLine());
			assertEquals(1, node.getStartPos().getPos());
			assertTrue(e.getMessage().contains("Invalid semicolon after last operation"));
		}
	}

	@Test
	public void checkForInvalidSemicolonBeforeEnd() throws Exception {
		String s = "MACHINE MissingSemicolon\nOPERATIONS\n Foo=BEGIN skip\n; END\nEND";
		try {
			getTreeAsString(s);
			fail("Invalid Semicolon was not detected");
		} catch (BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			Node node = cause.getNodes()[0];
			assertEquals(4, node.getStartPos().getLine());
			assertEquals(1, node.getStartPos().getPos());
			assertTrue(e.getMessage().contains("Invalid semicolon after last substitution"));
		}
	}

	@Test
	public void checkForInvalidSemicolonBeforeEnd2() throws Exception {
		String s = "MACHINE MissingSemicolon\nOPERATIONS\n Foo=BEGIN skip;skip\n; END\nEND";
		try {
			getTreeAsString(s);
			fail("Invalid Semicolon was not detected");
		} catch (BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			Node node = cause.getNodes()[0];
			assertEquals(4, node.getStartPos().getLine());
			assertEquals(1, node.getStartPos().getPos());
			assertTrue(e.getMessage().contains("Invalid semicolon after last substitution"));
		}
	}

	@Test
	public void testRepeatingClauses() {
		final String testMachine = "MACHINE TestMachineX\n" + "VARIABLES a,b,c\n" + "CONSTANTS X,Y,Z\n"
				+ "VARIABLES x,y,z\n" + "END";
		try {
			getTreeAsString(testMachine);
			fail("Expecting exception");
		} catch (final BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			assertEquals(2, cause.getNodes().length);
			// IGNORE: is expected
		}
	}

	@Test
	public void testMissingProperties() {
		final String testMachine = "MACHINE TestMachineX\n" + "CONSTANTS X,Y,Z\n" + "END";
		try {
			getTreeAsString(testMachine);
			fail("Expecting exception");
		} catch (final BCompoundException e) {
			final CheckException cause = (CheckException) e.getCause();
			assertEquals(1, cause.getNodes().length);
			assertEquals("Clause(s) missing: PROPERTIES", cause.getMessage());
			// IGNORE: is expected
		}
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		// System.out.println("Parsing: \"" + testMachine + "\":");
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		// System.out.println();
		return string;
	}

	@Test
	public void testHexLiterals() throws Exception {
		final String testMachine = "#EXPRESSION 0x12";
		final String result = getTreeAsString(testMachine);

		assertEquals("Start(AExpressionParseUnit(AIntegerExpression(18)))", result);
	}
}
