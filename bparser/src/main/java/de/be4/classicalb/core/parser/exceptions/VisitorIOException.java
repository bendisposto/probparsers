package de.be4.classicalb.core.parser.exceptions;

import java.io.IOException;

@SuppressWarnings("serial")
public class VisitorIOException extends RuntimeException {
	private final IOException exception;

	public VisitorIOException(IOException e) {
		this.exception = e;
	}

	public IOException getException() {
		return this.exception;
	}

	@Override
	public String toString() {
		return this.exception.toString();
	}
}
