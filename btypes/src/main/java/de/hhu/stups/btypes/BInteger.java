package de.hhu.stups.btypes;

public class BInteger extends BNumber {

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

    public BInteger(java.math.BigInteger value) {
		this.value = value;
	}

	public int compareTo(BNumber o) {
		return this.value.compareTo(o.asBigInteger());
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


	public int compareTo(java.lang.Number o) {
		java.math.BigInteger oi;
		if (o == null) {
			throw new NullPointerException();
		}
		if (getClass() != o.getClass()) {
			oi = new java.math.BigInteger(java.lang.String.valueOf(o.longValue()));
		} else {
            BInteger oo = (BInteger) o;
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
	public BNumber plus(BNumber o) {
		return new BInteger(this.value.add(o.asBigInteger()));
	}

	@Override
	public java.math.BigInteger asBigInteger() {
		return this.value;
	}

	public java.lang.String toString() {
		return this.value.toString();
	}

	@Override
	public BNumber minus(BNumber o) {
		return new BInteger(this.value.subtract(o.asBigInteger()));
	}

	@Override
	public BNumber multiply(BNumber o) {
		return new BInteger(this.value.multiply(o.asBigInteger()));
	}

	@Override
	public BNumber power(BNumber o) {
		return new BInteger(this.value.pow(o.intValue()));
	}

	@Override
	public BNumber divide(BNumber o) {
		return new BInteger(this.value.divide(o.asBigInteger()));
	}

	@Override
	public BNumber modulo(BNumber o) {
		return new BInteger(this.value.mod(o.asBigInteger()));
	}

	@Override
	public BNumber or(BNumber o) {
		return new BInteger(this.value.or(o.asBigInteger()));
	}

	@Override
	public BNumber and(BNumber o) {
		return new BInteger(this.value.and(o.asBigInteger()));
	}

	@Override
	public BNumber xor(BNumber o) {
		return new BInteger(this.value.xor(o.asBigInteger()));
	}

	@Override
	public BNumber next() {
		return new BInteger(this.value.add(new java.math.BigInteger("1")));
	}

	@Override
	public BNumber previous() {
		return new BInteger(this.value.subtract(new java.math.BigInteger("1")));
	}

	@Override
	public BNumber leftShift(BNumber o) {
		return new BInteger(this.value.shiftLeft(o.intValue()));
	}

	@Override
	public BNumber rightShift(BNumber o) {
		return new BInteger(this.value.shiftRight(o.intValue()));
	}

	@Override
	public boolean isCase(BNumber o) {
		return this.equals(o);
	}

	@Override
	public BNumber negative() {
		return new BInteger(this.value.negate());
	}

	@Override
	public BNumber positive() {
		return this;
	}

}
