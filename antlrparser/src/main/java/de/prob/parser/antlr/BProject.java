package de.prob.parser.antlr;

import java.util.LinkedHashMap;
import java.util.List;

import de.prob.parser.ast.nodes.MachineNode;

public class BProject {
	private final LinkedHashMap<String, MachineNode> machinesMap = new LinkedHashMap<>();

	public BProject(List<MachineNode> machineNodeList) {
		for (MachineNode node : machineNodeList) {
			machinesMap.put(node.getName(), node);
		}
	}

	public MachineNode getMainMachine() {
		return this.machinesMap.entrySet().iterator().next().getValue();
	}

	public MachineNode getMachineNode(String machineName) {
		if (machinesMap.containsKey(machineName)) {
			return machinesMap.get(machineName);
		} else {
			throw new RuntimeException("Unknown operation name: " + machineName);
		}
	}

}
