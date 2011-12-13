/**
 * 
 */
package de.prob.parserbase;

import java.util.Collection;

import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;

/**
 * @author plagge
 * 
 */
public class ProBParserBaseAdapter {
	private final ProBParserBase base;

	public ProBParserBaseAdapter(final ProBParserBase base) {
		this.base = base;
	}

	public PrologTerm parseExpression(final String expression,
			final boolean wrap) throws ProBParseException,
			UnsupportedOperationException {
		final StructuredPrologOutput pto = new StructuredPrologOutput();
		base.parseExpression(pto, expression, wrap);
		return getSingleTerm(pto);
	}

	public PrologTerm parsePredicate(final String predicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		final StructuredPrologOutput pto = new StructuredPrologOutput();
		base.parsePredicate(pto, predicate, wrap);
		return getSingleTerm(pto);
	}

	public PrologTerm parseTransitionPredicate(final String transPredicate,
			final boolean wrap) throws ProBParseException,
			UnsupportedOperationException {
		final StructuredPrologOutput pto = new StructuredPrologOutput();
		base.parseTransitionPredicate(pto, transPredicate, wrap);
		return getSingleTerm(pto);
	}

	private PrologTerm getSingleTerm(final StructuredPrologOutput pto) {
		pto.fullstop();
		final Collection<PrologTerm> sentences = pto.getSentences();
		int size = sentences.size();
		if (size == 1)
			return sentences.iterator().next();
		else
			throw new IllegalStateException(
					"ProBParserBase should create a single term, but returned "
							+ size + " terms");
	}

}
