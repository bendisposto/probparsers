package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.AOperatorSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TKwPredicateOperator;
import de.be4.classicalb.core.parser.node.TKwSubstitutionOperator;
import de.be4.classicalb.core.rules.project.RulesParseUnit;
import de.be4.classicalb.core.rules.tranformation.RulesMachineVisitor;

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
				"parse_exception(pos(1,35,'UnkownFile'),'A CHOICE substitution is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testGoalIsNotAPredicateException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == skip END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,32,'UnkownFile'),'The GOAL definition must be a predicate.').\n", result);
	}

	@Test
	public void testGoalIsNotAPredicate2Exception() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,32,'UnkownFile'),'The GOAL definition must be a predicate.').\n", result);
	}

	@Test
	public void testNoNumberOfErrorTypesDefinedException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(2, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,55,'UnkownFile'),'Define the number of error types of the rule operation.').\n",
				result);
	}

	@Test
	public void testRuleForallUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FORALL x WHERE x : 1..3 EXPECT 1=1 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnkownFile'),'RULE_FORALL used outside of a RULE operation').\n",
				result);
	}

	@Test
	public void testRuleAnyUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_ANY x WHERE x : 1..3 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnkownFile'),'RULE_ANY used outside of a RULE operation').\n", result);
	}

	@Test
	public void testErrorTypeExceededTheNumberOfErrorTypesException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL(3, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,69,'UnkownFile'),'The error type exceeded the number of error types specified for this rule operation.').\n",
				result);
	}

	@Test
	public void testRuleFailUsedOutsideOfRuleException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FAIL(2, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,52,'UnkownFile'),'RULE_FAIL used outside of a RULE operation').\n", result);
	}

	@Test
	public void testFirstArgumentOfRuleFailMustBeAnIntegerException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(v, \"abc\") END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,45,'UnkownFile'),'The first argument of RULE_FAIL must be an integer.').\n",
				result);
	}

	@Test
	public void testRuleFailShouldHaveAMostTwoArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL(1,2,3) END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,45,'UnkownFile'),'RULE_FAIL has at most two argument.').\n", result);
	}

	@Test(expected = AssertionError.class)
	public void testUnkownSubstitutionOperatorException() throws Exception {
		AOperatorSubstitution operator = new AOperatorSubstitution(new TKwSubstitutionOperator("foo"),
				new ArrayList<PExpression>());
		RulesMachineVisitor rulesMachineVisitor = new RulesMachineVisitor(null, null, null);
		operator.apply(rulesMachineVisitor);
	}

	@Test(expected = AssertionError.class)
	public void testUnkownExpressionOperatorException() throws Exception {
		AOperatorPredicate operator = new AOperatorPredicate(new TKwPredicateOperator("foo"),
				new ArrayList<PExpression>());
		RulesMachineVisitor rulesMachineVisitor = new RulesMachineVisitor(null, null, null);
		operator.apply(rulesMachineVisitor);
	}

	@Test
	public void testSucceededRuleOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The SUCCEEDED_RULE predicate operator expects exactly one argument.').\n",
				result);
	}

	@Test
	public void testSucceededRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The SUCCEEDED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.').\n",
				result);
	}

	@Test
	public void testFailedRuleOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The FAILED_RULE predicate operator expects exactly one argument.').\n",
				result);
	}

	@Test
	public void testFailedRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The FAILED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.').\n",
				result);
	}

	@Test
	public void testNotCheckedRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == NOT_CHECKED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The NOT_CHECKED_RULE predicate operator expects exactly one argument.').\n",
				result);
	}

	@Test
	public void testDisabledRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == DISABLED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,40,'UnkownFile'),'The DISABLED_RULE predicate operator expects exactly one argument.').\n",
				result);
	}

	@Test
	public void testRenamingIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test PROPERTIES a.b = 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,31,'UnkownFile'),'Identifier renaming is not allowed in a RULES_MACHINE.').\n",
				result);
	}

	@Test
	public void testAssignToANonIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f(1) := 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,35,'UnkownFile'),'There must be an identifier on the left side of the assign substitution. A function assignment \\'f(1) := 1\\' is also not permitted.').\n",
				result);
	}

	@Test
	public void testRenamingOperationCallsException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f <-- Foo.bar END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals("parse_exception(pos(1,35,'UnkownFile'),'Renaming of operation names is not allowed.').\n",
				result);
	}

	@Test
	public void testActivationClauseIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ACTIVATION 1=1 ACTIVATION 1=1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertEquals(
				"parse_exception(pos(1,55,'UnkownFile'),'ACTIVATION clause is used more than once in operation \\'foo\\'.').\n",
				result);
	}

	public static String getRulesMachineAsPrologTerm(final String content) {
		RulesParseUnit unit = new RulesParseUnit();
		unit.setMachineAsString(content);
		ParsingBehaviour pb = new ParsingBehaviour();
		pb.addLineNumbers = false;
		unit.setParsingBehaviour(pb);
		unit.parse();
		unit.translate();
		String result = unit.getResultAsPrologTerm();
		return result;
	}
}
