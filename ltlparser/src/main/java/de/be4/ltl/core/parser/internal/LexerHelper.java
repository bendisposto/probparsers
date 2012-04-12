/**
 * 
 */
package de.be4.ltl.core.parser.internal;


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

	public LexerHelper(final STATE initialState) {
		this.lastState = initialState;
	}

	public TOKEN filter(STATE newState, TOKEN token) {
		state = newState;
		if (isInAction(state)) {
			if (externalFormula == null) {
				externalFormula = token;
				text = new StringBuilder();
				count = 1;
				token = null;
			} else {
				final String tokenText = readToken(token);
				text.append(tokenText);
				if (isOpening(token)) {
					count++;
				} else if (isClosing(token)) {
					count--;
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
		} else {
			lastState = state;
		}
		return token;
	}

	public STATE getState() {
		return state;
	}

}
