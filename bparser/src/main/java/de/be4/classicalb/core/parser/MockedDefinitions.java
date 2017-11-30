package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;

public class MockedDefinitions extends IDefinitions {
	private Map<String, Type> types = new HashMap<>();
	private Map<String, Integer> arity = new HashMap<>();

	public void addMockedDefinition(String name, String type, String parameterCount) {
		if ("predicate".equals(type)) {
			types.put(name, Type.Predicate);
		} else if ("expression".equals(type)) {
			types.put(name, Type.Expression);
		} else if ("substitution".equals(type)) {
			types.put(name, Type.Substitution);
		} else {
			throw new IllegalStateException("Unkown definition type: " + type);
		}
		arity.put(name, new Integer(parameterCount));
	}

	public void addMockedDefinition(String name, Type type, int parameterCount) {
		types.put(name, type);
		arity.put(name, parameterCount);
	}

	@Override
	public PDefinition getDefinition(String defName) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Map<String, Type> getTypes() {
		return types;
	}

	@Override
	public int getParameterCount(String defName) {
		return arity.get(defName);
	}

	@Override
	public Type getType(String defName) {
		Type type = types.get(defName);

		if (type == null)
			return Type.NoDefinition;

		return type;
	}

	@Override
	public Set<String> getDefinitionNames() {
		return types.keySet();
	}

	@Override
	public void addDefinition(APredicateDefinitionDefinition defNode, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDefinition(ASubstitutionDefinitionDefinition defNode, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDefinition(AExpressionDefinitionDefinition defNode, Type type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDefinition(PDefinition defNode, Type type, String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addDefinitions(IDefinitions defs) {
		for (String d : defs.getDefinitionNames()) {
			addMockedDefinition(d, defs.getType(d), defs.getParameterCount(d));
		}
	}

	@Override
	public void assignIdsToNodes(NodeIdAssignment nodeIdMapping, List<File> machineFilesLoaded) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsDefinition(String defName) {
		return false;
	}

	@Override
	public void setDefinitionType(String identifierString, Type expression) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceDefinition(String key, Type type, PDefinition node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public File getFile(String defName) {
		throw new UnsupportedOperationException();
	}

}
