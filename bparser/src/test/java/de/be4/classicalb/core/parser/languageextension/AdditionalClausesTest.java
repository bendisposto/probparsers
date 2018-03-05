package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Helpers;

public class AdditionalClausesTest {

	@Test
	public void testExpressionsClause() throws Exception {
		final String testMachine = "MACHINE test EXPRESSIONS foo(a) == 1; bar == TRUE END";
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"[expressions(4,expression(5,foo,identifier(6,a),integer(7,1)),expression(8,bar,boolean_true(9)))]"));
	}

	@Test
	public void testPredicatesClause() throws Exception {
		final String testMachine = "MACHINE test PREDICATES foo(a) == 1=1; bar == 2=2 END";
		final String result = Helpers.getMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"[predicates(4,predicate(5,foo,identifier(6,a),equal(7,integer(8,1),integer(9,1))),predicate(10,bar,equal(11,integer(12,2),integer(13,2))))]"));
	}
}
