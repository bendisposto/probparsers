package de.prob.parser.ast.nodes.substitution;

import java.util.HashSet;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

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
