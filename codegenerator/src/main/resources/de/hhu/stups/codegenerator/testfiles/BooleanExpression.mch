MACHINE BooleanExpression

VARIABLES  counter, expr

INVARIANT  counter : INT & expr : BOOL

INITIALISATION counter := 0; expr := bool(counter < 100)

OPERATIONS
	simulate = WHILE not(counter >= 5000000) DO
	        expr := bool(counter > 100);
	        counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END