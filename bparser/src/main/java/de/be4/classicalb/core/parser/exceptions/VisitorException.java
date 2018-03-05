package de.be4.classicalb.core.parser.exceptions;

@SuppressWarnings("serial")
public class VisitorException extends RuntimeException {

	private final CheckException exception;

	public VisitorException(CheckException e) {
		this.exception = e;
	}

	public CheckException getException() {
		return this.exception;
	}

	@Override
	public String toString() {
		return this.exception.toString();
	}
}
