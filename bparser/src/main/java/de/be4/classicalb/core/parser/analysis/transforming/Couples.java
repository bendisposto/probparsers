package de.be4.classicalb.core.parser.analysis.transforming;

import java.util.LinkedList;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AAnySubstitution;
import de.be4.classicalb.core.parser.node.AComprehensionSetExpression;
import de.be4.classicalb.core.parser.node.ACoupleExpression;
import de.be4.classicalb.core.parser.node.AExistsPredicate;
import de.be4.classicalb.core.parser.node.AGeneralProductExpression;
import de.be4.classicalb.core.parser.node.AGeneralSumExpression;
import de.be4.classicalb.core.parser.node.ALambdaExpression;
import de.be4.classicalb.core.parser.node.ALetSubstitution;
import de.be4.classicalb.core.parser.node.AQuantifiedIntersectionExpression;
import de.be4.classicalb.core.parser.node.AQuantifiedUnionExpression;
import de.be4.classicalb.core.parser.node.AForallPredicate;
import de.be4.classicalb.core.parser.node.AVarSubstitution;
import de.be4.classicalb.core.parser.node.PExpression;

/**
 * <p>
 * In some constructs (ExistsPredicate,
 * UniversalQuantificationPredicate, ...) a list of identifiers is recognized as
 * a couple if the identifier list is surrounded by parenthesis. This results in
 * constructs having a list of children with one element, which is a couple,
 * which has some children (identifiers) itself.
 * </p>
 * <p>
 * This visitor finds those cases and pulls up the list of identifiers one
 * level, so that the identifiers are children of the construct.
 * </p>
 * 
 * <p>
 * todo: refactor this fix the grammar and remove this complete module
 * </p>
 * @author Fabian
 * 
 */
public class Couples extends DepthFirstAdapter {
	@Override
	public void inAExistsPredicate(
			final AExistsPredicate node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAForallPredicate(
			final AForallPredicate node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAGeneralSumExpression(final AGeneralSumExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAGeneralProductExpression(final AGeneralProductExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inALambdaExpression(final ALambdaExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAComprehensionSetExpression(
			final AComprehensionSetExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAQuantifiedUnionExpression(
			final AQuantifiedUnionExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAQuantifiedIntersectionExpression(
			final AQuantifiedIntersectionExpression node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAAnySubstitution(final AAnySubstitution node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inALetSubstitution(final ALetSubstitution node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	@Override
	public void inAVarSubstitution(final AVarSubstitution node) {

		final LinkedList<PExpression> coupleReplacement = getCoupleReplacement(node
				.getIdentifiers());
		if (coupleReplacement != null) {
			node.setIdentifiers(coupleReplacement);
		}
	}

	private LinkedList<PExpression> getCoupleReplacement(
			final LinkedList<PExpression> identifiers) {

		// allow only one child if this child is a couple
		if (identifiers.size() == 1) {
			final PExpression firstChild = identifiers.getFirst();
			if (firstChild instanceof ACoupleExpression) {
				// if couple, pull children one level up,
				// replacing the couple
				final ACoupleExpression couple = (ACoupleExpression) firstChild;
				final LinkedList<PExpression> elements = couple.getList();
				final LinkedList<PExpression> newIdentifiers = new LinkedList<PExpression>();

				for (final PExpression child : elements) {
					newIdentifiers.add(child);
				}

				return newIdentifiers;
			}
		}

		return null;
	}
}
