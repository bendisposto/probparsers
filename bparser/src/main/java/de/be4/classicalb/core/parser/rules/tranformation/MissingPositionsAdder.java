package de.be4.classicalb.core.parser.rules.tranformation;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class MissingPositionsAdder extends DepthFirstAdapter {

	private MissingPositionsAdder() {
	}

	public static void injectPositions(Start start) {
		MissingPositionsAdder missingPositionsInjector = new MissingPositionsAdder();
		start.apply(missingPositionsInjector);
	}

	public void defaultIn(Node node) {
		SourcePosition startPos = node.getStartPos();
		if (startPos == null) {
			Node parent = node.parent();
			if (parent != null && parent.getStartPos() != null) {
				node.setStartPos(parent.getStartPos());
				node.setEndPos(parent.getEndPos());
			}
		}
	}
}
