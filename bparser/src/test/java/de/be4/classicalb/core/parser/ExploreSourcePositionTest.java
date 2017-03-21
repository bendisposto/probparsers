package de.be4.classicalb.core.parser;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.visualisation.ASTPrinter;

public class ExploreSourcePositionTest {

	@Test
	public void test() throws BCompoundException, IOException {

		final BParser parser = new BParser("m");
		Start parse = parser.parseFile(new File("src/test/resources/LabelTest.mch"), false);

		parse.apply(new ASTPrinter());

		assertTrue(true);
	}

}
