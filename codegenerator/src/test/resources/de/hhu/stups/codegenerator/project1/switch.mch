MACHINE
    switch

VARIABLES abc

INVARIANT abc : INT
INITIALISATION abc := 0

OPERATIONS
  abc <-- IncC(pp) =  PRE pp : 1..10 THEN abc := abc + pp END

END
