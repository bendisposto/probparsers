#include <stdio.h>
#include <stdlib.h>

typedef struct BBoolean {
	int value;
} BBoolean;

BBoolean create_boolean(int value) {
    BBoolean result = {value};
    return result;
}

int booleanValue(BBoolean b) {
    return b.value;
}


BBoolean or(BBoolean b1, BBoolean b2) {
    return create_boolean(b1.value || b2.value);
}

BBoolean and(BBoolean b1, BBoolean b2) {
    return create_boolean(b1.value && b2.value);
}

BBoolean xor(BBoolean b1, BBoolean b2) {
    return create_boolean(b1.value != b2.value);
}

BBoolean not(BBoolean b1) {
    return create_boolean(!b1.value);
}

BBoolean implies(BBoolean b1, BBoolean b2) {
    return create_boolean(!b1.value || b2.value);
}

BBoolean equivalent(BBoolean b1, BBoolean b2) {
    return and(implies(b1,b2),implies(b2,b1));
}

BBoolean equal(BBoolean i1, BBoolean i2) {
    return create_boolean(i1.value == i2.value);
}

BBoolean unequal(BBoolean i1, BBoolean i2) {
    return create_boolean(i1.value != i2.value);
}

