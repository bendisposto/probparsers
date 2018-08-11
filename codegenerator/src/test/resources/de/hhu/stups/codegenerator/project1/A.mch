MACHINE
    A

INCLUDES switch

SETS switch = {red,yellow,redyellow,green,private,SWITCH}

VARIABLES aa

INVARIANT aa:INT
INITIALISATION aa := 1

OPERATIONS
    xx <-- op1(bb, switch) =
        PRE switch : INT & bb : 1..10 THEN
            xx := switch + 1;
            VAR switch IN
	    	    switch := bb + 1
	        END;
            VAR switch IN
	    	    switch := bb + 1
	        END;
	        xx := switch + 1
        END
END