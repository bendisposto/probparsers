#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef RangeBig_H
#define RangeBig_H

using namespace std;

class RangeBig {



    private:



        BInteger counter;
        BSet<BInteger > set;

        bool initialized = false;

    public:

        RangeBig(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            counter = static_cast<BInteger >((BInteger(0)));
            set = static_cast<BSet<BInteger > >((BSet<BInteger >()));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(1000)))).booleanValue()) {
                set = static_cast<BSet<BInteger > >((BSet<BInteger>::range((BInteger(1)),(BInteger(25000)))));
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
            printf("%d\n",set.size());
        }

};

int main() {
    clock_t start,finish;
    double time;
    RangeBig exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

