package de.be4.classicalb.core.parser.analysis.pragma.internal;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

class ParamFinder extends DepthFirstAdapter {

	private Node n;
	private final SourcePosition start;
	private final Class<? extends Node> clazz;

	public ParamFinder(SourcePosition sourcePosition, Class<? extends Node> clazz) {
		this.start = sourcePosition;
		this.clazz = clazz;
	}

	@Override
	public void inStart(Start node) { // skip
	}

	@Override
	public void defaultIn(Node node) {
		if (clazz.isInstance(node)) {
			SourcePosition startPos = node.getStartPos();
			if (n == null && start.compareTo(startPos) <= 0) n = node;
		}
	}

	public Node getNode() {
		return n;
	}

}