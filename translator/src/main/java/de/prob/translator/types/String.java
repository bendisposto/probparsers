package de.prob.translator.types;

public class String implements BObject {
	public java.lang.String getValue() {
		return value;
	}

	private final java.lang.String value;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		String bString = (String) o;

		if (!value.equals(bString.value))
			return false;

		return true;
	}

	public int length() {
		return value.length();
	}

	public boolean isEmpty() {
		return value.isEmpty();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public String(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String toString() {
		return '"' + this.value + '"';
	}

	public boolean isCase(Object o) {
		return this.value.equals(o);
	}
}
