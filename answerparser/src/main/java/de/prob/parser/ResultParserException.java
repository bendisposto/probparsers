/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.prob.parser;


public class ResultParserException extends Exception {

	private static final long serialVersionUID = -4980160896135162797L;

	public ResultParserException(final String message, final Throwable t) {
		super(message, t);
	}

}
