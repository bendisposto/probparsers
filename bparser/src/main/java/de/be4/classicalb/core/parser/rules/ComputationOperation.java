package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class ComputationOperation extends AbstractOperation {

	private final Map<String, TIdentifierLiteral> defineMap = new HashMap<>();

	public ComputationOperation(TIdentifierLiteral computationName, String fileName, String machineName,
			List<RulesMachineReference> machineReferences) {
		super(computationName, fileName, machineName, machineReferences);
	}

	public void addDefineVariable(TIdentifierLiteral identifierLiteral) throws CheckException {
		String name = identifierLiteral.getText();
		if (defineMap.containsKey(name)) {
			throw new CheckException("Variable '" + name + "' is defined more than once.",
					new Node[] { identifierLiteral, defineMap.get(name) });
		}
		if (readMap.containsKey(name)) {
			throw new CheckException("Variable '" + name + "' read before defined.",
					new Node[] { readMap.get(name), identifierLiteral });
		}
		defineMap.put(name, identifierLiteral);
	}

	public Set<String> getDefineVariables() {
		return new HashSet<>(defineMap.keySet());
	}

	public List<TIdentifierLiteral> getDefineLiterals() {
		return new ArrayList<>(defineMap.values());
	}

}
