package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.predicate.PredicateNode;

public class IfOrSelectSubstitutionsNode extends SubstitutionNode {
	protected List<PredicateNode> conditions;
	protected List<SubstitutionNode> substitutions;
	protected SubstitutionNode elseSubstitution;
	private Operator operator;

	public enum Operator {
		IF, SELECT
	}

	/**
	 * Constructor called by the subclasses.
	 * 
	 * @param conditions
	 *            the list of conditions
	 * @param substitutions
	 *            the list of substitution
	 * @param elseSubstitution
	 *            the else substitution; maybe {@code null}
	 */
	public IfOrSelectSubstitutionsNode(SourceCodePosition sourceCodePosition, Operator operator,
			List<PredicateNode> conditions, List<SubstitutionNode> substitutions, SubstitutionNode elseSubstitution) {
		super(sourceCodePosition);
		this.operator = operator;
		this.conditions = conditions;
		this.substitutions = substitutions;
		this.elseSubstitution = elseSubstitution;
	}

	public Operator getOperator() {
		return this.operator;
	}

	public List<SubstitutionNode> getSubstitutions() {
		return this.substitutions;
	}

	public SubstitutionNode getElseSubstitution() {
		return this.elseSubstitution;
	}

	public List<PredicateNode> getConditions() {
		return this.conditions;
	}

	public void setConditions(List<PredicateNode> conditions) {
		this.conditions = conditions;
	}

	public void setSubstitutions(List<SubstitutionNode> substitutions) {
		this.substitutions = substitutions;
	}

	public void setElseSubstitution(SubstitutionNode elseSub) {
		this.elseSubstitution = elseSub;
	}

	String prepareToString(String selectIf, String whenElsif) {
		StringBuilder sb = new StringBuilder();
		sb.append(selectIf).append(" ").append(conditions.get(0)).append(" THEN ").append(substitutions.get(0));
		for (int i = 1; i < conditions.size(); i++) {
			sb.append(" ").append(whenElsif).append(" ").append(conditions.get(i)).append(" THEN ")
					.append(substitutions.get(i));
		}
		if (null != elseSubstitution) {
			sb.append(" ELSE ").append(elseSubstitution);
		}
		sb.append(" END");
		return sb.toString();
	}

}
