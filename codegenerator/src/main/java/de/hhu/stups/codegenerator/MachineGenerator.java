package de.hhu.stups.codegenerator;

import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetDeclarationNode;
import de.prob.parser.ast.nodes.EnumeratedSetElementNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.MachineReferenceNode;
import de.prob.parser.ast.nodes.OperationNode;
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
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hhu.stups.codegenerator.GeneratorMode.C;
import static de.hhu.stups.codegenerator.GeneratorMode.CLJ;
import static de.hhu.stups.codegenerator.GeneratorMode.CPP;
import static de.hhu.stups.codegenerator.GeneratorMode.JAVA;
import static de.hhu.stups.codegenerator.GeneratorMode.PY;

/*
* The code generator is implemented by using the visitor pattern
*/

public class MachineGenerator implements AbstractVisitor<String, Void> {

	/*
	* Template groups for the supported programming languages
	*/
	private static final STGroup JAVA_GROUP = new STGroupFile(MachineGenerator.class.getClassLoader()
			.getResource("de/hhu/stups/codegenerator/JavaTemplate.stg").getFile());

	private static final STGroup C_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/CTemplate.stg").getFile());

	private static final STGroup CPP_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/CppTemplate.stg").getFile());

	private static final STGroup PYTHON_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/PythonTemplate.stg").getFile());

	private static final STGroup CLJ_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/ClojureTemplate.stg").getFile());

	private static final Map<GeneratorMode, STGroup> TEMPLATE_MAP = new HashMap<>();

	static {
		TEMPLATE_MAP.put(JAVA, JAVA_GROUP);
		TEMPLATE_MAP.put(C, C_GROUP);
		TEMPLATE_MAP.put(CPP, CPP_GROUP);
		TEMPLATE_MAP.put(PY, PYTHON_GROUP);
		TEMPLATE_MAP.put(CLJ, CLJ_GROUP);
	}

	private final TypeGenerator typeGenerator;

	private final OperatorGenerator operatorGenerator;

	private final OperationGenerator operationGenerator;

	private final SubstitutionGenerator substitutionGenerator;

	private final IdentifierGenerator identifierGenerator;

	private final NameHandler nameHandler;

	private STGroup currentGroup;

	private Map<String, String> machineFromOperation;

	private Map<String, List<String>> setToEnum;

	private MachineNode machineNode;

	private boolean useBigInteger;

	private String addition;

