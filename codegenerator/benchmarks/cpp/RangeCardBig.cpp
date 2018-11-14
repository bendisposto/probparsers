#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef RangeCardBig_H
#define RangeCardBig_H

using namespace std;

class RangeCardBig {



    private:



        BInteger counter;
        BInteger result;

        bool initialized = false;

    public:

        RangeCardBig(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            counter = static_cast<BInteger >((BInteger(0)));
            result = static_cast<BInteger >((BInteger(0)));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(10000)))).booleanValue()) {
                result = static_cast<BInteger >((BSet<BInteger>::range((BInteger(1)),(BInteger(25000)))).card());
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    RangeCardBig exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

