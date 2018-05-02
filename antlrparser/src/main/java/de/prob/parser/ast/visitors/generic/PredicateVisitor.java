package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;

public interface PredicateVisitor {

	default void visitPredicateNode(PredicateNode node) {
		if (node instanceof PredicateOperatorNode) {
			visitPredicateOperatorNode((PredicateOperatorNode) node);
		} else if (node instanceof PredicateOperatorWithExprArgsNode) {
			visitPredicateOperatorWithExprArgs((PredicateOperatorWithExprArgsNode) node);
		} else if (node instanceof IdentifierPredicateNode) {
			visitIdentifierPredicateNode((IdentifierPredicateNode) node);
		} else if (node instanceof QuantifiedPredicateNode) {
			visitQuantifiedPredicateNode((QuantifiedPredicateNode) node);
		} else {
			throw new AssertionError(node);
		}

	}

	void visitIdentifierPredicateNode(IdentifierPredicateNode node);

	void visitPredicateOperatorNode(PredicateOperatorNode node);

	void visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node);

	void visitQuantifiedPredicateNode(QuantifiedPredicateNode node);
}
