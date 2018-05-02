package de.prob.parser.ast.nodes.substitution;

import de.prob.parser.ast.SourceCodePosition;


public class SkipSubstitutionNode extends SubstitutionNode {

	public SkipSubstitutionNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

	public String toString() {
		return "skip";
	}
}
