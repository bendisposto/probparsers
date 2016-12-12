package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.DefinitionTypes;
import de.be4.classicalb.core.parser.Definitions;
import de.be4.classicalb.core.parser.IDefinitions;
import de.be4.classicalb.core.parser.IDefinitions.Type;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AConversionDefinition;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;
import de.be4.classicalb.core.parser.node.Start;

/**
 * Collects the
 * {@link de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition},
 * {@link de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition}
 * and
 * {@link de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition}
 * nodes, i.e. the declarations which were found by the main parser to store
 * them into an instance of {@link Definitions}.
 * 
 * @author Fabian
 * 
 */
public class DefinitionCollector extends DepthFirstAdapter {

	private final IDefinitions definitions;
	private final DefinitionTypes defTypes;
	private List<Exception> exceptions = new ArrayList<>();

	public DefinitionCollector(final DefinitionTypes defTypes, IDefinitions definitions) {
		this.defTypes = defTypes;
		this.definitions = definitions;
	}

	public List<Exception> collectDefinitions(Start rootNode) {
		rootNode.apply(this);
		return this.exceptions;
	}

	@Override
	public void inAPredicateDefinitionDefinition(final APredicateDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		try {
			definitions.addDefinition(node, type);
		} catch (CheckException | BException e) {
			this.exceptions.add(e);
		}
	}

	@Override
	public void inASubstitutionDefinitionDefinition(final ASubstitutionDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		try {
			definitions.addDefinition(node, type);
		} catch (CheckException | BException e) {
			this.exceptions.add(e);
		}
	}

	@Override
	public void inAExpressionDefinitionDefinition(final AExpressionDefinitionDefinition node) {
		final String defName = node.getName().getText();
		final Type type = defTypes.getType(defName);
		try {
			definitions.addDefinition(node, type);
		} catch (CheckException | BException e) {
			this.exceptions.add(e);
		}
	}

	@Override
	public void caseAConversionDefinition(AConversionDefinition node) {
		PDefinition def = node.getDefinition();
		if (def instanceof AExpressionDefinitionDefinition) {
			AExpressionDefinitionDefinition exprDef = (AExpressionDefinitionDefinition) def;
			final String defName = exprDef.getName().getText();
			final Type type = defTypes.getType(defName);
			try {
				definitions.addDefinition(node, type, defName);
			} catch (CheckException | BException e) {
				this.exceptions.add(e);
			}
		} else {
			this.exceptions.add(new CheckException(
					"Only an expression is allowed on the right hand side of a conversion definition.", node));
		}
	}

	public IDefinitions getDefinitions() {
		return definitions;
	}

}
