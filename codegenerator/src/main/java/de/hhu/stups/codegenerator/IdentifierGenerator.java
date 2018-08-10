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


    public String generate(IdentifierExprNode node) {
        boolean isReturn = outputParams.stream()
                .map(declarationNode -> nameHandler.handleIdentifier(declarationNode.getName(), NameHandler.IdentifierHandlingEnum.MACHINES))
                .collect(Collectors.toList())
                .contains(node.toString());

        boolean isPrivate = nameHandler.getGlobals().contains(node.getName());
        return generate(node, isReturn, isPrivate);
    }

    private String generate(IdentifierExprNode node, boolean isReturn, boolean isPrivate) {
        ST identifier = group.getInstanceOf("identifier");
        identifier.add("identifier", nameHandler.handleIdentifier(node.getName(), NameHandler.IdentifierHandlingEnum.MACHINES));
        identifier.add("isReturn", isReturn);
        identifier.add("isPrivate", isPrivate);
        return identifier.render();
    }

    public String generateVarDeclaration(String name) {
        ST identifier = group.getInstanceOf("identifier");
        String resultIdentifier = nameHandler.handleIdentifier(name, NameHandler.IdentifierHandlingEnum.MACHINES);
        if(locals.keySet().contains(name)) {
            for (int i = 0; i < locals.get(name); i++) {
                resultIdentifier = "_" + resultIdentifier;
            }
        }
        identifier.add("identifier", resultIdentifier);
        identifier.add("isReturn", false);
        identifier.add("isPrivate", false);
        return identifier.render();
    }

    public void setOutputParams(List<DeclarationNode> outputParams){
        this.outputParams = outputParams;
        this.locals.clear();
        List<String> outputs = outputParams.stream()
                .map(output -> nameHandler.handleIdentifier(output.getName(), NameHandler.IdentifierHandlingEnum.MACHINES))
                .collect(Collectors.toList());
        for(String output : outputs) {
            locals.put(output, 0);
        }
    }

    public void addLocal(String local) {
        if(locals.keySet().contains(local)) {
            int value = locals.get(local);
            locals.put(local, value + 1);
        } else {
            locals.put(local, 0);
        }
    }

}
