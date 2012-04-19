package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.List;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

class ParamFinder extends DepthFirstAdapter {

	private Node n;
	private final SourcePosition start;
	private final List<Class<? extends Node>> classes;

	public ParamFinder(SourcePosition sourcePosition, List<Class<? extends Node>> classes) {
		this.start = sourcePosition;
		this.classes = classes;
	}

	@Override
	public void inStart(Start node) { // skip
	}

	@Override
	public void defaultIn(Node node) {
		
		for (Class<? extends Node> clazz : classes) {
			if (clazz.isInstance(node)) {
				SourcePosition startPos = node.getStartPos();
				if (n == null && start.compareTo(startPos) <= 0) n = node;
				break;
			}
		}
		
	}

	public Node getNode() {
		return n;
	}

}