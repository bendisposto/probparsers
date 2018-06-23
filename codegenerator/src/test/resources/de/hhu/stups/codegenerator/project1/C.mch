MACHINE
    C

VARIABLES cc

INVARIANT cc : INT
INITIALISATION cc := 0

OPERATIONS
  cc <-- IncC(pp) =  PRE pp : 1..10 THEN cc := cc + pp END

END
