MACHINE SetIntersectionBig3

VARIABLES  counter, set1, set2, set3

INVARIANT  counter : 0..10000 & set1 : POW(INTEGER) & set2 : POW(INTEGER) & set3 : POW(INTEGER)

INITIALISATION counter := 0; set1 := (1..35000) - {24999}; set2 := 1..2000 \/ {-1}; set3 := 1..45000

OPERATIONS
	simulate = WHILE counter < 10000 DO
	      set3 := set1 /\ set2;
	      set3 := set3 /\ set1;
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 10000 - counter
	      END

END