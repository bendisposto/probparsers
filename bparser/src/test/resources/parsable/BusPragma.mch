/*@ version 1.3.5 */
MACHINE           Bus /*@ */

VARIABLES         /*@ unit m */tickets, passengers

INVARIANT          !x.(x : NAT => x:INT) &
                   f2 = /*@ symbolic */ %x.(x:NATURAL1 & x mod 2 = 0|x/2) &
                  /*@ label only typing */ tickets : NAT &  passengers : NAT & /*@ label important */ tickets <= passengers & op = foo(5)

INITIALISATION    tickets, passengers := 0,0


/*@ ltl-assertion "we can always call inc" "GF [inc]" */

OPERATIONS

buy(mm) =
  PRE mm : NAT & /*@ symbolic */ /*@ conversion m to cm */ (tickets + mm) <= passengers
  THEN tickets /*@ symbolic */  := tickets + mm
  END;

board(nn) =
  PRE nn : NAT
  THEN passengers := passengers /*@ non-negative */ + nn
  END;

dble =
  BEGIN
   tickets, passengers  := 2*tickets, 2*passengers
  END;
  
 r <-- foo(x) = BEGIN r := 6 END
  
/*@ pragma_am_ende */
END
