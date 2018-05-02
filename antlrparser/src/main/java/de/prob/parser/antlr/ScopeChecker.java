package de.prob.parser.antlr;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.Node;
import de.prob.parser.ast.nodes.OperationNode;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;
import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;
import de.prob.parser.ast.nodes.substitution.AnySubstitutionNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ConditionSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ParallelSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SelectSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SequentialCompositionNode;
import de.prob.parser.ast.nodes.substitution.SingleAssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SkipSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import de.prob.parser.ast.visitors.FormulaAndSubstitutionVisitor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ScopeChecker {
	final LinkedList<LinkedHashMap<String, DeclarationNode>> scopeTable = new LinkedList<>();
	final LinkedHashMap<Node, DeclarationNode> declarationReferences = new LinkedHashMap<>();
	private MachineNode machineNode;

	public ScopeChecker(MachineNode machineNode) {
		this.machineNode = machineNode;
		check();
	}

	private void check() {

		if (machineNode.getProperties() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			new FormulaScopeChecker().visitPredicateNode(machineNode.getProperties(), null);
		}

		if (machineNode.getInvariant() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			new FormulaScopeChecker().visitPredicateNode(machineNode.getInvariant(), null);
		}

		if (machineNode.getInitialisation() != null) {
			scopeTable.clear();
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			new FormulaScopeChecker().visitSubstitutionNode(machineNode.getInitialisation(), null);
		}

		for (OperationNode op : machineNode.getOperations()) {
			createNewScope(machineNode.getConstants());
			createNewScope(machineNode.getVariables());
			new FormulaScopeChecker().visitSubstitutionNode(op.getSubstitution(), null);
		}

	}

	private void createNewScope(List<DeclarationNode> list) {
		LinkedHashMap<String, DeclarationNode> scope = new LinkedHashMap<>();
		for (DeclarationNode declarationNode : list) {
			scope.put(declarationNode.getName(), declarationNode);
		}
		this.scopeTable.add(scope);
	}

	class FormulaScopeChecker implements FormulaAndSubstitutionVisitor<Void, Void> {

		@Override
		public Void visitExprOperatorNode(ExpressionOperatorNode node, Void expected) {
			for (ExprNode exprNode : node.getExpressionNodes()) {
				visitExprNode(exprNode, expected);
			}
			return null;
		}

		@Override
		public Void visitIdentifierExprNode(IdentifierExprNode node, Void expected) {
			DeclarationNode declarationNode = lookUpIdentifier(node.getName(), node);
			node.setDeclarationNode(declarationNode);
			return null;
		}

		@Override
		public Void visitCastPredicateExpressionNode(CastPredicateExpressionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitNumberNode(NumberNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitQuantifiedExpressionNode(QuantifiedExpressionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitSetComprehensionNode(SetComprehensionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitIdentifierPredicateNode(IdentifierPredicateNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitPredicateOperatorNode(PredicateOperatorNode node, Void expected) {
			for (PredicateNode pred : node.getPredicateArguments()) {
				visitPredicateNode(pred, expected);
			}
			return null;
		}

		@Override
		public Void visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, Void expected) {
			for (ExprNode exprNode : node.getExpressionNodes()) {
				visitExprNode(exprNode, expected);
			}
			return null;
		}

		@Override
		public Void visitQuantifiedPredicateNode(QuantifiedPredicateNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitSkipSubstitutionNode(SkipSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitIfSubstitutionNode(IfSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitConditionSubstitutionNode(ConditionSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitAnySubstitution(AnySubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitSelectSubstitutionNode(SelectSubstitutionNode node, Void expected) {
			for (PredicateNode con : node.getConditions()) {
				visitPredicateNode(con, expected);
			}
			for (SubstitutionNode sub : node.getSubstitutions()) {
				visitSubstitutionNode(sub, expected);
			}

			if (node.getElseSubstitution() != null) {
				visitSubstitutionNode(node.getElseSubstitution(), expected);
			}
			return null;
		}

		@Override
		public Void visitSingleAssignSubstitution(SingleAssignSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitParallelSubstitutionNode(ParallelSubstitutionNode node, Void expected) {
			for (SubstitutionNode sub : node.getSubstitutions()) {
				visitSubstitutionNode(sub, expected);
			}
			return null;
		}

		@Override
		public Void visitSequentialCompositionNode(SequentialCompositionNode node, Void expected) {
			for (SubstitutionNode sub : node.getSubstitutions()) {
				visitSubstitutionNode(sub, expected);
			}
			return null;
		}

		@Override
		public Void visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node, Void expected) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Void visitAssignSubstitutionNode(AssignSubstitutionNode node, Void expected) {
			for (ExprNode expr : node.getLeftSide()) {
				visitExprNode(expr, expected);
			}
			for (ExprNode expr : node.getRightSide()) {
				visitExprNode(expr, expected);
			}
			return null;
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
