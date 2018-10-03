MACHINE Implies

VARIABLES  counter

INVARIANT  counter : INT

INITIALISATION counter := 0

OPERATIONS
	simulate = WHILE 1=1 => counter < 5000000 DO
	        counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END