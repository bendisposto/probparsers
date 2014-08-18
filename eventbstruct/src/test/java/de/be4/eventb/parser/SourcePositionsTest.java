package de.be4.eventb.parser;

import java.util.LinkedList;

import de.be4.eventb.core.parser.EventBParser;
import de.be4.eventb.core.parser.node.AMachineParseUnit;
import de.be4.eventb.core.parser.node.PVariable;
import de.be4.eventb.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.PositionedNode;

public class SourcePositionsTest extends AbstractTest {

	EventBParser parser = new EventBParser();

	@Override
	protected void setUp() throws Exception {
		parser = new EventBParser();
	}

	@Override
	protected void tearDown() throws Exception {
		parser = null;
	}

	public void testGetEndLine() throws Exception {
		final Start root = parser.parse(
				"machine\nTestMachine\n\nvariables\nx\n\nend", false);

		final AMachineParseUnit parseUnit = (AMachineParseUnit) root
				.getPParseUnit();
		assertEquals(7, ((PositionedNode) parseUnit).getEndPos().getLine());

		final LinkedList<PVariable> variables = parseUnit.getVariables();
		assertEquals(5, ((PositionedNode) variables.get(0)).getEndPos()
				.getLine());
	}
}
