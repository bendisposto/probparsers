package de.hhu.stups.btypes;


import clojure.lang.BigInt;
import clojure.lang.RT;
import clojure.lang.Var;

import java.math.BigInteger;

public class BBigInteger extends BNumber {

	private static final Var PLUS = RT.var("clojure.core", "+");

	private static final Var MINUS = RT.var("clojure.core", "-");

	private static final Var MULTIPLY = RT.var("clojure.core", "*");

	private static final Var DIVIDE = RT.var("clojure.core", "quot");

	private static final Var MODULO = RT.var("clojure.core", "mod");

	private static final Var COMPARE = RT.var("clojure.core", "compare");

	public static final Var INC = RT.var("clojure.core", "inc");

	private static final Var DEC = RT.var("clojure.core", "dec");

	private static final Var POWER = RT.var("clojure.core", "^");

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof BNumber) {
			//TODO: other numbers
			return this.compareTo((BNumber) obj) == 0;
		}
		// assert getClass() != obj.getClass()
		return false;
	}

	private static final long serialVersionUID = -6484548796859331267L;
	private java.lang.Number value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	public BBigInteger(String value) {
		try {
			this.value = Long.parseLong(value);
		} catch (NumberFormatException e) {
			this.value = BigInt.fromBigInteger(new java.math.BigInteger(value));
		}
	}

	public BBigInteger(java.lang.Number value) {
		this.value = value;
	}

	public int compareTo(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return (int) COMPARE.invoke(this.value, other.value);
	}

	public BBoolean lessEqual(BNumber o) {
		return new BBoolean(compareTo(o) <= 0);
	}


	public BBoolean greaterEqual(BNumber o) {
		return new BBoolean(compareTo(o) >= 0);
	}

	@Override
	public BigInteger asBigInteger() {
		return new java.math.BigInteger(value.toString());
	}

	public BBoolean less(BNumber o) {
		return new BBoolean(compareTo(o) < 0);
	}

	public BBoolean greater(BNumber o) {
		return new BBoolean(compareTo(o) > 0);
	}

	public BBoolean equal(BNumber o) {
		return new BBoolean(compareTo(o) == 0);
	}

	public BBoolean unequal(BNumber o) {
		return new BBoolean(compareTo(o) != 0);
	}

	@Override
	public int intValue() {
		return this.value.intValue();
	}

	@Override
	public long longValue() {
		return this.value.longValue();
	}

	@Override
	public float floatValue() {
		return this.value.floatValue();
	}

	@Override
	public double doubleValue() {
		return this.value.doubleValue();
	}

	@Override
	public BNumber plus(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) PLUS.invoke(this.value, other.value));
	}

	public java.lang.String toString() {
		return this.value.toString();
	}

	@Override
	public BNumber minus(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) MINUS.invoke(this.value, other.value));
	}

	@Override
	public BNumber multiply(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) MULTIPLY.invoke(this.value, other.value));
	}

	@Override
	public BNumber power(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) POWER.invoke(this.value, other.value));
	}

	@Override
	public BNumber divide(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) DIVIDE.invoke(this.value, other.value));
	}

	@Override
	public BNumber modulo(BNumber o) {
		BBigInteger other = (BBigInteger) o;
		return new BBigInteger((java.lang.Number) MODULO.invoke(this.value, other.value));
	}

	/*@Override
	public BNumber or(BNumber o) {
		return null;
	}

	@Override
	public BNumber and(BNumber o) {
		return null;
	}

	@Override
	public BNumber xor(BNumber o) {
		return null;
	}*/

	@Override
	public BNumber next() {
		return new BBigInteger((java.lang.Number) INC.invoke(this.value));
	}

	@Override
	public BNumber previous() {
		return new BBigInteger((java.lang.Number) DEC.invoke(this.value));
	}

	@Override
	public BNumber leftShift(BNumber o) {
		return null;
	}

	@Override
	public BNumber rightShift(BNumber o) {
		return null;
	}

	@Override
	public boolean isCase(BNumber o) {
		return this.equals(o);
	}

	@Override
	public BNumber negative() {
		return new BBigInteger((java.lang.Number) MINUS.invoke(this.value));
	}

	@Override
	public BNumber positive() {
		return this;
	}

	public java.lang.Number getValue() {
		return value;
	}
}