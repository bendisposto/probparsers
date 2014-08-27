package de.be4.classicalb.core.parser.analysis;

/* -*- jde -*- ASTPrinter.java.in */

import java.io.PrintStream;
import java.util.Stack;

import de.be4.classicalb.core.parser.node.EOF;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;
import de.hhu.stups.sablecc.patch.PositionedNode;

/**
 * Text display of the AST, with (optionally) color output.
 * 
 * To print the AST we do a reverse depth first traversal. We do this because it
 * is easier to know which element is the last child in any node. This makes it
 * easier to do nice indenting.
 * 
 * @author Roger Keays
 */
public class ASTPrinter extends ReversedDepthFirstAdapter {

	// ---Constants------------------------------------------------
	public static char ESC = 27;

	// Text attributes
	public static final int NORMAL = 0;
	public static final int BOLD = 1;
	public static final int UNDERSCORE = 4;
	public static final int BLINK = 5;
	public static final int REVERSE = 7;
	public static final int CONCEALED = 8;

	// Foreground colors
	public static final int FG_BLACK = 30;
	public static final int FG_RED = 31;
	public static final int FG_GREEN = 32;
	public static final int FG_YELLOW = 33;
	public static final int FG_BLUE = 34;
	public static final int FG_MAGENTA = 35;
	public static final int FG_CYAN = 36;
	public static final int FG_WHITE = 37;

	// Background colors
	public static final int BG_BLACK = 40;
	public static final int BG_RED = 41;
	public static final int BG_GREEN = 42;
	public static final int BG_YELLOW = 43;
	public static final int BG_BLUE = 44;
	public static final int BG_MAGENTA = 45;
	public static final int BG_CYAN = 46;
	public static final int BG_WHITE = 47;

	// variables. We use a stack to push on indent tokens...
	private String indent = "", output = "";
	private boolean last = false;
	private Stack<String> indentchar = new Stack<String>();
	private boolean color = false;

	private final PrintStream out;

	public ASTPrinter(PrintStream out) {
		this.out = out;
	}

	public ASTPrinter() {
		this(System.out);
	}

	/*
	 * The last node we visit. It prints out the entire text that we have built.
	 */
	public void outStart(Start node) {
		out.println(treeColor() + "\n  >"
				+ output.substring(3, output.length()) + "\n" + resetColor());
	}

	/*
	 * As we visit each non-terminal node push on the indent we need for this
	 * node. The next node we visit will always be the last child of this node.
	 */
	public void defaultIn(Node node) {
		if (last)
			indentchar.push("`");
		else
			indentchar.push("|");

		indent = indent + "   ";
		last = true;
	}

	/*
	 * As we leave a non-terminal node, we pull off the indent character and
	 * prepend this nodes line to the output text.
	 */
	public void defaultOut(Node node) {
		// replace the current indent with the one from the stack
		indent = indent.substring(0, indent.length() - 3);
		indent = indent.substring(0, indent.length() - 1) + indentchar.pop();

		// prepend this line to the output.
		output = indent + "- " + setColor(BOLD, FG_CYAN, BG_BLACK)
				+ node.getClass().getSimpleName()
				+ ((PositionedNode) node).getStartPos() + "-"
				+ ((PositionedNode) node).getEndPos() + treeColor() + "\n"
				+ output;

		// replace any ` with a |
		indent = indent.substring(0, indent.length() - 1) + '|';
	}

	/*
	 * When we visit a terminals we just print it out. We always set last to
	 * false after this because the next node we visit will never be the last
	 * sibling.
	 */
	public void defaultCase(Node node) {
		// last sibling has a ` instead of a |
		if (last)
			indent = indent.substring(0, indent.length() - 1) + '`';

		// prepend this line to the output
		output = indent + "- " + setColor(BOLD, FG_GREEN, BG_BLACK)
				+ ((Token) node).getText()
				+ ((PositionedNode) node).getStartPos() + "-"
				+ ((PositionedNode) node).getEndPos() + treeColor() + "\n"
				+ output;

		// replace any ` with a |
		indent = indent.substring(0, indent.length() - 1) + '|';
		last = false;
	}

	public void caseEOF(EOF node) {
		last = false;
	}

	/*
	 * A method to change the color codes. This only works on color-enabled
	 * terminals. In Windows/MS-DOS you need to load the ansi.sys driver from
	 * config.sys or c:\winnt\system32\config.nt (NT/win2k). ANSI.sys only works
	 * under Win2k in DOS mode. In UNIX, you need an ansi-enabled terminal...
	 */
	public String setColor(int style, int fgColor, int bgColor) {
		if (color)
			return ESC + "[" + style + ";" + fgColor + ";" + bgColor + "m";
		else
			return "";
	}

	public String resetColor() {
		return (setColor(NORMAL, FG_WHITE, BG_BLACK));
	}

	public String treeColor() {
		return (setColor(NORMAL, FG_YELLOW, BG_BLACK));
	}

	/*
	 * Not everyone wants color. It is disabled by default
	 */
	public void setColor(boolean b) {
		color = b;
	}
}
