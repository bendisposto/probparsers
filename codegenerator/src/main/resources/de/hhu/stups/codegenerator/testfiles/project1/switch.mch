MACHINE
    switch

VARIABLES switch

INVARIANT switch : INT
INITIALISATION switch := 0

OPERATIONS
  switch <-- IncC(pp) =  PRE pp : 1..10 THEN switch := switch + pp END

END
