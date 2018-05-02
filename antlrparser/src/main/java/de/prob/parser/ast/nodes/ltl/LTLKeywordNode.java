package de.prob.parser.ast.nodes.ltl;

import de.prob.parser.ast.SourceCodePosition;

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

}
