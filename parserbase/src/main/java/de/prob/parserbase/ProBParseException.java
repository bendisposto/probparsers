package de.prob.parserbase;

/**
 * @author plagge
 * 
 */
public class ProBParseException extends Exception {
	private static final long serialVersionUID = -207894458525751023L;

	public ProBParseException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ProBParseException(final String message) {
		super(message);
	}

	public ProBParseException(final Throwable cause) {
		super(cause);
	}

	public ProBParseException() {
		super();
	}
}
