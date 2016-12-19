package de.be4.classicalb.core.parser.rules.tranformation;

import java.util.List;

import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.rules.project.RulesMachineReference;

public class FunctionOperation extends AbstractOperation {

	private PPredicate preconditionPredicate;

	public FunctionOperation(TIdentifierLiteral name, String fileName, String machineName,
			List<RulesMachineReference> machineReferences) {
		super(name, fileName, machineName, machineReferences);
	}

	public PPredicate getPreconditionPredicate() {
		return preconditionPredicate;
	}

	public void setPreconditionPredicate(PPredicate preconditionPredicate) {
		this.preconditionPredicate = preconditionPredicate;
	}
}
