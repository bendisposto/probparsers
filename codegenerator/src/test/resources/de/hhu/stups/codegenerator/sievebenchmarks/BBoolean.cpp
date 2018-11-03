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

    	BBoolean(){};

    	BBoolean(const BBoolean& val) {
    	    value = val.value;
    	}

    	static string toString(bool b) {
    		return b ? "true" : "false";
    	}

	    bool booleanValue() const {
		    return value;
	    }

        BBoolean _or(const BBoolean& other) {
            return value || other.value;
        }

        BBoolean _or(bool other) {
            return value || other;
        }

        BBoolean _xor(const BBoolean& other) {
            return value ^ other.value;
        }

        BBoolean _xor(bool other) {
            return value ^ other;
        }

        BBoolean _and(const BBoolean& other) {
            return value && other.value;
        }

        BBoolean _and(bool other) {
            return value && other;
        }

        BBoolean _not() {
            return !value;
        }

        BBoolean implies(const BBoolean& other) {
            return !value || other.value;
        }

        BBoolean implies(bool other) {
            return !value || other;
        }

        BBoolean equivalent(bool other) {
            return value == other;
        }

        BBoolean equivalent(const BBoolean& other) {
            return value == other.value;
        }

        BBoolean equal(const BBoolean& other) {
            return value == other.value;
        }

        BBoolean unequal(const BBoolean& other) {
            return value != other.value;
        }

        friend bool operator !=(const BBoolean& o1, const BBoolean& o2) {
            return o1.value != o2.value;
        }

        friend bool operator ==(const BBoolean& o1, const BBoolean& o2) {
            return o1.value == o2.value;
        }

        bool operator <(const BBoolean& other) {
            return value != other.value;
        }

        void operator =(const BBoolean& other) {
            value = other.value;
        }

        int hashCode() const {
            return value == true ? 1 : 0;
        }
};
#endif