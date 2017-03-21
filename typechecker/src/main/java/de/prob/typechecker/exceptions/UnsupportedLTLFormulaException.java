package de.prob.typechecker.exceptions;

@SuppressWarnings("serial")
public class UnsupportedLTLFormulaException extends AbstractException{

	public UnsupportedLTLFormulaException(String e) {
		super(e);
	}

	@Override
	public String getError() {
		return "NotSupportedLTLFormula";
	}

}
