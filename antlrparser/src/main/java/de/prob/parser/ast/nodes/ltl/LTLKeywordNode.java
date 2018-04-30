package de.prob.parser.ast.nodes.ltl;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

public class LTLKeywordNode extends LTLNode {

	public enum Kind {
		TRUE, FALSE
	}

	private Kind kind;

	public LTLKeywordNode(SourceCodePosition sourceCodePosition, Kind kind) {
		super(sourceCodePosition);
		this.kind = kind;
	}

	public Kind getKind() {
		return this.kind;
	}

	public String toString() {
		return this.kind.toString();
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other) && this.kind.equals(((LTLKeywordNode) other).kind);

	}

}
