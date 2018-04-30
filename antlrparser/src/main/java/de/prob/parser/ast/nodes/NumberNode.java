package de.prob.parser.ast.nodes;


import de.prob.parser.ast.SourceCodePosition;

import java.math.BigInteger;

public class NumberNode extends ExprNode {

	private final BigInteger value;

	public NumberNode(SourceCodePosition sourceCodePosition, BigInteger value) {
		super(sourceCodePosition);
		this.value = value;
	}

	public BigInteger getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other) && this.value.equals(((NumberNode) other).value);
	}
}
