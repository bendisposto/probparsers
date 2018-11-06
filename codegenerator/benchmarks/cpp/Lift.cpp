#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BInteger.cpp"

#ifndef Lift_H
#define Lift_H

using namespace std;

class Lift {



    private:



        BInteger floor;

        bool initialized = false;

    public:

        Lift(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            floor = static_cast<BInteger >((BInteger(0)));
            initialized = true;
        }

        void inc() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            floor = static_cast<BInteger >(floor.plus((BInteger(1))));
        }

        void dec() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            floor = static_cast<BInteger >(floor.minus((BInteger(1))));
        }

};
#endif

