package de.be4.classicalb.core.rules.project;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.be4.classicalb.core.parser.exceptions.BCompoundException;
import de.be4.classicalb.core.parser.exceptions.BException;
import de.prob.prolog.output.IPrologTermOutput;

public interface IModel {

	public String getMachineName();

	public List<Reference> getMachineReferences();

	public void printAsProlog(final PrintWriter out, NodeIdAssignment nodeIdMapping);

	public void printAsProlog(final IPrologTermOutput pout, NodeIdAssignment nodeIdMapping);

	public void printExceptionAsProlog(final PrintStream err);

	public String getModelAsPrologTerm(NodeIdAssignment nodeIdMapping);

	public File getFile();

	public boolean hasError();

	public BCompoundException getBExeption();

}
