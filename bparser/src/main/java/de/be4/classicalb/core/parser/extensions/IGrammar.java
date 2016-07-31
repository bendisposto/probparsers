package de.be4.classicalb.core.parser.extensions;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.Token;

public interface IGrammar {

	public boolean containsAlternativeDefinitionForToken(Token token);

	public Token createNewToken(Token token);

	public void applyAstTransformation(Start start, BParser bparser)
			throws CheckException, BException;
}
