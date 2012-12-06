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

public class UnitPragmaExpressions {

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
	public void testUnitPragmaExpressions1() throws IOException, BException {
		machine = new File(PATH + "UnitPragmaExpressions1.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(3, parser.getPragmas().size());

		String[] results = {
				"pragma(5,unit,['10**1','*','m**1'],[],-1,3,3,3,27)",
				"pragma(6,unit,['10**1','*','m**1'],[],-1,4,3,4,27)",
				"pragma(7,unit,['10**2','*','m**2'],[],-1,5,3,5,27)", };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testUnitPragmaExpressions2() throws IOException, BException {
		machine = new File(PATH + "UnitPragmaExpressions2.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(3, parser.getPragmas().size());

		String[] results = {
				"global_pragma(unit_alias,[myKMpH,km,'*','s**-1'],[],-1,1,1,1,36,[start,0,1,0,2])",
				"pragma(5,unit,[myKMpH],[],-1,4,3,4,22)",
				"pragma(7,unit,[km,'*','s**-1'],[],-1,6,3,6,25)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}