#include <iostream>
#include <string>
#include "BBoolean.cpp"

using namespace std;


class BInteger : public BObject {

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

        int intValue() {
            return value;
        }

        /*int compareTo(BInteger o) {
            return value.compareTo(o.asBigInteger());
        }*/

        int compareTo(BInteger o) {
            return intValue() - o.intValue();
        }

        BBoolean lessEqual(BInteger o) {
            return new BBoolean(compareTo(o) <= 0);
        }


        BBoolean greaterEqual(BInteger o) {
            return new BBoolean(compareTo(o) >= 0);
        }

        BBoolean less(BInteger o) {
            return new BBoolean(compareTo(o) < 0);
        }

        BBoolean greater(BInteger o) {
            return new BBoolean(compareTo(o) > 0);
        }

        BBoolean equal(BInteger o) {
            return new BBoolean(compareTo(o) == 0);
        }

        BBoolean unequal(BInteger o) {
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

        /*long longValue() {
            return value.longValue();
        }

        float floatValue() {
            return value.floatValue();
        }

        double doubleValue() {
            return value.doubleValue();
        }*/

        BInteger plus(BInteger o) {
            return BInteger(value + o.intValue());
        }

        /*java.math.BigInteger asBigInteger() {
            return value;
        }

        java.lang.String toString() {
            return value.toString();
        }*/

        BInteger minus(BInteger o) {
            return BInteger(value - o.intValue());
        }

        BInteger multiply(BInteger o) {
            return BInteger(value * o.intValue());
        }

        BInteger power(BInteger o) {
            return BInteger(value^o.intValue());
        }

        BInteger divide(BInteger o) {
            return BInteger(value/o.intValue());
        }

        BInteger modulo(BInteger o) {
            return BInteger(value % o.intValue());
        }

        /*BInteger or(BInteger o) {
            return new BInteger(value.or(o.value));
        }

        BInteger and(BInteger o) {
            return new BInteger(value.and(o.value));
        }

        BInteger xor(BInteger o) {
            return new BInteger(value.xor(o.value));
        }*/

        BInteger next() {
            return BInteger(value + 1);
        }

        BInteger previous() {
            return BInteger(value - 1);
        }

        /*BInteger leftShift(BInteger o) {
            return new BInteger(value.shiftLeft(o.intValue()));
        }

        BInteger rightShift(BInteger o) {
            return new BInteger(value.shiftRight(o.intValue()));
        }

        boolean isCase(BInteger o) {
            return equals(o);
        }*/

        BInteger negative() {
            return BInteger(-value);
        }

        BInteger positive() {
            return BInteger(value);
        }

        BInteger operator=(BInteger other) {
            this->value = other.value;
            return *this;
        }

        bool operator !=(BInteger o) {
            return compareTo(o) != 0;
        }

        bool operator ==(BInteger o) {
            return compareTo(o) == 0;
        }

};
