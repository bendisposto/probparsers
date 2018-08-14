MACHINE
    A

INCLUDES switch

VARIABLES aa

INVARIANT aa:INT
INITIALISATION aa := 5

OPERATIONS
    xx <-- op1(bb, switch) =
        PRE switch : INT & bb : 1..10 THEN
            switch := 1;
            switch <-- IncC(aa);
            VAR switch IN
	    	    switch := bb + 1
	        END;
            VAR switch IN
	    	    switch := bb + 1
	        END;
	        xx := switch + 1
        END
END