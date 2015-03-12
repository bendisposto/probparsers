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

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(Object o) {
        return elements.contains(o);
    }

    public Iterator<BObject> iterator() {
        return elements.iterator();
    }

    public Object[] toArray() {
        return elements.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }

    public boolean add(BObject bObject) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
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
        return elements.retainAll(c);
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple bObjects = (Tuple) o;

        if (elements != null ? !elements.equals(bObjects.elements) : bObjects.elements != null) return false;

        return true;
    }

    public int hashCode() {
        return elements.hashCode();
    }

    public BObject get(int index) {
        return elements.get(index);
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
        return elements.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return elements.lastIndexOf(o);
    }

    public ListIterator<BObject> listIterator() {
        return elements.listIterator();
    }

    public ListIterator<BObject> listIterator(int index) {
        return elements.listIterator(index);
    }

    public List<BObject> subList(int fromIndex, int toIndex) {
        return elements.subList(fromIndex, toIndex);
    }

    public BObject getFirst() {
        return this.get(0);
    }

    public BObject getSecond() {
        return this.get(1);
    }

    public BObject getAt(final int key) {
        return this.get(key);
    }

    @Override
    public String toString() {


        return "(" + this.getFirst() + " |-> " + this.getSecond() + ')';
    }
}
