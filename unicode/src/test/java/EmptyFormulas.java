import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.prob.unicode.UnicodeTranslator;

public class EmptyFormulas {
	@Test
	public void EmptyToAscii() {
		assertTrue(UnicodeTranslator.toAscii("").equals(""));
	}

	@Test
	public void EmptyToUnicode() {
		assertTrue(UnicodeTranslator.toUnicode("").equals(""));
	}

}
