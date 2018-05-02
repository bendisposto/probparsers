package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;

public class AnySubstitutionNode extends SubstitutionNode {

	private List<DeclarationNode> parameters;
	private PredicateNode wherePredicate;
	private SubstitutionNode thenSubstitution;

	public AnySubstitutionNode(SourceCodePosition sourceCodePosition, List<DeclarationNode> parameters,
			PredicateNode wherePredicate, SubstitutionNode thenSubstitution) {
		super(sourceCodePosition);
		this.parameters = parameters;
		this.wherePredicate = wherePredicate;
		this.thenSubstitution = thenSubstitution;
	}

	public List<DeclarationNode> getParameters() {
		return parameters;
	}

	public PredicateNode getWherePredicate() {
		return wherePredicate;
	}

	public SubstitutionNode getThenSubstitution() {
		return thenSubstitution;
	}

	public void setPredicate(PredicateNode predNode) {
		this.wherePredicate = predNode;
	}

	public void setSubstitution(SubstitutionNode substitutionNode) {
		this.thenSubstitution = substitutionNode;
	}

	@Override
	public String toString() {
		return "ANY " + parameters + " WHERE " + wherePredicate + " THEN " + thenSubstitution + " END";
	}

}
