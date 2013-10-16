import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.prob.unicode.UnicodeTranslator;

public class UnicodeTest {

	@Test
	public void badAssTestsToAscii() {
		assertEquals("nafor", UnicodeTranslator.toAscii("nafor"));
		assertEquals("x: NAT", UnicodeTranslator.toAscii("x\u2208   \u2115"));
		assertEquals("x:NAT", UnicodeTranslator.toAscii("x\u2208\u2115"));
		assertEquals("x :NAT", UnicodeTranslator.toAscii("x  \u2208\u2115"));
		assertEquals("INTERNAT", UnicodeTranslator.toAscii("INTERNAT"));
		assertEquals("cur_floor := groundf",
				UnicodeTranslator.toAscii("cur_floor \u2254 groundf"));
		assertEquals("cur_floor < groundf",
				UnicodeTranslator.toAscii("cur_floor < groundf"));
		assertEquals("direction_up = FALSE",
				UnicodeTranslator.toAscii("direction_up = FALSE"));
		assertEquals("b : (groundf .. topf)",
				UnicodeTranslator.toAscii("b \u2208 (groundf \uu2025 topf)"));
		assertEquals("call_buttons := call_buttons \\/ {b}",
				UnicodeTranslator
						.toAscii("call_buttons \u2254 call_buttons \u222a {b}"));
		assertEquals("b /: call_buttons",
				UnicodeTranslator.toAscii("b \u2209 call_buttons"));
	}

	@Test
	public void basAssTestsToUnicode() {
		assertEquals("INTERNAT", UnicodeTranslator.toUnicode("INTERNAT"));
	}

	@Test
	public void basAssShittyNames() {
		assertEquals("POW12", UnicodeTranslator.toUnicode("POW12"));
	}

	@Test
	public void NoSpaceAndAmpersand() {
		assertEquals("active \u2227 waiting",
				UnicodeTranslator.toUnicode("active & waiting"));
		assertEquals("active \u2227waiting",
				UnicodeTranslator.toUnicode("active &waiting"));
		assertEquals("active\u2227 waiting",
				UnicodeTranslator.toUnicode("active& waiting"));
		assertEquals("active\u2227waiting",
				UnicodeTranslator.toUnicode("active&waiting"));
	}

	@Test
	public void PROBCORE413() {
		String unicodeNoSpaces = "\u2200r\u2982ROUTES\u00B7r\u2208ROUTES\u21D2(\u2200S\u2982\u2119(BLOCKS)\u00B7S\u2286BLOCKS\u2227S\u2286(nxt(r))[S]\u21D2S=(\u2205 \u2982 \u2119(BLOCKS)))";
		String unicodeWithSpaces = "\u2200r \u2982 ROUTES\u00B7r\u2208ROUTES\u21D2(\u2200S \u2982 \u2119(BLOCKS)\u00B7S\u2286BLOCKS\u2227S\u2286(nxt(r))[S]\u21D2S=(\u2205 \u2982 \u2119(BLOCKS)))";
		String ascii = "!r oftype ROUTES.r:ROUTES=>(!S oftype POW(BLOCKS).S<:BLOCKS&S<:(nxt(r))[S]=>S=({} oftype POW(BLOCKS)))";
		UnicodeTranslator.toUnicode(ascii);
		assertEquals(ascii, UnicodeTranslator.toAscii(unicodeNoSpaces));
		assertEquals(unicodeWithSpaces, UnicodeTranslator.toUnicode(ascii));
	}

	@Test
	public void PARSERLIB22() {
		String unicode = "\u22a4\u2228\u00acmss_button=ato\u2228ato_availability=TRUE";
		String unicodeWithSpaces = "\u22a4 \u2228 \u00ac mss_button=ato \u2228 ato_availability=TRUE";
		String ascii = UnicodeTranslator.toAscii(unicode);
		assertEquals("true or not mss_button=ato or ato_availability=TRUE",
				ascii);
		assertEquals(unicodeWithSpaces, UnicodeTranslator.toUnicode(ascii));
	}

	@Test
	public void PARSERLIB23() {
		// The problem is when identifier is a prime
		String unicode = "current_mode=mss_button'\u2228stationary=TRUE";
		String unicodeWithSpaces = "current_mode=mss_button' \u2228 stationary=TRUE";
		String ascii = UnicodeTranslator.toAscii(unicode);
		assertEquals(unicodeWithSpaces, UnicodeTranslator.toUnicode(ascii));
	}

	@Test
	public void testLovelyOrAndNumberProblem() {
		// Problem with number before or
		String unicode = "x=1\u2228y=2";
		String unicodeWithSpaces = "x=1 \u2228 y=2";
		String ascii = UnicodeTranslator.toAscii(unicode);
		assertEquals(unicodeWithSpaces, UnicodeTranslator.toUnicode(ascii));
	}

	@Test
	public void testNAT1NoSpace() {
		String ascii = "NAT1";
		String unicode = "\u21151";

		assertEquals(unicode, UnicodeTranslator.toUnicode(ascii));
		assertEquals(ascii, UnicodeTranslator.toAscii(unicode));
	}

	@Test
	public void testPOW1NoSpace() {
		String ascii = "POW1";
		String unicode = "\u21191";

		assertEquals(unicode, UnicodeTranslator.toUnicode(ascii));
		assertEquals(ascii, UnicodeTranslator.toAscii(unicode));
	}
}
