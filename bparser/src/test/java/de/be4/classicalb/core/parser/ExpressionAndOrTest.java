package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.node.Start;

public class ExpressionAndOrTest {

	@Test
	public void testFomulaExpression() throws Exception {
		BParser parser = new BParser();
		Start tree = parser.parseFile(new File("src/test/resources/PredicatesAndOrExpressions.mch"), false);
		
		tree.apply(new ASTPrinter());
		
		assertTrue(true);
	}

}
