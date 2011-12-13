/**
 * 
 */
package de.be4.classicalb.core.parser;

/**
 * @author plagge
 */
public class ParseOptions {
	/*
	 * The parser should accept a primed identifier ("x$0") only in becomeSuch
	 * substitutions and there only with the integer 0.
	 */
	public boolean restrictPrimedIdentifiers = true;

	/*
	 * The parser must not accept some expressions that are only relevant in PO
	 * files. (E.g. bfalse or SET(x).(P) )
	 */
	public boolean restrictProverExpressions = true;
}
