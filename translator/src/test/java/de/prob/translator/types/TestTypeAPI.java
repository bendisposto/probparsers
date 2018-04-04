package de.prob.translator.types;

import de.prob.translator.Translator;
import de.prob.translator.types.Number;
import de.prob.translator.types.String;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTypeAPI {

	@Test
	public void testSetIteration() throws Exception {
		Set s = (Set) Translator.translate("{1,2,3, \"a\"}");
		assertEquals(4, s.size());

		assertTrue(s.contains(new String("a")));
		BObject[] c = s.toArray(new BObject[] {});
		assertEquals(4, c.length);
		for (BObject bObject : c) {
			assertTrue(s.contains(bObject));
		}
		ArrayList<BObject> bo = new ArrayList<BObject>();
		for (BObject bObject : s) {
			bo.add(bObject);
		}
		assertEquals(4, bo.size());
	}

	@Test
	public void testString() throws Exception {
		Sequence s = (Sequence) Translator.translate("[\"a\", \"word\", \"a\"]");
		String s1 = (String) s.get(1);
		String s2 = (String) s.get(2);
		String s3 = (String) s.get(3);

		assertEquals(s1, s3);
		assertSame(s1, s1);
		assertFalse(s1.equals(s2));
		assertEquals(4, s2.length());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testTuple() throws Exception {
		Tuple t1 = (Tuple) Translator.translate("(1,2)");
		Tuple t2 = (Tuple) Translator.translate("(1 |-> 2)");
		assertEquals(t1, t2);
		assertEquals(t1.get(0), t2.get(0));
		for (BObject bObject : t1) {
			t2.contains(bObject);
		}
		assertEquals(t1.get(0), t1.getFirst());
		assertEquals(t1.get(1), t1.getSecond());

		assertTrue(t1.equals(t1));
		assertTrue(t1.equals(t2));
		assertFalse(t1.equals(null));
		assertFalse(t1.equals(Number.build(5)));
		assertEquals(t1.hashCode(), t2.hashCode());

		Object[] array = t1.toArray();
		assertEquals(t1.getFirst(), array[0]);
		assertEquals(t1.getSecond(), array[1]);
		assertEquals(array.length, t1.toArray(array).length);

		assertEquals(0, t1.indexOf(Number.build(1)));
		assertEquals(0, t1.lastIndexOf(Number.build(1)));
		assertFalse(t1.listIterator().next().equals(t1.listIterator(1).next()));
		assertTrue(t1.subList(1, 2).get(0).equals(t1.get(1)));

	}

	@Test
	public void testTupleExceptions() throws Exception {
		try {
			new Tuple(null);
			fail("expecting exception");
		} catch (IllegalArgumentException e) {
		}
		try {
			List<BObject> list = new ArrayList<>();
			list.add(Number.build(5));
			new Tuple(list);
			fail("expecting exception");
		} catch (IllegalArgumentException e) {
		}
		try {
			new Tuple(null, Number.build(5));
			fail("expecting exception");
		} catch (IllegalArgumentException e) {
		}
		try {
			new Tuple(Number.build(5), null);
			fail("expecting exception");
		} catch (IllegalArgumentException e) {
		}
		Tuple t1 = (Tuple) Translator.translate("(1,2)");
		try {
			t1.addAll(1, new ArrayList<BObject>());
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			t1.add(1, Number.build(5));
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			t1.remove(1);
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			t1.retainAll(new ArrayList<BObject>());
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
	}

	@Test
	public void testBInt() throws Exception {
		Number a = Number.build(5);
		Number b = Number.build(6);
		Number c = Number.build(5);

		assertEquals(a, c);

		Number aa = Number.build("123123123123123123121");
		Number bb = Number.build("123123123123123123123");
		Number cc = Number.build("123123123123123123121");

		assertEquals(aa, cc);

		assertEquals(Number.build(11), Number.build(11L));

		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
		assertTrue(a.compareTo(a) == 0);

		assertTrue(aa.compareTo(bb) < 0);
		assertTrue(bb.compareTo(aa) > 0);
		assertTrue(aa.compareTo(aa) == 0);

		assertEquals((int) a.floatValue(), (int) a.doubleValue());

		BigInteger b1 = new BigInteger(new java.math.BigInteger("5"));
		assertEquals(Number.build(2), b1.minus(Number.build(3)));
		assertEquals(Number.build(15), b1.multiply(Number.build(3)));
		assertEquals(Number.build(25), b1.power(Number.build(2)));
		assertEquals(Number.build(1), b1.div(Number.build(5)));
		assertEquals(Number.build(2), b1.mod(Number.build(3)));
		assertEquals(Number.build(-1), b1.or(Number.build(-5)));
		assertEquals(Number.build(5), b1.or(Number.build(5)));
		assertEquals(Number.build(5), b1.and(Number.build(5)));
		assertEquals(Number.build(0), b1.xor(Number.build(5)));
		assertEquals(Number.build(6), b1.next());
		assertEquals(Number.build(4), b1.previous());
		assertEquals(Number.build(5), b1.positive());
		assertEquals(Number.build(-5), b1.negative());
		assertEquals(Number.build(10), b1.leftShift(Number.build(1)));
		assertEquals(Number.build(2), b1.rightShift(Number.build(1)));
		assertEquals(31, new BigInteger(null).hashCode());
	}

	@Test
	public void testBIntExceptions() throws Exception {
		BigInteger a = new BigInteger(new java.math.BigInteger("5"));
		try {
			a.compareTo((java.lang.Number) null);
			fail("expecting exception");
		} catch (NullPointerException e) {
		}
		try {
			a.compareTo((Number) null);
			fail("expecting exception");
		} catch (NullPointerException e) {
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testSequence() throws Exception {
		Sequence s1 = (Sequence) Translator.translate("[1,2,3]");
		Sequence s2 = (Sequence) Translator.translate("[1,2,3]");
		Sequence s3 = (Sequence) Translator.translate("[1,2,1]");
		assertTrue(s1.equals(s1));
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(null));
		assertFalse(s1.equals(Number.build(3)));
		assertFalse(s1.equals(s3));
		assertEquals(s1.hashCode(), s2.hashCode());
		Object[] array = s1.toArray();
		assertEquals(array.length, s1.toArray(array).length);
	}

	@Test
	public void testSequencetExceptions() throws Exception {
		Sequence s = new Sequence();
		try {
			s.add(1, null);
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			s.get(2);
			fail("expecting exception");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			s.subList(1, 2);
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			s.retainAll(new ArrayList<>());
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			s.remove(null);
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}
		try {
			s.addAll(1, null);
			fail("expecting exception");
		} catch (UnsupportedOperationException e) {
		}

	}

}
