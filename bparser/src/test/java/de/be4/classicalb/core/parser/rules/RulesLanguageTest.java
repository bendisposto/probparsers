package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.rules.project.RulesParseUnit;

public class RulesLanguageTest {

	@Test
	public void testSimpleRule() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testForAllPredicate() throws FileNotFoundException, IOException {
		String file = "src/test/resources/rules/ForAllPredicate.rmch";
		String result = getFileAsPrologTerm(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testRuleForall() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id1 BODY RULE_FORALL x WHERE x : 1..3 EXPECT x > 2 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testRuleAny() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id1 BODY RULE_ANY x WHERE x : 1..3 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testDefinitionInjection() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 \n BODY RULE_FORALL r WHERE r : 1..3 EXPECT 1=2 COUNTEREXAMPLE \"foo\"  END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Does not contain TO_STRING definition.", result.contains("expression_definition(none,'TO_STRING'"));
		assertTrue("Does not contain EXTERNAL_FUNCTION_TO_STRING definition.",
				result.contains("expression_definition(none,'EXTERNAL_FUNCTION_TO_STRING'"));
	}

	@Test
	public void testComputation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Missing computation variable.", result.contains("variables(none,[identifier(none,computeM1)])"));
		assertTrue("Missing invariant.", result.contains(
				"invariant(none,member(none,identifier(none,computeM1),set_extension(none,[string(none,'EXECUTED'),string(none,'NOT_EXECUTED'),string(none,'COMPUTATION_DISABLED')])))"));
	}

	@Test
	public void testDefine() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY DEFINE foo TYPE POW(INTEGER) VALUE {1} END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue("Missing DEFINE variable",
				result.contains("variables(none,[identifier(none,foo),identifier(none,computeM1)])"));
		System.out.println(result);
	}

	@Test
	public void testRuleOperation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL({}) END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Invalid variables transformation",
				result.contains("variables(none,[identifier(none,foo),identifier(none,foo_Counterexamples)])"));
		assertTrue("Invalid invariant transformation", result.contains(
				"invariant(none,conjunct(none,member(none,identifier(none,foo),set_extension(none,[string(none,'FAIL'),string(none,'SUCCESS'),string(none,'NOT_CHECKED'),string(none,'DISABLED')]))"));

		assertTrue("Invalid invariant transformation", result.contains(
				"member(none,identifier(none,foo_Counterexamples),pow_subset(none,mult_or_cart(none,natural_set(none),string_set(none)))"));

	}

	@Test
	public void testRuleFailErrorType() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(1, {}) END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue(result.contains(
				"member(none,identifier(none,foo_Counterexamples),pow_subset(none,mult_or_cart(none,natural_set(none),string_set(none))))"));
		System.out.println(result);
	}

	@Test
	public void testFunction() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS FUNCTION out <-- foo(x) PRECONDITION x : INTEGER BODY out := x + 1 END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
		assertTrue(result.contains("precondition(none,member(none,identifier(none,x),integer_set(none))"));
	}

	@Test
	public void testRuleId() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo RULEID id2 BODY RULE_FAIL({\"Rule violated\"}) END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Missing rule id in counterexample message.", result.contains("string(none,'id2: ')"));
	}

	@Test
	public void testRuleFail() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL({\"1\"});RULE_FAIL({\"2\"}) END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testActivation() throws Exception {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS RULE foo ACTIVATION k = TRUE BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "machine(abstract_machine(none,machine(none),machine_header(none,'Test',[]),[variables(none,[identifier(none,foo),identifier(none,foo_Counterexamples)]),invariant(none,conjunct(none,member(none,identifier(none,foo),set_extension(none,[string(none,'FAIL'),string(none,'SUCCESS'),string(none,'NOT_CHECKED'),string(none,'DISABLED')])),member(none,identifier(none,foo_Counterexamples),pow_subset(none,mult_or_cart(none,natural_set(none),string_set(none)))))),initialisation(none,sequence(none,[assign(none,[identifier(none,foo)],[if_then_else(none,equal(none,identifier(none,k),boolean_true(none)),string(none,'NOT_CHECKED'),string(none,'DISABLED'))]),assign(none,[identifier(none,foo_Counterexamples)],[empty_set(none)])])),constants(none,[identifier(none,k)]),properties(none,equal(none,identifier(none,k),boolean_false(none))),operations(none,[operation(none,identifier(none,foo),[identifier(none,'#RESULT'),identifier(none,'#COUNTEREXAMPLES')],[],select(none,conjunct(none,equal(none,identifier(none,foo),string(none,'NOT_CHECKED')),member(none,identifier(none,'#COUNTEREXAMPLES'),pow_subset(none,mult_or_cart(none,integer_set(none),string_set(none))))),sequence(none,[skip(none),if(none,equal(none,identifier(none,foo_Counterexamples),empty_set(none)),assign(none,[identifier(none,foo),identifier(none,'#RESULT'),identifier(none,'#COUNTEREXAMPLES')],[string(none,'SUCCESS'),string(none,'SUCCESS'),empty_set(none)]),[],assign(none,[identifier(none,foo),identifier(none,'#RESULT'),identifier(none,'#COUNTEREXAMPLES')],[string(none,'FAIL'),string(none,'FAIL'),identifier(none,foo_Counterexamples)]))]),[]))])])).\n";
		assertEquals(expected, result);
	}

	@Test
	public void testRulePredicates() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == DISABLED_RULE(rule1)\n"
				+ "& NOT_CHECKED_RULE(rule1) & \nFAILED_RULE(rule1) & \nFAILED_RULE_ERROR_TYPE(rule1,1) \n"
				+ "& SUCCEEDED_RULE(rule1) & SUCCEEDED_RULE_ERROR_TYPE(rule1,1)\n"
				+ "OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testActivationFunction() throws Exception {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS FUNCTION out <-- foo ACTIVATION k = TRUE BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "parse_exception(pos(1,85,'UnkownFile'),'ACTIVATED is not a valid attribute of a FUNCTION operation.').\n";
		assertEquals(expected, result);
	}

	@Test
	public void testVariableDefinedTwice() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute_x BODY DEFINE x TYPE POW(INTEGER) VALUE {1} END; \n DEFINE x TYPE POW(INTEGER) VALUE {2} END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("exception"));

	}

	@Test
	public void testInvalidComputation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute_x_y BODY DEFINE x TYPE POW(STRING) VALUE y END \n; DEFINE y TYPE POW(STRING) VALUE {\"foo\"}END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("exception"));
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
	public void testRuleSkip() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS \nRULE foo = skip END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		// TODO assertFalse(result.contains("exception"));
		System.out.println(result);
	}

	@Test
	public void testAllowedDefinitionCall() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS foo == skip OPERATIONS RULE rule1 BODY RULE_SUCCESS; foo END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDependsOnRule() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_SUCCESS END\n;"
				+ " RULE foo2 DEPENDS_ON_RULE foo BODY RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDependsOnComputation() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute BODY skip END\n;"
				+ " RULE foo2 DEPENDS_ON_COMPUTATION compute BODY skip END END";
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
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE(rule1) & 1=1 OPERATIONS RULE rule1 BODY RULE_SUCCESS END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
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
