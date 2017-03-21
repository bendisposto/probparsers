package de.prob.typechecker.exceptions;

@SuppressWarnings("serial")
public class TypeErrorException extends AbstractException {

	public TypeErrorException(String e) {
		super(e);
	}

	@Override
	public String getError() {
		return "TypeError";
	}
}
