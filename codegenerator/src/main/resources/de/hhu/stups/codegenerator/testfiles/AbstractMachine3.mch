MACHINE
    AbstractMachine3

SETS cars = {red, redyellow, yellow, green}; peds = {red, green}

VARIABLES xx, yy

INVARIANT xx : INT && yy <: peds
INITIALISATION xx := 1 || yy := cars /\ peds

OPERATIONS
out <-- Inc(pp) =
    PRE pp : 1..10 THEN xx := xx + pp || out := xx END

END
