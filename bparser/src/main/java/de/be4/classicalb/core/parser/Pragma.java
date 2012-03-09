package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.node.Token;

public class Pragma {
	private final int line;
	private final int column;
	private final String content;

	public Pragma(int line, int column, String content, Token pred) {
		this.line = line;
		this.column = column;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content + " ("+line+","+column+")";
	}
}