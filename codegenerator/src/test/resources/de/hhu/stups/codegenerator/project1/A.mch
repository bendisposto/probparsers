MACHINE
    A

INCLUDES switch

SETS switch = {red,yellow,redyellow,green}; peds = {green, red, GREEN}; Peds = {green, red, GREEN}

VARIABLES aa, bb

INVARIANT aa : INT & bb : INT
INITIALISATION aa := 0; bb := 2

OPERATIONS
  IncAB(pp) =  PRE pp : 1..10 THEN aa := aa + pp ; aa <-- IncC(pp) END;
  switch(switch) =  PRE switch : 1..10 THEN aa := aa + switch ; aa <-- IncC(switch) END
END
