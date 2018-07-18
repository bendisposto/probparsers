/*
 * (c) 2009-2014 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */

package de.be4.ltl.core.parser.internal;

import de.be4.ltl.core.parser.analysis.DepthFirstAdapter;
import de.be4.ltl.core.parser.node.AActionLtl;
import de.be4.ltl.core.parser.node.AAndFair1Ltl;
import de.be4.ltl.core.parser.node.AAndFair2Ltl;
import de.be4.ltl.core.parser.node.ACtrlLtl;
import de.be4.ltl.core.parser.node.ACurrentLtl;
import de.be4.ltl.core.parser.node.ADeadlockLtl;
import de.be4.ltl.core.parser.node.ADetLtl;
import de.be4.ltl.core.parser.node.AEnabledLtl;
import de.be4.ltl.core.parser.node.AAvailableLtl;
import de.be4.ltl.core.parser.node.AExistsLtl;
import de.be4.ltl.core.parser.node.AForallLtl;
import de.be4.ltl.core.parser.node.AOpActions;
import de.be4.ltl.core.parser.node.ASinkLtl;
import de.be4.ltl.core.parser.node.AStrongFairAllLtl;
import de.be4.ltl.core.parser.node.AStrongFairLtl;
import de.be4.ltl.core.parser.node.AUnparsedLtl;
import de.be4.ltl.core.parser.node.AWeakFairAllLtl;
import de.be4.ltl.core.parser.node.AWeakFairLtl;
import de.be4.ltl.core.parser.node.ADlkLtl;
import de.be4.ltl.core.parser.node.Node;
import de.be4.ltl.core.parser.node.PLtl;
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
	public void caseAStrongFairLtl(final AStrongFairLtl node) {
		final Token token = node.getOperation();
		helper.strong_fair(UniversalToken.createToken(token));
	}

	@Override
	public void caseAWeakFairLtl(final AWeakFairLtl node) {
		final Token token = node.getOperation();
		helper.weak_fair(UniversalToken.createToken(token));
	}

	@Override
	public void caseAAndFair1Ltl(final AAndFair1Ltl node) {
		final PLtl left_node = node.getLeft();
		final PLtl right_node = node.getRight();
		helper.and_fair1(left_node,right_node,this);
	}

	@Override
	public void caseAAndFair2Ltl(final AAndFair2Ltl node) {
		final PLtl left_node = node.getLeft();
		final PLtl right_node = node.getRight();
		helper.and_fair2(left_node,right_node,this);
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
	public void caseAWeakFairAllLtl(final AWeakFairAllLtl node) {
		helper.weak_fair_all();
	}

	@Override
	public void caseAStrongFairAllLtl(final AStrongFairAllLtl node) {
		helper.strong_fair_all();
	}

	@Override
    public void caseAExistsLtl(AExistsLtl node)
    {
		helper.existsTerm(node, this);
    }

	@Override
    public void caseAForallLtl(AForallLtl node)
    {
    	helper.forallTerm(node, this);
    }


	@Override
	public void caseADlkLtl(ADlkLtl node) {
		helper.dlk(node, this);
	}

	@Override
	public void caseADetLtl(ADetLtl node) {
		helper.det(node, this);
	}

	@Override
	public void caseACtrlLtl(ACtrlLtl node) {
		helper.ctrl(node, this);
	}

	@Override
	public void caseAOpActions(AOpActions node) {
		final Token token = node.getOperation();
		helper.parseTransitionPredicate(UniversalToken.createToken(token));
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
