MACHINE LocalDeclaration

VARIABLES xx

INVARIANT xx : INT
INITIALISATION xx := 1

OPERATIONS
out <-- Inc(pp) =
    PRE pp : 1..10 THEN pp := 1; xx := xx + pp; out := xx END

END
