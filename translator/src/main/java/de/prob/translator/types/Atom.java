package de.prob.translator.types;

public class Atom implements BObject {
	private final java.lang.String value;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Atom that = (Atom) o;

		if (!value.equals(that.value))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public java.lang.String getValue() {
		return value;
	}

	public Atom(java.lang.String value) {
		this.value = value;
	}

	@Override
	public java.lang.String toString() {
		return this.value;
	}
}
