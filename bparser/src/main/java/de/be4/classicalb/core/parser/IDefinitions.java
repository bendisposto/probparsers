package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;

public abstract class IDefinitions {
	public enum Type {
		NoDefinition, Expression, Predicate, Substitution, ExprOrSubst
	};

	public abstract PDefinition getDefinition(String defName);

	public abstract Map<String, Type> getTypes();

	public abstract int getParameterCount(String defName);

	public abstract Type getType(String defName);

	public abstract Set<String> getDefinitionNames();

	public abstract void addDefinition(APredicateDefinitionDefinition defNode,
			Type type);

	public abstract void addDefinition(
			ASubstitutionDefinitionDefinition defNode, Type type);

	public abstract void addDefinition(AExpressionDefinitionDefinition defNode,
			Type type);

	public abstract void addDefinition(PDefinition defNode, Type type,
			String key);

	public abstract void addAll(IDefinitions defs);

	public abstract PDefinition removeDefinition(String key);

	public abstract void addDefinitionFile(File defFile);

	public abstract Set<File> getDefinitionFiles();

}