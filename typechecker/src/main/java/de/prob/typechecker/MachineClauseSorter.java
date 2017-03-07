package de.prob.typechecker;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
import de.be4.classicalb.core.parser.node.AAssertionsMachineClause;
import de.be4.classicalb.core.parser.node.AConcreteVariablesMachineClause;
import de.be4.classicalb.core.parser.node.AConstantsMachineClause;
import de.be4.classicalb.core.parser.node.AConstraintsMachineClause;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.ASetsMachineClause;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PMachineClause;
import de.be4.classicalb.core.parser.node.Start;

public class MachineClauseSorter extends DepthFirstAdapter {

	public static void sortMachineClauses(Start start) {
		MachineClauseSorter sorter = new MachineClauseSorter();
		start.apply(sorter);
	}

	@Override
	public void caseAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {

		LinkedList<PMachineClause> machineClauses = node.getMachineClauses();

		PMachineClauseComparator comperator = new PMachineClauseComparator();
		// Sort the machine clauses:
		// 1. import clauses
		// 2. declaration clauses
		// 3. properties clauses
		// 4. operation clauses
		Collections.sort(machineClauses, comperator);
	}
}

class PMachineClauseComparator implements Comparator<PMachineClause>,
		Serializable {

	private static final long serialVersionUID = 2606332412649258695L;
	private static Hashtable<Object, Integer> priority = new Hashtable<Object, Integer>();
	static {
		// declarations clauses

		priority.put(ASeesMachineClause.class, 12);
		priority.put(ASetsMachineClause.class, 11);
		priority.put(AAbstractConstantsMachineClause.class, 10);
		priority.put(AConstantsMachineClause.class, 9);
		priority.put(AVariablesMachineClause.class, 8);
		priority.put(AConcreteVariablesMachineClause.class, 7);
		priority.put(ADefinitionsMachineClause.class, 6);

		// properties clauses
		priority.put(AConstraintsMachineClause.class, 5);
		priority.put(APropertiesMachineClause.class, 4);
		priority.put(AInvariantMachineClause.class, 3);
		priority.put(AAssertionsMachineClause.class, 2);
		priority.put(AOperationsMachineClause.class, 1);
		priority.put(AInitialisationMachineClause.class, 0);
	}

	public int compare(PMachineClause arg0, PMachineClause arg1) {
		if (priority.get(arg0.getClass()).intValue() < priority.get(
				arg1.getClass()).intValue()) {
			return 1;
		}
		if (priority.get(arg0.getClass()).intValue() > priority.get(
				arg1.getClass()).intValue()) {
			return -1;
		}

		return 0;
	}

}