/**
 * 
 */
package de.prob.prolog.match;

import java.util.Map;

import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.PrologTerm;

/**
 * Matches on compound Prolog terms. The found compound term can be directly
 * accessed.
 * 
 * @see CompoundPrologTerm
 * @author plagge
 */
public class PrologTermMatch extends PrologMatch {
	private final String functor;
	private final int arity;
	private final PrologMatch[] args;

	public static PrologTermMatch anonTerm() {
		return namedTerm(null, null, null);
	}

	public static PrologTermMatch anonTerm(String functor, PrologMatch[] args) {
		return namedTerm(null, functor, args);
	}

	public static PrologTermMatch anonTerm(String functor, int arity) {
		return namedTerm(null, functor, arity);
	}

	public static PrologTermMatch anonTerm(String functor) {
		return namedTerm(null, functor, -1);
	}

	public static PrologTermMatch anonAtom(String functor) {
		return namedTerm(null, functor, 0);
	}

	public static PrologTermMatch namedTerm(String name) {
		return namedTerm(name, null, null);
	}

	public static PrologTermMatch namedTerm(String name, String functor,
			PrologMatch[] args) {
		return new PrologTermMatch(name, functor, args);
	}

	public static PrologTermMatch namedTerm(String name, String functor,
			int arity) {
		return new PrologTermMatch(name, functor, arity);
	}

	public static PrologTermMatch namedTerm(String name, String functor) {
		return namedTerm(name, functor, -1);
	}

	public static PrologTermMatch namedAtom(String name, String functor) {
		return namedTerm(name, functor, 0);
	}

	/**
	 * Matches on a term with given functor and given arity
	 * 
	 * @param functor
	 *            the functor, <code>null</code> if it should not be checked
	 * @param arity
	 *            the arity, &lt;0 if it should not be checked
	 */
	private PrologTermMatch(String name, String functor, int arity) {
		super(name);
		this.functor = functor;
		this.arity = arity;
		this.args = null;
	}

	/**
	 * Matches on a term with given functor and arguments
	 * 
	 * @param functor
	 *            the functor, <code>null</code> if it should not be checked
	 * @param args
	 *            the arguments. If <code>null</code>, they remain unchecked. If
	 *            an element of <code>args</code> is <code>null</code>, it
	 *            remains unchecked.
	 */
	private PrologTermMatch(String name, String functor, PrologMatch[] args) {
		super(name);
		this.functor = functor;
		this.arity = args == null ? -1 : args.length;
		this.args = args;
	}

	@Override
	protected boolean isMatch(PrologTerm term, Map<String, PrologTerm> hits) {
		boolean match = false;
			if ((arity < 0 || term.getArity() == arity)
					&& (functor == null || functor.equals(term.getFunctor()))) {
				match = args == null || allArgsMatch(term, hits);
			}
		return match;
	}

	private boolean allArgsMatch(PrologTerm term,
			Map<String, PrologTerm> hits) {
		for (int i = 1; i <= arity; i++) {
			PrologMatch argMatch = args[i];
			if (argMatch != null
					&& !argMatch.matches(term.getArgument(i), hits)) {
				return false;
			}
		}
		return true;
	}
}
