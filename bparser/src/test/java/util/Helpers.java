package util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.extensions.RuleGrammar;
import de.be4.classicalb.core.parser.node.Start;

public class Helpers {

	public String getTreeAsString(final String testMachine) throws BException {
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

	public static void parseFile2(String filename) throws
			IOException {
		final File machineFile = new File(filename);
		final BParser parser = new BParser(machineFile.getAbsolutePath());
		Start tree;
		try {
			tree = parser.parseFile(machineFile, false);
			OutputStream o = new OutputStream() {
				
				@Override
				public void write(int b) throws IOException {
				}
			};
			PrintStream p = new PrintStream(o);
			BParser.printASTasProlog(System.out, parser, machineFile, tree, false,
					false, parser.getContentProvider());
			

		} catch (BException e) {
			OutputStream output = new OutputStream()
		    {
		        private StringBuilder string = new StringBuilder();
		        @Override
		        public void write(int b) throws IOException {
		            this.string.append((char) b );
		        }

		        public String toString(){
		            return this.string.toString();
		        }
		    };
			PrologExceptionPrinter.printException(output, e);
			String result = output.toString();
			System.out.println(result);
		}
		
		//

		// int fullParsing = parser.fullParsing(machineFile, behaviour,
		// System.out,
		// System.err);
		// System.out.println(fullParsing);
	}

	public static void parseFile(final String filename) throws IOException,
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
}
