/*
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, Heinrich
 * Heine Universitaet Duesseldorf This software is licenced under EPL 1.0
 * (http://www.eclipse.org/org/documents/epl-v10.html)
 * */

package de.prob.parser;

import java.math.BigInteger;

import de.prob.core.sablecc.node.AAtomTerm;
import de.prob.core.sablecc.node.AEmptyMoreParams;
import de.prob.core.sablecc.node.AExceptionResult;
import de.prob.core.sablecc.node.AInterruptedResult;
import de.prob.core.sablecc.node.AMoreParams;
import de.prob.core.sablecc.node.ANoResult;
import de.prob.core.sablecc.node.ANumberTerm;
import de.prob.core.sablecc.node.AParams;
import de.prob.core.sablecc.node.ATerm;
import de.prob.core.sablecc.node.AVariableTerm;
import de.prob.core.sablecc.node.AYesResult;
import de.prob.core.sablecc.node.PMoreParams;
import de.prob.core.sablecc.node.PResult;
import de.prob.core.sablecc.node.PTerm;
import de.prob.core.sablecc.node.Start;
import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.IntegerPrologTerm;
import de.prob.prolog.term.ListPrologTerm;
import de.prob.prolog.term.PrologTerm;
import de.prob.prolog.term.VariablePrologTerm;

/**
 * This generator extracts prolog terms from a SableCC syntax tree.
 * 
 * @author plagge
 */
public class PrologTermGenerator {
	private static final PrologTerm[] EMPTY_PROLOG_LIST = new PrologTerm[0];

	public static PrologTerm toPrologTerm(final Start node)
			throws ResultParserException {
		PResult topnode = node.getPResult();
		PrologTerm term = null;
		if (topnode instanceof AYesResult) {
			term = toPrologTerm(((AYesResult) topnode).getTerm());
		} else if (topnode instanceof ANoResult) {
			term = null;
		} else if (topnode instanceof AInterruptedResult) {
			term = null;
		} else if (topnode instanceof AExceptionResult) {
			String message = "ProB raised an exception: "
					+ ((AExceptionResult) topnode).getString().getText();
			throw new ResultParserException(message, null);
		} else
			throw new IllegalStateException("Unknown subclass of PResult: "
					+ topnode.getClass().getCanonicalName());
		return term;
	}

	public static PrologTerm toPrologTermMustNotFail(final String query,
			final Start node) throws ResultParserException {
		PrologTerm term = toPrologTerm(node);
		if (term == null) {
			final String message = "Prolog query unexpectedly failed: " + query;
			throw new ResultParserException(message, null);
		}
		return term;
	}

	private static PrologTerm toPrologTerm(final PTerm node) {
		PrologTerm term;
		if (node instanceof ANumberTerm) {
			String text = ((ANumberTerm) node).getNumber().getText();
			term = new IntegerPrologTerm(new BigInteger(text));
		} else if (node instanceof AAtomTerm) {
			String text = ((AAtomTerm) node).getName().getText();
			if ("[]".equals(text)) {
				term = new ListPrologTerm(EMPTY_PROLOG_LIST);
			} else {
				text = removeQuotes(text);
				term = new CompoundPrologTerm(text);
			}
		} else if (node instanceof ATerm) {
			ATerm aterm = (ATerm) node;
			int listSize = getListSize(node);
			if (listSize >= 0) {
				PrologTerm[] list = new PrologTerm[listSize];
				fillListWithElements(list, aterm);
				term = new ListPrologTerm(list);
			} else {
				String functor = removeQuotes(aterm.getFunctor().getText());
				PrologTerm[] params = evalParameters((AParams) aterm
						.getParams());
				term = new CompoundPrologTerm(functor, params);
			}
		} else if (node instanceof AVariableTerm) {
			String text = removeQuotes(((AVariableTerm) node).getVariable()
					.getText());
			term = new VariablePrologTerm(text);
		} else
			throw new IllegalStateException("Unexpected subclass of PTerm: "
					+ node.getClass().getCanonicalName());
		return term;
	}

