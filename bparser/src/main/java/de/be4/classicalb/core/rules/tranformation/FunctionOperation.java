package de.be4.classicalb.core.rules.tranformation;

import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class FunctionOperation extends AbstractOperation {

	private PPredicate preconditionPredicate;

	public FunctionOperation(TIdentifierLiteral name) {
		super(name);
	}

	public PPredicate getPreconditionPredicate() {
		return preconditionPredicate;
	}

	public void setPreconditionPredicate(PPredicate preconditionPredicate) {
		this.preconditionPredicate = preconditionPredicate;
	}
}
