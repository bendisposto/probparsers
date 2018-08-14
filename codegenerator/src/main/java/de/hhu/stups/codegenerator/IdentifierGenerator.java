package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IdentifierGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    private List<DeclarationNode> outputParams;

    private Map<String, Integer> locals;

    public IdentifierGenerator(final STGroup group, final NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.outputParams = new ArrayList<>();
        this.locals = new HashMap<>();
    }


    /*
    * This function generates code for an identifier.
    * It also calculates whether the identifier is a output parameter and whether it is generated to a private variable within a machine.
    */
    public String generate(IdentifierExprNode node) {
        boolean isReturn = outputParams.stream()
                .map(declarationNode -> nameHandler.handleIdentifier(declarationNode.getName(), NameHandler.IdentifierHandlingEnum.MACHINES))
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
        identifier.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        identifier.add("isReturn", isReturn);
        identifier.add("isPrivate", isPrivate);
        return identifier.render();
    }

    /*
    * This function generates code for a declaration of a local variable in B.
    */
    public String generateVarDeclaration(String name) {
        ST identifier = group.getInstanceOf("identifier");
        StringBuilder resultIdentifier = new StringBuilder(nameHandler.handleIdentifier(name, NameHandler.IdentifierHandlingEnum.MACHINES));
        if(locals.keySet().contains(name)) {
            for (int i = 0; i < locals.get(name); i++) {
                resultIdentifier.insert(0, "_");
            }
        }
        identifier.add("identifier", resultIdentifier.toString());
        identifier.add("isReturn", false);
        identifier.add("isPrivate", false);
        return identifier.render();
    }

    /*
    * This function sets the output paramters and calculates the generated local variables (collision problem between output parameters and local variables).
    */
    public void setParams(List<DeclarationNode> inputParams, List<DeclarationNode> outputParams){
        this.outputParams = outputParams;
        this.locals.clear();
        List<String> parameters = outputParams.stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList());
        parameters.addAll(inputParams.stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList()));
        for(String parameter : parameters) {
            locals.put(parameter, 0);
        }
    }

    /*
    * This function is needed for solving the collision problem beteween output parameters and local variables.
    */
    public void addLocal(String local) {
        if(locals.keySet().contains(local)) {
            int value = locals.get(local);
            locals.put(local, value + 1);
        } else {
            locals.put(local, 0);
        }
    }

}
