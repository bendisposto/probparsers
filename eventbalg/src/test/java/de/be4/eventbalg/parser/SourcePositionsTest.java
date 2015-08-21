package de.be4.eventbalg.parser;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.be4.eventbalg.core.parser.EventBParser;
import de.be4.eventbalg.core.parser.node.AMachineParseUnit;
import de.be4.eventbalg.core.parser.node.PVariable;
import de.be4.eventbalg.core.parser.node.Start;
import de.hhu.stups.sablecc.patch.PositionedNode;

public class SourcePositionsTest extends AbstractTest {

	EventBParser parser = new EventBParser();

	@Before
	public void setUp() throws Exception {
		parser = new EventBParser();
	}

	@After
	public void tearDown() throws Exception {
		parser = null;
	}

	@Test
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
