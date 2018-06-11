package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.DIVIDE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.FALSE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.INTERSECTION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.INTERVAL;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MINUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MOD;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.MULT;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.PLUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.SET_ENUMERATION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.SET_SUBTRACTION;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.TRUE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.UNARY_MINUS;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.UNION;


public class ExpressionOperatorGenerator {

    private static final List<ExpressionOperatorNode.ExpressionOperator> BINARY_ARITHMETIC_OPERATORS =
        Arrays.asList(PLUS,MINUS,MULT,DIVIDE,MOD,INTERSECTION, UNION, SET_SUBTRACTION);

    private static final List<ExpressionOperatorNode.ExpressionOperator> UNARY_ARITHMETIC_OPERATORS =
            Arrays.asList(UNARY_MINUS);

    private static final List<ExpressionOperatorNode.ExpressionOperator> BOOLEANS =
        Arrays.asList(TRUE,FALSE);

    public static String generate(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        ExpressionOperatorNode.ExpressionOperator operator = node.getOperator();
        if(BINARY_ARITHMETIC_OPERATORS.contains(operator)) {
            return generateBinaryExpression(node, expressionList, template);
        } else if(UNARY_ARITHMETIC_OPERATORS.contains(operator)) {
            return generateUnaryExpression(node, expressionList, template);
        } else if(BOOLEANS.contains(operator)) {
            return generateBoolean(operator, template);
        } else if(node.getOperator() == SET_ENUMERATION){
            return generateSetEnumeration(expressionList, template);
        } else if(node.getOperator() == INTERVAL) {
            return generateInterval(expressionList, template);
        }
        return "";
    }


    private static String generateUnaryExpression(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        ST arithmetic = generateUnaryArithmetic(node.getOperator(), template);
        arithmetic.add("arg", expressionList.get(0));
        return arithmetic.render();
    }

    private static String generateBinaryExpression(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        Optional<String> result = expressionList.stream()
            .reduce((a, e) -> {
                ST arithmetic = generateBinaryArithmetic(node.getOperator(), template);
                arithmetic.add("arg1", a);
                arithmetic.add("arg2", e);
                return arithmetic.render();
            });
        return result.isPresent() ? result.get() : "";
    }

    private static ST generateUnaryArithmetic(ExpressionOperatorNode.ExpressionOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("unary_arithmetic");
        switch (operator) {
            case UNARY_MINUS:
                template.add("operator", "negative");
                break;
            default:
                template = new ST("");
                break;
        }
        return template;
    }

    private static ST generateBinaryArithmetic(ExpressionOperatorNode.ExpressionOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary_arithmetic");
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
                template = new ST("");
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

    private static String generateSetEnumeration(List<String> expressions, STGroup template) {
        return template.getInstanceOf("set_enumeration").add("enums", expressions).render();
    }

    private static String generateBoolean(ExpressionOperatorNode.ExpressionOperator operator, STGroup template) {
        return template.getInstanceOf("boolean_val").add("val", operator == TRUE).render();
    }

}
