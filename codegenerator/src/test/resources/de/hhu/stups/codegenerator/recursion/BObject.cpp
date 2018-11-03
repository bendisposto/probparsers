#include <typeinfo>
#ifndef BOBJECT_H
#define BOBJECT_H

class BObject {

    public:

        friend bool operator !=(const BObject& p1, const BObject& p2);

        friend bool operator ==(const BObject& p1, const BObject& p2);

        virtual int hashCode() {
            return 0;
        };
};
#endif