package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.analysis.prolog.ASTProlog;
import de.be4.classicalb.core.parser.analysis.prolog.ClassicalPositionPrinter;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.parserbase.ProBParseException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

public class ClassicalBParser implements ProBParserBase {
	private static final String WRAPPER_EXPR = "bexpr";
	private static final String WRAPPER_PRED = "bpred";
	private static final String WRAPPER_TRANS = "bop";

	@Override
	public void parseExpression(final IPrologTermOutput pto, final String expression, final boolean wrap)
			throws ProBParseException {
		try {
			Start ast = new BParser().parseExpression(expression);
			printAst(pto, ast, wrap, WRAPPER_EXPR);
		} catch (BCompoundException e) {
			throw new ProBParseException(e.getFirstException().getLocalizedMessage());
		}
	}

	@Override
	public void parsePredicate(final IPrologTermOutput pto, final String predicate, final boolean wrap)
			throws ProBParseException {
		try {
			Start ast = new BParser().parsePredicate(predicate);
			printAst(pto, ast, wrap, WRAPPER_PRED);
		} catch (BCompoundException e) {
			throw new ProBParseException(e.getFirstException().getLocalizedMessage());
		}
	}

	@Override
	public void parseTransitionPredicate(final IPrologTermOutput pto, final String trans, final boolean wrap)
			throws ProBParseException {
		try {
			Start ast = new BParser().parseTranstion(trans);
			printAst(pto, ast, wrap, WRAPPER_TRANS);
		} catch (BCompoundException e) {
			throw new ProBParseException(e.getFirstException().getLocalizedMessage());
		}
	}

	private void printAst(final IPrologTermOutput pto, Start ast, final boolean wrap, final String wrapper) {
		NodeIdAssignment na = new NodeIdAssignment();
		ast.apply(na);
		ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(na, -1, 0);
		final ASTProlog prologPrinter = new ASTProlog(pto, pprinter);
		if (wrap) {
			pto.openTerm(wrapper);
		}
		ast.apply(prologPrinter);
		if (wrap) {
			pto.closeTerm();
		}
	}

}
