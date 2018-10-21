package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.predicate.PredicateOperatorNode;
import de.prob.parser.ast.nodes.predicate.PredicateOperatorWithExprArgsNode;

import java.util.List;
import java.util.stream.Collectors;

public class PredicateGenerator {

    private final MachineGenerator machineGenerator;

    private final ImportGenerator importGenerator;

    private final OperatorGenerator operatorGenerator;

    public PredicateGenerator(MachineGenerator machineGenerator, ImportGenerator importGenerator, OperatorGenerator operatorGenerator) {
        this.machineGenerator = machineGenerator;
        this.importGenerator = importGenerator;
        this.operatorGenerator = operatorGenerator;
    }

    /*
    * This function generates code for a predicate with the belonging AST node
    */
    public String visitPredicateOperatorNode(PredicateOperatorNode node) {
        importGenerator.addImport(node.getType());
        List<String> expressionList = node.getPredicateArguments().stream()
                .map(expr -> machineGenerator.visitPredicateNode(expr, null))
                .collect(Collectors.toList());
        return operatorGenerator.generatePredicate(node, expressionList);
    }

    /*
    * This function generates code for a predicate with expression as arguments with the belonging AST node
    */
    public String visitPredicateOperatorWithExprArgs(PredicateOperatorWithExprArgsNode node) {
        importGenerator.addImport(node.getType());
        List<String> expressionList = node.getExpressionNodes().stream()
                .map(expr -> machineGenerator.visitExprNode(expr, null))
                .collect(Collectors.toList());
        return operatorGenerator.generateBinary(node::getOperator, expressionList);
    }

}
