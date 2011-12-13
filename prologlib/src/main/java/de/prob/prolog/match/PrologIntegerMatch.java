/**
 * 
 */
package de.prob.prolog.match;

import java.math.BigInteger;
import java.util.Map;

import de.prob.prolog.term.IntegerPrologTerm;
import de.prob.prolog.term.PrologTerm;

/**
 * Matches an integer, provides the found integer as BigInt
 * 
 * @author plagge
 */
public class PrologIntegerMatch extends PrologMatch {
	private final BigInteger integer;

	public static PrologIntegerMatch anonInt(final BigInteger integer) {
		return namedInt(null, integer);
	}

	public static PrologIntegerMatch anonInt(final long integer) {
		return namedInt(null, BigInteger.valueOf(integer));
	}

	public static PrologIntegerMatch anonInt() {
		return namedInt(null, null);
	}

	public static PrologIntegerMatch namedInt(final String name,
			final BigInteger integer) {
		return new PrologIntegerMatch(name, integer);
	}

	public static PrologIntegerMatch namedInt(final String name,
			final long integer) {
		return new PrologIntegerMatch(name, BigInteger.valueOf(integer));
	}

	public static PrologIntegerMatch namedInt(final String name) {
		return namedInt(name, null);
	}

	private PrologIntegerMatch(final String name, final BigInteger integer) {
		super(name);
		this.integer = integer;
	}

	@Override
	protected boolean isMatch(final PrologTerm term,
			final Map<String, PrologTerm> hits) {
		boolean match = term instanceof IntegerPrologTerm;
		if (match && integer != null) {
			match = integer.equals(((IntegerPrologTerm) term).getValue());
		}
		return match;
	}
}
