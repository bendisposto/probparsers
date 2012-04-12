/** 
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.ctlparser.analysis.DepthFirstAdapter;
import de.be4.ltl.core.ctlparser.node.ACurrentCtl;
import de.be4.ltl.core.ctlparser.node.ADeadlockCtl;
import de.be4.ltl.core.ctlparser.node.AEnaCtl;
import de.be4.ltl.core.ctlparser.node.AEnabledCtl;
import de.be4.ltl.core.ctlparser.node.ASinkCtl;
import de.be4.ltl.core.ctlparser.node.AUnparsedCtl;
import de.be4.ltl.core.ctlparser.node.Node;
import de.be4.ltl.core.ctlparser.node.Start;
import de.be4.ltl.core.ctlparser.node.Token;
import de.prob.parserbase.ProBParserBase;
import de.prob.prolog.output.IPrologTermOutput;

public class PrologCtlGenerator extends DepthFirstAdapter {

	private final IPrologTermOutput p;
	private final PrologGeneratorHelper helper;

	public PrologCtlGenerator(final IPrologTermOutput pto,
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
	public void caseAUnparsedCtl(AUnparsedCtl node) {
		final Token token = node.getPredicate();
		helper.caseUnparsed(UniversalToken.createToken(token));
	}

	@Override
	public void caseAEnaCtl(AEnaCtl node) {
		final Token token = node.getOperation();
		p.openTerm("ena");
		helper.parseTransitionPredicate(UniversalToken.createToken(token));
		node.getCont().apply(this);
		p.closeTerm();
	}

	@Override
	public void caseAEnabledCtl(final AEnabledCtl node) {
		final Token token = node.getOperation();
		helper.enabled(UniversalToken.createToken(token));
	}

	@Override
	public void caseASinkCtl(final ASinkCtl node) {
		helper.sink();
	}

	@Override
	public void caseADeadlockCtl(final ADeadlockCtl node) {
		helper.deadlock();
	}

	@Override
	public void caseACurrentCtl(final ACurrentCtl node) {
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
