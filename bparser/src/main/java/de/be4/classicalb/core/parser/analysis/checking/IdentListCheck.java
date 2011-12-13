package de.be4.classicalb.core.parser.analysis.checking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.be4.classicalb.core.parser.ParseOptions;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.exceptions.CheckException;
import de.be4.classicalb.core.parser.node.*;

/**
 * <p>
 * In several constructs the BParser only checks if a list of identifiers is a
 * valid list of expressions instead of checking if each entry is an identifier
 * expression. Thus it excepts to many expressions in these cases.
 * </p>
 * <p>
 * This class finds those constructs and checks if the identifier lists only
 * contain {@link AIdentifierExpression} nodes.
 * </p>
 * <p>
 * Additionally it checks if the LHS of an {@link AAssignSubstitution} and the
 * result list of an {@link AOpWithReturnSubstitution} only contain
 * {@link AIdentifierExpression} or {@link AFunctionExpression} nodes.
 * </p>
 * 
 * @author Fabian
 * 
 */
public class IdentListCheck extends DepthFirstAdapter implements SemanticCheck {

	private final Set<Node> nonIdentifiers = new HashSet<Node>();
	private ParseOptions options;

	/**
	 * <p>
	 * See class description. First {@link AAssignSubstitution} nodes are
	 * checked, then the other nodes.
	 * </p>
	 * <p>
	 * An {@link CheckException} is thrown if there are
	 * {@link AAssignSubstitution} or {@link AOpWithReturnSubstitution} nodes
	 * with illegal elements in the LHS. Otherwise the other relevant nodes are
	 * checked for illegal entries in their identifier lists.
	 * </p>
	 * <p>
	 * In both cases the erroneous nodes are collected, so that only one
	 * exception is thrown for the {@link AAssignSubstitution} and
	 * {@link AOpWithReturnSubstitution} nodes respectively one for all other
	 * nodes.
	 * </p>
	 * 
	 * @param rootNode
	 * @throws CheckException
	 *             : Erroneous {@link AAssignSubstitution} and
	 *             {@link AOpWithReturnSubstitution} nodes are collected in one
	 *             exception and all other nodes in another one.
	 */
	public void runChecks(final Start rootNode) throws CheckException {
		nonIdentifiers.clear();

		/*
		 * First check all assignment nodes if the LHS only contains identifiers
		 * or functions.
		 */
		final AssignCheck assignCheck = new AssignCheck();
		rootNode.apply(assignCheck);

		final Set<Node> assignErrorNodes = assignCheck.nonIdentifiers;
		if (assignErrorNodes.size() > 0) {
			throw new CheckException("Identifier or function expected",
					assignErrorNodes.toArray(new Node[assignErrorNodes.size()]));
		}

		/*
		 * Then check other constructs which can only contain identifiers at
		 * special places.
		 */
		rootNode.apply(this);

		if (nonIdentifiers.size() > 0) {
			// at least one error was found
			throw new CheckException("Identifier expected", nonIdentifiers
					.toArray(new Node[nonIdentifiers.size()]));
		}
	}

	@Override
	public void inAExistentialQuantificationPredicate(
			final AExistentialQuantificationPredicate node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAUniversalQuantificationPredicate(
			final AUniversalQuantificationPredicate node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAGeneralSumExpression(final AGeneralSumExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAGeneralProductExpression(final AGeneralProductExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inALambdaExpression(final ALambdaExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAQuantifiedUnionExpression(
			final AQuantifiedUnionExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAQuantifiedIntersectionExpression(
			final AQuantifiedIntersectionExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAComprehensionSetExpression(
			final AComprehensionSetExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAEventBComprehensionSetExpression(
			AEventBComprehensionSetExpression node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAAnySubstitution(final AAnySubstitution node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inALetSubstitution(final ALetSubstitution node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inAVarSubstitution(final AVarSubstitution node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inABecomesSuchSubstitution(final ABecomesSuchSubstitution node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	@Override
	public void inABecomesElementOfSubstitution(
			final ABecomesElementOfSubstitution node) {
		checkForNonIdentifiers(node.getIdentifiers());
	}

	/**
	 * Adds all elements of the {@link List} to {@link #nonIdentifiers} that are
	 * not an instance of {@link AIdentifierExpression}.
	 * 
	 * @param identifiers
	 *            {@link List} to check
	 */
	private void checkForNonIdentifiers(final List<PExpression> identifiers) {
		for (final Iterator<PExpression> iterator = identifiers.iterator(); iterator
				.hasNext();) {
			final PExpression expression = iterator.next();

			if (!(isIdentifierExpression(expression))) {
				nonIdentifiers.add(expression);
			}
		}
	}

	private boolean isIdentifierExpression(final PExpression expression) {
		return expression instanceof AIdentifierExpression
				|| (!options.restrictPrimedIdentifiers && expression instanceof APrimedIdentifierExpression);
	}

	class AssignCheck extends DepthFirstAdapter {
		final Set<Node> nonIdentifiers = new HashSet<Node>();

		@Override
		public void inAAssignSubstitution(final AAssignSubstitution node) {
			checkList(node.getLhsExpression());
		}

		@Override
		public void inAOpWithReturnSubstitution(
				final AOpWithReturnSubstitution node) {
			checkList(node.getResultIdentifiers());
		}

		private void checkList(final List<PExpression> list) {
			for (final Iterator<PExpression> iterator = list.iterator(); iterator
					.hasNext();) {
				final PExpression expression = iterator.next();

				if (!(expression instanceof AIdentifierExpression || expression instanceof AFunctionExpression)) {
					nonIdentifiers.add(expression);
				}
			}
		}
	}

	public void setOptions(ParseOptions options) {
		this.options = options;
	}
}
