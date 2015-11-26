package de.be4.eventbalg.parser;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.Test;

import de.be4.eventbalg.core.parser.node.AAxiom;
import de.be4.eventbalg.core.parser.node.AContextParseUnit;
import de.be4.eventbalg.core.parser.node.PAxiom;
import de.be4.eventbalg.core.parser.node.Start;

public class TheoriesIssuesTest extends AbstractTest {
	@Test
	public void testCommentPredicates1() throws Exception {
		final Start rootNode = parseInput(
				"context C4 \naxioms\n@axm2 {1↦1,2↦2} = seqAppend({1↦1},2)\nend",
				false);

		final AContextParseUnit parseUnit = (AContextParseUnit) rootNode
				.getPParseUnit();
		final LinkedList<PAxiom> axioms = parseUnit.getAxioms();
		final AAxiom axiom = (AAxiom) axioms.get(0);

		assertEquals("{1↦1,2↦2} = seqAppend({1↦1},2)", axiom.getPredicate()
				.getText());
	}
}
