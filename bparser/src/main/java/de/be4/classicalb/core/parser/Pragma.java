package de.be4.classicalb.core.parser;

import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class Pragma {
	private final String content;
	private final Node predecessor;
	private final Node container;
	private final SourcePosition start;
	private final SourcePosition end;
	private final Node nearest;


	public Pragma(Pragma p, Node nearest, Node predecessor, Node container) {
		this.nearest = nearest;
		this.predecessor = predecessor;
		this.container = container;
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
		nearest = null;
	}

	@Override
	public String toString() {
		return "Pragma '"+content + "' " + start + "-" + end + ". Right after '"+getNearest().getClass().getSimpleName()+"/"
				+ getPredecessor().getClass().getSimpleName() + "' and inside '" + getContainer().getClass().getSimpleName() + "'";
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

	public Node getNearest() {
		return nearest;
	}

}