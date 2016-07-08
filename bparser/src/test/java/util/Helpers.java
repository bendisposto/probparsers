package util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.analysis.Ast2String;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public class Helpers {

	public static String getTreeAsString(final String testMachine)
			throws BException {
		final BParser parser = new BParser("testcase");
		final Start startNode = parser.parse(testMachine, false);

		// startNode.apply(new ASTPrinter());
		final Ast2String ast2String = new Ast2String();
		startNode.apply(ast2String);
		final String string = ast2String.toString();
		// System.out.println(string);
		return string;
	}

	public static String parseFile2(String filename) throws IOException {
		final File machineFile = new File(filename);
		final BParser parser = new BParser(machineFile.getAbsolutePath());
		Start tree;
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};

		try {
			tree = parser.parseFile(machineFile, false);
			PrintStream printStream = new PrintStream(output);
			BParser.printASTasProlog(printStream, parser, machineFile, tree,
					new ParsingBehaviour(), parser.getContentProvider());
			return output.toString();
		} catch (BException e) {
			PrologExceptionPrinter.printException(output, e);
			return output.toString();
		}

		//

		// int fullParsing = parser.fullParsing(machineFile, behaviour,
		// System.out,
		// System.err);
		// System.out.println(fullParsing);
	}

	public static String fullParsing(String filename) {
		final File machineFile = new File(filename);
		final BParser parser = new BParser(machineFile.getAbsolutePath());
		final ParsingBehaviour behaviour = new ParsingBehaviour();
		behaviour.prologOutput = true;
		behaviour.useIndention = false;
		behaviour.addLineNumbers = false;
		behaviour.verbose = true;
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		PrintStream printStream = new PrintStream(output);
		// int fullParsing =
		parser.fullParsing(machineFile, behaviour, printStream, printStream);
		printStream.flush();
		printStream.close();
		return output.toString();
	}


	public static String getMachineAsPrologTerm(String input) throws BException {
		final BParser parser = new BParser("Test");
		Start start = parser.parse(input, true);
		final ParsingBehaviour parsingBehaviour = new ParsingBehaviour();
		parsingBehaviour.prologOutput = true;
		parsingBehaviour.useIndention = false;
		parsingBehaviour.addLineNumbers = false;
		parsingBehaviour.verbose = true;
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			public String toString() {
				return this.string.toString();
			}
		};
		final IPrologTermOutput pout = new PrologTermOutput(output,
				parsingBehaviour.useIndention);
		printAsProlog(start, pout);
		return output.toString();
	}
	
	public static void printAsProlog(final Start start,
			final IPrologTermOutput pout) {
		final NodeIdAssignment nodeIds = new NodeIdAssignment();
		nodeIds.assignIdentifiers(1, start);
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(
				nodeIds);
		final ASTProlog prolog = new ASTProlog(pout, pprinter);

		pout.openTerm("machine");
		// if (lineNumbers) {
		// final SourcePositions src = positions.get(entry.getKey());
		// pprinter.setSourcePositions(src);
		// }
		start.apply(prolog);
		pout.closeTerm();
		pout.fullstop();
		pout.flush();
	}

	public static void parseFile(final String filename) throws IOException,
			BException {
		final int dot = filename.lastIndexOf('.');
		if (dot >= 0) {
			final File machineFile = new File(filename);
			final String probfilename = filename.substring(0, dot) + ".prob";

			BParser parser = new BParser(filename);
			Start tree = parser.parseFile(machineFile, false);

			final ParsingBehaviour behaviour = new ParsingBehaviour();
			behaviour.verbose = true;

			PrintStream output = new PrintStream(probfilename);
			BParser.printASTasProlog(output, parser, machineFile, tree,
					behaviour, parser.getContentProvider());
			output.close();
		} else
			throw new IllegalArgumentException("Filename '" + filename
					+ "' has no extension");
	}

}
