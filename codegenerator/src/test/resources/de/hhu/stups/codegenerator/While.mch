MACHINE
    While

VARIABLES xx, yy

INVARIANT xx : INT & yy : POW(INTEGER)
INITIALISATION xx := 1; yy := {1,2,3} \/ {2}

OPERATIONS
out <-- Inc(pp) =
  SELECT pp : 1..2
  THEN
    WHILE xx = 1 & xx < 10 DO
      xx := xx + pp; out := xx
    INVARIANT 1=1
    VARIANT 10 - xx
    END
  END
END
