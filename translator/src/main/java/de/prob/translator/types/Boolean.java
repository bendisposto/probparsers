package de.prob.translator.types;

public class Boolean implements BObject {
	private final java.lang.Boolean value;

	public static boolean parseBoolean(java.lang.String s) {
		return java.lang.Boolean.parseBoolean(s);
	}

	public static java.lang.String toString(boolean b) {
		return java.lang.Boolean.toString(b);
	}

	public static java.lang.Boolean valueOf(boolean b) {
		return java.lang.Boolean.valueOf(b);
	}

	public int compareTo(java.lang.Boolean b) {
		return value.compareTo(b);
	}

	public static java.lang.Boolean valueOf(java.lang.String s) {
		return java.lang.Boolean.valueOf(s);
	}

	public static int compare(boolean x, boolean y) {
		return java.lang.Boolean.compare(x, y);
	}

	public static boolean getBoolean(java.lang.String name) {
		return java.lang.Boolean.getBoolean(name);
	}

	public boolean booleanValue() {
		return value.booleanValue();
	}

	@Override
	public java.lang.String toString() {
		return value.toString();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return value.equals(obj);
	}

	public Boolean(boolean value) {
		this.value = new java.lang.Boolean(value);
	}

	public Boolean(java.lang.String s) {
		this.value = new java.lang.Boolean(s);
	}

	/* groovy operator overloading support */
	@SuppressWarnings("rawtypes")
	Object asType(Class clazz) {
		if (clazz == new java.lang.Boolean(true).getClass()) {
			return this.booleanValue();
		}
		return this;
	}

	public boolean or(Boolean other) {
		return this.booleanValue() || other.booleanValue();
	}

	public boolean or(java.lang.Boolean other) {
		return this.booleanValue() || other;
	}

	public boolean xor(Boolean other) {
		return this.booleanValue() ^ other.booleanValue();
	}

	public boolean xor(java.lang.Boolean other) {
		return this.booleanValue() ^ other;
	}

	public boolean and(Boolean other) {
		return this.booleanValue() && other.booleanValue();
	}

	public boolean and(java.lang.Boolean other) {
		return this.booleanValue() && other;
	}
}
