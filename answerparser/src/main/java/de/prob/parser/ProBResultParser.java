/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.prob.parser;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

import de.prob.core.sablecc.lexer.Lexer;
import de.prob.core.sablecc.lexer.LexerException;
import de.prob.core.sablecc.node.Start;
import de.prob.core.sablecc.parser.Parser;
import de.prob.core.sablecc.parser.ParserException;

public final class ProBResultParser {

	private ProBResultParser() {
		throw new UnsupportedOperationException(
				"not intended for instantiation");
	}

	public static Start parse(final String prologAnswer)
			throws ResultParserException {

		if (prologAnswer.length() == 0)
			throw new ResultParserException("Received empty Result", null);

		final PushbackReader codeReader = new PushbackReader(new StringReader(
				prologAnswer), prologAnswer.length());
		final Lexer lexer = new Lexer(codeReader);
		final Parser parser = new Parser(lexer);
		Start parseResult = null;
		try {
			parseResult = parser.parse();
		} catch (final ParserException e) {
			String message = "Internal Error while parsing ProB Answer. This ist most likly a bug in the Result-Parser. String was: '"
					+ prologAnswer
					+ "'. Last Token was '"
					+ e.getToken()
					+ "': " + e.getLocalizedMessage();
			throw new ResultParserException(message, e);
		} catch (final LexerException e) {
			String message = "Internal Error while parsing ProB Answer. This ist most likly a bug in the Result-Parser String was: '"
					+ prologAnswer + "': " + e.getLocalizedMessage();
			throw new ResultParserException(message, e);
		} catch (final IOException e) {
			String message = "Internal Error while parsing ProB Answer. This ist most likly a bug in the Result-Parser String was: "
					+ prologAnswer + "': " + e.getLocalizedMessage();
			throw new ResultParserException(message, e);
		}

		return parseResult;
	}
}