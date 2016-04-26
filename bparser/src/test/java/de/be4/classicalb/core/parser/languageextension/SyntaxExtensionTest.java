package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import util.Helpers;

public class SyntaxExtensionTest {

	@Test
	public void testIfThenElsePredicate() throws Exception {
		final String testMachine = "MACHINE Test PROPERTIES IF 1=1 THEN 2=2 ELSE 3=3 END END";
		final String result = Helpers.getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[APropertiesMachineClause(AConjunctPredicate(AImplicationPredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)),AEqualPredicate(AIntegerExpression(2),AIntegerExpression(2))),AImplicationPredicate(ANegationPredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1))),AEqualPredicate(AIntegerExpression(3),AIntegerExpression(3)))))]))",
				result);
	}

}
