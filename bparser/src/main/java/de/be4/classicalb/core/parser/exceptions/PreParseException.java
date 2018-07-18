package de.be4.classicalb.core.parser.exceptions;

import de.be4.classicalb.core.preparser.node.Token;

@SuppressWarnings("serial")
public class PreParseException extends Exception {

	private final Token[] tokens;

	public PreParseException(final Token[] tokens, final String message, final Throwable cause) {
		super(message, cause);
		this.tokens = tokens;
	}

	public PreParseException(final Token token, final String message, final Throwable cause) {
		this(new Token[] { token }, message, cause);
	}

	public PreParseException(final String message, final Throwable cause) {
		this(new Token[0], message, cause);
	}

	public PreParseException(final Token[] tokens, final String message) {
		super(message);
		this.tokens = tokens;
	}

	public PreParseException(final Token token, final String message) {
		this(new Token[] { token }, message);
	}

	public PreParseException(final String message) {
		this(new Token[0], message);
	}

	public Token[] getTokens() {
		return tokens;
	}
}
