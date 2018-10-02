#include <iostream>
#include <string>
#include "BBoolean.cpp"

using namespace std;

class BNumber : public BObject {

    public:
    	/*static BNumber build(string s) {
    		return new BInteger(new java.math.BigInteger(s));
    	}

    	static BNumber build(long i) {
    		return BNumber.build("" + i);
    	}

    	static BNumber build(int i) {
    		return BNumber.build("" + i);
    	}*/

        //virtual java.math.BigInteger asBigInteger();

        virtual int intValue(){
            return 0;
        }

        virtual BBoolean less(BNumber o);

        virtual BBoolean lessEqual(BNumber o);

        virtual BBoolean greater(BNumber o);

        virtual BBoolean greaterEqual(BNumber o);

        virtual BBoolean equal(BNumber o);

        virtual BBoolean unequal(BNumber o);

        virtual BNumber plus(BNumber o);

        virtual BNumber minus(BNumber o);

        virtual BNumber multiply(BNumber o);

        virtual BNumber power(BNumber o);

        virtual BNumber divide(BNumber o);

        virtual BNumber modulo(BNumber o);

        virtual BNumber next();

        virtual BNumber previous();

        //virtual BNumber leftShift(BNumber o);

        //virtual BNumber rightShift(BNumber o);

        //virtual bool isCase(BNumber o);

        virtual BNumber negative();

        virtual BNumber positive();

};
