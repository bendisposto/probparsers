package de.prob.parser.ast.nodes;

public interface OperatorNode<T> {
	T getOperator();

	void setOperator(T operator);
}
