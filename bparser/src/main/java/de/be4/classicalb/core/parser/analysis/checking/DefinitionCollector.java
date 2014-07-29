package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.DefinitionTypes;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.IDefinitions.Type;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;

/**
 * Collects the {@link APredicateDefinition}, {@link ASubstitutionDefinition}
 * and {@link AExpressionDefinition} nodes, i.e. the declarations which were
 * found by the main parser to store them into an instance of
 * {@link Definitions}.
 * 
 * @author Fabian
 * 
 */
public class DefinitionCollector extends DepthFirstAdapter {

	private final IDefinitions defintions = new Definitions();
	private final DefinitionTypes defTypes;

	public DefinitionCollector(final DefinitionTypes defTypes) {
		this.defTypes = defTypes;
	}

	@Override
	public void inAPredicateDefinitionDefinition(
			final APredicateDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	@Override
	public void inASubstitutionDefinitionDefinition(
			final ASubstitutionDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	@Override
	public void inAExpressionDefinitionDefinition(
			final AExpressionDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	public IDefinitions getDefintions() {
		return defintions;
	}
}
