/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BLexerException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Token;
import de.hhu.stups.sablecc.patch.SourcePosition;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

/**
 * Just a helper class to print exceptions in Prolog-Syntax.
 * 
 * Ugly, but the alternative would have been Prolog-References in the exception
 * definitions.
 * 
 * @author plagge
 * 
 */
public final class PrologExceptionPrinter {
	private static final String PARSE_EXCEPTION_PROLOG_TERM = "parse_exception";

	private PrologExceptionPrinter() {
		// this class contains only static methods
	}

	public static void printException(final OutputStream out, final IOException e) {
		printException(out, e, true);
	}

	public static void printException(final OutputStream out, final IOException e, boolean useIndentation) {
		IPrologTermOutput pto = new PrologTermOutput(out, useIndentation);
		pto.openTerm("io_exception");
		printMsg(pto, e, useIndentation);
		pto.closeTerm();
		pto.fullstop();
		pto.flush();
	}

	public static void printException(final OutputStream out, final BCompoundException e) {
		printException(out, e, true, false);
	}

	public static void printException(final OutputStream out, final BCompoundException e, boolean useIndentation,
			boolean lineOneOff) {
		IPrologTermOutput pto = new PrologTermOutput(out, useIndentation);
		if (e.getBExceptions().size() > 1) {
			pto.openTerm("compound_exception", true);
			pto.openList();
			BCompoundException comp = e;
			for (Exception ex : comp.getBExceptions()) {
				try {
					printBException(pto, (BException) ex, useIndentation, lineOneOff);
				} catch (ClassCastException e2) {
					throw new IllegalStateException(
							"Unexpected exception in compound exceptions:" + ex.getClass().getSimpleName());
				}

			}
			pto.closeList();
			pto.closeTerm();
			pto.fullstop();
			pto.flush();
			return;
		} else if (e.getBExceptions().size() == 1) {
			// single BException
			printBException(pto, e.getBExceptions().get(0), useIndentation, lineOneOff);
			pto.fullstop();
			pto.flush();
		} else {
			throw new IllegalStateException("Empty compoundException.");
		}
	}

	public static void printBException(IPrologTermOutput pto, final BException e, boolean useIndentation,
			boolean lineOneOff) {
		Throwable cause = e.getCause();
		String filename = e.getFilename();
		if (cause == null) {
			printGeneralException(pto, e, useIndentation);
		} else {
			while (cause.getClass().equals(BException.class) && cause.getCause() != null) {
				BException bex = (BException) cause;
				cause = bex.getCause();
				filename = bex.getFilename();
			}
			if (cause instanceof BLexerException) {
				printBLexerException(pto, (BLexerException) cause, filename, useIndentation, lineOneOff);
			} else if (cause instanceof LexerException) {
				printLexerException(pto, (LexerException) cause, filename, useIndentation, lineOneOff);
			} else if (cause instanceof BParseException) {
				printBParseException(pto, (BParseException) cause, filename, useIndentation, lineOneOff);
			} else if (cause instanceof PreParseException) {
				printPreParseException(pto, (PreParseException) cause, filename, useIndentation, lineOneOff);
			} else if (cause instanceof CheckException) {
				printCheckException(pto, (CheckException) cause, filename, useIndentation, lineOneOff);
			} else {
				printGeneralException(pto, cause, useIndentation);
			}
		}
	}

	private static void printLexerException(IPrologTermOutput pto, LexerException cause, String filename,
			boolean useIndentation, boolean lineOneOff) {
		pto.openTerm(PARSE_EXCEPTION_PROLOG_TERM);
		// there is no source information / position attached to lexer
		// exceptions -> extract from message
		Pattern p = Pattern.compile("\\[(\\d+)[,](\\d+)\\].*", Pattern.DOTALL);
		Matcher m = p.matcher(cause.getMessage());
		boolean posFound = m.lookingAt();
		if (posFound) {
			pto.openTerm("pos");
			int line = Integer.parseInt(m.group(1));
			if (lineOneOff) {
				line--;
			}
			pto.printNumber(line);
			pto.printNumber(Integer.parseInt(m.group(2)));
			pto.printAtom(filename);
			pto.closeTerm();
		} else {
			pto.printAtom("none");
		}

		printMsg(pto, cause, useIndentation);
		pto.closeTerm();
	}

	private static void printCheckException(final IPrologTermOutput pto, final CheckException cause,
			final String filename, final boolean useIndentation, final boolean lineOneOff) {
		final Node[] nodes = cause.getNodes();
		if (nodes != null && nodes.length > 0) {
			final Node node = nodes[0];
			printExceptionWithSourceRange(pto, cause, filename, node.getStartPos(), node.getEndPos(), useIndentation,
					lineOneOff);
		} else {
			printExceptionWithSourcePosition(pto, cause, filename, null, useIndentation, lineOneOff);
		}
	}

