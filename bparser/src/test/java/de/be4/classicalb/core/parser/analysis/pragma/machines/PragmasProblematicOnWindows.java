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

public class PragmasProblematicOnWindows {

	private static final String PATH = "src/test/resources/problematicOnWindows/";
	private PrologTermStringOutput out;
	private NodeIdAssignment ids;

	private File machine;

	@Before
	public void before() {
		out = new PrologTermStringOutput();
		ids = new NodeIdAssignment();
	}

	@Test
	public void testAssertionPragmas() throws IOException, BException {
		machine = new File(PATH + "AssertionPragmas.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(13, parser.getPragmas().size());

		String[] results = {
				"pragma(13,label,[axm1],[],-1,6,2,6,19)",
				"pragma(18,label,[axm2],[],-1,7,2,7,19)",
				"pragma(23,label,[axm3],[],-1,8,2,8,19)",
				"pragma(28,label,[axm4],[],-1,9,2,9,19)",
				"pragma(33,label,[axm5,gt],[],-1,10,2,10,22)",
				"pragma(33,label,['a greater than b'],[],-1,10,23,10,54)",
				"pragma(33,label,[last],[],-1,10,55,10,72)",
				"pragma(33,label,[a,gt,b],[],-1,11,2,11,21)",
				"pragma(33,label,['a  *is*  greater than b'],[],-1,11,22,11,60)",
				"pragma(33,label,['\\'this',quote,is,not,closed],[],-1,12,2,12,40)",
				"pragma(38,label,[thm1],[],-1,14,2,14,19)",
				"pragma(43,label,[thm2],[],-1,15,2,15,19)",
				"pragma(48,label,[thm3],[],-1,16,2,16,19)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testInfiniteRelationImage() throws IOException, BException {
		machine = new File(PATH + "InfiniteRelationImage.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(1, parser.getPragmas().size());

		String[] results = { "pragma(15,symbolic,[],[],-1,7,13,7,28)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testSymbolicPragmas() throws IOException, BException {
		machine = new File(PATH + "SymbolicPragmas.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(1, parser.getPragmas().size());

		String[] results = { "pragma(23,symbolic,[],[],-1,7,2,7,17)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testTautologiesPL() throws IOException, BException {
		machine = new File(PATH + "TautologiesPL.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(1, parser.getPragmas().size());

		String[] results = { "pragma(254,label,[missing_closing_paren],[],-1,56,4,56,38)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testTuringMachine() throws IOException, BException {
		machine = new File(PATH + "TuringMachine_Goedelisation.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(2, parser.getPragmas().size());

		String[] results = { "pragma(135,symbolic,[],[],-1,27,10,27,25)",
				"pragma(190,symbolic,[],[],-1,31,12,31,27)" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}