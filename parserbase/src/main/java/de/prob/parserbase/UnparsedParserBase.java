/**
 * 
 */
package de.prob.parserbase;

import de.prob.prolog.output.IPrologTermOutput;

/**
 * An implementation of the ProB Parser Base that returns everything as unparsed
 * atoms.
 * 
 * @author plagge
 */
public class UnparsedParserBase implements ProBParserBase {
	private final String expr, pred, trans;

	public UnparsedParserBase(final String expr, final String pred,
			final String trans) {
		this.expr = expr;
		this.pred = pred;
		this.trans = trans;
	}

	public void parseExpression(final IPrologTermOutput pto,
			final String expression, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parse(pto, expression, wrap, expr, "expression");
	}

	public void parsePredicate(final IPrologTermOutput pto,
			final String predicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parse(pto, predicate, wrap, pred, "predicate");
	}

	public void parseTransitionPredicate(final IPrologTermOutput pto,
			final String transPredicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parse(pto, transPredicate, wrap, trans, "transition predicate");
	}

	private void parse(final IPrologTermOutput pto, final String formula,
			final boolean wrap, final String wrapper, final String type) {
		if (wrapper == null)
			throw new UnsupportedOperationException(type + " not supported");
		else {
			if (wrap) {
				pto.openTerm(wrapper);
			}
			pto.printAtom(formula);
			if (wrap) {
				pto.closeTerm();
			}
		}
	}

}
