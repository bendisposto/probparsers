MACHINE
    BooleanPredicate

VARIABLES xx, yy, aa, switch

INVARIANT xx : INT & yy: POW(INT * INT) & aa : INT & switch : BOOL
INITIALISATION xx := 1; yy := {1 |-> 2, 2 |-> 3, 3|-> 4}; switch := TRUE; aa := yy(1)

OPERATIONS
out <-- Inc(pp) =
    IF btrue THEN
        xx := xx + pp; out := xx
    END

END
