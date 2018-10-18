package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class IdentifierGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    private List<DeclarationNode> outputParams;

    private Map<String, Integer> currentLocals;

    private Map<String, Integer> maxLocals;

    private Stack<Integer> stackScope;

    private final List<String> identifierOnLhsInParallel;

    private boolean lhsInParallel;

    public IdentifierGenerator(final STGroup group, final NameHandler nameHandler,
                               final List<String> identifierOnLhsInParallel) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.outputParams = new ArrayList<>();
        this.currentLocals = new HashMap<>();
        this.maxLocals = new HashMap<>();
        this.stackScope = new Stack<>();
        this.identifierOnLhsInParallel = identifierOnLhsInParallel;
        stackScope.push(0);
    }


    /*
    * This function generates code for an identifier.
    * It also calculates whether the identifier is a output parameter and whether it is generated to a private variable within a machine.
    */
    public String generate(IdentifierExprNode node) {
        boolean isReturn = outputParams.stream()
                .map(declarationNode -> declarationNode.getType().toString().startsWith("POW") ?
                        nameHandler.handleIdentifier(declarationNode.getName(), NameHandler.IdentifierHandlingEnum.VARIABLES) :
                        nameHandler.handleIdentifier(declarationNode.getName(), NameHandler.IdentifierHandlingEnum.MACHINES))
                .collect(Collectors.toList())
                .contains(node.toString());

        boolean isPrivate = nameHandler.getGlobals().contains(node.getName());
        return generate(node, isReturn, isPrivate);
    }

    /*
    * This function generates code for an identifier from the template directly with the given information.
    */
    private String generate(IdentifierExprNode node, boolean isReturn, boolean isPrivate) {
        ST identifier = group.getInstanceOf("identifier");
        identifier.add("identifier", node.getType() != null && node.getType().toString().startsWith("POW") ?
                nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.VARIABLES) :
                nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        identifier.add("isReturn", isReturn);
        identifier.add("isPrivate", isPrivate);
        identifier.add("rhsOnLhs", identifierOnLhsInParallel.contains(node.getName()) && !lhsInParallel);
        return identifier.render();
    }

    /*
    * This function generates code for a declaration of a local variable in B.
    */
    public String generateVarDeclaration(String name) {
        ST identifier = group.getInstanceOf("identifier");
        StringBuilder resultIdentifier = new StringBuilder(nameHandler.handleIdentifier(name, NameHandler.IdentifierHandlingEnum.MACHINES));
        if(currentLocals.keySet().contains(name)) {
            for (int i = 0; i < currentLocals.get(name); i++) {
                resultIdentifier.insert(0, "_");
            }
        }
        identifier.add("identifier", resultIdentifier.toString());
        identifier.add("isReturn", false);
        identifier.add("isPrivate", false);
        identifier.add("rhsOnLhs", identifierOnLhsInParallel.contains(name) && !lhsInParallel);
        return identifier.render();
    }

    /*
    * This function sets the output paramters and calculates the generated local variables (collision problem between output parameters and local variables).
    */
    public void setParams(List<DeclarationNode> inputParams, List<DeclarationNode> outputParams){
        this.outputParams = outputParams;
        this.currentLocals.clear();
        this.maxLocals.clear();
        List<String> parameters = outputParams.stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList());
        parameters.addAll(inputParams.stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList()));
        for(String parameter : parameters) {
            currentLocals.put(parameter, 0);
            maxLocals.put(parameter, 0);
        }
    }

    /*
    * This function is needed for solving the collision problem beteween output parameters and local variables.
    */
    public void addLocal(String local) {
        if(maxLocals.keySet().contains(local)) {
            int value = maxLocals.get(local);
            maxLocals.put(local, value + 1);
            currentLocals.put(local, value + 1);
        } else {
            currentLocals.put(local, 0);
        }
    }

    /*
    * This function is needed for solving the collision problem beteween output parameters and local variables.
    */
    public void resetLocal(String local) {
        currentLocals.put(local, stackScope.peek());
    }

    /*
    * Puts an element on the stack handling collision problem between local variables
    */
    public void push(int value) {
        stackScope.push(value);
    }


    /*
    * Pops from the stack handling collision problem between local variables
    */
    public void pop() {
        stackScope.pop();
    }

    public void setLhsInParallel(boolean lhsInParallel) {
        this.lhsInParallel = lhsInParallel;
    }
}
