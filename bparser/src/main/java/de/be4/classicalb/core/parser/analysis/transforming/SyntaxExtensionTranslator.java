package de.be4.classicalb.core.parser.analysis.transforming;

import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.AConjunctPredicate;
import de.be4.classicalb.core.parser.node.AIfPredicatePredicate;
import de.be4.classicalb.core.parser.node.AImplicationPredicate;
import de.be4.classicalb.core.parser.node.AMultilineStringExpression;
import de.be4.classicalb.core.parser.node.ANegationPredicate;
import de.be4.classicalb.core.parser.node.AStringExpression;
import de.be4.classicalb.core.parser.node.TMultilineStringContent;
import de.be4.classicalb.core.parser.node.TStringLiteral;

import static de.be4.classicalb.core.parser.util.NodeCloner.cloneNode;

public class SyntaxExtensionTranslator extends DepthFirstAdapter {

	@Override
	public void outAIfPredicatePredicate(AIfPredicatePredicate node) {
		// IF P THE P2 ELSE P3 END
		// will be translated into
		// (p => p2) & (not(p) => p3)
		AImplicationPredicate imp1 = new AImplicationPredicate(cloneNode(node.getCondition()),
				cloneNode(node.getThen()));
		AImplicationPredicate imp2 = new AImplicationPredicate(
				new ANegationPredicate(cloneNode(node.getCondition())),
				cloneNode(node.getElse()));
		AConjunctPredicate con = new AConjunctPredicate(imp1, imp2);
		con.setStartPos(node.getStartPos());
		con.setEndPos(node.getEndPos());
		node.replaceBy(con);
	}

	@Override
	public void caseAMultilineStringExpression(AMultilineStringExpression node) {
		final TMultilineStringContent content = node.getContent();
		final String text = content.getText();
		TStringLiteral tStringLiteral = new TStringLiteral(text, content.getLine(), content.getPos());
		AStringExpression stringNode = new AStringExpression(tStringLiteral);
		stringNode.setStartPos(node.getStartPos());
		stringNode.setEndPos(node.getEndPos());
		node.replaceBy(stringNode);
	}

}
