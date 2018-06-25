package de.prob.parser.ast.nodes.substitution;

import java.util.ArrayList;
import java.util.List;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.expression.ExprNode;

public class OperationCallSubstitutionNode extends SubstitutionNode {

	private List<String> names;
	private List<ExprNode> arguments;
	private OperationNode operationNode;
	private List<ExprNode> assignedVariables;

	public OperationCallSubstitutionNode(SourceCodePosition sourceCodePosition, List<String> names,
			List<ExprNode> arguments) {
		this(sourceCodePosition, names, arguments, new ArrayList<>());
	}

	public OperationCallSubstitutionNode(SourceCodePosition sourceCodePosition, List<String> names,
			List<ExprNode> arguments, List<ExprNode> assignedVariables) {
		super(sourceCodePosition);
		this.names = names;
		this.arguments = arguments;
		this.assignedVariables = assignedVariables;
	}

	public List<String> getNames() {
		return names;
	}

	public List<ExprNode> getArguments() {
		return this.arguments;
	}

	public void setOperationsNode(OperationNode operationNode) {
		this.operationNode = operationNode;
	}

	public OperationNode getOperationNode() {
		return this.operationNode;
	}

	public List<ExprNode> getAssignedVariables() {
		return assignedVariables;
	}

}
