package de.prob.parser.ast.visitors;

import de.prob.parser.antlr.ScopeException;
import de.prob.parser.antlr.VisitorException;
import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetDeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
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
import de.prob.parser.ast.nodes.substitution.OperationCallSubstitutionNode;
import de.prob.parser.ast.visitors.generic.ASTVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MachineScopeChecker {
	private final LinkedList<LinkedHashMap<String, DeclarationNode>> scopeTable = new LinkedList<>();
	private final LinkedHashMap<Node, DeclarationNode> declarationReferences = new LinkedHashMap<>();
	private final Map<String, OperationNode> operationsInScope = new HashMap<>();

	private MachineNode machineNode;

	private List<MachineNode> machinesInScope;
	private List<DeclarationNode> setsInScope;
	private List<DeclarationNode> constantsInScope;
	private List<DeclarationNode> variablesInScope;

	public MachineScopeChecker(MachineNode machineNode) throws ScopeException {
		this.machineNode = machineNode;
		try {
			check();
		} catch (VisitorException e) {
			throw (ScopeException) e.getCause();
		}

	}

	public MachineNode getMachineNode() {
		return this.machineNode;
	}

	private void check() {
		FormulaScopeChecker formulaScopeChecker = new FormulaScopeChecker();

		if (machineNode.getProperties() != null) {
			scopeTable.clear();
			createNewScope(getSetsInScope());
			createNewScope(getConstantsInScope());
			formulaScopeChecker.visitPredicateNode(machineNode.getProperties());
		}

		if (machineNode.getInvariant() != null) {
			scopeTable.clear();
			createNewScope(getSetsInScope());
			createNewScope(getConstantsInScope());
			createNewScope(getVariablesInScope());
			formulaScopeChecker.visitPredicateNode(machineNode.getInvariant());
		}

		if (machineNode.getInitialisation() != null) {
			scopeTable.clear();
			createNewScope(getSetsInScope());
			createNewScope(getConstantsInScope());
			createNewScope(getVariablesInScope());
			formulaScopeChecker.visitSubstitutionNode(machineNode.getInitialisation());
		}

		addOperationsToScope(machineNode, true);
		for (OperationNode op : machineNode.getOperations()) {
			createNewScope(getSetsInScope());
			createNewScope(getConstantsInScope());
			createNewScope(getVariablesInScope());
			createNewScope(op.getParams());
			createNewScope(op.getOutputParams());
			formulaScopeChecker.visitSubstitutionNode(op.getSubstitution());
		}
	}

	private void addOperationsToScope(MachineNode mNode, boolean first) {
		if (!first) {
			for (OperationNode op : mNode.getOperations()) {
				this.operationsInScope.put(op.getName(), op);
			}
		}
		for (MachineReferenceNode ref : mNode.getMachineReferences()) {
			MachineNode refMachine = ref.getMachineNode();
			if (ref.getType() == MachineReferenceNode.Kind.EXTENDED
					|| ref.getType() == MachineReferenceNode.Kind.INCLUDED) {
				addOperationsToScope(refMachine, false);
			}
		}
	}

	public List<DeclarationNode> getConstantsInScope() {
		if (constantsInScope == null) {
			constantsInScope = getConstantsInScope(getMachinesInScope());
		}
		return constantsInScope;
	}

	public List<DeclarationNode> getConstantsInScope(List<MachineNode> list) {
		List<DeclarationNode> result = new ArrayList<>();
		for (MachineNode machine : list) {
			result.addAll(machine.getConstants());
		}
		return result;
	}

	public List<DeclarationNode> getVariablesInScope() {
		if (variablesInScope == null) {
			variablesInScope = getVariablesInScope(getMachinesInScope());
		}
		return variablesInScope;
	}

	public List<DeclarationNode> getVariablesInScope(List<MachineNode> list) {
		List<DeclarationNode> result = new ArrayList<>();
		for (MachineNode machine : list) {
			result.addAll(machine.getVariables());
		}
		return result;
	}

	private List<DeclarationNode> getSetsInScope() {
		if (this.setsInScope == null) {
			setsInScope = getSetsInScope(getMachinesInScope());
		}
		return setsInScope;
	}

	private List<DeclarationNode> getSetsInScope(List<MachineNode> list) {
		List<DeclarationNode> result = new ArrayList<>();
		for (MachineNode machine : list) {
			for (EnumeratedSetDeclarationNode enumSet : machine.getEnumaratedSets()) {
				result.add(enumSet.getSetDeclarationNode());
				result.addAll(enumSet.getElements());
			}
			result.addAll(machine.getDeferredSets());
		}
		return result;
	}

	private List<MachineNode> getMachinesInScope() {
		if (this.machinesInScope == null) {
			machinesInScope = new ArrayList<>();
			machinesInScope.add(this.machineNode);
			for (MachineReferenceNode ref : this.machineNode.getMachineReferences()) {
				MachineNode refMachine = ref.getMachineNode();
				machinesInScope.add(refMachine);
				if (ref.getType() == MachineReferenceNode.Kind.EXTENDED
						|| ref.getType() == MachineReferenceNode.Kind.INCLUDED) {
					machinesInScope.addAll(getMachinesInScope(refMachine));
				}
			}
		}
		return machinesInScope;

	}

	private List<MachineNode> getMachinesInScope(MachineNode mNode) {
		List<MachineNode> result = new ArrayList<>();
		result.add(mNode);
		for (MachineReferenceNode ref : mNode.getMachineReferences()) {
			MachineNode refMachine = ref.getMachineNode();
			if (ref.getType() == MachineReferenceNode.Kind.EXTENDED
					|| ref.getType() == MachineReferenceNode.Kind.INCLUDED) {
				result.addAll(getMachinesInScope(refMachine));
			}
		}
		return result;
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
		public void visitSubstitutionIdentifierCallNode(OperationCallSubstitutionNode node) {
			List<String> names = node.getNames();
			String opName = names.get(names.size() - 1);
			if (operationsInScope.containsKey(opName)) {
				node.setOperationsNode(operationsInScope.get(opName));
			} else {
				throw new VisitorException(new ScopeException("Unknown operation name: " + opName));
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
		throw new VisitorException(new ScopeException("Unknown identifier: " + name));
	}

	public void addDeclarationReference(Node identifierToken, DeclarationNode declarationToken) {
		this.declarationReferences.put(identifierToken, declarationToken);
	}

}
