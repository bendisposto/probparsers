package de.hhu.stups.btypes;

import org.pcollections.HashTreePSet;
import org.pcollections.PSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BSet implements BObject, Set<BObject> {

	private final PSet<BObject> set;

	public BSet(java.util.Set<BObject> elements) {
		this.set = HashTreePSet.from(elements);
	}

	public BSet(PSet<BObject> elements) {
		this.set = elements;
	}

	public BSet(BObject... elements) {
		this.set = HashTreePSet.from(Arrays.asList(elements));
	}

	public static LinkedHashSet<BObject> newStorage() {
		return new LinkedHashSet<>();
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
		throw new UnsupportedOperationException();
	}

	public Iterator<BObject> iterator() {
		return set.iterator();
	}

	public BSet intersect(BSet set) {
		return new BSet(this.set.minusAll(this.set.minusAll(set)));
	}

	public BSet complement(BSet set) {
		return new BSet(this.set.minusAll(set));
	}

	public BSet union(BSet set) {
		return new BSet(this.set.plusAll(set));
	}

	public static BSet range(BInteger a, BInteger b) {
		HashSet<BObject> set = new HashSet<>();
		for(BInteger i = a; i.lessEqual(b).booleanValue(); i = (BInteger) i.next()) {
			set.add(new BInteger(String.valueOf(i)));
		}
		return new BSet(set);
	}

	public BSet relationImage(BSet domain) {
		return new BSet(set.stream()
			.filter(object -> domain.contains(((BCouple) object).getFirst()))
			.map(object -> ((BCouple) object).getSecond())
			.collect(Collectors.toSet()));
	}


	public BObject functionCall(BObject arg) {
		List<BCouple> matchedCouples = set.stream()
			.map(object -> (BCouple) object)
			.filter(couple -> couple.getFirst().equals(arg))
			.collect(Collectors.toList());
		if(matchedCouples.size() > 0) {
			return matchedCouples.get(0).getSecond();
		}
		throw new RuntimeException("Argument is not in the key set of this map");
	}


	public BInteger card() {
		return new BInteger(String.valueOf(this.size()));
	}

	public BBoolean elementOf(BObject object) {
		return new BBoolean(this.contains(object));
	}

	public BBoolean equal(BSet o) {
		return new BBoolean(equals(o));
	}

	public BBoolean unequal(BSet o) {
		return new BBoolean(!equals(o));
	}

}
