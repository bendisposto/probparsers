package de.hhu.stups.codegenerator;

import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.expression.QuantifiedExpressionNode;
import de.prob.parser.ast.nodes.expression.SetComprehensionNode;
import de.prob.parser.ast.nodes.ltl.LTLBPredicateNode;
import de.prob.parser.ast.nodes.ltl.LTLInfixOperatorNode;
import de.prob.parser.ast.nodes.ltl.LTLKeywordNode;
import de.prob.parser.ast.nodes.ltl.LTLPrefixOperatorNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;
import de.prob.parser.ast.nodes.predicate.IdentifierPredicateNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import de.prob.parser.ast.nodes.predicate.QuantifiedPredicateNode;
import de.prob.parser.ast.nodes.substitution.AnySubstitutionNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesSuchThatSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ChoiceSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ConditionSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.OperationCallSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SkipSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.VarSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.WhileSubstitutionNode;
import de.prob.parser.ast.visitors.AbstractVisitor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
* The code generator is implemented by using the visitor pattern
*/

public class MachineGenerator implements AbstractVisitor<String, Void> {

	private final TypeGenerator typeGenerator;

	private final ImportGenerator importGenerator;

	private final OperatorGenerator operatorGenerator;

	private final OperationGenerator operationGenerator;

	private final DeclarationGenerator declarationGenerator;

	private final ExpressionGenerator expressionGenerator;

	private final PredicateGenerator predicateGenerator;

	private final SubstitutionGenerator substitutionGenerator;

	private final IdentifierGenerator identifierGenerator;

	private final NameHandler nameHandler;

	private STGroup currentGroup;

	private MachineNode machineNode;

	private String addition;

