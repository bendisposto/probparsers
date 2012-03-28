package de.be4.classicalb.core.parser.analysis.pragma.internal;

import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.Pragma;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.node.Node;
import de.prob.prolog.output.IPrologTermOutput;

public class ClassifiedPragma implements Pragma {

	private final String name;
	private final Node attachedTo;
	private final List<String> arguments;
	private final List<String> warnings;

	public ClassifiedPragma(String name, Node attachedTo, List<String> arguments, List<String> warnings) {
		this.name = name;
		this.attachedTo = attachedTo;
		this.arguments = arguments;
		this.warnings = warnings;
	}

	public void printProlog(IPrologTermOutput pto, NodeIdAssignment ids) {
		Integer id = ids.lookup(attachedTo);
		String node = id == null ? "none" : String.valueOf(id);
		pto.openTerm(name).printAtomOrNumber(node).openList();
		for (String argument : arguments) {
			pto.printAtom(argument);
		}
		pto.closeList().openList();
		for (String argument : warnings) {
			pto.printAtom(argument);
		}
		pto.closeList().closeTerm();
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
