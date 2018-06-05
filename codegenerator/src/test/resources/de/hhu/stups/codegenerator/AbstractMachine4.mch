MACHINE
    AbstractMachine4

SETS cars = {red,yellow,redyellow,green}; peds = {green, red}

VARIABLES xx, yy

INVARIANT xx : BOOL & yy : POW(INTEGER)
INITIALISATION xx := TRUE; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(pp) =
    xx := pp; out := xx

END
