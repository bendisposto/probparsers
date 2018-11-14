MACHINE SetElementOfBig2

VARIABLES  counter

INVARIANT  counter : 0..10000

INITIALISATION counter := 0

OPERATIONS

	simulate = WHILE counter < 10000 & 25000:((1..25000) - {24999}) DO
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 10000 - counter
	      END

END