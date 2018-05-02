package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

public class ParallelSubstitutionNode extends ListSubstitutionNode {

	public ParallelSubstitutionNode(SourceCodePosition sourceCodePosition, List<SubstitutionNode> substitutions) {
		super(sourceCodePosition, substitutions);
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other)
				&& NodeUtil.equalAst(super.getSubstitutions(), ((ParallelSubstitutionNode) other).getSubstitutions());

	}

}
