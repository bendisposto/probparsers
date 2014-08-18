package de.be4.eventb.parser;

import junit.framework.TestCase;
import de.be4.eventb.core.parser.BException;
import de.be4.eventb.core.parser.EventBParser;
import de.be4.eventb.core.parser.node.Start;

public class AbstractTest extends TestCase {
	protected Start parseInput(final String input, final boolean debugOutput)
			throws BException {
		if (debugOutput) {
			System.out.println();
			System.out.println();
		}

		final EventBParser parser = new EventBParser();
		final Start rootNode = parser.parse(input, debugOutput);
		return rootNode;
	}

	public void testNoTest() throws Exception {
	}
}
