package de.prob.translator.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import de.prob.translator.Translator


class RecordTest {

	def rec1
	def rec2
	def rec3
	def one
	@Before
	public void setUp() throws Exception {
		this.rec1 = Translator.translate("rec(a:1, b:2)")
		this.rec2 = Translator.translate("rec(a:1, b:2)")
		this.rec3 = Translator.translate("rec(a:1, b:3)")
		this.one = Translator.translate("1")
	}

	@Test
	public void testSize() {
		assert rec1.size() == 2
	}

	@Test
	public void testIsEmpty() {
		assertFalse rec1.isEmpty()
	}

	@Test
	public void testContainsKey() {
		assert rec1.containsKey("a")
		assertFalse rec1.containsKey("c")
	}

	@Test
	public void testContainsValue() {
		assert rec1.containsValue(one)
		assertFalse rec1.containsValue(1)
		assertFalse rec1.containsValue(3)
	}

	@Test
	public void testGet() {
		assert rec1.get('a') == one
	}

	@Test
	public void testGetAt() {
		assert rec1['a'] == one
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testPutAt() {
		rec1['a'] = 5
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testPut() {
		rec1.put('a', one)
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		rec1.remove(one)
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testPutAll() {
		rec1.putAll(['c': one])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		rec1.clear()
	}

	@Test
	public void testKeySet() {
		rec1.keySet().size() == 2
		rec1.keySet().containsAll(['a', 'b'])
	}

	@Test
	public void testEquals() {
		assert rec1.equals(rec2)
		assertFalse rec1.equals(rec3)
	}

	@Test
	public void testToString() {
		def str = rec1.toString();
		assert str == "rec(a: 1, b: 2)"
	}
}
