package de.be4.classicalb.core.parser.analysis.pragma.machines;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.pragma.internal.UnknownPragma;
import de.be4.classicalb.core.parser.exceptions.BException;

public class PragmaMachines {

	private static final String PATH = "src/test/resources/pragmas/";

	private File machine;

	@Test
	public void testBusPragma() throws IOException, BException {
		machine = new File(PATH + "BusPragma.mch");
		final BParser parser = new BParser(machine.getName());
		parser.parseFile(machine, false);

		assertEquals(14, parser.getPragmas().size());

		assertTrue(parser.getPragmas().get(0) instanceof UnknownPragma);
		assertEquals(
				"Pragma 'goto hell' (2,17)-(2,33). Right after 'Start/na', inside 'AExpressionDefinitionDefinition' and before 'AAddExpression/AIdentifierExpression'",
				parser.getPragmas().get(0).toString());
	}
}