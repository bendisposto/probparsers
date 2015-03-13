package de.prob.translator.test

import static org.junit.Assert.*

import java.lang.reflect.Array
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import de.prob.translator.Translator

class SetTest {

	def set1
	def set2
	def one

	@Before
	public void setUp() throws Exception {
		set1 = Translator.translate("{1,2,3}")
		set2 = Translator.translate("{}")
		one = Translator.translate("1")
	}

	@Test
	public void testToString() {
		assert set1.toString() == "{1, 2, 3}"
		assert set2.toString() == "{}"
	}

	@Test
	public void testSize() {
		assert set1.size() == 3
		assert set2.size() == 0
	}

	@Test
	public void testIsEmpty() {
		assertFalse set1.isEmpty()
		assert set2.isEmpty()
	}

	@Test
	public void testContains() {
		assert set1.contains(one)
		assertFalse set1.contains(2)
		
		assertFalse(set2.contains(one))
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		set1.add(one)
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		set1.remove(one)
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		set1.clear()
	}

	@Test
	public void testEqualsObject() {
		def set3 = Translator.translate("{1,2,3}")
		assertFalse set1 == set2
		assert set1 == set3
		assertFalse set2 == one
		
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		set1.removeAll([one])
	}

	@Test
	public void testToArray() {
		def a = set1.toArray()
		assert a.size() == 3
		assert a.contains(one)
	}

	@Test
	public void testContainsAll() {
		assert set1.containsAll([one])
		assertFalse set2.containsAll([one])
	}
	

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		set1.addAll([one])
	}

	@Test
	public void testIterator() {
		def values = []
		set1.each {
			values << it.intValue()
		}
		values.sort()
		assert values == [1,2,3]
	}
}
