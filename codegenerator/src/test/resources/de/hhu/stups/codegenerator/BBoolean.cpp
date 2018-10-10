#include <iostream>
#include <string>
#include "BObject.cpp"

using namespace std;


#ifndef BBOOLEAN_H
#define BBOOLEAN_H

class BBoolean : public BObject {
	private:
	    bool value;

    /*
	public static boolean parseBoolean(String s) {
		return Boolean.parseBoolean(s);
	}*/

	/*public int compareTo(bool b) {
		return value.compareTo(b);
	}*/

	/*public static Boolean valueOf(string s) {
		return Boolean.valueOf(s);
	}*/

	/*public static int compare(boolean x, boolean y) {
		return Boolean.compare(x, y);
	}*/

	/*public static boolean getBoolean(String name) {
		return Boolean.getBoolean(name);
	}*/

	/*@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return value.equals(obj);
	}*/

	/*BBoolean(string s) {
		this.value = new Boolean(s);
	}*/

	/* groovy operator overloading support */
	/*@SuppressWarnings("rawtypes")
	Object asType(Class clazz) {
		if (clazz == new Boolean(true).getClass()) {
			return this.booleanValue();
		}
		return this;
	}*/

    public:

    	BBoolean(bool val) {
    		value = val;
    	}

    	BBoolean(){}

    	static string toString(bool b) {
    		return b ? "true" : "false";
    	}

	    bool booleanValue() {
		    return value;
	    }

        BBoolean _or(BBoolean other) {
            return BBoolean(booleanValue() || other.booleanValue());
        }

        BBoolean _or(bool other) {
            return BBoolean(booleanValue() || other);
        }

        BBoolean _xor(BBoolean other) {
            return BBoolean(booleanValue() ^ other.booleanValue());
        }

        BBoolean _xor(bool other) {
            return BBoolean(booleanValue() ^ other);
        }

        BBoolean _and(BBoolean other) {
            return BBoolean(booleanValue() && other.booleanValue());
        }

        BBoolean _and(bool other) {
            return BBoolean(booleanValue() && other);
        }

        BBoolean _not() {
            return BBoolean(!booleanValue());
        }

        BBoolean implies(BBoolean other) {
            return BBoolean(!booleanValue() || other.booleanValue());
        }

        BBoolean implies(bool other) {
            return BBoolean(!booleanValue() || other);
        }

        BBoolean equivalent(bool other) {
            return BBoolean(booleanValue() == other);
        }

        BBoolean equivalent(BBoolean other) {
            return BBoolean(booleanValue() == other.booleanValue());
        }

        BBoolean equal(BBoolean other) {
            return BBoolean(booleanValue() == other.booleanValue());
        }

        BBoolean unequal(BBoolean other) {
            return BBoolean(booleanValue() != other.booleanValue());
        }

        bool operator !=(BBoolean other) {
            return booleanValue() != other.booleanValue();
        }

        bool operator ==(BBoolean other) {
            return booleanValue() == other.booleanValue();
        }

        bool operator <(BBoolean other) {
            return booleanValue() != other.booleanValue();
        }
};
#endif