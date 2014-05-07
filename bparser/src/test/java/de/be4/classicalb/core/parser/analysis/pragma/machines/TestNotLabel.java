package de.be4.classicalb.core.parser.analysis.pragma.machines;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.PrologTermStringOutput;

public class TestNotLabel {

	private static final String PATH = "src/test/resources/pragmas/";
	private PrologTermStringOutput out;
	private NodeIdAssignment ids;

	private File machine;

	@Before
	public void before() {
		out = new PrologTermStringOutput();
		ids = new NodeIdAssignment();
	}

	// TODO: Is this the intended result? See PROB-239 on Jira.
	@Test
	public void testNotLabel1() throws IOException, BException {
		machine = new File(PATH + "TestNotLabel1.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(1, parser.getPragmas().size());

		String[] results = { "pragma(12,label,['NOT'],[],-1,9,1,9,16)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	// TODO: Is this the intended result? See PROB-239 on Jira.
	@Test
	public void testNotLabel2() throws IOException, BException {
		machine = new File(PATH + "TestNotLabel2.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(2, parser.getPragmas().size());

		String[] results = { "pragma(12,label,['NOT1'],[],-1,9,1,9,17)",
				"pragma(16,label,['NOT2'],[],-1,10,1,10,17)" };

		BParser.printASTasProlog(System.out, parser, machine, start, true,
				true, null);

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}