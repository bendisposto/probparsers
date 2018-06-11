package de.prob.parser.antlr;

import java.util.LinkedHashMap;
import java.util.List;

import de.prob.parser.ast.nodes.MachineNode;

public class BProject {
	private final MachineNode mainMachineNode;
	private final String mainMachineName;
	private final LinkedHashMap<String, MachineNode> referencedMachines = new LinkedHashMap<>();

	public BProject(MachineNode mainMachineNode, List<MachineNode> machineNodeList) {
		this.mainMachineNode = mainMachineNode;
		this.mainMachineName = mainMachineNode.getName();
		for (MachineNode node : machineNodeList) {
			referencedMachines.put(node.getName(), node);
		}
	}

	public MachineNode getMainMachine() {
		return this.mainMachineNode;
	}

	public MachineNode getMachineNode(String machineName) {
		if (machineName.equals(mainMachineName)) {
			return mainMachineNode;
		} else if (referencedMachines.containsKey(machineName)) {
			return referencedMachines.get(machineName);
		} else {
			throw new RuntimeException("Unknown operation name: " + machineName);
		}
	}

}
