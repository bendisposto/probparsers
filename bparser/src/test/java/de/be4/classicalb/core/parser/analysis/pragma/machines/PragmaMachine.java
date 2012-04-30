package de.be4.classicalb.core.parser.analysis.pragma.machines;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.PrologTermStringOutput;

public class PragmaMachine {

	private static final String PATH = "src/test/resources/pragmas/";
	private PrologTermStringOutput out;
	private NodeIdAssignment ids;

	private File machine;

	@Before
	public void before() {
		out = new PrologTermStringOutput();
		ids = new NodeIdAssignment();
	}

	@Test
	public void testPragmaMachine() throws IOException, BException {
		machine = new File(PATH + "PragmaMachine.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(8, parser.getPragmas().size());

		String[] results = { "pragma(13,symbolic,[],[],-1,7,9,7,23)",
				"pragma(21,label,[axm2],[],-1,8,3,8,20)",
				"global_pragma(rec_let,[],[],-1,9,2,9,16,[10,9,26,25,27])",
				"pragma(26,label,[axm3],[],-1,9,17,9,34)",
				"pragma(53,label,[axm4],[],-1,10,3,10,20)",
				"pragma(61,label,[thm1],[],-1,13,4,13,21)",
				"pragma(64,label,[thm2],[],-1,14,3,14,20)",
				"pragma(70,label,[inv1],[],-1,18,11,18,27)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}