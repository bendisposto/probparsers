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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hhu.stups.codegenerator.GeneratorMode.C;
import static de.hhu.stups.codegenerator.GeneratorMode.JAVA;
import static de.hhu.stups.codegenerator.GeneratorMode.PY;

public class MachineGenerator implements AbstractVisitor<String, Void> {

	private static final STGroup JAVA_GROUP = new STGroupFile(MachineGenerator.class.getClassLoader()
			.getResource("de/hhu/stups/codegenerator/JavaTemplate.stg").getFile());

	private static final STGroup C_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/CTemplate.stg").getFile());

	private static final STGroup PYTHON_GROUP = new STGroupFile(
			MachineGenerator.class.getClassLoader().getResource("de/hhu/stups/codegenerator/PythonTemplate.stg").getFile());

	private static final Map<GeneratorMode, STGroup> TEMPLATE_MAP = new HashMap<>();

	static {
		TEMPLATE_MAP.put(JAVA, JAVA_GROUP);
		TEMPLATE_MAP.put(C, C_GROUP);
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

	private boolean isLocalScope;

	public MachineGenerator(GeneratorMode mode) {
		this.currentGroup = TEMPLATE_MAP.get(mode);
		this.nameHandler = new NameHandler(currentGroup);
		this.identifierGenerator = new IdentifierGenerator(currentGroup, nameHandler);
		this.typeGenerator = new TypeGenerator(currentGroup, nameHandler);
		this.operatorGenerator = new OperatorGenerator(currentGroup);
		this.operationGenerator = new OperationGenerator(currentGroup, nameHandler, identifierGenerator, typeGenerator);
		this.machineFromOperation = new HashMap<>();
		this.setToEnum = new HashMap<>();
		this.isLocalScope = false;
	}

	public String generateMachine(MachineNode node) {
		initialize(node);
		ST machine = currentGroup.getInstanceOf("machine");
		machine.add("imports", typeGenerator.getImports());
		machine.add("machine", nameHandler.handle(node.getName()));
		generateBody(node, machine);
		return machine.render();
	}

	private void initialize(MachineNode node) {
		this.machineNode = node;
		nameHandler.initialize(node);
		mapOperationsToMachine(node);
	}

	private void mapOperationsToMachine(MachineNode node) {
		node.getMachineReferences()
				.forEach(reference -> reference.getMachineNode().getOperations()
						.forEach(operation -> machineFromOperation.put(operation.getName(), reference.getMachineName())));
	}

	private void generateBody(MachineNode node, ST machine) {
		machine.add("enums", generateEnumDeclarations(node));
		machine.add("sets", generateSetDeclarations(node));
		machine.add("declarations", visitDeclarations(node.getVariables()));
		machine.add("includes", generateIncludes(node));
		machine.add("initialization", visitInitialization(node));
		machine.add("operations", visitOperations(node.getOperations()));
	}

	private List<String> visitDeclarations(List<DeclarationNode> declarations) {
		return declarations.stream()
				.map(this::generateGlobalDeclaration)
				.collect(Collectors.toList());
	}

	private String generateGlobalDeclaration(DeclarationNode node) {
		ST declaration = currentGroup.getInstanceOf("global_declaration");
		declaration.add("type", typeGenerator.generate(node.getType(), false));
		declaration.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
		return declaration.render();
	}

	private List<String> generateIncludes(MachineNode node) {
		return node.getMachineReferences().stream()
				.map(this::generateIncludeDeclaration)
				.collect(Collectors.toList());
	}

	private String generateIncludeDeclaration(MachineReferenceNode reference) {
		ST declaration = currentGroup.getInstanceOf("include_declaration");
		String machine = reference.getMachineName();
		declaration.add("type", nameHandler.handle(machine));
		declaration.add("identifier", nameHandler.handle(machine));
		return declaration.render();
	}

	private String visitInitialization(MachineNode node) {
		ST initialization = currentGroup.getInstanceOf("initialization");
		initialization.add("machines", node.getMachineReferences().stream()
				.map(reference -> nameHandler.handle(reference.getMachineNode().getName()))
				.collect(Collectors.toList()));
		initialization.add("body", visitSubstitutionNode(node.getInitialisation(), null));
		return initialization.render();
	}

	private List<String> visitOperations(List<OperationNode> operations) {
		return operations.stream()
				.map(this::visitOperation)
				.collect(Collectors.toList());
	}

	private String visitOperation(OperationNode node) {
		identifierGenerator.setParams(node.getParams(), node.getOutputParams());
		ST operation = operationGenerator.generate(node, machineNode.getVariables());
		operation.add("body", visitSubstitutionNode(node.getSubstitution(), null));
		return operation.render();
	}

	@Override
	public String visitExprNode(ExprNode node, Void expected) {
		typeGenerator.addImport(node.getType());
		if (node instanceof NumberNode) {
			return visitNumberNode((NumberNode) node, expected);
		} else if (node instanceof ExpressionOperatorNode) {
			return visitExprOperatorNode((ExpressionOperatorNode) node, expected);
		} else if (node instanceof EnumeratedSetElementNode) {
			return visitEnumeratedSetElementNode((EnumeratedSetElementNode) node);
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

	private List<String> generateEnumDeclarations(MachineNode node) {
		node.getEnumaratedSets().forEach(set -> setToEnum.put(set.getSetDeclarationNode().getName(), set.getElements().stream()
				.map(DeclarationNode::getName)
				.collect(Collectors.toList())));
		return node.getEnumaratedSets().stream()
				.map(this::declareEnums)
				.collect(Collectors.toList());
	}

	private List<String> generateSetDeclarations(MachineNode node) {
		return node.getEnumaratedSets().stream()
				.map(this::visitEnumeratedSetDeclarationNode)
				.collect(Collectors.toList());
	}

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

	public String callEnum(String setName, DeclarationNode enumNode) {
		ST enumST = currentGroup.getInstanceOf("enum_call");
		enumST.add("class", nameHandler.handleIdentifier(setName, NameHandler.IdentifierHandlingEnum.MACHINES));
		enumST.add("identifier", nameHandler.handleEnum(enumNode.getName(), setToEnum.get(setName)));
		return enumST.render();
	}

	public String visitEnumeratedSetElementNode(EnumeratedSetElementNode node) {
		String typeName = node.getType().toString();
		ST element = currentGroup.getInstanceOf("set_element");
		element.add("set", nameHandler.handleIdentifier(typeName, NameHandler.IdentifierHandlingEnum.MACHINES));
		element.add("element", nameHandler.handleEnum(node.getName(), setToEnum.get(typeName)));
		return element.render();
	}

	@Override
	public String visitExprOperatorNode(ExpressionOperatorNode node, Void expected) {
		List<String> expressionList = node.getExpressionNodes().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generateExpression(node, expressionList);
	}

	@Override
	public String visitIdentifierExprNode(IdentifierExprNode node, Void expected) {
		if(isLocalScope) {
			return identifierGenerator.generateVarDeclaration(node.getName());
		}
		return identifierGenerator.generate(node);
	}

	@Override
	public String visitCastPredicateExpressionNode(CastPredicateExpressionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitNumberNode(NumberNode node, Void expected) {
		ST number = currentGroup.getInstanceOf("number");
		number.add("number", node.getValue().toString());
		return number.render();
	}

	@Override
	public String visitQuantifiedExpressionNode(QuantifiedExpressionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitQuantifiedPredicateNode(QuantifiedPredicateNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitSetComprehensionNode(SetComprehensionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitIdentifierPredicateNode(IdentifierPredicateNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitPredicateOperatorNode(PredicateOperatorNode node, Void expected) {
		List<String> expressionList = node.getPredicateArguments().stream()
				.map(expr -> visitPredicateNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generatePredicate(node, expressionList);
	}

	@Override
	public String visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node, Void expected) {
		List<String> expressionList = node.getExpressionNodes().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList());
		return operatorGenerator.generateBinary(node::getOperator, expressionList);
	}

	@Override
	public String visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node, Void expected) {
		if (node.getOperator() == IfOrSelectSubstitutionsNode.Operator.SELECT) {
			return visitSelectSubstitution(node);
		}
		return visitIfSubstitution(node);
	}

	private String visitSelectSubstitution(IfOrSelectSubstitutionsNode node) {
		ST select = currentGroup.getInstanceOf("select");
		select.add("predicate", visitPredicateNode(node.getConditions().get(0), null));
		select.add("then", visitSubstitutionNode(node.getSubstitutions().get(0), null));
		return select.render();
	}

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

	private String generateElse(IfOrSelectSubstitutionsNode node) {
		ST elseST = currentGroup.getInstanceOf("else");
		elseST.add("then", visitSubstitutionNode(node.getElseSubstitution(), null));
		return elseST.render();
	}

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
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitAssignSubstitutionNode(AssignSubstitutionNode node, Void expected) {
		ST substitutions = currentGroup.getInstanceOf("assignments");
		List<String> assignments = new ArrayList<>();
		for (int i = 0; i < node.getLeftSide().size(); i++) {
			assignments.add(generateAssignment(node.getLeftSide().get(i), node.getRightSide().get(i)));
		}
		substitutions.add("assignments", assignments);
		return substitutions.render();
	}

	public String generateAssignment(ExprNode lhs, ExprNode rhs) {
		ST substitution = currentGroup.getInstanceOf("assignment");
		substitution.add("identifier", visitIdentifierExprNode((IdentifierExprNode) lhs, null));
		String typeCast = typeGenerator.generate(rhs.getType(), true);
		substitution.add("typeCast", typeCast);
		substitution.add("val", visitExprNode(rhs, null));
		return substitution.render();
	}

	@Override
	public String visitListSubstitutionNode(ListSubstitutionNode node, Void expected) {
		if(node.getOperator() == ListSubstitutionNode.ListOperator.Parallel) {
			throw new RuntimeException("Given list substitution is not implemented: " + node.getOperator());
		}
		return visitSequentialSubstitutionNode(node);
	}

	public String visitSequentialSubstitutionNode(ListSubstitutionNode node) {
		List<String> substitutionCodes = node.getSubstitutions().stream()
				.map(substitutionNode -> visitSubstitutionNode(substitutionNode, null))
				.collect(Collectors.toList());
		return String.join("\n", substitutionCodes);
	}

	@Override
	public String visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitBecomesSuchThatSubstitutionNode(BecomesSuchThatSubstitutionNode node, Void expected) {
		throw new RuntimeException("Given node is not implemented: " + node.getClass());
	}

	@Override
	public String visitLTLPrefixOperatorNode(LTLPrefixOperatorNode node, Void expected) {
		return "";
	}

	@Override
	public String visitLTLKeywordNode(LTLKeywordNode node, Void expected) {
		return "";
	}

	@Override
	public String visitLTLInfixOperatorNode(LTLInfixOperatorNode node, Void expected) {
		return "";
	}

	@Override
	public String visitLTLBPredicateNode(LTLBPredicateNode node, Void expected) {
		return "";
	}

	@Override
	public String visitSubstitutionIdentifierCallNode(OperationCallSubstitutionNode node, Void expected) {
		List<String> variables = node.getAssignedVariables().stream()
				.map(var -> visitExprNode(var, expected))
				.collect(Collectors.toList());
		String operationName = node.getOperationNode().getName();
		String machineName = machineFromOperation.get(operationName);
		ST functionCall;
		if(variables.size() > 0) {
			functionCall = currentGroup.getInstanceOf("operation_call_with_assignment");
			functionCall.add("var", variables.get(0));
		} else {
			functionCall = currentGroup.getInstanceOf("operation_call");
		}
		functionCall.add("machine", nameHandler.handle(machineName));
		functionCall.add("function", operationName);
		functionCall.add("args", node.getArguments().stream()
				.map(expr -> visitExprNode(expr, expected))
				.collect(Collectors.toList()));
		functionCall.add("this", machineName.equals(machineNode.getName()));
		return functionCall.render();
	}

	@Override
	public String visitWhileSubstitutionNode(WhileSubstitutionNode node, Void expected) {
		ST whileST = currentGroup.getInstanceOf("while");
		whileST.add("predicate", visitPredicateNode(node.getCondition(), expected));
		whileST.add("then", visitSubstitutionNode(node.getBody(), expected));
		return whileST.render();
	}

	@Override
	public String visitVarSubstitutionNode(VarSubstitutionNode node, Void expected) {
		ST varST = currentGroup.getInstanceOf("var");
		node.getLocalIdentifiers().forEach(identifier -> identifierGenerator.addLocal(identifier.getName()));
		this.isLocalScope = true;
		varST.add("locals", generateVariablesInVar(node.getLocalIdentifiers()));
		varST.add("body", visitSubstitutionNode(node.getBody(), expected));
		this.isLocalScope = false;
		return varST.render();
	}

	public List<String> generateVariablesInVar(List<DeclarationNode> identifiers) {
		return identifiers.stream()
				.map(this::generateVariableInVar)
				.collect(Collectors.toList());
	}

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
