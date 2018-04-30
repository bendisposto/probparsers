package de.prob.parser.ast;

public class SourceCodePosition {

	private Integer startColumn;
	private Integer startLine;
	private String text;

	public int getStartLine() {
		return this.startLine;
	}

	public int getStartColumn() {
		return this.startColumn;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setStartLine(int line) {
		this.startLine = line;
	}

	public void setStartColumn(int charPositionInLine) {
		this.startColumn = charPositionInLine;
	}

}
