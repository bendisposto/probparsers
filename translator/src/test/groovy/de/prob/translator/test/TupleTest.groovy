package de.prob.translator.test
import de.prob.translator.Translator

import org.junit.Before;
import org.junit.Test

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue;


class TupleTest {
	def t1
	def one 
	def other
	@Before
	public void setUp() throws Exception {
		this.t1 = Translator.translate("(1 |-> 2)")
		
		this.one = Translator.translate("1")
		this.other = Translator.translate("99")
	}

	@Test
	public void tesṭSize() {
		assert t1.size() == 2
	}

	@Test
	public void testIsEmpty() {
		assertFalse t1.isEmpty() 
	}

	@Test
	public void testContains() {
		assertTrue t1.contains(one)
		assertFalse t1.contains(other)
	}

	@Test
	public void testIterator() {
		def sols = []
		for(Number x: t1) {
			sols << x.intValue()
		}
		assertTrue(sols == [1,2])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddBObject() {
		t1 << one 
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveObject() {
		t1.remove(one)
	}

	@Test
	public void testContainsAll() {
		assertTrue(t1.containsAll([one]))
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		t1.addAll([one])
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		t1.removeAll([one])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		t1.clear()
	}

	@Test
	public void testEqualsObject() {
		def t1_1 = Translator.translate("(1 |-> 2)")
		assertTrue(t1.equals(t1_1))
	}

	@Test
	public void testGet() {
		assertTrue t1.get(0) == one
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSet() {
		t1.set(0, other)
	}

	@Test
	public void testGetFirst() {
		assertTrue(t1.first.intValue() == 1)
	}

	@Test
	public void testGetSecond() {
		assertTrue(t1.second.intValue() == 2)
	}

	@Test
	public void testGetAt() {
		assertTrue(t1[0].intValue() == 1)
	}

	@Test
	public void testToString() {
		assertTrue(t1.toString() == "(1 |-> 2)")
	}

}
