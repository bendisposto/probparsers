#include <iostream>
#include <string>
#include "BBoolean.cpp"

using namespace std;

#ifndef BCOUPLE_H
#define BCOUPLE_H

class BCouple : public BObject {

	private:
	    BObject lhs;
	    BObject rhs;

	public:

        BCouple(BObject* left, BObject* right) {
            if (left == NULL || right == NULL) {
                throw runtime_error("Illegal argument");
            }
            lhs = *left;
            rhs = *right;
        }

        BCouple(){}

        BObject getFirst() {
            return lhs;
        }

        BObject getSecond() {
            return rhs;
        }

        BBoolean operator ==(BCouple o) {
            return new BBoolean(lhs == o.lhs && rhs == o.rhs);
        }

        BBoolean operator !=(BCouple o) {
            return new BBoolean(lhs != o.lhs || rhs != o.rhs);
        }

	/*public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

        BCouple bObjects = (BCouple) o;
		// elements is never null
		return bObjects.getFirst().equals(this.first) && bObjects.getSecond().equals(this.second);
	}

	public int hashCode() {
		return Objects.hash(first, second);
	}

	@Override
	public java.lang.String toString() {
		return "(" + this.getFirst() + " |-> " + this.getSecond() + ')';
	}*/

};
#endif