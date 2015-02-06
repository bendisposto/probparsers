package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.parser.lexer.Lexer.State;
import de.be4.ltl.core.parser.node.EOF;
import de.be4.ltl.core.parser.node.TActionBegin;
import de.be4.ltl.core.parser.node.TActionEnd;
import de.be4.ltl.core.parser.node.TActionsSplit;
import de.be4.ltl.core.parser.node.TArgsBegin;
import de.be4.ltl.core.parser.node.TArgsEnd;
import de.be4.ltl.core.parser.node.TAtomicPropositionBegin;
import de.be4.ltl.core.parser.node.TAtomicPropositionEnd;
import de.be4.ltl.core.parser.node.TCtrl;
import de.be4.ltl.core.parser.node.TDet;
import de.be4.ltl.core.parser.node.TDlk;
import de.be4.ltl.core.parser.node.TLPar;
import de.be4.ltl.core.parser.node.TRPar;
import de.be4.ltl.core.parser.node.Token;

public class LtlLexerHelper extends LexerHelper<Token, State> {

	public LtlLexerHelper() {
		super(State.LTL);
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
		return token instanceof TArgsBegin || token instanceof TLPar;
	}

	@Override
	protected boolean isClosingActionArg(Token token) {
		return token instanceof TArgsEnd || token instanceof TRPar;
	}

	@Override
	protected boolean isArgumentClosing(Token token) {
		return (token instanceof TActionsSplit);
	}

	@Override
	protected boolean isArgumentSplittingToken(Token token) {
		return token instanceof TActionsSplit;
	}

	@Override
	protected boolean isBeginningActionsToken(Token token) {
		return (token instanceof TDlk) || (token instanceof TDet)
				|| (token instanceof TCtrl);
	}

	@Override
	protected boolean isQuote(Token token) {
		return token.getText().equals("\"");
	}
}