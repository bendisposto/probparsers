package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;
import java.util.stream.Collectors;

public class IdentifierGeneratorHandler {


    public static String generate(IdentifierExprNode node, List<DeclarationNode> locals, List<DeclarationNode> globals, STGroup template) {
        boolean isReturn = locals.stream()
                .map(declarationNode -> NameHandler.handle(declarationNode.getName(), template))
                .collect(Collectors.toList())
                .contains(node.toString());

        boolean isPrivate = globals.stream()
                .map(DeclarationNode::getName)
                .collect(Collectors.toList())
                .contains(node.getName());
        return generate(node, template, isReturn, isPrivate);
    }

    private static String generate(IdentifierExprNode node, STGroup template, boolean isReturn, boolean isPrivate) {
        ST identifier = template.getInstanceOf("identifier");
        identifier.add("identifier", NameHandler.handle(node.getName(), template));
        identifier.add("isReturn", isReturn);
        identifier.add("isPrivate", isPrivate);
        return identifier.render();
    }

}
