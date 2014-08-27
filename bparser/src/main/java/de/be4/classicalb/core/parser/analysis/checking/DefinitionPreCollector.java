package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.be4.classicalb.core.preparser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.preparser.node.ADefinition;
import de.be4.classicalb.core.preparser.node.AFileDefinition;
import de.be4.classicalb.core.preparser.node.Token;

/**
 * Collects the {@link ADefinition} nodes which were found by the PreParser and
 * stores them into a mapping "definition identifer" -&gt; "rhs of definition".
 * 
 * @author Fabian
 * 
 */
public class DefinitionPreCollector extends DepthFirstAdapter {

	private final Map<Token, Token> definitions = new HashMap<Token, Token>();
	private final List<Token> fileDefinitions = new ArrayList<Token>();

	@Override
	public void inADefinition(final ADefinition node) {
		definitions.put(node.getDefName(), node.getRhs());
	}

	@Override
	public void inAFileDefinition(final AFileDefinition node) {
		fileDefinitions.add(node.getFilename());
	}

	/**
	 * Returns the result of this DFS visitor, i.e. a mapping "definition
	 * identifer" -&gt; "rhs of definition"
	 * 
	 * @return
	 */
	public Map<Token, Token> getDefinitions() {
		return definitions;
	}

	public List<Token> getFileDefinitions() {
		return fileDefinitions;
	}
}
