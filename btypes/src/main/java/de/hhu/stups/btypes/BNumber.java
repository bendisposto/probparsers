package de.hhu.stups.btypes;

public abstract class BNumber extends java.lang.Number implements Comparable<BNumber>, BObject {

	private static final long serialVersionUID = 7702079048348822936L;

	// TODO Maybe add a Integer subtype that promotes to BigInter on operations
	public static BInteger build(String s) {
		return new BInteger(s);
	}

	public static BNumber build(long i) {
		return BNumber.build(i);
	}

	public abstract java.math.BigInteger asBigInteger();

	public abstract BBoolean less(BNumber o);

	public abstract BBoolean lessEqual(BNumber o);

	public abstract BBoolean greater(BNumber o);

	public abstract BBoolean greaterEqual(BNumber o);

	public abstract BBoolean equal(BNumber o);

	/* groovy operator overloading */
	public abstract BNumber plus(BNumber o);

	public abstract BNumber minus(BNumber o);

	public abstract BNumber multiply(BNumber o);

	public abstract BNumber power(BNumber o);

	public abstract BNumber divide(BNumber o);

	public abstract BNumber modulo(BNumber o);

	public abstract BNumber or(BNumber o);

	public abstract BNumber and(BNumber o);

	public abstract BNumber xor(BNumber o);

	public abstract BNumber next();

	public abstract BNumber previous();

	public abstract BNumber leftShift(BNumber o);

	public abstract BNumber rightShift(BNumber o);

	public abstract boolean isCase(BNumber o);

	public abstract BNumber negative();

	public abstract BNumber positive();
}
