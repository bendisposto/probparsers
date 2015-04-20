/** 
 * (c) 2009-2014 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.io.PushbackReader;

import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.node.TExistsIdentifier;
import de.be4.ltl.core.parser.node.TForallIdentifier;

public class LtlLexer extends Lexer {

	private LtlLexerHelper helper = new LtlLexerHelper();

	private TExistsIdentifier exists_identifier;
	private TForallIdentifier forall_identifier;

	public LtlLexer(final PushbackReader in) {
		super(in);
	}

	@Override
	protected void filter() {
		if (token instanceof TExistsIdentifier) {
			exists_identifier = (TExistsIdentifier) token;
			token = helper.getIdentifier(token, exists_identifier);
		} else if (token instanceof TForallIdentifier) {
			forall_identifier = (TForallIdentifier) token;
			token = helper.getIdentifier(token, forall_identifier);
		} else {
			token = helper.filter(state, token);
			state = helper.getState();
		}
	}
}
