package de.be4.classicalb.core.parser.analysis.checking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AImplementationMachineParseUnit;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PMachineClause;

public class ClausesCollector extends DepthFirstAdapter {

	private final Map<String, Set<Node>> availableClauses = new HashMap<String, Set<Node>>();
	private boolean scalarParameter = false;
	boolean collectParams = false;
	boolean refinement = false;

	public boolean hasScalarParameter() {
		return scalarParameter;
	}

	public boolean isRefinement() {
		return refinement;
	}
	
	@Override
	public void inAAbstractMachineParseUnit(final AAbstractMachineParseUnit node) {
		super.inAAbstractMachineParseUnit(node);

		final LinkedList<PMachineClause> machineClauses = node
				.getMachineClauses();

		for (final Iterator<PMachineClause> iterator = machineClauses
				.iterator(); iterator.hasNext();) {
			final PMachineClause clause = iterator.next();
			final String className = clause.getClass().getSimpleName();

			Set<Node> nodesForclause = availableClauses.get(className);

			if (nodesForclause == null) {
				nodesForclause = new HashSet<Node>();
			}

			nodesForclause.add(clause);
			availableClauses.put(className, nodesForclause);
		}
	}
	
	@Override
	public void inARefinementMachineParseUnit(ARefinementMachineParseUnit node) {
		super.inARefinementMachineParseUnit(node);
		refinement = true;
	}
	
	@Override
	public void inAImplementationMachineParseUnit(
			AImplementationMachineParseUnit node) {
		super.inAImplementationMachineParseUnit(node);
		refinement = true;
	}

	@Override
	public void inAMachineHeader(AMachineHeader node) {
		super.inAMachineHeader(node);
		collectParams = true;
	}

	@Override
	public void outAMachineHeader(AMachineHeader node) {
		super.outAMachineHeader(node);
		collectParams = false;
	}

	@Override
	public void inAIdentifierExpression(AIdentifierExpression node) {
		super.inAIdentifierExpression(node);
		scalarParameter = scalarParameter
				|| (collectParams && allLowerCase(node.getIdentifier()
						.getLast().getText()));
	}

	private boolean allLowerCase(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i)))
				return false;
		}
		return true;
	}

	public Map<String, Set<Node>> getAvailableClauses() {
		return availableClauses;
	}

}
