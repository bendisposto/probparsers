package de.be4.classicalb.core.parser.analysis.pragma.machines;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.PrologTermStringOutput;

public class BusPragmaRandomLineSeparator {
	final String[] busPragmaMachine = {
			"/*@ version 1.3.5 */",
			"MACHINE           Bus /*@ */",
			"",
			"DEFINITIONS",
			"        FFF == /*@ murder */ (4 + 6);",
			"        \"BusPragmaDef.def\"",
			"",
			"VARIABLES         /*@ unit m */tickets, passengers",
			"",
			"INVARIANT          !x.(x : NAT => x:INT) &",
			"                   f2 = /*@ symbolic */ %x.(x:NATURAL1 & x mod 2 = 0|x/2) &",
			"                  /*@ label only typing */ tickets : NAT &  passengers : NAT & /*@ label important */ tickets <= passengers & op = foo(5)",
			"",
			"INITIALISATION    tickets, passengers := 0,0",
			"",
			"",
			"/*@ ltl-assertion \"we can always call inc\" \"GF [inc]\" */",
			"",
			"OPERATIONS",
			"",
			"buy(mm) =",
			"  PRE mm : NAT & /*@ symbolic */ /*@ conversion m to cm */ (tickets + mm) <= passengers",
			"  THEN tickets /*@ symbolic */  := tickets + mm", "END;", "",
			"board(nn) =", "PRE nn : NAT",
			"  THEN passengers := passengers /*@ non-negative */ + nn",
			"  END;", "", "dble =", "BEGIN",
			"tickets, passengers  := 2*tickets, 2*passengers", "END;", "",
			"r <-- foo(x) = BEGIN r := 6 END", "", "/*@ pragma_am_ende */",
			"END" };

	final String[] busPragmaDefinitionFile = { "DEFINITIONS",
			"   gotohell ==  /*@ goto hell */ (x + 3);", "" };
	private final String[] results = {
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

	private static File MACHINE;
	private PrologTermStringOutput out;
	private NodeIdAssignment ids;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void before() throws IOException {
		out = new PrologTermStringOutput();
		ids = new NodeIdAssignment();
	}

	@Test
	public void testBusPragmaFirstSeparator() throws IOException, BException {
		// Test 1: Using \n as line separator
		MACHINE = writeFile("\n");

		final BParser parser = new BParser(MACHINE.getName());
		Start start = parser.parseFile(MACHINE, false);
		start.apply(ids);

		assertEquals(14, parser.getPragmas().size());

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testBusPragmaSecondSeparator() throws IOException, BException {
		// Test 1: Using \n as line separator
		MACHINE = writeFile("\r\n");

		final BParser parser = new BParser(MACHINE.getName());
		Start start = parser.parseFile(MACHINE, false);
		start.apply(ids);

		assertEquals(14, parser.getPragmas().size());

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	@Test
	public void testBusPragmaRandomSeparator() throws IOException, BException {
		// Test 1: Using \n as line separator
		MACHINE = writeFileRandomSeparator();

		final BParser parser = new BParser(MACHINE.getName());
		Start start = parser.parseFile(MACHINE, false);
		start.apply(ids);

		assertEquals(14, parser.getPragmas().size());

		for (int i = 0; i < parser.getPragmas().size(); i++) {
			out = new PrologTermStringOutput();
			parser.getPragmas().get(i).printProlog(out, ids);
			assertEquals(results[i], out.toString());
		}

	}

	File writeFile(String separator) throws IOException {
		File mainMachine = folder.newFile("BusPragma.mch");
		File definitionFile = folder.newFile("BusPragmaDef.def");

		writeLines(separator, mainMachine, busPragmaMachine);
		writeLines(separator, definitionFile, busPragmaDefinitionFile);

		return mainMachine;
	}

	File writeFileRandomSeparator() throws IOException {
		File mainMachine = folder.newFile("BusPragma.mch");
		File definitionFile = folder.newFile("BusPragmaDef.def");

		writeLinesRandomSeparator(mainMachine, busPragmaMachine);
		writeLinesRandomSeparator(definitionFile, busPragmaDefinitionFile);

		return mainMachine;
	}

	void writeLines(String separator, File f, String[] lines)
			throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		for (String line : lines) {
			out.write(line + separator);
		}
		out.close();
	}

	void writeLinesRandomSeparator(File f, String[] lines) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter(f));
		for (String line : lines) {
			if (Math.random() > 0.5) {
				out.write(line + "\r\n");
			} else {
				out.write(line + "\n");
			}
		}
		out.close();
	}
}