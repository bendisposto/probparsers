package de.prob.prolog.term;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Test;

import de.prob.prolog.output.PrologTermStringOutput;
import de.prob.prolog.term.CompoundPrologTerm;
import de.prob.prolog.term.IntegerPrologTerm;
import de.prob.prolog.term.ListPrologTerm;
import de.prob.prolog.term.PrologTerm;

public class ListPrologTermTest {
	@Test(expected = IllegalStateException.class)
	public void tailTestEmpty() {
		ListPrologTerm l = new ListPrologTerm(new PrologTerm[0]);
		l.tail(); // throws exception
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void headTestEmpty() {
		ListPrologTerm l = new ListPrologTerm(new PrologTerm[0]);
		l.head();// throws exception
	}

	@Test
	public void tailTest2() {
		ListPrologTerm l = new ListPrologTerm(
				new PrologTerm[] { new IntegerPrologTerm(42) });
		ListPrologTerm tail = l.tail();
		assertTrue(tail.isEmpty());
	}

	@Test
	public void tailTest3() {
		ListPrologTerm l = new ListPrologTerm(new PrologTerm[] {
				new IntegerPrologTerm(42), new IntegerPrologTerm(5) });
		ListPrologTerm tail = l.tail();
		assertFalse(tail.isEmpty());
		assertTrue(tail.tail().isEmpty());
	}

	@Test
	public void initTest1() {
		ListPrologTerm t1 = new ListPrologTerm(new PrologTerm[0]);
		ListPrologTerm t2 = new ListPrologTerm();
		assertEquals(t1, t2);
	}

	@Test
	public void initTest2() {
		ListPrologTerm t1 = new ListPrologTerm(new PrologTerm[] {
				new IntegerPrologTerm(42), new IntegerPrologTerm(5) });
		ListPrologTerm t2 = new ListPrologTerm(new IntegerPrologTerm(42),
				new IntegerPrologTerm(5));
		assertEquals(t1, t2);
	}

	@Test
	public void testLength() {
		ListPrologTerm t2 = new ListPrologTerm(createFixture(10));
		assertEquals(10, t2.size());
		ListPrologTerm t1 = new ListPrologTerm(1, 4, t2);
		assertEquals(3, t1.size());
	}

	@Test
	public void testGet() {
		ListPrologTerm t1 = new ListPrologTerm(createFixture(10));
		assertEquals("4", getAsString(t1, 4));
		ListPrologTerm t2 = new ListPrologTerm(1, 5, t1);
		assertEquals("2", getAsString(t2, 1));
		assertEquals("4", getAsString(t2, 3));
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetOutOfBounds() {
		ListPrologTerm t1 = new ListPrologTerm(createFixture(10));
		assertEquals("4", getAsString(t1, 4));
		ListPrologTerm t2 = new ListPrologTerm(1, 5, t1);
		t2.get(7); // raise exception
	}

	@Test
	public void testToTermOutput() {
		ListPrologTerm term = new ListPrologTerm(1, 5, new ListPrologTerm(
				createFixture(10)));
		PrologTermStringOutput output = new PrologTermStringOutput();
		term.toTermOutput(output);
		assertEquals("['1','2','3','4']", output.toString());
	}

	@Test
	public void testIterator() {
		ListPrologTerm term = new ListPrologTerm(1, 3, new ListPrologTerm(
				createFixture(10)));
		ListIterator<PrologTerm> i = term.listIterator();
		assertFalse(i.hasPrevious());
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		assertEquals("1", getAsString(i.next()));
		assertEquals("2", getAsString(i.next()));
		assertFalse(i.hasNext());
		assertTrue(i.hasPrevious());
		assertEquals(2, i.nextIndex());
		assertEquals("2", getAsString(i.previous()));
		assertEquals("1", getAsString(i.previous()));
		assertTrue(i.hasNext());
	}

	@Test
	public void testEmptyIterator() {
		ListPrologTerm term = new ListPrologTerm();
		ListIterator<PrologTerm> i = term.listIterator();
		assertFalse(i.hasNext());
		assertFalse(i.hasPrevious());
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
	}

	@Test
	public void testSingletonIterator() {
		ListPrologTerm term = new ListPrologTerm(new CompoundPrologTerm("foo"));
		ListIterator<PrologTerm> i = term.listIterator();
		assertTrue(i.hasNext());
		assertFalse(i.hasPrevious());
		assertEquals(-1, i.previousIndex());
		assertEquals(0, i.nextIndex());
		PrologTerm next = i.next();
		assertEquals("foo", getAsString(next));
		PrologTerm previous = i.previous();
		assertEquals(previous, next);
	}
	
	@Test
	public void testMultiTail() {
		ListPrologTerm term = new ListPrologTerm(createFixture(3));
		PrologTerm h1 = term.head();
		PrologTerm h2 = term.tail().head();
		PrologTerm h3 = term.tail().tail().head();
		assertEquals("0", getAsString(h1));
		assertEquals("1", getAsString(h2));
		assertEquals("2", getAsString(h3));
	}
	
	@Test
	public void testMultiTail2() {
		ListPrologTerm term = new ListPrologTerm(createFixture(3));
		ListPrologTerm term2 = term.tail();
		assertEquals("1", getAsString(term2.head()));
		ListPrologTerm term3 = term2.tail();
		assertEquals("2", getAsString(term3.head()));
	}
	

	private String getAsString(ListPrologTerm l, int pos) {
		return l.get(pos).getFunctor();
	}

	private String getAsString(PrologTerm p) {
		return p.getFunctor();
	}

	private PrologTerm[] createFixture(int size) {
		PrologTerm[] res = new PrologTerm[size];
		for (int i = 0; i < res.length; i++) {
			res[i] = new CompoundPrologTerm("" + i);
		}
		return res;
	}

}
