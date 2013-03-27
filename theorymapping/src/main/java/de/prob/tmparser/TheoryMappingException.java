package de.prob.tmparser;

public class TheoryMappingException extends RuntimeException {
	private static final long serialVersionUID = -6323718760001476629L;

	public TheoryMappingException() {
		super();
	}

	public TheoryMappingException(String message, Throwable cause) {
		super(message, cause);
	}

	public TheoryMappingException(String message) {
		super(message);
	}

	public TheoryMappingException(Throwable cause) {
		super(cause);
	}
}
