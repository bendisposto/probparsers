package de.be4.eventbalg.core.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import de.be4.eventbalg.core.parser.analysis.ASTDisplay;
import de.be4.eventbalg.core.parser.analysis.ASTPrinter;
import de.be4.eventbalg.core.parser.lexer.LexerException;
import de.be4.eventbalg.core.parser.node.Start;
import de.be4.eventbalg.core.parser.node.TComment;
import de.be4.eventbalg.core.parser.node.Token;
import de.be4.eventbalg.core.parser.parser.Parser;
import de.be4.eventbalg.core.parser.parser.ParserException;
import de.hhu.stups.sablecc.patch.IParser;
import de.hhu.stups.sablecc.patch.IToken;
import de.hhu.stups.sablecc.patch.ITokenListContainer;
import de.hhu.stups.sablecc.patch.PositionedNode;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.hhu.stups.sablecc.patch.SourcecodeRange;

public class EventBParser {

	public static final String MSG_COMMENT_PLACEMENT = "Comment can only be place behind the element they belong to. Please move the comment to an appropriate place!";

	private static final String CLI_SWITCH_VERBOSE = "-v";
	private static final String CLI_SWITCH_TIME = "-time";
	private static final String CLI_SWITCH_AST = "-ast";
	private static final String CLI_SWITCH_UI = "-ui";

	private SourcePositions sourcePositions;

	public static void main(final String[] args) {
		if (args.length < 1) {
			System.err.println("usage: BParser [options] <BMachine file>");
			System.err.println();
			System.err.println("Available options are:");
			System.err.println(CLI_SWITCH_VERBOSE + "\t\tVerbose output during lexing and parsing");
			System.err.println(CLI_SWITCH_TIME + "\t\tOutput time used for complete parsing process.");
			System.err.println(CLI_SWITCH_AST + "\t\tPrint AST on standard output.");
			System.err.println(CLI_SWITCH_UI + "\t\tShow AST as Swing UI.");
			System.exit(-1);
		}

		try {
			final long start = System.currentTimeMillis();
			final EventBParser parser = new EventBParser();
			final Start tree = parser.parseFile(new File(args[args.length - 1]),
					isCliSwitchSet(args, CLI_SWITCH_VERBOSE));
			final long end = System.currentTimeMillis();
			System.out.println();

			if (isCliSwitchSet(args, CLI_SWITCH_TIME)) {
				System.out.println("Time for parsing: " + (end - start) + "ms");
			}

			if (isCliSwitchSet(args, CLI_SWITCH_AST)) {
				System.out.println("AST:");
				tree.apply(new ASTPrinter());
			}

			if (isCliSwitchSet(args, CLI_SWITCH_UI)) {
				tree.apply(new ASTDisplay());
			}
		} catch (final IOException e) {
			System.err.println();
			System.err.println("Error reading input file: " + e.getLocalizedMessage());
			System.exit(-2);
		} catch (final BException e) {
			System.err.println();
			System.err.println("Error parsing input file: " + e.getLocalizedMessage());
			System.exit(-3);
		}
	}

	/**
	 * Parses the input file.
	 * 
	 * @see #parse(String, boolean)
	 * @param machine
	 *            the machine file
	 * @param verbose
	 *            print debug information
	 * @return the generated AST
	 * @throws IOException
	 *             if stream cannot be written to or closed
	 * @throws BException
	 *             if parsing fails
	 */
	public Start parseFile(final File machine, final boolean verbose) throws IOException, BException {
		final InputStreamReader inputStreamReader
            = new InputStreamReader(new FileInputStream(machine), Charset.forName("UTF-8"));

		final StringBuilder builder = new StringBuilder();
		final char[] buffer = new char[1024];
		int read;
		while ((read = inputStreamReader.read(buffer)) >= 0) {
			builder.append(String.valueOf(buffer, 0, read));
		}
		inputStreamReader.close();

		return parse(builder.toString(), verbose);
	}

