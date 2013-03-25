package de.prob.tmparser.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import de.prob.core.theorymapping.analysis.DepthFirstAdapter;
import de.prob.core.theorymapping.node.AInternalDecltype;
import de.prob.core.theorymapping.node.AOperatordecl;
import de.prob.core.theorymapping.node.PDecltype;
import de.prob.tmparser.OperatorMapping;
import de.prob.tmparser.TMOperatorType;
import de.prob.tmparser.TheoryMappingException;

public class MappingVisitor extends DepthFirstAdapter {
	private final String theoryName;
	private Collection<OperatorMapping> mappings = new ArrayList<OperatorMapping>();
	private Collection<String> knownOperators = new HashSet<String>();

	public MappingVisitor(String theoryName) {
		super();
		this.theoryName = theoryName;
	}

	@Override
	public void caseAOperatordecl(AOperatordecl node) {
		final PDecltype type = node.getDecltype();
		final String raw = node.getInternalSpec().getText();
		final String opname1 = node.getOpname().getText();
		final String opname = opname1.substring(1, opname1.length() - 1);

		if (knownOperators.contains(opname)) {
			throw new TheoryMappingException("Mapping for operation '" + opname
					+ "' declared twice.");
		}

		knownOperators.add(opname);

		if (type instanceof AInternalDecltype) {
			mappings.add(new OperatorMapping(theoryName, opname,
					TMOperatorType.INTERNAL, raw));
		} else {
			throw new TheoryMappingException("Unsupported theory mapping type "
					+ type.getClass().getName());
		}
	}

	public Collection<OperatorMapping> getMappings() {
		return mappings;
	}
}
