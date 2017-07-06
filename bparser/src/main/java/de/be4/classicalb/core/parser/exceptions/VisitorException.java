package de.be4.classicalb.core.parser.exceptions;

@SuppressWarnings("serial")
public class VisitorException extends RuntimeException {

	private final Exception exception;

	public VisitorException(Exception e) {
		this.exception = e;
	}

	public Exception getException() {
		return this.exception;
	}

	@Override
	public String toString() {
		return this.exception.toString();
	}
}
