MACHINE
    A

INCLUDES switch

SETS switch = {red,yellow,redyellow,green,switch,SWITCH}

VARIABLES aa, switch

INVARIANT aa:INT & switch : INT
INITIALISATION aa := 1; switch := 0

OPERATIONS
    switch(switch) =  PRE switch : 1..10 THEN aa := aa + switch; aa <-- IncC(switch) END
END