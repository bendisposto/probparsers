package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;

public class DeclarationNode extends TypedNode {
	public enum Kind {
		VARIABLE, CONSTANT, ENUMERATED_SET, ENUMERATED_SET_ELEMENT, DEFERRED_SET, OP_OUTPUT_PARAMETER, OP_INPUT_PARAMETER, SUBSTITUION_IDENTIFIER
	}

	private final String name;
	private final Kind kind;
	private final MachineNode surroundingMachineNode;

	public DeclarationNode(SourceCodePosition sourceCodePosition, String name, Kind kind, MachineNode machineNode) {
		super(sourceCodePosition);
		this.name = name;
		this.kind = kind;
		this.surroundingMachineNode = machineNode;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public Kind getKind() {
		return this.kind;
	}

	public MachineNode getSurroundingMachineNode() {
		return this.surroundingMachineNode;
	}

	@Override
	public void removeChild(Node child) {
		// no child
	}

}
