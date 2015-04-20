package de.be4.classicalb.core.parser.analysis.prolog;

import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.prob.prolog.output.IPrologTermOutput;

public class OffsetPositionPrinter implements PositionPrinter {
	// this printer fixes the problem of lines or columns being off by one,
	// because #FORMULA\n or something like it had to be appended

	private IPrologTermOutput pout;

	// to look up the identifier of each node
	public final NodeIdAssignment nodeIds;

	private SourcePositions positions;

	private int lineOffset;

	private int columnOffset;

	public OffsetPositionPrinter(final NodeIdAssignment nodeIds,
			final int lineOffset, final int columnOffset) {
		this.nodeIds = nodeIds;
		this.lineOffset = lineOffset;
		this.columnOffset = columnOffset;
	}

	public void setSourcePositions(final SourcePositions positions) {
		this.positions = positions;
	}

	public void printPosition(final Node node) {
		final Integer id = nodeIds.lookup(node);
		if (id == null) {
			pout.printAtom("none");
		} else {
			if (positions == null) {
				pout.printNumber(id);
			} else {
				pout.openTerm("pos", true);
				pout.printNumber(id);
				pout.printNumber(nodeIds.lookupFileNumber(node));
				pout.printNumber(positions.getBeginLine(node) + lineOffset);
				pout.printNumber(positions.getBeginColumn(node) + columnOffset);
				pout.printNumber(positions.getEndLine(node) + lineOffset);
				pout.printNumber(positions.getEndColumn(node) + columnOffset);
				pout.closeTerm();
			}
		}
	}

	public void setPrologTermOutput(final IPrologTermOutput pout) {
		this.pout = pout;
	}

}
