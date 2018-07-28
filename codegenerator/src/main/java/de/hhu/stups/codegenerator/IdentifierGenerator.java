package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;
import java.util.stream.Collectors;

public class IdentifierGenerator {

    private final STGroup group;

    private final NameHandler nameHandler;

    public IdentifierGenerator(final STGroup group, final NameHandler nameHandler) {
        this.group = group;
        this.nameHandler = nameHandler;
    }


    public String generate(IdentifierExprNode node, List<DeclarationNode> outputs, List<String> globals) {
        boolean isReturn = outputs.stream()
                .map(declarationNode -> nameHandler.handle(declarationNode.getName()))
                .collect(Collectors.toList())
                .contains(node.toString());

        boolean isPrivate = globals.contains(node.getName());
        return generate(node, isReturn, isPrivate);
    }

    private String generate(IdentifierExprNode node, boolean isReturn, boolean isPrivate) {
        ST identifier = group.getInstanceOf("identifier");
        identifier.add("identifier", nameHandler.handle(node.getName()));
        identifier.add("isReturn", isReturn);
        identifier.add("isPrivate", isPrivate);
        return identifier.render();
    }

}
