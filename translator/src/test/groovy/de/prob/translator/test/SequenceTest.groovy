package de.prob.translator.test;
import de.prob.translator.Translator
import de.prob.translator.types.BObject
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class SequenceTest {

	def seq1
	def seq2
	def atom_a

	@Before
	public void setUp() throws Exception {
		this.seq1 = Translator.translate("[a,b,c]")
		this.seq2 = Translator.translate("[]")
		this.atom_a = Translator.translate("a")
	}

	@Test
	public void testSize() {
		assert seq1.size() == 3
		assert seq2.size() == 0
	}

	@Test
	public void testIsEmpty() {
		assertFalse seq1.isEmpty()
		assert seq2.isEmpty()
	}

	@Test
	public void testContains() {
		assert seq1.contains(atom_a)
		assertFalse seq2.contains(atom_a)
	}

	@Test
	public void testIterator() {
		def values = []
		seq1.each {
			values << it.toString();
		}
		assert values == ['a', 'b', 'c']
		values = []

		for(BObject i : seq1) {
			values << i.toString()
		}
		assert values == ['a', 'b', 'c']
	}

	@Test
	public void testToArray() {
		def a = seq1.toArray()
		assert a.size() == 3
		assert a[0].toString() == 'a'
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAdd() {
		seq1.add(atom_a);
	}

	@Test
	public void testContainsAll() {
		assert seq1.containsAll([atom_a])
		assertFalse seq1.containsAll([5])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAddAll() {
		seq1.addAll([atom_a])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() {
		seq1.removeAll([atom_a])
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testClear() {
		seq1.clear()
	}

	@Test
	public void testEquals() {
		def seq3 = Translator.translate("[a,b,c]");
		assert seq1 == seq3
		assert seq1.equals(seq3)
		assertFalse(seq1 == seq2)
	}

	@Test
	public void testGet() {
		assert seq1.get(1) == atom_a
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetIndex0() {
		assert seq1.get(0)
	}

	@Test
	public void testGetAt() {
		assert seq1.getAt(1) == atom_a
		assert seq1[1] == atom_a
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSet() {
		seq1.set(2, atom_a)
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testRemove() {
		seq1.remove(1)
	}

	@Test
	public void testIndexOf() {
		assert seq1.indexOf(atom_a) == 1
	}

	@Test
	public void testLastIndexOf() {
		assert seq1.lastIndexOf(atom_a) == 1
	}

	@Test
	public void testToString() {
		assert seq1.toString() == "[a, b, c]"
	}
}
