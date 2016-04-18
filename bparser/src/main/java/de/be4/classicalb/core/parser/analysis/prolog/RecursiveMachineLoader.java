package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.FileSearchPathProvider;
import de.be4.classicalb.core.parser.IDefinitionFileProvider;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.IFileContentProvider;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AAssertionsMachineClause;
import de.be4.classicalb.core.parser.node.AConstantsMachineClause;
import de.be4.classicalb.core.parser.node.ADefinitionsMachineClause;
import de.be4.classicalb.core.parser.node.AInitialisationMachineClause;
import de.be4.classicalb.core.parser.node.AInvariantMachineClause;
import de.be4.classicalb.core.parser.node.AOperationsMachineClause;
import de.be4.classicalb.core.parser.node.APropertiesMachineClause;
import de.be4.classicalb.core.parser.node.AVariablesMachineClause;
import de.be4.classicalb.core.parser.node.PDefinition;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePositions;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

/**
 * This class implements the functionality to load and parse all machines that
 * are referenced in any other loaded machine.
 * 
 * Cyclic references are detected. If an error occurs in an external machine,
 * the error message is mapped to the uses/includes/etc. statement in the main
 * machine.
 * 
 * 
 */
public class RecursiveMachineLoader {
	private static final String[] SUFFICES = new String[] { ".ref", ".mch",
			".sys" };
	private final File rootDirectory;
	private final NodeIdAssignment nodeIds = new NodeIdAssignment();
	private String main;
	private boolean verbose;
	private final Map<String, Start> parsedMachines = new TreeMap<String, Start>();
	private final Map<String, File> parsedFiles = new TreeMap<String, File>();
	private final List<File> files = new ArrayList<File>();
	private final Map<String, SourcePositions> positions = new HashMap<String, SourcePositions>();
	private final IFileContentProvider contentProvider;

	public RecursiveMachineLoader(final String directory,
			final IDefinitionFileProvider contentProvider2) throws BException {
		if (directory == null) {
			this.rootDirectory = new File(".");
		} else {
			this.rootDirectory = new File(directory);
		}
		if (!rootDirectory.exists()) {
			throw new BException(null, new IOException(
					"Directory does not exist: " + directory));
		}
		contentProvider = contentProvider2;
	}

	/**
	 * Loads all machines that are (recursively) included (seen,etc) by the
	 * given main machine
	 * 
	 * @param main
	 *            The main machine
	 * @throws BException
	 */
	public void loadAllMachines(final File startfile, final Start main,
			final SourcePositions positions, final IDefinitions definitions)
			throws BException {
		injectDefinitions(main, definitions);
		registerDefinitionFileUsage(definitions);
		files.add(startfile);
		recursivlyLoadMachine(startfile, main, new ArrayList<String>(), true,
				positions, rootDirectory);
	}

	public void printAsProlog(final PrintWriter out, final boolean useIndention) {
		final IPrologTermOutput pout = new PrologTermOutput(out, useIndention);
		printAsProlog(pout);
	}

	/**
	 * Prints the machines loaded by
	 * {@link #loadAllMachines(File,Start,SourcePositions,IDefinitions)}.
	 * 
	 * @param pout
	 */
	public void printAsProlog(final IPrologTermOutput pout) {
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(
				getNodeIdMapping());
		final ASTProlog prolog = new ASTProlog(pout, pprinter);

		// parser version
		pout.openTerm("parser_version");
		pout.printAtom(BParser.getBuildRevision());
		pout.closeTerm();
		pout.fullstop();

		// machine
		pout.openTerm("classical_b");
		pout.printAtom(main);
		pout.openList();
		for (final File file : files) {
			try {
				pout.printAtom(file.getCanonicalPath());
			} catch (IOException e) {
				pout.printAtom(file.getPath());
			}
		}
		pout.closeList();
		pout.closeTerm();
		pout.fullstop();
		for (final Map.Entry<String, Start> entry : getParsedMachines()
				.entrySet()) {
			pout.openTerm("machine");
			final SourcePositions src = positions.get(entry.getKey());
			pprinter.setSourcePositions(src);
			entry.getValue().apply(prolog);
			pout.closeTerm();
			pout.fullstop();
		}

		pout.flush();
	}

	private void loadMachine(final List<String> ancestors,
			final File machineFile) throws BException, IOException {
		if (files.contains(machineFile)) {
			return;
		}
		final BParser parser = new BParser(machineFile.getAbsolutePath());
		final Start tree = parser.parseFile(machineFile, verbose,
				contentProvider
				//new CachingDefinitionFileProvider(machineFile)
		);
		//
		
		files.add(machineFile);

		registerDefinitionFileUsage(parser.getDefinitions());
		injectDefinitions(tree, parser.getDefinitions());
		recursivlyLoadMachine(machineFile, tree, ancestors, false,
				parser.getSourcePositions(), machineFile.getParentFile());
	}

	private void registerDefinitionFileUsage(final IDefinitions definitions) {
		files.addAll(definitions.getDefinitionFiles());
	}

