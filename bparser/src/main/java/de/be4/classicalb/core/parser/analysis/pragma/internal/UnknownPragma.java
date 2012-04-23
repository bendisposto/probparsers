package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.ArgumentLexer;
import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePosition;
import de.prob.prolog.output.IPrologTermOutput;

public class UnknownPragma implements Pragma {

	private final RawPragma raw;
	private final Node predecessor;
	private final Node container;
	private final Node nearestLeft;
	private final Node successor;
	private final Node nearestRight;
	private final String name;
	private final ArrayList<String> arguments = new ArrayList<String>();;

	public UnknownPragma(RawPragma raw, Node nearestLeft, Node predecessor,
			Node container, Node successor, Node nearestRight) {
		this.raw = raw;
		this.nearestLeft = nearestLeft;
		this.predecessor = predecessor;
		this.container = container;
		this.successor = successor;
		this.nearestRight = nearestRight;

		String text = raw.getText();
		List<String> matchList = ArgumentLexer.split(text);

		ArrayList<String> a = new ArrayList<String>();
		for (String string : matchList) {
			String stripped = strip(string);
			if (!stripped.isEmpty()) a.add(stripped);
		}

		if (a.isEmpty()) {
			this.name = "empty";

		} else {
			this.name = a.remove(0);
			arguments.addAll(a);
		}
	}

	private String strip(String string) {
		if (string.startsWith("'") && string.endsWith("'"))
			return string.substring(1, string.length() - 1).trim();
		if (string.startsWith("\"") && string.endsWith("\""))
			return string.substring(1, string.length() - 1).trim();
		return string.trim();
	}

	public String getPragmaName() {
		return name;
	}

	public List<String> getPragmaArguments() {
		return arguments;
	}

	@Override
	public String toString() {
		return "Pragma '" + getText() + "' " + getStart() + "-" + getEnd()
				+ ". Right after '" + name(nearestLeft) + "/"
				+ name(predecessor) + "', inside '" + name(container)
				+ "' and before '" + name(successor) + "/" + name(nearestRight)
				+ "'";
	}

	private String name(Node n) {
		if (n == null) return "na";
		return n.getClass().getSimpleName();
	}

	public SourcePosition getEnd() {
		return raw.getEnd();
	}

	public SourcePosition getStart() {
		return raw.getStart();
	}

	public String getText() {
		return raw.getText();
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public Node getContainer() {
		return container;
	}

	public Node getNearestLeft() {
		return nearestLeft;
	}

	public Node getSuccessor() {
		return successor;
	}

	public Node getNearestRight() {
		return nearestRight;
	}

	public void printProlog(IPrologTermOutput pto, NodeIdAssignment ids) {
		pto.openTerm("global_pragma");
		pto.printAtom(getPragmaName());
		
		pto.openList();
		for (String arg : getPragmaArguments()) {
			pto.printAtom(arg);
		}
		pto.closeList();
		pto.openList(); // no warnings
		pto.closeList();

		
		int filenr = ids.lookupFileNumber(container);
		pto.printNumber(filenr);
		pto.printNumber(raw.getStart().getLine());
		pto.printNumber(raw.getStart().getPos());
		pto.printNumber(raw.getEnd().getLine());
		pto.printNumber(raw.getEnd().getPos());


		pto.openList(); 		
		Integer pred = ids.lookup(getPredecessor());
		String predecessor = pred == null ? "start" : pred.toString();
		pto.printAtomOrNumber(predecessor);
		Integer cont = ids.lookup(getContainer());
		String container = cont == null ? "start" : cont.toString();
		pto.printAtomOrNumber(container);
		Integer succ = ids.lookup(getSuccessor());
		String successor = succ == null ? "eof" : succ.toString();
		pto.printAtomOrNumber(successor);

		Integer nearL = ids.lookup(getNearestLeft());
		String nearLeft = nearL == null ? "start" : nearL.toString();
		pto.printAtomOrNumber(nearLeft);

		Integer nearR = ids.lookup(getNearestRight());
		String nearRight = nearR == null ? "eof" : nearR.toString();
		pto.printAtomOrNumber(nearRight);
		pto.closeList();

		pto.closeTerm();
	}

}
