/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.io.PushbackReader;

import de.be4.ltl.core.parser.lexer.Lexer;
import de.be4.ltl.core.parser.node.TActionBegin;
import de.be4.ltl.core.parser.node.TActionEnd;
import de.be4.ltl.core.parser.node.TAtomicPropositionBegin;
import de.be4.ltl.core.parser.node.TAtomicPropositionEnd;
import de.be4.ltl.core.parser.node.Token;

public class LtlLexer extends Lexer {

	private int count;
	private Token externalFormula;
	private StringBuilder text;
	private State lastState = State.LTL;;

	public LtlLexer(final PushbackReader in) {
		super(in);
	}

	@Override
	protected void filter() {
		if (state.equals(State.ATOMIC) || state.equals(State.ACTION)) {
			if (externalFormula == null) {
				externalFormula = token;
				text = new StringBuilder();
				count = 1;
				token = null;
			} else {
				text.append(token.getText());
				if (isOpening(token)) {
					count++;
				} else if (isClosing(token)) {
					count--;
				}
				if (count != 0) {
					token = null;
				} else {
					text.deleteCharAt(text.length() - 1);
					externalFormula.setText(text.toString());
					token = externalFormula;
					state = lastState;
					externalFormula = null;
				}
			}
		} else {
			lastState = state;
		}
	}

	private boolean isOpening(final Token token) {
		return token instanceof TAtomicPropositionBegin
				|| token instanceof TActionBegin;
	}

	private boolean isClosing(final Token token) {
		return token instanceof TAtomicPropositionEnd
				|| token instanceof TActionEnd;
	}
}
