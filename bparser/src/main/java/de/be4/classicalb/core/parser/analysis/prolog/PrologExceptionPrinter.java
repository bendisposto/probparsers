/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.IOException;
import java.io.OutputStream;

import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.BParseException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.PreParseException;
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
	static public void printException(final OutputStream out,
			final IOException e, final String filename) {
		IPrologTermOutput pto = new PrologTermOutput(System.err);
		pto.openTerm("io_exception");
		printMsg(pto, e, filename);
		pto.closeTerm();
		pto.fullstop();
		pto.flush();
	}

	static public void printException(final OutputStream out, final BException e) {
		IPrologTermOutput pto = new PrologTermOutput(System.err);
		Throwable cause = e.getCause();
		String filename = e.getFilename();
		if (cause == null) {
			printGeneralException(pto, e, filename);
		} else {
			if (cause instanceof BParseException) {
				printBPException(pto, (BParseException) cause, filename);
			} else if (cause instanceof PreParseException) {
				printPreParseException(pto, (PreParseException) cause, filename);
			} else if (cause instanceof CheckException) {
				printCheckException(pto, (CheckException) cause, filename);
			} else {
				printGeneralException(pto, cause, filename);
			}
		}
		pto.fullstop();
		pto.flush();
	}

	private static void printCheckException(final IPrologTermOutput pto,
			final CheckException cause, final String filename) {
		final Node[] nodes = cause.getNodes();
		final SourcePosition pos;
		if (nodes != null && nodes.length > 0) {
			pos = nodes[0].getStartPos();
		} else {
			pos = null;
		}
		printParseException(pto, cause, filename, pos);
	}

	private static void printGeneralException(final IPrologTermOutput pto,
			final Throwable cause, final String filename) {
		pto.openTerm("exception");
		printMsg(pto, cause, filename);
		pto.closeTerm();
	}

	private static void printPreParseException(final IPrologTermOutput pto,
			final PreParseException e, final String filename) {
		de.be4.classicalb.core.preparser.node.Token[] tokens = e.getTokens();
		pto.openTerm("preparse_exception");
		pto.openList();
		for (int i = 0; i < tokens.length; i++) {
			de.be4.classicalb.core.preparser.node.Token token = tokens[i];
			if (token == null) {
				pto.printAtom("none");
			} else {
				pto.openTerm("pos");
				pto.printNumber(token.getLine());
				pto.printNumber(token.getPos());
				pto.printAtom(filename);
				pto.closeTerm();
			}
		}
		pto.closeList();
		printMsg(pto, e, filename);
		pto.closeTerm();
	}

	private static void printBPException(final IPrologTermOutput pto,
			final BParseException e, final String filename) {
		final Token token = e.getToken();
		final SourcePosition pos = token == null ? null : new SourcePosition(
				token.getLine(), token.getPos());
		printParseException(pto, e, filename, pos);
	}

	private static void printParseException(final IPrologTermOutput pto,
			final Throwable e, final String filename, final SourcePosition pos) {
		pto.openTerm("parse_exception");
		if (pos == null) {
			pto.printAtom("none");
		} else {
			pto.openTerm("pos");
			pto.printNumber(pos.getLine());
			pto.printNumber(pos.getPos());
			pto.printAtom(filename);
			pto.closeTerm();
		}
		printMsg(pto, e, filename);
		pto.closeTerm();
	}

	private static void printMsg(final IPrologTermOutput pto,
			final Throwable cause, final String filename) {
		final Exception wrapper = new BException(filename, cause);
		pto.printAtom(wrapper.getLocalizedMessage());
	}

}
