#include <stdio.h>
#include <stdlib.h>

typedef struct BAtom {
	char* value;
} BAtom;


BAtom create_atom(char* value) {
    BAtom result = {value};
    return result;
}


int main() {
    BAtom result = create_atom("Fabian");
    printf("%s\n", result.value);
    return 0;
}


