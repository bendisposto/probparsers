package de.prob.parser.ast.visitors.generic;

import de.prob.parser.ast.nodes.ltl.LTLBPredicateNode;
import de.prob.parser.ast.nodes.ltl.LTLInfixOperatorNode;
import de.prob.parser.ast.nodes.ltl.LTLKeywordNode;
import de.prob.parser.ast.nodes.ltl.LTLNode;
import de.prob.parser.ast.nodes.ltl.LTLPrefixOperatorNode;

public interface ParametrisedLTLVisitor<R, P> {

	default R visitLTLNode(LTLNode node, P expected) {
		if (node instanceof LTLBPredicateNode) {
			return visitLTLBPredicateNode((LTLBPredicateNode) node, expected);
		} else if (node instanceof LTLInfixOperatorNode) {
			return visitLTLInfixOperatorNode((LTLInfixOperatorNode) node, expected);
		} else if (node instanceof LTLKeywordNode) {
			return visitLTLKeywordNode((LTLKeywordNode) node, expected);
		} else if (node instanceof LTLPrefixOperatorNode) {
			return visitLTLPrefixOperatorNode((LTLPrefixOperatorNode) node, expected);
		}
		throw new AssertionError(node.getClass());
	}

	R visitLTLPrefixOperatorNode(LTLPrefixOperatorNode node, P expected);

	R visitLTLKeywordNode(LTLKeywordNode node, P expected);

	R visitLTLInfixOperatorNode(LTLInfixOperatorNode node, P expected);

	R visitLTLBPredicateNode(LTLBPredicateNode node, P expected);
}
