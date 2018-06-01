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

int main() {

    BBoolean a = create_boolean(1);
    BBoolean b = create_boolean(0);

    BBoolean c = or(a,b);
    BBoolean d = and(a,b);
    BBoolean e = xor(a,b);

    printf("%d\n", c.value);
    printf("%d\n", d.value);
    printf("%d\n", e.value);


}


