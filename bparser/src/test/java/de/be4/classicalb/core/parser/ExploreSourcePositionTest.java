package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.analysis.ASTPrinter;
import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.pragma.internal.UnknownPragma;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class ExploreSourcePositionTest {

	@Test
	public void test() throws BException, IOException {
		
		final BParser parser = new BParser("m");
		Start parse = parser.parseFile(new File("src/test/resources/LabelTest.mch"), false);
		

		parse.apply(new ASTPrinter());
		
		List<Pragma> x = parser.getPragmas();
		for (Pragma pragma : x) {
			System.out.println(pragma);
		}
		assertTrue(true);
	}

}
