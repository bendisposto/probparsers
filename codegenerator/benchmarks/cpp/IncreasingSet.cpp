#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef IncreasingSet_H
#define IncreasingSet_H

using namespace std;

class IncreasingSet {



    private:



        BInteger counter;
        BSet<BInteger > set;

        bool initialized = false;

    public:

        IncreasingSet(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            counter = static_cast<BInteger >((BInteger(0)));
            set = static_cast<BSet<BInteger > >((BSet<BInteger >((vector<BInteger>){(BInteger(1)), (BInteger(2)), (BInteger(3))})));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(25000)))).booleanValue()) {
                set = static_cast<BSet<BInteger > >(set._union((BSet<BInteger >((vector<BInteger>){counter}))));
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    IncreasingSet exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

