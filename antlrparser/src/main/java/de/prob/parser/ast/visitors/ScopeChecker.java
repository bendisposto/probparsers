package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;
import de.prob.parser.ast.nodes.substitution.AnySubstitutionNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.visitors.generic.ASTVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ScopeChecker {
	private final LinkedList<LinkedHashMap<String, DeclarationNode>> scopeTable = new LinkedList<>();
	private final LinkedHashMap<Node, DeclarationNode> declarationReferences = new LinkedHashMap<>();
	private MachineNode machineNode;

	public ScopeChecker(MachineNode machineNode) {
		this.machineNode = machineNode;
		check();
	}

	private void check() {
		FormulaScopeChecker formulaScopeChecker = new FormulaScopeChecker();

		if (machineNode.getProperties() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			formulaScopeChecker.visitPredicateNode(machineNode.getProperties());
		}

		if (machineNode.getInvariant() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			formulaScopeChecker.visitPredicateNode(machineNode.getInvariant());
		}

		if (machineNode.getInitialisation() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			formulaScopeChecker.visitSubstitutionNode(machineNode.getInitialisation());
		}

		for (OperationNode op : machineNode.getOperations()) {
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			formulaScopeChecker.visitSubstitutionNode(op.getSubstitution());
		}

	}

	private void createNewScope(List<DeclarationNode> list) {
		LinkedHashMap<String, DeclarationNode> scope = new LinkedHashMap<>();
		for (DeclarationNode declarationNode : list) {
			scope.put(declarationNode.getName(), declarationNode);
		}
		this.scopeTable.add(scope);
	}

	class FormulaScopeChecker extends ASTVisitor {

		@Override
		public void visitIdentifierExprNode(IdentifierExprNode node) {
			DeclarationNode declarationNode = lookUpIdentifier(node.getName(), node);
			node.setDeclarationNode(declarationNode);

		}

		@Override
		public void visitQuantifiedExpressionNode(QuantifiedExpressionNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitSetComprehensionNode(SetComprehensionNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitIdentifierPredicateNode(IdentifierPredicateNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitQuantifiedPredicateNode(QuantifiedPredicateNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitAnySubstitution(AnySubstitutionNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node) {
			// TODO Auto-generated method stub

		}

		@Override
		public void visitAssignSubstitutionNode(AssignSubstitutionNode node) {
			for (ExprNode expr : node.getLeftSide()) {
				visitExprNode(expr);
			}
			for (ExprNode expr : node.getRightSide()) {
				visitExprNode(expr);
			}

		}

	}

	public DeclarationNode lookUpIdentifier(String name, Node node) {
		for (int i = scopeTable.size() - 1; i >= 0; i--) {
			LinkedHashMap<String, DeclarationNode> map = scopeTable.get(i);
			if (map.containsKey(name)) {
				DeclarationNode declarationNode = map.get(name);
				addDeclarationReference(node, declarationNode);
				return declarationNode;
			}
		}
		throw new RuntimeException("Identifier not found: " + name);
	}

	public void addDeclarationReference(Node identifierToken, DeclarationNode declarationToken) {
		this.declarationReferences.put(identifierToken, declarationToken);
	}

}
