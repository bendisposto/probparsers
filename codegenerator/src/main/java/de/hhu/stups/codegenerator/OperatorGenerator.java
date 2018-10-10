package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.CARD;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.COUPLE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.DIVIDE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.FALSE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.FUNCTION_CALL;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.INTERSECTION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.INTERVAL;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MINUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MOD;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MULT;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.PLUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.RELATIONAL_IMAGE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.SET_ENUMERATION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.SET_SUBTRACTION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.TRUE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.UNARY_MINUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.UNION;


public class OperatorGenerator {

    @FunctionalInterface
    public interface IOperator {
        Object getOperator();
    }

    /*
    * Hard-coded lists for identifying the type of the operators for expresion and predicates
    */
    private static final List<ExpressionOperatorNode.ExpressionOperator> BINARY_EXPRESSION_OPERATORS =
            Arrays.asList(PLUS,MINUS,MULT,DIVIDE,MOD,INTERSECTION, UNION, SET_SUBTRACTION);

    private static final List<ExpressionOperatorNode.ExpressionOperator> UNARY_EXPRESSION_OPERATORS =
            Arrays.asList(UNARY_MINUS, CARD, RELATIONAL_IMAGE, FUNCTION_CALL);

    private static final List<PredicateOperatorNode.PredicateOperator> BINARY_PREDICATE_OPERATORS =
            Arrays.asList(PredicateOperatorNode.PredicateOperator.AND, PredicateOperatorNode.PredicateOperator.OR,
                        PredicateOperatorNode.PredicateOperator.IMPLIES, PredicateOperatorNode.PredicateOperator.EQUIVALENCE);

    private static final List<PredicateOperatorNode.PredicateOperator> UNARY_PREDICATE_OPERATORS =
            Collections.singletonList(PredicateOperatorNode.PredicateOperator.NOT);

    private static final List<ExpressionOperatorNode.ExpressionOperator> EXPRESSION_BOOLEANS =
            Arrays.asList(TRUE,FALSE);

    private static final List<PredicateOperatorNode.PredicateOperator> PREDICATE_BOOLEANS =
            Arrays.asList(PredicateOperatorNode.PredicateOperator.TRUE, PredicateOperatorNode.PredicateOperator.FALSE);

    /*
    * Generating code for operators in this lists swaps the argument.
    * Example: Consider the predicate for checking whether x is in element in the list Y in B
    * B code: x <: Y (x is on the left-hand side and Y is on the right hand-side in the AST)
    * Generated code: Y.elementOf(x) (x is an argument, while Y is the set where the operation is acted on)
    */
    private static final List<Object> BINARY_SWAP =
            Collections.singletonList(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.ELEMENT_OF);

    private final STGroup group;

    private final NameHandler nameHandler;

    public OperatorGenerator(final STGroup group, final NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
    }

    /*
    * This function generates code for an expression with the given AST node and the list of expressions within the expression.
    */
    public String generateExpression(ExpressionOperatorNode node, List<String> expressionList) {
        ExpressionOperatorNode.ExpressionOperator operator = node.getOperator();
        if(BINARY_EXPRESSION_OPERATORS.contains(operator)) {
            return generateBinary(() -> operator, expressionList);
        } else if(UNARY_EXPRESSION_OPERATORS.contains(operator)) {
            return generateUnaryExpression(operator, expressionList);
        } else if(EXPRESSION_BOOLEANS.contains(operator)) {
            return generateBoolean(operator);
        } else if(node.getOperator() == SET_ENUMERATION){
            return generateSetEnumeration(expressionList);
        } else if(node.getOperator() == INTERVAL) {
            return generateInterval(expressionList);
        } else if(node.getOperator() == COUPLE) {
            return generateCouple(expressionList);
        }
        throw new RuntimeException("Given operator is not implemented: " + node.getOperator());
    }