	/**
	 * Tries to find a file containing the machine with the given file name.
	 * 
	 * @param machineName
	 *            Name of the machine to include, never <code>null</code>
	 * @return reference to a file containing the machine, may be non-existent
	 *         but never <code>null</code>.
	 * @throws BException
	 *             if file is not found
	 * @throws CheckException 
	 */
	private File lookupFile(final File directory, final MachineReference machineRef)
			throws BException, CheckException {
		for (final String suffix : SUFFICES) {
			try {
				return new FileSearchPathProvider(directory.getAbsolutePath(),
						machineRef.getName() + suffix).resolve();
			} catch (FileNotFoundException e) {
				// could not resolve the combination of prefix, machineName and
				// suffix, trying next one
			}
		}
		throw new CheckException("Machine file not found: " + machineRef.getName(), machineRef.getNode());
//		throw new BException(null, "Machine file not found: " + machineName,
//				null);
	}

	private void recursivlyLoadMachine(final File machineFile,
			final Start current, List<String> ancestors, final boolean isMain,
			final SourcePositions sourcePositions, File directory)
			throws BException {

		// make a copy of the referencing machines
		ancestors = new ArrayList<String>(ancestors);

		final int fileNumber = files.indexOf(machineFile) + 1;
		if (fileNumber > 0) {
			getNodeIdMapping().assignIdentifiers(fileNumber, current);
		} else {
			throw new IllegalStateException("machine file is not registered");
		}

		final ReferencedMachines refMachines = new ReferencedMachines(current);
		final String name = refMachines.getName();

		try {
			getParsedMachines().put(name, current);
			parsedFiles.put(name, machineFile);
		} catch (NullPointerException e) {
			throw new BException(machineFile.getName(),
					"No machines loaded so far.", e);
		}

		if (name != null) {
			ancestors.add(name);
		}
		if (isMain) {
			main = name;
		}
		positions.put(name, sourcePositions);

		final SortedSet<String> references2 = refMachines
				.getReferencedMachines();
		checkForCycles(ancestors, references2);

		Set<MachineReference> references = refMachines.getReferences();
		for (final MachineReference refMachine : references) {
			try {
				final String filePragma = refMachine.getPath();
				File file = null;
				if (filePragma == null) {
					file = lookupFile(directory, refMachine);
				} else {
					File p = new File(filePragma);
					if (p.isAbsolute()) {
						file = p;
					} else {
						file = new File(directory, filePragma);
					}
				}
				if (parsedFiles.containsKey(refMachine.getName())
						&& !parsedFiles.get(refMachine.getName()).getCanonicalPath()
								.equals(file.getCanonicalPath())) {
					throw new BException(null,
							"Two files with the same name are referenced:\n"
									+ parsedFiles.get(refMachine.getName())
											.getAbsoluteFile() + "\n"
									+ file.getAbsoluteFile(), null);
				}
				if (!getParsedMachines().containsKey(refMachine.getName())) {
					loadMachine(ancestors, file);
				}
			} catch (final BException e) {
				// TODO[dp, 22.04.2008]
				throw new BException(machineFile.getName(), e);
			} catch (final IOException e) {
				// TODO[dp, 22.04.2008]
				throw new BException(machineFile.getName(), e);
			} catch (CheckException e) {
				throw new BException(machineFile.getName(), e);
			}
		}
	}

	private void checkForCycles(final List<String> ancestors,
			final Set<String> references) throws BException {
		final Set<String> cycles = new TreeSet<String>(ancestors);
		intersect(cycles, references);
		if (!cycles.isEmpty()) {
			// TODO[dp, 22.04.2008] Use sensible exception
			StringBuilder sb = new StringBuilder();
			for (String string : ancestors) {
				sb.append(string).append(" -> ");
			}
			String next = cycles.iterator().next();
			sb.append(next);
			throw new BException(next, "Cycle detected: " + sb.toString(), null);
		}
	}

	private void intersect(final Set<String> a, final Set<String> b) {
		for (final Iterator<String> it = a.iterator(); it.hasNext();) {
			final String elem = it.next();
			if (elem != null && !b.contains(elem)) {
				it.remove();
			}
		}
	}

	private void injectDefinitions(final Start tree,
			final IDefinitions definitions) {
		final DefInjector defInjector = new DefInjector(definitions);
		tree.apply(defInjector);
	}

	public NodeIdAssignment getNodeIdMapping() {
		return nodeIds;
	}

	public Map<String, Start> getParsedMachines() {
		return parsedMachines;
	}

	private static class DefInjector extends DepthFirstAdapter {
		private final IDefinitions definitions;

		public DefInjector(final IDefinitions definitions) {
			this.definitions = definitions;
		}

		@Override
		public void caseADefinitionsMachineClause(
				final ADefinitionsMachineClause node) {
			final LinkedList<PDefinition> defList = node.getDefinitions();
			defList.clear();
			for (final String name : definitions.getDefinitionNames()) {
				final PDefinition def = definitions.getDefinition(name);
				defList.add(def);
			}
		}

		// IGNORE most machine parts
		@Override
		public void caseAConstantsMachineClause(
				final AConstantsMachineClause node) {
		}

		@Override
		public void caseAVariablesMachineClause(
				final AVariablesMachineClause node) {
		}

		@Override
		public void caseAPropertiesMachineClause(
				final APropertiesMachineClause node) {
		}

		@Override
		public void caseAInvariantMachineClause(
				final AInvariantMachineClause node) {
		}

		@Override
		public void caseAAssertionsMachineClause(
				final AAssertionsMachineClause node) {
		}

		@Override
		public void caseAInitialisationMachineClause(
				final AInitialisationMachineClause node) {
		}

		@Override
		public void caseAOperationsMachineClause(
				final AOperationsMachineClause node) {
		}

	}
}
