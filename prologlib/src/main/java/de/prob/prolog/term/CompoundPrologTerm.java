/**
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, Heinrich
 * Heine Universitaet Duesseldorf This software is licenced under EPL 1.0
 * (http://www.eclipse.org/org/documents/epl-v10.html)
 * */

/**
 * 
 */
package de.prob.prolog.term;

import java.util.Arrays;

import de.prob.prolog.output.IPrologTermOutput;

/**
 * Represents a prolog term that consists of a functor and an (optional) list of
 * arguments. If no arguments are given, the term is an atom.
 * 
 * @author plagge
 */
public final class CompoundPrologTerm extends PrologTerm {
	private static final long serialVersionUID = 4825557199378803498L;

	public CompoundPrologTerm(final String functor,
			final PrologTerm... arguments) {
		super(functor,arguments);
	}

	public CompoundPrologTerm(final String atom) {
		this(atom, (PrologTerm[]) null);
	}

	@Override
	public boolean isAtom() {
		return arguments == null;
	}

	@Override
	public boolean isTerm() {
		return true;
	}

	@Override
	public void toTermOutput(final IPrologTermOutput pto) {
		pto.openTerm(functor);
		if (arguments != null) {
			for (int i = 0; i < arguments.length; i++) {
				arguments[i].toTermOutput(pto);
			}
		}
		pto.closeTerm();
	}

	@Override
	public boolean equals(final Object other) {
		boolean isEqual;
		if (this == other) {
			isEqual = true;
		} else if (other != null && other instanceof PrologTerm) {
			PrologTerm cother = (PrologTerm) other;
			isEqual = functor.equals(cother.functor)
					&& Arrays.equals(arguments, cother.arguments);
		} else {
			isEqual = false;
		}
		return isEqual;
	}

	@Override
	public int hashCode() {
		return (functor.hashCode() * 17 + Arrays.hashCode(arguments)) * 5 + 4;
	}

	@Override
	public boolean hasFunctor(final String functor, final int arity) {
		return this.functor.equals(functor) && getArity() == arity;
	}

}
