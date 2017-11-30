package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BException;
import util.Helpers;

public class SyntaxExtensionTest {

	@Test
	public void testMultiLineString() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES '''foo''' /= '''bar\nbazz''' END";
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		assertTrue(result.contains("'bar\\nbazz'"));
	}

	@Test
	public void testMultiLineStringIncludingSingleQuate() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES '''' ''' = \"b\" END";
		Helpers.getMachineAsPrologTerm(testMachine);
	}

	@Test
	public void testFile() throws IOException, BException {
		String file = "src/test/resources/strings/MultiLineString.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertTrue(result.contains("'\\n\\'\\na\\n\\'\\'\\'\\n'"));
	}

	@Test
	public void testMultiLineStringIncludingTwoSingleQuates() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES ''''' ''' = \"b\" END";
		Helpers.getMachineAsPrologTerm(testMachine);
	}

	@Test
	public void testLocalOperations() throws Exception {
		final String testMachine = "MACHINE Test LOCAL_OPERATIONS foo = skip END";
		Helpers.getMachineAsPrologTerm(testMachine);
	}

	@Test
	public void testIfThenElseExpression() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES 1 = IF 1=1 THEN 1 ELSE 2 END END";

		final String result = Helpers.getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[APropertiesMachineClause(AEqualPredicate(AIntegerExpression(1),AIfThenElseExpression(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1))AIntegerExpression(1)AIntegerExpression(2))))]))",
				result);
	}

	@Test
	public void testLetExpression() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES\n LET x BE x = 1 IN x + 1 END = 2 END";
		final String result = Helpers.getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[APropertiesMachineClause(AEqualPredicate(ALetExpressionExpression(AIdentifierExpression([x])AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(1))AAddExpression(AIdentifierExpression([x]),AIntegerExpression(1))),AIntegerExpression(2)))]))",
				result);
	}

	@Test
	public void testIfThenElsePredicate() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES IF 1=1 THEN 2=2 ELSE 3=3 END END";
		final String result = Helpers.getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[APropertiesMachineClause(AConjunctPredicate(AImplicationPredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)),AEqualPredicate(AIntegerExpression(2),AIntegerExpression(2))),AImplicationPredicate(ANegationPredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1))),AEqualPredicate(AIntegerExpression(3),AIntegerExpression(3)))))]))",
				result);
	}

	@Test
	public void testIfThenElsePredicate2() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES IF 1=1 THEN 2=2 ELSE 3=3 END & 1=1 END";
		String result = Helpers.getTreeAsString(testMachine);
		assertFalse(result.contains("if"));
		assertFalse(result.contains("IF"));
	}

	@Test
	public void testLetPredicate() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES LET x BE x = 1+1 IN x = 2 END END";
		final String result = Helpers.getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[APropertiesMachineClause(ALetPredicatePredicate(AIdentifierExpression([x])AEqualPredicate(AIdentifierExpression([x]),AAddExpression(AIntegerExpression(1),AIntegerExpression(1)))AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(2))))]))",
				result);
	}

}