	public MachineGenerator(GeneratorMode mode, boolean useBigInteger, Path addition) {
		this.currentGroup = TEMPLATE_MAP.get(mode);
		if(addition != null) {
			try {
				this.addition = new String(Files.readAllBytes(addition));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.useBigInteger = useBigInteger;
		this.nameHandler = new NameHandler(currentGroup);
		this.identifierGenerator = new IdentifierGenerator(currentGroup, nameHandler);
		this.typeGenerator = new TypeGenerator(currentGroup, nameHandler);
		this.operatorGenerator = new OperatorGenerator(currentGroup, nameHandler);
		this.operationGenerator = new OperationGenerator(currentGroup, nameHandler, typeGenerator);
		this.substitutionGenerator = new SubstitutionGenerator(currentGroup, this, nameHandler, typeGenerator, operatorGenerator, identifierGenerator);
		this.machineFromOperation = new HashMap<>();
		this.setToEnum = new HashMap<>();
	}

	/*
	* This function generates code for the whole machine with the given AST node.
	*/
	public String generateMachine(MachineNode node) {
		initialize(node);
		ST machine = currentGroup.getInstanceOf("machine");
		machine.add("addition", addition);
		machine.add("imports", typeGenerator.getImports());
		machine.add("includedMachines", generateMachineImports(node));
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
		mapOperationsToMachine(node);
	}

	/*
	* This function maps operations to machines for identifying the included machine where the operation is called from.
	*/
	private void mapOperationsToMachine(MachineNode node) {
		node.getMachineReferences()
				.forEach(reference -> reference.getMachineNode().getOperations()
						.forEach(operation -> machineFromOperation.put(operation.getName(), reference.getMachineName())));
	}

	/*
	* This function generates the whole body of a machine from the given AST node for the machine.
	*/
	private void generateBody(MachineNode node, ST machine) {
		machine.add("constants", generateConstants(node));
		machine.add("values", generateValues(node));
		machine.add("enums", generateEnumDeclarations(node));
		machine.add("sets", generateSetDeclarations(node));
		machine.add("declarations", visitDeclarations(node.getVariables()));
		machine.add("includes", generateIncludes(node));
		machine.add("initialization", substitutionGenerator.visitInitialization(node));
		machine.add("operations", visitOperations(node.getOperations()));
	}

	private String generateValues(MachineNode node) {
		if(node.getValues().size() == 0) {
			return "";
		}
		ST values = currentGroup.getInstanceOf("values");
		List<String> assignments = node.getValues().stream()
				.map(substitution -> visitSubstitutionNode(substitution, null))
				.collect(Collectors.toList());
		values.add("assignments", assignments);
		return values.render();
	}

	private List<String> generateConstants(MachineNode node) {
		//TODO Generate code for PROPERTIES (?)
		return node.getConstants().stream()
				.map(this::generateConstant)
				.collect(Collectors.toList());
	}

	private String generateConstant(DeclarationNode constant) {
		ST declaration = currentGroup.getInstanceOf("constant");
		declaration.add("type", typeGenerator.generate(constant.getType(), false));
		declaration.add("identifier", nameHandler.handleIdentifier(constant.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
		return declaration.render();
	}

	/*
	* This function generates code from the VARIABLES clause
	*/
	private List<String> visitDeclarations(List<DeclarationNode> declarations) {
		return declarations.stream()
				.map(this::generateGlobalDeclaration)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for each declaration from the VARIABLES clause
	*/
	private String generateGlobalDeclaration(DeclarationNode node) {
		ST declaration = currentGroup.getInstanceOf("global_declaration");
		declaration.add("type", typeGenerator.generate(node.getType(), false));
		declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
		return declaration.render();
	}

	private List<String> generateMachineImports(MachineNode node) {
		return node.getMachineReferences().stream()
				.map(this::generateMachineImport)
				.collect(Collectors.toList());
	}

	private String generateMachineImport(MachineReferenceNode reference) {
		ST imp = currentGroup.getInstanceOf("import_type");
		String machine = reference.getMachineName();
		imp.add("type", nameHandler.handle(machine));
		return imp.render();
	}

	/*
	* This function generates code for all including other machines with the given AST node of the main machine.
	*/
	private List<String> generateIncludes(MachineNode node) {
		return node.getMachineReferences().stream()
				.map(this::generateIncludeDeclaration)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for one included machine with the given AST node and the template.
	*/
	private String generateIncludeDeclaration(MachineReferenceNode reference) {
		ST declaration = currentGroup.getInstanceOf("include_declaration");
		String machine = reference.getMachineName();
		declaration.add("type", nameHandler.handle(machine));
		declaration.add("identifier", nameHandler.handle(machine));
		return declaration.render();
	}

	/*
	* This function generates code for all operations in a machine.
	*/
	private List<String> visitOperations(List<OperationNode> operations) {
		return operations.stream()
				.map(this::visitOperation)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for one operation with the given AST node for an operation.
	*/
	private String visitOperation(OperationNode node) {
		identifierGenerator.setParams(node.getParams(), node.getOutputParams());
		substitutionGenerator.resetParallel();
		ST operation = operationGenerator.generate(node);
		operation.add("machine", nameHandler.handle(machineNode.getName()));
		operation.add("body", visitSubstitutionNode(node.getSubstitution(), null));
		return operation.render();
	}

	/*
	* This function generates code from an expression in B.
	*/
	@Override
	public String visitExprNode(ExprNode node, Void expected) {
		typeGenerator.addImport(node.getType());
		if (node instanceof NumberNode) {
			return visitNumberNode((NumberNode) node, expected);
		} else if (node instanceof ExpressionOperatorNode) {
			return visitExprOperatorNode((ExpressionOperatorNode) node, expected);
		} else if (node instanceof EnumeratedSetElementNode) {
			EnumeratedSetElementNode element = (EnumeratedSetElementNode) node;
			return callEnum(element.getType().toString(), element.getDeclarationNode());
		} else if(node instanceof IdentifierExprNode) {
			Map<String, List<String>> enumTypes = nameHandler.getEnumTypes();
			if(enumTypes.keySet().contains(node.getType().toString()) &&
					enumTypes.get(node.getType().toString()).contains(((IdentifierExprNode) node).getName())) {
				return callEnum(node.getType().toString(), ((IdentifierExprNode) node).getDeclarationNode());
			}
			return visitIdentifierExprNode((IdentifierExprNode) node, expected);
		} else if(node instanceof CastPredicateExpressionNode) {
			return visitCastPredicateExpressionNode((CastPredicateExpressionNode) node, expected);
		}
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* This function generates code for enumerated sets within a machine.
	*/
	private List<String> generateEnumDeclarations(MachineNode node) {
		node.getEnumaratedSets().forEach(set -> setToEnum.put(set.getSetDeclarationNode().getName(), set.getElements().stream()
				.map(DeclarationNode::getName)
				.collect(Collectors.toList())));
		return node.getEnumaratedSets().stream()
				.map(this::declareEnums)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for all declarations of enums for enumerated sets from the node of the machine.
	*/
	private List<String> generateSetDeclarations(MachineNode node) {
		return node.getEnumaratedSets().stream()
				.map(this::visitEnumeratedSetDeclarationNode)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for declarating a enum for an enumerated set from the belonging AST node and the belonging template.
	*/
	private String declareEnums(EnumeratedSetDeclarationNode node) {
		typeGenerator.addImport(node.getElements().get(0).getType());
		ST enumDeclaration = currentGroup.getInstanceOf("set_enum_declaration");
		enumDeclaration.add("name", nameHandler.handleIdentifier(node.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
		List<String> enums = node.getElements().stream()
				.map(element -> nameHandler.handleEnum(element.getName(), node.getElements().stream().map(DeclarationNode::getName).collect(Collectors.toList())))
				.collect(Collectors.toList());
		enumDeclaration.add("enums", enums);
		return enumDeclaration.render();
	}

	/*
	* This function generates code with creating a BSet for an enumerated set from the belonging AST node and the belonging template.
	*/
	public String visitEnumeratedSetDeclarationNode(EnumeratedSetDeclarationNode node) {
		typeGenerator.addImport(node.getSetDeclarationNode().getType());
		ST setDeclaration = currentGroup.getInstanceOf("set_declaration");
		setDeclaration.add("identifier", nameHandler.handleIdentifier(node.getSetDeclarationNode().getName(), NameHandler.IdentifierHandlingEnum.VARIABLES));
		List<String> enums = node.getElements().stream()
				.map(declaration -> callEnum(node.getSetDeclarationNode().getName(), declaration))
				.collect(Collectors.toList());
		setDeclaration.add("enums", enums);
		return setDeclaration.render();
	}

	/*
	* This function generates code for calling enums from an enumerated from the belonging AST node,
	* template and the name of the enumerated set the enum belongs to.
	*/
	public String callEnum(String setName, DeclarationNode enumNode) {
		ST enumST = currentGroup.getInstanceOf("enum_call");
		enumST.add("class", nameHandler.handleIdentifier(setName, NameHandler.IdentifierHandlingEnum.MACHINES));
		enumST.add("identifier", nameHandler.handleEnum(enumNode.getName(), setToEnum.get(setName)));
		return enumST.render();
	}

	/*
	* This function generates code for an expression.
	*/
	@Override
	public String visitExprOperatorNode(ExpressionOperatorNode node, Void expected) {
		List<String> expressionList = node.getExpressionNodes().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generateExpression(node, expressionList);
	}

	/*
	* This function generates code for an identifier from the belonging AST node.
	*/
	@Override
	public String visitIdentifierExprNode(IdentifierExprNode node, Void expected) {
		if(substitutionGenerator.getCurrentLocalScope() > 0) {
			return identifierGenerator.generateVarDeclaration(node.getName());
		}
		return identifierGenerator.generate(node);
	}

	/*
	* This function generates code for cast predicates
	*/
	@Override
	public String visitCastPredicateExpressionNode(CastPredicateExpressionNode node, Void expected) {
		return visitPredicateNode(node.getPredicate(), expected);
	}

	/*
	* This function generates code for numbers from the belonging AST node and the belonging template
	*/
	@Override
	public String visitNumberNode(NumberNode node, Void expected) {
		ST number = currentGroup.getInstanceOf("number");
		number.add("number", node.getValue().toString());
		number.add("useBigInteger", useBigInteger);
		return number.render();
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
		typeGenerator.addImport(node.getType());
		List<String> expressionList = node.getPredicateArguments().stream()
				.map(expr -> visitPredicateNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generatePredicate(node, expressionList);
	}

	/*
	* This function generates code for a predicate with expression as arguments with the belonging AST node
	*/
	@Override
	public String visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, Void expected) {
		typeGenerator.addImport(node.getType());
		List<String> expressionList = node.getExpressionNodes().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generateBinary(node::getOperator, expressionList);
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

	public Map<String, String> getMachineFromOperation() {
		return machineFromOperation;
	}
}
