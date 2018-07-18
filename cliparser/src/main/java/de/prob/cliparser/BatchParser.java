package de.prob.cliparser;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;

/**
 * This is a command line version of the parser that just takes all command line
 * arguments as machines to be parsed, parses them and write the parsed AST as
 * Prolog terms into a matching .prob file
 * 
 * @author plagge
 */
public class BatchParser {

	public static void main(final String[] args) {
		try {
			for (final String filename : args) {
				System.out.print("Parsing machine '" + filename + "' ... ");
				System.out.flush();
				parseFile(filename);
				System.out.println("ok");
			}
		} catch (Exception e) {
			System.out.println();
			System.out.flush();
			e.printStackTrace();
			System.exit(1);
		}
	}

	private static void parseFile(final String filename) throws IOException, BCompoundException {
		final int dot = filename.lastIndexOf('.');
		if (dot >= 0) {
			final File machineFile = new File(filename);
			final String probfilename = filename.substring(0, dot) + ".prob";

			BParser parser = new BParser(filename);
			Start tree = parser.parseFile(machineFile, false);

			PrintStream output = new PrintStream(probfilename);
			BParser.printASTasProlog(output, parser, machineFile, tree, new ParsingBehaviour(),
					parser.getContentProvider());
			output.close();
		} else
			throw new IllegalArgumentException("Filename '" + filename + "' has no extension");
	}
}
