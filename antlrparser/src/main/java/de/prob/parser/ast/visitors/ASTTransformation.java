package de.prob.parser.ast.visitors;

import de.prob.parser.ast.nodes.Node;

public interface ASTTransformation {
    
    boolean canHandleNode(Node node);

    Node transformNode(Node node);
}
