MACHINE IncreasingSet

VARIABLES  counter, set

INVARIANT  counter : 0..25000 & set : POW(INTEGER)

INITIALISATION counter := 0; set := {1,2,3}

OPERATIONS

	simulate = WHILE counter < 25000 DO
	      set := set \/ {counter};
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 25000 - counter
	      END

END