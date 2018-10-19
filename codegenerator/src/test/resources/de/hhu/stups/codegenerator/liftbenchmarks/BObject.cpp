#include <typeinfo>
#ifndef BOBJECT_H
#define BOBJECT_H

class BObject {

    public:

        /*friend bool operator <(const BObject& p1, const BObject& p2) {
            std::cout << typeid(p1).name() << std::endl;
            return p1.hashCode() < p2.hashCode();
        }*/

        friend bool operator !=(const BObject& p1, const BObject& p2);

        friend bool operator ==(const BObject& p1, const BObject& p2);

        virtual int hashCode() const = 0;

};
#endif