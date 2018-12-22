package de.be4.classicalb.core.parser.languageextension;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Helpers;

public class IfThenElseExpressionTest {

	@Test
	public void testIfThenElseExpression() throws Exception {
		final String testMachine = "#EXPRESSION IF x < 3 THEN 5 ELSE 17 END";
		final String result = Helpers.parseMachineAndGetPrologOutput(testMachine);
		System.out.println(result);
		assertTrue(result.contains("if_then_else(2,less(3,identifier(4,x),integer(5,3)),integer(6,5),integer(7,17))"));
	}

	@Test
	public void testIfThenElseSubstitution() throws Exception {
		final String testMachine = "#SUBSTITUTION IF x < 3 THEN skip ELSIF 1=1 THEN skip ELSE skip END";
		final String result = Helpers.parseMachineAndGetPrologOutput(testMachine);
		System.out.println(result);

	}

	@Test
	public void testElseIf() throws Exception {
		final String testMachine = "#EXPRESSION IF x < 1 THEN 2 ELSIF x= 3 THEN 4 ELSE 5 END";
		final String result = Helpers.parseMachineAndGetPrologOutput(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"machine(if_then_else(2,less(3,identifier(4,x),integer(5,1)),integer(6,2),if_then_else(7,equal(8,identifier(9,x),integer(10,3)),integer(11,4),integer(12,5))))."));
	}

}