    /*
    * This function generates code for a predicate with the given AST node and the list of expresions within the predicate.
    */
    public String generatePredicate(PredicateOperatorNode node, List<String> expressionList) {
        PredicateOperatorNode.PredicateOperator operator = node.getOperator();
        if(BINARY_PREDICATE_OPERATORS.contains(operator)) {
            return generateBinary(() -> operator, expressionList);
        } else if(UNARY_PREDICATE_OPERATORS.contains(operator)) {
            return generateUnaryPredicate(operator, expressionList);
        } else if (PREDICATE_BOOLEANS.contains(operator)) {
            return generateBoolean(operator);
        }
        throw new RuntimeException("Given operator is not implemented: " + node.getOperator());
    }


    /*
    * This function generates code for an unary expression with the given operator and arguments.
    */
    private String generateUnaryExpression(ExpressionOperatorNode.ExpressionOperator operator, List<String> expressionList) {
        ST expression = generateUnary(operator);
        expression.add("obj", expressionList.get(0));
        expression.add("args", expressionList.subList(1, expressionList.size()));
        return expression.render();
    }

    /*
    * This function generates code for an unary predicate with the given operator and arguments.
    */
    private String generateUnaryPredicate(PredicateOperatorNode.PredicateOperator operator, List<String> expressionList) {
        ST expression = generateUnary(operator);
        expression.add("obj", expressionList.get(0));
        expression.add("args", expressionList.subList(1, expressionList.size()));
        return expression.render();
    }

    /*
    * This function generates code for binary predicates and expressions
    */
    public String generateBinary(IOperator operator, List<String> expressionList) {
        Optional<String> result = expressionList.stream()
            .reduce((a, e) -> {
                Object op = operator.getOperator();
                ST expression = getTemplateFromBinaryOperator(op);
                if(expression == null) {
                    throw new RuntimeException("Given operator was not implemented: " + op);
                }
                if(BINARY_SWAP.contains(op)) {
                    expression.add("arg1", e);
                    expression.add("arg2", a);
                } else {
                    expression.add("arg1", a);
                    expression.add("arg2", e);
                }
                return expression.render();
            });
        return result.isPresent() ? result.get() : "";
    }

    /*
    * This function gets the template fro the given binary operator.
    * The given binary operator can be an operator for expressions as well as for predicates.
    */
    private ST getTemplateFromBinaryOperator(Object op) {
        ST expression = null;
        if(op instanceof ExpressionOperatorNode.ExpressionOperator) {
            expression = generateBinary((ExpressionOperatorNode.ExpressionOperator) op);
        } else if(op instanceof PredicateOperatorNode.PredicateOperator) {
            expression = generateBinary((PredicateOperatorNode.PredicateOperator) op);
        } else if(op instanceof PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) {
            expression = generateBinary((PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) op);
        }
        return expression;
    }

    /*
    * This function gets the template for unary expressions and replaces the placeholder with the given operator.
    */
    private ST generateUnary(ExpressionOperatorNode.ExpressionOperator operator) {
        ST template = group.getInstanceOf("unary");
        String operatorName;
        switch (operator) {
            case UNARY_MINUS:
                operatorName = "negative";
                break;
            case CARD:
                operatorName = "card";
                break;
            case FUNCTION_CALL:
                operatorName = "functionCall";
                break;
            case RELATIONAL_IMAGE:
                operatorName = "relationImage";
                break;
            default:
                throw new RuntimeException("Given operator is not implemented: " + operator);
        }
        template.add("operator", nameHandler.handle(operatorName));
        return template;
    }

