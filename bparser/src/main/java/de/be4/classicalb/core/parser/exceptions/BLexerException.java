package de.be4.classicalb.core.parser.exceptions;

import de.be4.classicalb.core.parser.lexer.LexerException;
import de.be4.classicalb.core.parser.node.Token;

@SuppressWarnings("serial")
public class BLexerException extends LexerException {

	private final Token lastToken;
	private final String lastText;
	private final int lastLine;
	private final int lastPos;

	public BLexerException(final Token lastToken, final String message,
			final String lastText, final int lastLine, final int lastPos) {
		super(message);
		this.lastToken = lastToken;
		this.lastText = lastText;
		this.lastLine = lastLine;
		this.lastPos = lastPos;
	}

	public String getLastText() {
		return lastText;
	}

	public int getLastLine() {
		return lastLine;
	}

	public int getLastPos() {
		return lastPos;
	}

	public Token getLastToken() {
		return lastToken;
	}
}
