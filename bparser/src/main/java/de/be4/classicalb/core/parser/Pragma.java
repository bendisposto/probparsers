package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class Pragma {
	private final String content;
	private final Node predecessor;
	private final Node container;
	private final SourcePosition start;
	private final SourcePosition end;
	private final Node nearestLeft;
	private final Node successor;
	private final Node nearestRight;

	public Pragma(Pragma p, Node nearestLeft, Node predecessor, Node container, Node successor, Node nearestRight) {
		this.nearestLeft = nearestLeft;
		this.predecessor = predecessor;
		this.container = container;
		this.successor = successor;
		this.nearestRight = nearestRight;
		this.start = p.start;
		this.content = p.content;
		this.end = p.end;
	}

	public Pragma(SourcePosition startPos, SourcePosition endPos,
			String pragmaText) {
		start = startPos;
		end = endPos;
		content = pragmaText;
		container = null;
		predecessor = null;
		nearestLeft = null;
		nearestRight = null;
		successor = null;
	}

	@Override
	public String toString() {
		return "Pragma '" + content + "' " + start + "-" + end
				+ ". Right after '" + name(nearestLeft)
				+ "/" + name(predecessor)
				+ "', inside '" + name(container)
				+ "' and before '"+ name(successor)
				+ "/" + name(nearestRight)
				+ "'" ;
	}
	
	private String name(Node n) {
		if (n == null) return "na";
		return n.getClass().getSimpleName();
	}

	public SourcePosition getEnd() {
		return end;
	}

	public SourcePosition getStart() {
		return start;
	}

	public Node getContainer() {
		return container;
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public Node getNearestLeft() {
		return nearestLeft;
	}
	
	public Node getNearestRight() {
		return nearestRight;
	}
	
	public Node getSuccessor() {
		return successor;
	}

}