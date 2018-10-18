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
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import de.prob.parser.ast.nodes.substitution.VarSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.WhileSubstitutionNode;
import de.prob.parser.ast.visitors.AbstractVisitor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hhu.stups.codegenerator.GeneratorMode.C;
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

	private static final Map<GeneratorMode, STGroup> TEMPLATE_MAP = new HashMap<>();

	static {
		TEMPLATE_MAP.put(JAVA, JAVA_GROUP);
		TEMPLATE_MAP.put(C, C_GROUP);
		TEMPLATE_MAP.put(CPP, CPP_GROUP);
		TEMPLATE_MAP.put(PY, PYTHON_GROUP);
	}

	private final TypeGenerator typeGenerator;

	private final OperatorGenerator operatorGenerator;

	private final OperationGenerator operationGenerator;

	private final IdentifierGenerator identifierGenerator;

	private final NameHandler nameHandler;

	private STGroup currentGroup;

	private Map<String, String> machineFromOperation;

	private Map<String, List<String>> setToEnum;

	private MachineNode machineNode;

	private int currentLocalScope;

	private int localScopes;

	private boolean useBigInteger;

	private List<String> identifierOnLhsInParallel;

	private List<String> definedLoadsInParallel;

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
		this.identifierOnLhsInParallel = new ArrayList<>();
		this.definedLoadsInParallel = new ArrayList<>();
		this.identifierGenerator = new IdentifierGenerator(currentGroup, nameHandler, identifierOnLhsInParallel);
		this.typeGenerator = new TypeGenerator(currentGroup, nameHandler);
		this.operatorGenerator = new OperatorGenerator(currentGroup, nameHandler);
		this.operationGenerator = new OperationGenerator(currentGroup, nameHandler, typeGenerator);
		this.machineFromOperation = new HashMap<>();
		this.setToEnum = new HashMap<>();
		this.currentLocalScope = 0;
		this.localScopes = 0;
	}

	/*
	* This function generates code for the whole machine with the given AST node.
	*/
	public String generateMachine(MachineNode node) {
		initialize(node);
		ST machine = currentGroup.getInstanceOf("machine");
		machine.add("addition", addition);
		machine.add("imports", typeGenerator.getImports());
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
		machine.add("initialization", visitInitialization(node));
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
	* This function generates code for the initialization for the given AST node of the machine.
	*/
	private String visitInitialization(MachineNode node) {
		ST initialization = currentGroup.getInstanceOf("initialization");
		initialization.add("machine", nameHandler.handle(node.getName()));
		initialization.add("machines", node.getMachineReferences().stream()
				.map(reference -> nameHandler.handle(reference.getMachineNode().getName()))
				.collect(Collectors.toList()));
		initialization.add("body", visitSubstitutionNode(node.getInitialisation(), null));
		return initialization.render();
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
		definedLoadsInParallel.clear();
		identifierOnLhsInParallel.clear();
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
		if(currentLocalScope > 0) {
			return identifierGenerator.generateVarDeclaration(node.getName());
		}
		return identifierGenerator.generate(node);
	}

	/*
	* Code is not generated from the cast predicate expressions in the given subset of B
	*/
	@Override
	public String visitCastPredicateExpressionNode(CastPredicateExpressionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
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

	/*
	* This function generates code for if substitutions and select substitutions from the belonging AST node.
	*/
	@Override
	public String visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node, Void expected) {
		if (node.getOperator() == IfOrSelectSubstitutionsNode.Operator.SELECT) {
			return visitSelectSubstitution(node);
		}
		return visitIfSubstitution(node);
	}

	/*
	* This function generates code for select substitutions from the belonging AST node and the belonging template.
	*/
	private String visitSelectSubstitution(IfOrSelectSubstitutionsNode node) {
		ST select = currentGroup.getInstanceOf("select");
		select.add("predicate", visitPredicateNode(node.getConditions().get(0), null));
		select.add("then", visitSubstitutionNode(node.getSubstitutions().get(0), null));
		return select.render();
	}

	/*
	* This function generates code for if substitutions with and without else-branches from the belonging AST node and the belonging template.
	*/
	private String visitIfSubstitution(IfOrSelectSubstitutionsNode node) {
		ST ifST = currentGroup.getInstanceOf("if");
		ifST.add("predicate", visitPredicateNode(node.getConditions().get(0), null));
		ifST.add("then", visitSubstitutionNode(node.getSubstitutions().get(0), null));
		ifST.add("else1", generateElseIfs(node));

		if (node.getElseSubstitution() != null) {
			ifST.add("else1", generateElse(node));
		}
		return ifST.render();
	}

	/*
	* This function generates code from the else if branches with the belonging AST node.
	*/
	private List<String> generateElseIfs(IfOrSelectSubstitutionsNode node) {
		List<String> conditions = node.getConditions().subList(1, node.getConditions().size()).stream()
				.map(condition -> visitPredicateNode(condition, null))
				.collect(Collectors.toList());
		List<String> then = node.getSubstitutions().subList(1, node.getSubstitutions().size()).stream()
				.map(substitutionNode -> visitSubstitutionNode(substitutionNode, null))
				.collect(Collectors.toList());

		List<String> elseIfs = new ArrayList<>();

		for (int i = 0; i < conditions.size(); i++) {
			ST elseST = currentGroup.getInstanceOf("elseif");
			elseST.add("predicate", conditions.get(i));
			elseST.add("then", then.get(i));
			elseIfs.add(elseST.render());
		}

		return elseIfs;
	}

	/*
	* This function generates code from the else branch from the belonging AST node.
	*/
	private String generateElse(IfOrSelectSubstitutionsNode node) {
		ST elseST = currentGroup.getInstanceOf("else");
		elseST.add("then", visitSubstitutionNode(node.getElseSubstitution(), null));
		return elseST.render();
	}

	@Override
	public String visitChoiceSubstitutionNode(ChoiceSubstitutionNode node, Void expected) {
		ST choice = currentGroup.getInstanceOf("choice");
		int length = node.getSubstitutions().size();
		List<SubstitutionNode> substitutions = node.getSubstitutions();
		choice.add("len", length);
		choice.add("then", visitSubstitutionNode(substitutions.get(0), null));
		choice.add("choice1", generateOtherChoices(node));
		if(substitutions.size() > 1) {
			ST choice2 = currentGroup.getInstanceOf("choice2");
			choice.add("choice1", choice2.add("then", visitSubstitutionNode(substitutions.get(length - 1), expected)));
		}
		return choice.render();
	}

	private List<String> generateOtherChoices(ChoiceSubstitutionNode node) {
		List<String> otherChoices = new ArrayList<>();
		for (int i = 1; i < node.getSubstitutions().size() - 1; i++) {
			ST choice = currentGroup.getInstanceOf("choice1");
			choice.add("counter", i);
			choice.add("then", visitSubstitutionNode(node.getSubstitutions().get(i), null));
			otherChoices.add(choice.render());
		}
		return otherChoices;
	}

	/*
    * Generating code from the skip substitution results in an empty string.
    */
	@Override
	public String visitSkipSubstitutionNode(SkipSubstitutionNode node, Void expected) {
		return "";
	}

	/*
	* Code is not generated from the Condition substitution in the given subset of B.
	*/
	@Override
	public String visitConditionSubstitutionNode(ConditionSubstitutionNode node, Void expected) {
		return visitSubstitutionNode(node.getSubstitution(), expected);
	}

	/*
	* Code is not generated from the Any substitution in the given subset of B.
	*/
	@Override
	public String visitAnySubstitution(AnySubstitutionNode node, Void expected) {
		//return visitSubstitutionNode(node.getThenSubstitution(), expected);
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	/*
	* This function generates code for a list of assignments from the belonging AST node.
	*/
	@Override
	public String visitAssignSubstitutionNode(AssignSubstitutionNode node, Void expected) {
		ST substitutions = currentGroup.getInstanceOf("assignments");
		List<String> assignments = new ArrayList<>();
		//TODO: For now, the variable on the left-hand side and on the right-hand side must be distinct
		for (int i = 0; i < node.getLeftSide().size(); i++) {
			assignments.add(generateAssignment(node.getLeftSide().get(i), node.getRightSide().get(i)));
		}
		substitutions.add("assignments", assignments);
		return substitutions.render();
	}

	/*
	* This function generates code for one assignment with the expressions and the belonging template
	*/
	public String generateAssignment(ExprNode lhs, ExprNode rhs) {
		ST substitution = currentGroup.getInstanceOf("assignment");
		substitution.add("identifier", visitIdentifierExprNode((IdentifierExprNode) lhs, null));
		String typeCast = typeGenerator.generate(rhs.getType(), true);
		substitution.add("typeCast", typeCast);
		substitution.add("val", visitExprNode(rhs, null));
		return substitution.render();
	}

	/*
	* This function generates code for sequential substitution and throws an exception for parallel substitutions as
	* code generation for parallel subsitutition is not supported in the given subset of B.
	* Therefore the belonging AST node is used.
	*/
	@Override
	public String visitListSubstitutionNode(ListSubstitutionNode node, Void expected) {
		if(node.getOperator() == ListSubstitutionNode.ListOperator.Parallel) {
			return visitParallelSubstitutionNode(node);
		}
		return visitSequentialSubstitutionNode(node);
	}

	public String visitParallelSubstitutionNode(ListSubstitutionNode node) {
		//TODO implement parallel execution of operation call from included machine
		identifierOnLhsInParallel.clear();
		ST substitutions = currentGroup.getInstanceOf("parallel");
		List<SubstitutionNode> assignments = node.getSubstitutions().stream()
				.filter(substituion -> substituion instanceof AssignSubstitutionNode)
				.collect(Collectors.toList());
		List<String> loads = assignments.stream()
				.map(assignment -> visitParallelLoads((AssignSubstitutionNode) assignment))
				.collect(Collectors.toList());
		List<String> others = node.getSubstitutions().stream()
				.filter(substituion -> !(substituion instanceof AssignSubstitutionNode))
				.map(substitution -> visitSubstitutionNode(substitution, null))
				.collect(Collectors.toList());
		List<String> stores = assignments.stream()
				.map(assignment -> visitParallelStores((AssignSubstitutionNode) assignment))
				.collect(Collectors.toList());
		substitutions.add("loads", loads);
		substitutions.add("others", others);
		substitutions.add("stores", stores);
		identifierOnLhsInParallel.clear();
		return substitutions.render();
	}

	public String visitParallelLoads(AssignSubstitutionNode node) {
		ST substitutions = currentGroup.getInstanceOf("assignments");
		List<String> assignments = new ArrayList<>();
		//TODO: For now, the variable on the left-hand side and on the right-hand side must be distinct
		for (int i = 0; i < node.getLeftSide().size(); i++) {
			IdentifierExprNode identifier = (IdentifierExprNode) node.getLeftSide().get(i);
			if(definedLoadsInParallel.contains(identifier.getName())) {
				continue;
			}
			assignments.add(visitParallelLoad(identifier));
			identifierOnLhsInParallel.add(identifier.getName());
			definedLoadsInParallel.add(identifier.getName());
		}
		substitutions.add("assignments", assignments);
		return substitutions.render();
	}

	public String visitParallelLoad(ExprNode expr) {
		ST substitution = currentGroup.getInstanceOf("parallel_load");
		substitution.add("type", typeGenerator.generate(expr.getType(), false));
		substitution.add("identifier", visitIdentifierExprNode((IdentifierExprNode) expr, null));
		String typeCast = typeGenerator.generate(expr.getType(), true);
		substitution.add("typeCast", typeCast);
		return substitution.render();
	}


	public String visitParallelStores(AssignSubstitutionNode node) {
		ST substitutions = currentGroup.getInstanceOf("assignments");
		List<String> assignments = new ArrayList<>();
		//TODO: For now, the variable on the left-hand side and on the right-hand side must be distinct
		for (int i = 0; i < node.getLeftSide().size(); i++) {
			assignments.add(visitParallelStore(node.getLeftSide().get(i), node.getRightSide().get(i)));
		}
		substitutions.add("assignments", assignments);
		return substitutions.render();
	}

	public String visitParallelStore(ExprNode lhs, ExprNode rhs) {
		ST substitution = currentGroup.getInstanceOf("parallel_store");
		identifierGenerator.setLhsInParallel(true);
		substitution.add("identifier", visitIdentifierExprNode((IdentifierExprNode) lhs, null));
		identifierGenerator.setLhsInParallel(false);
		String typeCast = typeGenerator.generate(rhs.getType(), true);
		substitution.add("typeCast", typeCast);
		substitution.add("val", visitExprNode(rhs, null));
		return substitution.render();
	}

	/*
	* This function generates code for a sequential substitution with the belonging AST node.
	*/
	public String visitSequentialSubstitutionNode(ListSubstitutionNode node) {
		List<String> substitutionCodes = node.getSubstitutions().stream()
				.map(substitutionNode -> visitSubstitutionNode(substitutionNode, null))
				.collect(Collectors.toList());
		return String.join("\n", substitutionCodes);
	}

	@Override
	public String visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, Void expected) {
		ST substitutions = currentGroup.getInstanceOf("assignments");
		List<String> assignments = new ArrayList<>();
		for (int i = 0; i < node.getIdentifiers().size(); i++) {
			assignments.add(generateNondeterminism(node.getIdentifiers().get(i), node.getExpression()));
		}
		substitutions.add("assignments", assignments);
		return substitutions.render();
	}

	public String generateNondeterminism(IdentifierExprNode lhs, ExprNode rhs) {
		ST substitution = currentGroup.getInstanceOf("nondeterminism");
		substitution.add("identifier", visitIdentifierExprNode(lhs, null));
		String typeCast = typeGenerator.generate(lhs.getType(), true);
		substitution.add("typeCast", typeCast);
		substitution.add("set", visitExprNode(rhs, null));
		return substitution.render();
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

	/*
	* This function generates code from operation calls from the belonging AST node and the belonging templates
	* for operation calls with and without assignments.
	*/
	@Override
	public String visitSubstitutionIdentifierCallNode(OperationCallSubstitutionNode node, Void expected) {
		List<String> variables = node.getAssignedVariables().stream()
				.map(var -> visitExprNode(var, expected))
				.collect(Collectors.toList());
		String operationName = node.getOperationNode().getName();
		String machineName = machineFromOperation.get(operationName);
		ST functionCall;
		//Size of variables must be less equal than 1 for now.
		//TODO: Implement Records
		if(variables.size() > 0) {
			functionCall = currentGroup.getInstanceOf("operation_call_with_assignment");
			functionCall.add("var", variables.get(0));
		} else {
			functionCall = currentGroup.getInstanceOf("operation_call_without_assignment");
		}
		functionCall.add("machine", nameHandler.handle(machineName));
		functionCall.add("function", operationName);
		functionCall.add("args", node.getArguments().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList()));
		functionCall.add("this", machineName.equals(machineNode.getName()));
		return functionCall.render();
	}

	/*
	* This function generates code for a while loop with the belonging AST node and the belonging template.
	*/
	@Override
	public String visitWhileSubstitutionNode(WhileSubstitutionNode node, Void expected) {
		ST whileST = currentGroup.getInstanceOf("while");
		whileST.add("predicate", visitPredicateNode(node.getCondition(), expected));
		whileST.add("then", visitSubstitutionNode(node.getBody(), expected));
		return whileST.render();
	}

	/*
	* This function generates from a var substitution with the belonging AST node and template. During this step the
	* flag for local scope is set to true and finally resetted to false. This is needed for handling collisions between
	* local variables and output parameters.
	*/
	@Override
	public String visitVarSubstitutionNode(VarSubstitutionNode node, Void expected) {
		ST varST = currentGroup.getInstanceOf("var");
		this.localScopes++;
		this.currentLocalScope++;
		identifierGenerator.push(localScopes);
		node.getLocalIdentifiers().forEach(identifier -> identifierGenerator.addLocal(identifier.getName()));
		varST.add("locals", generateVariablesInVar(node.getLocalIdentifiers()));
		varST.add("body", visitSubstitutionNode(node.getBody(), expected));
		identifierGenerator.pop();
		node.getLocalIdentifiers().forEach(identifier -> identifierGenerator.resetLocal(identifier.getName()));
		this.currentLocalScope--;
		return varST.render();
	}

	/*
	* This function generates code for all declarations of local variables from a var substitution from the list of identifiers as AST nodes.
	*/
	public List<String> generateVariablesInVar(List<DeclarationNode> identifiers) {
		return identifiers.stream()
				.map(this::generateVariableInVar)
				.collect(Collectors.toList());
	}

	/*
	* This function generates code for one declaration of a local variable from a var substitution node from the belonging AST node and template.
	*/
	public String generateVariableInVar(DeclarationNode identifier) {
		ST declaration = currentGroup.getInstanceOf("local_declaration");
		declaration.add("type", typeGenerator.generate(identifier.getType(), false));
		declaration.add("identifier", identifierGenerator.generateVarDeclaration(identifier.getName()));
		return declaration.render();
	}

	public NameHandler getNameHandler() {
		return nameHandler;
	}

}
