package de.prob.parser.ast.nodes.predicate;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.TypedNode;

public abstract class PredicateNode extends TypedNode {

	public PredicateNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}
}
