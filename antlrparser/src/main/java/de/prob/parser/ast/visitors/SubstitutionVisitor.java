package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.*;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;
import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;

public interface SubstitutionVisitor<R, P> extends FormulaAndSubstitutionVisitor<R, P> {

    /*
     * Expressions
     */
    @Override
    default R visitExprNode(ExprNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitExprOperatorNode(ExpressionOperatorNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitIdentifierExprNode(IdentifierExprNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitCastPredicateExpressionNode(CastPredicateExpressionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitNumberNode(NumberNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitQuantifiedExpressionNode(QuantifiedExpressionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitSetComprehensionNode(SetComprehensionNode node, P expected) {
        throw new AssertionError();
    }

    /*
     * Predicates
     */
    @Override
    default R visitPredicateNode(PredicateNode node, P expected) {
        throw new AssertionError(node);
    }

    @Override
    default R visitIdentifierPredicateNode(IdentifierPredicateNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitPredicateOperatorNode(PredicateOperatorNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitQuantifiedPredicateNode(QuantifiedPredicateNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitEnumerationSetNode(EnumerationSetNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitDeferredSetNode(DeferredSetNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitEnumeratedSetElementNode(EnumeratedSetElementNode node, P expected) {
        throw new AssertionError();
    }
}
