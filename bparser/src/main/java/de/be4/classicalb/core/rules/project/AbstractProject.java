package de.be4.classicalb.core.rules.project;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import de.be4.classicalb.core.parser.BParser;
import de.be4.classicalb.core.parser.analysis.prolog.NodeIdAssignment;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.PrologTermOutput;

public abstract class AbstractProject {

	protected final List<IModel> bModels = new ArrayList<>();
	protected final NodeIdAssignment nodeIds = new NodeIdAssignment();

	public List<IModel> getBModels() {
		return bModels;
	}

	public void addModel(IModel model) {
		bModels.add(model);
	}

	public boolean projectHasErrors() {
		for (IModel iModel : bModels) {
			if (iModel.hasError())
				return true;
		}
		return false;
	}

	public int printPrologOutput(final PrintStream out, final PrintStream err) {
		if (projectHasErrors()) {
			for (IModel iModel : bModels) {
				if (iModel.hasError()) {
					iModel.printExceptionAsProlog(err);
					break;
				}
			}
			return -2;
		} else {
			final IPrologTermOutput pout = new PrologTermOutput(new PrintWriter(out), false);
			printProjectAsPrologTerm(pout);
			pout.flush();
			return 0;
		}

	}



	public void printProjectAsPrologTerm(final PrintStream out) {
		PrologTermOutput prologTermOutput = new PrologTermOutput(new PrintWriter(out), false);
		for (IModel iModel : bModels) {
			iModel.printAsProlog(prologTermOutput, nodeIds);
		}
		out.flush();
		out.close();
	}

	public String getProjectAsPrologTerm() {
		OutputStream output = new OutputStream() {
			private StringBuilder string = new StringBuilder();

			@Override
			public void write(int b) throws IOException {
				this.string.append((char) b);
			}

			@Override
			public String toString() {
				return this.string.toString();
			}
		};
		final IPrologTermOutput pout = new PrologTermOutput(output, false);
		printProjectAsPrologTerm(pout);
		pout.flush();
		return output.toString();

	}

	public void printProjectAsPrologTerm(final IPrologTermOutput pout) {
		// parser version
		pout.openTerm("parser_version");
		pout.printAtom(BParser.getBuildRevision());
		pout.closeTerm();
		pout.fullstop();

		// machine
		pout.openTerm("classical_b");
		pout.printAtom("Main");
		pout.openList();

		for (IModel iModel : bModels) {
			try {
				pout.printAtom(iModel.getFile().getCanonicalPath());
			} catch (IOException e) {
				pout.printAtom(iModel.getFile().getPath());
			}
		}
		pout.closeList();
		pout.closeTerm();
		pout.fullstop();

		for (IModel iModel : bModels) {
			iModel.printAsProlog(pout, nodeIds);
		}
	}

}
