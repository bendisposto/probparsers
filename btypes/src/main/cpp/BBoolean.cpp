#include <iostream>
#include <string>
#include "BObject.cpp"

using namespace std;

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


    	static string toString(bool b) {
    		return b ? "true" : "false";
    	}

	    bool booleanValue() {
		    return value;
	    }

        BBoolean operator or(BBoolean other) {
            return new BBoolean(booleanValue() || other.booleanValue());
        }

        BBoolean operator or(bool other) {
            return new BBoolean(booleanValue() || other);
        }

        BBoolean operator xor(BBoolean other) {
            return new BBoolean(booleanValue() ^ other.booleanValue());
        }

        BBoolean operator xor(bool other) {
            return new BBoolean(booleanValue() ^ other);
        }

        BBoolean operator and(BBoolean other) {
            return new BBoolean(booleanValue() && other.booleanValue());
        }

        BBoolean operator and(bool other) {
            return new BBoolean(booleanValue() && other);
        }

        BBoolean operator not() {
            return new BBoolean(!booleanValue());
        }

        BBoolean implies(BBoolean other) {
            return new BBoolean(!booleanValue() || other.booleanValue());
        }

        BBoolean implies(bool other) {
            return new BBoolean(!booleanValue() || other);
        }

        BBoolean equivalent(bool other) {
            return new BBoolean(booleanValue() == other);
        }

        BBoolean equivalent(BBoolean other) {
            return new BBoolean(booleanValue() == other.booleanValue());
        }

        BBoolean equal(BBoolean other) {
            return new BBoolean(booleanValue() == other.booleanValue());
        }

        BBoolean unequal(BBoolean other) {
            return new BBoolean(booleanValue() != other.booleanValue());
        }

};
