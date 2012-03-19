package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		
		// Snippet by Jan Goyvaerts, taken from 
		// http://stackoverflow.com/questions/366202/regex-for-splitting-a-string-using-space-when-not-surrounded-by-single-or-double
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(raw.getText());
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		} 
		
		ArrayList<String> a = new ArrayList<String>();
		for (String string : matchList) {
			if (!string.trim().isEmpty()) a.add(string);
		}

		if (a.isEmpty()) {
			this.name = "empty";

		} else {
			this.name = a.remove(0);
			arguments.addAll(a);
		}
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
		pto.openTerm("pragma");
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
		pto.openList();
		pto.printAtom(getPragmaName());
		for (String arg : getPragmaArguments()) {
			pto.printAtom(arg);
		}
		pto.closeList();

		pto.closeTerm();
	}

}
