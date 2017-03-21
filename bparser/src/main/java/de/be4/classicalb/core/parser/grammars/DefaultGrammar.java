package de.be4.classicalb.core.parser.grammars;

import de.be4.classicalb.core.parser.node.Token;

public class DefaultGrammar implements IGrammar {

	@Override
	public boolean containsAlternativeDefinitionForToken(Token token) {
		return false;
	}

	@Override
	public Token createNewToken(Token token) {
		throw new RuntimeException("No rewrite rules prepared in default grammar.");
	}

}
