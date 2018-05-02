package de.prob.parser.antlr;

import static files.BParser.AND;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.PredicateNode;
import de.prob.parser.ast.nodes.PredicateOperatorNode;
import de.prob.parser.ast.nodes.PredicateOperatorNode.PredicateOperator;
import files.BParserBaseVisitor;
import files.BParser.AndOrListContext;
import files.BParser.Predicate_atomicContext;

public class ASTFormulaCreator extends BParserBaseVisitor<Node> {

	public List<PredicateNode> createPredicateNodeList(PredicateNode... predicateArguments) {
		List<PredicateNode> list = new ArrayList<>();
		for (PredicateNode predicateNode : predicateArguments) {
			list.add(predicateNode);
		}
		return list;
	}

	@Override
	public PredicateNode visitAndOrList(AndOrListContext ctx) {
		List<Predicate_atomicContext> terms = ctx.terms;
		List<Token> operators = ctx.operators;
		PredicateNode temp = (PredicateNode) ctx.terms.get(0).accept(this);
		for (int i = 0; i < operators.size(); i++) {
			Predicate_atomicContext rightContext = terms.get(i + 1);
			PredicateNode right = (PredicateNode) rightContext.accept(this);
			PredicateOperator op = ctx.operators.get(i).getType() == AND ? PredicateOperator.AND : PredicateOperator.OR;
			temp = new PredicateOperatorNode(Util.createSourceCodePosition(ctx), op,
					createPredicateNodeList(temp, right));
		}
		return temp;
	}
}
