MACHINE
    A

INCLUDES C
VARIABLES aa
   
INVARIANT aa : INT
INITIALISATION aa := 0

OPERATIONS
  IncAB(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa := IncC(pp) END
END
