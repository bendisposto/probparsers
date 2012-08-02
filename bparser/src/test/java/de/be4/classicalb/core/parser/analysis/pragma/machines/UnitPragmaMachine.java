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

public class UnitPragmaMachine {

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
	public void testUnitPragmaMachine() throws IOException, BException {
		machine = new File(PATH + "UnitPragmaMachine.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(3, parser.getPragmas().size());

		String[] results = { "global_pragma(unit_alias,[nameOfAlias,km,'[0,h,-1]'],[],-1,1,1,1,42,[start,0,1,0,2])",
				"pragma(5,unit,['[0,u,0]'],[],-1,6,3,6,22)",
				"pragma(6,inferred_unit,['[0,u2,1]'],[],-1,7,3,7,32)",
				 };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}