package de.be4.classicalb.core.parser.pragmas;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.analysis.prolog.RecursiveMachineLoader;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.Start;

public class FilePragmaTest {

	@Test
	public void testFilePragma() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "Main1.mch";
		File f = new File(file);
		BParser bparser = new BParser();
		Start ast = bparser.parseFile(f, false);
		assertNotNull(ast);
		RecursiveMachineLoader rml = new RecursiveMachineLoader(PATH,
				bparser.getContentProvider());
		rml.loadAllMachines(f, ast, null, bparser.getDefinitions());
	}
	
	@Test
	public void testInvalidUseOfFilePragma() {
		final String testMachine = "MACHINE foo CONSTANTS a PROPERTIES a /*@file \"foo1/foo2.mch\" */  END";
		try {
			getTreeAsString(testMachine);
		} catch (BException e) {
			assertTrue(e.getMessage().startsWith("A file pragma"));
		}
	}
	
	@Test (expected= BException.class)
	public void testFilePragma2() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "Main2.mch";
		File f = new File(file);
		BParser bparser = new BParser();
		Start ast = bparser.parseFile(f, false);
		assertNotNull(ast);
		RecursiveMachineLoader rml = new RecursiveMachineLoader(PATH,
				bparser.getContentProvider());
		rml.loadAllMachines(f, ast, null, bparser.getDefinitions());
	}
	

	@Test
	public void testFilePragmaExtends() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "Extends.mch";
		parseFile(file);
	}
	

	@Test
	public void testFilePragma3() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "Main1.mch";
		parseFile(file);
	}
	
	@Test
	public void testFilePragmaDefinitionsFiles() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "Main3.mch";
		parseFile(file);
	}
	
	@Test (expected = BException.class)
	public void testFileCircle() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/circle/";
		String file = PATH + "Mch1.mch";
		parseFile(file);
	}

	@Test (expected = BException.class)
	public void testInvalidPragmaFile() throws IOException, BException {
		String PATH = "src/test/resources/pragmas/filePragma/";
		String file = PATH + "InvalidPragmaFile.mch";
		parseFile(file);
	}
	
	private static void parseFile(final String filename) throws IOException,
			BException {
		final int dot = filename.lastIndexOf('.');
		if (dot >= 0) {
			final File machineFile = new File(filename);
			final String probfilename = filename.substring(0, dot) + ".prob";

			BParser parser = new BParser(filename);
			Start tree = parser.parseFile(machineFile, false);

			PrintStream output = new PrintStream(probfilename);
			BParser.printASTasProlog(output, parser, machineFile, tree, false,
					true, parser.getContentProvider());
			output.close();
		} else
			throw new IllegalArgumentException("Filename '" + filename
					+ "' has no extension");
	}
	
	private String getTreeAsString(final String testMachine) throws BException {
		// System.out.println("Parsing \"" + testMachine + "\"");
		final BParser parser = new BParser("testcase");
		parser.getOptions().grammar = RuleGrammar.getInstance();
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}
}
