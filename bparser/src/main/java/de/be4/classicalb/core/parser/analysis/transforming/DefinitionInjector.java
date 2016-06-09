package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.LinkedList;

import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
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

public class DefinitionInjector extends DepthFirstAdapter {
	private final IDefinitions definitions;

	public static void injectDefinitions(final Start tree,
			final IDefinitions definitions) {
		final DefinitionInjector defInjector = new DefinitionInjector(
				definitions);
		tree.apply(defInjector);
	}

	private DefinitionInjector(final IDefinitions definitions) {
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
	public void caseAConstantsMachineClause(final AConstantsMachineClause node) {
	}

	@Override
	public void caseAVariablesMachineClause(final AVariablesMachineClause node) {
	}

	@Override
	public void caseAPropertiesMachineClause(final APropertiesMachineClause node) {
	}

	@Override
	public void caseAInvariantMachineClause(final AInvariantMachineClause node) {
	}

	@Override
	public void caseAAssertionsMachineClause(final AAssertionsMachineClause node) {
	}

	@Override
	public void caseAInitialisationMachineClause(
			final AInitialisationMachineClause node) {
	}

	@Override
	public void caseAOperationsMachineClause(final AOperationsMachineClause node) {
	}
}
