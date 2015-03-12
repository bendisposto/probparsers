package de.prob.translator.test
import de.prob.translator.Translator
import org.junit.Test

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue;

class TestBoolenAPI {
	@Test
	void testAnd() {
		def a = Translator.translate("TRUE")
		def b = Translator.translate("FALSE")
		assertTrue(a & a)
		assertFalse(a & b)
		assertFalse(b & a)
		assertFalse(b & b)
		
		assertTrue(a & true)
		assertTrue(true & a as java.lang.Boolean)
		
		assertFalse(a & false)
		assertFalse(false & a as java.lang.Boolean)
		
		assertFalse(b & false)
	}
	
	@Test
	void testOr() {
		def a = Translator.translate("TRUE")
		def b = Translator.translate("FALSE")
		assertTrue(a | a)
		assertTrue(a | b)
		assertTrue(b | a)
		assertFalse(b | b)
		
		assertTrue(a | true)
		assertTrue(true | b as java.lang.Boolean)
		assertFalse(false |  b as java.lang.Boolean)
	}
	
	@Test
	void testXor() {
		def a = Translator.translate("TRUE")
		def b = Translator.translate("FALSE")
		assertFalse(a ^ a)
		assertTrue(a ^ b)
		assertTrue(b ^ a)
		assertFalse(b ^ b)
		
		assertTrue(a ^ false)
		assertTrue(false ^ a  as java.lang.Boolean)
	}
}
