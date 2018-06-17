package de.hhu.stups.btypes;

public class BBoolean implements BObject {
	private final Boolean value;

	public static boolean parseBoolean(String s) {
		return Boolean.parseBoolean(s);
	}

	public static String toString(boolean b) {
		return Boolean.toString(b);
	}

	public static Boolean valueOf(boolean b) {
		return Boolean.valueOf(b);
	}

	public int compareTo(Boolean b) {
		return value.compareTo(b);
	}

	public static Boolean valueOf(String s) {
		return Boolean.valueOf(s);
	}

	public static int compare(boolean x, boolean y) {
		return Boolean.compare(x, y);
	}

	public static boolean getBoolean(String name) {
		return Boolean.getBoolean(name);
	}

	public boolean booleanValue() {
		return value.booleanValue();
	}

	@Override
	public String toString() {
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

	public BBoolean(boolean value) {
		this.value = new Boolean(value);
	}

	public BBoolean(String s) {
		this.value = new Boolean(s);
	}

	/* groovy operator overloading support */
	@SuppressWarnings("rawtypes")
	Object asType(Class clazz) {
		if (clazz == new Boolean(true).getClass()) {
			return this.booleanValue();
		}
		return this;
	}

	public BBoolean or(BBoolean other) {
		return new BBoolean(this.booleanValue() || other.booleanValue());
	}

	public BBoolean or(Boolean other) {
		return new BBoolean(this.booleanValue() || other);
	}

	public BBoolean xor(BBoolean other) {
		return new BBoolean(this.booleanValue() ^ other.booleanValue());
	}

	public BBoolean xor(Boolean other) {
		return new BBoolean(this.booleanValue() ^ other);
	}

	public BBoolean and(BBoolean other) {
		return new BBoolean(this.booleanValue() && other.booleanValue());
	}

	public BBoolean and(Boolean other) {
		return new BBoolean(this.booleanValue() && other);
	}
}
