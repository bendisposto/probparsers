package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public abstract class ExprNode extends TypedNode {

	public ExprNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

}
