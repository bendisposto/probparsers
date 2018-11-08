#include <iostream>
#include <string>
#include "BBoolean.cpp"

#ifndef BINTEGER_H
#define BINTEGER_H

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

        int intValue() const {
            return value;
        }

        BInteger(){};

        BInteger(const BInteger& val) {
            value = val.value;
        }

        /*int compareTo(BInteger o) {
            return value.compareTo(o.asBigInteger());
        }*/

        int compareTo(const BInteger& o) const {
            return value - o.value;
        }

        BBoolean lessEqual(const BInteger& o) {
            return compareTo(o) <= 0;
        }


        BBoolean greaterEqual(const BInteger& o) {
            return compareTo(o) >= 0;
        }

        BBoolean less(const BInteger& o) {
            return compareTo(o) < 0;
        }

        BBoolean greater(const BInteger& o) {
            return compareTo(o) > 0;
        }

        BBoolean equal(const BInteger& o) {
            return compareTo(o) == 0;
        }

        BBoolean unequal(const BInteger& o) {
            return compareTo(o) != 0;
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

        BInteger plus(const BInteger& o) {
            return value + o.value;
        }

        /*java.math.BigInteger asBigInteger() {
            return value;
        }

        java.lang.String toString() {
            return value.toString();
        }*/

        BInteger minus(const BInteger& o) {
            return value - o.value;
        }

        BInteger multiply(const BInteger& o) {
            return value * o.value;
        }

        BInteger power(const BInteger& o) {
            return value ^ o.value;
        }

        BInteger divide(const BInteger& o) {
            return value / o.value;
        }

        BInteger modulo(const BInteger& o) {
            return value % o.value;
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
            return value + 1;
        }

        BInteger previous() {
            return value - 1;
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
            return -value;
        }

        BInteger positive() {
            return *this;
        }

        /*BInteger operator=(BInteger other) {
            this->value = other.value;
            return *this;
        }*/

        friend bool operator !=(const BInteger& o1, const BInteger& o2) {
            return o1.value != o2.value;
        }

        friend bool operator ==(const BInteger& o1, const BInteger& o2) {
            return o1.value == o2.value;
        }

        void operator =(const BInteger& other) {
            value = other.value;
        }

        int hashCode() const {
            int prime = 31;
            int result = 1;
            result = prime * result + hash(value);
            return result;
        }

};
#endif