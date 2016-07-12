package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import de.be4.classicalb.core.parser.Utils;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.exceptions.VisitorException;
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
import de.be4.classicalb.core.parser.node.APackagePragmaExpression;
import de.be4.classicalb.core.parser.node.APackagePragmaMachineHeader;
import de.be4.classicalb.core.parser.node.APackagePragmaMachineReference;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.AUsesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.parser.node.TPragmaIdOrString;

/**
 * This class finds all references to external machines in a machine definition.
 * Use this class by calling the static method
 * {@link #getSetOfReferencedMachines()}.
 * 
 */
public class ReferencedMachines extends DepthFirstAdapter {
	private final File mainFile;
	private final Node start;
	private String name;
	private String packageName;
	private String[] packageNameArray;
	private File rootDirectory;
	private final LinkedHashMap<String, MachineReference> referncesTable;

	/**
	 * Searches the syntax tree of a machine for references to external
	 * machines, like in SEES, INCLUDES, etc.
	 * 
	 * @param machineFile
	 * 
	 * @param node
	 *            the root node of the machine's syntax tree, never
	 *            <code>null</code>
	 * @throws CheckException
	 */
	public ReferencedMachines(File machineFile, Node node) {
		this.referncesTable = new LinkedHashMap<>();
		this.mainFile = machineFile;
		this.start = node;
	}

	public void findReferencedMachines() throws BException {
		String fileName = null;
		try {
			fileName = mainFile.getCanonicalPath();
		} catch (IOException e) {
			fileName = mainFile.getAbsolutePath();
		}
		try {
			this.start.apply(this);
		} catch (VisitorException e) {
			throw new BException(fileName, new CheckException(e.getMessage(), e.getNode()));
		}
	}

	/**
	 * Returns all referenced machine names in the given machine
	 * 
	 * @return a set of machine names, never <code>null</code>
	 */
	public Set<String> getSetOfReferencedMachines() {
		Set<String> set = new HashSet<>(referncesTable.keySet());
		return set;
	}

	/**
	 * 
	 * @return the name of the machine, <code>null</code> if no name was found
	 */
	public String getName() {
		return name;
	}

	public Hashtable<String, MachineReference> getReferencesTable() {
		return new Hashtable<>(referncesTable);
	}

