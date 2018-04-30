package de.prob.parser.ast.nodes;

import java.util.List;
import java.util.stream.Collectors;

import de.prob.parser.ast.SourceCodePosition;

public class BecomesSuchThatSubstitutionNode extends SubstitutionNode {
	private List<IdentifierExprNode> identifiers;
	private PredicateNode predicate;

	public BecomesSuchThatSubstitutionNode(SourceCodePosition sourceCodePosition, List<IdentifierExprNode> identifiers,
			PredicateNode predicate) {
		super(sourceCodePosition);
		this.identifiers = identifiers;
		this.predicate = predicate;
		super.setAssignedVariables(
				identifiers.stream().map(IdentifierExprNode::getDeclarationNode).collect(Collectors.toSet()));
	}

	public List<IdentifierExprNode> getIdentifiers() {
		return identifiers;
	}

	public PredicateNode getPredicate() {
		return predicate;
	}

	public void setPredicate(PredicateNode predicate) {
		this.predicate = predicate;
	}

	@Override
	public String toString() {
		return identifiers.stream().map(Object::toString).collect(Collectors.joining(",")) + " :( " + predicate + ")";
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		BecomesSuchThatSubstitutionNode that = (BecomesSuchThatSubstitutionNode) other;
		return this.predicate.equalAst(that.predicate) && NodeUtil.equalAst(this.identifiers, that.identifiers);
	}
}
