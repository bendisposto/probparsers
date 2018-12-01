MACHINE SetPredicates

VARIABLES a,b,c

INVARIANT a : POW(INT) & b : POW(INT) & c : BOOL

INITIALISATION
    a := {1,2,3,4};
    b := {2,3};
    c := TRUE


OPERATIONS
    simulate =
        c := bool(1/:a);
        c := bool(a<:b);
        c := bool(a/<:b);
        c := bool(a<<:b);
        c := bool(a/<<:b)

END