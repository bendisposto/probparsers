#include <stdio.h>
#include <stdlib.h>

typedef struct BInteger {
	int value;
} BInteger;


BInteger create_integer(int value) {
    BInteger result = {value};
    return result;
}

int intValue(BInteger i) {
	return (int) i.value;
}

long longValue(BInteger i) {
	return (long) i.value;
}


float floatValue(BInteger i) {
	return (float) i.value;
}


double doubleValue(BInteger i) {
	return (double) i.value;
}


BInteger plus(BInteger i1, BInteger i2) {
    return create_integer(i1.value + i2.value);
}

BInteger minus(BInteger i1, BInteger i2) {
    return create_integer(i1.value - i2.value);
}

BInteger multiply(BInteger i1, BInteger i2) {
    return create_integer(i1.value * i2.value);
}


BInteger divide(BInteger i1, BInteger i2) {
    return create_integer(i1.value/i2.value);
}


BInteger modulo(BInteger i1, BInteger i2) {
    return create_integer(i1.value/i2.value);
}


BInteger next(BInteger i) {
    return create_integer(i.value + 1);
}

BInteger previous(BInteger i) {
    return create_integer(i.value - 1);
}

BInteger negative(BInteger i) {
    return create_integer(i.value * -1);
}

BInteger positive(BInteger i) {
    return create_integer(i.value);
}


