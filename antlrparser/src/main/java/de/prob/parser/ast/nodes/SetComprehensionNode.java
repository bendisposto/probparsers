package de.prob.parser.ast.nodes;

import java.util.List;
import java.util.stream.Collectors;

import de.prob.parser.ast.SourceCodePosition;

public class SetComprehensionNode extends ExprNode {
	private List<DeclarationNode> declarationList;
	private PredicateNode predicateNode;

	public SetComprehensionNode(SourceCodePosition sourceCodePosition, List<DeclarationNode> declarationList,
			PredicateNode predicateNode) {
		super(sourceCodePosition);
		this.declarationList = declarationList;
		this.predicateNode = predicateNode;
	}

	public List<DeclarationNode> getDeclarationList() {
		return declarationList;
	}

	public PredicateNode getPredicateNode() {
		return predicateNode;
	}

	public void setPredicate(PredicateNode newPredicate) {
		predicateNode = newPredicate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SET_COMPREHENSION(");

		sb.append(declarationList.stream().map(Object::toString).collect(Collectors.joining(",")));
		sb.append(",");
		sb.append(predicateNode);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		SetComprehensionNode that = (SetComprehensionNode) other;
		return this.predicateNode.equalAst(that.predicateNode)
				&& NodeUtil.equalAst(this.declarationList, that.declarationList);

	}
}
