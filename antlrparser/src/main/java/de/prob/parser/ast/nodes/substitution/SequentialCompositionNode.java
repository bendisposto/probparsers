package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.NodeUtil;

public class SequentialCompositionNode extends ListSubstitutionNode {

	private List<SubstitutionNode> substitutions;

	public SequentialCompositionNode(SourceCodePosition sourceCodePosition, List<SubstitutionNode> substitutions) {
		super(sourceCodePosition, substitutions);
	}

	@Override
	public boolean equalAst(Node other) {
		return NodeUtil.isSameClass(this, other)
				&& NodeUtil.equalAst(substitutions, ((SequentialCompositionNode) other).substitutions);

	}

}
