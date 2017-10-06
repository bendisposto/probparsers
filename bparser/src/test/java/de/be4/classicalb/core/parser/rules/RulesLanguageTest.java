package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import static de.be4.classicalb.core.parser.rules.RulesUtil.*;

public class RulesLanguageTest {

	@Test
	public void testSimpleRule() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testRuleClassification() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 CLASSIFICATION SAFTY BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testRuleTags() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 TAGS SAFTY, \"Rule-123\" BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testEnumeratedSet() {
		final String testMachine = "RULES_MACHINE Test SETS foo= {foo1, foo2} END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);

		assertTrue(result.contains("sets(none,[enumerated_set(none,foo,[identifier(none,foo1),identifier(none,foo2)]"));
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testRulePrint() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("EXTERNAL_SUBSTITUTION_PRINT"));
		assertTrue(!result.contains("exception"));
	}

	@Test
	public void testForAllPredicate() {
		String file = "src/test/resources/rules/ForAllPredicate.rmch";
		String result = getFileAsPrologTerm(file);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testRuleForall() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id11 BODY RULE_FORALL x WHERE x : 1..3 EXPECT x > 2 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testDublicateRuleName() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END;RULE rule1 BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("parse_exception"));
		assertTrue(result.contains("Duplicate operation name"));
	}

	@Test
	public void testRuleFailWithoutParametersAndPredicate() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id1 BODY RULE_FAIL COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testRuleFailSequence() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY RULE_FAIL COUNTEREXAMPLE \"fail1\"END; RULE_FAIL COUNTEREXAMPLE \"fail2\"END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		assertTrue(!result.contains("$ResultTuple"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testRuleFail2() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id1 BODY RULE_FAIL x WHEN x : 1..3 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testRuleFailWithoutParametersButWitWhenPredicate() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY RULE_FAIL WHEN 1=1 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testDefinitionInjection() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 \n BODY RULE_FORALL r WHERE r : 1..3 EXPECT 1=2 COUNTEREXAMPLE \"foo\"  END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Does not contain TO_STRING definition.", result.contains("expression_definition(none,'TO_STRING'"));
		assertTrue("Does not contain EXTERNAL_FUNCTION_TO_STRING definition.",
				result.contains("expression_definition(none,'EXTERNAL_FUNCTION_TO_STRING'"));
	}

	@Test
	public void testComputation() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Missing computation variable.", result.contains("variables(none,[identifier(none,computeM1)])"));
		assertTrue("Missing invariant.", result.contains(
				"invariant(none,member(none,identifier(none,computeM1),set_extension(none,[string(none,'EXECUTED'),string(none,'NOT_EXECUTED'),string(none,'COMPUTATION_DISABLED')])))"));
	}

	@Test
	public void testDefine() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY DEFINE foo TYPE POW(INTEGER) VALUE {1} END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Missing DEFINE variable",
				result.contains("variables(none,[identifier(none,computeM1),identifier(none,foo)])"));
		System.out.println(result);
	}

	@Test
	public void testDefine2() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY DEFINE foo TYPE INTEGER DUMMY_VALUE 1 VALUE 2 END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		assertTrue("Missing DEFINE variable",
				result.contains("variables(none,[identifier(none,computeM1),identifier(none,foo)])"));
		System.out.println(result);
	}

	@Test
	public void testRuleCounterexmples() {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL== GET_RULE_COUNTEREXAMPLES(foo) /= {} OPERATIONS RULE foo BODY RULE_FAIL COUNTEREXAMPLE \"fail\" END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
	}

	@Test
	public void testFunction() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS FUNCTION out <-- foo(x) PRECONDITION x : INTEGER BODY out := x + 1 END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
		assertTrue(result.contains("precondition(none,member(none,identifier(none,x),integer_set(none))"));
	}

	@Test
	public void testFunctionPostcondition() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS FUNCTION out <-- foo(x) PRECONDITION x : INTEGER POSTCONDITION out : INTEGER BODY out := x + 1 END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		System.out.println(getRulesMachineAsBMachine(testMachine));
		assertFalse(result.contains("exception"));
		assertTrue(result.contains("assertion(none,member(none,identifier(none,out),integer_set(none)),skip(none))"));
	}

	@Test
	public void testRuleId() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo RULEID id2 BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
		assertTrue(!result.contains("exception"));
		assertTrue("RULEID should not appear in the translated B machine.", !result.contains("id2"));
	}

	@Test
	public void testActivation() {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS RULE foo ACTIVATION k = TRUE BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
		// TODO do not use the prettyprinter
		assertTrue(rulesMachineAsBMachine.contains("foo := IF k=TRUE THEN \"NOT_CHECKED\" ELSE \"DISABLED\" END"));

	}

	@Test
	public void testRulePredicates() {
		String testMachine = "RULES_MACHINE Test\n";
		testMachine += "DEFINITIONS GOAL == \n";
		testMachine += "  DISABLED_RULE(rule1)\n";
		testMachine += "& NOT_CHECKED_RULE(rule1) \n";
		testMachine += "& FAILED_RULE(rule1) \n";
		testMachine += "& FAILED_RULE_ERROR_TYPE(rule1,1) \n";
		testMachine += "& SUCCEEDED_RULE(rule1) & SUCCEEDED_RULE_ERROR_TYPE(rule1,1)\n";
		testMachine += "& SUCCEEDED_RULE(rule1)\n";
		testMachine += "OPERATIONS RULE rule1 BODY skip END\n";
		testMachine += "END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDefineReadItself() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY DEFINE xx TYPE POW(INTEGER) VALUE xx END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "parse_exception(pos(1,86,'UnknownFile'),'Variable \\'xx\\' read before defined.').\n";
		assertEquals(expected, result);
	}

	@Test
	public void testVariableDefinedTwice() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute_x BODY DEFINE x TYPE POW(INTEGER) VALUE {1} END; \n DEFINE x TYPE POW(INTEGER) VALUE {2} END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("exception"));

	}

	@Test
	public void testInvalidComputation() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute_x_y BODY DEFINE x TYPE POW(STRING) VALUE y END \n; DEFINE y TYPE POW(STRING) VALUE {\"foo\"}END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("exception"));
	}

	@Test
	public void testRuleIfThenElse() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN IF 1=1 THEN RULE_SUCCESS ELSE RULE_FAIL END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testNestedForLoop() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS foo = \nFOR x IN 1..3 \nDO FOR y IN 1..3 \nDO skip END END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		assertTrue(result.contains("var(none,[identifier(none,'$SET0')"));
		assertTrue(result.contains("var(none,[identifier(none,'$SET1')"));
	}

	@Test
	public void testForLoopTwoLoopVariables() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS foo = \nFOR x,y IN {1..3}*{TRUE} \nDO skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		// x,y:: {CHOOSE(..)}
		assertTrue(result.contains(
				"becomes_element_of(none,[identifier(none,x),identifier(none,y)],set_extension(none,[definition(none,'CHOOSE',[identifier(none,'$SET0')])"));
	}

	@Test
	public void testRuleSkip() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS \nRULE foo BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));

	}

	@Test
	public void testDependsOnRule() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY skip END\n;"
				+ " RULE foo2 DEPENDS_ON_RULE foo BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDependsOnComputation() {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION compute BODY skip END\n;"
				+ " RULE foo2 DEPENDS_ON_COMPUTATION compute BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testDefinition() {
		final String testMachine = "RULES_MACHINE test DEFINITIONS foo == 1 END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue("Invalid definition injector",
				result.contains("expression_definition(none,foo,[],integer(none,1))"));
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testGoal() {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE(rule1) & 1=1 OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testGetCounterexamples() {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(rule1,2) = {} OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)={}"));
	}

	@Test
	public void testSucceededRuleErrorType() {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE_ERROR_TYPE(rule1, 2) OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)={};"));
	}

	@Test
	public void testFailedRuleErrorType() {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(rule1, 2) OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)/={};"));
	}

	@Test
	public void testVarSubstitution() {
		final String testMachine = "RULES_MACHINE Test INITIALISATION VAR a,b IN a := 1; b:=1 END END";
		final String result = getRulesProjectAsPrologTerm(testMachine);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testReplaces() {
		String testMachine = "RULES_MACHINE Test\n";
		testMachine += "OPERATIONS\n";
		testMachine += "COMPUTATION comp1 BODY skip END;\n";
		testMachine += "COMPUTATION comp2 REPLACES comp1 BODY skip END\n";
		testMachine += "END\n";
		final String prologOutput = getRulesProjectAsPrologTerm(testMachine);
		assertFalse(prologOutput.contains("exception"));
	}

	@Test
	public void testPreferences() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS SET_PREF_TIME_OUT == 1000; SET_PREF_COMPRESSION == FALSE; SET_PREF_TRY_FIND_ABORT == TRUE; SET_PREF_SOME_PREF == \"foo\" END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("SET_PREF_TIME_OUT"));
		assertTrue(result.contains("SET_PREF_TIME_OUT"));
	}

	@Test
	public void testDefineSymbolicLambda() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION comp BODY DEFINE foo TYPE POW(INTEGER) VALUE /*@symbolic */ %x.(x : INTEGER | x ) END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
		assertFalse(result.contains("FORCE"));
	}

	@Test
	public void testDefineSymbolicSetComprehension() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION comp BODY DEFINE foo TYPE POW(INTEGER) VALUE /*@symbolic */ {x| x : INTEGER}END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
		assertFalse(result.contains("FORCE"));
	}

	@Test
	public void testFailedRuleAllErrorTypes() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == FAILED_RULE_ALL_ERROR_TYPES(foo)  OPERATIONS RULE foo BODY skip END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

}
