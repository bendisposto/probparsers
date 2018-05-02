package de.prob.parser.ast.nodes.substitution;

import java.util.HashSet;
import java.util.Set;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;

public class SingleAssignSubstitutionNode extends SubstitutionNode {

	private IdentifierExprNode identifier;
	private ExprNode value;

	public SingleAssignSubstitutionNode(SourceCodePosition sourceCodePosition, IdentifierExprNode identifier,
			ExprNode expr) {
		super(sourceCodePosition);
		this.identifier = identifier;
		this.value = expr;
		Set<DeclarationNode> set = new HashSet<>();
		set.add(identifier.getDeclarationNode());
		super.setAssignedVariables(set);
	}

	public IdentifierExprNode getIdentifier() {
		return identifier;
	}

	public ExprNode getValue() {
		return value;
	}

	@Override
	public String toString() {
		return identifier + " := " + value;
	}

	public void setValue(ExprNode value) {
		this.value = value;
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		SingleAssignSubstitutionNode that = (SingleAssignSubstitutionNode) other;
		return this.identifier.equalAst(that.identifier) && this.value.equalAst(that.value);
	}
}
