package de.be4.ltl.core.parser;

import java.io.IOException;

import de.be4.ltl.core.parser.internal.LtlAdapterException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;

public abstract class TemporalLogicParser<T> {

	public final ProBParserBase specParser;

	protected TemporalLogicParser(final ProBParserBase specParser) {
		this.specParser = specParser;
	}

	abstract protected T parseFormula(String formula) throws LtlParseException,
			IOException;

	abstract protected void applyPrologGenerator(StructuredPrologOutput pto,
			String stateID, ProBParserBase specParser2, T ast);

	public PrologTerm generatePrologTerm(final String formula,
			final String stateID) throws LtlParseException {
		T ast;
		try {
			ast = parseFormula(formula);
		} catch (IOException e) {
			String msg = "StringReader should not cause IOExceptions";
			throw new IllegalStateException(msg);
		}
		StructuredPrologOutput pto = new StructuredPrologOutput();
		try {
			applyPrologGenerator(pto, stateID, specParser, ast);
		} catch (LtlAdapterException e) {
			throw e.getOriginalException();
		}
		pto.fullstop();
		return pto.getSentences().iterator().next();
	}

}