package de.be4.classicalb.core.parser.analysis.pragma;

import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.prob.prolog.output.IPrologTermOutput;

public interface Pragma {

	public abstract void printProlog(IPrologTermOutput pout, NodeIdAssignment ids);

}