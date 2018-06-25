MACHINE
    C

VARIABLES cc

INVARIANT cc : INT
INITIALISATION cc := 0

OPERATIONS
  res <-- IncC(pp) =  PRE pp : 1..10 THEN res := cc + pp END

END
