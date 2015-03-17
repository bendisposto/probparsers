package de.prob.translator.types;

public abstract class Number extends java.lang.Number implements
		Comparable<Number>, BObject {

	private static final long serialVersionUID = 7702079048348822936L;

	// TODO Maybe add a Integer subtype that promotes to BigInter on operations
	public static Number build(java.lang.String s) {
		return new BigInteger(new java.math.BigInteger(s));
	}

	public static Number build(long i) {
		return Number.build("" + i);
	}

	public static Number build(int i) {
		return Number.build("" + i);
	}

	public abstract java.math.BigInteger asBigInteger();

	/* groovy operator overloading */
	public abstract Number plus(Number o);

	public abstract Number minus(Number o);

	public abstract Number multiply(Number o);

	public abstract Number power(Number o);

	public abstract Number div(Number o);

	public abstract Number mod(Number o);

	public abstract Number or(Number o);

	public abstract Number and(Number o);

	public abstract Number xor(Number o);

	public abstract Number next(Number o);

	public abstract Number previous(Number o);

	public abstract Number leftShift(Number o);

	public abstract Number rightShift(Number o);

	public abstract boolean isCase(Number o);

	public abstract Number bitwiseNegate(Number o);

	public abstract Number negative(Number o);

	public abstract Number positive(Number o);
}
