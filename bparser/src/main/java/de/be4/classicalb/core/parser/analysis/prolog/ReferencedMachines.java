package de.be4.classicalb.core.parser.analysis.prolog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AConstraintsMachineClause;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AFileExpression;
import de.be4.classicalb.core.parser.node.AFileMachineReference;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.AImplementationMachineParseUnit;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.ALocalOperationsMachineClause;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AMachineReference;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.AUsesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

/**
 * This class finds all references to external machines in a machine definition.
 * Use this class by calling the static method {@link #getReferencedMachines()}.
 * 
 * @author plagge
 */
public class ReferencedMachines extends DepthFirstAdapter {
	private SortedSet<String> machines = new TreeSet<String>();
	private String name;
	private HashMap<String, MachineReference> referncesTable = new HashMap<>();

	/**
	 * Searches the syntax tree of a machine for references to external
	 * machines, like in SEES, INCLUDES, etc.
	 * 
	 * @param node
	 *            the root node of the machine's syntax tree, never
	 *            <code>null</code>
	 */
	public ReferencedMachines(Node node) {
		node.apply(this);
	}

	/**
	 * Returns all referenced machine names in the given machine
	 * 
	 * @return a set of machine names, never <code>null</code>
	 */
	public SortedSet<String> getReferencedMachines() {
		return machines;
	}

	/**
	 * 
	 * @return the name of the machine, <code>null</code> if no name was found
	 */
	public String getName() {
		return name;
	}

	public Set<MachineReference> getReferences(){
		return new HashSet<>(referncesTable.values());
	}
	
	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		name = Utils.getIdentifierAsString(node.getName());
	}

	@Override
	public void caseAMachineReference(AMachineReference node) {
		String name = getIdentifier(node.getMachineName());
		machines.add(name);
		if (node.parent() instanceof AFileMachineReference) {
			final AFileMachineReference fileNode = (AFileMachineReference) node
					.parent();
			String file = fileNode.getFile().getText().replaceAll("\"", "");
			referncesTable.put(name, new MachineReference(name, node, file));
		} else {
			referncesTable.put(name, new MachineReference(name, node));
		}
	}

	private String getIdentifier(LinkedList<TIdentifierLiteral> list) {
		return list.getLast().getText();
	}

	// SEES and USES

	@Override
	public void caseASeesMachineClause(ASeesMachineClause node) {
		registerMachineNames(node.getMachineNames());
	}

	@Override
	public void caseAUsesMachineClause(AUsesMachineClause node) {
		registerMachineNames(node.getMachineNames());
	}

	// REFINES
	@Override
	public void caseARefinementMachineParseUnit(ARefinementMachineParseUnit node) {
		node.getHeader().apply(this);
		machines.add(node.getRefMachine().getText());
		for (Node mclause : node.getMachineClauses()) {
			mclause.apply(this);
		}
	}

	// IMPLEMENTS
	@Override
	public void caseAImplementationMachineParseUnit(
			AImplementationMachineParseUnit node) {
		node.getHeader().apply(this);
		machines.add(node.getRefMachine().getText());
		for (Node mclause : node.getMachineClauses()) {
			mclause.apply(this);
		}
	}

	private void registerMachineNames(List<PExpression> machineNames) {
		for (PExpression machineName : machineNames) {
			if (machineName instanceof AIdentifierExpression) {
				AIdentifierExpression identifier = (AIdentifierExpression) machineName;
				String name = getIdentifier(identifier.getIdentifier());
				machines.add(name);
				referncesTable.put(name, new MachineReference(name, identifier));
			} else if (machineName instanceof AFileExpression) {
				final AFileExpression fileNode = (AFileExpression) machineName;
				final AIdentifierExpression identifier = (AIdentifierExpression) fileNode
						.getIdentifier();
				String file = fileNode.getContent().getText()
						.replaceAll("\"", "");
				final String name = getIdentifier(identifier.getIdentifier());
				machines.add(name);
				referncesTable.put(name, new MachineReference(name, identifier, file));
			} else {
				throw new RuntimeException("Not supported class: "
						+ machineName.getClass());
			}
		}
	}

	/***************************************************************************
	 * exclude large sections of a machine without machine references by doing
	 * nothing
	 */

	@Override
	public void caseAConstraintsMachineClause(AConstraintsMachineClause node) {
	}

	@Override
	public void caseAInvariantMachineClause(AInvariantMachineClause node) {
	}

	@Override
	public void caseAOperationsMachineClause(AOperationsMachineClause node) {
	}

	@Override
	public void caseAPropertiesMachineClause(APropertiesMachineClause node) {
	}

	@Override
	public void caseADefinitionsMachineClause(ADefinitionsMachineClause node) {
	}

	@Override
	public void caseAInitialisationMachineClause(
			AInitialisationMachineClause node) {
	}

	@Override
	public void caseALocalOperationsMachineClause(
			ALocalOperationsMachineClause node) {
	}
}
