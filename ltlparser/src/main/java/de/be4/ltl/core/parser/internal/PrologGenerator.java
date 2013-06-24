/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.parser.analysis.DepthFirstAdapter;
import de.be4.ltl.core.parser.node.AActionLtl;
import de.be4.ltl.core.parser.node.ACurrentLtl;
import de.be4.ltl.core.parser.node.ADeadlockLtl;
import de.be4.ltl.core.parser.node.AEnabledLtl;
import de.be4.ltl.core.parser.node.AAvailableLtl;
import de.be4.ltl.core.parser.node.ASinkLtl;
import de.be4.ltl.core.parser.node.AUnparsedLtl;
import de.be4.ltl.core.parser.node.Node;
import de.be4.ltl.core.parser.node.Start;
import de.be4.ltl.core.parser.node.Token;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

public class PrologGenerator extends DepthFirstAdapter {

	private final IPrologTermOutput p;
	private final PrologGeneratorHelper helper;

	public PrologGenerator(final IPrologTermOutput pto,
			final String currentStateID, final ProBParserBase specParser) {
		this.p = pto;
		this.helper = new PrologGeneratorHelper(pto, currentStateID, specParser);
	}

	@Override
	public void defaultOut(final Node node) {
		helper.defaultOut();
	}

	@Override
	public void defaultIn(final Node node) {
		helper.defaultIn(node.getClass());
	}

	@Override
	public void caseAUnparsedLtl(final AUnparsedLtl node) {
		final Token token = node.getPredicate();
		helper.caseUnparsed(UniversalToken.createToken(token));
	}

	@Override
	public void caseAActionLtl(final AActionLtl node) {
		final Token token = node.getOperation();
		p.openTerm("action");
		helper.parseTransitionPredicate(UniversalToken.createToken(token));
		p.closeTerm();
	}

	@Override
	public void caseAEnabledLtl(final AEnabledLtl node) {
		final Token token = node.getOperation();
		helper.enabled(UniversalToken.createToken(token));
	}

	@Override
	public void caseAAvailableLtl(final AAvailableLtl node) {
		final Token token = node.getOperation();
		helper.available(UniversalToken.createToken(token));
	}

	@Override
	public void caseASinkLtl(final ASinkLtl node) {
		helper.sink();
	}

	@Override
	public void caseADeadlockLtl(final ADeadlockLtl node) {
		helper.deadlock();
	}

	@Override
	public void caseACurrentLtl(final ACurrentLtl node) {
		helper.current();
	}

	@Override
	public void inStart(final Start node) {
		// Do not call default in Method
	}

	@Override
	public void outStart(final Start node) {
		// Do not call default out Method
	}
}
