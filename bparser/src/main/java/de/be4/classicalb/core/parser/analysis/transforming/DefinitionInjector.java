package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.LinkedList;

import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAbstractMachineParseUnit;
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
	private AAbstractMachineParseUnit abstractMachineParseUnit = null;
	private ADefinitionsMachineClause definitionsMachineClause = null;

	public static void injectDefinitions(final Start start,
			final IDefinitions definitions) {
		new DefinitionInjector(start, definitions);

	}

	private DefinitionInjector(Start start, final IDefinitions definitions) {
		start.apply(this);
		if (definitions.getDefinitionNames().size() == 0)
			return;
		if (definitionsMachineClause == null
				&& abstractMachineParseUnit != null) {
			definitionsMachineClause = new ADefinitionsMachineClause();
			abstractMachineParseUnit.getMachineClauses().add(
					definitionsMachineClause);
		} else if (definitionsMachineClause == null
				&& abstractMachineParseUnit == null) {
			throw new RuntimeException(
					"Only AAbstractMachineParseUnit are supported by the DefinitionsInjector.");
		}
		LinkedList<PDefinition> existingDefintions = definitionsMachineClause
				.getDefinitions();
		for (final String name : definitions.getDefinitionNames()) {
			final PDefinition def = definitions.getDefinition(name);
			existingDefintions.add(def);
		}
	}

	public void inAAbstractMachineParseUnit(AAbstractMachineParseUnit node) {
		abstractMachineParseUnit = node;
	}

	@Override
	public void inADefinitionsMachineClause(final ADefinitionsMachineClause node) {
		definitionsMachineClause = node;
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
