package de.be4.classicalb.core.parser.prolog;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Helpers;

public class FormulaTest {

	
	@Test
	public void testString() throws Exception {
		final String testString = "#FORMULA a = b";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
	
	@Test
	public void testExpression() throws Exception {
		final String testString = "#FORMULA a + b";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
	
	@Test
	public void testSubstitution() throws Exception {
		final String testString = "#FORMULA IF 1=1 THEN skip ELSE 1=1 END ";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
	
	@Test
	public void testAssign() throws Exception {
		final String testString = "#FORMULA a(2),b,c := 1 ";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
	
	@Test
	public void testSequenceAssign() throws Exception {
		final String testString = "#FORMULA (a;b) ";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
	
	@Test
	public void testFunctionCall() throws Exception {
		final String testString = "#FORMULA f(1,2) ";
		final String result = Helpers.getMachineAsPrologTerm(testString);
		System.out.println(result);
	}
}
