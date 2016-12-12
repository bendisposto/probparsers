/**
 * 
 */
package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.parserbase.ProBParseException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

/**
 * @author plagge
 * 
 */
public class ClassicalBParser implements ProBParserBase {
	private static final String WRAPPER_EXPR = "bexpr";
	private static final String WRAPPER_PRED = "bpred";
	private static final String WRAPPER_TRANS = "bop";

	public void parseExpression(final IPrologTermOutput pto, final String expression, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parseFormula(pto, BParser.EXPRESSION_PREFIX + expression, wrap, WRAPPER_EXPR);
	}

	public void parsePredicate(final IPrologTermOutput pto, final String predicate, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parseFormula(pto, BParser.PREDICATE_PREFIX + predicate, wrap, WRAPPER_PRED);
	}

	public void parseTransitionPredicate(final IPrologTermOutput pto, final String trans, final boolean wrap)
			throws ProBParseException, UnsupportedOperationException {
		parseFormula(pto, BParser.OPERATION_PATTERN_PREFIX + trans, wrap, WRAPPER_TRANS);
	}

	private void parseFormula(final IPrologTermOutput pto, final String formula, final boolean wrap,
			final String wrapper) throws ProBParseException {
		final Start ast;
		try {
			ast = BParser.parse(formula);
		} catch (BCompoundException e) {
			throw new ProBParseException(e.getExceptions().get(0).getLocalizedMessage());
		}
		final ASTProlog prologPrinter = new ASTProlog(pto, null);
		if (wrap) {
			pto.openTerm(wrapper);
		}
		ast.apply(prologPrinter);
		if (wrap) {
			pto.closeTerm();
		}
	}

}
