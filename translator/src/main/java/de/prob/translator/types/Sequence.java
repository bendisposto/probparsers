package de.prob.translator.types;

import java.lang.String;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Sequence implements List<BObject>, BObject {
	private final List<BObject> list;

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public boolean contains(Object o) {
		return list.contains(o);
	}

	public Iterator<BObject> iterator() {
		return new SequenceIterator(this);
	}

	public Object[] toArray() {
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public boolean add(BObject bObject) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	public boolean addAll(Collection<? extends BObject> c) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(int index, Collection<? extends BObject> c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Sequence bObjects = (Sequence) o;
		return list.equals(bObjects.list);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public BObject get(final int index) {
		if (index < 1 || index > this.size()) {
			throw new IndexOutOfBoundsException("Invalid index " + index);
		}
		int idx = index - 1;
		return list.get(idx);
	}

	public BObject getAt(final int key) {
		return this.get(key);
	}

	public BObject set(int index, BObject element) {
		throw new UnsupportedOperationException();
	}

	public void add(int index, BObject element) {
		throw new UnsupportedOperationException();
	}

	public BObject remove(int index) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o) {
		return list.indexOf(o) + 1;
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o) + 1;
	}

	public ListIterator<BObject> listIterator() {
		return new SequenceIterator(this);
	}

	public ListIterator<BObject> listIterator(int index) {
		return new SequenceIterator(this, index);
	}

	public List<BObject> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	public Sequence(List<BObject> l) {
		this.list = l;
	}

	public Sequence() {
		this.list = new java.util.ArrayList<BObject>();
	}

	public String toString() {
		return list.toString();
	}
}
