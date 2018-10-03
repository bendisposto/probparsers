MACHINE Or

VARIABLES  counter

INVARIANT  counter : INT

INITIALISATION counter := 0

OPERATIONS
	simulate = WHILE counter < 5000000 or 1=2 DO
	        counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END