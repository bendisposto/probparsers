/** 
 * (c) 2009-2014 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.io.PushbackReader;

import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.node.EOF;
import de.be4.ltl.core.parser.node.TActionBegin;
import de.be4.ltl.core.parser.node.TActionEnd;
import de.be4.ltl.core.parser.node.TActionsSplit;
import de.be4.ltl.core.parser.node.TArgsBegin;
import de.be4.ltl.core.parser.node.TArgsEnd;
import de.be4.ltl.core.parser.node.TAtomicPropositionBegin;
import de.be4.ltl.core.parser.node.TAtomicPropositionEnd;
import de.be4.ltl.core.parser.node.TDet;
import de.be4.ltl.core.parser.node.TDlk;
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
			token = helper.getIdentifier(token,exists_identifier);
		} else if (token instanceof TForallIdentifier) {
			forall_identifier = (TForallIdentifier) token;
			token = helper.getIdentifier(token,forall_identifier);
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
		protected boolean isInActions(State state) {
			return state.equals(State.ACTIONS);
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

		@Override
		protected boolean isOpeningActionArg(Token token) {
			return token instanceof TArgsBegin;
		}

		@Override
		protected boolean isClosingActionArg(Token token) {
			return token instanceof TArgsEnd;
		}

		@Override
		protected boolean isArgumentClosing(Token token) {
			return (token instanceof TActionsSplit) || (token instanceof TArgsEnd);
		}
		
		@Override
		protected boolean isArgumentSplittingToken(Token token) {
			return token instanceof TActionsSplit;
		}
		
		@Override
		protected boolean isBeginningActionsToken(Token token) {
			return (token instanceof TDlk) || (token instanceof TDet);
		}
	}
}
