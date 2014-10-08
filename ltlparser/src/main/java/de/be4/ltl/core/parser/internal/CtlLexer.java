/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.io.PushbackReader;

import de.be4.ltl.core.ctlparser.lexer.Lexer;
import de.be4.ltl.core.ctlparser.node.EOF;
import de.be4.ltl.core.ctlparser.node.TActionBegin;
import de.be4.ltl.core.ctlparser.node.TActionEnd;
import de.be4.ltl.core.ctlparser.node.TAtomicPropositionBegin;
import de.be4.ltl.core.ctlparser.node.TAtomicPropositionEnd;
import de.be4.ltl.core.ctlparser.node.Token;

public class CtlLexer extends Lexer {

	private CtlLexerHelper helper = new CtlLexerHelper();

	public CtlLexer(final PushbackReader in) {
		super(in);
	}

	@Override
	protected void filter() {
		token = helper.filter(state, token);
		state = helper.getState();
	}

	public static class CtlLexerHelper extends LexerHelper<Token, State> {

		public CtlLexerHelper() {
			super(State.CTL);
		}

		@Override
		protected boolean isOpening(final Token token) {
			return token instanceof TAtomicPropositionBegin
					|| token instanceof TActionBegin;
		}

		@Override
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

		@Override
		protected boolean isOpeningActionArg(Token token) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean isClosingActionArg(Token token) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean isInActions(State state) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean isBeginningActionsToken(Token token) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean isArgumentClosing(Token token) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean isArgumentSplittingToken(Token token) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
