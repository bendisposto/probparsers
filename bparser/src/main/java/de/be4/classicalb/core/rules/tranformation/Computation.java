package de.be4.classicalb.core.rules.tranformation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public class Computation extends AbstractOperation {

	private Map<String, TIdentifierLiteral> defineMap = new HashMap<>();
	private Map<String, AIdentifierExpression> readMap = new HashMap<>();

	public Computation(TIdentifierLiteral computationName) {
		super(computationName);
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

	public void addReadVariable(AIdentifierExpression identifier) {
		LinkedList<TIdentifierLiteral> list = identifier.getIdentifier();
		String name = list.get(0).getText();
		readMap.put(name, identifier);
	}
}
