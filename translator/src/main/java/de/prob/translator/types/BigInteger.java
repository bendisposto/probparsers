package de.prob.translator.types;

public class BigInteger extends Number {

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof java.lang.Number) {
			return this.compareTo((java.lang.Number) obj) == 0;
		}
		// assert getClass() != obj.getClass()
		return false;
	}

	private static final long serialVersionUID = -6484548796859331267L;
	private java.math.BigInteger value;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	BigInteger(java.math.BigInteger value) {
		this.value = value;
	}

	public int compareTo(Number o) {
		return this.value.compareTo(o.asBigInteger());
	}

	public int compareTo(java.lang.Number o) {
		java.math.BigInteger oi;
		if (o == null) {
			throw new NullPointerException();
		}
		if (getClass() != o.getClass()) {
			oi = new java.math.BigInteger(java.lang.String.valueOf(o.longValue()));
		} else {
			BigInteger oo = (BigInteger) o;
			oi = oo.value;
		}
		return this.value.compareTo(oi);
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
	public Number plus(Number o) {
		return new BigInteger(this.value.add(o.asBigInteger()));
	}

	@Override
	public java.math.BigInteger asBigInteger() {
		return this.value;
	}

	public java.lang.String toString() {
		return this.value.toString();
	}

	@Override
	public Number minus(Number o) {
		return new BigInteger(this.value.subtract(o.asBigInteger()));
	}

	@Override
	public Number multiply(Number o) {
		return new BigInteger(this.value.multiply(o.asBigInteger()));
	}

	@Override
	public Number power(Number o) {
		return new BigInteger(this.value.pow(o.intValue()));
	}

	@Override
	public Number div(Number o) {
		return new BigInteger(this.value.divide(o.asBigInteger()));
	}

	@Override
	public Number mod(Number o) {
		return new BigInteger(this.value.mod(o.asBigInteger()));
	}

	@Override
	public Number or(Number o) {
		return new BigInteger(this.value.or(o.asBigInteger()));
	}

	@Override
	public Number and(Number o) {
		return new BigInteger(this.value.and(o.asBigInteger()));
	}

	@Override
	public Number xor(Number o) {
		return new BigInteger(this.value.xor(o.asBigInteger()));
	}

	@Override
	public Number next() {
		return new BigInteger(this.value.add(new java.math.BigInteger("1")));
	}

	@Override
	public Number previous() {
		return new BigInteger(this.value.subtract(new java.math.BigInteger("1")));
	}

	@Override
	public Number leftShift(Number o) {
		return new BigInteger(this.value.shiftLeft(o.intValue()));
	}

	@Override
	public Number rightShift(Number o) {
		return new BigInteger(this.value.shiftRight(o.intValue()));
	}

	@Override
	public boolean isCase(Number o) {
		return this.equals(o);
	}

	@Override
	public Number negative() {
		return new BigInteger(this.value.negate());
	}

	@Override
	public Number positive() {
		return this;
	}

}
