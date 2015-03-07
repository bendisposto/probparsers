package de.prob.translator.types;

import java.lang.String;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Tuple implements BObject, List<BObject> {
    private final List<BObject> elements;

    public Tuple(List<BObject> s) {
        if (s.size() != 2) {
            throw new IllegalArgumentException("");
        }
        this.elements = s;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<BObject> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
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
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BObject> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends BObject> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple bObjects = (Tuple) o;

        if (elements != null ? !elements.equals(bObjects.elements) : bObjects.elements != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public BObject get(int index) {
        return elements.get(index);
    }

    @Override
    public BObject set(int index, BObject element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, BObject element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BObject remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        return elements.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    @Override
    public ListIterator<BObject> listIterator() {
        return elements.listIterator();
    }

    @Override
    public ListIterator<BObject> listIterator(int index) {
        return elements.listIterator(index);
    }

    @Override
    public List<BObject> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }

    public BObject getFirst() {
        return this.get(0);
    }

    public BObject getSecond() {
        return this.get(1);
    }

    @Override
    public String toString() {
        return "(" + this.getFirst() + " |-> " + this.getSecond() + ')';
    }
}
