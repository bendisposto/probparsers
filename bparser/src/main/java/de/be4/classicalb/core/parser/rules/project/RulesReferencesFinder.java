package de.be4.classicalb.core.parser.rules.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import de.be4.classicalb.core.parser.FileSearchPathProvider;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AConstraintsMachineClause;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AFileMachineReference;
import de.be4.classicalb.core.parser.node.AImportPackage;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.AMachineHeader;
import de.be4.classicalb.core.parser.node.AMachineReference;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APackageParseUnit;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.AReferencesMachineClause;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PImportPackage;
import de.be4.classicalb.core.parser.node.PMachineReference;
import de.be4.classicalb.core.parser.node.TPragmaIdOrString;
import de.be4.classicalb.core.parser.util.Utils;

public class RulesReferencesFinder extends DepthFirstAdapter {

	private final File mainFile;
	private final Node start;
	private final List<String> pathList = new ArrayList<String>();
	private String machineName;
	private String packageName;
	private File rootDirectory;
	private final LinkedHashMap<String, RulesMachineReference> referncesTable;
	private final ArrayList<CheckException> errorList = new ArrayList<>();

	public RulesReferencesFinder(File machineFile, Node node) {
		this.referncesTable = new LinkedHashMap<>();
		this.mainFile = machineFile;
		this.start = node;
	}

	public void findReferencedMachines() throws BCompoundException {
		this.start.apply(this);
		if (errorList.size() > 0) {
			final List<BException> bExceptionList = new ArrayList<>();
			for (CheckException checkException : errorList) {
				final BException bException = new BException(mainFile.getAbsolutePath(), checkException);
				bExceptionList.add(bException);
			}
			throw new BCompoundException(bExceptionList);
		}
	}

	public String getName() {
		return machineName;
	}

