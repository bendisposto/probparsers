package de.prob.parser.ast.nodes.ltl;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;

public abstract class LTLNode extends Node {
	public LTLNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

}
