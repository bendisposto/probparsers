package de.bmoth.codegenerator.types;

public class BAtom implements BObject {
	private final String value;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BAtom that = (BAtom) o;

		if (!value.equals(that.value))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public String getValue() {
		return value;
	}

	public BAtom(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
