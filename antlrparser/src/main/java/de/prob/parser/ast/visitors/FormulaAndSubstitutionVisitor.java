package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.DeferredSetNode;
import de.prob.parser.ast.nodes.EnumeratedSetElementNode;
import de.prob.parser.ast.nodes.EnumerationSetNode;
import de.prob.parser.ast.nodes.ltl.*;

public interface FormulaAndSubstitutionVisitor<R, P> extends AbstractVisitor<R, P> {

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

	@Override
	default R visitLTLNode(LTLNode node, P expected) {
		throw new AssertionError();
	}

	@Override
	default R visitLTLPrefixOperatorNode(LTLPrefixOperatorNode node, P expected) {
		throw new AssertionError();
	}

	@Override
	default R visitLTLKeywordNode(LTLKeywordNode node, P expected) {
		throw new AssertionError();
	}

	@Override
	default R visitLTLInfixOperatorNode(LTLInfixOperatorNode node, P expected) {
		throw new AssertionError();
	}

	@Override
	default R visitLTLBPredicateNode(LTLBPredicateNode node, P expected) {
		throw new AssertionError();
	}
}
