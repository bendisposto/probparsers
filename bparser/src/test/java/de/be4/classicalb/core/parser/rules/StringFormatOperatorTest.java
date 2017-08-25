package de.be4.classicalb.core.parser.rules;

import static de.be4.classicalb.core.parser.rules.RulesUtil.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringFormatOperatorTest {

	@Test
	public void testStringFormat() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w ~w \", 1, 2) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testStringFormatWrongNumberOfArguments() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w ~w \", 1) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,31,'UnknownFile'),'The number of arguments (1) does not match the number of placeholders (2) in the string.')"));
	}

	@Test
	public void testStringFormatWithConcat() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w \" ^  \"~w \", 1, 2) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testStringFormatWithConcatWrongNumberOfArguments() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w \" ^  \"~w \", 1) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertTrue(result.contains(
				"parse_exception(pos(1,31,'UnknownFile'),'The number of arguments (1) does not match the number of placeholders (2) in the string.')"));
	}

	@Test
	public void testStringFormatConcatWithVariableLeft() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(x ^ \" ~w \", 1) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

	@Test
	public void testStringFormatConcatWithVariableRight() {
		final String testMachine = "RULES_MACHINE Test PROPERTIES STRING_FORMAT(\" ~w \" ^ x, 1) = \" 1 2 \" END";
		String result = getRulesMachineAsPrologTerm(testMachine);
		System.out.println(result);
		assertFalse(result.contains("exception"));
	}

}
