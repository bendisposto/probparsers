package de.be4.classicalb.core.parser.analysis.checking;

import java.util.ArrayList;

import de.be4.classicalb.core.parser.Pragma;
import de.be4.classicalb.core.parser.analysis.DepthFirstAdapter;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.SourcePosition;

public class PragmaLocator extends DepthFirstAdapter {

	private Node[] nearest;
	private Node[] predecessor;
	private Node[] container;
	private Pragma[] pragmas;

	public PragmaLocator(Pragma[] p) {
		this.pragmas = p;
	}

	@Override
	public void inStart(Start node) {
		for (int i = 0; i < pragmas.length; i++) {
			nearest[i] = node;
			container[i] = node;
		}
	}

	@Override
	public void defaultOut(Node node) {
		SourcePosition endPos = node.getEndPos();
		if (endPos == null) return; // no source info available
		for (int i = 0; i < pragmas.length; i++) {
			SourcePosition start = pragmas[i].getStart();
			if (endPos.compareTo(start) <= 0) predecessor[i] = node;
		}
	}

	@Override
	public void defaultIn(Node node) {
		Node n = node;
		SourcePosition startPos = node.getStartPos();
		SourcePosition endPos = node.getEndPos();
		if (startPos != null && endPos != null) {
			for (int i = 0; i < pragmas.length; i++) {
				SourcePosition start = pragmas[i].getStart();
				SourcePosition end = pragmas[i].getEnd();
				if (endPos.compareTo(start) <= 0) nearest[i] = node;
				if (startPos.compareTo(start) <= 0
						&& endPos.compareTo(end) >= 0) container[i] = node;
			}
		}
	}

	public static ArrayList<Pragma> locate(Start ast, Pragma[] p) {
		PragmaLocator locator = new PragmaLocator(p);
		int size = p.length;
		locator.nearest = new Node[size];
		locator.container = new Node[size];
		locator.predecessor = new Node[size];
		ast.apply(locator);
		ArrayList<Pragma> list = new ArrayList<Pragma>();
		for (int i = 0; i < p.length; i++) {
			list.add(new Pragma(locator.pragmas[i], locator.nearest[i],
					locator.predecessor[i], locator.container[i]));
		}
		return list;
	}
}
