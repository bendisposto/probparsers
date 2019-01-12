package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.AProverComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.AFalsityPredicate;
import de.be4.classicalb.core.parser.node.ASubstitutionPredicate;
import de.be4.classicalb.core.parser.node.Start;

/**
 * Semantic check for expressions that can only be used in the prover, not
 * standard B machines
 * 
 * @author plagge
 */
public class ProverExpressionsCheck extends DepthFirstAdapter implements SemanticCheck {

	private ParseOptions options;
	private final List<CheckException> exceptions = new ArrayList<>();

	public void runChecks(Start rootNode) {
		if (options.isRestrictProverExpressions()) {
			rootNode.apply(this);
		}
	}

	public void setOptions(ParseOptions options) {
		this.options = options;
	}
	
// we allow #truth_predicate and bfalse cannot be used anyway as it is tokenize
// so: why not allow it?
// 	@Override
// 	public void caseAFalsityPredicate(AFalsityPredicate node) {
// 		exceptions.add(new CheckException("bfalse is not allowed in ordinary B files", node));
// 	}

	/* todo: ask Jens */
	@Override
	public void caseAProverComprehensionSetExpression(AProverComprehensionSetExpression node) {
		exceptions.add(new CheckException("SET not allowed in ordinary B files", node));
	}

	@Override
	public void caseASubstitutionPredicate(ASubstitutionPredicate node) {
		// if (error == null) {
		// error = new
		// CheckException("Substitution in Predicates are not supported in
		// ordinary B files",
		// node);
		// }
	}

	@Override
	public List<CheckException> getCheckExceptions() {
		return this.exceptions;
	}

}
