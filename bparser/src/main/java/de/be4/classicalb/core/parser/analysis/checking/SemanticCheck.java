/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.Start;

/**
 * A common subclass for semantic checks
 * 
 * @author plagge
 */
public interface SemanticCheck {
	void setOptions(ParseOptions options);

	void runChecks(Start rootNode) throws CheckException;
}
