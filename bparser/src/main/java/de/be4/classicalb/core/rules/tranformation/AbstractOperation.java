package de.be4.classicalb.core.rules.tranformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;
import de.be4.classicalb.core.rules.project.Reference;

public abstract class AbstractOperation {

	private final TIdentifierLiteral name;
	private final String fileName; // can be null
	private final String machineName;
	private final List<Reference> machineReferences;
	private final List<AIdentifierExpression> dependsOnRuleList = new ArrayList<>();
	private final List<AIdentifierExpression> dependsOnComputationList = new ArrayList<>();
	private PPredicate activationPredicate;
	private Set<AbstractOperation> dependencies;
	protected Map<String, AIdentifierExpression> readMap = new HashMap<>();
	protected Map<String, TIdentifierLiteral> functionCallMap = new HashMap<>();

	public AbstractOperation(TIdentifierLiteral name, String fileName, String machineName,
			List<Reference> machineReferences2) {
		this.name = name;
		this.fileName = fileName;
		this.machineName = machineName;
		this.machineReferences = machineReferences2;
	}

	public String getFileName() {
		return this.fileName;
	}

	public List<AIdentifierExpression> getDependsOnRulesList() {
		return this.dependsOnRuleList;
	}

	public List<AIdentifierExpression> getDependsOnComputationList() {
		return this.dependsOnComputationList;
	}

	public List<TIdentifierLiteral> getFunctionCalls() {
		return new ArrayList<>(this.functionCallMap.values());
	}

	public void addAllRuleDependencies(List<AIdentifierExpression> list) {
		this.dependsOnRuleList.addAll(list);
	}

	public void addAllComputationDependencies(List<AIdentifierExpression> list) {
		this.dependsOnComputationList.addAll(list);
	}

	public void setActivationPredicate(PPredicate predicate) {
		this.activationPredicate = predicate;
	}

	public PPredicate getActivationPredicate() {
		return this.activationPredicate;
	}

	public String getName() {
		return this.name.getText();
	}

	public TIdentifierLiteral getNameLiteral() {
		return this.name;
	}

	public void setDependencies(Set<AbstractOperation> dependencies) {
		this.dependencies = dependencies;
	}

	public Set<AbstractOperation> getDependencies() {
		return this.dependencies;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public void addReadVariable(AIdentifierExpression identifier) {
		LinkedList<TIdentifierLiteral> list = identifier.getIdentifier();
		String name = list.get(0).getText();
		// storing the first occurrence an identifier read
		if (!readMap.containsKey(name)) {
			readMap.put(name, identifier);
		}
	}

	public void addFunctionCall(TIdentifierLiteral identifier) {
		String name = identifier.getText();
		// storing the first occurrence an identifier read
		if (!functionCallMap.containsKey(name)) {
			functionCallMap.put(name, identifier);
		}
	}

	public AIdentifierExpression getVariableReadByName(String name) {
		return this.readMap.get(name);
	}

	public HashSet<String> getReadVariables() {
		return new HashSet<>(this.readMap.keySet());
	}

	public List<String> getMachineReferencesAsString() {
		List<String> list = new ArrayList<>();
		for (Reference reference : this.machineReferences) {
			list.add(reference.getName());
		}
		return list;
	}

	public String getMachineName() {
		return this.machineName;
	}
}
