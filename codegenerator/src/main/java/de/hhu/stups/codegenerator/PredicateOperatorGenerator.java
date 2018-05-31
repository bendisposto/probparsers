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


    /*public static ST generate(PredicateOperatorNode node, List<String> expressionList, STGroup template) {
        PredicateOperatorNode.PredicateOperator operator = node.getOperator();
        if(PREDICATE_OPERATIONS.contains(operator)) {
            return generatePredicate(operator, template);
        } else if(PREDICATE_OPERATIONS_ARGS.contains(operator)) {
            return generatePredicateArgs()
        }
        return "";
    }*/

    public static String generatePredicateExpression(PredicateOperatorNode.PredicateOperator operator, List<String> predicates, STGroup template) {
        Optional<String> result = predicates.stream()
                //TODO
                .reduce((a, e) -> {
                    ST predicate = generatePredicate(operator, template);
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
                    ST predicate = generatePredicateArgs(operator, template);
                    predicate.add("arg1", a);
                    predicate.add("arg2", e);
                    return predicate.render();
                });
        return result.isPresent() ? result.get() : "";
    }

    public static ST generatePredicate(PredicateOperatorNode.PredicateOperator operator, STGroup template) {
        switch(operator) {
            case AND:
                return template.getInstanceOf("and");
            case OR:
                return template.getInstanceOf("or");
            case IMPLIES:
                return template.getInstanceOf("implies");
            default:
                break;
        }
        return new ST("");
    }


    public static ST generatePredicateArgs(PredicateOperatorWithExprArgsNode.PredOperatorExprArgs operator, STGroup template) {
        switch(operator) {
            case EQUAL:
                return template.getInstanceOf("equal");
            case LESS:
                return template.getInstanceOf("less");
            case GREATER:
                return template.getInstanceOf("greater");
            default:
                break;
        }
        return new ST("");
    }

}
