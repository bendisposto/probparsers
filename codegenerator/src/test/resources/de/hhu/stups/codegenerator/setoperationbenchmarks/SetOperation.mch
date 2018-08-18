MACHINE SetOperation

VARIABLES  counter, set

INVARIANT  counter : 0..500000 & set : POW(INTEGER)

INITIALISATION counter := 0; set := {1,2,3}

OPERATIONS

	simulate = WHILE counter < 500000 DO
          set := set \/ {1};
	      counter := counter + 1
		  INVARIANT 1 = 1
		  VARIANT 500000 - counter
	      END

END