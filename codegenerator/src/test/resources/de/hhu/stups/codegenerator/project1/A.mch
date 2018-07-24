MACHINE
    A

INCLUDES C

SETS C = {red,yellow,redyellow,green}; peds = {green, red, GREEN}; Peds = {green, red, GREEN}

VARIABLES aa, bb

INVARIANT aa : INT & bb : INT
INITIALISATION aa := 0; bb := 2

OPERATIONS
  IncAB(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp) END;
  incab(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp) END
END
