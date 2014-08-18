package de.be4.eventb.parser;

import java.util.LinkedList;

import de.be4.eventb.core.parser.BException;
import de.be4.eventb.core.parser.EventBLexerException;
import de.be4.eventb.core.parser.EventBParseException;
import de.be4.eventb.core.parser.node.AEvent;
import de.be4.eventb.core.parser.node.AMachineParseUnit;
import de.be4.eventb.core.parser.node.AVariable;
import de.be4.eventb.core.parser.node.AWitness;
import de.be4.eventb.core.parser.node.PEvent;
import de.be4.eventb.core.parser.node.PVariable;
import de.be4.eventb.core.parser.node.PWitness;
import de.be4.eventb.core.parser.node.Start;
import de.be4.eventb.core.parser.node.TAt;

public class StructureTest extends AbstractTest {

	public void testEventStructure() throws Exception {
		parseInput(
				"machine Test\nevents\n\nconvergent event test //comment\nend\nend",
				false);
	}

	public void testOptionalConvergence() throws Exception {
		parseInput("machine Test\nevents\n\nevent test //comment\nend\nend",
				false);
	}

	public void testContextExtends() throws Exception {
		parseInput("context Context2 extends Context1 end", false);
	}

	public void testIdentifierTick() throws Exception {
		final Start root = parseInput("machine Mac variables x' y end", false);
		final AMachineParseUnit parseUnit = (AMachineParseUnit) root
				.getPParseUnit();

		final LinkedList<PVariable> variables = parseUnit.getVariables();
		assertEquals(2, variables.size());

		assertEquals("x'", ((AVariable) variables.get(0)).getName().getText());
		assertEquals("y", ((AVariable) variables.get(1)).getName().getText());
	}

	public void testWitnessTick() throws Exception {
		final Start root = parseInput(
				"machine WitnessTick\nevents\nevent Eve\nwith\n@x' x' :: NAT\nend\nend",
				false);
		final AMachineParseUnit parseUnit = (AMachineParseUnit) root
				.getPParseUnit();

		final LinkedList<PEvent> events = parseUnit.getEvents();
		assertEquals(1, events.size());

		final AEvent event = (AEvent) events.get(0);
		final LinkedList<PWitness> witnesses = event.getWitnesses();
		assertEquals(1, witnesses.size());

		assertEquals("x'", ((AWitness) witnesses.get(0)).getName().getText());
	}

	public void testUnicodeIdentifiers1() throws Exception {
		parseInput("context UnicodeIdentifiers1 constants Über æ mäh end",
				false);
	}

	public void testUnicodeIdentifiers2() {
		try {
			parseInput("context UnicodeIdentifiers2 constants Über @ end",
					false);
			fail("Expecting exception");
		} catch (final BException e) {
			final Exception cause = e.getCause();

			assertTrue("Unexpected cause: " + e.getCause() + " - "
					+ e.getLocalizedMessage(),
					cause instanceof EventBParseException);
			assertTrue("Unexpected token: "
					+ ((EventBParseException) cause).getToken().getClass()
							.getSimpleName() + " - " + e.getLocalizedMessage(),
					((EventBParseException) cause).getToken() instanceof TAt);
		}
	}

	public void testUnicodeIdentifiers3() {
		try {
			parseInput("context UnicodeIdentifiers3 constants Über ' end",
					false);
			fail("Expecting exception");
		} catch (final BException e) {
			final Exception cause = e.getCause();

			assertTrue("Unexpected cause: " + e.getCause() + " - "
					+ e.getLocalizedMessage(),
					cause instanceof EventBLexerException);
			assertTrue("Unexpected last text: "
					+ ((EventBLexerException) cause).getLastText() + " - "
					+ e.getLocalizedMessage(), "'"
					.equals(((EventBLexerException) cause).getLastText()));
		}
	}

	public void testMissingAtMessage() {
		try {
			parseInput("context MissingAtMessage axioms blub x:=1 end", false);
			fail("Expecting exception");
		} catch (final BException e) {
			final Exception cause = e.getCause();

			assertTrue("Unexpected cause: " + e.getCause() + " - "
					+ e.getLocalizedMessage(),
					cause instanceof EventBParseException);

			final EventBParseException exception = (EventBParseException) cause;
			assertTrue("Message missing @", exception.getMessage()
					.contains("@"));
		}
	}
}
