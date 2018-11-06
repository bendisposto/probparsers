#include <stdio.h>
#include <stdlib.h>
#include "pbl/pblSet.c"
#include "BBoolean.c"
#include "BInteger.c"
#include "BObject.c"

#ifndef BSET_H
#define BSET_H

typedef struct BSet {
    struct BObject;
	PblSet* set;
} BSet;


BSet create_bset(BObject elements[]) {
    BSet result = {pblSetNewHashSet()};
    int length = sizeof(BObject)/sizeof(BObject*);
    for(int i = 0; i < length; i++) {
        pblSetAdd(result.set, elements + i);
    }
    return result;
}

int size(BSet set) {
    return pblSetSize(set.set);
}

int isEmpty(BSet set) {
    return pblSetIsEmpty(set.set);
}

int contains(BSet set, BObject o) {
    return pblSetContains(set.set, &o);
}

BInteger card(BSet set) {
    return create_integer(size(set));
}

BBoolean elementOf(BSet set, BObject object) {
    return create_boolean(contains(set, object) > 0);
}

BSet _union(BSet set1, BSet set2) {
    BSet result = {pblSetUnion(set1.set, set2.set)};
    return result;
}

BSet complement(BSet set1, BSet set2) {
    BSet result = {pblSetDifference(set1.set, set2.set)};
    return result;
}

BSet intersection(BSet set1, BSet set2) {
    BSet result = {pblSetIntersection(set1.set, set2.set)};
    return result;
}

BSet range(BInteger a, BInteger b) {
    BSet result = {pblSetNewHashSet()};
    for(BInteger i = a; booleanValue(lessEqual(i,b)); i = next(i)) {
        pblSetAdd(result.set, &i);
    }
    return result;
}

#endif