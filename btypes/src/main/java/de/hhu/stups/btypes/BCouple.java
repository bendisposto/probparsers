package de.hhu.stups.btypes;

import java.util.Objects;

public class BCouple implements BObject {

	private BObject first;

	private BObject second;

	public BCouple(BObject first, BObject second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		this.second = second;
	}


	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

        BCouple bObjects = (BCouple) o;
		// elements is never null
		return bObjects.getFirst().equals(this.first) && bObjects.getSecond().equals(this.second);
	}

	public int hashCode() {
		return Objects.hash(first, second);
	}

	@Override
	public java.lang.String toString() {
		return "(" + this.getFirst() + " |-> " + this.getSecond() + ')';
	}

	public BObject getFirst() {
		return first;
	}

	public BObject getSecond() {
		return second;
	}

	public BBoolean equal(BCouple o) {
		return new BBoolean(equals(o));
	}

	public BBoolean unequal(BCouple o) {
		return new BBoolean(!equals(o));
	}

}
