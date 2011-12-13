/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.checking;

import java.util.LinkedList;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.ABecomesSuchSubstitution;
import de.be4.classicalb.core.parser.node.APrimedIdentifierExpression;
import de.be4.classicalb.core.parser.node.AWhileSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

/**
 * This semantic checks looks for occurrences of primed identifiers like x$0.
 * 
 * If {@link ParseOptions#restrictPrimedIdentifiers} is <code>true</code>, "x$i"
 * can only occur in the predicate of a become-such-substitution, and i must be
 * 0 in that case.
 * 
 * If {@link ParseOptions#restrictPrimedIdentifiers} is <code>false</code>,
 * "x$i" can occur where ever an identifier can occur. i may become any
 * non-negative number.
 * 
 * @author plagge
 */
public class PrimedIdentifierCheck extends DepthFirstAdapter implements
		SemanticCheck {
	private ParseOptions options;
	private boolean in_acceptable_place;
	private CheckException error;

	public void runChecks(final Start rootNode) throws CheckException {
		// if the usage of primed identifiers is not restricted, there is
		// nothing to check for us
		if (options.restrictPrimedIdentifiers) {
			in_acceptable_place = false;
			error = null;
			rootNode.apply(this);
			if (error != null)
				throw error;
		}
	}

	public void setOptions(final ParseOptions options) {
		this.options = options;
	}

	@Override
	public void caseABecomesSuchSubstitution(final ABecomesSuchSubstitution node) {
		for (PExpression expr : node.getIdentifiers()) {
			expr.apply(this);
		}
		in_acceptable_place = true;
		node.getPredicate().apply(this);
		in_acceptable_place = false;
	}

	@Override
	public void caseAWhileSubstitution(final AWhileSubstitution node) {
		inAWhileSubstitution(node);
		if (node.getCondition() != null) {
			node.getCondition().apply(this);
		}
		if (node.getDoSubst() != null) {
			node.getDoSubst().apply(this);
		}
		if (node.getInvariant() != null) {
			in_acceptable_place = true;
			node.getInvariant().apply(this);
			in_acceptable_place = false;
		}
		if (node.getVariant() != null) {
			node.getVariant().apply(this);
		}
		outAWhileSubstitution(node);
	}

	@Override
	public void caseAPrimedIdentifierExpression(
			final APrimedIdentifierExpression node) {
		if (error == null) {
			final String id = getIdentifier(node.getIdentifier());
			final String grade = node.getGrade().getText();
			if (in_acceptable_place) {
				if (!"0".equals(grade)) {
					error = new CheckException(
							"construct $ only allowed with zero here (" + id
									+ "$0)", node);
				}
			} else {
				error = new CheckException("construct " + id + "$" + grade
						+ " only allowed in become-such-substitutions", node);
			}
		}
	}

	private static String getIdentifier(
			final LinkedList<TIdentifierLiteral> identifiers) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (TIdentifierLiteral id : identifiers) {
			if (!first) {
				sb.append('.');
			}
			sb.append(id.getText().trim());
			first = false;
		}
		return sb.toString();
	}

}
