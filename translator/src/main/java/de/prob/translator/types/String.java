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

	/**
	 * Returns the length of this string. The length is equal to the number of
	 * <a href="Character.html#unicode">Unicode code units</a> in the string.
	 * 
	 * @return the length of the sequence of characters represented by this
	 *         object.
	 */
	public int length() {
		return value.length();
	}

	/**
	 * Returns <tt>true</tt> if, and only if, {@link #length()} is <tt>0</tt>.
	 * 
	 * @return <tt>true</tt> if {@link #length()} is <tt>0</tt>, otherwise
	 *         <tt>false</tt>
	 * @since 1.6
	 */
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
		return this.value;
	}
}
