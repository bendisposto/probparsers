package de.prob.typechecker.exceptions;

@SuppressWarnings("serial")
public abstract class AbstractException extends RuntimeException {

	public AbstractException(String e) {
		super(e);
	}

	public abstract String getError();
	
}