	/**
	 * Parses the input string.
	 * 
	 * @param input
	 *            the {@link String} to be parsed
	 * @param debugOutput
	 *            output debug messages on standard out?
	 * @return the root node of the AST
	 * @throws BException
	 *             The {@link BException} class stores the actual exception as
	 *             delegate and forwards all method calls to it. So it is save
	 *             for tools to just use this exception if they want to extract
	 *             an error message. If the tools needs to extract additional
	 *             information, such as a sourcecode position or involved tokens
	 *             respectively nodes, it needs to retrieve the delegate
	 *             exception. The {@link BException} class offers a
	 *             {@link BException#getCause()} method for this, which returns
	 *             the delegate exception.
	 *             <p>
	 *             Internal exceptions:
	 *             <ul>
	 *             <li>{@link EventBLexerException}: If any error occurs in the
	 *             generated or customized lexer a {@link LexerException} is
	 *             thrown. Usually the lexer classes just throw a
	 *             {@link LexerException}. But this class unfortunately does not
	 *             contain any explicit information about the sourcecode
	 *             position where the error occured. Using aspect-oriented
	 *             programming we intercept the throwing of these exceptions to
	 *             replace them by our own exception. In our own exception we
	 *             provide the sourcecode position of the last characters that
	 *             were read from the input.</li>
	 *             <li>{@link EventBParseException}: This exception is thrown in
	 *             two situations. On the one hand if the parser throws a
	 *             {@link ParserException} we convert it into a
	 *             {@link EventBParseException}. On the other hand it can be
	 *             thrown if any error is found during the AST transformations
	 *             after the parser has finished. We try to provide a token if a
	 *             single token is involved in the error. Otherwise a
	 *             {@link SourcecodeRange} is provided, which can be used to
	 *             retrieve detailed position information from the
	 *             {@link SourcePositions} (s. {@link #getSourcePositions()}).
	 *             </li>
	 *             <li>{@link CheckException}: If any problem occurs while
	 *             performing semantic checks, a {@link CheckException} is
	 *             thrown. We provide one or more nodes that are involved in the
	 *             problem. For example, if we find dublicate machine clauses,
	 *             we will list all occurances in the exception.</li>
	 *             </ul>
	 */
	public Start parse(final String input, final boolean debugOutput) throws BException {
		final Reader reader = new StringReader(input);

		try {
			/*
			 * Main parser
			 */
			final EventBLexer lexer = new EventBLexer(new PushbackReader(reader, 99));
			lexer.setDebugOutput(debugOutput);

			Parser parser = new Parser(lexer);
			final Start rootNode = parser.parse();
			final List<IToken> tokenList = ((ITokenListContainer) lexer).getTokenList();

			/*
			 * Retrieving sourcecode positions which were found by ParserAspect
			 */
			final Map<PositionedNode, SourcecodeRange> positions = ((IParser) parser).getMapping();

			sourcePositions = new SourcePositions(tokenList, positions);
			parser = null;

			return rootNode;
		} catch (final LexerException e) {
			/*
			 * Actually it's supposed to be a EventBLexerException because the
			 * aspect 'LexerAspect' replaces any LexerException to provide
			 * sourcecode position information in the BLexerException.
			 */
			throw new BException(e);
		} catch (final ParserException e) {
			throw new BException(createEventBParseException(e));
		} catch (final EventBParseException e) {
			throw new BException(e);
		} catch (final IOException e) {
			// shouldn't happen and if, we cannot handle it
			throw new BException(e);
		}
	}

	private EventBParseException createEventBParseException(final ParserException e) {
		final Token token = e.getToken();
		String message = e.getMessage();
		final boolean expectingFound = message.indexOf("expecting") >= 0;

		/*
		 * Special error message for misplaced comments.
		 */
		if (expectingFound && token instanceof TComment) {
			message = MSG_COMMENT_PLACEMENT;
		}

		/*
		 * Replace some token names...
		 */
		message = message.replaceFirst(" at", " @");

		return new EventBParseException(token, message);
	}

	private static boolean isCliSwitchSet(final String[] args, final String cliSwitch) {
		for (int i = 0; i < args.length; i++) {
			if (cliSwitch.equals(args[i])) {
				return true;
			}
		}

		return false;
	}

	public SourcePositions getSourcePositions() {
		return sourcePositions;
	}
}
