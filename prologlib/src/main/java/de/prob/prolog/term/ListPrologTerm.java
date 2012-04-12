/**
 * (c) 2009 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, Heinrich
 * Heine Universitaet Duesseldorf This software is licenced under EPL 1.0
 * (http://www.eclipse.org/org/documents/epl-v10.html)
 * */

/**
 * 
 */
package de.prob.prolog.term;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import de.prob.prolog.output.IPrologTermOutput;

/**
 * Represents a Prolog list.
 * 
 * @author plagge
 */
public final class ListPrologTerm extends PrologTerm implements
		List<PrologTerm> {

	private static final long serialVersionUID = -629922806578121593L;

	public static final ListPrologTerm EMPTY_LIST = new ListPrologTerm(
			new PrologTerm[0]);

	private final PrologTerm[] elements;

	private final int start;
	private final int end;

	public ListPrologTerm(final PrologTerm... elements) {
		super(".",elements);
		if (elements == null)
			throw new IllegalStateException(
					"elements of Prolog list must not be null");
		this.elements = elements;
		this.start = 0;
		this.end = elements.length;
	}

	public ListPrologTerm(int start, int end, ListPrologTerm org) {
		super(".");
		this.start = start;
		this.end = end;
		if (org == null)
			throw new IllegalStateException(
					"elements of Prolog list must not be null");
		this.elements = org.elements;
	}

	@Override
	public boolean isList() {
		return true;
	}

	public int size() {
		return end - start;
	}

	public PrologTerm get(final int index) {
		int i = index + start;
		if (i >= end)
			throw new ArrayIndexOutOfBoundsException();
		return elements[i];
	}

	@Override
	public void toTermOutput(final IPrologTermOutput pto) {
		pto.openList();
		for (int i = start; i < end; i++) {
			elements[i].toTermOutput(pto);
		}
		pto.closeList();
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (other == null || !(other instanceof ListPrologTerm))
			return false;
		return Arrays.equals(elements, ((ListPrologTerm) other).elements);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements) + start * 13 + end;
	}

	public Iterator<PrologTerm> iterator() {
		return listIterator();
	}

	public boolean add(final PrologTerm o) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(final Collection<? extends PrologTerm> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(final Object o) {
		for (int i = start; i < end; i++) {
			if (elements[i].equals(o))
				return true;
		}
		return false;
	}

	public boolean containsAll(final Collection<?> c) {
		boolean contained = true;
		for (Object o : c) {
			contained &= contains(o);
		}
		return contained;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean remove(final Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(final Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray() {
		Object[] res = new Object[size()];
		System.arraycopy(elements, start, res, 0, size());
		return res;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		int size = size();
		if (a.length < size) {
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass()
					.getComponentType(), size);
		}
		System.arraycopy(elements, 0, a, 0, size);
		if (a.length > size) {
			a[size] = null;
		}
		return a;
	}

	public void add(final int arg0, final PrologTerm arg1) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(final int arg0,
			final Collection<? extends PrologTerm> arg1) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(final Object object) {
		for (int i = start; i < end; i++) {
			if (elements[i].equals(object))
				return i;
		}
		return -1;
	}

	public int lastIndexOf(final Object object) {
		for (int i = end - 1; i >= start; i++) {
			if (elements[i].equals(object))
				return i;
		}
		return -1;
	}

	public ListIterator<PrologTerm> listIterator() {
		return new PrologTermListIterator(elements, start, end);
	}

	public ListIterator<PrologTerm> listIterator(final int index) {
		return new PrologTermListIterator(elements, index, end);
	}

	public PrologTerm remove(final int arg0) {
		throw new UnsupportedOperationException();
	}

	public PrologTerm set(final int arg0, final PrologTerm arg1) {
		throw new UnsupportedOperationException();
	}

	public List<PrologTerm> subList(int start, int end) {
		return new ListPrologTerm(start, end, this);
	}

	public ListPrologTerm tail() {
		if (isEmpty()) throw new IllegalStateException("Cannot call tail on an empty list");
		if (size() == 1) return EMPTY_LIST;
	   return new ListPrologTerm(start+1,end,this);
	}

	public PrologTerm head() {
		return get(0);
	}

}
