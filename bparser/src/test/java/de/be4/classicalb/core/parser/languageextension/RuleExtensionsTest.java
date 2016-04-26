package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import util.Helpers;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.Start;

public class RuleExtensionsTest {

	@Test
	public void testForAllSubstitution() throws Exception {
		final String testMachine = "#SUBSTITUTION FORALL x WHERE x : 1..10 EXPECT 1=1 THEN skip ELSE skip END";
		final String result = getTreeAsString(testMachine);

		assertEquals(
				"Start(ASubstitutionParseUnit(AIfSubstitution(AForallPredicate([AIdentifierExpression([x])],AImplicationPredicate(AMemberPredicate(AIdentifierExpression([x]),AIntervalExpression(AIntegerExpression(1),AIntegerExpression(10))),AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)))),ASkipSubstitution(),[],AAnySubstitution([AIdentifierExpression([x])],AConjunctPredicate(AMemberPredicate(AIdentifierExpression([x]),AIntervalExpression(AIntegerExpression(1),AIntegerExpression(10))),ANegationPredicate(AEqualPredicate(AIntegerExpression(1),AIntegerExpression(1)))),ASkipSubstitution()))))",
				result);
	}

	@Test
	public void testForAllPredicate() {
		String file = "src/test/resources/languageextensions/rules/ForAllPredicate.mch";
		String result = Helpers.fullParsing(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testRuleOperation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END END";
		final String result = getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([foo]),AIdentifierExpression([foo_Counterexamples])]),AInvariantMachineClause(AConjunctPredicate(AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)])),AMemberPredicate(AIdentifierExpression([foo_Counterexamples]),APowSubsetExpression(AStringSetExpression())))),AInitialisationMachineClause(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([foo_Counterexamples])],[AStringExpression(NOT_CHECKED),AEmptySetExpression()])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ABlockSubstitution(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[AStringExpression(SUCCESS),AStringExpression(SUCCESS),AStringExpression()])),[],))])]))",
				result);
	}

	@Test(expected = BException.class)
	public void testRuleOperationMissingFailOrSuccess() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN skip END END";
		getTreeAsString(testMachine);
	}

	@Test
	public void testRuleFailAsAnOrdinaryIdentifierInANormalMachine()
			throws Exception {
		final String testMachine = "MACHINE Test CONSTANTS RULE_FAI PROPERTIES RULE_FAI = 1 END";
		String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AConstantsMachineClause([AIdentifierExpression([RULE_FAI])]),APropertiesMachineClause(AEqualPredicate(AIdentifierExpression([RULE_FAI]),AIntegerExpression(1)))]))",
				result);
	}

	@Test(expected = BException.class)
	public void testDuplicateRuleAssignStatement() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS;RULE_SUCCESS  END END";
		getTreeAsString(testMachine);
	}

	@Test
	public void testRuleFailNoMessage() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_FAIL END END";
		final String result = getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([foo]),AIdentifierExpression([foo_Counterexamples])]),AInvariantMachineClause(AConjunctPredicate(AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)])),AMemberPredicate(AIdentifierExpression([foo_Counterexamples]),APowSubsetExpression(AStringSetExpression())))),AInitialisationMachineClause(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([foo_Counterexamples])],[AStringExpression(NOT_CHECKED),AEmptySetExpression()])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ABlockSubstitution(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE]),AIdentifierExpression([foo_Counterexamples])],[AStringExpression(FAIL),AStringExpression(FAIL),AStringExpression(),AEmptySetExpression()])),[],))])]))",
				result);
	}

	@Test
	public void testRuleFail() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_FAIL(\"foo is violated\") END END";
		getTreeAsString(testMachine);
	}

	@Test
	public void testRuleOperationAndExistingVariables() throws Exception {
		final String testMachine = "RULES_MACHINE Test VARIABLES x INVARIANT x = 1 INITIALISATION x:= 1 OPERATIONS RULE foo = BEGIN RULE_SUCCESS END END";
		getTreeAsString(testMachine);
	}

	@Test
	public void testDependsOnRule() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END;"
				+ " RULE foo2 = SELECT DEPENDS_ON_RULES(foo) THEN RULE_SUCCESS END END";
		getTreeAsString(testMachine);

	}

	public static String getTreeAsString(final String testMachine)
			throws BException {
		// System.out.println("Parsing \"" + testMachine + "\"");
		final BParser parser = new BParser("testcase");
		parser.getOptions().grammar = RuleGrammar.getInstance();
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}

}
