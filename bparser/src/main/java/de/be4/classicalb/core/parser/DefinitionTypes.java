package de.be4.classicalb.core.parser;

import java.util.HashMap;
import java.util.Map;

public class DefinitionTypes {

	private final Map<String, Definitions.Type> types = new HashMap<String, Definitions.Type>();

	public DefinitionTypes() {
	}

	public DefinitionTypes(final Map<String, Definitions.Type> newTypes) {
		addAll(newTypes);
	}

	public void addTyping(final String definitionName,
			final Definitions.Type type) {
		types.put(definitionName, type);
	}

	public void addAll(final Map<String, Definitions.Type> newTypes) {
		types.putAll(newTypes);
	}

	public Definitions.Type getType(final String definitionName) {
		if (types.containsKey(definitionName)) {
			return types.get(definitionName);
		} else {
			return Definitions.Type.NoDefinition;
		}
	}
}
