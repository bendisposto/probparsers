package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;

public interface ParametrisedPredicateVisitor<R, P> {

	default R visitPredicateNode(PredicateNode node, P expected) {
		if (node instanceof PredicateOperatorNode) {
			return visitPredicateOperatorNode((PredicateOperatorNode) node, expected);
		} else if (node instanceof PredicateOperatorWithExprArgsNode) {
			return visitPredicateOperatorWithExprArgs((PredicateOperatorWithExprArgsNode) node, expected);
		} else if (node instanceof IdentifierPredicateNode) {
			return visitIdentifierPredicateNode((IdentifierPredicateNode) node, expected);
		} else if (node instanceof QuantifiedPredicateNode) {
			return visitQuantifiedPredicateNode((QuantifiedPredicateNode) node, expected);
		}
		throw new AssertionError(node);
	}

	R visitIdentifierPredicateNode(IdentifierPredicateNode node, P expected);

	R visitPredicateOperatorNode(PredicateOperatorNode node, P expected);

	R visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, P expected);

	R visitQuantifiedPredicateNode(QuantifiedPredicateNode node, P expected);
}
