MACHINE
    A

INCLUDES C
VARIABLES aa, bb
   
INVARIANT aa : INT & bb : INT
INITIALISATION aa := 0; bb := 2

OPERATIONS
  IncAB(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp); bb <-- IncD(pp) END
  cc <-- IncD(pp) =  PRE pp : 1..10 THEN cc := cc + pp END
END
