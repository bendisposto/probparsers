package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.node.AOperatorExpression;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TKwExpressionOperator;
import de.be4.classicalb.core.parser.node.TKwPredicateOperator;
import de.be4.classicalb.core.parser.node.TKwSubstitutionOperator;

public class RulesLanguageExceptionTest {

	@Test
	public void testMachineParameterException() throws Exception {
		final String testMachine = "RULES_MACHINE Test(a) CONSTRAINTS a : INTEGER END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("parse_exception(pos(1,15"));
		assertTrue(result.contains("A RULES_MACHINE must not have any machine parameters"));
	}

	@Test
	public void testRenamedMachineNameException() throws Exception {
		final String testMachine = "RULES_MACHINE Test.Foo END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("parse_exception(pos(1,15"));
		assertTrue(result.contains("Renaming of a RULES_MACHINE name is not allowed"));
	}

	@Test
	public void testChoiceSubstitutionException() throws Exception {
		final String testMachine = "RULES_MACHINE Test INITIALISATION CHOICE skip OR skip END  END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,35,'UnknownFile'),'A CHOICE substitution is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testGoalIsNotAPredicateException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == skip END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,32,'UnknownFile'),'The GOAL definition must be a predicate.').\n", result);
	}

	@Test
	public void testGoalIsNotAPredicate2Exception() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,32,'UnknownFile'),'The GOAL definition must be a predicate.').\n", result);
	}

