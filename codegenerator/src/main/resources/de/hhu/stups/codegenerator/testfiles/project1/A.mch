MACHINE
    A

INCLUDES C
VARIABLES aa, bb, C
   
INVARIANT aa : INT & bb : INT & C : INT
INITIALISATION aa := 0; bb := 2; C := 1

OPERATIONS
  IncAB(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp) END;
  incab(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp) END

END
