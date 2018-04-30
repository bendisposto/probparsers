package de.prob.parser.ast.nodes;

import java.util.List;

public class ParallelSubstitutionNode extends ListSubstitutionNode {

    private List<SubstitutionNode> substitutions;

    public ParallelSubstitutionNode(List<SubstitutionNode> substitutions) {
        super(substitutions);
    }

    @Override
    public boolean equalAst(Node other) {
        return NodeUtil.isSameClass(this, other)
                && NodeUtil.equalAst(substitutions, ((ParallelSubstitutionNode) other).substitutions);

    }
}
