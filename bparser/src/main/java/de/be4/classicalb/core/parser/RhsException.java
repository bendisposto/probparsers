package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.node.Token;

@SuppressWarnings("serial")
public class RhsException extends Exception {

	private Token token;

	public RhsException(de.be4.classicalb.core.parser.node.Token token) {
		this.token = token;
	}
	
	public Token getToken() {
		return token;
	}

}
