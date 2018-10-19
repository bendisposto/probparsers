MACHINE BooleanConstant

VARIABLES  counter

INVARIANT  counter : INT

INITIALISATION counter := 0

OPERATIONS
	simulate = WHILE not(counter >= 5000000) & btrue DO
	        counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END