package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.*;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.ltl.*;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import de.prob.parser.ast.visitors.generic.ParametrisedLTLVisitor;
import de.prob.parser.ast.visitors.generic.ParametrisedExpressionVisitor;
import de.prob.parser.ast.visitors.generic.ParametrisedPredicateVisitor;
import de.prob.parser.ast.visitors.generic.ParametrisedSubstitutionVisitor;

public interface AbstractVisitor<R, P>
		extends ParametrisedPredicateVisitor<R, P>, ParametrisedExpressionVisitor<R, P>, ParametrisedSubstitutionVisitor<R, P>, ParametrisedLTLVisitor<R, P> {

	default R visitNode(Node node, P expected) {
		if (node instanceof ExprNode) {
			return visitExprNode((ExprNode) node, expected);
		} else if (node instanceof PredicateNode) {
			return visitPredicateNode((PredicateNode) node, expected);
		} else if (node instanceof SubstitutionNode) {
			return visitSubstitutionNode((SubstitutionNode) node, expected);
		} else if (node instanceof LTLNode) {
			return visitLTLNode((LTLNode) node, expected);
		}
		throw new AssertionError();
	}

}
