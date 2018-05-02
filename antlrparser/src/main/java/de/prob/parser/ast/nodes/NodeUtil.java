package de.prob.parser.ast.nodes;

public class NodeUtil {
    private NodeUtil() {
    }

    public static boolean isSameClass(Node node1, Node node2) {
        return node1 == node2
            || (node1 != null
            && node2 != null
            && node1.getClass() == node2.getClass());
    }

}
