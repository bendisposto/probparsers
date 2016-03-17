package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.extensions.RuleExtension;
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
	public void testRuleOperation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END END";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([foo])]),AInvariantMachineClause(AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)]))),AInitialisationMachineClause(AAssignSubstitution([AIdentifierExpression([foo])],[AStringExpression(NOT_CHECKED)])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ASequenceSubstitution([ABlockSubstitution(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([#COUNTEREXAMPLE])],[AStringExpression(SUCCESS),AStringExpression()])),AAssertionSubstitution(ANotEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),AIfSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(SUCCESS)]),[],AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(FAIL)])))]),[],))])]))",
				result);
	}

	@Test
	public void testRuleFail() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RuleFail(\"foo is violated\") END END";
		final String result = getTreeAsString(testMachine);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([foo])]),AInvariantMachineClause(AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)]))),AInitialisationMachineClause(AAssignSubstitution([AIdentifierExpression([foo])],[AStringExpression(NOT_CHECKED)])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ASequenceSubstitution([ABlockSubstitution(AOpSubstitution(AIdentifierExpression([RuleFail]),[AStringExpression(foo is violated)])),AAssertionSubstitution(ANotEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),AIfSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(SUCCESS)]),[],AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(FAIL)])))]),[],))])]))",
				result);
	}

	@Test
	public void testRuleOperationAndExistingVariables() throws Exception {
		final String testMachine = "RULES_MACHINE Test VARIABLES x INVARIANT x = 1 INITIALISATION x:= 1 OPERATIONS RULE foo = BEGIN RULE_SUCCESS END END";
		final String result = getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([x]),AIdentifierExpression([foo])]),AInvariantMachineClause(AConjunctPredicate(AEqualPredicate(AIdentifierExpression([x]),AIntegerExpression(1)),AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)])))),AInitialisationMachineClause(AParallelSubstitution([AAssignSubstitution([AIdentifierExpression([x])],[AIntegerExpression(1)]),AAssignSubstitution([AIdentifierExpression([foo])],[AStringExpression(NOT_CHECKED)])])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ASequenceSubstitution([ABlockSubstitution(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([#COUNTEREXAMPLE])],[AStringExpression(SUCCESS),AStringExpression()])),AAssertionSubstitution(ANotEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),AIfSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(SUCCESS)]),[],AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(FAIL)])))]),[],))])]))",
				result);
	}

	@Test
	public void testDependsOnRule() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END;"
				+ " RULE foo2 = SELECT DEPENDS_ON_RULES(foo) THEN RULE_SUCCESS END END";
		final String result = getTreeAsString(testMachine);
		System.out.println(result);
		assertEquals(
				"Start(AAbstractMachineParseUnit(AMachineHeader([Test],[]),[AVariablesMachineClause([AIdentifierExpression([foo]),AIdentifierExpression([foo2])]),AInvariantMachineClause(AConjunctPredicate(AMemberPredicate(AIdentifierExpression([foo]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)])),AMemberPredicate(AIdentifierExpression([foo2]),ASetExtensionExpression([AStringExpression(FAIL),AStringExpression(SUCCESS),AStringExpression(NOT_CHECKED)])))),AInitialisationMachineClause(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([foo2])],[AStringExpression(NOT_CHECKED),AStringExpression(NOT_CHECKED)])),AOperationsMachineClause([AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),ASequenceSubstitution([ABlockSubstitution(AAssignSubstitution([AIdentifierExpression([foo]),AIdentifierExpression([#COUNTEREXAMPLE])],[AStringExpression(SUCCESS),AStringExpression()])),AAssertionSubstitution(ANotEqualPredicate(AIdentifierExpression([foo]),AStringExpression(NOT_CHECKED)),AIfSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(SUCCESS)]),[],AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(FAIL)])))]),[],)),AOperation([AIdentifierExpression([#RESULT]),AIdentifierExpression([#COUNTEREXAMPLE])],[foo2],[],ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo2]),AStringExpression(NOT_CHECKED)),ASequenceSubstitution([ASelectSubstitution(AEqualPredicate(AIdentifierExpression([foo]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([foo2]),AIdentifierExpression([#COUNTEREXAMPLE])],[AStringExpression(SUCCESS),AStringExpression()]),[],),AAssertionSubstitution(ANotEqualPredicate(AIdentifierExpression([foo2]),AStringExpression(NOT_CHECKED)),AIfSubstitution(AEqualPredicate(AIdentifierExpression([foo2]),AStringExpression(SUCCESS)),AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(SUCCESS)]),[],AAssignSubstitution([AIdentifierExpression([#RESULT])],[AStringExpression(FAIL)])))]),[],))])]))",
				result);
	}

	private String getTreeAsString(final String testMachine) throws BException {
		// System.out.println("Parsing \"" + testMachine + "\"");
		final BParser parser = new BParser("testcase");
		parser.setGrammar(new RuleExtension());
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}
}
