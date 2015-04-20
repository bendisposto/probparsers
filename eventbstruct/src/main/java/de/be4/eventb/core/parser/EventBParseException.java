package de.be4.eventb.core.parser;

import de.be4.eventb.core.parser.node.Node;
import de.be4.eventb.core.parser.node.Token;
import de.hhu.stups.sablecc.patch.SourcecodeRange;

@SuppressWarnings("serial")
public class EventBParseException extends RuntimeException {
	private final Token token;
	private final SourcecodeRange range;

	public EventBParseException(final Token token, final SourcecodeRange range,
			final String message) {
		super(message);
		this.token = token;
		this.range = range;
	}

	public EventBParseException(final Token token, final String message) {
		this(token, null, message);
	}

	/**
	 * {@link Token} which caused the parse exception. May be <code>null</code>
	 * if no special token was affected.
	 * 
	 * @return
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
	 * @return
	 */
	public SourcecodeRange getRange() {
		return range;
	}
}
