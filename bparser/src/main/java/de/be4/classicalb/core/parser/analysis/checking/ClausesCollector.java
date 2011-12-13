package de.be4.classicalb.core.parser.analysis.checking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PMachineClause;

public class ClausesCollector extends DepthFirstAdapter {

	private final Map<String, Set<Node>> availableClauses = new HashMap<String, Set<Node>>();

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

	public Map<String, Set<Node>> getAvailableClauses() {
		return availableClauses;
	}
}
