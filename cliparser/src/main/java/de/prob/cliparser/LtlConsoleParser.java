/**
 * 
 */
package de.prob.cliparser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

import de.be4.classicalb.core.parser.ClassicalBParser;
import de.be4.ltl.core.parser.CtlParser;
import de.be4.ltl.core.parser.LtlParseException;
import de.be4.ltl.core.parser.LtlParser;
import de.be4.ltl.core.parser.TemporalLogicParser;
import de.prob.parserbase.JoinedParserBase;
import de.prob.parserbase.ProBParserBase;
import de.prob.parserbase.UnparsedParserBase;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;
import de.prob.prolog.term.PrologTerm;

/**
 * @author plagge
 * 
 */
public class LtlConsoleParser {

	private static final UnparsedParserBase UNPARSED_PARSER_BASE = new UnparsedParserBase(
			"unparsed_expr", "unparsed_pred", "unparsed_trans");
	private static final String CLI_LANG = "-lang";
	private static final String CLI_OUT = "-out";
	private static final String CLI_HELP = "-h";
	private static final String CLI_CTL = "-ctl";

	public static void main(final String[] args) {
		ConsoleOptions options = new ConsoleOptions();
		options.addOption(CLI_LANG,
				"set language for atomic propositions, etc. (e.g. none, B)", 1);
		options.addOption(CLI_OUT, "set output file, use stdout if omitted", 1);
		options.addOption(CLI_CTL, "use CTL instead of LTL");
		options.setIntro("usage: LtlConsoleParser [options] <LTL file>\n\n"
				+ "If the file is omitted, stdin is used\n"
				+ "Available options are:");
		options.addOption(CLI_HELP, "print this message");

		options.parseOptions(args);
		String[] params = options.getRemainingOptions();
		if (params.length > 1) {
			options.printUsage(System.out);
			System.exit(-1);
			return;
		}

		final OutputStream out;
		if (options.isOptionSet(CLI_OUT)) {
			final String filename = options.getOptions(CLI_OUT)[0];
			try {
				out = new FileOutputStream(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace(System.err);
				System.exit(-1);
				return;
			}
		} else {
			out = System.out;
		}

		final String lang = options.isOptionSet(CLI_LANG) ? options
				.getOptions(CLI_LANG)[0] : null;
		final ProBParserBase extParser = getExtensionParser(lang);

		final IPrologTermOutput pto = new PrologTermOutput(out, false);

		final String inputFile = params.length == 1 ? params[0] : null;
		String input = null;
		try {
			input = readFormula(inputFile);
		} catch (IOException e) {
			pto.openTerm("io_error").printAtom(e.getLocalizedMessage())
					.closeTerm();
		}

		if (input != null) {
			final String[] formulas = input.split("###");
			final TemporalLogicParser<?> parser = createParser(extParser,
					options.isOptionSet(CLI_CTL));
			pto.openList();
			for (final String formula : formulas) {
				try {
					final PrologTerm term = parser.generatePrologTerm(formula,
							null);
					pto.openTerm("ltl").printTerm(term).closeTerm();
				} catch (LtlParseException e) {
					pto.openTerm("syntax_error")
							.printAtom(e.getLocalizedMessage()).closeTerm();
				}
			}
			pto.closeList();
		}

		pto.fullstop();
		pto.flush();

		if (options.isOptionSet(CLI_OUT)) {
			try {
				out.close();
			} catch (IOException e) {
				// ignore
			}
		}
	}

	private static TemporalLogicParser<?> createParser(
			final ProBParserBase extParser, final boolean isCtlSelected) {
		return isCtlSelected ? new CtlParser(extParser) : new LtlParser(
				extParser);
	}

	private static String readFormula(final String inputFile)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		final Reader reader = inputFile != null ? new FileReader(inputFile)
				: new InputStreamReader(System.in);
		final Reader in = new BufferedReader(reader);
		int c;
		while ((c = in.read()) != -1) {
			sb.append((char) c);
		}
		return sb.toString();
	}

	private static ProBParserBase getExtensionParser(final String pattern) {
		final ProBParserBase result;
		if (pattern == null) {
			result = UNPARSED_PARSER_BASE;
		} else {
			final String[] langs = pattern.split(",");
			final ProBParserBase[] sublangs = new ProBParserBase[langs.length];
			for (int i = 0; i < langs.length; i++) {
				final String lang = langs[i];
				final ProBParserBase sub;
				if ("none".equals(lang)) {
					sub = UNPARSED_PARSER_BASE;
				} else if ("B".equals(lang)) {
					sub = new ClassicalBParser();
				} else
					throw new IllegalArgumentException("Unknown language "
							+ lang);
				sublangs[i] = sub;
			}
			if (sublangs.length == 1) {
				result = sublangs[0];
			} else {
				result = new JoinedParserBase(sublangs);
			}
		}
		return result;
	}
}
