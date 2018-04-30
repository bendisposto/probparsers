package de.prob.parser.ast.nodes;

import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;

public class QuantifiedPredicateNode extends PredicateNode {

	public enum QuantifiedPredicateOperator {
		UNIVERSAL_QUANTIFICATION, EXISTENTIAL_QUANTIFICATION
	}

	private List<DeclarationNode> declarationList;
	private PredicateNode predicateNode;
	private QuantifiedPredicateOperator operator;

	public QuantifiedPredicateNode(ParseTree ctx, List<DeclarationNode> declarationList, PredicateNode predNode,
			QuantifiedPredicateOperator operator) {
		super(ctx);
		this.declarationList = declarationList;
		this.predicateNode = predNode;
		this.operator = operator;
	}

	public List<DeclarationNode> getDeclarationList() {
		return declarationList;
	}

	public PredicateNode getPredicateNode() {
		return predicateNode;
	}

	public QuantifiedPredicateOperator getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (operator == QuantifiedPredicateOperator.EXISTENTIAL_QUANTIFICATION) {
			sb.append("EXISTS(");
		} else if (operator == QuantifiedPredicateOperator.UNIVERSAL_QUANTIFICATION) {
			sb.append("FORALL(");
		}
		sb.append(declarationList.stream().map(Object::toString).collect(Collectors.joining(",")));
		sb.append(",");
		sb.append(predicateNode);
		sb.append(")");
		return sb.toString();
	}

	public void setPredicate(PredicateNode pred) {
		this.predicateNode = pred;
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		QuantifiedPredicateNode that = (QuantifiedPredicateNode) other;
		return this.operator.equals(that.operator) && this.predicateNode.equalAst(that.predicateNode)
				&& NodeUtil.equalAst(this.declarationList, that.declarationList);

	}
}
