package de.be4.classicalb.core.parser.predvars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;

public class PredVarsTest {

	@Test
	public void testAandB() throws Exception {
		final String testMachine = "#FORMULA A & B";
		String res = "#PREDICATE (A=TRUE) & (B=TRUE)";
		final String result1 = getTreeAsString(testMachine);
		final String result2 = getTreeAsStringOrg(res);
		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	@Test
	public void testSemiAandB() throws Exception {
		final String testMachine = "#FORMULA A<3 & B";
		String res = "#PREDICATE (A<3) & (B=TRUE)";
		final String result1 = getTreeAsString(testMachine);
		final String result2 = getTreeAsStringOrg(res);
		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	@Test
	public void testPred() throws Exception {
		final String testMachine = "#FORMULA A<3 & B>9";
		String res = "#PREDICATE (A<3) & (B>9)";
		final String result1 = getTreeAsString(testMachine);
		final String result2 = getTreeAsStringOrg(res);
		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	@Test
	public void testImpl() throws Exception {
		final String testMachine = "#FORMULA A & (B>9 => C) & D";
		String res = "#PREDICATE (A=TRUE) & (B>9 => C=TRUE) & (D=TRUE)";
		final String result1 = getTreeAsString(testMachine);
		final String result2 = getTreeAsStringOrg(res);
		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	private String getTreeAsString(final String testMachine) throws BException,
			LexerException, IOException {
		final BParser parser = new BParser("testcase");
		Start ast = parser.eparse(testMachine, new Definitions());
		final Ast2String ast2String = new Ast2String();
		ast.apply(ast2String);
		final String string = ast2String.toString();
		return string;
	}

	private String getTreeAsStringOrg(final String testMachine)
			throws BException {
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		return string;
	}

}