	@Test
	public void testNoNumberOfErrorTypesDefinedException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(2, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,55,'UnknownFile'),'The error type exceeded the number of error types specified for this rule operation.').\n",
				result);
	}

	@Test
	public void testInvalidRuleTag() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo TAGS 1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'Expected identifier or string after the TAGS attribute.').\n",
				result);
	}

	@Test
	public void testErrorTypeZeroException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL(0, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,69,'UnknownFile'),'The ERROR_TYPE must be a natural number greater than zero.').\n",
				result);
	}

	@Test
	public void testRuleForallUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FORALL x WHERE x : 1..3 EXPECT 1=1 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnknownFile'),'RULE_FORALL used outside of a RULE operation').\n",
				result);
	}

	@Test
	public void testRuleAnyUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_ANY x WHERE x : 1..3 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnknownFile'),'RULE_ANY used outside of a RULE operation').\n", result);
	}

	@Test
	public void testErrorTypeExceededTheNumberOfErrorTypesException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL(3, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,69,'UnknownFile'),'The error type exceeded the number of error types specified for this rule operation.').\n",
				result);
	}

	@Test
	public void testRuleFailUsedOutsideOfRuleException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FAIL(2, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnknownFile'),'RULE_FAIL used outside of a RULE operation').\n",
				result);
	}

	@Test
	public void testFirstArgumentOfRuleFailMustBeAnIntegerException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(v, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,45,'UnknownFile'),'The first argument of RULE_FAIL must be an integer.').\n",
				result);
	}

	@Test
	public void testRuleFailShouldHaveAMostTwoArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(1,2,3) END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,45,'UnknownFile'),'RULE_FAIL has at most two argument.').\n", result);
	}

	@Test(expected = AssertionError.class)
	public void testUnkownSubstitutionOperatorException() throws Exception {
		AOperatorSubstitution operator = new AOperatorSubstitution(new TKwSubstitutionOperator("foo"),
				new ArrayList<PExpression>());
		RulesMachineChecker rulesMachineVisitor = new RulesMachineChecker(null, null, null, null);
		operator.apply(rulesMachineVisitor);
	}

	@Test(expected = AssertionError.class)
	public void testUnkownPredicateOperatorException() throws Exception {
		AOperatorPredicate operator = new AOperatorPredicate(new TKwPredicateOperator("foo"),
				new ArrayList<PExpression>());
		RulesMachineChecker rulesMachineVisitor = new RulesMachineChecker(null, null, null, null);
		operator.apply(rulesMachineVisitor);
	}

	@Test(expected = AssertionError.class)
	public void testUnkownExpressionOperatorException() throws Exception {
		AOperatorExpression operator = new AOperatorExpression(new TKwExpressionOperator("foo"),
				new ArrayList<PExpression>());
		RulesMachineChecker rulesMachineVisitor = new RulesMachineChecker(null, null, null, null);
		operator.apply(rulesMachineVisitor);
	}

	@Test
	public void testSucceededRuleOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'The SUCCEEDED_RULE predicate operator expects exactly one rule identifier.').\n",
				result);
	}

	@Test
	public void testSucceededRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,40,'UnknownFile'),'The SUCCEEDED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.')"));
	}

	@Test
	public void testFailedRuleOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'The FAILED_RULE predicate operator expects exactly one rule identifier.').\n",
				result);
	}

	@Test
	public void testFailedRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,40,'UnknownFile'),'The FAILED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.')"));
	}

	@Test
	public void testNotCheckedRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == NOT_CHECKED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'The NOT_CHECKED_RULE predicate operator expects exactly one rule identifier.').\n",
				result);
	}

	@Test
	public void testDisabledRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == DISABLED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'The DISABLED_RULE predicate operator expects exactly one rule identifier.').\n",
				result);
	}

	@Test
	public void testRenamingIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test PROPERTIES a.b = 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,31,'UnknownFile'),'Identifier renaming is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testAssignToANonIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f(1) := 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,35,'UnknownFile'),'There must be an identifier on the left side of the assign substitution. A function assignment \\'f(1) := 1\\' is also not permitted.').\n",
				result);
	}

	@Test
	public void testRenamingOperationCallsException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f <-- Foo.bar END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result
				.contains("parse_exception(pos(1,35,'UnknownFile'),'Renaming of operation names is not allowed.')"));
	}

	@Test
	public void testActivationClauseIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ACTIVATION 1=1 ACTIVATION 1=1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,55,'UnknownFile'),'ACTIVATION clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	@Test
	public void testPreconditionInRuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo PRECONDITION 1=1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'PRECONDITION clause is not allowed for a RULE or COMPUTATION operation').\n",
				result);
	}

	@Test
	public void testPreconditionClauseIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS FUNCTION out <-- foo PRECONDITION 1=1 PRECONDITION 1=1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,69,'UnknownFile'),'PRECONDITION clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	@Test
	public void testDependsOnRuleUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE bar DEPENDS_ON_RULE bazz BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,60,'UnknownFile'),'DEPENDS_ON_RULE clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	@Test
	public void testDependsOnRuleInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE 1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'Expected a list of identifiers after DEPENDS_ON_RULE.').\n",
				result);
	}

	@Test
	public void testActivationFunction() throws Exception {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS FUNCTION out <-- foo ACTIVATION k = TRUE BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "parse_exception(pos(1,85,'UnknownFile'),'ACTIVATION is not a valid attribute of a FUNCTION operation.').\n";
		assertEquals(expected, result);
	}

	@Test
	public void testDependsOnComputationUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_COMPUTATION bar DEPENDS_ON_COMPUTATION bazz BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,67,'UnknownFile'),'DEPENDS_ON_COMPUTATION clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	@Test
	public void testDependsOnComputationInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_COMPUTATION 1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'Expected a list of identifiers after DEPENDS_ON_COMPUTATION.').\n",
				result);
	}

	@Test
	public void testRuleIdAttributeIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID req1 RULEID req2 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,52,'UnknownFile'),'RULEID clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	@Test
	public void testRuleIdAttributeInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID 1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,40,'UnknownFile'),'Expected exactly one identifier behind RULEID').\n",
				result);
	}

	@Test
	public void testRuleIdAttributeTwoIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID req1, req2 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,40,'UnknownFile'),'Expected exactly one identifier behind RULEID').\n",
				result);
	}

	@Test
	public void testRuleIdInComputationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION foo RULEID req1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,47,'UnknownFile'),'RULEID is not an attribute of a FUNCTION or Computation operation').\n",
				result);
	}

	@Test
	public void testErrorTypesAttributeNoIntegerValueException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ERROR_TYPES k BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,40,'UnknownFile'),'Expected exactly one integer after ERROR_TYPES.').\n",
				result);
	}

	@Test
	public void testErrorTypesAttributeTwoIntegersException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ERROR_TYPES 1,2 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,40,'UnknownFile'),'Expected exactly one integer after ERROR_TYPES.').\n",
				result);
	}

	@Test
	public void testErrorTypesAttributeInFunctionOrComputationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION foo ERROR_TYPES 2 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,47,'UnknownFile'),'ERROR_TYPES is not an attribute of a FUNCTION or COMPUTATION operation.').\n",
				result);
	}

	@Test
	public void testSeesException() throws Exception {
		final String testMachine = "RULES_MACHINE test SEES foo END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,20,'UnknownFile'),'The SEES clause is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testUsesException() throws Exception {
		final String testMachine = "RULES_MACHINE test USES foo END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,20,'UnknownFile'),'The USES clause is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testGetRuleCounterexamplesException() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(1,2,3) = {} END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,40,'UnknownFile'),'Invalid number of arguments. Expected one or two arguments.')"));
	}

	@Test
	public void testGetRuleCounterexamples2Exception() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(1) = {} END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnknownFile'),'The first argument of GET_RULE_COUNTEREXAMPLES must be an identifier.').\n",
				result);
	}

	@Test
	public void testFunctionReturnValuesNoIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS FUNCTION a /*@desc dd */ <-- foo BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,40,'UnknownFile'),'Identifier expected.').\n", result);
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
}
