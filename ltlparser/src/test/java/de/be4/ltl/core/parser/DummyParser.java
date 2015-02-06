package de.be4.ltl.core.parser;

import de.prob.parserbase.ProBParseException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

class DummyParser implements ProBParserBase {
	private final boolean suppPred, suppTransPred;

	public DummyParser(final boolean suppPred, final boolean suppTransPred) {
		this.suppPred = suppPred;
		this.suppTransPred = suppTransPred;
	}

	public void parseExpression(final IPrologTermOutput pto,
			final String expression, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		throw new UnsupportedOperationException("no dummy expressions");
	}

	public void parsePredicate(final IPrologTermOutput pto,
			final String predicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		if (suppPred) {
			parse(pto, predicate, wrap, "dpred");
		} else
			throw new UnsupportedOperationException("no dummy predicates");
	}

	public void parseTransitionPredicate(final IPrologTermOutput pto,
			final String transPredicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		if (suppTransPred) {
			parse(pto, transPredicate, wrap, "dtrans");
		} else
			throw new UnsupportedOperationException(
					"no dummy transition predicates");
	}

	private void parse(final IPrologTermOutput pto, final String text,
			final boolean wrap, final String wrapper) throws ProBParseException {
		// TODO: cant we use the real B parser here?
		for (int i = 0; i < text.length(); i++) {
			final char ch = text.charAt(i);
			if (!Character.isLowerCase(ch) && "()[]{}".indexOf(ch) == -1)
				throw new ProBParseException("syntax error");
		}
		if (wrap) {
			pto.openTerm(wrapper);
		}
		pto.printAtom(text);
		if (wrap) {
			pto.closeTerm();
		}

	}
}
