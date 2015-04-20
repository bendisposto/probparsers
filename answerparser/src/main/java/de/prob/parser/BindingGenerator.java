/**
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, Heinrich
 * Heine Universitaet Duesseldorf This software is licenced under EPL 1.0
 * (http://www.eclipse.org/org/documents/epl-v10.html)
 * */

/**
 * 
 */
package de.prob.parser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.prob.core.sablecc.node.Start;
import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.IntegerPrologTerm;
import de.prob.prolog.term.ListPrologTerm;
import de.prob.prolog.term.PrologTerm;
import de.prob.prolog.term.VariablePrologTerm;

/**
 * Takes a Prolog term of the form (only its canonical form) "[x=a,y=b,z=c]" and
 * converts it into a mapping x-&gt;a, y-&gt;b, z-&gt;c, where x,y and z are
 * identifiers and a,b,c prolog terms.
 * 
 * @author plagge
 */
public class BindingGenerator {

	public static Map<String, PrologTerm> createBinding(final Start ast)
			throws ResultParserException {
		PrologTerm term = PrologTermGenerator.toPrologTerm(ast);
		return createBinding(term);
	}

	public static Map<String, PrologTerm> createBindingMustNotFail(
			final String query, final Start ast) throws ResultParserException {
		PrologTerm term = PrologTermGenerator.toPrologTermMustNotFail(query,
				ast);
		return createBinding(term);
	}

	private static Map<String, PrologTerm> createBinding(final PrologTerm term) {
		Map<String, PrologTerm> result;
		if (term == null) {
			result = null;
		} else if (term.isList()) {
			ListPrologTerm list = (ListPrologTerm) term;
			result = createBinding(list);
		} else
			throw new IllegalArgumentException("Expected list");
		return result;
	}

	private static Map<String, PrologTerm> createBinding(
			final ListPrologTerm list) {
		Map<String, PrologTerm> result;
		result = new HashMap<String, PrologTerm>();
		for (int i = 0; i < list.size(); i++) {
			PrologTerm element = list.get(i);
			if (element.isTerm()) {
				CompoundPrologTerm binding = (CompoundPrologTerm) element;
				if (binding.getArity() == 2 && "=".equals(binding.getFunctor())) {
					extractBinding(result, binding);
				} else
					throw new IllegalArgumentException(
							"Expected binding (=/2), but was "
									+ binding.getFunctor() + "/"
									+ String.valueOf(binding.getArity()));
			} else
				throw new IllegalArgumentException(
						"Expected binding but was not a term");
		}
		return Collections.unmodifiableMap(result);
	}

	private static void extractBinding(final Map<String, PrologTerm> result,
			final CompoundPrologTerm binding) {
		PrologTerm varterm = binding.getArgument(1);
		if (varterm.isAtom()) {
			String name = ((CompoundPrologTerm) varterm).getFunctor();
			PrologTerm value = binding.getArgument(2);
			result.put(name, value);
		} else
			throw new IllegalArgumentException(
					"Expected atomic variable name, but found "
							+ varterm.toString());
	}

	public static CompoundPrologTerm getCompoundTerm(final PrologTerm term,
			final String functor, final int arity) throws ResultParserException {
		return checkSignature(checkComponentType(term), functor, arity);
	}

	private static CompoundPrologTerm checkComponentType(final PrologTerm term)
			throws ResultParserException {
		if (!(term instanceof CompoundPrologTerm)) {
			final String message = "Expected CompoundPrologTerm, but got "
					+ term.getClass().getSimpleName();
			throw new ResultParserException(message, null);
		}
		return (CompoundPrologTerm) term;
	}

	public static CompoundPrologTerm getCompoundTerm(final PrologTerm term,
			final int arity) throws ResultParserException {
		if ((term instanceof CompoundPrologTerm))
			return checkArity((CompoundPrologTerm) term, arity);
		final String message = "Expected CompoundPrologTerm, but got "
				+ term.getClass().getSimpleName();
		throw new ResultParserException(message, null);
	}

	public static CompoundPrologTerm getCompoundTerm(
			final Map<String, PrologTerm> bindings, final String name,
			final String functor, final int arity) throws ResultParserException {
		final PrologTerm prologTerm = getFromBindings(bindings, name);
		return getCompoundTerm(prologTerm, functor, arity);
	}

