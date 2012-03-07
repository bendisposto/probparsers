/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AProverComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.AFalsityPredicate;
import de.be4.classicalb.core.parser.node.Start;

/**
 * Semantic check for expressions that can only be used in the prover, not
 * standard B machines
 * 
 * @author plagge
 */
public class ProverExpressionsCheck extends DepthFirstAdapter implements
		SemanticCheck {

	private ParseOptions options;
	private CheckException error;

	public void runChecks(Start rootNode) throws CheckException {
		if (options.restrictProverExpressions) {
			error = null;
			rootNode.apply(this);
			if (error != null) {
				throw error;
			}
		}
	}

	public void setOptions(ParseOptions options) {
		this.options = options;
	}

	@Override
	public void caseAFalsityPredicate(AFalsityPredicate node) {
		if (error == null) {
			error = new CheckException(
					"bfalse is not allowed in ordenary B files", node);
		}
	}

    /* todo: ask Jens */
	@Override
	public void caseAProverComprehensionSetExpression(
			AProverComprehensionSetExpression node) {
		if (error == null) {
			error = new CheckException("SET not allowed in ordenary B files",
					node);
		}
	}

}
