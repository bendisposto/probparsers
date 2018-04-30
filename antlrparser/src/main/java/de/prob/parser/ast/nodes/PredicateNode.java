package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public abstract class PredicateNode extends TypedNode {

	public PredicateNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}
}
