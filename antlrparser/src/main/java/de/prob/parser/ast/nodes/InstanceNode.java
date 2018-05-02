package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public class InstanceNode extends Node {

	private final String name;
	private String prefix;

	public InstanceNode(SourceCodePosition sourceCodePosition, String machineName, String prefix) {
		super(sourceCodePosition);
		this.name = machineName;
		this.prefix = prefix;
	}

	public String getMachineName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
