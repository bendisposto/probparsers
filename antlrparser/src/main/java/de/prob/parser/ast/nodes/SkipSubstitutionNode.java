package de.prob.parser.ast.nodes;

import java.util.HashSet;

import de.prob.parser.ast.SourceCodePosition;

public class SkipSubstitutionNode extends SubstitutionNode {

	public SkipSubstitutionNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
		setAssignedVariables(new HashSet<>());
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other);
	}

	public String toString() {
		return "skip";
	}
}
