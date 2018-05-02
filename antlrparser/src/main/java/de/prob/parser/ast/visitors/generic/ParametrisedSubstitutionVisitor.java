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

public interface ParametrisedSubstitutionVisitor<R, P> {

	default R visitSubstitutionNode(SubstitutionNode node, P expected) {
		if (node instanceof IfOrSelectSubstitutionsNode) {
			return visitIfOrSelectSubstitutionsNode((IfOrSelectSubstitutionsNode) node, expected);
		} else if (node instanceof AnySubstitutionNode) {
			return visitAnySubstitution((AnySubstitutionNode) node, expected);
		} else if (node instanceof BecomesSuchThatSubstitutionNode) {
			return visitBecomesSuchThatSubstitutionNode((BecomesSuchThatSubstitutionNode) node, expected);
		} else if (node instanceof BecomesElementOfSubstitutionNode) {
			return visitBecomesElementOfSubstitutionNode((BecomesElementOfSubstitutionNode) node, expected);
		} else if (node instanceof ConditionSubstitutionNode) {
			return visitConditionSubstitutionNode((ConditionSubstitutionNode) node, expected);
		} else if (node instanceof SkipSubstitutionNode) {
			return visitSkipSubstitutionNode((SkipSubstitutionNode) node, expected);
		} else if (node instanceof AssignSubstitutionNode) {
			return visitAssignSubstitutionNode((AssignSubstitutionNode) node, expected);
		} else if (node instanceof ListSubstitutionNode) {
			return visitListSubstitutionNode((ListSubstitutionNode) node, expected);
		}
		throw new AssertionError(node.getClass());
	}

	R visitListSubstitutionNode(ListSubstitutionNode node, P expected);

	R visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node, P expected);

	R visitAssignSubstitutionNode(AssignSubstitutionNode node, P expected);

	R visitSkipSubstitutionNode(SkipSubstitutionNode node, P expected);

	R visitConditionSubstitutionNode(ConditionSubstitutionNode node, P expected);

	R visitAnySubstitution(AnySubstitutionNode node, P expected);

	R visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, P expected);

	R visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node, P expected);

}
