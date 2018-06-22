MACHINE
    While

VARIABLES xx, yy

INVARIANT xx : INT & yy : POW(INTEGER)
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(pp) =
    WHILE xx = 1 & xx < 10 DO
      xx := xx + pp; out := xx
    INVARIANT ii : 1..pp+1
    VARIANT pp-ii
    END
END