	private static void printGeneralException(final IPrologTermOutput pto, final Throwable cause,
			final boolean useIndentation) {
		pto.openTerm("exception");
		printMsg(pto, cause, useIndentation);
		pto.closeTerm();
	}

	private static void printPreParseException(final IPrologTermOutput pto, final PreParseException e,
			final String filename, final boolean useIndentation, final boolean lineOneOff) {
		de.be4.classicalb.core.preparser.node.Token[] tokens = e.getTokens();
		pto.openTerm("preparse_exception");
		pto.openList();
		for (int i = 0; i < tokens.length; i++) {
			de.be4.classicalb.core.preparser.node.Token token = tokens[i];
			if (token == null) {
				pto.printAtom("none");
			} else {
				pto.openTerm("pos");
				if (lineOneOff) {
					pto.printNumber((long) token.getLine() - 1);
				} else {
					pto.printNumber(token.getLine());
				}
				pto.printNumber(token.getPos());
				pto.printAtom(filename);
				pto.closeTerm();
			}
		}
		pto.closeList();
		printMsg(pto, e, useIndentation);
		pto.closeTerm();
	}

	private static void printBParseException(final IPrologTermOutput pto, final BParseException e,
			final String filename, final boolean useIndentation, final boolean lineOneOff) {
		final Token token = e.getToken();
		final SourcePosition pos = token == null ? null : new SourcePosition(token.getLine(), token.getPos());
		printExceptionWithSourcePosition(pto, e, filename, pos, useIndentation, lineOneOff);
	}

	private static void printBLexerException(final IPrologTermOutput pto, final BLexerException e,
			final String filename, final boolean useIndentation, final boolean lineOneOff) {
		final Token token = e.getLastToken();
		final SourcePosition pos = token == null ? null : new SourcePosition(token.getLine(), token.getPos());
		printExceptionWithSourcePosition(pto, e, filename, pos, useIndentation, lineOneOff);
	}

	private static void printExceptionWithSourceRange(final IPrologTermOutput pto, final Throwable e,
			final String filename, final SourcePosition beginPos, final SourcePosition endPos,
			final boolean useIndentation, final boolean lineOneOff) {
		pto.openTerm(PARSE_EXCEPTION_PROLOG_TERM);
		if (beginPos == null) {
			pto.printAtom("none");
		} else {
			pto.openTerm("pos");
			if (lineOneOff) {
				pto.printNumber((long) beginPos.getLine() - 1);
			} else {
				pto.printNumber(beginPos.getLine());
			}
			pto.printNumber(beginPos.getPos());
			if (lineOneOff) {
				pto.printNumber((long) endPos.getLine() - 1);
			} else {
				pto.printNumber(endPos.getLine());
			}
			pto.printNumber(endPos.getPos());
			pto.printAtom(filename);
			pto.closeTerm();
		}
		printMsg(pto, e, useIndentation);
		pto.closeTerm();
	}

	private static void printExceptionWithSourcePosition(final IPrologTermOutput pto, final Throwable e,
			final String filename, final SourcePosition pos, final boolean useIndentation, final boolean lineOneOff) {
		pto.openTerm(PARSE_EXCEPTION_PROLOG_TERM);
		if (pos == null) {
			pto.printAtom("none");
		} else {
			pto.openTerm("pos");
			if (lineOneOff) {
				pto.printNumber((long) pos.getLine() - 1);
			} else {
				pto.printNumber(pos.getLine());
			}
			pto.printNumber(pos.getPos());
			pto.printAtom(filename);
			pto.closeTerm();
		}
		printMsg(pto, e, useIndentation);
		pto.closeTerm();
	}

	private static void printMsg(final IPrologTermOutput pto, final Throwable cause, final boolean useIndentation) {
		String message = cause.getMessage();
		if (useIndentation) {
			pto.printAtom(message);
		} else {
			pto.printAtom(message.replace("\n", " "));

		}
	}

	@SuppressWarnings("unused")
	private static String fixMessageLineOneOff(String message) {
		Pattern p = Pattern.compile("\\[(\\d+)[,](\\d+)\\](.*)", Pattern.DOTALL);
		Matcher m = p.matcher(message);

		if (m.lookingAt()) {
			int actualLineNr = Integer.parseInt(m.group(1)) - 1;
			return message.replaceFirst(m.group(1), Integer.toString(actualLineNr));
		} else {
			// did not match - can not fix line number
			return message;
		}
	}

}
