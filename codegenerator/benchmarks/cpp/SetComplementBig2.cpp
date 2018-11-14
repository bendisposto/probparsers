#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef SetComplementBig2_H
#define SetComplementBig2_H

using namespace std;

class SetComplementBig2 {



    private:



        BInteger counter;
        BSet<BInteger > set1;
        BSet<BInteger > set2;

        bool initialized = false;

    public:

        SetComplementBig2(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            counter = static_cast<BInteger >((BInteger(0)));
            set1 = static_cast<BSet<BInteger > >((BSet<BInteger>::range((BInteger(1)),(BInteger(25000)))).complement((BSet<BInteger >((BInteger(24999))))));
            set2 = static_cast<BSet<BInteger > >((BSet<BInteger>::range((BInteger(1)),(BInteger(3000)))));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(10000)))).booleanValue()) {
                set1 = static_cast<BSet<BInteger > >(set1.complement(set2));
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    SetComplementBig2 exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

