package de.be4.classicalb.core.parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.be4.classicalb.core.parser.node.AExpressionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.APredicateDefinitionDefinition;
import de.be4.classicalb.core.parser.node.ASubstitutionDefinitionDefinition;
import de.be4.classicalb.core.parser.node.PDefinition;

public class MockedDefinitions extends IDefinitions {
	public Map<String, Type> types = new HashMap<String, Type>();
	public Map<String, Integer> arity = new HashMap<String, Integer>();

	public void addMockedDefinition(String name, String type,
			String parameterCount) {
		if ("predicate".equals(type)) {
			types.put(name, Type.Predicate);
		}
		if ("expressiom".equals(type)) {
			types.put(name, Type.Expression);
		}
		arity.put(name, new Integer(parameterCount));
	}

	public void addMockedDefinition(String name, Type type, int parameterCount) {
		types.put(name, type);
		arity.put(name, parameterCount);
	}

	@Override
	public PDefinition getDefinition(String defName) {
		throw new NotImplementedException();

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
		return types.get(defName);
	}

	@Override
	public Set<String> getDefinitionNames() {
		return types.keySet();
	}

	@Override
	public void addDefinition(APredicateDefinitionDefinition defNode, Type type) {
		throw new NotImplementedException();
	}

	@Override
	public void addDefinition(ASubstitutionDefinitionDefinition defNode,
			Type type) {
		throw new NotImplementedException();
	}

	@Override
	public void addDefinition(AExpressionDefinitionDefinition defNode, Type type) {
		throw new NotImplementedException();
	}

	@Override
	public void addDefinition(PDefinition defNode, Type type, String key) {
		throw new NotImplementedException();
	}

	@Override
	public void addAll(IDefinitions defs) {
		for (String d : defs.getDefinitionNames()) {
			addMockedDefinition(d, defs.getType(d), defs.getParameterCount(d));
		}
	}

	@Override
	public PDefinition removeDefinition(String key) {
		throw new NotImplementedException();
	}

	@Override
	public void addDefinitionFile(File defFile) {
		throw new NotImplementedException();
	}

	@Override
	public Set<File> getDefinitionFiles() {
		throw new NotImplementedException();
	}

}
