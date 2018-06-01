package de.bmoth.codegenerator.types;

import java.util.ListIterator;
import java.util.NoSuchElementException;

class BSequenceIterator implements ListIterator<BObject> {

	private int i;
	private BSequence seq;

	public BSequenceIterator(BSequence seq, int start) {
		this.i = start - 1;
		this.seq = seq;
	}

	public BSequenceIterator(BSequence seq) {
		this.i = 0;
		this.seq = seq;
	}

	/**
	 * Returns {@code true} if this list iterator has more elements when
	 * traversing the list in the forward direction. (In other words,
	 * returns {@code true} if {@link #next} would return an element rather
	 * than throwing an exception.)
	 *
	 * @return {@code true} if the list iterator has more elements when
	 *         traversing the list in the forward direction
	 */
	public boolean hasNext() {
		return this.i >= 0 && this.i < seq.size();
	}

	/**
	 * Returns the next element in the list and advances the cursor
	 * position. This method may be called repeatedly to iterate through the
	 * list, or intermixed with calls to {@link #previous} to go back and
	 * forth. (Note that alternating calls to {@code next} and
	 * {@code previous} will return the same element repeatedly.)
	 *
	 * @return the next element in the list
	 * @throws NoSuchElementException
	 *             if the iteration has no next element
	 */
	public BObject next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException();
		}
		return new BTuple(BNumber.build(++this.i),
				this.seq.get(this.i));
	}

	/**
	 * Returns {@code true} if this list iterator has more elements when
	 * traversing the list in the reverse direction. (In other words,
	 * returns {@code true} if {@link #previous} would return an element
	 * rather than throwing an exception.)
	 *
	 * @return {@code true} if the list iterator has more elements when
	 *         traversing the list in the reverse direction
	 */
	public boolean hasPrevious() {
		return this.i > 0 && this.i <= seq.size() + 1;
	}

	/**
	 * Returns the previous element in the list and moves the cursor
	 * position backwards. This method may be called repeatedly to iterate
	 * through the list backwards, or intermixed with calls to {@link #next}
	 * to go back and forth. (Note that alternating calls to {@code next}
	 * and {@code previous} will return the same element repeatedly.)
	 *
	 * @return the previous element in the list
	 * @throws NoSuchElementException
	 *             if the iteration has no previous element
	 */
	public BObject previous() {
		if(!this.hasPrevious()) {
			throw new NoSuchElementException();
		}
		return new BTuple(BNumber.build(this.i),
				this.seq.get(this.i--));
	}

	/**
	 * Returns the index of the element that would be returned by a
	 * subsequent call to {@link #next}. (Returns list size if the list
	 * iterator is at the end of the list.)
	 *
	 * @return the index of the element that would be returned by a
	 *         subsequent call to {@code next}, or list size if the list
	 *         iterator is at the end of the list
	 */
	public int nextIndex() {
		if (this.i == this.seq.size()) {
			return this.i;
		}
		return this.i + 1;
	}

	/**
	 * Returns the index of the element that would be returned by a
	 * subsequent call to {@link #previous}. (Returns -1 if the list
	 * iterator is at the beginning of the list.)
	 *
	 * @return the index of the element that would be returned by a
	 *         subsequent call to {@code previous}, or -1 if the list
	 *         iterator is at the beginning of the list
	 */
	public int previousIndex() {
		return this.i - 1;
	}

	public void set(BObject e) {
		throw new UnsupportedOperationException();
	}

	public void add(BObject e) {
		throw new UnsupportedOperationException();
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
