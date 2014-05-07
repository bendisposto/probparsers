package de.be4.classicalb.core.parser.predvars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;

public class SatProblem {
	@Test
	public void testAandB() throws Exception {
		final String test = "#PREDICATE"
				+ new Scanner(new File("src/test/resources/predvars/sat_pred"))
						.useDelimiter("\\Z").next();
		final String reference = "#PREDICATE"
				+ new Scanner(new File("src/test/resources/predvars/sat_pred"))
						.useDelimiter("\\Z").next();

		final String result1 = getTreeAsString(test);
		final String result2 = getTreeAsStringOrg(reference);

		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	private String getTreeAsString(final String testMachine) throws BException,
			LexerException, IOException {
		Start ast = BParser.eparse(testMachine);
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
