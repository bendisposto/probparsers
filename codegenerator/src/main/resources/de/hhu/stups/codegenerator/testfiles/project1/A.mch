MACHINE
    A

INCLUDES switch

SETS switch = {red,yellow,redyellow,green,switch,SWITCH}

VARIABLES aa

INVARIANT aa:INT
INITIALISATION aa := 1

OPERATIONS
    switch(switch) =  PRE switch : 1..10 THEN aa := aa + switch; aa <-- IncC(switch) END
END