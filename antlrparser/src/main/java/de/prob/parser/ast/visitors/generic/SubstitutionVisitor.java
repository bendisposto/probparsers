package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.substitution.AnySubstitutionNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ConditionSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SkipSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;

public interface SubstitutionVisitor {

	default void visitSubstitutionNode(SubstitutionNode node) {
		if (node instanceof ListSubstitutionNode) {
			visitListSubstitutionNode((ListSubstitutionNode) node);
		} else if (node instanceof AnySubstitutionNode) {
			visitAnySubstitution((AnySubstitutionNode) node);
		} else if (node instanceof BecomesSuchThatSubstitutionNode) {
			visitBecomesSuchThatSubstitutionNode((BecomesSuchThatSubstitutionNode) node);
		} else if (node instanceof BecomesElementOfSubstitutionNode) {
			visitBecomesElementOfSubstitutionNode((BecomesElementOfSubstitutionNode) node);
		} else if (node instanceof ConditionSubstitutionNode) {
			visitConditionSubstitutionNode((ConditionSubstitutionNode) node);
		} else if (node instanceof IfOrSelectSubstitutionsNode) {
			visitIfOrSelectSubstitutionsNode((IfOrSelectSubstitutionsNode) node);
		} else if (node instanceof SkipSubstitutionNode) {
			visitSkipSubstitutionNode((SkipSubstitutionNode) node);
		} else if (node instanceof AssignSubstitutionNode) {
			visitAssignSubstitutionNode((AssignSubstitutionNode) node);
		} else {
			throw new AssertionError(node.getClass());
		}

	}

	void visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node);

	void visitListSubstitutionNode(ListSubstitutionNode node);

	void visitAssignSubstitutionNode(AssignSubstitutionNode node);

	void visitSkipSubstitutionNode(SkipSubstitutionNode node);

	void visitConditionSubstitutionNode(ConditionSubstitutionNode node);

	void visitAnySubstitution(AnySubstitutionNode node);

	void visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node);

	void visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node);
}
