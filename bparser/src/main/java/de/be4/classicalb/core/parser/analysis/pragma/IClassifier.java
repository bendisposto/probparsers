package de.be4.classicalb.core.parser.analysis.pragma;

import java.util.List;

import de.be4.classicalb.core.parser.analysis.pragma.internal.UnknownPragma;
import de.be4.classicalb.core.parser.node.Node;
import de.be4.classicalb.core.parser.node.Start;

public interface IClassifier {

	public abstract PragmaParser getParser(int i);

	public abstract Node seek(UnknownPragma p, Start ast);
	public abstract List<String> getWarnings();

}