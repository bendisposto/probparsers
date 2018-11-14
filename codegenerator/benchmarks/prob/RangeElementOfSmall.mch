MACHINE RangeElementOfSmall

VARIABLES  counter

INVARIANT  counter : 0..5000000

INITIALISATION counter := 0

OPERATIONS

	simulate = WHILE counter < 5000000 & 1:(0..5) DO
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END