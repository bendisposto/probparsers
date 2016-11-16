package de.be4.classicalb.core.rules.tranformation;

import java.util.List;

import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public abstract class AbstractOperation {

	private final TIdentifierLiteral name;
	private List<AIdentifierExpression> dependsOnRuleList;
	private List<AIdentifierExpression> dependsOnComputationList;
	private PPredicate activationPredicate;

	public AbstractOperation(TIdentifierLiteral name) {
		this.name = name;
	}

	public List<AIdentifierExpression> getDependsOnRulesList() {
		return this.dependsOnRuleList;
	}

	public List<AIdentifierExpression> getDependsOnComputationList() {
		return this.dependsOnComputationList;
	}

	public void setDependsOnRules(List<AIdentifierExpression> list) {
		this.dependsOnRuleList = list;
	}

	public void setDependsOnComputations(List<AIdentifierExpression> list) {
		this.dependsOnComputationList = list;
	}

	public void setActivationPredicate(PPredicate predicate) {
		this.activationPredicate = predicate;
	}

	public PPredicate getActivationPredicate() {
		return this.activationPredicate;
	}

	public String getName() {
		return this.name.getText();
	}

	public TIdentifierLiteral getNameLiteral() {
		return this.name;
	}

}
