/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.prolog;

import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.prob.prolog.output.IPrologTermOutput;

/**
 * @author Daniel Plagge
 */
public class ClassicalPositionPrinter implements PositionPrinter {

	private IPrologTermOutput pout;

	// to look up the identifier of each node
	public final NodeIdAssignment nodeIds;

	private SourcePositions positions;

	public ClassicalPositionPrinter(final NodeIdAssignment nodeIds) {
		this.nodeIds = nodeIds;
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
				pout.printNumber(positions.getBeginLine(node));
				pout.printNumber(positions.getBeginColumn(node));
				pout.printNumber(positions.getEndLine(node));
				pout.printNumber(positions.getEndColumn(node));
				pout.closeTerm();
			}
		}
	}

	public void setPrologTermOutput(final IPrologTermOutput pout) {
		this.pout = pout;
	}

}
