package de.prob.parser.ast.nodes;

import java.util.List;

public class SequentialCompositionNode extends ListSubstitutionNode {

    private List<SubstitutionNode> substitutions;

    public SequentialCompositionNode(List<SubstitutionNode> substitutions) {
        super(substitutions);
    }

    @Override
    public boolean equalAst(Node other) {
        return NodeUtil.isSameClass(this, other)
                && NodeUtil.equalAst(substitutions, ((SequentialCompositionNode) other).substitutions);

    }

}
