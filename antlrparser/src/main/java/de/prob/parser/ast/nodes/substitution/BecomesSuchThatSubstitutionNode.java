package de.prob.parser.ast.nodes.substitution;

import java.util.List;
import java.util.stream.Collectors;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;

public class BecomesSuchThatSubstitutionNode extends SubstitutionNode {
	private List<IdentifierExprNode> identifiers;
	private PredicateNode predicate;

	public BecomesSuchThatSubstitutionNode(SourceCodePosition sourceCodePosition, List<IdentifierExprNode> identifiers,
			PredicateNode predicate) {
		super(sourceCodePosition);
		this.identifiers = identifiers;
		this.predicate = predicate;
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

}
