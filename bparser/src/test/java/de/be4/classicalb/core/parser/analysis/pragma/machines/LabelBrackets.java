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

public class LabelBrackets {

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
	public void testLabelBrackets() throws IOException, BException {
		machine = new File(PATH + "LabelBrackets.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(8, parser.getPragmas().size());

		String[] results = { "pragma(19,label,['(1)'],[],-1,6,2,6,20)",
				"pragma(24,label,['(2)'],[],-1,7,2,7,17)",
				"pragma(28,label,['(3)'],[],-1,8,2,8,17)",
				"pragma(33,label,['(4)'],[],-1,9,2,9,17)",
				"pragma(39,label,['(5)'],[],-1,10,2,10,17)",
				"pragma(44,label,['(6)'],[],-1,11,2,11,17)",
				"pragma(49,label,['(7)'],[],-1,12,2,12,17)",
				"pragma(59,label,['(8)'],[],-1,13,2,13,17)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}