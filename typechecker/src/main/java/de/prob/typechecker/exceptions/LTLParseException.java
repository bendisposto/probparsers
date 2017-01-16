package de.prob.typechecker.exceptions;

@SuppressWarnings("serial")
public class LTLParseException extends AbstractException{

	public LTLParseException(String e) {
		super(e);
	}

	@Override
	public String getError() {
		return "LTLParseError";
	}

}
