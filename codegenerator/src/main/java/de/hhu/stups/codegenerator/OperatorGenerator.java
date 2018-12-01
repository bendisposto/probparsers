package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.expression.ExpressionOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import org.stringtemplate.v4.ST;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class OperatorGenerator {

    @FunctionalInterface
    public interface IOperator {
        Object getOperator();
    }

    /*
    * Generating code for operators in this lists swaps the argument.
    * Example: Consider the predicate for checking whether x is in element in the list Y in B
    * B code: x <: Y (x is on the left-hand side and Y is on the right hand-side in the AST)
    * Generated code: Y.elementOf(x) (x is an argument, while Y is the set where the operation is acted on)
    */
    private static final List<Object> BINARY_SWAP =
            Collections.singletonList(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.ELEMENT_OF);

    private final PredicateGenerator predicateGenerator;

    private final ExpressionGenerator expressionGenerator;

    public OperatorGenerator(final PredicateGenerator predicateGenerator, final ExpressionGenerator expressionGenerator) {
        this.predicateGenerator = predicateGenerator;
        this.predicateGenerator.setOperatorGenerator(this);
        this.expressionGenerator = expressionGenerator;
        this.expressionGenerator.setOperatorGenerator(this);
    }

    /*
    * This function generates code for binary predicates and expressions
    */
    public String generateBinary(IOperator operator, List<String> expressionList) {
        Optional<String> result = expressionList.stream()
            .reduce((a, e) -> {
                Object op = operator.getOperator();
                ST template = getTemplateFromBinaryOperator(op);
                if(template == null) {
                    throw new RuntimeException("Given operator was not implemented: " + op);
                }
                if(BINARY_SWAP.contains(op)) {
                    TemplateHandler.add(template, "arg1", e);
                    TemplateHandler.add(template, "arg2", a);
                } else {
                    TemplateHandler.add(template, "arg1", a);
                    TemplateHandler.add(template, "arg2", e);
                }
                return template.render();
            });
        return result.isPresent() ? result.get() : "";
    }

    /*
    * This function gets the template fro the given binary operator.
    * The given binary operator can be an operator for expressions as well as for predicates.
    */
    private ST getTemplateFromBinaryOperator(Object op) {
        ST template = null;
        if(op instanceof ExpressionOperatorNode.ExpressionOperator) {
            template = expressionGenerator.generateBinary((ExpressionOperatorNode.ExpressionOperator) op);
        } else if(op instanceof PredicateOperatorNode.PredicateOperator) {
            template = predicateGenerator.generateBinary((PredicateOperatorNode.PredicateOperator) op);
        } else if(op instanceof PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) {
            template = predicateGenerator.generateBinary((PredicateOperatorWithExprArgsNode.PredOperatorExprArgs) op);
        }
        return template;
    }

}
