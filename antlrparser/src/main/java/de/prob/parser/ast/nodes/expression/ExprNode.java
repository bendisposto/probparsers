package de.prob.parser.ast.nodes.expression;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.TypedNode;

public abstract class ExprNode extends TypedNode {

	public ExprNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

}
