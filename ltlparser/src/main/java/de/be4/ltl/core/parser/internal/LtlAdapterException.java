package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.parser.LtlParseException;

public class LtlAdapterException extends RuntimeException {
	private static final long serialVersionUID = -3723243181317857351L;
	private final LtlParseException e;

	public LtlAdapterException(final LtlParseException e) {
		this.e = e;
	}

	public LtlParseException getOriginalException() {
		return e;
	}
}