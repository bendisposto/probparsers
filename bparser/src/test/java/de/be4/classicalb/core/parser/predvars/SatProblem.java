package de.be4.classicalb.core.parser.predvars;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;

public class SatProblem {
	@Test
	public void compareSatPredAndPredVars() throws Exception {
		final File f1 = new File("src/test/resources/predvars/sat_predvars");
		final File f2 = new File("src/test/resources/predvars/sat_pred");

		final Scanner s1 = new Scanner(f1);
		final Scanner s2 = new Scanner(f2);

		final String test = "#PREDICATE" + s1.useDelimiter("\\Z").next();
		final String reference = "#PREDICATE" + s2.useDelimiter("\\Z").next();

		s1.close();
		s2.close();

		final String result1 = getTreeAsString(test);
		final String result2 = getTreeAsStringOrg(reference);

		assertNotNull(result1);
		assertNotNull(result2);
		assertEquals(result1, result2);
	}

	private String getTreeAsString(final String testMachine) throws BCompoundException,
			LexerException, IOException {
		final BParser parser = new BParser("testcase");
		Start ast = parser.eparse(testMachine, new Definitions());
		final Ast2String ast2String = new Ast2String();
		ast.apply(ast2String);
		final String string = ast2String.toString();
		return string;
	}

	private String getTreeAsStringOrg(final String testMachine)
			throws BCompoundException {
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		return string;
	}
}
