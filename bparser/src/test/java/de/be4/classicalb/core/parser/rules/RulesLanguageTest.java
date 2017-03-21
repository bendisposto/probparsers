package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.rules.project.RulesParseUnit;
import de.be4.classicalb.core.parser.util.PrettyPrinter;

public class RulesLanguageTest {

	@Test
	public void testSimpleRule() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}
	
	@Test
	public void testRuleClassification() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 CLASSIFICATION SAFTY BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
	}
	
	@Test
	public void testRuleTags() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 TAGS SAFTY, \"Rule-123\" BODY skip END END";
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
	public void testStringFormat() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w ~w \", 1) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,31,'UnknownFile'),'The number of arguments (1) does not match the number of placeholders (2) in the string.')"));
	}

	@Test
	public void testRuleForall() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id11 BODY RULE_FORALL x WHERE x : 1..3 EXPECT x > 2 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
	}

	@Test
	public void testDublicateRuleName() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 BODY skip END;RULE rule1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("parse_exception"));
		assertTrue(result.contains("Duplicate operation name"));
	}

	@Test
	public void testRuleAny() throws FileNotFoundException, IOException {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE rule1 RULEID id1 BODY RULE_ANY x WHERE x : 1..3 COUNTEREXAMPLE \"fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(!result.contains("exception"));
		String result2 = getRulesMachineAsBMachine(testMachine);
		System.out.println(result2);
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
	public void testDefine2() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION computeM1 BODY DEFINE foo TYPE INTEGER DUMMY_VALUE 1 VALUE 2 END END END";
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
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL(2, \"fail\") END END";
		String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		//TODO do not use the prettyprinter
		assertTrue(result.contains("foo_Counterexamples := foo_Counterexamples\\/{2}*{\"fail\"}"));

	}

	@Test
	public void testRuleCounterexmples() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL== GET_RULE_COUNTEREXAMPLES(foo) /= {} OPERATIONS RULE foo BODY RULE_FAIL( \"fail\") END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
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
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo RULEID id2 BODY RULE_FAIL(\"Rule violated\") END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
		assertTrue(!result.contains("exception"));
		assertTrue("RULEID should not appear in the translated B machine.", !result.contains("id2"));
	}

	@Test
	public void testWritingDefineVariable() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY DEFINE v1 TYPE POW(INTEGER) VALUE {1} END; v1 := {2} END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		final String expected = "parse_exception(pos(1,95,'UnknownFile'),'Identifier \\'v1\\' is not a local variable (VAR). Hence it can not be assigned here.').\n";
		assertEquals(expected, result);
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
		assertTrue(!result.contains("exception"));
		String rulesMachineAsBMachine = getRulesMachineAsBMachine(testMachine);
		System.out.println(rulesMachineAsBMachine);
		//TODO do not use the prettyprinter
		assertTrue(rulesMachineAsBMachine.contains("foo := IF k=TRUE THEN \"NOT_CHECKED\" ELSE \"DISABLED\" END"));
		
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
	public void testDefineReadItself() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY DEFINE xx TYPE POW(INTEGER) VALUE xx END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "parse_exception(pos(1,86,'UnknownFile'),'Variable \\'xx\\' read before defined.').\n";
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
	public void testRuleFailNoMessage() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,45,'UnknownFile'),'RULE_FAIL requires at least one argument.').\n", result);
	}

	@Test
	public void testRuleIfThenElse() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo = BEGIN IF 1=1 THEN RULE_SUCCESS ELSE RULE_FAIL END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testForLoop() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS foo = \nFOR x IN 1..3 \nDO FOR y IN 1..3 \nDO skip END END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		assertTrue(result.contains("var(none,[identifier(none,'$SET0')"));
		assertTrue(result.contains("var(none,[identifier(none,'$SET1')"));
	}

	@Test
	public void testRuleSkip() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS \nRULE foo BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));

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

	@Test
	public void testGetCounterexamples() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(rule1,2) = {} OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)={}"));
	}

	@Test
	public void testSucceededRuleErrorType() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE_ERROR_TYPE(rule1, 2) OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)={};"));
	}

	@Test
	public void testFailedRuleErrorType() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(rule1, 2) OPERATIONS RULE rule1 BODY skip END END";
		final String result = getRulesMachineAsBMachine(testMachine);
		System.out.println(result);
		assertTrue(result.contains("%$x.($x:1..1|rule1_Counterexamples[{$x}])(2)/={};"));
	}

	@Test
	public void testVarSubstitution() throws Exception {
		final String testMachine = "RULES_MACHINE Test INITIALISATION VAR a,b IN a := 1; b:=1 END END";
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
		unit.translate();
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		unit.printPrologOutput(new PrintStream(output), new PrintStream(output));
		return output.toString();
	}

	public static String getRulesMachineAsPrologTerm(final String content) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		unit.translate();

		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		unit.printPrologOutput(new PrintStream(output), new PrintStream(output));
		return output.toString();
	}

	public static String getRulesMachineAsBMachine(final String content) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		if (unit.getCompoundException() != null) {
			throw new RuntimeException(unit.getCompoundException());
		}
		unit.translate();
		if (unit.getCompoundException() != null) {
			throw new RuntimeException(unit.getCompoundException());
		}
		PrettyPrinter pp = new PrettyPrinter();
		unit.getStart().apply(pp);
		return pp.getPrettyPrint();
	}

}
