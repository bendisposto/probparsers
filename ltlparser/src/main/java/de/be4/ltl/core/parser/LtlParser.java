/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import de.be4.ltl.core.parser.internal.LtlLexer;
import de.be4.ltl.core.parser.internal.PrologGenerator;
import de.be4.ltl.core.parser.internal.PrologGenerator.LtlAdapterException;
import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.lexer.LexerException;
import de.be4.ltl.core.parser.node.Start;
import de.be4.ltl.core.parser.parser.Parser;
import de.be4.ltl.core.parser.parser.ParserException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;

public class LtlParser {
	private final ProBParserBase specParser;

	public LtlParser(final ProBParserBase specParser) {
		this.specParser = specParser;
	}

	public PrologTerm generatePrologTerm(final String formula,
			final String stateID) throws LtlParseException {
		Start ast = parseLTLFormula(formula);
		StructuredPrologOutput pto = new StructuredPrologOutput();
		PrologGenerator prologGenerator = new PrologGenerator(pto, stateID,
				specParser);
		try {
			ast.apply(prologGenerator);
		} catch (LtlAdapterException e) {
			throw e.getOriginalException();
		}
		pto.fullstop();
		return pto.getSentences().iterator().next();
	}

	private static Start parseLTLFormula(final String formula)
			throws LtlParseException {
		StringReader reader = new StringReader(formula);
		PushbackReader r = new PushbackReader(reader);
		Lexer l = new LtlLexer(r);
		Parser p = new Parser(l);
		Start ast = null;
		try {
			ast = p.parse();
		} catch (ParserException e) {
			throw new LtlParseException(e.getToken(), e.getLocalizedMessage());
		} catch (LexerException e) {
			throw new LtlParseException(null, e.getLocalizedMessage());
		} catch (IOException e) {
			String msg = "StringReader should not cause IOExceptions";
			throw new IllegalStateException(msg);
		}
		return ast;
	}
}