package de.prob.parser.ast.nodes;

import java.util.List;

import de.prob.parser.ast.SourceCodePosition;

public class FormulaNode extends Node {

	public enum FormulaType {
		EXPRESSION_FORMULA, PREDICATE_FORMULA
	}

	private final FormulaType type;
	private List<DeclarationNode> implicitDeclarations;
	private Node formula;
	private List<String> warnings;

	public FormulaNode(SourceCodePosition sourceCodePosition, FormulaType type) {
		super(sourceCodePosition);
		this.type = type;
	}

	public void setImplicitDeclarations(List<DeclarationNode> implicitDeclarations) {
		this.implicitDeclarations = implicitDeclarations;
	}

	public List<DeclarationNode> getImplicitDeclarations() {
		return implicitDeclarations;
	}

	public Node getFormula() {
		return formula;
	}

	public void setFormula(Node formula) {
		this.formula = formula;
	}

	public FormulaType getFormulaType() {
		return type;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public List<String> getWarnings() {
		return this.warnings;
	}

}
