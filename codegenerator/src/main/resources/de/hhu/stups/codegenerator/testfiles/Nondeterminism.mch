MACHINE
    Nondeterminism

SETS cars = {red,yellow,redyellow,green,switch}; peds = {green, red}

VARIABLES xx, yy, switch

INVARIANT xx : INT & yy : INT & switch : BOOL
INITIALISATION xx := 1; yy :: {1,2,3}; switch := TRUE

OPERATIONS
out <-- Inc(pp) =
    IF xx < 10 THEN
        xx := xx + pp; out := xx
    ELSIF xx < 2 THEN
        xx := xx - pp; out := xx + 1
    ELSIF xx > 3 THEN
        xx := 1
    ELSE
        xx := 2
    END

END
