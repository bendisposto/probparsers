package de.prob.typechecker.exceptions;

@SuppressWarnings("serial")
public class ScopeException extends AbstractException{

	public ScopeException(String e){
		super(e);
	}

	@Override
	public String getError() {
		return "ScopeException";
	}
	
}
