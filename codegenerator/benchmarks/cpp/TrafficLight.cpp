#include <iostream>
#include <string>
#include "BUtils.cpp"
#include "BSet.cpp"
#include "BObject.cpp"
#include "BBoolean.cpp"

#ifndef TrafficLight_H
#define TrafficLight_H

using namespace std;

class TrafficLight {

    class colors : public BObject {
        public:

            enum colors_type {
                red, 
                redyellow, 
                yellow, 
                green
            };

            colors_type value;

            colors(){}

            colors(colors_type type) {
                this->value = type;
            }

            BBoolean equal(const colors& o) {
                return value == o.value;
            }

            BBoolean unequal(const colors& o) {
                return value != o.value;
            }

            friend bool operator ==(const colors& p1, const colors& p2) {
                return p1.value == p2.value;
            }

            friend bool operator !=(const colors& p1, const colors& p2) {
                return p1.value != p2.value;
            }

            int hashCode() const {
                return static_cast<int>(value);
            }
    };


    private:


        #define _colors (BSet<colors >((colors(colors::red)), (colors(colors::redyellow)), (colors(colors::yellow)), (colors(colors::green))))

        colors tl_cars;
        colors tl_peds;

        bool initialized = false;

    public:

        TrafficLight(){}

        void initialize() {
            if(initialized) {
                throw runtime_error("Machine is already initialized");
            }
            tl_cars = static_cast<colors >((colors(colors::red)));
            tl_peds = static_cast<colors >((colors(colors::red)));
            initialized = true;
        }

        void cars_ry() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_cars.equal((colors(colors::red)))._and(tl_peds.equal((colors(colors::red))))).booleanValue()) {
                tl_cars = static_cast<colors >((colors(colors::redyellow)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void cars_y() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_cars.equal((colors(colors::green)))._and(tl_peds.equal((colors(colors::red))))).booleanValue()) {
                tl_cars = static_cast<colors >((colors(colors::yellow)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void cars_g() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_cars.equal((colors(colors::redyellow)))._and(tl_peds.equal((colors(colors::red))))).booleanValue()) {
                tl_cars = static_cast<colors >((colors(colors::green)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void cars_r() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_cars.equal((colors(colors::yellow)))._and(tl_peds.equal((colors(colors::red))))).booleanValue()) {
                tl_cars = static_cast<colors >((colors(colors::red)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void peds_r() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_peds.equal((colors(colors::green)))._and(tl_cars.equal((colors(colors::red))))).booleanValue()) {
                tl_peds = static_cast<colors >((colors(colors::red)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

        void peds_g() {
            if(!initialized) {
                throw runtime_error("Machine was not initialized");
            }
            if((tl_peds.equal((colors(colors::red)))._and(tl_cars.equal((colors(colors::red))))).booleanValue()) {
                tl_peds = static_cast<colors >((colors(colors::green)));
            } else {
                throw runtime_error("Invocation of the operation is not possible");
            }
        }

};
#endif

