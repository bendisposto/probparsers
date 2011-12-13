/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.prolog;

import de.be4.classicalb.core.parser.node.Node;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

/**
 * PositionPrinters are used to determine the source position of an AST node and
 * to print that information as a Prolog term.
 * 
 * @author plagge
 */
public interface PositionPrinter {
	/**
	 * Sets the {@link PrologTermOutput} instance that should be used to print
	 * the position information
	 * 
	 * @param pout
	 *            The {@link PrologTermOutput}, never <code>null</code>.
	 */
	void setPrologTermOutput(IPrologTermOutput pout);

	/**
	 * Prints the position info of an AST node as exactly one Prolog term. If no
	 * source position can be found for the node, this function should print
	 * something like an atom "none".
	 * 
	 * @param node
	 *            The AST node, never <code>null</code>
	 */
	void printPosition(Node node);
}
