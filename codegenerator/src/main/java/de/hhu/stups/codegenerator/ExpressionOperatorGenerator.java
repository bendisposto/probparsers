package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.expression.ExprNode;
import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.DIVIDE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.FALSE;
import static de.prob.parser.ast.nodes.expression.ExpressionOperatorNode.ExpressionOperator.INTERSECTION;
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

    private static final List<ExpressionOperatorNode.ExpressionOperator> ARITHMETIC_OPERATORS =
        Arrays.asList(PLUS,MINUS,MULT,DIVIDE,MOD,UNARY_MINUS, INTERSECTION, UNION, SET_SUBTRACTION);

    private static final List<ExpressionOperatorNode.ExpressionOperator> BOOLEANS =
        Arrays.asList(TRUE,FALSE);

    public static String generate(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        ExpressionOperatorNode.ExpressionOperator operator = node.getOperator();
        if(ARITHMETIC_OPERATORS.contains(operator)) {
            return generateArithmeticExpression(node, expressionList, template);
        } else if(BOOLEANS.contains(operator)) {
            return generateBoolean(operator, template);
        } else if(node.getOperator() == SET_ENUMERATION){
            return generateSetEnumeration(node, template);
        }
        return "";
    }

    private static String generateArithmeticExpression(ExpressionOperatorNode node, List<String> expressionList, STGroup template) {
        if(expressionList.size() == 1) {
            ST arithmetic = generateArithmetic(node.getOperator(), template);
            arithmetic.add("arg", expressionList.get(0));
            return arithmetic.render();
        }
        Optional<String> result = expressionList.stream()
            //TODO
            .reduce((a, e) -> {
                ST arithmetic = generateArithmetic(node.getOperator(), template);
                arithmetic.add("arg1", a);
                arithmetic.add("arg2", e);
                return arithmetic.render();
            });
        return result.isPresent() ? result.get() : "";
    }

    private static ST generateArithmetic(ExpressionOperatorNode.ExpressionOperator operator, STGroup template) {
        switch(operator) {
            case PLUS:
                return template.getInstanceOf("plus");
            case MINUS:
                return template.getInstanceOf("minus");
            case MULT:
                return template.getInstanceOf("mult");
            case DIVIDE:
                return template.getInstanceOf("div");
            case MOD:
                return template.getInstanceOf("modulo");
            case UNARY_MINUS:
                return template.getInstanceOf("unary_minus");
            case INTERSECTION:
                return template.getInstanceOf("intersect");
            case UNION:
                return template.getInstanceOf("union");
            case SET_SUBTRACTION:
                return template.getInstanceOf("complement");
            default:
                break;
        }
        return new ST("");
    }

    private static String generateSetEnumeration(ExpressionOperatorNode node, STGroup template) {
        //TODO
        return template.getInstanceOf("set_enumeration").add("enums", String.join(", ", node.getExpressionNodes().stream()
                .map(ExprNode::toString)
                .collect(Collectors.toList()))).render();
    }

    private static String generateBoolean(ExpressionOperatorNode.ExpressionOperator operator, STGroup template) {
        if(operator == TRUE) {
            return template.getInstanceOf("true_val").render();
        } else {
            return template.getInstanceOf("false_val").render();
        }
    }

}
