package de.be4.eventb.parser;

import de.be4.eventb.core.parser.BException;
import de.be4.eventb.core.parser.EventBParser;
import de.be4.eventb.core.parser.node.Start;

public class AbstractTest {
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
}
