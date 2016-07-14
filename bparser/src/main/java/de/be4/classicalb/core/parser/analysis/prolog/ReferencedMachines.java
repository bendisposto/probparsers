package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import de.be4.classicalb.core.parser.node.AImport;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.ALocalOperationsMachineClause;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AMachineReference;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APackageParseUnit;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.ARefinementMachineParseUnit;
import de.be4.classicalb.core.parser.node.ASeesMachineClause;
import de.be4.classicalb.core.parser.node.AUsesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.PImport;
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
	private final List<String> pathList = new ArrayList<String>();
	private final Hashtable<String, String> filePathTable = new Hashtable<>();
	private String name;
	private String packageName;
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

	public List<String> getPathList() {
		return this.pathList;
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
	public void caseAPackageParseUnit(APackageParseUnit node) {
		determineRootDirectory(node.getPackage(), node);
		List<PImport> copy = new ArrayList<PImport>(node.getImports());
		for (PImport e : copy) {
			e.apply(this);
		}
		node.getParseUnit().apply(this);
		//delete this node
		node.replaceBy(node.getParseUnit());
	}

	@Override
	public void caseAImport(AImport node) {
		final String[] packageArray = determinePackage(node.getImport(), node);
		final String last = packageArray[packageArray.length - 1];
		final String[] array = Arrays.copyOf(packageArray, packageArray.length - 1);
		if (last.equals("*")) {
			File file = getFileStartingAtRootDirectory(array);
			this.pathList.add(file.getAbsolutePath());
		} else {
			File file = getFileStartingAtRootDirectory(array);
			this.filePathTable.put(last, file.getAbsolutePath());
		}
	}

	private void determineRootDirectory(final TPragmaIdOrString packageTerminal, final Node node) {
		final String[] packageNameArray = determinePackage(packageTerminal, node);
		File file;
		try {
			file = mainFile.getCanonicalFile();
		} catch (IOException e) {
			throw new VisitorException(null, e.getMessage());
		}
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
	}

	private String[] determinePackage(final TPragmaIdOrString packageTerminal, final Node node) {
		final String text = packageTerminal.getText();
		// "foo.bar"
		if (!(text.startsWith("\"") && text.endsWith("\""))) {
			throw new VisitorException(node,
					"The package pragma should be followed by a string, e.g. /*@package \"foo.bar\" */.");
		}
		packageName = text.replaceAll("\"", "");

		final String[] packageNameArray = packageName.split("\\.");
		return packageNameArray;
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
		} else {
			referncesTable.put(name, new MachineReference(name, node));
		}
	}

	private File getFileStartingAtRootDirectory(String[] array) {
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
				final MachineReference machineReference = new MachineReference(name, identifier);
				if (this.filePathTable.containsKey(name)) {
					machineReference.setDirectoryPath(filePathTable.get(name));
				}
				referncesTable.put(name, machineReference);
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
