MACHINE
    AbstractMachine4

SETS cars = {red,yellow,redyellow,green}; peds = {green, red}

VARIABLES xx, yy

INVARIANT xx : INT & yy : POW(INTEGER)
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(pp) =
    IF xx = 1 THEN
        xx := xx + pp; out := xx
    ELSIF xx = 2 THEN
        xx := xx - pp; out := xx + 1
    ELSIF xx = 3 THEN
        xx := 1
    ELSE
        xx := 2
    END

END
