package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.rules.project.RulesParseUnit;

public class RuleExtensionsTest {

	@Test
	public void testForAllPredicate() throws FileNotFoundException, IOException {
		String file = "src/test/resources/rules/ForAllPredicate.mch";
		String result = getFileAsPrologTerm(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDefinitionInjection() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 = RULE_FORALL r WHERE r : 1..3 EXPECT 1=2 COUNTEREXAMPLE \"foo\"  END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Does not contain TO_STRING definition.", result.contains("expression_definition(none,'TO_STRING'"));
		assertTrue("Does not contain EXTERNAL_FUNCTION_TO_STRING definition.",
				result.contains("expression_definition(none,'EXTERNAL_FUNCTION_TO_STRING'"));
	}

	@Test
	public void testComputation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 = skip END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Missing computation variable.", result.contains("variables(none,[identifier(none,computeM1)])"));
		assertTrue("Missing invariant.", result.contains(
				"invariant(none,member(none,identifier(none,computeM1),set_extension(none,[string(none,'EXECUTED'),string(none,'NOT_EXECUTED'),string(none,'COMPUTATION_DISABLED')])))"));
	}

	@Test
	public void testDefine() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 = DEFINE foo TYPE POW(INTEGER) VALUE {1} END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue("Missing DEFINE variable",
				result.contains("variables(none,[identifier(none,foo),identifier(none,computeM1)])"));
		System.out.println(result);
	}

	@Test
	public void testRuleOperation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_FAIL({}) END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Invalid variables transformation",
				result.contains("variables(none,[identifier(none,foo),identifier(none,foo_Counterexamples)])"));
		assertTrue("Invalid invariant transformation", result.contains(
				"invariant(none,conjunct(none,member(none,identifier(none,foo),set_extension(none,[string(none,'FAIL'),string(none,'SUCCESS'),string(none,'NOT_CHECKED'),string(none,'DISABLED')])),member(none,identifier(none,foo_Counterexamples),pow_subset(none,string_set(none)))))"));
	}

	@Test
	public void testRuleId() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo RULEID id2 = BEGIN RULE_FAIL({\"Rule violated\"}) END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue("Missing rule id in counterexample message.", result.contains("string(none,'id2: ')"));
	}

	@Test
	public void testConstantDependencies() throws Exception {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS RULE foo = SELECT \nCONSTANT_DEPENDENCIES\n(k = TRUE) THEN RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Invalid initialisation", result.contains(
				"initialisation(none,assign(none,[identifier(none,foo)],[if_then_else(none,equal(none,identifier(none,k),boolean_true(none)),string(none,'NOT_CHECKED'),string(none,'DISABLED'))]))"));
	}

	@Test
	public void testDuplicateRuleAssignStatement() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS;\nRULE_SUCCESS  END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue(result.contains(
				"parse_exception(pos(2,1,'UnkownFile'),'Result value of rule operation is assigned more than once')."));
	}

	@Test
	public void testOrdinaryOperationInRulesMachine() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS foo = BEGIN skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		// TODO should not work
	}

	@Test
	public void testRuleFailNoMessage() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_FAIL END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Checking substitution", !result.contains("counterexamples"));
	}

	@Test
	public void testRuleIfThenElse() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN IF 1=1 THEN RULE_SUCCESS ELSE RULE_FAIL END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testSequencingSubstitution() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS;skip END END";
		getRulesMachineAsPrologTerm(testMachine);
		final String testMachine2 = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN skip;RULE_SUCCESS END END";
		getRulesMachineAsPrologTerm(testMachine2);
	}

	@Test
	public void testForLoop() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS foo = \nFOR x IN 1..3 \nDO skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue(result.contains("var(none,[identifier(none,'$SET')"));
	}

	@Test
	public void testSequencingSubstitution2() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS;IF 1=1 THEN skip END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testRuleSkip() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS \nRULE foo = skip END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		// TODO assertFalse(result.contains("exception"));
		System.out.println(result);
	}

	@Test
	public void testAllowedDefinitionCall() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS foo == skip OPERATIONS RULE rule1 = BEGIN RULE_SUCCESS; foo END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testIfElseNoError() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = SELECT 1=1 THEN IF 1=1 THEN skip ELSE skip END ; RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDependsOnRule() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END\n;"
				+ " RULE foo2 = SELECT DEPENDS_ON_RULES(foo) THEN RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));

	}

	@Test
	public void testDefinition() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS foo == 1 END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue("Invalid definition injector", result.contains(
				"machine(abstract_machine(none,machine(none),machine_header(none,test,[]),[definitions(none,[expression_definition(none,foo,[],integer(none,1))])]))."));
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testGoal() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULES(rule1) & 1=1 OPERATIONS RULE rule1 = BEGIN RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testSucceededRules() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_SUCCESS END;"
				+ " RULE foo2 = SELECT SUCCEEDED_RULES(foo) THEN RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);

	}

	@Test
	public void testFailedRules() throws Exception {
		final String testMachine = "MACHINE Test OPERATIONS RULE foo = BEGIN RULE_FAIL END;"
				+ " RULE foo2 = SELECT FAILED_RULES(foo) THEN RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);

	}

	public static String getFileAsPrologTerm(final String file) throws FileNotFoundException, IOException {
		RulesParseUnit unit = new RulesParseUnit();
		unit.readMachineFromFile(new File(file));
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		String result = unit.getResultAsPrologTerm();
		return result;
	}

	public static String getRulesMachineAsPrologTerm(final String content) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		String result = unit.getResultAsPrologTerm();
		return result;
	}

}
