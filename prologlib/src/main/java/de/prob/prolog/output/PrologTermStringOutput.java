package de.prob.prolog.output;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This class encapsulates the process of creating a Prolog Term as a String.
 * 
 * @author Jens Bendisposto
 * 
 */
public class PrologTermStringOutput extends PrologTermDelegate {
	private final StringWriter sw;

	public PrologTermStringOutput() {
		this(new StringWriter());
	}

	private PrologTermStringOutput(StringWriter sw) {
		super(new PrologTermOutput(new PrintWriter(sw), false));
		this.sw = sw;
	}

	public IPrologTermOutput getPrologTermOutput() {
		return pto;
	}

	@Override
	public String toString() {
		pto.flush();
		return sw.toString();
	}
}
