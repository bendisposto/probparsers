package de.prob.translator.types;

import java.lang.String;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Set implements BObject, java.util.Set<BObject> {
	private final HashSet<BObject> set;

	public Set(java.util.Set<BObject> elements) {
		this.set = new HashSet<BObject>(elements);
	}

	public Set() {
		this.set = new HashSet<BObject>();
	}

	public String toString() {
		return "{" + set + '}';
	}

	public int size() {
		return this.set.size();
	}

	public boolean isEmpty() {
		return this.set.isEmpty();
	}

	public boolean contains(Object o) {
		return set.contains(o);
	}

	public boolean add(BObject bObject) {
		throw new UnsupportedOperationException();
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		return new Set((java.util.Set<BObject>) this.set.clone());
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Set bObjects = (Set) o;

		if (!set.equals(bObjects.set))
			return false;

		return true;
	}

	public int hashCode() {
		return set.hashCode();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public Object[] toArray() {
		return set.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}

	public boolean addAll(Collection<? extends BObject> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		return set.retainAll(c);
	}

	public Iterator<BObject> iterator() {
		return set.iterator();
	}
}
