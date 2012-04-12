/**
 * 
 */
package de.be4.ltl.core.parser.internal;

/**
 * A common class for LTL and CTL tokens
 * 
 * @author plagge
 */
public final class UniversalToken {
	private final String text;
	private final int line, column;

	public static UniversalToken createToken(
			final de.be4.ltl.core.parser.node.Token token) {
		return token == null ? null : new UniversalToken(token.getText(),
				token.getLine(), token.getPos());
	}

	public static UniversalToken createToken(
			final de.be4.ltl.core.ctlparser.node.Token token) {
		return token == null ? null : new UniversalToken(token.getText(),
				token.getLine(), token.getPos());
	}

	private UniversalToken(String text, int line, int column) {
		this.text = text;
		this.line = line;
		this.column = column;
	}

	public String getText() {
		return text;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

}