    /*
    * This function gets the template for binary expressions and replaces the placeholder with the given operator.
    */
    private ST generateBinary(ExpressionOperatorNode.ExpressionOperator operator) {
        ST template = group.getInstanceOf("binary");
        String operatorName;
        switch(operator) {
            case PLUS:
                operatorName = "plus";
                break;
            case MINUS:
                operatorName = "minus";
                break;
            case MULT:
                operatorName = "multiply";
                break;
            case DIVIDE:
                operatorName = "divide";
                break;
            case MOD:
                operatorName = "modulo";
                break;
            case INTERSECTION:
                operatorName = "intersect";
                break;
            case UNION:
                operatorName = "union";
                break;
            case SET_SUBTRACTION:
                operatorName = "complement";
                break;
            default:
                throw new RuntimeException("Given operator is not implemented: " + operator);
        }
        template.add("operator", nameHandler.handle(operatorName));
        return template;
    }

    /*
    * This functions gets the template for unary predicates and replaces the placeholder with the given operator.
    */
    private ST generateUnary(PredicateOperatorNode.PredicateOperator operator) {
        ST template = group.getInstanceOf("unary");
        String operatorName;
        switch(operator) {
            case NOT:
                operatorName = "not";
                break;
            default:
                throw new RuntimeException("Given node is not implemented: " + operator);
        }
        template.add("operator", nameHandler.handle(operatorName));
        return template;
    }

    /*
    * This functions gets the template for binary predicates and replaces the placeholder with the given operator.
    */
    private ST generateBinary(PredicateOperatorNode.PredicateOperator operator) {
        ST template = group.getInstanceOf("binary");
        String operatorName;
        switch(operator) {
            case AND:
                operatorName = "and";
                break;
            case OR:
                operatorName = "or";
                break;
            case IMPLIES:
                operatorName = "implies";
                break;
            case EQUIVALENCE:
                operatorName = "equivalent";
                break;
            default:
                throw new RuntimeException("Given node is not implemented: " + operator);
        }
        template.add("operator", nameHandler.handle(operatorName));
        return template;
    }

    /*
    * This functions gets the template for binary predicates with expression arguments and replaces the placeholder with the given operator.
    */
    private ST generateBinary(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs operator) {
        ST template = group.getInstanceOf("binary");
        String operatorName;
        switch(operator) {
            case ELEMENT_OF:
                operatorName = "elementOf";
                break;
            case EQUAL:
                operatorName = "equal";
                break;
            case NOT_EQUAL:
                operatorName = "unequal";
                break;
            case LESS:
                operatorName = "less";
                break;
            case LESS_EQUAL:
                operatorName = "lessEqual";
                break;
            case GREATER:
                operatorName = "greater";
                break;
            case GREATER_EQUAL:
                operatorName = "greaterEqual";
                break;
            default:
                throw new RuntimeException("Given node is not implemented: " + operator);
        }
        template.add("operator", nameHandler.handle(operatorName));
        return template;
    }

    /*
    * This function generates code for an interval with the given arguments.
    */
    private String generateInterval(List<String> arguments) {
        ST interval = group.getInstanceOf("interval");
        interval.add("arg1", arguments.get(0));
        interval.add("arg2", arguments.get(1));
        return interval.render();
    }

    /*
    * This function generates code for a couple with the given arguments.
    */
    private String generateCouple(List<String> arguments) {
        ST couple = group.getInstanceOf("couple_create");
        couple.add("arg1", arguments.get(0));
        couple.add("arg2", arguments.get(1));
        return couple.render();
    }

    /*
    * This function generates code for set enumerations with the given arguments.
    */
    private String generateSetEnumeration(List<String> expressions) {
        return group.getInstanceOf("set_enumeration").add("enums", expressions).render();
    }

    /*
    * This function generates code for boolean constants as expressions.
    */
    private String generateBoolean(ExpressionOperatorNode.ExpressionOperator operator) {
        return group.getInstanceOf("boolean_val").add("val", operator == TRUE).render();
    }

    /*
    * This function generates code for boolean constants as predicates.
    */
    private String generateBoolean(PredicateOperatorNode.PredicateOperator operator) {
        return group.getInstanceOf("boolean_val").add("val", operator == PredicateOperatorNode.PredicateOperator.TRUE).render();
    }

}
