MACHINE Sum

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
    END;

out <-- Calc1(pp) =
    PRE pp : INT THEN
        IF pp = 0 THEN
            out := 0
        ELSE
            xx <-- Calc(pp - 1);
            out := pp + xx
        END
    END

END
