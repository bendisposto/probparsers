package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;

import java.util.List;

/**
 * Created by fabian on 13.10.18.
 */
public class ChoiceSubstitutionNode extends SubstitutionNode {

    private List<SubstitutionNode> substitutions;

    public ChoiceSubstitutionNode(SourceCodePosition sourceCodePosition, List<SubstitutionNode> substitutions) {
        super(sourceCodePosition);
        this.substitutions = substitutions;
    }

    public List<SubstitutionNode> getSubstitutions() {
        return substitutions;
    }
}
