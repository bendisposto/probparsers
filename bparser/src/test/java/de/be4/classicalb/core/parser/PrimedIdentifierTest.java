package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;

public class PrimedIdentifierTest {
	private BParser parser = new BParser("testcase");

	@Test
	public void testBecomeSuchSubstitution() throws BCompoundException {
		final String testMachine = "#SUBSTITUTION x : (x$0 = x)";
		final String expected = "Start(ASubstitutionParseUnit(ABecomesSuchSubstitution([AIdentifierExpression([x])],AEqualPredicate(APrimedIdentifierExpression([x]0),AIdentifierExpression([x])))))";
		final String actual = getTreeAsString(testMachine);
		assertEquals(expected, actual);
	}

	@Test
	public void testRestrictedUsage() {
		final String testMachine = "#SUBSTITUTION x : (x = x$5)";
		try {
			getTreeAsString(testMachine);
			fail("exception expected");
		} catch (BCompoundException e) {
			assertTrue(e.getFirstException().getCause() instanceof CheckException);
			// ok
		}
	}

	@Test
	public void testDontPrimedIdentifiersInQuantifiers() {
		final String testMachine = "#PREDICATE !a$0.(a=5 => b=6)";
		try {
			getTreeAsString(testMachine);
			fail("exception expected");
		} catch (BCompoundException e) {
			assertTrue(e.getFirstException().getCause() instanceof BParseException);
			// ok
		}
	}

	@Test(expected = BCompoundException.class)
	public void testPrimedIdentifiersInQuantifiersRestrictedModeFalse()
			throws BCompoundException {
		final String testMachine = "#PREDICATE !a$0.(a$0=5 => b=6)";
		parser.getOptions().restrictPrimedIdentifiers = false;
		getTreeAsString(testMachine);
		// this mode is no longer supported 
	}

	@Test
	public void testPrimedIdentifiersInQuantifiers() throws BCompoundException {
		final String testMachine = "#PREDICATE !a$0.(a$0=5 => b=6)";
		try {
			getTreeAsString(testMachine);
			fail("exception expected");
		} catch (BCompoundException e) {
			assertTrue(e.getCause() instanceof BParseException);
			// ok
		}
	}

	@Before
	public void before() {
		// resetting static variable to default value
		parser.getOptions().restrictPrimedIdentifiers = true;
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException {
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		return ast2String.toString();
	}

}
