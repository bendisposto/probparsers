/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import java.util.Locale;

import de.be4.ltl.core.parser.LtlParseException;
import de.be4.ltl.core.parser.analysis.DepthFirstAdapter;
import de.be4.ltl.core.parser.node.AActionLtl;
import de.be4.ltl.core.parser.node.ACurrentLtl;
import de.be4.ltl.core.parser.node.ADeadlockLtl;
import de.be4.ltl.core.parser.node.AEnabledLtl;
import de.be4.ltl.core.parser.node.ASinkLtl;
import de.be4.ltl.core.parser.node.AUnparsedLtl;
import de.be4.ltl.core.parser.node.Node;
import de.be4.ltl.core.parser.node.Start;
import de.be4.ltl.core.parser.node.Token;
import de.prob.parserbase.ProBParseException;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

public class PrologGenerator extends DepthFirstAdapter {

	private final IPrologTermOutput p;
	private final String currentStateID;
	private final ProBParserBase specParser;

	public PrologGenerator(final IPrologTermOutput pto,
			final String currentStateID, final ProBParserBase specParser) {
		this.currentStateID = currentStateID;
		this.p = pto;
		this.specParser = specParser;
	}

	@Override
	public void defaultOut(final Node node) {
		p.closeTerm();
	}

	@Override
	public void defaultIn(final Node node) {
		StringBuffer sb = new StringBuffer(node.getClass().getSimpleName());
		sb.setLength(sb.length() - 3);
		sb.deleteCharAt(0);
		String term = sb.toString().toLowerCase(Locale.ENGLISH);
		p.openTerm(term);
	}

	@Override
	public void caseAUnparsedLtl(final AUnparsedLtl node) {
		final Token token = node.getPredicate();
		p.openTerm("ap");
		try {
			specParser.parsePredicate(p, token.getText(), true);
		} catch (ProBParseException e) {
			throw new LtlAdapterException(token, e.getMessage());
		} catch (UnsupportedOperationException e) {
			throw new LtlAdapterException(token, e.getMessage());
		}
		p.closeTerm();
	}

	@Override
	public void caseAActionLtl(final AActionLtl node) {
		p.openTerm("action");
		parseTransitionPredicate(node.getOperation());
		p.closeTerm();
	}

	private void parseTransitionPredicate(final Token token) {
		try {
			specParser.parseTransitionPredicate(p, token.getText(), true);
		} catch (ProBParseException e) {
			throw new LtlAdapterException(token, e.getMessage());
		} catch (UnsupportedOperationException e) {
			throw new LtlAdapterException(token, e.getMessage());
		}
	}

	@Override
	public void caseAEnabledLtl(final AEnabledLtl node) {
		p.openTerm("ap");
		p.openTerm("enabled");
		parseTransitionPredicate(node.getOperation());
		p.closeTerm();
		p.closeTerm();
	}

	@Override
	public void caseASinkLtl(final ASinkLtl node) {
		p.openTerm("ap");
		p.printAtom("sink");
		p.closeTerm();
	}

	@Override
	public void caseADeadlockLtl(final ADeadlockLtl node) {
		p.openTerm("ap");
		p.printAtom("deadlock");
		p.closeTerm();
	}

	@Override
	public void caseACurrentLtl(final ACurrentLtl node) {
		p.openTerm("ap");
		if (currentStateID != null) {
			p.openTerm("stateid");
			p.printAtomOrNumber(currentStateID);
			p.closeTerm();
		} else {
			p.printAtom("current");
		}
		p.closeTerm();
	}

	@Override
	public void inStart(final Start node) {
		// Do not call default in Method
	}

	@Override
	public void outStart(final Start node) {
		// Do not call default out Method
	}

	public static class LtlAdapterException extends RuntimeException {
		private static final long serialVersionUID = -3723243181317857351L;
		private final LtlParseException e;

		public LtlAdapterException(final LtlParseException e) {
			this.e = e;
		}

		public LtlAdapterException(final Token token, final String msg) {
			this(new LtlParseException(token, msg));
		}

		public LtlParseException getOriginalException() {
			return e;
		}
	}
}
