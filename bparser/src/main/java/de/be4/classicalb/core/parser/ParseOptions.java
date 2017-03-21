/**
 * 
 */
package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.grammars.DefaultGrammar;
import de.be4.classicalb.core.parser.grammars.IGrammar;

public class ParseOptions {
	
	/*
	 * The parser must not accept some expressions that are only relevant in PO
	 * files. (E.g. bfalse or SET(x).(P) )
	 */
	public boolean restrictProverExpressions = true;
	
	
	/*
	 * The parser should accept a primed identifier ("x$0") only in becomeSuch
	 * substitutions and there only with the integer 0.
	 * This option can be set to false in order to parse PO files of AtelierB.
	 */
	public boolean restrictPrimedIdentifiers = true;
	public boolean useAntlr4Parser = true;
	
	public IGrammar grammar = new DefaultGrammar();
}
