package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetDeclarationNode;
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
import de.prob.parser.ast.nodes.substitution.SubstitutionIdentifierCallNode;
import de.prob.parser.ast.visitors.generic.ASTVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MachineContex {
	private final LinkedList<LinkedHashMap<String, DeclarationNode>> scopeTable = new LinkedList<>();
	private final LinkedHashMap<Node, DeclarationNode> declarationReferences = new LinkedHashMap<>();
	private final Map<String, OperationNode> operationsInScope = new HashMap<>();
	private MachineNode machineNode;

	public MachineContex(MachineNode machineNode) {
		this(machineNode, new ArrayList<>());
	}

	public MachineContex(MachineNode machineNode, List<MachineContex> scopeList) {
		this.machineNode = machineNode;
		check(scopeList);
	}

	public MachineNode getMachineNode() {
		return this.machineNode;
	}

	private void check(List<MachineContex> scopeList) {
		FormulaScopeChecker formulaScopeChecker = new FormulaScopeChecker();

		if (machineNode.getProperties() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope2(getConstants(scopeList));
			createNewScope3(machineNode.getEnumaratedSets());
			formulaScopeChecker.visitPredicateNode(machineNode.getProperties());
		}

		if (machineNode.getInvariant() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			createNewScope3(machineNode.getEnumaratedSets());
			formulaScopeChecker.visitPredicateNode(machineNode.getInvariant());
		}

		if (machineNode.getInitialisation() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			createNewScope3(machineNode.getEnumaratedSets());
			formulaScopeChecker.visitSubstitutionNode(machineNode.getInitialisation());
		}

		addOperationsToScope(scopeList);
		for (OperationNode op : machineNode.getOperations()) {
			createNewScope(op.getParams());
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			createNewScope(op.getOutputParams());
			createNewScope3(machineNode.getEnumaratedSets());
			formulaScopeChecker.visitSubstitutionNode(op.getSubstitution());
		}

	}

	private void addOperationsToScope(List<MachineContex> scopeList) {
		for (MachineContex ct : scopeList) {
			for (OperationNode opNode : ct.machineNode.getOperations()) {
				operationsInScope.put(opNode.getName(), opNode);
			}
		}
	}

	private List<List<DeclarationNode>> getConstants(List<MachineContex> scopeList) {
		List<List<DeclarationNode>> result = new ArrayList<>();
		for (MachineContex ct : scopeList) {
			result.add(ct.machineNode.getConstants());
		}
		return result;
	}

	private void createNewScope2(List<List<DeclarationNode>> list) {
		for (List<DeclarationNode> l : list) {
			createNewScope(l);
		}
	}

	private void createNewScope(List<DeclarationNode> list) {
		LinkedHashMap<String, DeclarationNode> scope = new LinkedHashMap<>();
		for (DeclarationNode declarationNode : list) {
			scope.put(declarationNode.getName(), declarationNode);
		}
		this.scopeTable.add(scope);
	}

	private void createNewScope3(List<EnumeratedSetDeclarationNode> list) {
		LinkedHashMap<String, DeclarationNode> scope = new LinkedHashMap<>();
		for (EnumeratedSetDeclarationNode declarationNode : list) {
			scope.put(declarationNode.getSetDeclaration().getName(), declarationNode.getSetDeclaration());
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
		public void visitSubstitutionIdentifierCallNode(SubstitutionIdentifierCallNode node) {
			List<String> names = node.getNames();
			String opName = names.get(names.size() - 1);
			if (operationsInScope.containsKey(opName)) {
				node.setOperationsNode(operationsInScope.get(opName));
			} else {
				throw new RuntimeException("Unkown operation name: " + opName);
			}
			for (ExprNode arg : node.getArguments()) {
				visitExprNode(arg);
			}
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