	public MachineGenerator(GeneratorMode mode, boolean useBigInteger, Path addition) {
		this.currentGroup = CodeGeneratorUtils.getGroup(mode);
		if(addition != null) {
			try {
				this.addition = new String(Files.readAllBytes(addition));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.nameHandler = new NameHandler(currentGroup);
		this.identifierGenerator = new IdentifierGenerator(currentGroup, nameHandler);
		this.typeGenerator = new TypeGenerator(currentGroup, nameHandler);
		this.importGenerator = new ImportGenerator(currentGroup, nameHandler);
		this.declarationGenerator = new DeclarationGenerator(currentGroup, this, typeGenerator, importGenerator, nameHandler);
		this.predicateGenerator = new PredicateGenerator(currentGroup, this, nameHandler, importGenerator);
		this.expressionGenerator = new ExpressionGenerator(currentGroup, this, useBigInteger, nameHandler, importGenerator,
															declarationGenerator, identifierGenerator, typeGenerator);
		this.substitutionGenerator = new SubstitutionGenerator(currentGroup, this, nameHandler, typeGenerator,
																expressionGenerator, identifierGenerator);
		this.operatorGenerator = new OperatorGenerator(predicateGenerator, expressionGenerator);
		this.operationGenerator = new OperationGenerator(currentGroup, this, substitutionGenerator,
															declarationGenerator, identifierGenerator, nameHandler, typeGenerator);
	}

	/*
	* This function generates code for the whole machine with the given AST node.
	*/
	public String generateMachine(MachineNode node) {
		initialize(node);
		ST machine = currentGroup.getInstanceOf("machine");
		machine.add("addition", addition);
		machine.add("imports", importGenerator.getImports());
		machine.add("includedMachines", importGenerator.generateMachineImports(node));
		machine.add("machine", nameHandler.handle(node.getName()));
		generateBody(node, machine);
		return machine.render();
	}

	/*
	* This function initializes needed semantic information during code generation.
	*/
	private void initialize(MachineNode node) {
		this.machineNode = node;
		nameHandler.initialize(node);
		operationGenerator.mapOperationsToMachine(node);
	}

	/*
	* This function generates the whole body of a machine from the given AST node for the machine.
	*/
	private void generateBody(MachineNode node, ST machine) {
		machine.add("constants", declarationGenerator.generateConstants(node));
		machine.add("values", declarationGenerator.generateValues(node));
		machine.add("enums", declarationGenerator.generateEnumDeclarations(node));
		machine.add("sets", declarationGenerator.generateSetDeclarations(node));
		machine.add("declarations", declarationGenerator.visitDeclarations(node.getVariables()));
		machine.add("includes", declarationGenerator.generateIncludes(node));
		machine.add("initialization", substitutionGenerator.visitInitialization(node));
		machine.add("operations", operationGenerator.visitOperations(node.getOperations()));
	}

	@Override
	public String visitExprNode(ExprNode node, Void expected) {
		return expressionGenerator.visitExprNode(node);
	}

	@Override
	public String visitExprOperatorNode(ExpressionOperatorNode node, Void expected) {
		return expressionGenerator.visitExprOperatorNode(node);
	}

	@Override
	public String visitIdentifierExprNode(IdentifierExprNode node, Void expected) {
		return expressionGenerator.visitIdentifierExprNode(node);
	}

	@Override
	public String visitCastPredicateExpressionNode(CastPredicateExpressionNode node, Void expected) {
		return expressionGenerator.visitCastPredicateExpressionNode(node);
	}

	@Override
	public String visitNumberNode(NumberNode node, Void expected) {
		return expressionGenerator.visitNumberNode(node);
	}

	/*
	* Code is not generated from quantified expresions in the given subset of B
	*/
	@Override
	public String visitQuantifiedExpressionNode(QuantifiedExpressionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* Code is not generated from quantified predicates in the given subset of B
	*/
	@Override
	public String visitQuantifiedPredicateNode(QuantifiedPredicateNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* Code is not generated from set comprehensions in the given subset of B
	*/
	@Override
	public String visitSetComprehensionNode(SetComprehensionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* Code is not generated from identifier predicates in the given subset of B
	*/
	@Override
	public String visitIdentifierPredicateNode(IdentifierPredicateNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* This function generates code for a predicate with the belonging AST node
	*/
	@Override
	public String visitPredicateOperatorNode(PredicateOperatorNode node, Void expected) {
		return predicateGenerator.visitPredicateOperatorNode(node);
	}

	/*
	* This function generates code for a predicate with expression as arguments with the belonging AST node
	*/
	@Override
	public String visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, Void expected) {
		return predicateGenerator.visitPredicateOperatorWithExprArgs(node);
	}


	@Override
	public String visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node, Void expected) {
		return substitutionGenerator.visitIfOrSelectSubstitutionsNode(node);
	}

	@Override
	public String visitChoiceSubstitutionNode(ChoiceSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitChoiceSubstitutionNode(node, expected);
	}

	/*
    * Generating code from the skip substitution results in an empty string.
    */
	@Override
	public String visitSkipSubstitutionNode(SkipSubstitutionNode node, Void expected) {
		return "";
	}

	@Override
	public String visitConditionSubstitutionNode(ConditionSubstitutionNode node, Void expected) {
		return visitSubstitutionNode(node.getSubstitution(), expected);
	}

	@Override
	public String visitAnySubstitution(AnySubstitutionNode node, Void expected) {
		return substitutionGenerator.generateAnyParameters(node.getParameters(), node.getParameters().get(0), node.getWherePredicate(), node.getThenSubstitution(), 0, node.getParameters().size());
	}

	@Override
	public String visitAssignSubstitutionNode(AssignSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitAssignSubstitutionNode(node);
	}

	@Override
	public String visitListSubstitutionNode(ListSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitListSubstitutionNode(node);
	}

	@Override
	public String visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitBecomesElementOfSubstitutionNode(node);
	}

	/*
	* Code is not generated from the becomes such that substitution in the given subset of B.
	*/
	@Override
	public String visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* Generating code for LTL formulae results in an empty string as it is proven.
	*/
	@Override
	public String visitLTLPrefixOperatorNode(LTLPrefixOperatorNode node, Void expected) {
		return "";
	}

	/*
	* Generating code for LTL formulae results in an empty string as it is proven.
	*/
	@Override
	public String visitLTLKeywordNode(LTLKeywordNode node, Void expected) {
		return "";
	}

	/*
	* Generating code for LTL formulae results in an empty string as it is proven.
	*/
	@Override
	public String visitLTLInfixOperatorNode(LTLInfixOperatorNode node, Void expected) {
		return "";
	}

	/*
	* Generating code for LTL formulae results in an empty string as it is proven.
	*/
	@Override
	public String visitLTLBPredicateNode(LTLBPredicateNode node, Void expected) {
		return "";
	}

	@Override
	public String visitSubstitutionIdentifierCallNode(OperationCallSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitSubstitutionIdentifierCallNode(node, expected);
	}

	@Override
	public String visitWhileSubstitutionNode(WhileSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitWhileSubstitutionNode(node, expected);
	}

	@Override
	public String visitVarSubstitutionNode(VarSubstitutionNode node, Void expected) {
		return substitutionGenerator.visitVarSubstitutionNode(node, expected);
	}

	public NameHandler getNameHandler() {
		return nameHandler;
	}

	public String getMachineName() {
		return machineNode.getName();
	}

}
