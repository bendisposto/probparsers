package de.hhu.stups.codegenerator;

import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static de.prob.parser.ast.nodes.predicate.PredicateOperatorNode.PredicateOperator.AND;
import static de.prob.parser.ast.nodes.predicate.PredicateOperatorNode.PredicateOperator.IMPLIES;
import static de.prob.parser.ast.nodes.predicate.PredicateOperatorNode.PredicateOperator.OR;
import static de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.EQUAL;

/**
 * Created by fabian on 29.05.18.
 */
public class PredicateOperatorGenerator {

    private static final List<PredicateOperatorNode.PredicateOperator> PREDICATE_OPERATIONS =
            Arrays.asList(AND, OR, IMPLIES);

    private static final List<PredicateOperatorWithExprArgsNode.PredOperatorExprArgs> PREDICATE_OPERATIONS_ARGS =
            Arrays.asList(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.LESS, PredicateOperatorWithExprArgsNode.PredOperatorExprArgs.GREATER, EQUAL);

    public static String generatePredicateExpression(PredicateOperatorNode.PredicateOperator operator, List<String> predicates, STGroup template) {
        Optional<String> result = predicates.stream()
                //TODO
                .reduce((a, e) -> {
                    ST predicate = generateBinaryArithmetic(operator, template);
                    predicate.add("arg1", a);
                    predicate.add("arg2", e);
                    return predicate.render();
                });
        return result.isPresent() ? result.get() : "";
    }

    public static String generatePredicateExpression(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs operator, List<String> predicates, STGroup template) {
        Optional<String> result = predicates.stream()
                //TODO
                .reduce((a, e) -> {
                    ST predicate = generateBinaryArithmetic(operator, template);
                    predicate.add("arg1", a);
                    predicate.add("arg2", e);
                    return predicate.render();
                });
        return result.isPresent() ? result.get() : "";
    }

    private static ST generateBinaryArithmetic(PredicateOperatorNode.PredicateOperator operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary_arithmetic");
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


    private static ST generateBinaryArithmetic(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs operator, STGroup templateGroup) {
        ST template = templateGroup.getInstanceOf("binary_arithmetic");
        switch(operator) {
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

}
