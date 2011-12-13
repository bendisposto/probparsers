package de.be4.ltl.core.parser;

import de.be4.ltl.core.parser.node.Token;

@SuppressWarnings("serial")
public class LtlParseException extends Exception {

	private final Token token;

	/**
	 * @noreference This constructor is not intended to be referenced by
	 *              clients.
	 */
	public LtlParseException(final Token token, final String msg) {
		super(msg);
		this.token = token;
	}

	public String getTokenString() {
		return token != null ? token.getText() : "";
	}

	public int getTokenLine() {
		return token != null ? token.getLine() : 0;
	}

	public int getTokenColumn() {
		return token != null ? token.getPos() : 0;
	}

}
