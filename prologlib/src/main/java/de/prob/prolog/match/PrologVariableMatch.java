/**
 * 
 */
package de.prob.prolog.match;

import java.util.Map;

import de.prob.prolog.term.PrologTerm;
import de.prob.prolog.term.VariablePrologTerm;

/**
 * Matches a Prolog variable.
 * 
 * @author plagge
 */
public class PrologVariableMatch extends PrologMatch {
	private final String varName;

	public static PrologVariableMatch anonVar(String varName) {
		return namedVar(null, varName);
	}

	public static PrologVariableMatch anonVar() {
		return namedVar(null, null);
	}

	public static PrologVariableMatch namedVar(String name, String varName) {
		return new PrologVariableMatch(name, varName);
	}

	public static PrologVariableMatch namedVar(String name) {
		return namedVar(name, null);
	}

	/**
	 * Matches on a Prolog variable with the given name
	 * 
	 * @param name
	 *            the name, if <code>null</code> it will not be checked
	 */
	private PrologVariableMatch(final String name, final String varName) {
		super(name);
		this.varName = varName;
	}

	@Override
	protected boolean isMatch(PrologTerm term, Map<String, PrologTerm> hits) {
		boolean match = term instanceof VariablePrologTerm;
		if (match && varName != null) {
			match = varName.equals(((VariablePrologTerm) term).getName());
		}
		return match;
	}
}