	public List<RulesMachineReference> getReferences() {
		ArrayList<RulesMachineReference> list = new ArrayList<>();
		for (Entry<String, RulesMachineReference> entry : referncesTable.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}

	@Override
	public void caseAMachineHeader(AMachineHeader node) {
		machineName = Utils.getIdentifierAsString(node.getName());
		if (mainFile != null) {
			final String fileNameWithoutExtension = Utils.getFileWithoutExtension(mainFile.getName());
			if (!machineName.equals(fileNameWithoutExtension)) {
				CheckException ch = new CheckException(
						String.format("RULES_MACHINE name must match the file name: '%s' vs '%s'", machineName,
								fileNameWithoutExtension),
						node);
				errorList.add(ch);
			}
		}
	}

	@Override
	public void caseAPackageParseUnit(APackageParseUnit node) {
		determineRootDirectory(node.getPackage(), node);
		List<PImportPackage> copy = new ArrayList<PImportPackage>(node.getImports());
		for (PImportPackage e : copy) {
			e.apply(this);
		}
		node.getParseUnit().apply(this);
		// delete this node
		node.replaceBy(node.getParseUnit());
	}

	@Override
	public void caseAImportPackage(AImportPackage node) {
		final String[] packageArray = determinePackage(node.getPackage(), node);
		final File pathFile = getFileStartingAtRootDirectory(packageArray);
		final String path = pathFile.getAbsolutePath();
		if (!pathFile.exists()) {
			errorList.add(new CheckException(String.format("Imported package does not exist: %s", path), node));
			return;
		}
		if (this.pathList.contains(path)) {
			errorList.add(new CheckException(String.format("Duplicate package import: %s", node.getPackage().getText()),
					node));
			return;
		}
		this.pathList.add(path);
	}

	private void determineRootDirectory(final TPragmaIdOrString packageTerminal, final Node node) {
		final String text = packageTerminal.getText();
		if ((text.startsWith("\"") && text.endsWith("\""))) {
			this.packageName = text.replaceAll("\"", "");
		} else {
			this.packageName = text;
		}
		final String[] packageNameArray = determinePackage(packageTerminal, node);
		File dir = null;
		try {
			dir = mainFile.getCanonicalFile();
		} catch (IOException e) {
			errorList.add(new CheckException(e.getMessage(), (Node) null));
			return;
		}
		for (int i = packageNameArray.length - 1; i >= 0; i--) {
			final String name1 = packageNameArray[i];
			dir = dir.getParentFile();
			final String name2 = dir.getName();
			if (!name1.equals(name2)) {
				errorList.add(new CheckException(
						String.format("Package declaration '%s' does not match the folder structure: '%s' vs '%s'",
								this.packageName, name1, name2),
						node));
			}
		}
		rootDirectory = dir.getParentFile();
	}

	private String[] determinePackage(final TPragmaIdOrString packageTerminal, final Node node) {
		String text = packageTerminal.getText();
		// "foo.bar" or foo.bar
		if ((text.startsWith("\"") && text.endsWith("\""))) {
			text = text.replaceAll("\"", "");
		}
		final String[] packageNameArray = text.split("\\.");
		final Pattern VALID_IDENTIFIER = Pattern.compile("([\\p{L}][\\p{L}\\p{N}_]*)");
		for (int i = 0; i < packageNameArray.length; i++) {
			boolean matches = VALID_IDENTIFIER.matcher(packageNameArray[i]).matches();
			if (!matches) {
				errorList.add(new CheckException(
						String.format("Invalid folder name '%s' in package declaration.", text), node));
			}
		}
		return packageNameArray;
	}

	private File getFileStartingAtRootDirectory(String[] array) {
		File f = rootDirectory;
		for (String folder : array) {
			f = new File(f, folder);
		}
		return f;
	}

	// REFERENCES foo, bar
	@Override
	public void caseAReferencesMachineClause(AReferencesMachineClause node) {
		for (PMachineReference ref : node.getMachineReferences()) {
			registerMachineNames(ref);
		}
	}

	private void registerMachineNames(final PMachineReference machineReference) {
		if (machineReference instanceof AFileMachineReference) {
			registerMachineByFilePragma((AFileMachineReference) machineReference);

		} else {
			AMachineReference mchRef = (AMachineReference) machineReference;
			registerMachineReference(mchRef);

		}
	}

	private void registerMachineReference(AMachineReference mchRef) {
		String name = mchRef.getMachineName().get(0).getText();
		try {
			final File file = lookupFile(mainFile.getParentFile(), name, mchRef);
			RulesMachineReference rulesMachineReference = new RulesMachineReference(file, name, mchRef);
			referncesTable.put(name, rulesMachineReference);
		} catch (CheckException e) {
			errorList.add(e);
		}
	}

	private void registerMachineByFilePragma(AFileMachineReference fileNode) {
		final String filePath = fileNode.getFile().getText().replaceAll("\"", "");
		final AMachineReference ref = (AMachineReference) fileNode.getReference();
		final String name = ref.getMachineName().get(0).getText();
		File file = null;
		File tempFile = new File(filePath);
		if (tempFile.exists() && tempFile.isAbsolute() && !tempFile.isDirectory()) {
			file = tempFile;
		} else {
			tempFile = new File(mainFile.getParentFile(), filePath);
			if (tempFile.exists() && !tempFile.isDirectory()) {
				file = tempFile;
			}
		}
		if (tempFile.isDirectory()) {
			errorList.add(new CheckException(String.format("File '%s' is a directory.", filePath), fileNode.getFile()));
			return;
		} else if (file == null) {
			errorList.add(new CheckException(String.format("File '%s' does not exist.", filePath), fileNode.getFile()));
			return;
		} else {
			RulesMachineReference rulesMachineReference = new RulesMachineReference(file, name,
					fileNode.getReference());
			referncesTable.put(name, rulesMachineReference);
			return;
		}

	}

	private static final String[] SUFFICES = new String[] { ".rmch", ".mch" };

	private File lookupFile(final File parentMachineDirectory, final String name, final Node node)
			throws CheckException {
		for (final String suffix : SUFFICES) {
			try {
				final File file = new FileSearchPathProvider(parentMachineDirectory.getPath(), name + suffix,
						this.pathList).resolve();
				return file;
			} catch (FileNotFoundException e) {
				// could not resolve the combination of prefix, machineName and
				// suffix, trying next one
			}
		}
		throw new CheckException(String.format("Machine not found: '%s'", name), node);
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

}
