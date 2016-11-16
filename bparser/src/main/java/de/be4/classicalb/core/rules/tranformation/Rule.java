package de.be4.classicalb.core.rules.tranformation;

import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AIntegerExpression;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class Rule extends AbstractOperation {
	private AIdentifierExpression ruleId;
	private AIntegerExpression errorTypes;
	private boolean hasCounterExamples = false;

	public Rule(TIdentifierLiteral ruleName) {
		super(ruleName);
	}

	public Integer getNumberOfErrorTypes() {
		if (this.errorTypes == null) {
			return null;
		} else {
			final String text = errorTypes.getLiteral().getText();
			return Integer.parseInt(text);
		}
	}

	public void setRuleId(AIdentifierExpression ruleId) {
		this.ruleId = ruleId;
	}

	public void setErrrorTypes(AIntegerExpression aIntegerExpression) {
		this.errorTypes = aIntegerExpression;

	}

	public void setHasCounterExamples() {
		this.hasCounterExamples = true;
	}

	public boolean hasCounterExamples() {
		return this.hasCounterExamples;
	}

	public String getRuleIdString() {
		if (ruleId == null) {
			return null;
		} else {
			return ruleId.getIdentifier().getFirst().getText();
		}
	}
}
