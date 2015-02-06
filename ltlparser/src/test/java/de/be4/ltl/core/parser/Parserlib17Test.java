package de.be4.ltl.core.parser;

import org.junit.Ignore;
import org.junit.Test;

import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.PrologTerm;

public class Parserlib17Test extends AbstractTest {
	@Test
	@Ignore
	public void testParserlib17() throws Exception {
		// has to be ignored because the dummyparser can not parse the ap
		// might check for the syntax error exception instead?
		final PrologTerm none = new CompoundPrologTerm("none");
		final PrologTerm stringl = new CompoundPrologTerm("string", none,
				new CompoundPrologTerm("{"));
		final PrologTerm stringr = new CompoundPrologTerm("string", none,
				new CompoundPrologTerm("1"));
		final PrologTerm eq = new CompoundPrologTerm("equal", none, stringl,
				stringr);
		final PrologTerm bpred = new CompoundPrologTerm("bpred", eq);
		final PrologTerm ap = new CompoundPrologTerm("ap", bpred);
		final PrologTerm expected = new CompoundPrologTerm("globally", ap);

		check("G {\"{\"=\"1\"}", expected);
	}
}