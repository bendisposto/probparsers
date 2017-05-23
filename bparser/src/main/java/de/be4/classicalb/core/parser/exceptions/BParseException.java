package de.be4.classicalb.core.parser.exceptions;

import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Token;
import de.hhu.stups.sablecc.patch.SourcecodeRange;

@SuppressWarnings("serial")
public class BParseException extends RuntimeException {
	private final Token token;
	private final SourcecodeRange range;
	private final String realMsg;

	public BParseException(final Token token, final SourcecodeRange range, final String message) {
		this(token, range, message, message.substring(message.indexOf(']') + 1));
	}

	public BParseException(final Token token, final String message) {
		this(token, null, message);
	}

	public BParseException(Token token, SourcecodeRange range, String msg, String realMsg) {
		super(msg);
		this.token = token;
		this.range = range;
		this.realMsg = realMsg;

	}

	public BParseException(Token token, SourcecodeRange range, String msg, String realMsg, Throwable throwable) {
		super(msg, throwable);
		this.token = token;
		this.range = range;
		this.realMsg = realMsg;

	}

	/**
	 * {@link Token} which caused the parse exception. May be <code>null</code>
	 * if no special token was affected.
	 * 
	 * @return the token which caused the parse exception
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * Returns the {@link SourcecodeRange} which is causing this exception. Will
	 * be <code>null</code> in case of a real lexing or parsing exception cause
	 * sourcecode ranges for the {@link Node}s of the AST have not yet been
	 * evaluated then.
	 * 
	 * @return the range of the error node
	 */
	public SourcecodeRange getRange() {
		return range;
	}

	public String getRealMsg() {
		return realMsg;
	}
}
