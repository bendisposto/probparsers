package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AConversionDefinition;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;

public class Definitions extends IDefinitions {
	private final Map<String, PDefinition> definitions = new HashMap<String, PDefinition>();
	private final Map<String, Type> types = new HashMap<String, Type>();
	private final Set<File> definitionFiles = new HashSet<File>();

	@Override
	public PDefinition getDefinition(final String defName) {
		return definitions.get(defName);
	}

	@Override
	public Map<String, Type> getTypes() {
		return types;
	}

	@Override
	public int getParameterCount(final String defName) {
		final PDefinition defNode = getDefinition(defName);
		return getParameterCount(defNode);
	}

	private int getParameterCount(PDefinition defNode) {
		if (defNode instanceof APredicateDefinitionDefinition)
			return ((APredicateDefinitionDefinition) defNode).getParameters()
					.size();
		else if (defNode instanceof ASubstitutionDefinitionDefinition)
			return ((ASubstitutionDefinitionDefinition) defNode)
					.getParameters().size();
		else if (defNode instanceof AExpressionDefinitionDefinition)
			return ((AExpressionDefinitionDefinition) defNode).getParameters()
					.size();
		else if (defNode instanceof AConversionDefinition) {
			return getParameterCount(((AConversionDefinition) defNode)
					.getDefinition());
		} else
			return -1;

	}

	@Override
	public Type getType(final String defName) {
		final Type type = types.get(defName);

		if (type == null)
			return Type.NoDefinition;

		return type;
	}

	@Override
	public Set<String> getDefinitionNames() {
		return definitions.keySet();
	}

	@Override
	public void addDefinition(final APredicateDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	@Override
	public void addDefinition(final ASubstitutionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	@Override
	public void addDefinition(final AExpressionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	@Override
	public void addDefinition(final PDefinition defNode, final Type type,
			final String key) {
		definitions.put(key, defNode);
		types.put(key, type);
	}

	@Override
	public void addAll(final IDefinitions defs) {
		for (final String defName : defs.getDefinitionNames()) {
			addDefinition(defs.getDefinition(defName), defs.getType(defName),
					defName);
		}
		definitionFiles.addAll(defs.getDefinitionFiles());
	}

	@Override
	public PDefinition removeDefinition(final String key) {
		types.remove(key);
		return definitions.remove(key);
	}

	@Override
	public void addDefinitionFile(final File defFile) {
		definitionFiles.add(defFile);
	}

	@Override
	public Set<File> getDefinitionFiles() {
		return definitionFiles;
	}
	
	@Override
	public String toString(){
		return definitions.keySet().toString();
	}
}
