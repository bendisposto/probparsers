package de.hhu.stups.btypes;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BSet implements BObject, Set<BObject> {

	private final ImmutableSet<BObject> set;

	public BSet(java.util.Set<BObject> elements) {
		this.set = ImmutableSet.copyOf(elements);
	}

	public static LinkedHashSet<BObject> newStorage() {
		return new LinkedHashSet<BObject>();
	}

	public java.lang.String toString() {
		Iterator<BObject> it = this.iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		while (it.hasNext()) {
			BObject b = (BObject) it.next();
			sb.append(b.toString());
			if (it.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BSet bObjects = (BSet) o;

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

	public BSet intersect(BSet set) {
		return new BSet(this.stream()
				.filter(set::contains)
				.collect(Collectors.toSet()));
	}

	public BSet complement(BSet set) {
		return new BSet(this.stream()
				.filter(element -> !set.contains(element))
				.collect(Collectors.toSet()));
	}

	public BSet union(BSet set) {
		HashSet<BObject> result = new HashSet<>(this);
		result.addAll(set);
		return new BSet(result);
	}

	public static BSet range(BInteger a, BInteger b) {
		HashSet<BObject> set = new HashSet();
		//TODO test critical values
		for(int i = a.intValue(); i < b.intValue() + 1; i++) {
			set.add(new BInteger(new java.math.BigInteger(String.valueOf(i))));
		}
		return new BSet(set);
	}

	public BObject call(BObject arg) {
		for(BObject object : set) {
			BTuple tuple = (BTuple) object;
			if(tuple.getFirst().equals(arg)) {
				return tuple.getSecond();
			}
		}
		return null;
	}

}
