package de.prob.parser.ast.nodes.substitution;


import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.Node;

public abstract class SubstitutionNode extends Node {

	public SubstitutionNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}
}
