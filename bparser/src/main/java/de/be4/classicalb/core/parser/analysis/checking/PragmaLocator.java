package de.be4.classicalb.core.parser.analysis.checking;

import de.be4.classicalb.core.parser.Pragma;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PragmaLocator extends DepthFirstAdapter {

	private final SourcePosition end;
	private final SourcePosition start;
	private Node nearest;
	private Node predecessor;
	private Node container;

	public PragmaLocator(SourcePosition start, SourcePosition end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void inStart(Start node) {
		nearest = node;
		container = node;
	}

	@Override
	public void defaultOut(Node node) {
		if (node instanceof Start) return; // no source info available
		SourcePosition endPos = node.getEndPos();
		if (endPos.compareTo(start) <= 0) predecessor = node;
	}

	@Override
	public void defaultIn(Node node) {
		Node n = node;
		SourcePosition startPos = node.getStartPos();
		SourcePosition endPos = node.getEndPos();
		SourcePosition s = start;
		SourcePosition e = end;
		int before = startPos.compareTo(start);
		int after = endPos.compareTo(end);

		if (endPos.compareTo(start) <= 0) nearest = node;
		if (before <= 0 && after >= 0) container = node;
	}

	public static Pragma locate(Start ast, Pragma p) {
		PragmaLocator locator = new PragmaLocator(p.getStart(), p.getEnd());
		ast.apply(locator);
		return new Pragma(p, locator.nearest, locator.predecessor,
				locator.container);
	}
}
