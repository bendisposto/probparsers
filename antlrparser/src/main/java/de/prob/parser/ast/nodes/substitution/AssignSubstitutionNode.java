package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.ExprNode;

public class AssignSubstitutionNode extends SubstitutionNode {

	private List<ExprNode> leftSide;
	private List<ExprNode> rightSide;

	public AssignSubstitutionNode(SourceCodePosition sourceCodePosition, List<ExprNode> leftSide,
			List<ExprNode> rightSide) {
		super(sourceCodePosition);
		this.leftSide = leftSide;
		this.rightSide = rightSide;

	}

	public List<ExprNode> getLeftSide() {
		return this.leftSide;
	}

	public List<ExprNode> getRightSide() {
		return this.rightSide;
	}
}
