package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public class MachineReferenceNode extends Node {

	private final String name;
	private String prefix;
	private final Kind kind;
	private MachineNode machineNode;

	public enum Kind {
		SEEN, INCLUDED, EXTENDED, USED
	}

	public MachineReferenceNode(SourceCodePosition sourceCodePosition, String machineName, Kind kind, String prefix,
			boolean explicitly) {
		super(sourceCodePosition);
		this.name = machineName;
		this.prefix = prefix;
		this.kind = kind;
	}

	public String getMachineName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setMachineNode(MachineNode machineNode) {
		this.machineNode = machineNode;
	}

	public MachineNode getMachineNode() {
		return this.machineNode;
	}

	public Kind getType() {
		return this.kind;
	}
}
