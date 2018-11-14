#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"

#ifndef SetUnionSmall2_H
#define SetUnionSmall2_H

using namespace std;

class SetUnionSmall2 {



    private:



        BInteger counter;
        BSet<BInteger > set1;
        BSet<BInteger > set2;

        bool initialized = false;

    public:

        SetUnionSmall2(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            counter = static_cast<BInteger >((BInteger(0)));
            set1 = static_cast<BSet<BInteger > >((BSet<BInteger >((BInteger(1)))));
            set2 = static_cast<BSet<BInteger > >((BSet<BInteger >((BInteger(2)))));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(5000000)))).booleanValue()) {
                set1 = static_cast<BSet<BInteger > >(set1._union(set2));
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    SetUnionSmall2 exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

