package de.prob.cliparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;

public class CliBParser {

	private static final String CLI_SWITCH_VERBOSE = "-v";
	private static final String CLI_SWITCH_VERSION = "-version";
	private static final String CLI_SWITCH_TIME = "-time";
	private static final String CLI_SWITCH_AST = "-ast";
	private static final String CLI_SWITCH_UI = "-ui";
	private static final String CLI_SWITCH_PROLOG = "-prolog";
	private static final String CLI_SWITCH_FASTPROLOG = "-fastprolog";
	private static final String CLI_SWITCH_PROLOG_LINES = "-lineno";
	private static final String CLI_SWITCH_OUTPUT = "-out";
	private static final String CLI_SWITCH_INDENTION = "-indent";

	public static void main(final String[] args) throws IOException {
//		System.out.println("Ready. Press enter");
//		System.in.read();
//		System.out.println("Starting");
		final ConsoleOptions options = createConsoleOptions(args);

		if (options.isOptionSet(CLI_SWITCH_VERSION)) {
			System.out.println(CliBParser.getBuildRevision());
			System.exit(0);
		}

		final String[] arguments = options.getRemainingOptions();
		if (arguments.length != 1) {
			options.printUsage(System.err);
			System.exit(-1);
		}

		final ParsingBehaviour behaviour = new ParsingBehaviour();

		final File f;
		final PrintStream out;
		if (options.isOptionSet(CLI_SWITCH_OUTPUT)) {
			final String filename = options.getOptions(CLI_SWITCH_OUTPUT)[0];
			try {
				out = new PrintStream(filename);
				f = new File(filename);
			} catch (final FileNotFoundException e) {
				if (options.isOptionSet(CLI_SWITCH_PROLOG)) {
					PrologExceptionPrinter.printException(System.err, e,
							filename);
				} else {
					System.err.println("Unable to create file '" + filename
							+ "'");
				}
				System.exit(-1);
				return; // Unreachable, but needed
			}
		} else {
			out = System.out;
			f = null;
		}
		behaviour.out = out;
		behaviour.outputFile = f;
		behaviour.printTime = options.isOptionSet(CLI_SWITCH_TIME);
		behaviour.prologOutput = options.isOptionSet(CLI_SWITCH_PROLOG);
		behaviour.addLineNumbers = options.isOptionSet(CLI_SWITCH_PROLOG_LINES);
		behaviour.useIndention = options.isOptionSet(CLI_SWITCH_INDENTION);
		behaviour.displayGraphically = options.isOptionSet(CLI_SWITCH_UI);
		behaviour.printAST = options.isOptionSet(CLI_SWITCH_AST);
		behaviour.verbose = options.isOptionSet(CLI_SWITCH_VERBOSE);
		behaviour.fastPrologOutput = options.isOptionSet(CLI_SWITCH_FASTPROLOG);

		final File bfile = new File(args[args.length - 1]);
		int returnValue;
		try {
			final BParser parser = new BParser(bfile.getName());
			returnValue = parser.fullParsing(bfile, behaviour, out);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = -4;
		} finally {
			if (options.isOptionSet(CLI_SWITCH_OUTPUT)) {
				out.close();
			}
		}
		System.exit(returnValue);
	}

	private static ConsoleOptions createConsoleOptions(final String[] args) {
		final ConsoleOptions options = new ConsoleOptions();
		options
				.setIntro("BParser (rev. "
						+ CliBParser.getBuildRevision()
						+ ")\nusage: BParser [options] <BMachine file>\n\nAvailable options are:");
		options.addOption(CLI_SWITCH_VERBOSE,
				"Verbose output during lexing and parsing");
		options.addOption(CLI_SWITCH_TIME,
				"Output time used for complete parsing process");
		options.addOption(CLI_SWITCH_AST, "Print AST on standard output");
		options.addOption(CLI_SWITCH_UI, "Show AST as Swing UI");
		options.addOption(CLI_SWITCH_PROLOG, "Show AST as Prolog term");
		options.addOption(CLI_SWITCH_PROLOG_LINES,
				"Put line numbers into prolog terms");
		options.addOption(CLI_SWITCH_OUTPUT, "Specify output file", 1);
		options.addOption(CLI_SWITCH_VERSION,
				"Print the parser version and exit.");
		options.addOption(CLI_SWITCH_FASTPROLOG,
				"Show AST as Prolog term for fast loading (Do not use this representation in your tool! It depends on internal representation of Sicstus Prolog and will very likely change arbitrarily in the future!)");

		try {
			options.parseOptions(args);
		} catch (final IllegalArgumentException e) {
			System.err.println(e.getLocalizedMessage());
			options.printUsage(System.err);
			System.exit(-1);
		}
		return options;
	}

	public static String getBuildRevision() {
		return Utils.getRevisionFromManifest();
	}

}
