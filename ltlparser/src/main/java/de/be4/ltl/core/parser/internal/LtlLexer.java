/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.io.PushbackReader;

import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.node.EOF;
import de.be4.ltl.core.parser.node.TActionBegin;
import de.be4.ltl.core.parser.node.TActionEnd;
import de.be4.ltl.core.parser.node.TAtomicPropositionBegin;
import de.be4.ltl.core.parser.node.TAtomicPropositionEnd;
import de.be4.ltl.core.parser.node.TExistsIdentifier;
import de.be4.ltl.core.parser.node.TForallIdentifier;
import de.be4.ltl.core.parser.node.Token;

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
			String str = token.getText();
			String identifier = str.substring(1, str.length()-1);
			exists_identifier.setText(identifier);
			token = exists_identifier;
			exists_identifier = null;
		} else if (token instanceof TForallIdentifier) {
			forall_identifier = (TForallIdentifier) token; 
			String str = token.getText();
			String identifier = str.substring(1, str.length()-1);
			forall_identifier.setText(identifier);
			token = forall_identifier;
			forall_identifier = null;
		} else {
		    token = helper.filter(state, token);
		    state = helper.getState();
		}
		
	}

	public static class LtlLexerHelper extends LexerHelper<Token, State> {

		public LtlLexerHelper() {
			super(State.LTL);
		}

		protected boolean isOpening(final Token token) {
			return token instanceof TAtomicPropositionBegin
					|| token instanceof TActionBegin;
		}

		protected boolean isClosing(final Token token) {
			return token instanceof TAtomicPropositionEnd
					|| token instanceof TActionEnd;
		}

		@Override
		protected boolean isInAction(State state) {
			return state.equals(State.ATOMIC) || state.equals(State.ACTION);
		}

		@Override
		protected String readToken(Token token) {
			return token.getText();
		}

		@Override
		protected void writeToken(Token token, String text) {
			token.setText(text);
		}

		@Override
		protected boolean correctBalancedParenthesis(int count, Token token) {
			return !(token instanceof EOF) || count == 0;
		}

	}
}
