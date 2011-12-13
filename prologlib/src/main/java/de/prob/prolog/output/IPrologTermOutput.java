package de.prob.prolog.output;

import java.math.BigInteger;

import de.prob.prolog.term.PrologTerm;

public interface IPrologTermOutput {

	/**
	 * Start a new term. This methods prints the (escaped, if needed) functor
	 * and the opening brackets. All other terms (atoms, numbers, variables,
	 * ...) are printed as arguments of this term until it is closed with
	 * {@link #closeTerm()}.
	 * 
	 * Same as {@link #openTerm(String, boolean)} with <code>false</code> as
	 * second argument.
	 * 
	 * @param functor
	 *            the functor, never <code>null</code>.
	 */
	IPrologTermOutput openTerm(final String functor);

	/**
	 * Start a new term. This methods prints the (escaped, if needed) functor
	 * and the opening brackets. All other terms (atoms, numbers, variables,
	 * ...) are printed as arguments of this term until it is closed with
	 * {@link #closeTerm()}. You should close all opened terms.
	 * 
	 * Use this method instead of {@link #openTerm(String)} if you want to
	 * control whether the arguments of the term should be indented or not. This
	 * is useful to write terms more compact when you know that they are always
	 * short.
	 * 
	 * @param functor
	 *            the functor, never <code>null</code>
	 * @param ignoreIndention
	 *            if this is set to true, the arguments of this term are not
	 *            subject to indent.
	 */
	IPrologTermOutput openTerm(final String functor,
			final boolean ignoreIndention);

	/**
	 * Finish a term that was started with {@link #openTerm(String)}. This
	 * method basically prints the closing parenthesis.
	 * 
	 * @return
	 */
	IPrologTermOutput closeTerm();

	/**
	 * Print an atom. The atom will be escaped, if needed.
	 * 
	 * @param content
	 *            the name of the atom, never <code>null</code>
	 * @return
	 */
	IPrologTermOutput printAtom(final String content);

	/**
	 * Print an atom or number. Use this for State ID!
	 * 
	 * @param content
	 *            the name of the atom, never <code>null</code>
	 * @return
	 */
	IPrologTermOutput printAtomOrNumber(final String content);

	/**
	 * Print a string. The content of the string will be escaped, if needed.
	 * 
	 * @param content
	 *            the string content, never <code>null</code>
	 * @return
	 */
	IPrologTermOutput printString(final String content);

	/**
	 * Print a number.
	 * 
	 * @param number
	 *            the number to print
	 * @return
	 */
	IPrologTermOutput printNumber(final long number);

	/**
	 * Print a number.
	 * 
	 * @param number
	 *            the number to print
	 * @return
	 */
	IPrologTermOutput printNumber(final BigInteger number);

	/**
	 * Start a new list. All following terms (atoms, numbers, etc.) until the
	 * next call of {@link #closeList()} are put into the list. All opened lists
	 * should be closed. Basically this method prints the opening bracket.
	 * 
	 * @return
	 */
	IPrologTermOutput openList();

	/**
	 * Finish a list that was started with {@link #openList()}. Basically this
	 * method prints the closing bracket.
	 * 
	 * @return
	 */
	IPrologTermOutput closeList();

	/**
	 * Print an empty list.
	 * 
	 * @return
	 */
	IPrologTermOutput emptyList();

	/**
	 * Print a Prolog variable. Variables should start with an upper case
	 * character (or underscore) and should not contain spaces (and other
	 * illegal characters). For variables, no escaping is done.
	 * 
	 * @param var
	 *            the name of the variable, never <code>null</code>
	 * @return
	 * @throws IllegalArgumentException
	 *             if the variable is not a syntactically valid Prolog variable.
	 */
	IPrologTermOutput printVariable(final String var);

	/**
	 * Print a complete Term.
	 * 
	 * @param term
	 *            the term, never <code>null</code>
	 * @return
	 */
	IPrologTermOutput printTerm(final PrologTerm term);

	/**
	 * flush the underlying output stream
	 * 
	 * @return
	 */
	IPrologTermOutput flush();

	/**
	 * print a Prolog full stop.
	 * 
	 * @return
	 */
	IPrologTermOutput fullstop();

}