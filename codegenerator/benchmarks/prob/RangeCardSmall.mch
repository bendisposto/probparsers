MACHINE RangeCardSmall

VARIABLES  counter, set, result

INVARIANT  counter : 0..5000000 & result : INTEGER

INITIALISATION counter := 0; result := 0

OPERATIONS

	simulate = WHILE counter < 5000000 DO
	      result := card((0..5));
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END