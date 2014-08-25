package de.prob.cliparser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.IFileContentProvider;
import de.be4.classicalb.core.parser.MockedDefinitions;
import de.be4.classicalb.core.parser.NoContentProvider;
import de.be4.classicalb.core.parser.ParsingBehaviour;
import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.PrologExceptionPrinter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.ltl.core.parser.CtlParser;
import de.be4.ltl.core.parser.LtlParseException;
import de.be4.ltl.core.parser.LtlParser;
import de.be4.ltl.core.parser.TemporalLogicParser;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.PrologTermStringOutput;
import de.prob.prolog.term.PrologTerm;

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
	private static final String CLI_SWITCH_PREPL = "-prepl";

	private static final String osEncoding = System
			.getProperty("file.encoding");
	private static final String encoding = "MacRoman".equals(osEncoding) ? "UTF-8"
			: osEncoding;

	public static void main(final String[] args) throws IOException {
		// System.out.println("Ready. Press enter");
		// System.in.read();
		// System.out.println("Starting");
		final ConsoleOptions options = createConsoleOptions(args);

		if (options.isOptionSet(CLI_SWITCH_VERSION)) {
			System.out.println(CliBParser.getBuildRevision());
			System.exit(0);
		}

		final String[] arguments = options.getRemainingOptions();
		if (!options.isOptionSet(CLI_SWITCH_PREPL) && arguments.length != 1) {
			options.printUsage(System.err);
			System.exit(-1);
		}

		final ParsingBehaviour behaviour = new ParsingBehaviour();

		final File f;
		PrintStream out;
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

		if (options.isOptionSet(CLI_SWITCH_PREPL)) {
			runPRepl(behaviour);
		} else {
			String filename = args[args.length - 1];
			final File bfile = new File(filename);
			int returnValue;
			if (options.isOptionSet(CLI_SWITCH_OUTPUT)) {
				returnValue = doFileParsing(behaviour, out, System.err, true,
						bfile);
			} else {
				returnValue = doFileParsing(behaviour, out, System.err, false,
						bfile);
			}
			System.exit(returnValue);
		}
	}

	private static void runPRepl(final ParsingBehaviour behaviour)
			throws IOException, FileNotFoundException {

		PrintStream out;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		IDefinitions context = new MockedDefinitions();
		IFileContentProvider provider = new NoContentProvider();
		boolean terminate = false;
		while (!terminate) {
			line = in.readLine();

			EPreplCommands command;
			String theFormula;

			if (line == null) {
				// the prob instance has been terminated. exit gracefully
				command = EPreplCommands.halt;
			} else {
				command = EPreplCommands.valueOf(line);
			}

			switch (command) {
			case definition:
				String name = in.readLine();
				String type = in.readLine();
				String parameterCount = in.readLine();
				if (context instanceof Definitions) {
					context = new MockedDefinitions();
				}
				((MockedDefinitions) context).addMockedDefinition(name, type,
						parameterCount);
				break;
			case machine:
				String filename = in.readLine();
				String outFile = in.readLine();
				out = new PrintStream(outFile, encoding);
				final File bfile = new File(filename);

				int returnValue;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				try {
					final BParser parser = new BParser(bfile.getName());
					returnValue = parser.fullParsing(bfile, behaviour, out, ps);
					context = parser.getDefinitions();
					provider = parser.getContentProvider();
				} catch (Exception e) {
					e.printStackTrace();
					returnValue = -4;
				} finally {
					if (true) {
						out.close();
					}
				}

				if (returnValue == 0) {
					System.out.println("exit(" + returnValue + ").");
				} else {
					String output = baos.toString().replace("\n", " ").trim();
					System.out.println(output);
				}
				break;
			case formula:
				theFormula = "#FORMULA " + in.readLine();
				parseFormula(theFormula, context, provider);
				break;
			case expression:
				theFormula = "#EXPRESSION " + in.readLine();
				parseFormula(theFormula, context, provider);
				break;
			case predicate:
				theFormula = "#PREDICATE " + in.readLine();
				parseFormula(theFormula, context, provider);
				break;
			case extendedexpression:
				theFormula = "#EXPRESSION " + in.readLine();
				parseFormula(theFormula, context, provider);
				break;
			case extendedpredicate:
				theFormula = "#PREDICATE " + in.readLine();
				parseExtendedFormula(theFormula, context, provider);
				break;
			case ltl:
				String extension = in.readLine();
				final ProBParserBase extParser = LtlConsoleParser
						.getExtensionParser(extension);
				final TemporalLogicParser<?> parser = new LtlParser(extParser);

				parseTemporalFormula(in, parser);

				break;
			case ctl:
				String extension2 = in.readLine();
				final ProBParserBase extParser2 = LtlConsoleParser
						.getExtensionParser(extension2);
				final TemporalLogicParser<?> parser2 = new CtlParser(extParser2);
				parseTemporalFormula(in, parser2);
				break;

			case halt:
				terminate = true;
				break;
			default:
				throw new UnsupportedOperationException("Unsupported Command "
						+ line);
			}

		}
	}

	private static void parseTemporalFormula(BufferedReader in,
			final TemporalLogicParser<?> parser) throws IOException {
		String theFormula;
		PrologTermStringOutput strOutput = new PrologTermStringOutput();
		theFormula = in.readLine();

		try {
			final PrologTerm term = parser.generatePrologTerm(theFormula, null);
			strOutput.openTerm("ltl").printTerm(term).closeTerm();
		} catch (LtlParseException e) {
			strOutput.openTerm("syntax_error")
					.printAtom(e.getLocalizedMessage()).closeTerm();
		}

		strOutput.fullstop();

		// A Friendly Reminder: strOutput includes a newline!
		print(strOutput.toString());
	}

	private static void parseExtendedFormula(String theFormula,
			IDefinitions context, IFileContentProvider provider) {
		try {
			BParser parser = new BParser();
			parser.setDefinitions(context);
			Start start = parser.eparse(theFormula, context);

			PrologTermStringOutput strOutput = new PrologTermStringOutput();
			ASTProlog printer = new ASTProlog(strOutput, null);
			start.apply(printer);
			strOutput.fullstop();

			// A Friendly Reminder: strOutput includes a newline!
			print(strOutput.toString());
		} catch (NullPointerException e) {
			// Not Parseable - Sadly, calling e.getLocalizedMessage() on the
			// NullPointerException returns NULL itself, thus triggering another
			// NullPointerException in the catch statement. Therefore we need a
			// second catch statement with a special case for the
			// NullPointerException instead of catching a general Exception
			print("EXCEPTION NullPointerException\n");
		} catch (Exception e) {
			print("EXCEPTION " + e.getLocalizedMessage().replace("\n", " ")
					+ "\n");
		}
	}

	private static void parseFormula(String theFormula, IDefinitions context,
			IFileContentProvider provider) {
		try {
			BParser parser = new BParser();
			parser.setDefinitions(context);
			Start start = parser.parse(theFormula, false, provider);
			PrologTermStringOutput strOutput = new PrologTermStringOutput();
			ASTProlog printer = new ASTProlog(strOutput, null);
			start.apply(printer);
			strOutput.fullstop();

			// A Friendly Reminder: strOutput includes a newline!
			String output = strOutput.toString();
			print(output);
		} catch (NullPointerException e) {
			// Not Parseable - Sadly, calling e.getLocalizedMessage() on the
			// NullPointerException returns NULL itself, thus triggering another
			// NullPointerException in the catch statement. Therefore we need a
			// second catch statement with a special case for the
			// NullPointerException instead of catching a general Exception
			print("EXCEPTION NullPointerException\n");
		} catch (BException e) {
			print("EXCEPTION " + e.getLocalizedMessage().replace("\n", " ")
					+ "\n");
		}
	}

	private static void print(String output) {
		try {
			PrintStream out = new PrintStream(System.out, true,
					CliBParser.encoding);
			out.print(output);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static int doFileParsing(final ParsingBehaviour behaviour,
			final PrintStream out, final PrintStream err,
			final boolean closeStream, final File bfile) {
		int returnValue;
		try {
			final BParser parser = new BParser(bfile.getName());
			returnValue = parser.fullParsing(bfile, behaviour, out, err);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = -4;
		} finally {
			if (closeStream) {
				out.close();
			}
		}
		return returnValue;
	}

	private static ConsoleOptions createConsoleOptions(final String[] args) {
		final ConsoleOptions options = new ConsoleOptions();
		options.setIntro("BParser (rev. "
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
		options.addOption(
				CLI_SWITCH_FASTPROLOG,
				"Show AST as Prolog term for fast loading (Do not use this representation in your tool! It depends on internal representation of Sicstus Prolog and will very likely change arbitrarily in the future!)");
		options.addOption(CLI_SWITCH_PREPL,
				"Enter parser-repl. Should only be used from inside ProB's Prolog Core.");
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
