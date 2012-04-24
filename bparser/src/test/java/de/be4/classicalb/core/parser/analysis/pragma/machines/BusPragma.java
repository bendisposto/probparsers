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

public class BusPragma {

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
	public void testBusPragma() throws IOException, BException {
		machine = new File(PATH + "BusPragma.mch");

		final BParser parser = new BParser(machine.getName());
		Start start = parser.parseFile(machine, false);
		start.apply(ids);

		assertEquals(14, parser.getPragmas().size());

		parser.getPragmas().get(0).printProlog(out, ids);
		assertEquals(
				"global_pragma(goto,[hell],[],-1,2,17,2,33,[start,start,eof,start,eof])",
				out.toString());

		// TODO: Are all these globals correct?
		String[] results = {
				"global_pragma(goto,[hell],[],-1,2,17,2,33,[start,start,eof,start,eof])",
				"global_pragma(version,['1.3.5'],[],-1,1,1,1,21,[start,0,1,0,2])",
				"global_pragma(empty,[],[],-1,2,23,2,29,[3,1,4,3,7])",
				"global_pragma(murder,[],[],-1,5,16,5,29,[3,5,6,3,7])",
				"pragma(11,unit,[m],[],-1,8,19,8,32)",
				"pragma(30,symbolic,[],[],-1,11,25,11,40)",
				"pragma(44,label,[only,typing],[],-1,12,19,12,43)",
				"pragma(50,label,[important],[],-1,12,80,12,102)",
				"global_pragma('ltl-assertion',['we can always call inc','GF [inc]'],[],-1,17,1,17,57,[58,1,64,63,66])",
				"pragma(73,symbolic,[],[],-1,22,18,22,33)",
				"pragma(73,conversion,[m,to,cm],[],-1,22,34,22,59)",
				"pragma(80,symbolic,[],[maybe_illplaced],-1,23,16,23,31)",
				"global_pragma('non-negative',[],[],-1,28,33,28,52,[91,90,92,91,92])",
				"global_pragma(pragma_am_ende,[],[],-1,38,1,38,22,[64,1,eof,110,eof])" };

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}
}