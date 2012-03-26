package de.be4.classicalb.core.parser.analysis.pragma;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class ArgumentLexerTest {


	
	@Test
	public void testSimple() {
		String[] split = ArgumentLexer.splitA("one two three");
		assertArrayEquals(new String[]{ "one", "two", "three" }, split);
	}
	
	@Test
	public void testSingleQuote() {
		String[] split = ArgumentLexer.splitA("'one two' three");
		assertArrayEquals(new String[]{ "'one two'", "three" }, split);
	}

	@Test
	public void testBrokenSingleQuote() {
		String[] split = ArgumentLexer.splitA("'one two three");
		assertArrayEquals(new String[]{ "'one","two", "three" }, split);
	}
	
	@Test
	public void testDoubleQuote() {
		String[] split = ArgumentLexer.splitA("\"one two\" three");
		assertArrayEquals(new String[]{ "\"one two\"", "three" }, split);
	}
	@Test
	public void testBrokenDoubleQuote() {
		String[] split = ArgumentLexer.splitA("\"one two three");
		// for (String string : split) {
		// System.out.println(string);
		// }
		assertArrayEquals(new String[]{ "\"one","two", "three" }, split);
	}
	
}
