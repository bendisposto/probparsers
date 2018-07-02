MACHINE Sum1

INCLUDES Sum2

VARIABLES xx
INVARIANT xx : INT

OPERATIONS
out <-- Calc(pp) =
    PRE pp : INT THEN
        IF pp = 0 THEN
            out := 0
        ELSE
            xx <-- Calc1(pp - 1);
            out := pp + xx
        END
    END

END
