#include <iostream>
#include <string>
#include "BNumber.cpp"

using namespace std;


class BInteger : public BNumber {

    /*
	@Override
	public boolean equals(Object obj) {
		if (== obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof java.lang.Number) {
			return compareTo((java.lang.Number) obj) == 0;
		}
		// assert getClass() != obj.getClass()
		return false;
	}*/

	private:
	    int value;

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}*/

    public:
        /*BInteger(string val) {
            value = new java.math.BigInteger(val);
        }*/

        BInteger(int val) {
            value = val;
        }

        /*int compareTo(BNumber o) {
            return value.compareTo(o.asBigInteger());
        }*/

        int compareTo(BNumber o) {
            return intValue() - o.intValue();
        }

        BBoolean lessEqual(BNumber o) {
            return new BBoolean(compareTo(o) <= 0);
        }


        BBoolean greaterEqual(BNumber o) {
            return new BBoolean(compareTo(o) >= 0);
        }

        BBoolean less(BNumber o) {
            return new BBoolean(compareTo(o) < 0);
        }

        BBoolean greater(BNumber o) {
            return new BBoolean(compareTo(o) > 0);
        }

        BBoolean equal(BNumber o) {
            return new BBoolean(compareTo(o) == 0);
        }

        BBoolean unequal(BNumber o) {
            return new BBoolean(compareTo(o) != 0);
        }

        /*int compareTo(java.lang.Number o) {
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
            return value.compareTo(oi);
        }*/

        int intValue() {
            return value;
        }

        /*long longValue() {
            return value.longValue();
        }

        float floatValue() {
            return value.floatValue();
        }

        double doubleValue() {
            return value.doubleValue();
        }*/

        BNumber plus(BNumber o) {
            return BInteger(value + o.intValue());
        }

        /*java.math.BigInteger asBigInteger() {
            return value;
        }

        java.lang.String toString() {
            return value.toString();
        }*/

        BNumber minus(BNumber o) {
            return BInteger(value - o.intValue());
        }

        BNumber multiply(BNumber o) {
            return BInteger(value * o.intValue());
        }

        BNumber power(BNumber o) {
            return BInteger(value^o.intValue());
        }

        BNumber divide(BNumber o) {
            return BInteger(value/o.intValue());
        }

        BNumber modulo(BNumber o) {
            return BInteger(value % o.intValue());
        }

        /*BNumber or(BNumber o) {
            return new BInteger(value.or(o.value));
        }

        BNumber and(BNumber o) {
            return new BInteger(value.and(o.value));
        }

        BNumber xor(BNumber o) {
            return new BInteger(value.xor(o.value));
        }*/

        BNumber next() {
            return BInteger(value + 1);
        }

        BNumber previous() {
            return BInteger(value - 1);
        }

        /*BNumber leftShift(BNumber o) {
            return new BInteger(value.shiftLeft(o.intValue()));
        }

        BNumber rightShift(BNumber o) {
            return new BInteger(value.shiftRight(o.intValue()));
        }

        boolean isCase(BNumber o) {
            return equals(o);
        }*/

        BNumber negative() {
            return BInteger(-value);
        }

        BNumber positive() {
            return BInteger(value);
        }

        BInteger operator=(BInteger other) {
            return BInteger(other.value);
        }

        BInteger operator=(BNumber other) {
            return BInteger(other.intValue());
        }

};
