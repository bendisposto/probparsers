package de.prob.translator.test

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import de.prob.translator.Translator

class StringTest {

	def str1
	def str2

	@Before
	public void setUp() throws Exception {
		str1 = Translator.translate("\"lorem ipsum\"")
		str2 = Translator.translate("\"\"")
	}

	@Test
	public void testGetValue() {
		assert str1.getValue() == "lorem ipsum"
		assert str2.getValue() == ""
	}

	@Test
	public void testEqualsObject() {
		def other = Translator.translate("\"lorem ipsum\"")
		assertFalse str1 == str2
		assert str1 == other
	}

	@Test
	public void testLength() {
		assert str1.length() == 11
		assert str2.length() == 0
	}

	@Test
	public void testIsEmpty() {
		assertFalse str1.isEmpty()
		assert str2.isEmpty()
	}

	@Test
	public void testToString() {
		assert str1.toString() == "\"lorem ipsum\""
		assert str2.toString() == "\"\""
	}

	@Test
	public void testIsCase() {
		switch("lorem ipsum") {
			case str1:
				assert true
				break
			default:
				assert false
		}
	}
	
	@Test
	public void testAsType() {
		assert (str1 as java.lang.String).getClass() == java.lang.String
	}
}
