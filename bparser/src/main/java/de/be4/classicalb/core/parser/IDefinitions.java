package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;

public abstract class IDefinitions {
	public enum Type {
		NoDefinition, Expression, Predicate, Substitution, ExprOrSubst
	};

	protected final List<IDefinitions> referencedDefinitions = new ArrayList<IDefinitions>();

	public abstract PDefinition getDefinition(String defName);

	public abstract boolean containsDefinition(String defName);

	public abstract Map<String, Type> getTypes();

	public abstract int getParameterCount(String defName);

	public abstract Type getType(String defName);

	public abstract Set<String> getDefinitionNames();

	public abstract void addDefinition(APredicateDefinitionDefinition defNode, Type type) throws CheckException, BException;

	public abstract void addDefinition(ASubstitutionDefinitionDefinition defNode, Type type) throws CheckException, BException;

	public abstract void addDefinition(AExpressionDefinitionDefinition defNode, Type type) throws CheckException, BException;

	public abstract void addDefinition(PDefinition defNode, Type type, String key) throws CheckException, BException;

	public void addDefinitions(IDefinitions defs) {
		referencedDefinitions.add(defs);
	}

	public abstract void replaceDefinition(final String key, final Type type, final PDefinition node);

	public abstract void assignIdsToNodes(NodeIdAssignment nodeIdMapping, List<File> machineFilesLoaded);

	public abstract void setDefinitionType(String identifierString, Type expression);

}