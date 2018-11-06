#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"
#include "Lift.cpp"

#ifndef LiftExec_H
#define LiftExec_H

using namespace std;

class LiftExec {



    private:

        Lift Lift;


        BInteger counter;

        bool initialized = false;

    public:

        LiftExec(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            Lift.initialize();
            counter = static_cast<BInteger >((BInteger(0)));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(3000)))).booleanValue()) {
                BInteger i;
                i = static_cast<BInteger >((BInteger(0)));
                while((i.less((BInteger(100)))).booleanValue()) {
                    this->Lift.inc();
                    i = static_cast<BInteger >(i.plus((BInteger(1))));
                }
                BInteger _i;
                _i = static_cast<BInteger >((BInteger(0)));
                while((_i.less((BInteger(100)))).booleanValue()) {
                    this->Lift.dec();
                    _i = static_cast<BInteger >(_i.plus((BInteger(1))));
                }
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    LiftExec exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

