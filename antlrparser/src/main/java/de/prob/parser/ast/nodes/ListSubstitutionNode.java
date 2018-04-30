package de.prob.parser.ast.nodes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ListSubstitutionNode extends SubstitutionNode {

    private List<SubstitutionNode> substitutions;

    public ListSubstitutionNode(List<SubstitutionNode> substitutions) {
        Set<DeclarationNode> set = new HashSet<>();
        for (SubstitutionNode sub : substitutions) {
            set.addAll(sub.getAssignedVariables());
        }
        super.setAssignedVariables(set);
        this.substitutions = substitutions;
    }

    public List<SubstitutionNode> getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(List<SubstitutionNode> substitutions) {
        this.substitutions = substitutions;
    }

}
