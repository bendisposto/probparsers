package de.be4.classicalb.core.parser.prolog;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.prolog.RecursiveMachineLoader;
import de.be4.classicalb.core.parser.node.Start;

public class RecursiveMachineLoaderTest {

	
	@Test
	public void testParsedAllMachines() throws Exception {
		File machineFile = new File("src/test/resources/compound/M1.mch");
		final BParser parser = new BParser(machineFile.getName());
		Start start = parser.parseFile(machineFile, false);

		RecursiveMachineLoader r = new RecursiveMachineLoader(
				machineFile.getParent(), parser.getContentProvider());

		List<Pragma> pragmas = new ArrayList<Pragma>();
		pragmas.addAll(parser.getPragmas());
		r.loadAllMachines(machineFile, start, parser.getSourcePositions(),
				parser.getDefinitions(), pragmas);

		assertEquals(2, r.getParsedMachines().size());
	}
}