	public List<MachineReference> getReferences() {
		ArrayList<MachineReference> list = new ArrayList<>();
		for (Entry<String, MachineReference> entry : referncesTable.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		name = Utils.getIdentifierAsString(node.getName());
	}

	@Override
	public void caseAPackagePragmaMachineHeader(APackagePragmaMachineHeader node) {
		node.getMachineHeader().apply(this);
		determinePackageName(node.getPackage(), node);
		node.replaceBy(node.getMachineHeader()); // remove the package node
	}

	private void determinePackageName(final TPragmaIdOrString packageTerminal, final Node node) {
		final String text = packageTerminal.getText();
		// "foo.bar"
		if (!(text.startsWith("\"") && text.endsWith("\""))) {
			throw new VisitorException(node,
					"The package pragma should be followed by a string, e.g. /*@package \"foo.bar\" */.");
		}
		packageName = text.replaceAll("\"", "");

		final String[] packageNameArray = packageName.split("\\.");
		File file = mainFile;
		for (int i = packageNameArray.length - 1; i >= 0; i--) {
			final String name1 = packageNameArray[i];
			file = file.getParentFile();
			final String name2 = file.getName();
			if (!name1.equals(name2)) {
				throw new VisitorException(node, String
						.format("Package declaration does not match the folder structure: %s vs %s", name1, name2));
			}
		}
		rootDirectory = file.getParentFile();
		this.packageNameArray = packageNameArray;
	}

	/**
	 * INCLUDES, EXTENDS, IMPORTS
	 */
	@Override
	public void caseAMachineReference(AMachineReference node) {

		String name = getIdentifier(node.getMachineName());
		if (node.parent() instanceof AFileMachineReference) {
			final AFileMachineReference fileNode = (AFileMachineReference) node.parent();
			String file = fileNode.getFile().getText().replaceAll("\"", "");
			try {
				referncesTable.put(name, new MachineReference(name, node, file));
			} catch (CheckException e) {
				throw new VisitorException(e.getFirstNode(), e.getMessage());
			}
		} else if (node.parent() instanceof APackagePragmaMachineReference) {
			APackagePragmaMachineReference parent = (APackagePragmaMachineReference) node.parent();
			registerPackageReference(node, name, parent.getPackage());
			parent.replaceBy(parent.getReference());
		} else {
			referncesTable.put(name, new MachineReference(name, node));
		}
	}

	private void registerPackageReference(Node node, String machineName, TPragmaIdOrString tPragmaIdOrString) {
		if (packageNameArray == null) {
			String message = "There is no package declaration of machine " + machineName
					+ "\n. Hence, it is not allowed to use a package declaration for a referenced machine";
			throw new VisitorException(node, message);
		} else {
			String text = tPragmaIdOrString.getText().replaceAll("\"", "");
			String[] split = text.split("\\.");
			final File file = new File(getFile(split), machineName + ".mch");
			try {
				referncesTable.put(machineName, new MachineReference(machineName, node, file.getAbsolutePath()));
			} catch (CheckException e) {
				throw new VisitorException(e.getFirstNode(), e.getMessage());
			}
		}
	}

	private File getFile(String[] array) {
		File f = rootDirectory;
		for (String folder : array) {
			f = new File(f, folder);
		}
		return f;
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
		String name = node.getRefMachine().getText();
		referncesTable.put(name, new MachineReference(name, node.getRefMachine()));
		for (Node mclause : node.getMachineClauses()) {
			mclause.apply(this);
		}
	}

	// IMPLEMENTS
	@Override
	public void caseAImplementationMachineParseUnit(AImplementationMachineParseUnit node) {
		node.getHeader().apply(this);
		String name = node.getRefMachine().getText();
		referncesTable.put(name, new MachineReference(name, node.getRefMachine()));
		for (Node mclause : node.getMachineClauses()) {
			mclause.apply(this);
		}
	}

	private void registerMachineNames(List<PExpression> referencedMachineList) {
		for (PExpression machineExpression : referencedMachineList) {
			if (machineExpression instanceof AIdentifierExpression) {
				AIdentifierExpression identifier = (AIdentifierExpression) machineExpression;
				String name = getIdentifier(identifier.getIdentifier());
				referncesTable.put(name, new MachineReference(name, identifier));
			} else if (machineExpression instanceof AFileExpression) {
				final AFileExpression fileNode = (AFileExpression) machineExpression;
				final AIdentifierExpression identifier = (AIdentifierExpression) fileNode.getIdentifier();
				String file = fileNode.getContent().getText().replaceAll("\"", "");
				String name = getIdentifier(identifier.getIdentifier());
				MachineReference machineReference;
				try {
					machineReference = new MachineReference(name, identifier, file);
					referncesTable.put(name, machineReference);
				} catch (CheckException e) {
					throw new VisitorException(e.getFirstNode(), e.getMessage());
				}
			} else if (machineExpression instanceof APackagePragmaExpression) {
				final APackagePragmaExpression packagePragma = (APackagePragmaExpression) machineExpression;
				final AIdentifierExpression identifier = (AIdentifierExpression) packagePragma.getIdentifier();
				final String name = getIdentifier(identifier.getIdentifier());
				registerPackageReference(machineExpression, name, packagePragma.getPackage());
				packagePragma.replaceBy(packagePragma.getIdentifier());
			} else {
				throw new RuntimeException("Not supported class: " + machineExpression.getClass());
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
	public void caseAInitialisationMachineClause(AInitialisationMachineClause node) {
	}

	@Override
	public void caseALocalOperationsMachineClause(ALocalOperationsMachineClause node) {
	}
}
