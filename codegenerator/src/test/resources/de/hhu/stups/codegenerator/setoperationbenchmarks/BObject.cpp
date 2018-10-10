#ifndef BOBJECT_H
#define BOBJECT_H

class BObject {

        friend bool operator <(const BObject& p1, const BObject& p2);

        friend bool operator !=(const BObject& p1, const BObject& p2);

        friend bool operator ==(const BObject& p1, const BObject& p2);

};
#endif