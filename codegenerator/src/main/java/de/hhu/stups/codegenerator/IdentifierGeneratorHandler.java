package de.hhu.stups.codegenerator;


import de.prob.parser.ast.nodes.DeclarationNode;
import de.prob.parser.ast.nodes.expression.IdentifierExprNode;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;
import java.util.stream.Collectors;

public class IdentifierGeneratorHandler {


    public static String generate(IdentifierExprNode node, List<DeclarationNode> locals, STGroup template) {
        if(locals.stream()
            .map(DeclarationNode::getName)
            .collect(Collectors.toList())
            .contains(node.toString())) {
            return generate(node, template, true);
        } else {
            return generate(node, template, false);
        }
    }

    private static String generate(IdentifierExprNode node, STGroup template, boolean isReturn) {
        ST identifier = template.getInstanceOf("identifier");
        identifier.add("identifier", node.getName());
        identifier.add("isReturn", isReturn);
        return identifier.render();
    }

}
