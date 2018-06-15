package de.prob.parser.ast.visitors.generic;

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
import de.prob.parser.ast.nodes.substitution.AnySubstitutionNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ConditionSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SkipSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionIdentifierCallNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;

public class ASTVisitor implements ExpressionVisitor, SubstitutionVisitor, PredicateVisitor {

	@Override
	public void visitIdentifierPredicateNode(IdentifierPredicateNode node) {
		// no children
	}

	@Override
	public void visitPredicateOperatorNode(PredicateOperatorNode node) {
		for (PredicateNode arg : node.getPredicateArguments()) {
			visitPredicateNode(arg);
		}
	}

	@Override
	public void visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node) {
		for (ExprNode arg : node.getExpressionNodes()) {
			visitExprNode(arg);
		}
	}

	@Override
	public void visitQuantifiedPredicateNode(QuantifiedPredicateNode node) {
		visitPredicateNode(node.getPredicateNode());
	}

	@Override
	public void visitAssignSubstitutionNode(AssignSubstitutionNode node) {
		for (ExprNode arg : node.getLeftSide()) {
			visitExprNode(arg);
		}

		for (ExprNode arg : node.getRightSide()) {
			visitExprNode(arg);
		}
	}

	@Override
	public void visitSkipSubstitutionNode(SkipSubstitutionNode node) {
		// no children
	}

	@Override
	public void visitConditionSubstitutionNode(ConditionSubstitutionNode node) {
		visitPredicateNode(node.getCondition());
		visitSubstitutionNode(node.getSubstitution());
	}

	@Override
	public void visitAnySubstitution(AnySubstitutionNode node) {
		visitPredicateNode(node.getWherePredicate());
		visitSubstitutionNode(node.getThenSubstitution());
	}

	@Override
	public void visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node) {
		for (PredicateNode pred : node.getConditions()) {
			visitPredicateNode(pred);
		}
		for (SubstitutionNode arg : node.getSubstitutions()) {
			visitSubstitutionNode(arg);
		}
		if (node.getElseSubstitution() != null) {
			visitSubstitutionNode(node.getElseSubstitution());
		}
	}

	@Override
	public void visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node) {
		// no children
	}

	@Override
	public void visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node) {
		// no children

	}

	@Override
	public void visitExprOperatorNode(ExpressionOperatorNode node) {
		for (ExprNode arg : node.getExpressionNodes()) {
			visitExprNode(arg);
		}

	}

	@Override
	public void visitIdentifierExprNode(IdentifierExprNode node) {
		// no children

	}

	@Override
	public void visitCastPredicateExpressionNode(CastPredicateExpressionNode node) {
		visitPredicateNode(node.getPredicate());
	}

	@Override
	public void visitNumberNode(NumberNode node) {
		// no children

	}

	@Override
	public void visitQuantifiedExpressionNode(QuantifiedExpressionNode node) {
		visitPredicateNode(node.getPredicateNode());
		visitExprNode(node.getExpressionNode());
	}

	@Override
	public void visitSetComprehensionNode(SetComprehensionNode node) {
		visitPredicateNode(node.getPredicateNode());
	}

	@Override
	public void visitListSubstitutionNode(ListSubstitutionNode node) {
		for (SubstitutionNode arg : node.getSubstitutions()) {
			visitSubstitutionNode(arg);
		}
	}

	@Override
	public void visitSubstitutionIdentifierCallNode(SubstitutionIdentifierCallNode node) {
		for (ExprNode arg : node.getArguments()) {
			visitExprNode(arg);
		}
	}

}
