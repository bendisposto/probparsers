package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IdentifierGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    private List<DeclarationNode> outputParams;

    public IdentifierGenerator(final STGroup group, final NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
        this.outputParams = new ArrayList<>();
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

    public void setOutputParams(List<DeclarationNode> outputParams){
        this.outputParams = outputParams;
    }

}
