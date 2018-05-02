package de.prob.parser.ast.nodes;

import de.prob.parser.ast.SourceCodePosition;
import de.prob.parser.ast.nodes.ltl.LTLFormula;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;

import java.util.ArrayList;
import java.util.List;

public class MachineNode extends Node {

	private List<DeclarationNode> constants;
	private List<DeclarationNode> variables;
	private PredicateNode properties;
	private PredicateNode invariant;
	private SubstitutionNode initialisation;
	private List<OperationNode> operations = new ArrayList<>();
	private List<String> warnings = new ArrayList<>();
	private List<EnumeratedSetDeclarationNode> setEnumerations = new ArrayList<>();
	private List<DeclarationNode> deferredSets = new ArrayList<>();
	private List<LTLFormula> ltlFormulas = new ArrayList<>();

	public List<DeclarationNode> getVariables() {
		return variables;
	}

	public void setVariables(List<DeclarationNode> variables) {
		this.variables = variables;
	}

	public MachineNode(SourceCodePosition sourceCodePosition) {
		super(sourceCodePosition);
	}

	public List<DeclarationNode> getConstants() {
		return constants;
	}

	public void setConstants(List<DeclarationNode> constants) {
		this.constants = constants;
	}

	public void addLTLFormula(LTLFormula ltlFormula) {
		this.ltlFormulas.add(ltlFormula);
	}

	public SubstitutionNode getInitialisation() {
		return initialisation;
	}

	public void setInitialisation(SubstitutionNode initialisation) {
		this.initialisation = initialisation;
	}

	public List<OperationNode> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationNode> operations) {
		this.operations = operations;
	}

	public PredicateNode getInvariant() {
		return invariant;
	}

	public void setInvariant(PredicateNode invariant) {
		this.invariant = invariant;
	}

	public PredicateNode getProperties() {
		return properties;
	}

	public void setProperties(PredicateNode properties) {
		this.properties = properties;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings.addAll(warnings);
	}

	public List<String> getWarnings() {
		return this.warnings;
	}

	public void addSetEnumeration(EnumeratedSetDeclarationNode setEnumeration) {
		this.setEnumerations.add(setEnumeration);
	}

	public List<EnumeratedSetDeclarationNode> getEnumaratedSets() {
		return new ArrayList<>(this.setEnumerations);
	}

	public void addDeferredSet(DeclarationNode setDeclNode) {
		this.deferredSets.add(setDeclNode);
	}

	public List<DeclarationNode> getDeferredSets() {
		return new ArrayList<>(this.deferredSets);
	}

	public List<LTLFormula> getLTLFormulas() {
		return new ArrayList<>(this.ltlFormulas);
	}


	public void addOperation(OperationNode operationNode) {
		this.operations.add(operationNode);
	}
}
