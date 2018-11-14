MACHINE RangeUnionSmall2

VARIABLES  counter, set1, set2

INVARIANT  counter : 0..5000000 & set1 : POW(INTEGER) & set2 : POW(INTEGER)

INITIALISATION counter := 0; set1 := 0..5; set2 := 1..2

OPERATIONS

	simulate = WHILE counter < 5000000 DO
	      set1 := set1 \/ set2;
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 5000000 - counter
	      END

END