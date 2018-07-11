MACHINE
    Equivalence

VARIABLES xx, yy, switch

INVARIANT xx : INT & yy : POW(INTEGER) & switch : BOOL
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}; switch := TRUE

OPERATIONS
out <-- Inc(pp) =
    IF xx < 10 <=> xx < 5 THEN
        xx := xx + pp; out := xx
    ELSIF xx < 2 THEN
        xx := xx - pp; out := xx + 1
    ELSIF xx > 3 THEN
        xx := 1
    ELSE
        xx := 2
    END

END
