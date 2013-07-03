package de.be4.ltl.core.parser;

/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

import java.io.PushbackReader;
import java.io.StringReader;

import junit.framework.Assert;

import org.junit.Test;

import de.be4.ltl.core.ctlparser.lexer.Lexer;
import de.be4.ltl.core.ctlparser.parser.Parser;
import de.be4.ltl.core.ctlparser.parser.ParserException;
import de.prob.parserbase.ProBParseException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.PrologTerm;

public class PrologGeneratorTest {
	private static final PrologTerm TERM_TRUE = new CompoundPrologTerm("true");
	private static final PrologTerm TERM_FALSE = new CompoundPrologTerm("false");

	@Test
	public void testTrue() throws Exception {
		check("true", TERM_TRUE);
	}

	@Test
	public void testFalse() throws Exception {
		check("false", TERM_FALSE);
	}

	@Test
	public void testImplication() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("implies",
				TERM_FALSE, TERM_TRUE);
		check("false =>   true ", expected);
	}

	@Test
	public void testSink() throws Exception {
		PrologTerm sink = new CompoundPrologTerm("sink");
		final PrologTerm expected = new CompoundPrologTerm("ap", sink);
		check("sink", expected);
	}

	@Test
	public void testDeadlock() throws Exception {
		PrologTerm deadlock = new CompoundPrologTerm("deadlock");
		check("deadlock", new CompoundPrologTerm("ap", deadlock));
	}

	@Test
	public void testCurrent() throws Exception {
		final PrologTerm root = new CompoundPrologTerm("root");
		final PrologTerm stateid = new CompoundPrologTerm("stateid", root);
		final PrologTerm expected = new CompoundPrologTerm("ap", stateid);
		check("current", expected);
	}

	@Test
	public void testAnd() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("and", TERM_TRUE,
				TERM_FALSE);
		check("true &  false", expected);
	}

	@Test
	public void testOr() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("or", TERM_TRUE,
				TERM_FALSE);
		check("true or  false", expected);
	}

	@Test
	public void testNot() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("not", TERM_TRUE);
		check("not true", expected);
	}

	@Test
	public void testAction() throws Exception {
		final PrologTerm transPred = new CompoundPrologTerm("bla");
		final PrologTerm wrapped = new CompoundPrologTerm("dtrans", transPred);
		final PrologTerm expected = new CompoundPrologTerm("action", wrapped);
		check("[bla]", expected);
	}

	@Test
	public void testEnabled() throws Exception {
		final PrologTerm transPred = new CompoundPrologTerm("bla");
		final PrologTerm wrapped = new CompoundPrologTerm("dtrans", transPred);
		final PrologTerm enabled = new CompoundPrologTerm("enabled", wrapped);
		final PrologTerm expected = new CompoundPrologTerm("ap", enabled);
		check("e(bla)", expected);
	}

	@Test
	public void testAvailable() throws Exception {
		final PrologTerm transPred = new CompoundPrologTerm("bla");
		final PrologTerm wrapped = new CompoundPrologTerm("dtrans", transPred);
		final PrologTerm available = new CompoundPrologTerm("available", wrapped);
		final PrologTerm expected = new CompoundPrologTerm("ap", available);
		check("Av(bla)", expected);
	}
	
	@Test
	public void testPredicate() throws Exception {
		final PrologTerm pred = new CompoundPrologTerm("blubb");
		final PrologTerm wrapped = new CompoundPrologTerm("dpred", pred);
		final PrologTerm expected = new CompoundPrologTerm("ap", wrapped);
		check("{blubb}", expected);
	}

	@Test
	public void testUntil() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("until", TERM_FALSE,
				TERM_TRUE);
		check("false U      true ", expected);
	}

	@Test
	public void testWeakUntil() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("weakuntil",
				TERM_FALSE, TERM_TRUE);
		check("false W      true ", expected);
	}

	@Test
	public void testRelease() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("release",
				TERM_FALSE, TERM_TRUE);
		check("false R      true ", expected);
	}

	@Test
	public void testSince() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("since", TERM_FALSE,
				TERM_TRUE);
		check("false S true ", expected);
	}

	@Test
	public void testTrigger() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("trigger",
				TERM_FALSE, TERM_TRUE);
		check("false T true ", expected);
	}

	@Test
	public void testGlobally() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("globally",
				TERM_TRUE);
		check("G true ", expected);
	}

	@Test
	public void testFinally() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("finally", TERM_TRUE);
		check(" F true ", expected);
	}

	@Test
	public void testNext() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("next", TERM_TRUE);
		check("X true ", expected);
	}

	@Test
	public void testHistorically() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("historically",
				TERM_TRUE);
		check("H true ", expected);
	}

	@Test
	public void testOnce() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("once", TERM_TRUE);
		check("O true ", expected);
	}

	@Test
	public void testYesterday() throws Exception {
		final PrologTerm expected = new CompoundPrologTerm("yesterday",
				TERM_TRUE);
		check("Y true ", expected);
	}

	@Test
	public void testGloballyFinallyAP() throws Exception {
		final PrologTerm pred = new CompoundPrologTerm("blubb");
		final PrologTerm wrapped = new CompoundPrologTerm("dpred", pred);
		final PrologTerm ap = new CompoundPrologTerm("ap", wrapped);
		final PrologTerm fin = new CompoundPrologTerm("finally", ap);
		final PrologTerm expected = new CompoundPrologTerm("globally", fin);
		check("GF {blubb}", expected);
	}

	@Test
	public void testComplex2() throws Exception {
		final PrologTerm pred1 = new CompoundPrologTerm("xxx");
		final PrologTerm wrapped1 = new CompoundPrologTerm("dpred", pred1);
		final PrologTerm ap1 = new CompoundPrologTerm("ap", wrapped1);
		final PrologTerm pred2 = new CompoundPrologTerm("blubb");
		final PrologTerm wrapped2 = new CompoundPrologTerm("dpred", pred2);
		final PrologTerm ap2 = new CompoundPrologTerm("ap", wrapped2);
		final PrologTerm fin = new CompoundPrologTerm("finally", ap2);
		final PrologTerm glob = new CompoundPrologTerm("globally", fin);
		final PrologTerm imp = new CompoundPrologTerm("implies", ap1, glob);
		final PrologTerm expected = new CompoundPrologTerm("not", imp);
		check("not({xxx} => GF {blubb})", expected);
	}

	@Test
	public void testForExistsImplication() throws Exception {
		//final PrologTerm id = new CompoundPrologTerm("x");

		// ap(dpred("x:PID"))
		//final PrologTerm pred = new CompoundPrologTerm("x:PID");

		// G [x]
		final PrologTerm transPred = new CompoundPrologTerm("x");
		final PrologTerm wrapped2 = new CompoundPrologTerm("dtrans", transPred);
		final PrologTerm action = new CompoundPrologTerm("action", wrapped2);
		final PrologTerm glob = new CompoundPrologTerm("globally", action);

		final PrologTerm expected = new CompoundPrologTerm("exists", glob);

		check("#x. ( {x:PID} => G [x])", expected);
	}

	@Test
	public void testForExistsImplicationNested() throws Exception {
		//final PrologTerm id = new CompoundPrologTerm("x");

		//final PrologTerm pred = new CompoundPrologTerm("x:PID");

		final PrologTerm transPredx = new CompoundPrologTerm("x");
		final PrologTerm wrappedx = new CompoundPrologTerm("dtrans", transPredx);
		final PrologTerm actionx = new CompoundPrologTerm("action", wrappedx);

		final PrologTerm transPredy = new CompoundPrologTerm("y");
		final PrologTerm wrappedy = new CompoundPrologTerm("dtrans", transPredy);
		final PrologTerm actiony = new CompoundPrologTerm("action", wrappedy);

		final PrologTerm orPred = new CompoundPrologTerm("or", actionx, actiony);
		
		final PrologTerm glob = new CompoundPrologTerm("globally", orPred);

		final PrologTerm forall = new CompoundPrologTerm("forall", glob);
		final PrologTerm expected = new CompoundPrologTerm("exists", forall);

		check("#x. ( {x:PID} => !y. ({y:PID} => G ([x] or [y])))", expected);
	}

	
	@Test
	public void testForAllImplication() throws Exception {
		//final PrologTerm id = new CompoundPrologTerm("x");

		// ap(dpred("x:PID"))
		//final PrologTerm pred = new CompoundPrologTerm("x:PID");

		// G [x]
		final PrologTerm transPred = new CompoundPrologTerm("x");
		final PrologTerm wrapped2 = new CompoundPrologTerm("dtrans", transPred);
		final PrologTerm action = new CompoundPrologTerm("action", wrapped2);
		final PrologTerm glob = new CompoundPrologTerm("globally", action);

		final PrologTerm expected = new CompoundPrologTerm("forall", glob);

		check("!xyz. ( {x:PID} => G [x])", expected);
	}

	@Test(expected = LtlParseException.class)
	public void ticket_parserlib_11() throws Exception {
		String buggy = "G {taken= {} ";
		parse(buggy);
		
	}
	
	@Test(expected = ParserException.class)
	public void ticket_parserlib_11_ctl() throws Exception {
		new Parser(new Lexer(new PushbackReader(new StringReader("AG {taken= {}")))).parse();
	}
	
	

	

	public void testPredSyntaxError() throws Exception {
		try {
			parse("{X}");
			Assert.fail("expected parser exception");
		} catch (LtlParseException e) {
			// ok
		}
	}

	private void check(final String input, final PrologTerm expectedTerm)
			throws LtlParseException {
		final PrologTerm term = parse(input);
		Assert.assertEquals(expectedTerm, term);
	}

	private PrologTerm parse(final String input) throws LtlParseException {
		final LtlParser parser = new LtlParser(new DummyParser(true, true));
		return parser.generatePrologTerm(input, "root");
	}

	private static class DummyParser implements ProBParserBase {
		private final boolean suppPred, suppTransPred;

		public DummyParser(final boolean suppPred, final boolean suppTransPred) {
			this.suppPred = suppPred;
			this.suppTransPred = suppTransPred;
		}

		public void parseExpression(final IPrologTermOutput pto,
				final String expression, final boolean wrap)
				throws ProBParseException, UnsupportedOperationException {
			throw new UnsupportedOperationException("no dummy expressions");
		}

		public void parsePredicate(final IPrologTermOutput pto,
				final String predicate, final boolean wrap)
				throws ProBParseException, UnsupportedOperationException {
			if (suppPred) {
				parse(pto, predicate, wrap, "dpred");
			} else
				throw new UnsupportedOperationException("no dummy predicates");
		}

		public void parseTransitionPredicate(final IPrologTermOutput pto,
				final String transPredicate, final boolean wrap)
				throws ProBParseException, UnsupportedOperationException {
			if (suppTransPred) {
				parse(pto, transPredicate, wrap, "dtrans");
			} else
				throw new UnsupportedOperationException(
						"no dummy transition predicates");
		}

		private void parse(final IPrologTermOutput pto, final String text,
				final boolean wrap, final String wrapper)
				throws ProBParseException {
			for (int i = 0; i < text.length(); i++) {
				final char ch = text.charAt(i);
				if (!Character.isLowerCase(ch) && "()[]{}".indexOf(ch) == -1)
					throw new ProBParseException("syntax error");
			}
			if (wrap) {
				pto.openTerm(wrapper);
			}
			pto.printAtom(text);
			if (wrap) {
				pto.closeTerm();
			}

		}
	}
}
