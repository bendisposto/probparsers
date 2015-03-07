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

    @Override
    public String toString() {
        return "{" + set + '}';
    }

    @Override
    public int size() {
        return this.set.size();
    }

    @Override
    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public boolean add(BObject bObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object clone() {
        return new Set((java.util.Set<BObject>) this.set.clone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Set bObjects = (Set) o;

        if (!set.equals(bObjects.set)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BObject> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public Iterator<BObject> iterator() {
        return set.iterator();
    }
}
