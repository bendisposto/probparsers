/**
 * 
 */
package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.APrimedIdentifierExpression;
import de.be4.classicalb.core.parser.node.Start;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

/**
 * This semantic check looks for occurrences of primed identifiers like x$0 and
 * checks that the number behind the dollar symbol is 0 in any case if
 * {@link ParseOptions#restrictPrimedIdentifiers} is <code>true</code>
 * 
 * Note, this semantic check do not ensure that a primed identifier only occurs
 * at a correct place, i.e. inside of a become-such-that-substitution or in a
 * loop invariant. Due to the use of definitions this conditions would become
 * more complex, and hence, will be checked on the prolog side.
 * 
 * 
 * If {@link ParseOptions#restrictPrimedIdentifiers} is <code>false</code>,
 * "x$i" can become any non-negative number.
 */
public class PrimedIdentifierCheck extends DepthFirstAdapter implements SemanticCheck {

	private final List<CheckException> exceptions = new ArrayList<>();
	private ParseOptions options;

	@Override
	public void runChecks(final Start rootNode) {

		if (options.isRestrictPrimedIdentifiers()) {
			rootNode.apply(this);
		}
	}

	@Override
	public void setOptions(final ParseOptions options) {
		this.options = options;
	}

	@Override
	public void caseAPrimedIdentifierExpression(final APrimedIdentifierExpression node) {
		final String id = getIdentifier(node.getIdentifier());
		final String grade = node.getGrade().getText();
		if (!"0".equals(grade)) {
			exceptions.add(new CheckException("construct $ only allowed with 0 (you should use " + id + "$0)", node));
		}
	}

	private static String getIdentifier(final LinkedList<TIdentifierLiteral> identifiers) {
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

	@Override
	public List<CheckException> getCheckExceptions() {
		return this.exceptions;
	}

}
