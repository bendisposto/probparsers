package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.DefinitionTypes;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.Definitions.Type;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AExpressionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinition;

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

	private final Definitions defintions = new Definitions();
	private final DefinitionTypes defTypes;

	public DefinitionCollector(final DefinitionTypes defTypes) {
		this.defTypes = defTypes;
	}

	@Override
	public void inAPredicateDefinition(final APredicateDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	@Override
	public void inASubstitutionDefinition(final ASubstitutionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	@Override
	public void inAExpressionDefinition(final AExpressionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		defintions.addDefinition(node, type);
	}

	public Definitions getDefintions() {
		return defintions;
	}
}
