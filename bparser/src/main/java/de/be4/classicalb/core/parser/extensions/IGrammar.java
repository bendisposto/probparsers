package de.be4.classicalb.core.parser.extensions;

import de.be4.classicalb.core.parser.node.Token;

public interface IGrammar {

	public boolean containsAlternativeDefinitionForToken(Token token);

	public Token createNewToken(Token token);

}
