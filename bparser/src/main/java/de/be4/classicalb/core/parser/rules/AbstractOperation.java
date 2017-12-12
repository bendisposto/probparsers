package de.be4.classicalb.core.parser.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.be4.classicalb.core.parser.node.AIdentifierExpression;
import de.be4.classicalb.core.parser.node.PPredicate;
import de.be4.classicalb.core.parser.node.TIdentifierLiteral;

public abstract class AbstractOperation implements Comparable<AbstractOperation> {

	private final TIdentifierLiteral name;
	private final String fileName; // can be null
	private final String machineName;
	private final List<RulesMachineReference> machineReferences;
	private final List<AIdentifierExpression> dependsOnRuleList = new ArrayList<>();
	private final List<AIdentifierExpression> dependsOnComputationList = new ArrayList<>();
	private final List<String> tags = new ArrayList<>();
	private PPredicate activationPredicate;
	private PPredicate postconditionPredicate;
	private Set<AbstractOperation> transitiveDependencies;
	private List<ComputationOperation> implicitDependenciesToComputations;
	private AIdentifierExpression replacesIdentifier;

	protected Map<String, AIdentifierExpression> readMap = new HashMap<>();
	protected Map<String, TIdentifierLiteral> functionCallMap = new HashMap<>();
	private Set<AbstractOperation> requiredDependencies;

	public AbstractOperation(TIdentifierLiteral name, String fileName, String machineName,
			List<RulesMachineReference> machineReferences2) {
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

	public void setPostcondition(PPredicate predicate) {
		this.postconditionPredicate = predicate;
	}

	public PPredicate getPostconditionPredicate() {
		return this.postconditionPredicate;
	}

	public String getName() {
		return this.name.getText();
	}

	public void addTags(List<String> list) {
		this.tags.addAll(list);
	}

	public List<String> getTags() {
		return this.tags;
	}

	public TIdentifierLiteral getNameLiteral() {
		return this.name;
	}

	public void setTransitiveDependencies(Set<AbstractOperation> dependencies) {
		this.transitiveDependencies = dependencies;
	}

	public Set<AbstractOperation> getTransitiveDependencies() {
		if(this.transitiveDependencies == null) {
			return null;
		}
		return new HashSet<>(this.transitiveDependencies);
	}

	public Set<AbstractOperation> getRequiredDependencies() {
		if (this.requiredDependencies == null) {
			this.requiredDependencies = new HashSet<>();
			HashSet<AIdentifierExpression> aIdentifierSet = new HashSet<>();
			aIdentifierSet.addAll(this.dependsOnComputationList);
			aIdentifierSet.addAll(this.dependsOnRuleList);
			HashSet<String> directDependencies = new HashSet<>();
			for (AIdentifierExpression aIdentifier : aIdentifierSet) {
				directDependencies.add(aIdentifier.getIdentifier().get(0).getText());
			}
			for (AbstractOperation abstractOperation : this.transitiveDependencies) {
				String opName = abstractOperation.getName();
				if (this.implicitDependenciesToComputations.contains(abstractOperation)
						|| directDependencies.contains(opName)) {
					requiredDependencies.add(abstractOperation);
				} else if (functionCallMap.containsKey(opName)) {
					requiredDependencies.addAll(abstractOperation.getRequiredDependencies());
				}
			}
		}
		return new HashSet<>(requiredDependencies);
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

	public Set<String> getReadVariables() {
		return new HashSet<>(this.readMap.keySet());
	}

	public List<String> getMachineReferencesAsString() {
		List<String> list = new ArrayList<>();
		for (RulesMachineReference reference : this.machineReferences) {
			list.add(reference.getName());
		}
		return list;
	}

	public boolean replacesOperation() {
		return this.replacesIdentifier != null;
	}

	public AIdentifierExpression getReplacesIdentifier() {
		return this.replacesIdentifier;
	}

	public String getMachineName() {
		return this.machineName;
	}

	public void addReplacesIdentifier(AIdentifierExpression idExpr) {
		this.replacesIdentifier = idExpr;
	}

	public void setImplicitComputationDependencies(List<ComputationOperation> inferredDependenciesToComputations) {
		implicitDependenciesToComputations = inferredDependenciesToComputations;
	}

	public List<TIdentifierLiteral> getImplicitDependenciesToComputations() {
		List<TIdentifierLiteral> result = new ArrayList<>();
		for (ComputationOperation comp : implicitDependenciesToComputations) {
			TIdentifierLiteral nameLiteral = comp.getNameLiteral();
			result.add(nameLiteral);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T extends AbstractOperation> Set<T> filterOperations(Collection<AbstractOperation> in,
			Class<T> clazz) {
		Set<T> set = new HashSet<>();
		for (AbstractOperation abstractOperation : in) {
			if (abstractOperation.getClass() == clazz) {
				set.add((T) abstractOperation);
			}
		}
		return set;
	}

	public List<AbstractOperation> getSortedListOfTransitiveDependencies() {
		List<AbstractOperation> result = new ArrayList<>(getTransitiveDependencies());
		Collections.sort(result);
		return result;
	}

	@Override
	public int compareTo(AbstractOperation other) {
		if (this.getTransitiveDependencies().contains(other)) {
			return 1;
		} else if (other.getTransitiveDependencies().contains(this)) {
			return 0;
		} else {
			return 0;
		}
	}

}
