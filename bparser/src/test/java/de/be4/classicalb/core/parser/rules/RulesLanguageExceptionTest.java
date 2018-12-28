package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.be4.classicalb.core.parser.node.AOperatorExpression;
import de.be4.classicalb.core.parser.node.AOperatorPredicate;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TKwExpressionOperator;
import de.be4.classicalb.core.parser.node.TKwPredicateOperator;

import static de.be4.classicalb.core.parser.rules.RulesUtil.*;

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
		assertTrue(result.contains("parse_exception(pos(1,15,1,23"));
		assertTrue(result.contains("Renaming of a RULES_MACHINE name is not allowed"));
	}

	@Test
	public void testOperatorFailedRulesErrorTypeWithSecondArgumentNotAnIntegerLiteral() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(rule, 1+1) OPERATIONS RULE rule BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END  END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The second argument of FAILED_RULE_ERROR_TYPE must be an integer literal.'"));
	}

	@Test
	public void testChoiceSubstitutionException() throws Exception {
		final String testMachine = "RULES_MACHINE Test INITIALISATION CHOICE RULE_FAIL COUNTEREXAMPLE \"never\" END OR RULE_FAIL COUNTEREXAMPLE \"never\" END END  END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'A CHOICE substitution is not allowed in a RULES_MACHINE.'"));
	}

	@Test
	public void testGoalIsNotAPredicateException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == skip END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The GOAL definition must be a predicate.'"));
	}

	@Test
	public void testGoalIsNotAPredicate2Exception() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The GOAL definition must be a predicate.'"));
	}

	@Test
	public void testNoNumberOfErrorTypesDefinedException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL ERROR_TYPE 2 COUNTEREXAMPLE \"abc\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result
				.contains("'The error type exceeded the number of error types specified for this rule operation.'"));
	}

	@Test
	public void testErrorTypeIsNotImplemented() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL ERROR_TYPE 2 COUNTEREXAMPLE \"abc\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Error type \\'1\\' is not implemented in rule \\'foo\\'"));
	}

	@Test
	public void testErrorTypeIsNotImplemented2() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
	}

	@Test
	public void testInvalidRuleTag() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo TAGS 1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Expected identifier or string after the TAGS attribute."));
	}

	@Test
	public void testErrorTypeZeroException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL ERROR_TYPE 0 COUNTEREXAMPLE \"abc\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The ERROR_TYPE must be a natural number greater than zero.'"));
	}

	@Test
	public void testRuleForallUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FORALL x WHERE x : 1..3 EXPECT 1=1 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'RULE_FORALL used outside of a RULE operation.'"));
	}
	
	
	@Test
	public void testDefineUsedInRuleException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY FOR i IN {1} DO DEFINE x TYPE POW(INTEGER) VALUE {i} END END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'A DEFINE substitution must be contained in a loop substitution.'"));
	}

	@Test
	public void testRuleFailUsedOutsideOfARuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS COMPUTATION foo BODY RULE_FAIL x WHEN x : 1..3 COUNTEREXAMPLE \"Fail\"END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'RULE_FAIL used outside of a RULE operation.'"));
	}

	@Test
	public void testErrorTypeExceededTheNumberOfErrorTypesException() throws Exception {
		final String testMachine = "RULES_MACHINE Test OPERATIONS RULE foo ERROR_TYPES 2 BODY RULE_FAIL ERROR_TYPE 3 COUNTEREXAMPLE \"abc\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result
				.contains("'The error type exceeded the number of error types specified for this rule operation.'"));
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
		assertTrue(result.contains("'The SUCCEEDED_RULE predicate operator expects exactly one rule identifier.'"));
	}

	@Test
	public void testSucceededRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == SUCCEEDED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(
				result.contains("'The SUCCEEDED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.'"));
	}

	@Test
	public void testFailedRuleOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The FAILED_RULE predicate operator expects exactly one rule identifier.'"));
	}

	@Test
	public void testFailedRuleErrorTypeOperatorWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == FAILED_RULE_ERROR_TYPE(1,2,3) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The FAILED_RULE_ERROR_TYPE predicate operator expects exactly two arguments.'"));
	}

	@Test
	public void testNotCheckedRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == NOT_CHECKED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The NOT_CHECKED_RULE predicate operator expects exactly one rule identifier.'"));
	}

	@Test
	public void testDisabledRuleWrongNumberOfArgumentsException() throws Exception {
		final String testMachine = "RULES_MACHINE Test DEFINITIONS GOAL == DISABLED_RULE(1,2) END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The DISABLED_RULE predicate operator expects exactly one rule identifier.'"));
	}

	@Test
	public void testRenamingIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test PROPERTIES a.b = 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Identifier renaming is not allowed in a RULES_MACHINE.'"));
	}

	@Test
	public void testAssignToANonIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f(1) := 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"'There must be an identifier on the left side of the assign substitution. A function assignment \\'f(1) := 1\\' is also not permitted.'"));
	}

	@Test
	public void testRenamingOperationCallsException() throws Exception {
		final String testMachine = "RULES_MACHINE test INITIALISATION f <-- Foo.bar END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Renaming of operation names is not allowed.'"));
	}

	@Test
	public void testActivationClauseIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ACTIVATION 1=1 ACTIVATION 1=1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'ACTIVATION clause is used more than once in operation \\'foo\\'."));
	}

	@Test
	public void testPreconditionInRuleOperationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo PRECONDITION 1=1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'PRECONDITION clause is not allowed for a RULE or COMPUTATION operation.'"));
	}

	@Test
	public void testPreconditionClauseIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS FUNCTION out <-- foo PRECONDITION 1=1 PRECONDITION 1=1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'PRECONDITION clause is used more than once in operation \\'foo\\'."));
	}

	@Test
	public void testDependsOnRuleUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE bar DEPENDS_ON_RULE bazz BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'DEPENDS_ON_RULE clause is used more than once in operation \\'foo\\'."));
	}

	@Test
	public void testDependsOnRuleInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_RULE 1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Expected a list of identifiers after DEPENDS_ON_RULE.'"));
	}

	@Test
	public void testActivationFunction() throws Exception {
		final String testMachine = "RULES_MACHINE Test CONSTANTS k PROPERTIES k = FALSE OPERATIONS FUNCTION out <-- foo ACTIVATION k = TRUE BODY skip END END";
		final String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'ACTIVATION is not a valid attribute of a FUNCTION operation.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testDependsOnComputationUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_COMPUTATION bar DEPENDS_ON_COMPUTATION bazz BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'DEPENDS_ON_COMPUTATION clause is used more than once in operation \\'foo\\'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testDependsOnComputationInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo DEPENDS_ON_COMPUTATION 1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Expected a list of identifiers after DEPENDS_ON_COMPUTATION.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRuleIdAttributeIsUsedMoreThanOnceException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID req1 RULEID req2 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'RULEID clause is used more than once in operation \\'foo\\'.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRuleIdAttributeInvalidIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID 1 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Expected exactly one identifier behind RULEID.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRuleIdAttributeTwoIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo RULEID req1, req2 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Expected exactly one identifier behind RULEID.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRuleIdInComputationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION foo RULEID req1 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'RULEID is not an attribute of a FUNCTION or Computation operation.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testErrorTypesAttributeNoIntegerValueException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ERROR_TYPES k BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Expected exactly one integer after ERROR_TYPES.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testErrorTypesAttributeTwoIntegersException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo ERROR_TYPES 1,2 BODY RULE_FAIL COUNTEREXAMPLE \"never\" END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Expected exactly one integer after ERROR_TYPES.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testErrorTypesAttributeInFunctionOrComputationException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION foo ERROR_TYPES 2 BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'ERROR_TYPES is not an attribute of a FUNCTION or COMPUTATION operation.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testSeesException() throws Exception {
		final String testMachine = "RULES_MACHINE test SEES foo END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'The SEES clause is not allowed in a RULES_MACHINE.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testUsesException() throws Exception {
		final String testMachine = "RULES_MACHINE test USES foo END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'The USES clause is not allowed in a RULES_MACHINE.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testGetRuleCounterexamplesException() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(1,2,3) = {} END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Invalid number of arguments. Expected one or two arguments.'"));
	}

	@Test
	public void testGetRuleCounterexamples2Exception() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(1) = {} END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'The first argument of GET_RULE_COUNTEREXAMPLES must be an identifier.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testGetRuleCounterexamples3Exception() throws Exception {
		final String testMachine = "RULES_MACHINE test DEFINITIONS GOAL == GET_RULE_COUNTEREXAMPLES(foo) = {} END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'\\'foo\\' does not match any rule visible to this machine.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testIdentifierAlreadyExists() throws Exception {
		final String testMachine = "RULES_MACHINE test CONSTANTS k, k PROPERTIES k = 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "Identifier already exists.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRenamingIsNotAllowed() throws Exception {
		final String testMachine = "RULES_MACHINE test CONSTANTS k PROPERTIES k.a = 1 END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Identifier renaming is not allowed in a RULES_MACHINE.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testRuleFailWithParameterButWithoutWHEN() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS RULE foo BODY RULE_FAIL x COUNTEREXAMPLE x END END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'The WHEN predicate must be provided if RULE_FAIL has at least one parameter.'";
		assertTrue(result.contains(expected));
	}

	@Test
	public void testFunctionReturnValuesNoIdentifierException() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS FUNCTION a /*@desc dd */ <-- foo BODY skip END END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		String expected = "'Identifier expected.'";
		assertTrue(result.contains(expected));
	}
}
