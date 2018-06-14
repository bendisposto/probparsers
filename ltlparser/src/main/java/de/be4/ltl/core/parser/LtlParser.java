/*
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
import de.be4.ltl.core.parser.internal.UniversalToken;
import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.lexer.LexerException;
import de.be4.ltl.core.parser.node.Start;
import de.be4.ltl.core.parser.parser.Parser;
import de.be4.ltl.core.parser.parser.ParserException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.StructuredPrologOutput;

public class LtlParser extends TemporalLogicParser<Start> {
	public LtlParser(final ProBParserBase specParser) {
		super(specParser);
	}

	protected Start parseFormula(final String formula)
			throws LtlParseException, IOException {
		StringReader reader = new StringReader(formula);
		PushbackReader r = new PushbackReader(reader);
		Lexer l = new LtlLexer(r);
		Parser p = new Parser(l);
		Start ast = null;
		try {
			ast = p.parse();
		} catch (ParserException e) {
			final UniversalToken token = UniversalToken.createToken(e
					.getToken());
			throw new LtlParseException(token, e.getLocalizedMessage());
		} catch (LexerException e) {
			throw new LtlParseException(null, e.getLocalizedMessage());
		}
		return ast;
	}

	@Override
	protected void applyPrologGenerator(StructuredPrologOutput pto,
			String stateID, ProBParserBase specParser, Start ast) {
		final PrologGenerator prologGenerator = new PrologGenerator(pto,
				stateID, specParser);
		ast.apply(prologGenerator);
	}
}
