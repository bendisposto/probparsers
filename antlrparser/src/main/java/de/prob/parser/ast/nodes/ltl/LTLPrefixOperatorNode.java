package de.prob.parser.ast.nodes.ltl;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

public class LTLPrefixOperatorNode extends LTLNode {

	public enum Kind {
		GLOBALLY, FINALLY, NEXT, NOT
	}

	private Kind kind;
	private LTLNode argument;

	public LTLPrefixOperatorNode(SourceCodePosition sourceCodePosition, Kind kind, LTLNode node) {
		super(sourceCodePosition);
		this.kind = kind;
		this.argument = node;
	}

	public Kind getKind() {
		return this.kind;
	}

	public LTLNode getArgument() {
		return this.argument;
	}

	public void setLTLNode(LTLNode argument) {
		this.argument = argument;
	}

	@Override
	public String toString() {
		return this.kind + "(" + this.argument + ")";
	}

	@Override
	public boolean equalAst(Node other) {
		if (!NodeUtil.isSameClass(this, other)) {
			return false;
		}

		LTLPrefixOperatorNode that = (LTLPrefixOperatorNode) other;
		return this.kind.equals(that.kind) && this.argument.equalAst(that.argument);
	}
}
