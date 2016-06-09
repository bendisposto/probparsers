package de.be4.classicalb.core.parser.analysis.transforming;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.AIfPredicatePredicate;
import de.be4.classicalb.core.parser.node.AImplicationPredicate;
import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.PPredicate;
import static de.be4.classicalb.core.parser.util.NodeCloner.*;

public class SyntaxExtensionTranslator extends DepthFirstAdapter {

	@Override
	public void outAIfPredicatePredicate(AIfPredicatePredicate node) {
		// IF P THE P2 ELSE P3 END
		// (p => p2) & (not(p) => p3)
		;
		AImplicationPredicate imp1 = new AImplicationPredicate(
				(PPredicate) cloneNode(node.getCondition()),
				(PPredicate) cloneNode(node.getThen()));
		AImplicationPredicate imp2 = new AImplicationPredicate(
				new ANegationPredicate(
						(PPredicate) cloneNode(node.getCondition())),
				(PPredicate) cloneNode(node.getElse()));
		AConjunctPredicate con = new AConjunctPredicate(imp1, imp2);
		node.replaceBy(con);
	}

}
