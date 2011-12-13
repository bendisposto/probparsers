/**
 * 
 */
package de.prob.prolog.output;

import java.math.BigInteger;

import de.prob.prolog.term.PrologTerm;

/**
 * An implementation of {@link IPrologTermOutput} that does nothing.
 * 
 * @author plagge
 */
public class DummyPrologOutput implements IPrologTermOutput {
	public static final DummyPrologOutput DUMMY = new DummyPrologOutput();

	private DummyPrologOutput() {
	}

	public IPrologTermOutput closeList() {
		return this;
	}

	public IPrologTermOutput closeTerm() {
		return this;
	}

	public IPrologTermOutput emptyList() {
		return this;
	}

	public IPrologTermOutput flush() {
		return this;
	}

	public IPrologTermOutput fullstop() {
		return this;
	}

	public IPrologTermOutput openList() {
		return this;
	}

	public IPrologTermOutput openTerm(final String arg0) {
		return this;
	}

	public IPrologTermOutput openTerm(final String arg0, final boolean arg1) {
		return this;
	}

	public IPrologTermOutput printAtom(final String arg0) {
		return this;
	}

	public IPrologTermOutput printAtomOrNumber(final String arg0) {
		return this;
	}

	public IPrologTermOutput printNumber(final long arg0) {
		return this;
	}

	public IPrologTermOutput printNumber(final BigInteger arg0) {
		return this;
	}

	public IPrologTermOutput printString(final String arg0) {
		return this;
	}

	public IPrologTermOutput printVariable(final String arg0) {
		return this;
	}

	public IPrologTermOutput printTerm(final PrologTerm term) {
		return this;
	}

}
