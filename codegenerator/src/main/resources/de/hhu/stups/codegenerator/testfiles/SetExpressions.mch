MACHINE SetExpressions

VARIABLES a,b,c

INVARIANT a : POW(INT) & b : INT & c : POW(POW(INT))

INITIALISATION
    a := {1,2,3,4};
    b := 0;
    c := {}


OPERATIONS
    simulate =
        b := min(a);
        b := max(a);
        c := POW(a)

END