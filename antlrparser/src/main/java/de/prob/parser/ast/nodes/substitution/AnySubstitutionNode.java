package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;
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
		super.setAssignedVariables(thenSubstitution.getAssignedVariables());
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

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		AnySubstitutionNode that = (AnySubstitutionNode) other;
		return NodeUtil.equalAst(this.parameters, that.parameters) && this.wherePredicate.equalAst(that.wherePredicate)
				&& this.thenSubstitution.equalAst(that.thenSubstitution);
	}
}
