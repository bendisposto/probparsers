package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.ExprNode;

import java.util.List;

public class AssignSubstitutionNode extends SubstitutionNode {

	private List<ExprNode> leftSide;
	private List<ExprNode> rightSide;

	public AssignSubstitutionNode(SourceCodePosition sourceCodePosition, List<ExprNode> leftSide,
			List<ExprNode> rightSide) {
		super(sourceCodePosition);
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		leftSide.forEach(node -> node.setParent(this));
		rightSide.forEach(node -> node.setParent(this));
	}

	public List<ExprNode> getLeftSide() {
		return this.leftSide;
	}

	public List<ExprNode> getRightSide() {
		return this.rightSide;
	}
}
