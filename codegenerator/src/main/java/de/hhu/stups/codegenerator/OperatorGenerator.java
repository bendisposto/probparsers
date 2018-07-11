package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    private static final List<ExpressionOperatorNode.ExpressionOperator> BINARY_EXPRESSION_OPERATORS =
        Arrays.asList(PLUS,MINUS,MULT,DIVIDE,MOD,INTERSECTION, UNION, SET_SUBTRACTION);

    private static final List<ExpressionOperatorNode.ExpressionOperator> UNARY_EXPRESSION_OPERATORS =
            Arrays.asList(UNARY_MINUS);

    private static final List<ExpressionOperatorNode.ExpressionOperator> BOOLEANS =
            Arrays.asList(TRUE,FALSE);

    private static final List<Object> BINARY_SWAP =
            Arrays.asList(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.ELEMENT_OF);

    public static String generateExpression(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        ExpressionOperatorNode.ExpressionOperator operator = node.getOperator();
        if(BINARY_EXPRESSION_OPERATORS.contains(operator)) {
            return generateBinary(() -> operator, expressionList, template);
        } else if(UNARY_EXPRESSION_OPERATORS.contains(operator)) {
            return generateUnaryExpression(operator, expressionList, template);
        } else if(BOOLEANS.contains(operator)) {
            return generateBoolean(operator, template);
        } else if(node.getOperator() == SET_ENUMERATION){
            return generateSetEnumeration(expressionList, template);
        } else if(node.getOperator() == INTERVAL) {
            return generateInterval(expressionList, template);
        } else if(node.getOperator() == COUPLE) {
            return generateCouple(expressionList, template);
        } else if(node.getOperator() == FUNCTION_CALL) {
            return generateFunctionCall(node, expressionList, template);
        } else if(node.getOperator() == RELATIONAL_IMAGE) {
            return generateRelationImage(node, expressionList, template);
        }
        return "";
    }


    private static String generateUnaryExpression(ExpressionOperatorNode.ExpressionOperator operator, List<String> expressionList, STGroup template) {
        ST expression = generateUnary(operator, template);
        expression.add("arg", expressionList.get(0));
        return expression.render();
    }

    public static String generateBinary(IOperator operator, List<String> expressionList, STGroup template) {
        Optional<String> result = expressionList.stream()
            .reduce((a, e) -> {
                Object op = operator.getOperator();
                ST expression = null;
                if(op instanceof ExpressionOperatorNode.ExpressionOperator) {
                    expression = generateBinary((ExpressionOperatorNode.ExpressionOperator) op, template);
                } else if(op instanceof PredicateOperatorNode.PredicateOperator) {
                    expression = generateBinary((PredicateOperatorNode.PredicateOperator) op, template);
                } else if(op instanceof PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) {
                    expression = generateBinary((PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) op, template);
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

    private static ST generateUnary(ExpressionOperatorNode.ExpressionOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("unary");
        switch (operator) {
            case UNARY_MINUS:
                template.add("operator", "negative");
                break;
            default:
                break;
        }
        return template;
    }

    private static ST generateBinary(ExpressionOperatorNode.ExpressionOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary");
        switch(operator) {
            case PLUS:
                template.add("operator", "plus");
                break;
            case MINUS:
                template.add("operator", "minus");
                break;
            case MULT:
                template.add("operator", "multiply");
                break;
            case DIVIDE:
                template.add("operator", "divide");
                break;
            case MOD:
                template.add("operator", "modulo");
                break;
            case INTERSECTION:
                template.add("operator", "intersect");
                break;
            case UNION:
                template.add("operator", "union");
                break;
            case SET_SUBTRACTION:
                template.add("operator", "complement");
                break;
            default:
                break;
        }
        return template;
    }

    private static ST generateBinary(PredicateOperatorNode.PredicateOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary");
        switch(operator) {
            case AND:
                template.add("operator", "and");
                break;
            case OR:
                template.add("operator", "or");
                break;
            case IMPLIES:
                template.add("operator", "implies");
                break;
            default:
                break;
        }
        return template;
    }


    private static ST generateBinary(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary");
        switch(operator) {
            case ELEMENT_OF:
                template.add("operator", "elementOf");
                break;
            case EQUAL:
                template.add("operator", "equal");
                break;
            case LESS:
                template.add("operator", "less");
                break;
            case GREATER:
                template.add("operator", "greater");
                break;
            default:
                break;
        }
        return template;
    }

    private static String generateInterval(List<String> arguments, STGroup template) {
        ST interval = template.getInstanceOf("interval");
        interval.add("arg1", arguments.get(0));
        interval.add("arg2", arguments.get(1));
        return interval.render();
    }

    private static String generateCouple(List<String> arguments, STGroup template) {
        ST couple = template.getInstanceOf("couple_create");
        couple.add("arg1", arguments.get(0));
        couple.add("arg2", arguments.get(1));
        return couple.render();
    }

    private static String generateSetEnumeration(List<String> expressions, STGroup template) {
        return template.getInstanceOf("set_enumeration").add("enums", expressions).render();
    }

    private static String generateBoolean(ExpressionOperatorNode.ExpressionOperator operator, STGroup template) {
        return template.getInstanceOf("boolean_val").add("val", operator == TRUE).render();
    }

    private static String generateFunctionCall(ExpressionOperatorNode node, List<String> arguments, STGroup template) {
        ST functionCall = template.getInstanceOf("expression_function_call");
        functionCall.add("function", node.getExpressionNodes().get(0));
        functionCall.add("args", arguments.subList(1, arguments.size()));
        return functionCall.render();
    }

    private static String generateRelationImage(ExpressionOperatorNode node, List<String> arguments, STGroup template) {
        ST relationImage = template.getInstanceOf("relation_image");
        relationImage.add("function", node.getExpressionNodes().get(0));
        relationImage.add("args", arguments.subList(1, arguments.size()));
        return relationImage.render();
    }

}
