package de.be4.classicalb.core.parser.definitions;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;

public class DefinitionsOrderTest {

	private static final String PATH = "src/test/resources/definitions/";

	private File machine;

	@Test
	public void testLinearOrder() throws IOException, BCompoundException {
		machine = new File(PATH + "DefinitionsOccurInLinearOrder.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		assertNotNull(start);
	}

	@Test
	public void testReordered() throws IOException, BCompoundException {
		machine = new File(PATH + "DefinitionsOccurReordered.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		assertNotNull(start);
	}
}