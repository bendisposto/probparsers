package de.be4.classicalb.core.parser.rules;

import static org.junit.Assert.*;

import org.junit.Test;
import static de.be4.classicalb.core.parser.rules.RulesUtil.*;

public class UnsupportedConstructsTest {

	@Test
	public void testANYIsNotAllowed() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION comp BODY ANY x WHERE x : 1..10 THEN skip END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The ANY substitution is not allowed in a RULES_MACHINE.'"));
	}

	@Test
	public void testBecomesElementOfIsNotAllowed() throws Exception {
		final String testMachine = "RULES_MACHINE test OPERATIONS COMPUTATION comp BODY VAR x IN x :: {1,2} END END END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'The BecomesElementOf substitution (a,b:(P)) is not allowed in a RULES_MACHINE.'"));
	}

	@Test
	public void testDeferredSetsAreNotAllowed() throws Exception {
		final String testMachine = "RULES_MACHINE test SETS D END";
		String result = getRulesProjectAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains("'Deferred sets are not allowed in a RULES_MACHINE.'"));
	}
}
