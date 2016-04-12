package de.prob.cliparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ConsoleOptionsTest {

	@Test
	public void testOutNotSet() {
		String[] args = { "Test" };

		final ConsoleOptions options = new ConsoleOptions();
		options.addOption("-out", "Specify output file", 1);
		options.parseOptions(args);

		assertFalse(options.isOptionSet("-out"));
		System.out.println(options.getRemainingOptions()[0]);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOutSetNoArgument() {
		String[] args = { "-out" };

		final ConsoleOptions options = new ConsoleOptions();
		options.addOption("-out", "Specify output file", 1);
		options.parseOptions(args);
	}

	@Test
	public void testOutSetWithArgument() {
		String[] args = { "-out", "blah.prob" };

		final ConsoleOptions options = new ConsoleOptions();
		options.addOption("-out", "Specify output file", 1);
		options.parseOptions(args);

		assertTrue(options.isOptionSet("-out"));
		assertEquals(1, options.getOptions("-out").length);
		assertEquals("blah.prob", options.getOptions("-out")[0]);

		assertEquals(0, options.getRemainingOptions().length);
	}

}
