package de.prob.prolog.match;

import java.util.HashMap;
import java.util.Map;

import de.prob.prolog.term.PrologTerm;

/**
 * This class and its subclasses are used to do pattern-matching on Prolog
 * terms. It is a kind of unification.
 * 
 * This base class matches every {@link PrologTerm}.
 * 
 * @author plagge
 */
public class PrologMatch {
	private final String name;

	public PrologMatch anon() {
		return named(null);
	}

	public PrologMatch named(final String name) {
		return new PrologMatch(name);
	}

	protected PrologMatch(final String name) {
		this.name = name;
	}

	public final boolean matches(final PrologTerm term,
			final Map<String, PrologTerm> hits) {
		boolean isMatch = isMatch(term, hits);
		if (isMatch && hits != null && name != null) {
			hits.put(name, term);
		}
		return isMatch;
	}

	protected boolean isMatch(final PrologTerm term,
			final Map<String, PrologTerm> hits) {
		return true;
	}

	public final Map<String, PrologTerm> getMatches(final PrologTerm term) {
		Map<String, PrologTerm> hits = new HashMap<String, PrologTerm>();
		boolean matches = matches(term, hits);
		return matches ? hits : null;
	}

	public final boolean matches(final PrologTerm term) {
		return matches(term, null);
	}
}
