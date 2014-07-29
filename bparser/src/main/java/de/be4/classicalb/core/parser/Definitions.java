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

public class Definitions extends IDefinitions {
	private final Map<String, PDefinition> definitions = new HashMap<String, PDefinition>();
	private final Map<String, Type> types = new HashMap<String, Type>();
	private final Set<File> definitionFiles = new HashSet<File>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#getDefinition(java.lang.String
	 * )
	 */
	public PDefinition getDefinition(final String defName) {
		return definitions.get(defName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.be4.classicalb.core.parser.IDefinitions#getTypes()
	 */
	public Map<String, Type> getTypes() {
		return types;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#getParameterCount(java.lang
	 * .String)
	 */
	public int getParameterCount(final String defName) {
		final Node defNode = getDefinition(defName);

		if (defNode instanceof APredicateDefinitionDefinition)
			return ((APredicateDefinitionDefinition) defNode).getParameters()
					.size();
		else if (defNode instanceof ASubstitutionDefinitionDefinition)
			return ((ASubstitutionDefinitionDefinition) defNode)
					.getParameters().size();
		else if (defNode instanceof AExpressionDefinitionDefinition)
			return ((AExpressionDefinitionDefinition) defNode).getParameters()
					.size();
		else
			return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.be4.classicalb.core.parser.IDefinitions#getType(java.lang.String)
	 */
	public Type getType(final String defName) {
		final Type type = types.get(defName);

		if (type == null)
			return Type.NoDefinition;

		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.be4.classicalb.core.parser.IDefinitions#getDefinitionNames()
	 */
	public Set<String> getDefinitionNames() {
		return definitions.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addDefinition(de.be4.classicalb
	 * .core.parser.node.APredicateDefinitionDefinition,
	 * de.be4.classicalb.core.parser.Definitions.Type)
	 */
	public void addDefinition(final APredicateDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addDefinition(de.be4.classicalb
	 * .core.parser.node.ASubstitutionDefinitionDefinition,
	 * de.be4.classicalb.core.parser.Definitions.Type)
	 */
	public void addDefinition(final ASubstitutionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addDefinition(de.be4.classicalb
	 * .core.parser.node.AExpressionDefinitionDefinition,
	 * de.be4.classicalb.core.parser.Definitions.Type)
	 */
	public void addDefinition(final AExpressionDefinitionDefinition defNode,
			final Type type) {
		addDefinition(defNode, type, defNode.getName().getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addDefinition(de.be4.classicalb
	 * .core.parser.node.PDefinition,
	 * de.be4.classicalb.core.parser.Definitions.Type, java.lang.String)
	 */
	public void addDefinition(final PDefinition defNode, final Type type,
			final String key) {
		definitions.put(key, defNode);
		types.put(key, type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addAll(de.be4.classicalb.core
	 * .parser.IDefinitions)
	 */
	public void addAll(final IDefinitions defs) {
		for (final String defName : defs.getDefinitionNames()) {
			addDefinition(defs.getDefinition(defName), defs.getType(defName),
					defName);
		}
		definitionFiles.addAll(defs.getDefinitionFiles());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#removeDefinition(java.lang
	 * .String)
	 */
	public PDefinition removeDefinition(final String key) {
		types.remove(key);
		return definitions.remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.be4.classicalb.core.parser.IDefinitions#addDefinitionFile(java.io.
	 * File)
	 */
	public void addDefinitionFile(final File defFile) {
		definitionFiles.add(defFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.be4.classicalb.core.parser.IDefinitions#getDefinitionFiles()
	 */
	public Set<File> getDefinitionFiles() {
		return definitionFiles;
	}
}