	public static CompoundPrologTerm getCompoundTerm(
			final Map<String, PrologTerm> bindings, final String name,
			final int arity) throws ResultParserException {
		final PrologTerm prologTerm = getFromBindings(bindings, name);
		return getCompoundTerm(prologTerm, arity);
	}

	private static CompoundPrologTerm checkSignature(
			final CompoundPrologTerm term, final String functor, final int arity)
			throws ResultParserException {
		checkArity(term, arity);
		checkFunctor(term, functor);
		return term;
	}

	private static CompoundPrologTerm checkFunctor(
			final CompoundPrologTerm term, final String functor)
			throws ResultParserException {
		if (!term.getFunctor().equals(functor)) {
			final String message = "Expected " + term + " to have functor "
					+ functor + ", but got " + term.getFunctor();
			throw new ResultParserException(message, null);
		}
		return term;
	}

	private static CompoundPrologTerm checkArity(final CompoundPrologTerm term,
			final int arity) throws ResultParserException {
		if (term.getArity() != arity) {
			final String message = "Expected " + term + " to have an arity "
					+ arity + ", but got " + term.getArity();
			throw new ResultParserException(message, null);
		}
		return term;
	}

	public static ListPrologTerm getList(final PrologTerm term)
			throws ResultParserException {
		if (term instanceof ListPrologTerm)
			return (ListPrologTerm) term;
		final String message = "Expected ListPrologTerm, but got "
				+ term.getClass().getSimpleName();
		throw new ResultParserException(message, null);
	}

	public static ListPrologTerm getList(
			final Map<String, PrologTerm> bindings, final String name)
			throws ResultParserException {
		PrologTerm prologTerm = getFromBindings(bindings, name);
		return getList(prologTerm);
	}

	public static ListPrologTerm getList(
			final ISimplifiedROMap<String, PrologTerm> bindings,
			final String name) throws ResultParserException {
		PrologTerm prologTerm = getFromBindings(bindings, name);
		return getList(prologTerm);
	}

	public static IntegerPrologTerm getInteger(final PrologTerm term)
			throws ResultParserException {
		if (term instanceof IntegerPrologTerm)
			return (IntegerPrologTerm) term;
		final String message = "Expected ListPrologTerm, but got "
				+ term.getClass().getSimpleName();
		throw new ResultParserException(message, null);
	}

	public static IntegerPrologTerm getInteger(
			final Map<String, PrologTerm> bindings, final String name)
			throws ResultParserException {
		PrologTerm prologTerm = getFromBindings(bindings, name);
		return getInteger(prologTerm);
	}

	public static VariablePrologTerm getVariable(final PrologTerm term)
			throws ResultParserException {
		if (term instanceof VariablePrologTerm)
			return (VariablePrologTerm) term;
		final String message = "Expected ListPrologTerm, but got "
				+ term.getClass().getSimpleName();
		throw new ResultParserException(message, null);
	}

	public static VariablePrologTerm getVariable(
			final Map<String, PrologTerm> bindings, final String name)
			throws ResultParserException {
		PrologTerm prologTerm = getFromBindings(bindings, name);
		return getVariable(prologTerm);
	}

	private static PrologTerm getFromBindings(
			final Map<String, PrologTerm> bindings, final String name)
			throws ResultParserException {
		PrologTerm prologTerm = bindings.get(name);
		if (prologTerm == null) {
			final String message = "Cannot extract " + name
					+ " from bindings.\n" + listBindings(bindings);
			throw new ResultParserException(message, null);
		}
		return prologTerm;
	}

	private static PrologTerm getFromBindings(
			final ISimplifiedROMap<String, PrologTerm> bindings,
			final String name) throws ResultParserException {
		PrologTerm prologTerm = bindings.get(name);
		if (prologTerm == null) {
			final String message = "Cannot extract " + name
					+ " from bindings.\n";
			throw new ResultParserException(message, null);
		}
		return prologTerm;
	}

	private static String listBindings(final Map<String, PrologTerm> bindings) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, PrologTerm> e : bindings.entrySet()) {
			sb.append(e.getKey());
			sb.append(" -> ");
			sb.append(e.getValue().toString());
			sb.append('\n');
		}
		return sb.toString();
	}

}
