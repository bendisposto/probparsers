package de.prob.prolog.output;

import java.math.BigInteger;

import de.prob.prolog.term.PrologTerm;

/**
 * @author plagge
 * 
 */
public class PrologTermDelegate implements IPrologTermOutput {
	protected final IPrologTermOutput pto;

	public PrologTermDelegate(final IPrologTermOutput pto) {
		this.pto = pto;
	}

	public IPrologTermOutput closeList() {
		pto.closeList();
		return this;
	}

	public IPrologTermOutput closeTerm() {
		pto.closeTerm();
		return this;
	}

	public IPrologTermOutput emptyList() {
		pto.emptyList();
		return this;
	}

	public IPrologTermOutput flush() {
		pto.flush();
		return this;
	}

	public IPrologTermOutput fullstop() {
		pto.fullstop();
		return this;
	}

	public IPrologTermOutput openList() {
		pto.openList();
		return this;
	}

	public IPrologTermOutput openTerm(final String functor) {
		pto.openTerm(functor);
		return this;
	}

	public IPrologTermOutput openTerm(final String functor,
			final boolean ignoreIndention) {
		pto.openTerm(functor, ignoreIndention);
		return this;
	}

	public IPrologTermOutput printAtom(final String content) {
		pto.printAtom(content);
		return this;
	}

	public IPrologTermOutput printAtomOrNumber(final String content) {
		pto.printAtomOrNumber(content);
		return this;
	}

	public IPrologTermOutput printNumber(final long number) {
		pto.printNumber(number);
		return this;
	}

	public IPrologTermOutput printNumber(final BigInteger number) {
		pto.printNumber(number);
		return this;
	}

	public IPrologTermOutput printString(final String content) {
		pto.printString(content);
		return this;
	}

	public IPrologTermOutput printVariable(final String var) {
		pto.printVariable(var);
		return this;
	}

	public IPrologTermOutput printTerm(final PrologTerm term) {
		pto.printTerm(term);
		return this;
	}

}
