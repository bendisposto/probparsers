package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;
import static org.junit.Assert.assertNotNull;
import java.io.IOException;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;

public class AtelierBCompatibilityTest {

	@Test
	public void testSysExtension() throws IOException, BException {
		String PATH = "src/test/resources/atelierb/sys_extension/";
		String file = PATH + "main.sys";
		File f = new File(file);
		BParser bparser = new BParser();
		Start ast = bparser.parseFile(f, false);
		assertNotNull(ast);
		RecursiveMachineLoader rml = new RecursiveMachineLoader(PATH,
				bparser.getContentProvider());
		rml.loadAllMachines(f, ast, null, bparser.getDefinitions(), bparser.getPragmas());
	}
}