	private static String removeQuotes(final String text) {
		String result;
		if (text.charAt(0) == '\'') {
			int length = text.length();
			StringBuilder builder = new StringBuilder(length - 2);
			for (int i = 1; i < length - 1; i++) {
				char c = text.charAt(i);
				if (c == '\\') {
					i = escapeCharacter(text, builder, i);
				} else {
					builder.append(c);
				}
			}
			result = builder.toString();
		} else {
			result = text;
		}
		return result;
	}

	private static int escapeCharacter(final String text,
			final StringBuilder builder, int i) {
		char c;
		i++;
		c = text.charAt(i);
		switch (c) {
		case 'b':
			builder.append('\b');
			break;
		case 't':
			builder.append('\t');
			break;
		case 'n':
			builder.append('\n');
			break;
		case 'v':
			builder.append('\u000C');
			break;
		case 'f':
			builder.append('\f');
			break;
		case 'r':
			builder.append('\r');
			break;
		case 'e':
			builder.append('\u001C');
			break;
		case 'd':
			builder.append('\u0008');
			break;
		case 'a':
			builder.append('\u0007');
			break;
		case 'x':
			i = getHex(i, text, builder);
			break;
		default:
			builder.append(c);
			break;
		}
		return i;
	}

	private static int getHex(int i, final String text,
			final StringBuilder builder) {
		StringBuilder hex = new StringBuilder();
		i++;
		char c = text.charAt(i);
		while (c != '\\') {
			hex.append(c);
			i++;
			c = text.charAt(i);
		}
		int value = Integer.parseInt(hex.toString(), 16);
		builder.append((char) value);
		return i;
	}

	private static PrologTerm[] evalParameters(final AParams node) {
		PrologTerm[] params = evalParameters(1, node.getMoreParams());
		params[0] = toPrologTerm(node.getTerm());
		return params;
	}

	private static PrologTerm[] evalParameters(final int before,
			final PMoreParams moreParams) {
		PrologTerm[] params;
		if (moreParams instanceof AEmptyMoreParams) {
			params = new PrologTerm[before];
		} else if (moreParams instanceof AMoreParams) {
			AMoreParams nonempty = (AMoreParams) moreParams;
			params = evalParameters(before + 1, nonempty.getMoreParams());
			params[before] = toPrologTerm(nonempty.getTerm());
		} else
			throw new IllegalStateException(
					"Unexpected subclass of PMoreParams: "
							+ moreParams.getClass().getCanonicalName());
		return params;
	}

	private static int getArity(final AParams params) {
		PMoreParams moreParams = params.getMoreParams();
		int arity = 1;
		while (moreParams != null) {
			if (moreParams instanceof AEmptyMoreParams) {
				moreParams = null;
			} else {
				arity++;
				moreParams = ((AMoreParams) moreParams).getMoreParams();
			}
		}
		return arity;
	}

	private static int getListSize(PTerm term) {
		int size = 0;
		while (term != null) {
			boolean invalid;
			if (term instanceof AAtomTerm) {
				String atom = ((AAtomTerm) term).getName().getText();
				invalid = !"[]".equals(atom);
				term = null;
			} else if (term instanceof ATerm) {
				ATerm copoundTerm = (ATerm) term;
				String functor = copoundTerm.getFunctor().getText();
				if (".".equals(functor) || "'.'".equals(functor)) {
					AParams params = (AParams) copoundTerm.getParams();
					int arity = getArity(params);
					if (arity == 2) {
						size++;
						term = ((AMoreParams) params.getMoreParams()).getTerm();
						invalid = false;
					} else {
						invalid = true;
					}
				} else {
					invalid = true;
				}
			} else {
				invalid = true;
			}
			if (invalid) {
				size = -1;
				term = null;
			}
		}
		return size;
	}

	private static void fillListWithElements(final PrologTerm[] list,
			ATerm aterm) {
		for (int i = 0; i < list.length; i++) {
			AParams params = (AParams) aterm.getParams();
			list[i] = toPrologTerm(params.getTerm());
			if (i < list.length - 1) {
				// only get next element when this iteration was not the last
				aterm = (ATerm) ((AMoreParams) params.getMoreParams())
						.getTerm();
			}
		}
	}
}
