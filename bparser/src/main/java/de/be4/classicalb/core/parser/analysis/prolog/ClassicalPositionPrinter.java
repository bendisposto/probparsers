/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.prolog;

import de.be4.classicalb.core.parser.node.Node;
import de.prob.prolog.output.IPrologTermOutput;

/**
 * @author Daniel Plagge
 */
public class ClassicalPositionPrinter implements PositionPrinter {

	private IPrologTermOutput pout;

	// to look up the identifier of each node
	public final NodeIdAssignment nodeIds;

	private boolean printSourcePositions = false;

	private int lineOffset = 0;
	private int columnOffset = 0;

	private static final int NO_LINE_OR_COLUMN_VALUE = 0;

	public ClassicalPositionPrinter(final NodeIdAssignment nodeIds) {
		this.nodeIds = nodeIds;
	}

	public ClassicalPositionPrinter(final NodeIdAssignment nodeIds, int lineOffset, int columnOffset) {
		this.nodeIds = nodeIds;
		this.lineOffset = lineOffset;
		this.columnOffset = columnOffset;
		this.printSourcePositions = true;
	}

	public void printSourcePositions(boolean b) {
		this.printSourcePositions = b;
	}

	public void setLineOffset(int lineOffset) {
		this.lineOffset = lineOffset;
	}

	public void setColumnOffset(int columnOffset) {
		this.columnOffset = columnOffset;
	}

	public void printPosition(final Node node) {
		final Integer id = nodeIds.lookup(node);
		if (id == null) {
			pout.printAtom("none");
		} else {
			if (printSourcePositions) {
				pout.openTerm("pos", true);
				pout.printNumber(id);
				pout.printNumber(nodeIds.lookupFileNumber(node));
				pout.printNumber(getStartLine(node));
				pout.printNumber(getStartColumn(node));
				pout.printNumber(getEndLine(node));
				pout.printNumber(getEndColumn(node));
				pout.closeTerm();
			} else {
				// only print the id
				pout.printNumber(id);
			}
		}
	}

	private int getStartLine(Node node) {
		if (node.getStartPos() != null) {
			return node.getStartPos().getLine() + lineOffset;
		} else {
			return NO_LINE_OR_COLUMN_VALUE;
		}
	}

	private int getStartColumn(Node node) {
		if (node.getStartPos() != null) {
			return node.getStartPos().getPos() + columnOffset;
		} else {
			return NO_LINE_OR_COLUMN_VALUE;
		}
	}

	private int getEndLine(Node node) {
		if (node.getEndPos() != null) {
			return node.getEndPos().getLine() + lineOffset;
		} else {
			return NO_LINE_OR_COLUMN_VALUE;
		}
	}

	private int getEndColumn(Node node) {
		if (node.getEndPos() != null) {
			return node.getEndPos().getPos() + columnOffset;
		} else {
			return NO_LINE_OR_COLUMN_VALUE;
		}
	}

	public void setPrologTermOutput(final IPrologTermOutput pout) {
		this.pout = pout;
	}

}
