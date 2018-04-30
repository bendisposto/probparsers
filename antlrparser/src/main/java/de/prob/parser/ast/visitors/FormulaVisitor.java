package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.AnySubstitutionNode;
import de.prob.parser.ast.nodes.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.nodes.ConditionSubstitutionNode;
import de.prob.parser.ast.nodes.IfSubstitutionNode;
import de.prob.parser.ast.nodes.ParallelSubstitutionNode;
import de.prob.parser.ast.nodes.SelectSubstitutionNode;
import de.prob.parser.ast.nodes.SequentialCompositionNode;
import de.prob.parser.ast.nodes.SingleAssignSubstitutionNode;
import de.prob.parser.ast.nodes.SkipSubstitutionNode;

public interface FormulaVisitor<R, P> extends FormulaAndSubstitutionVisitor<R, P> {

    @Override
    default R visitSelectSubstitutionNode(SelectSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitIfSubstitutionNode(IfSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitSingleAssignSubstitution(SingleAssignSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitParallelSubstitutionNode(ParallelSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitSequentialCompositionNode(SequentialCompositionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitAnySubstitution(AnySubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitConditionSubstitutionNode(ConditionSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

    @Override
    default R visitSkipSubstitutionNode(SkipSubstitutionNode node, P expected) {
        throw new AssertionError();
    }

}
