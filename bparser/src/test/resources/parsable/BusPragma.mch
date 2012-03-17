/*@ vor_der_maschine */
MACHINE           Bus /*@ dada */

VARIABLES         tickets, passengers

INVARIANT         /*@ label only typing */ tickets : NAT &  passengers : NAT & /*@ label important */ tickets <= passengers 

INITIALISATION    tickets, passengers := 0,0

OPERATIONS

buy(mm) =
  PRE mm : NAT & tickets + mm <= passengers
  THEN tickets := tickets + mm
  END;

board(nn) =
  PRE nn : NAT
  THEN passengers := passengers /*@ non-negative */ + nn
  END;

dble =
  BEGIN
   tickets, passengers /*@ symbolic */ := 2*tickets, 2*passengers
  END
/*@ pragma_am_ende */
END
