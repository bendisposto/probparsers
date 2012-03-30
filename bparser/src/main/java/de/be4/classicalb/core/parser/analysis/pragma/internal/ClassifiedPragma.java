package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.node.Node;
import de.hhu.stups.sablecc.patch.SourcePosition;
import de.prob.prolog.output.IPrologTermOutput;

public class ClassifiedPragma implements Pragma {

	private final String name;
	private final Node attachedTo;
	private final List<String> arguments;
	private final List<String> warnings;
	private final SourcePosition start;
	private final SourcePosition end;

	public ClassifiedPragma(String name, Node attachedTo, List<String> arguments, List<String> warnings, SourcePosition start, SourcePosition end) {
		this.name = name;
		this.attachedTo = attachedTo;
		this.arguments = arguments;
		this.warnings = warnings;
		this.start = start;
		this.end = end;
	}

	public void printProlog(IPrologTermOutput pto, NodeIdAssignment ids) {
		int fileNumber = ids.lookupFileNumber(attachedTo);
		Integer id = ids.lookup(attachedTo);
		String node = id == null ? "none" : String.valueOf(id);
		pto.openTerm("pragma");
		pto.printAtomOrNumber(node);
		pto.printAtom(name);
		pto.openList();
		for (String argument : arguments) {
			pto.printAtom(argument);
		}
		pto.closeList().openList();
		for (String argument : warnings) {
			pto.printAtom(argument);
		}
		pto.closeList();
		pto.printNumber(fileNumber);
		pto.printNumber(start.getLine());
		pto.printNumber(start.getPos());
		pto.printNumber(end.getLine());
		pto.printNumber(end.getPos());
		pto.closeTerm();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Pragma classified as '");
		sb.append(name);
		sb.append("' is attached to a ");
		sb.append(attachedTo.getClass().getSimpleName());
		sb.append(" with arguments: ");
		for (String a : arguments) {
			sb.append(a);
			sb.append(" ");
		}
		return sb.toString();
	}

}
