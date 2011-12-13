package de.be4.classicalb.core.parser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;

public class PrimedIdentifierTest  {
	private BParser parser = new BParser("testcase");

	@Test
	public void testBecomeSuchSubstitution() throws BException {
		final String testMachine = "#SUBSTITUTION x : (x$0 = x)";
		parser.getOptions().restrictPrimedIdentifiers = true;
		final String expected = "Start(ASubstitutionParseUnit(ABecomesSuchSubstitution([AIdentifierExpression([x])],AEqualPredicate(APrimedIdentifierExpression([x]0),AIdentifierExpression([x])))))";
		final String actual = getTreeAsString(testMachine);
		assertEquals(expected, actual);
	}

	@Test
	public void testRestrictedUsage() {
		final String testMachine = "#SUBSTITUTION x := (x$0 + 1)";
		parser.getOptions().restrictPrimedIdentifiers = true;
		try {
			getTreeAsString(testMachine);
			fail("exception expected");
		} catch (BException e) {
			assertTrue(e.getCause() instanceof CheckException);
			// ok
		}
	}

	@Test
	public void testDontPrimedIdentifiersInQuantifiers() {
		final String testMachine = "#PREDICATE !a$0.(a=5 => b=6)";
		parser.getOptions().restrictPrimedIdentifiers = true;
		try {
			getTreeAsString(testMachine);
			fail("exception expected");
		} catch (BException e) {
			assertTrue(e.getCause() instanceof CheckException);
			// ok
		}
	}

	@Test
	public void testPrimedIdentifiersInQuantifiers() throws BException {
		final String testMachine = "#PREDICATE !a$0.(a$0=5 => b=6)";
		parser.getOptions().restrictPrimedIdentifiers = false;
		getTreeAsString(testMachine);
	}

	private String getTreeAsString(final String testMachine) throws BException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}

}
