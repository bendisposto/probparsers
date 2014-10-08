/**
 * 
 */
package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.parser.node.Token;

/**
 * @author plagge
 * 
 */
abstract class LexerHelper<TOKEN, STATE> {

	private int count;
	private TOKEN externalFormula;
	private StringBuilder text;
	private STATE state, lastState;

	abstract protected String readToken(final TOKEN token);

	abstract protected void writeToken(final TOKEN token, final String text);

	abstract protected boolean isInAction(final STATE state);
	
	abstract protected boolean isOpening(final TOKEN token);

	abstract protected boolean isClosing(final TOKEN token);

	abstract protected boolean correctBalancedParenthesis(int count, TOKEN token);
	
	abstract protected boolean isInActions(final STATE state);

	abstract protected boolean isOpeningActionArg(final TOKEN token);
	
	abstract protected boolean isClosingActionArg(final TOKEN token);

	abstract protected boolean isBeginningActionsToken(final TOKEN token);

	abstract protected boolean isArgumentClosing(final TOKEN token);
	
	abstract protected boolean isArgumentSplittingToken(final TOKEN token);

	public LexerHelper(final STATE initialState) {
		this.lastState = initialState;
	}

	public TOKEN filter(STATE newState, TOKEN token)  {
		state = newState;
		if (isInAction(state)) {
			if (externalFormula == null) {
				initialiseActionToken(token);
				token = null;
			} else {
				final String tokenText = readToken(token);
				text.append(tokenText);
				if (isOpening(token)) {
					count++;
				} else if (isClosing(token)) {
					count--;
				}
			    if (!correctBalancedParenthesis(count, token)) {
				  return token;
			    }
				if (count != 0) {
					token = null;
				} else {
					text.deleteCharAt(text.length() - 1);
					writeToken(externalFormula, text.toString());
					token = externalFormula;
					state = lastState;
					externalFormula = null;
				}
			}
		} else if (isInActions(state)) {
			// ignore the first token in the arguments' list (this is either 'deadlock(' or 'deterministic(')
			if (!isBeginningActionsToken(token)) { 
				if (externalFormula == null) {
					initialiseActionToken(token);
					final String tokenText = readToken(token);
					text.append(tokenText);
					token=null;
				} else {
					if (isOpeningActionArg(token)) {
						count++;
					} else if (isClosingActionArg(token)) {
						count--;
					}
					if (!correctBalancedParenthesis(count, token)) {
						return token;
					}
					if ((count == 1 && !isArgumentClosing(token)) || count>1) {
						final String tokenText = readToken(token);
						text.append(tokenText);
					}
					if (count == 1 && isArgumentSplittingToken(token)) {
						token = updateTokenText();
					} else if (count==0) {
						token = updateTokenText();
						state = lastState;
					} else {
						token = null;
					}
				}
			}
		} else {
			lastState = state;
		}
		return token;
	}

	public void initialiseActionToken(TOKEN token) {
		this.externalFormula = token;
		this.text = new StringBuilder();
		this.count = 1;
	}
	
	public TOKEN updateTokenText() {
		writeToken(this.externalFormula, this.text.toString().trim());
		TOKEN tok = externalFormula;
		this.externalFormula = null;
		return tok;
	}
	
	public TOKEN getIdentifier(TOKEN token, TOKEN ident) {
		String str = ((Token) token).getText();
		String identifier = str.substring(1, str.length()-1).trim();
		((Token) ident).setText(identifier);
		token = ident;
		ident = null;
		return token;
	}
	
	public STATE getState() {
		return state;
	}

}
