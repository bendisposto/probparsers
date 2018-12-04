package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;

import java.util.List;
import java.util.stream.Collectors;

public class BecomesElementOfSubstitutionNode extends SubstitutionNode {
	private List<IdentifierExprNode> identifiers;
	private ExprNode expression;

	public BecomesElementOfSubstitutionNode(SourceCodePosition sourceCodePosition, List<IdentifierExprNode> identifiers,
			ExprNode expression) {
		super(sourceCodePosition);
		this.identifiers = identifiers;
		this.expression = expression;
		this.identifiers.forEach(node -> node.setParent(this));
		this.expression.setParent(this);
	}

	public List<IdentifierExprNode> getIdentifiers() {
		return identifiers;
	}

	public ExprNode getExpression() {
		return expression;
	}

	public void setExpression(ExprNode expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return identifiers.stream().map(Object::toString).collect(Collectors.joining(",")) + " :( " + expression + ")";
	}

}
