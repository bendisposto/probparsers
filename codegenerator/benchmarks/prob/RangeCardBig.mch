MACHINE RangeCardBig

VARIABLES  counter, result

INVARIANT  counter : 0..10000 & result : INTEGER

INITIALISATION counter := 0; result := 0

OPERATIONS

	simulate = WHILE counter < 10000 DO
	      result := card(1..25000);
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 10000 - counter
	      END

END