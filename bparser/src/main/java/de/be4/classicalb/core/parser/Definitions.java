package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.PDefinition;

public class Definitions {
	public enum Type {
		NoDefinition, Expression, Predicate, Substitution, ExprOrSubst
	};

	private final Map<String, PDefinition> definitions = new HashMap<String, PDefinition>();
	private final Map<String, Type> types = new HashMap<String, Type>();
	private final Set<File> definitionFiles = new HashSet<File>();

	public PDefinition getDefinition(final String defName) {
		return definitions.get(defName);
	}

	public Map<String, Type> getTypes() {
		return types;
	}

	public int getParameterCount(final String defName) {
		final Node defNode = getDefinition(defName);

		if (defNode instanceof APredicateDefinitionDefinition)
			return ((APredicateDefinitionDefinition) defNode).getParameters().size();
		else if (defNode instanceof ASubstitutionDefinitionDefinition)
			return ((ASubstitutionDefinitionDefinition) defNode).getParameters().size();
		else if (defNode instanceof AExpressionDefinitionDefinition)
			return ((AExpressionDefinitionDefinition) defNode).getParameters().size();
		else
			return -1;
	}

	public Type getType(final String defName) {
		final Type type = types.get(defName);

		if (type == null)
			return Type.NoDefinition;

		return type;
	}

	public Set<String> getDefinitionNames() {
		return definitions.keySet();
	}

	public void addDefinition(final APredicateDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	public void addDefinition(final ASubstitutionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	public void addDefinition(final AExpressionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	public void addDefinition(final PDefinition defNode, final Type type,
			final String key) {
		definitions.put(key, defNode);
		types.put(key, type);
	}

	public void addAll(final Definitions defs) {
		for (final String defName : defs.getDefinitionNames()) {
			addDefinition(defs.getDefinition(defName), defs.getType(defName),
					defName);
		}
		definitionFiles.addAll(defs.getDefinitionFiles());
	}

	public PDefinition removeDefinition(final String key) {
		types.remove(key);
		return definitions.remove(key);
	}

	public void addDefinitionFile(final File defFile) {
		definitionFiles.add(defFile);
	}

	public Set<File> getDefinitionFiles() {
		return definitionFiles;
	}
}
