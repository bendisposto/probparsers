#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BInteger.cpp"
#include "BBoolean.cpp"
#include "TrafficLight.cpp"

#ifndef TrafficLightExec_H
#define TrafficLightExec_H

using namespace std;

class TrafficLightExec {



    private:

        TrafficLight TrafficLight;


        BInteger counter;

        bool initialized = false;

    public:

        TrafficLightExec(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            TrafficLight.initialize();
            counter = static_cast<BInteger >((BInteger(0)));
            initialized = true;
        }

        void simulate() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            while((counter.less((BInteger(500000)))).booleanValue()) {
                this->TrafficLight.cars_ry();
                this->TrafficLight.cars_g();
                this->TrafficLight.cars_y();
                this->TrafficLight.cars_r();
                this->TrafficLight.peds_g();
                this->TrafficLight.peds_r();
                counter = static_cast<BInteger >(counter.plus((BInteger(1))));
            }
        }

};

int main() {
    clock_t start,finish;
    double time;
    TrafficLightExec exec;
    exec.initialize();
    start = clock();
    exec.simulate();
    finish = clock();
    time = (double(finish)-double(start))/CLOCKS_PER_SEC;
    printf("%f\n", time);
    return 0;
}
#endif

