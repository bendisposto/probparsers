package de.hhu.stups.btypes;

/**
 * Created by fabian on 15.10.18.
 */
public class BInteger extends BNumber {


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
    private int value;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    public BInteger(int value) {
        this.value = value;
    }

    public int compareTo(BNumber o) {
        BInteger other = (BInteger) o;
        return this.value - other.value;
    }

    public BBoolean lessEqual(BNumber o) {
        return new BBoolean(compareTo(o) <= 0);
    }


    public BBoolean greaterEqual(BNumber o) {
        return new BBoolean(compareTo(o) >= 0);
    }

    @Override
    public java.math.BigInteger asBigInteger() {
        return new java.math.BigInteger(String.valueOf(value));
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
        return this.value;
    }

    @Override
    public long longValue() {
        return (long) this.value;
    }

    @Override
    public float floatValue() {
        return (float) this.value;
    }

    @Override
    public double doubleValue() {
        return (double) this.value;
    }

    @Override
    public BNumber plus(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value + other.value);
    }

    public java.lang.String toString() {
        return String.valueOf(value);
    }

    @Override
    public BNumber minus(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value - other.value);
    }

    @Override
    public BNumber multiply(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value * other.value);
    }

    @Override
    public BNumber power(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value ^ other.value);
    }

    @Override
    public BNumber divide(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value / other.value);
    }

    @Override
    public BNumber modulo(BNumber o) {
        BInteger other = (BInteger) o;
        return new BInteger(this.value % other.value);
    }

    @Override
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
    }

    @Override
    public BNumber next() {
        return new BInteger(this.value + 1);
    }

    @Override
    public BNumber previous() {
        return new BInteger(this.value - 1);
    }

    @Override
    public BNumber leftShift(BNumber o) {
        return new BInteger(this.value << 1);
    }

    @Override
    public BNumber rightShift(BNumber o) {
        return new BInteger(this.value >> 1);
    }

    @Override
    public boolean isCase(BNumber o) {
        return this.equals(o);
    }

    @Override
    public BNumber negative() {
        return new BInteger(-this.value);
    }

    @Override
    public BNumber positive() {
        return this;
    }

    public int getValue() {
        return value;
    }

}
