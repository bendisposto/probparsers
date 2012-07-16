package de.prob.prolog.output;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;

import de.prob.prolog.term.PrologTerm;

/**
 * Helper class to generate Prolog terms.
 * 
 * @author Daniel Plagge
 */
public class PrologTermOutput implements IPrologTermOutput {
	private final static char[] VALID_CHARS = validChars();
	private final static char[] VALID_ATOM_CHARS = validAtomChars();

	private final PrintWriter out;

	// comma_needed states if the next term can be printed directly (false) or
	// if a separating comma is needed first
	private boolean comma_needed = false;

	private final boolean use_indention;
	private int indent_level = 0;
	private int ignore_indention_level = 0;

	private int termCount = 0;
	private int listCount = 0;

	// flag to enable printing of terms without arguments as atoms.
	// if set, the last printed object was a functor, and if anything is printed
	// before closing the term, an opening parenthesis should be printed.
	private boolean lazy_parenthesis = false;

	public PrologTermOutput(final PrintWriter out, final boolean use_indention) {
		this.use_indention = use_indention;
		this.out = out;
	}

	public PrologTermOutput(final PrintWriter out) {
		this(out, true);
	}

	public PrologTermOutput(final OutputStream out, final boolean use_indention) {
		this(new PrintWriter(out), use_indention);
	}

	public PrologTermOutput(final OutputStream out) {
		this(new PrintWriter(out));
	}

	/**
	 * Escapes existing apostrophes by backslashes.
	 * 
	 * @param input
	 *            A string, never <code>null</code>.
	 * @param single_quotes
	 *            if single quotes may be used
	 * @param double_quotes
	 *            if double quotes may be used
	 * @return the escaped input string
	 */
	private void escape(final String input, final boolean single_quotes,
			final boolean double_quotes) {
		for (int i = 0; i < input.length(); i++) {
			final char c = input.charAt(i);
			if (Arrays.binarySearch(VALID_CHARS, c) >= 0) {
				out.print(c);
			} else if (c == '\'') {
				out.print(single_quotes ? "'" : "\\'");
			} else if (c == '\\') {
				out.print("\\\\");
			} else if (c == '\n') {
				out.print("\\n");
			} else if (c == '"') {
				out.print(double_quotes ? "\"" : "\\\"");
			} else {
				out.print('\\');
				out.print(Integer.toOctalString(c));
				out.print('\\');
			}
		}
	}

	private static char[] validChars() {
		StringBuilder buf = new StringBuilder();
		buf.append("abcdefghijklmnopqrstuvwxyz");
		buf.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		buf.append("0123456789");
		buf.append("_ +-*/^<>=~:.?@#$&!;%(),[]{|}");
		char[] chars = buf.toString().toCharArray();
		Arrays.sort(chars);
		return chars;
	}

	private static char[] validAtomChars() {
		StringBuilder buf = new StringBuilder();
		buf.append("abcdefghijklmnopqrstuvwxyz");
		buf.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		buf.append("0123456789_");
		char[] chars = buf.toString().toCharArray();
		Arrays.sort(chars);
		return chars;
	}

