package de.be4.classicalb.core.parser.analysis.pragma;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import de.be4.classicalb.core.parser.BLexer;
import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.parser.Parser;
import de.be4.classicalb.core.parser.parser.ParserException;
import de.prob.prolog.output.PrologTermStringOutput;

public class LabelShiftTest {

	public File prepareFile(String content) {
		try {
			File temp = File.createTempFile("tempfile", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
			bw.write(content);
			bw.close();
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Test
	public void test1() {
		// MACHINE
		// TestNotLabel
		// /* Below the label NOT is not attached to not but to the aa=1
		// node */
		// CONCRETE_CONSTANTS
		// aa
		// PROPERTIES
		// aa=0
		// ASSERTIONS
		// /*@label NOT */ not(aa=1) => aa=2
		// END

		String machine = "MACHINE \nTestNotLabel \n/* Below the label NOT is not attached to not but to the aa=1 node */ \nCONCRETE_CONSTANTS \naa \nPROPERTIES \naa=0  \nASSERTIONS \n/*@label NOT */ not(aa=1) => aa=2 \nEND";
		
		
		File f = prepareFile(machine);
		try {

			NodeIdAssignment ids = new NodeIdAssignment();
			final BParser parser = new BParser(f.getName());
			Start start = parser.parseFile(f, false);
			start.apply(ids);

			BParser.printASTasProlog(System.out, parser, f, start, true, true,
					null);

			List<Pragma> pragmas = parser.getPragmas();
			for (Pragma p : pragmas) {
				for (int i = 0; i < parser.getPragmas().size(); i++) {
					PrologTermStringOutput out = new PrologTermStringOutput();
					p.printProlog(out, ids);
					System.out.println(out.toString());
				}
			}

		} catch (Exception e) {

		} finally {
			f.delete();
		}
	}

}
