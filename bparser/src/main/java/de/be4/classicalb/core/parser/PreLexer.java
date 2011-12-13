package de.be4.classicalb.core.parser;

import java.io.IOException;
import java.io.PushbackReader;

import de.be4.classicalb.core.preparser.lexer.Lexer;
import de.be4.classicalb.core.preparser.lexer.LexerException;
import de.be4.classicalb.core.preparser.node.EOF;
import de.be4.classicalb.core.preparser.node.TBeginNesting;
import de.be4.classicalb.core.preparser.node.TComment;
import de.be4.classicalb.core.preparser.node.TCommentContent;
import de.be4.classicalb.core.preparser.node.TCommentEnd;
import de.be4.classicalb.core.preparser.node.TEndNesting;
import de.be4.classicalb.core.preparser.node.TLeftPar;
import de.be4.classicalb.core.preparser.node.TOtherClauseBegin;
import de.be4.classicalb.core.preparser.node.TRhsBody;
import de.be4.classicalb.core.preparser.node.TRightPar;
import de.be4.classicalb.core.preparser.node.TSemicolon;
import de.be4.classicalb.core.preparser.node.TWhiteSpace;
import de.be4.classicalb.core.preparser.node.Token;

public class PreLexer extends Lexer {

	private TRhsBody rhsToken = null;
	private StringBuilder rhsBuffer = null;
	private int otherNestingLevel = 0;
	private int parenNestingLevel = 0;

	private State stateBeforeComment;
	private boolean debugOutput = false;

	public PreLexer(final PushbackReader in) {
		super(in);
	}

	@Override
	protected void filter() throws LexerException, IOException {
		Token filt = this.token;
		checkComment();

		if (token != null) {
			collectRhs();
		}

		if (token != null && debugOutput && !(token instanceof TWhiteSpace)
				&& !(token instanceof EOF)) {
			System.out.print(token.getClass().getSimpleName() + "('"
					+ token.getText() + "') ");
		}
	}

	private void collectRhs() throws LexerException, IOException {
		if (state.equals(State.DEFINITIONS_RHS)) {
			if (rhsToken == null) {
				// starting a new definition rhs
				rhsToken = new TRhsBody("", -1, -1);
				rhsBuffer = new StringBuilder();
			} else {
				final State nextState = getNextState();

				// end of rhs reached?
				if (nextState != null) {
					// push current token back into reader
					unread(token);

					// prepare rhs_body token to be the current one
					((Token) rhsToken).setText(rhsBuffer.toString().trim());
					token = rhsToken;
					rhsToken = null;
					rhsBuffer = null;
					state = nextState;
				} else {
					// first token after "==" sets start position
					if (rhsToken.getLine() == -1) {
						rhsToken.setLine(token.getLine());
						rhsToken.setPos(token.getPos());
					}

					rhsBuffer.append(token.getText());
					token = null;
				}
			}
		}
	}

	private State getNextState() {
		if (token instanceof TOtherClauseBegin || token instanceof EOF) {
			return State.NORMAL;
		}

		// check for parenthesis first
		if (token instanceof TLeftPar) {
			parenNestingLevel++;
		} else if (token instanceof TRightPar) {
			parenNestingLevel--;
		}
		// then check other tokens which start/end nestings
		else {
			otherNestingLevel += changeNesting();
		}

		if (otherNestingLevel == 0 && parenNestingLevel == 0
				&& token instanceof TSemicolon) {
			return State.DEFINITIONS;
		}

		if (otherNestingLevel < 0) {
			otherNestingLevel = 0;
			return State.NORMAL;
		}

		return null;
	}

	private int changeNesting() {
		// default if no nesting is started/ended
		int result = 0;

		// is the token starting a nesting?
		if (token instanceof TBeginNesting) {
			result++;
		}

		// is the token ending a nesting?
		if (token instanceof TEndNesting) {
			result--;
		}

		/*
		 * A token can start and end a nesting at the same time. Example
		 * "(PP=TRUE)". So both calls above are needed.
		 */
		return result;
	}

	private void checkComment() {
		if (token instanceof TComment) {
			stateBeforeComment = state;
			state = State.COMMENT;
			token = null;
		} else if (token instanceof TCommentContent) {
			token = null;
		} else if (token instanceof TCommentEnd) {
			state = stateBeforeComment;
			stateBeforeComment = null;
			token = null;
		}
	}

	public void setDebugOutput(final boolean debugOutput) {
		this.debugOutput = debugOutput;
	}
}
