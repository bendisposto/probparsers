package de.prob.translator.test
import de.prob.translator.Translator
import org.junit.Test

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue;


public class NumberTest {
    @Test
    void testAddNubers() {
        def one = Translator.translate("1")
        def two = Translator.translate("2")
        def three = one + two
        assertTrue(three == 3);
    }
	
	@Test void testCompareNumbers() {
		def three = Translator.translate("3")
		assertTrue(three == 3)
		assertTrue(three == new Long(3))
		assertTrue(three != 4)
		assertTrue(three < 4)
		assertTrue(three <= 5)
		assertTrue(three > 1)
		assertTrue(three >= 3)
    }
	
	@Test void testAddOverflows() {
		def a = Translator.translate(""+Long.MAX_VALUE)
		def b = Translator.translate(""+Long.MAX_VALUE)
		def aa = new java.math.BigInteger(""+Long.MAX_VALUE)
		def bb = new java.math.BigInteger(""+Long.MAX_VALUE)
		assertTrue(a + b == aa+bb );
	}
	
	@Test void testBitwiseNegate() {
		def a = 5;
		def aa = Translator.translate("5");
		assertTrue(aa == a);
		assertTrue(~aa == ~a);
	}
	
	@Test void testNegativeAndPositive() {
		def a = 5;
		def aa = Translator.translate("5");
		
		
		assertTrue(aa == a);
		assertTrue(-aa == -a);
		assertTrue(+aa == +a);
	}
	
	@Test void testisCase() {
		def a = Translator.translate("5");
		def aa = Translator.translate("5");
		switch(a) {
			case 5:
				assertTrue(true);
				break;
			default:
				assertTrue(false);
		}
		
		switch(a) {
			case aa:
				assertTrue(true);
				break;
			default:
				assertTrue(false);
		}
		
		switch(a) {
			case 7:
				assertTrue(false);
				break;
			default:
				assertTrue(true);
				
		}
	
	}
}