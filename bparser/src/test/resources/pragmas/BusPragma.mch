
MACHINE           Bus

VARIABLES         /*@ unit "m" */tickets, passengers

INVARIANT          !x.(x : NAT => x:INT) &
                   f2 = /* symbolic */ %x.(x:NATURAL1 & x mod 2 = 0|x/2) &
                  /*@ label only_typing */ tickets : NAT &  passengers : NAT & /*@ label important */ tickets <= passengers & op = foo(5)

INITIALISATION    tickets, passengers := 0,0



OPERATIONS

buy(mm) =
  PRE mm : NAT &  (tickets + mm) <= passengers
  THEN tickets   := tickets + mm
  END;

board(nn) =
  PRE nn : NAT
  THEN passengers := passengers  + nn
  END;

dble =
  BEGIN
   tickets, passengers  := 2*tickets, 2*passengers
  END;
  
 r <-- foo(x) = BEGIN r := 6 END
  

END
