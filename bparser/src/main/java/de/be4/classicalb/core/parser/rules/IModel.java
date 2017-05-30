package de.be4.classicalb.core.parser.rules;

import java.util.List;

import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.node.Start;
import de.prob.prolog.output.IPrologTermOutput;

public interface IModel {

	public String getMachineName();

	public List<RulesMachineReference> getMachineReferences();

	public void printAsProlog(final IPrologTermOutput pout, NodeIdAssignment nodeIdMapping);

	public String getPath();

	public boolean hasError();

	public BCompoundException getCompoundException();

	Start getStart();

}
