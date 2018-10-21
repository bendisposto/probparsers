package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.MachineNode;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.predicate.PredicateNode;
import de.prob.parser.ast.nodes.substitution.AssignSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.BecomesElementOfSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.ChoiceSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.IfOrSelectSubstitutionsNode;
import de.prob.parser.ast.nodes.substitution.ListSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.OperationCallSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.SubstitutionNode;
import de.prob.parser.ast.nodes.substitution.VarSubstitutionNode;
import de.prob.parser.ast.nodes.substitution.WhileSubstitutionNode;
import de.prob.parser.ast.types.BoolType;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubstitutionGenerator {

    private final STGroup currentGroup;

    private final MachineGenerator machineGenerator;

    private OperationGenerator operationGenerator;

    private final NameHandler nameHandler;

    private final TypeGenerator typeGenerator;

    private final ExpressionGenerator expressionGenerator;

    private final IdentifierGenerator identifierGenerator;

    private int currentLocalScope;

    private int localScopes;

    private final List<String> identifierOnLhsInParallel;

    private final List<String> definedLoadsInParallel;

    public SubstitutionGenerator(final STGroup currentGroup, final MachineGenerator machineGenerator, final NameHandler nameHandler,
                                 final TypeGenerator typeGenerator, final ExpressionGenerator expressionGenerator,
                                 final IdentifierGenerator identifierGenerator) {
        this.currentGroup = currentGroup;
        this.machineGenerator = machineGenerator;
        this.nameHandler = nameHandler;
        this.typeGenerator = typeGenerator;
        this.expressionGenerator = expressionGenerator;
        this.expressionGenerator.setSubstitutionGenerator(this);
        this.identifierGenerator = identifierGenerator;
        this.identifierOnLhsInParallel = new ArrayList<>();
        this.definedLoadsInParallel = new ArrayList<>();
        this.identifierGenerator.setIdentifierOnLhsInParallel(identifierOnLhsInParallel);
        this.currentLocalScope = 0;
        this.localScopes = 0;
    }

    /*
    * This function generates code for the initialization for the given AST node of the machine.
    */
    public String visitInitialization(MachineNode node) {
        ST initialization = currentGroup.getInstanceOf("initialization");
        initialization.add("machine", nameHandler.handle(node.getName()));
        initialization.add("machines", node.getMachineReferences().stream()
                .map(reference -> nameHandler.handle(reference.getMachineNode().getName()))
                .collect(Collectors.toList()));
        initialization.add("body", machineGenerator.visitSubstitutionNode(node.getInitialisation(), null));
        return initialization.render();
    }

    /*
    * This function generates code for if substitutions and select substitutions from the belonging AST node.
    */
    public String visitIfOrSelectSubstitutionsNode(IfOrSelectSubstitutionsNode node) {
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
        select.add("predicate", machineGenerator.visitPredicateNode(node.getConditions().get(0), null));
        select.add("then", machineGenerator.visitSubstitutionNode(node.getSubstitutions().get(0), null));
        return select.render();
    }

    /*
    * This function generates code for if substitutions with and without else-branches from the belonging AST node and the belonging template.
    */
    private String visitIfSubstitution(IfOrSelectSubstitutionsNode node) {
        ST ifST = currentGroup.getInstanceOf("if");
        ifST.add("predicate", machineGenerator.visitPredicateNode(node.getConditions().get(0), null));
        ifST.add("then", machineGenerator.visitSubstitutionNode(node.getSubstitutions().get(0), null));
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
                .map(condition -> machineGenerator.visitPredicateNode(condition, null))
                .collect(Collectors.toList());
        List<String> then = node.getSubstitutions().subList(1, node.getSubstitutions().size()).stream()
                .map(substitutionNode -> machineGenerator.visitSubstitutionNode(substitutionNode, null))
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
        elseST.add("then", machineGenerator.visitSubstitutionNode(node.getElseSubstitution(), null));
        return elseST.render();
    }


    public String visitChoiceSubstitutionNode(ChoiceSubstitutionNode node, Void expected) {
        ST choice = currentGroup.getInstanceOf("choice");
        int length = node.getSubstitutions().size();
        List<SubstitutionNode> substitutions = node.getSubstitutions();
        choice.add("len", length);
        choice.add("then", machineGenerator.visitSubstitutionNode(substitutions.get(0), null));
        choice.add("choice1", generateOtherChoices(node));
        if(substitutions.size() > 1) {
            ST choice2 = currentGroup.getInstanceOf("choice2");
            choice.add("choice1", choice2.add("then", machineGenerator.visitSubstitutionNode(substitutions.get(length - 1), expected)));
        }
        return choice.render();
    }

    private List<String> generateOtherChoices(ChoiceSubstitutionNode node) {
        List<String> otherChoices = new ArrayList<>();
        for (int i = 1; i < node.getSubstitutions().size() - 1; i++) {
            ST choice = currentGroup.getInstanceOf("choice1");
            choice.add("counter", i);
            choice.add("then", machineGenerator.visitSubstitutionNode(node.getSubstitutions().get(i), null));
            otherChoices.add(choice.render());
        }
        return otherChoices;
    }

    public String generateAnyParameters(List<DeclarationNode> parameters, DeclarationNode parameter,
                                        PredicateNode predicateNode, SubstitutionNode substitutionNode, int index, int length) {
        ST substitution = currentGroup.getInstanceOf("any");
        if(index == length - 1) {
            substitution.add("type", typeGenerator.generate(parameter.getType(), false));
            substitution.add("identifier", nameHandler.handle(parameter.getName()));
            if(!(parameter.getType() instanceof BoolType)) {
                substitution.add("set", nameHandler.handleIdentifier(parameter.getType().toString(), NameHandler.IdentifierHandlingEnum.VARIABLES));
            } else {

                substitution.add("set", expressionGenerator.generateBooleans());
            }
            substitution.add("index", index);
            substitution.add("body", generateAnyBody(predicateNode, substitutionNode));
        } else {
            substitution.add("type", typeGenerator.generate(parameter.getType(), false));
            substitution.add("identifier", nameHandler.handle(parameter.getName()));
            if(!(parameter.getType() instanceof BoolType)) {
                substitution.add("set", nameHandler.handleIdentifier(parameter.getType().toString(), NameHandler.IdentifierHandlingEnum.VARIABLES));
            } else {
                substitution.add("set", expressionGenerator.generateBooleans());
            }
            substitution.add("index", index);
            substitution.add("body", generateAnyParameters(parameters, parameters.get(index + 1), predicateNode, substitutionNode, index + 1, length));
        }
        return substitution.render();
    }

    private String generateAnyBody(PredicateNode predicateNode, SubstitutionNode substitutionNode) {
        ST body = currentGroup.getInstanceOf("any_body");
        body.add("predicate", machineGenerator.visitPredicateNode(predicateNode, null));
        body.add("body", machineGenerator.visitSubstitutionNode(substitutionNode, null));
        return body.render();
    }

    /*
    * This function generates code for a list of assignments from the belonging AST node.
    */
    public String visitAssignSubstitutionNode(AssignSubstitutionNode node) {
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
        substitution.add("identifier", machineGenerator.visitIdentifierExprNode((IdentifierExprNode) lhs, null));
        substitution.add("isPrivate", nameHandler.getGlobals().contains(((IdentifierExprNode) lhs).getName()));
        String typeCast = typeGenerator.generate(rhs.getType(), true);
        substitution.add("typeCast", typeCast);
        substitution.add("val", machineGenerator.visitExprNode(rhs, null));
        return substitution.render();
    }

    /*
    * This function generates code for sequential substitution and throws an exception for parallel substitutions as
    * code generation for parallel subsitutition is not supported in the given subset of B.
    * Therefore the belonging AST node is used.
    */
    public String visitListSubstitutionNode(ListSubstitutionNode node) {
        if(node.getOperator() == ListSubstitutionNode.ListOperator.Parallel) {
            return visitParallelSubstitutionNode(node);
        }
        return visitSequentialSubstitutionNode(node);
    }

    private String visitParallelSubstitutionNode(ListSubstitutionNode node) {
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
                .map(substitution -> machineGenerator.visitSubstitutionNode(substitution, null))
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

    private String visitParallelLoads(AssignSubstitutionNode node) {
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

    private String visitParallelLoad(ExprNode expr) {
        ST substitution = currentGroup.getInstanceOf("parallel_load");
        substitution.add("type", typeGenerator.generate(expr.getType(), false));
        substitution.add("identifier", machineGenerator.visitIdentifierExprNode((IdentifierExprNode) expr, null));
        String typeCast = typeGenerator.generate(expr.getType(), true);
        substitution.add("typeCast", typeCast);
        return substitution.render();
    }


    private String visitParallelStores(AssignSubstitutionNode node) {
        ST substitutions = currentGroup.getInstanceOf("assignments");
        List<String> assignments = new ArrayList<>();
        //TODO: For now, the variable on the left-hand side and on the right-hand side must be distinct
        for (int i = 0; i < node.getLeftSide().size(); i++) {
            assignments.add(visitParallelStore(node.getLeftSide().get(i), node.getRightSide().get(i)));
        }
        substitutions.add("assignments", assignments);
        return substitutions.render();
    }

    private String visitParallelStore(ExprNode lhs, ExprNode rhs) {
        ST substitution = currentGroup.getInstanceOf("parallel_store");
        identifierGenerator.setLhsInParallel(true);
        substitution.add("identifier", machineGenerator.visitIdentifierExprNode((IdentifierExprNode) lhs, null));
        substitution.add("isPrivate", nameHandler.getGlobals().contains(((IdentifierExprNode) lhs).getName()));
        identifierGenerator.setLhsInParallel(false);
        String typeCast = typeGenerator.generate(rhs.getType(), true);
        substitution.add("typeCast", typeCast);
        substitution.add("val", machineGenerator.visitExprNode(rhs, null));
        return substitution.render();
    }

    /*
    * This function generates code for a sequential substitution with the belonging AST node.
    */
    private String visitSequentialSubstitutionNode(ListSubstitutionNode node) {
        List<String> substitutionCodes = node.getSubstitutions().stream()
                .map(substitutionNode -> machineGenerator.visitSubstitutionNode(substitutionNode, null))
                .collect(Collectors.toList());
        return String.join("\n", substitutionCodes);
    }

    public String visitBecomesElementOfSubstitutionNode(BecomesElementOfSubstitutionNode node) {
        ST substitutions = currentGroup.getInstanceOf("assignments");
        List<String> assignments = new ArrayList<>();
        for (int i = 0; i < node.getIdentifiers().size(); i++) {
            assignments.add(generateNondeterminism(node.getIdentifiers().get(i), node.getExpression()));
        }
        substitutions.add("assignments", assignments);
        return substitutions.render();
    }

    private String generateNondeterminism(IdentifierExprNode lhs, ExprNode rhs) {
        ST substitution = currentGroup.getInstanceOf("nondeterminism");
        substitution.add("identifier", machineGenerator.visitIdentifierExprNode(lhs, null));
        substitution.add("isPrivate", nameHandler.getGlobals().contains(lhs.getName()));
        String typeCast = typeGenerator.generate(lhs.getType(), true);
        substitution.add("typeCast", typeCast);
        substitution.add("set", machineGenerator.visitExprNode(rhs, null));
        return substitution.render();
    }

    public String visitSubstitutionIdentifierCallNode(OperationCallSubstitutionNode node, Void expected) {
        List<String> variables = node.getAssignedVariables().stream()
                .map(var -> machineGenerator.visitExprNode(var, expected))
                .collect(Collectors.toList());
        String operationName = node.getOperationNode().getName();
        String machineName = operationGenerator.getMachineFromOperation().get(operationName);
        ST functionCall;
        //Size of variables must be less equal than 1 for now.
        //TODO: Implement Records
        if(variables.size() > 0) {
            functionCall = currentGroup.getInstanceOf("operation_call_with_assignment");
            functionCall.add("var", variables.get(0));
            functionCall.add("isPrivate", nameHandler.getGlobals().contains(variables.get(0)));
        } else {
            functionCall = currentGroup.getInstanceOf("operation_call_without_assignment");
        }
        functionCall.add("machine", nameHandler.handle(machineName));
        functionCall.add("function", operationName);
        functionCall.add("args", node.getArguments().stream()
                .map(expr -> machineGenerator.visitExprNode(expr, expected))
                .collect(Collectors.toList()));
        functionCall.add("this", machineName.equals(machineGenerator.getMachineName()));
        return functionCall.render();
    }

    /*
    * This function generates code for a while loop with the belonging AST node and the belonging template.
    */
    public String visitWhileSubstitutionNode(WhileSubstitutionNode node, Void expected) {
        ST whileST = currentGroup.getInstanceOf("while");
        whileST.add("predicate", machineGenerator.visitPredicateNode(node.getCondition(), expected));
        whileST.add("then", machineGenerator.visitSubstitutionNode(node.getBody(), expected));
        return whileST.render();
    }

    /*
    * This function generates from a var substitution with the belonging AST node and template. During this step the
    * flag for local scope is set to true and finally resetted to false. This is needed for handling collisions between
    * local variables and output parameters.
    */
    public String visitVarSubstitutionNode(VarSubstitutionNode node, Void expected) {
        ST varST = currentGroup.getInstanceOf("var");
        this.localScopes++;
        this.currentLocalScope++;
        identifierGenerator.push(localScopes);
        node.getLocalIdentifiers().forEach(identifier -> identifierGenerator.addLocal(identifier.getName()));
        varST.add("locals", generateVariablesInVar(node.getLocalIdentifiers()));
        varST.add("body", machineGenerator.visitSubstitutionNode(node.getBody(), expected));
        identifierGenerator.pop();
        node.getLocalIdentifiers().forEach(identifier -> identifierGenerator.resetLocal(identifier.getName()));
        this.currentLocalScope--;
        return varST.render();
    }

    /*
    * This function generates code for all declarations of local variables from a var substitution from the list of identifiers as AST nodes.
    */
    private List<String> generateVariablesInVar(List<DeclarationNode> identifiers) {
        return identifiers.stream()
                .map(this::generateVariableInVar)
                .collect(Collectors.toList());
    }

    /*
    * This function generates code for one declaration of a local variable from a var substitution node from the belonging AST node and template.
    */
    private String generateVariableInVar(DeclarationNode identifier) {
        ST declaration = currentGroup.getInstanceOf("local_declaration");
        declaration.add("type", typeGenerator.generate(identifier.getType(), false));
        declaration.add("identifier", identifierGenerator.generateVarDeclaration(identifier.getName()));
        return declaration.render();
    }

    public int getCurrentLocalScope() {
        return currentLocalScope;
    }

    public void resetParallel() {
        definedLoadsInParallel.clear();
        identifierOnLhsInParallel.clear();
    }

    public void setOperationGenerator(OperationGenerator operationGenerator) {
        this.operationGenerator = operationGenerator;
    }
}
