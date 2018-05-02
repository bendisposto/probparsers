package de.prob.parser.ast.nodes.substitution;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.expression.ExprNode;

public class SubstitutionIdentifierCallNode extends SubstitutionNode {

	private List<String> names;
	private List<ExprNode> arguments;

	public SubstitutionIdentifierCallNode(SourceCodePosition sourceCodePosition, List<String> names,
			List<ExprNode> arguments) {
		super(sourceCodePosition);
		this.names = names;
		this.arguments = arguments;

	}

	public List<String> getNames() {
		return names;
	}

	public List<ExprNode> getArguments() {
		return this.arguments;
	}

}
