MACHINE
    AbstractMachine3

SETS cars = {red,yellow,redyellow,green}; peds = {green, red}

VARIABLES xx, yy

INVARIANT xx : INT & yy : POW(INTEGER)
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(pp) =
    PRE pp : 1..10 THEN xx := xx + pp; out := xx END

END
