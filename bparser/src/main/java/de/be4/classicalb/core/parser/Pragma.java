package de.be4.classicalb.core.parser;

public class Pragma {
	private final int line;
	private final int column;
	private final String content;

	public Pragma(int line, int column, String content) {
		this.line = line;
		this.column = column;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return content + " ("+line+","+column+")";
	}
}