package de.prob.prolog.term;

import java.util.ListIterator;

class PrologTermListIterator implements ListIterator<PrologTerm> {

	private final PrologTerm[] elements;
	private final int start;
	private final int end;
	private int next;

	public PrologTermListIterator(PrologTerm[] elements, int start, int end) {
		this.elements = elements;
		this.start = start;
		this.end = end;
		this.next = start;
	}

	public boolean hasNext() {
		return next < end;
	}

	public PrologTerm next() {
		return elements[next++];
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public void add(PrologTerm arg0) {
		throw new UnsupportedOperationException();
	}

	public boolean hasPrevious() {
		return next > start;
	}

	public int nextIndex() {
		return Math.min(next-start, end);
	}

	public PrologTerm previous() {
		return elements[--next];
	}

	public int previousIndex() {
		if (next == start)
			return -1;
		return next - 1;
	}

	public void set(PrologTerm arg0) {
		throw new UnsupportedOperationException();
	}

}