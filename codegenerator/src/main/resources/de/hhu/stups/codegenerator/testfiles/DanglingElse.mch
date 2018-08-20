MACHINE
    DanglingElse

VARIABLES xx, yy, switch

INVARIANT xx : INT & yy : POW(INTEGER) & switch : BOOL
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}; switch := TRUE

OPERATIONS
out <-- Inc =
    IF xx = 1 THEN
        IF xx = 1 THEN
            xx := 42
        END
    ELSE
        xx := 42
    END;
    out := 3

END
