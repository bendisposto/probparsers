package de.hhu.stups.btypes;

import clojure.java.api.Clojure;
import clojure.lang.AFn;
import clojure.lang.IFn;
import clojure.lang.PersistentHashSet;
import clojure.lang.RT;
import clojure.lang.Var;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class BSet implements BObject, Set<BObject> {

	private static final class createBInteger extends AFn {
		@Override
		public Object invoke(Object obj) {
			return new BInteger(Integer.parseInt(obj.toString()));
		}
	}

	private static final class Contains extends AFn {

		private BSet domain;

		public Contains(BSet domain) {
			this.domain = domain;
		}

		@Override
		public Object invoke(Object obj) {
			return domain.contains(((BCouple) obj).getFirst());
		}
	}

	private static final class EqualsFirst extends AFn {

		private BObject arg;

		public EqualsFirst(BObject arg) {
			this.arg = arg;
		}

		@Override
		public Object invoke(Object obj) {
			return ((BCouple) obj).getFirst().equals(arg);
		}
	}

	private static final class Second extends AFn {
		@Override
		public Object invoke(Object obj) {
			return ((BCouple) obj).getSecond();
		}
	}



	private static final Var SET;

	private static final Var EMPTY;

	private static final Var COUNT;

	private static final IFn INTERSECTION;

	private static final IFn UNION;

	private static final IFn DIFFERENCE;

	private static final IFn RANGE;

	private static final IFn MAP;

	private static final IFn FILTER;

	private static final IFn FIRST;

	private static final IFn INC;

	private static final IFn CREATE_INTEGER;


	static {
		RT.var("clojure.core", "require").invoke(Clojure.read("clojure.set"));
		SET = RT.var("clojure.core", "set");
		EMPTY = RT.var("clojure.core", "empty?");
		COUNT = RT.var("clojure.core", "count");
		INTERSECTION = RT.var("clojure.set", "intersection");
		UNION = RT.var("clojure.set", "union");
		DIFFERENCE = RT.var("clojure.set", "difference");
		RANGE = RT.var("clojure.core", "range");
		MAP = RT.var("clojure.core", "map");
		FILTER = RT.var("clojure.core", "filter");
		FIRST = RT.var("clojure.core", "first");
		INC = RT.var("clojure.core", "inc");
		CREATE_INTEGER = new createBInteger();
	}

	private final PersistentHashSet set;

	public BSet(PersistentHashSet elements) {
		this.set = elements;
	}

	public BSet(BObject... elements) {
		this.set = (PersistentHashSet) SET.invoke(elements);
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
		return (int) COUNT.invoke(this.set);
	}

	public boolean isEmpty() {
		return (boolean) EMPTY.invoke(this.set);
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
		return (T[]) set.toArray(a);
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
		return new BSet((PersistentHashSet) INTERSECTION.invoke(this.set, set.set));
	}

	public BSet complement(BSet set) {
		return new BSet((PersistentHashSet) DIFFERENCE.invoke(this.set, set.set));
	}

	public BSet union(BSet set) {
		return new BSet((PersistentHashSet) UNION.invoke(this.set, set.set));
	}

	public static BSet range(BInteger a, BInteger b) {
		return new BSet((PersistentHashSet) SET.invoke(
				MAP.invoke(CREATE_INTEGER, RANGE.invoke(a.getValue(), INC.invoke(b.getValue())))));
	}

	public BSet relationImage(BSet domain) {
		return new BSet((PersistentHashSet)
				SET.invoke(MAP.invoke(new Second(),
						FILTER.invoke(new Contains(domain), set))));
	}


	public BObject functionCall(BObject arg) {
		PersistentHashSet matchedCouples = (PersistentHashSet) SET.invoke(FILTER.invoke(new EqualsFirst(arg), set));
		if((int) COUNT.invoke(matchedCouples) > 0) {
			return ((BCouple) FIRST.invoke(matchedCouples)).getSecond();
		}
		throw new RuntimeException("Argument is not in the key set of this map");
	}


	public BInteger card() {
		return new BInteger((int) COUNT.invoke(this.set));
	}

	public BBoolean elementOf(BObject object) {
		return new BBoolean(this.set.contains(object));
	}

	public BBoolean equal(BSet o) {
		return new BBoolean(equals(o));
	}

	public BBoolean unequal(BSet o) {
		return new BBoolean(!equals(o));
	}

	public BObject nondeterminism() {
		int index = (int) Math.floor(Math.random() * set.size());
		return (BObject) toArray()[index];
	}

}