package de.prob.translator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.prob.translator.Translator

class AtomTest {

	def atom
	@Before
	public void setUp() throws Exception {
		this.atom = Translator.translate("atom")
	}

	@Test
	public void testToString() {
		assert this.atom.toString() == "atom";
	}
	
	@Test
	public void testEquals() {
		assert this.atom == Translator.translate("atom")
		assert this.atom != Translator.translate("other")
	}
}
