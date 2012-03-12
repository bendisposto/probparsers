package de.be4.classicalb.core.parser.analysis.prolog;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
import de.be4.classicalb.core.parser.CachingDefinitionFileProvider;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.IFileContentProvider;
import de.be4.classicalb.core.parser.Pragma;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BException;
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
 * @author plagge
 * 
 */
public class RecursiveMachineLoader {
	private static final String[] SUFFICES = new String[] { ".ref", ".mch" };
	private final String directory;
	private final NodeIdAssignment nodeIds = new NodeIdAssignment();
	private String main;
	private boolean verbose;
	private final Map<String, Start> imported = new TreeMap<String, Start>();
	private final List<File> files = new ArrayList<File>();
	private final Map<String, SourcePositions> positions = new HashMap<String, SourcePositions>();
	private final IFileContentProvider contentProvider;

	public RecursiveMachineLoader(final String directory) {
		this.directory = directory;
		final File dirFile = directory == null ? null : new File(directory);
		contentProvider = new CachingDefinitionFileProvider(dirFile);
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
			final SourcePositions positions, final Definitions definitions)
			throws BException {
		injectDefinitions(main, definitions);
		registerDefinitionFileUsage(definitions);
		files.add(startfile);
		recursivlyLoadMachine(startfile, main, Collections.<String> emptySet(),
				true, positions);
	}

	public void printAsProlog(final PrintWriter out,
			final boolean useIndention, List<Pragma> pragmas) {
		final IPrologTermOutput pout = new PrologTermOutput(out, useIndention);
		printAsProlog(pout, pragmas);

	}

	public void printAsProlog(final PrintWriter out, final boolean useIndention) {
		final IPrologTermOutput pout = new PrologTermOutput(out, useIndention);
		printAsProlog(pout, new ArrayList<Pragma>());
	}

	/**
	 * Prints the machines loaded by {@link #loadAllMachines(Start)}.
	 * 
	 * @param pout
	 * @param pragmas
	 */
	public void printAsProlog(final IPrologTermOutput pout, List<Pragma> pragmas) {
		final ClassicalPositionPrinter pprinter = new ClassicalPositionPrinter(
				nodeIds);
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
		for (final Map.Entry<String, Start> entry : imported.entrySet()) {
			pout.openTerm("machine");
			final SourcePositions src = positions.get(entry.getKey());
			pprinter.setSourcePositions(src);
			entry.getValue().apply(prolog);
			pout.closeTerm();
			pout.fullstop();
		}

		NodeIdAssignment ids = pprinter.nodeIds;
		pout.openTerm("pragmas");
		pout.openList();
		for (Pragma pragma : pragmas) {
			pout.openTerm("pragma");
			Integer pred = ids.lookup(pragma.getPredecessor());
			String predecessor = pred == null ? "start" : pred.toString();
			pout.printAtomOrNumber(predecessor);
			Integer cont = ids.lookup(pragma.getContainer());
			String container = cont == null ? "start" : cont.toString();
			pout.printAtomOrNumber(container);
			Integer succ = ids.lookup(pragma.getSuccessor());
			String successor = succ == null ? "eof" : succ.toString();
			pout.printAtomOrNumber(successor);
			pout.printAtom(pragma.getContent());
			pout.closeTerm();
		}
		pout.closeList();
		pout.closeTerm();
		pout.fullstop();
		pout.flush();
	}

	public void printAsProlog(final IPrologTermOutput pout) {
		printAsProlog(pout, new ArrayList<Pragma>());
	}

	private void loadMachine(final Set<String> ancestors,
			final String machineName) throws BException, IOException {
		final File machineFile = lookupFile(machineName);
		final BParser parser = new BParser(machineFile.getName());
		final Start tree = parser.parseFile(machineFile, verbose,
				contentProvider);
		files.add(machineFile);
		registerDefinitionFileUsage(parser.getDefinitions());
		injectDefinitions(tree, parser.getDefinitions());
		recursivlyLoadMachine(machineFile, tree, ancestors, false,
				parser.getSourcePositions());
	}

	private void registerDefinitionFileUsage(final Definitions definitions) {
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
	 */
	private File lookupFile(final String machineName) throws BException {
		final String prefix = directory == null ? "" : directory
				+ File.separator;
		for (int i = 0; i < SUFFICES.length; i++) {
			final String suffix = SUFFICES[i];
			final File file = new File(prefix + machineName + suffix);
			if (file.exists()) return file;
		}
		throw new BException(null, "Machine file not found: " + machineName,
				null);
	}

	private void recursivlyLoadMachine(final File machineFile,
			final Start current, Set<String> ancestors, final boolean isMain,
			final SourcePositions sourcePositions) throws BException {
		// make a copy of the referencing machines
		ancestors = new TreeSet<String>(ancestors);

		final int fileNumber = files.indexOf(machineFile) + 1;
		if (fileNumber > 0) {
			nodeIds.assignIdentifiers(fileNumber, current);
		} else
			throw new IllegalStateException("machine file is not registered");

		final ReferencedMachines refMachines = new ReferencedMachines(current);
		final String name = refMachines.getName();
		final SortedSet<String> references = refMachines
				.getReferencedMachines();
		imported.put(name, current);
		ancestors.add(name);
		if (isMain) {
			main = name;
		}

		positions.put(name, sourcePositions);

		checkForCycles(ancestors, references);

		for (final String refMachine : references) {
			if (!imported.containsKey(refMachine)) {
				try {
					loadMachine(ancestors, refMachine);
				} catch (final BException e) {
					// TODO[dp, 22.04.2008]
					throw new BException(machineFile.getName(), e);
				} catch (final IOException e) {
					// TODO[dp, 22.04.2008]
					throw new BException(machineFile.getName(), e);
				}
			}
		}
	}

	private void checkForCycles(final Set<String> ancestors,
			final Set<String> references) {
		final Set<String> cycles = new TreeSet<String>(ancestors);
		intersect(cycles, references);
		if (!cycles.isEmpty())
		// TODO[dp, 22.04.2008] Use sensible exception
			throw new IllegalStateException("cycle detected");
	}

	private void intersect(final Set<String> a, final Set<String> b) {
		for (final Iterator<String> it = a.iterator(); it.hasNext();) {
			final String elem = it.next();
			if (!b.contains(elem)) {
				it.remove();
			}
		}
	}

	private void injectDefinitions(final Start tree,
			final Definitions definitions) {
		final DefInjector defInjector = new DefInjector(definitions);
		tree.apply(defInjector);
	}

	private static class DefInjector extends DepthFirstAdapter {
		private final Definitions definitions;

		public DefInjector(final Definitions definitions) {
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