	private static boolean escapeIsNeeded(final String input) {
		if (input == null) {
			return false; // null is a valid atom
		}
		final int length = input.length();
		if (length > 0
				&& Arrays.binarySearch(VALID_ATOM_CHARS, input.charAt(0)) >= 0
				&& Character.isLowerCase(input.charAt(0))) {
			for (int i = 1; i < length; i++) {
				char c = input.charAt(i);
				if (Arrays.binarySearch(VALID_ATOM_CHARS, c) < 0)
					return true;
			}
			return false;
		} else
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#openTerm
	 * (java.lang.String)
	 */
	public IPrologTermOutput openTerm(final String functor) {
		openTerm(functor, false);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#openTerm
	 * (java.lang.String, boolean)
	 */
	public IPrologTermOutput openTerm(final String functor,
			final boolean ignoreIndention) {
		termCount++;
		printAtom(functor);
		lazy_parenthesis = true;
		comma_needed = false;
		indent_level += 2;
		if (ignore_indention_level > 0) {
			ignore_indention_level++;
		} else if (ignoreIndention) {
			ignore_indention_level = 1;
		}
		return this;
	}

	private void printIndention() {
		if (use_indention && ignore_indention_level == 0) {
			// synchronized to speed up printing
			synchronized (out) {
				out.println();
				for (int i = 0; i < indent_level; i++) {
					out.print(' ');
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#closeTerm
	 * ()
	 */
	public IPrologTermOutput closeTerm() {
		termCount--;
		if (termCount < 0)
			throw new IllegalStateException(
					"Tried to close a term that has not been opened.");
		if (lazy_parenthesis) {
			lazy_parenthesis = false;
		} else {
			out.print(')');
		}
		comma_needed = true;
		indent_level -= 2;
		if (ignore_indention_level > 0) {
			ignore_indention_level--;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#printAtom
	 * (java.lang.String)
	 */
	public IPrologTermOutput printAtom(final String content) {
		synchronized (out) {
			printCommaIfNeeded();
			if (escapeIsNeeded(content)) {
				out.print('\'');
				escape(content, false, true);
				out.print('\'');
			} else {
				out.print(content);
			}
			comma_needed = true;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seede.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#
	 * printAtomOrNumber(java.lang.String)
	 */
	public IPrologTermOutput printAtomOrNumber(final String content) {
		synchronized (out) {
			try {
				printNumber(Long.parseLong(content));
			} catch (NumberFormatException e) {
				printAtom(content);
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#printString
	 * (java.lang.String)
	 */
	public IPrologTermOutput printString(final String content) {
		synchronized (out) {
			printCommaIfNeeded();
			out.print('"');
			escape(content, true, false);
			out.print('"');
			comma_needed = true;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#printNumber
	 * (long)
	 */
	public IPrologTermOutput printNumber(final long number) {
		synchronized (out) {
			printCommaIfNeeded();
			out.print(number);
			comma_needed = true;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#printNumber
	 * (java.math.BigInteger)
	 */
	public IPrologTermOutput printNumber(final BigInteger number) {
		synchronized (out) {
			printCommaIfNeeded();
			out.print(number);
			comma_needed = true;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#openList
	 * ()
	 */
	public IPrologTermOutput openList() {
		synchronized (out) {
			listCount++;
			printCommaIfNeeded();
			out.print('[');
			comma_needed = false;
			indent_level += 1;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#closeList
	 * ()
	 */
	public IPrologTermOutput closeList() {
		synchronized (out) {
			listCount--;
			if (listCount < 0)
				throw new IllegalStateException(
						"Tried to close a list that has not been opened.");
			out.print(']');
			comma_needed = true;
			indent_level -= 1;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#emptyList
	 * ()
	 */
	public IPrologTermOutput emptyList() {
		synchronized (out) {
			printCommaIfNeeded();
			out.print("[]");
			comma_needed = true;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#printVariable
	 * (java.lang.String)
	 */
	public IPrologTermOutput printVariable(final String var) {
		printCommaIfNeeded();
		checkVariable(var);
		out.print(var);
		comma_needed = true;
		return this;
	}

	private void checkVariable(final String var) {
		boolean ok = var.length() > 0;
		if (ok) {
			char c = var.charAt(0);
			ok = c == '_' || Character.isUpperCase(c);
			for (int i = 1; ok && i < var.length(); i++) {
				c = var.charAt(i);
				ok &= c == '_' || Character.isLetterOrDigit(c);
			}
		}
		if (!ok)
			throw new IllegalArgumentException(
					"Invalid name for Prolog variable '" + var + "'");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#flush()
	 */
	public IPrologTermOutput flush() {
		out.flush();
		return this;
	}

	private void printCommaIfNeeded() {
		if (lazy_parenthesis) {
			out.print('(');
			lazy_parenthesis = false;
		}
		if (comma_needed) {
			out.print(',');
			printIndention();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.analysis.prolog.IPrologTermOutput#fullstop
	 * ()
	 */
	public IPrologTermOutput fullstop() {
		if (listCount != 0)
			throw new IllegalStateException(
					"Number of openList and closeList do not match. openList Counter is "
							+ listCount);
		if (termCount != 0)
			throw new IllegalStateException(
					"Number of openTerm and closeTerm do not match. openTerm Counter is "
							+ termCount);
		out.println('.');
		comma_needed = false;
		return this;
	}

	public IPrologTermOutput printTerm(final PrologTerm term) {
		term.toTermOutput(this);
		return this;
	}
}
