MACHINE
    Choice

SETS cars = {red,yellow,redyellow,green,switch}; peds = {green, red}

VARIABLES xx, yy, switch

INVARIANT xx : INT & yy : INT & switch : BOOL
INITIALISATION xx := 1; yy :: {1,2,3}; switch := TRUE

OPERATIONS
out <-- Inc(pp) =
    CHOICE xx := xx + pp; out := xx
        OR xx := xx - pp; out := xx + 1
        OR xx := 1
        OR xx := 2
    END

END
