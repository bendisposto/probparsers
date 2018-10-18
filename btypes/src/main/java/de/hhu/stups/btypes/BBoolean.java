package de.hhu.stups.btypes;

import java.util.Objects;

public class BBoolean implements BObject {

	public static final BSet BOOL = new BSet(new BBoolean(true), new BBoolean(false));

	private final boolean value;

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
		return b.compareTo(value);
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
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof BBoolean)) {
			return false;
		}
		if(((BBoolean) obj).booleanValue() != value) {
			return false;
		}
		return true;
	}

	public BBoolean(boolean value) {
		this.value = value;
	}

	public BBoolean(String s) {
		this.value = Boolean.parseBoolean(s);
	}

	/* groovy operator overloading support */
	@SuppressWarnings("rawtypes")
	Object asType(Class clazz) {
		if (clazz == new BBoolean(true).getClass()) {
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

	public BBoolean not() {
		return new BBoolean(!this.booleanValue());
	}

	public BBoolean implies(BBoolean other) {
		return this.not().or(other);
	}

	public BBoolean implies(Boolean other) {
		return new BBoolean(this.not().booleanValue() || other);
	}

	public BBoolean equivalent(BBoolean other) {
		return this.implies(other).and(other.implies(this));
	}

	public BBoolean equivalent(Boolean other) {
		return new BBoolean(this.booleanValue() == other);
	}

	public BBoolean equal(BBoolean other) {
		return new BBoolean(this.value == other.value);
	}

	public BBoolean unequal(BBoolean other) {
		return new BBoolean(this.value != other.value);
	}

}
