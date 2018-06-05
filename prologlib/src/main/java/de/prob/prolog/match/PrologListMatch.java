package de.prob.prolog.match;

import java.util.Map;

import de.prob.prolog.term.ListPrologTerm;
import de.prob.prolog.term.PrologTerm;

/**
 * Matches on a list with optional given length, provides direct access to the
 * list.
 * 
 * @author plagge
 */
public class PrologListMatch extends PrologMatch {
	private final Integer size;

	public static PrologListMatch anonList(int size) {
		return namedList(null, size);
	}

	public static PrologListMatch anonList() {
		return namedList(null);
	}

	public static PrologListMatch namedList(String name, int size) {
		return new PrologListMatch(name, size);
	}

	public static PrologListMatch namedList(String name) {
		return new PrologListMatch(name);
	}

	private PrologListMatch(final String name, final int size) {
		super(name);
		this.size = size;
	}

	private PrologListMatch(final String name) {
		super(name);
		this.size = -1;
	}

	@Override
	protected boolean isMatch(PrologTerm term, Map<String, PrologTerm> hits) {
		boolean match = term instanceof ListPrologTerm;
		if (match && size != null) {
			match = ((ListPrologTerm) term).size() == size;
		}
		return match;
	}

}
