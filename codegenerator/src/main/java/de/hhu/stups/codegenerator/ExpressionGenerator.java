package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.EnumeratedSetElementNode;
import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import de.prob.parser.ast.nodes.expression.NumberNode;
import de.prob.parser.ast.nodes.predicate.CastPredicateExpressionNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpressionGenerator {

    private final STGroup currentGroup;

    private final MachineGenerator machineGenerator;

    private final NameHandler nameHandler;

    private final boolean useBigInteger;

    private final ImportGenerator importGenerator;

    private final DeclarationGenerator declarationGenerator;

    private final IdentifierGenerator identifierGenerator;

    private final SubstitutionGenerator substitutionGenerator;

    private final OperatorGenerator operatorGenerator;

    public ExpressionGenerator(final STGroup currentGroup, final MachineGenerator machineGenerator, boolean useBigInteger, final NameHandler nameHandler,
                               final ImportGenerator importGenerator, final DeclarationGenerator declarationGenerator,
                               final IdentifierGenerator identifierGenerator, final SubstitutionGenerator substitutionGenerator,
                               final OperatorGenerator operatorGenerator) {
        this.currentGroup = currentGroup;
        this.machineGenerator = machineGenerator;
        this.useBigInteger = useBigInteger;
        this.nameHandler = nameHandler;
        this.importGenerator = importGenerator;
        this.declarationGenerator = declarationGenerator;
        this.identifierGenerator = identifierGenerator;
        this.substitutionGenerator = substitutionGenerator;
        this.operatorGenerator = operatorGenerator;
    }

    /*
    * This function generates code from an expression in B.
    */
    public String visitExprNode(ExprNode node) {
        importGenerator.addImport(node.getType());
        if (node instanceof NumberNode) {
            return visitNumberNode((NumberNode) node);
        } else if (node instanceof ExpressionOperatorNode) {
            return visitExprOperatorNode((ExpressionOperatorNode) node);
        } else if (node instanceof EnumeratedSetElementNode) {
            EnumeratedSetElementNode element = (EnumeratedSetElementNode) node;
            return declarationGenerator.callEnum(element.getType().toString(), element.getDeclarationNode());
        } else if(node instanceof IdentifierExprNode) {
            Map<String, List<String>> enumTypes = nameHandler.getEnumTypes();
            if(enumTypes.keySet().contains(node.getType().toString()) &&
                    enumTypes.get(node.getType().toString()).contains(((IdentifierExprNode) node).getName())) {
                return declarationGenerator.callEnum(node.getType().toString(), ((IdentifierExprNode) node).getDeclarationNode());
            }
            return visitIdentifierExprNode((IdentifierExprNode) node);
        } else if(node instanceof CastPredicateExpressionNode) {
            return visitCastPredicateExpressionNode((CastPredicateExpressionNode) node);
        }
        throw new RuntimeException("Given node is not implemented: " + node.getClass());
    }

    /*
    * This function generates code for an expression.
    */
    public String visitExprOperatorNode(ExpressionOperatorNode node) {
        List<String> expressionList = node.getExpressionNodes().stream()
                .map(this::visitExprNode)
                .collect(Collectors.toList());
        return operatorGenerator.generateExpression(node, expressionList);
    }

    /*
    * This function generates code for an identifier from the belonging AST node.
    */
    public String visitIdentifierExprNode(IdentifierExprNode node) {
        if(substitutionGenerator.getCurrentLocalScope() > 0) {
            return identifierGenerator.generateVarDeclaration(node.getName());
        }
        return identifierGenerator.generate(node);
    }

    /*
    * This function generates code for numbers from the belonging AST node and the belonging template
    */
    public String visitNumberNode(NumberNode node) {
        ST number = currentGroup.getInstanceOf("number");
        number.add("number", node.getValue().toString());
        number.add("useBigInteger", useBigInteger);
        return number.render();
    }

    /*
    * This function generates code for cast predicates
    */
    public String visitCastPredicateExpressionNode(CastPredicateExpressionNode node) {
        return machineGenerator.visitPredicateNode(node.getPredicate(), null);
    }

}
