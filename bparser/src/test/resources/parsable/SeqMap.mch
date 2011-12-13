MACHINE SeqMap
DEFINITIONS
  SET_PREF_SYMBOLIC == TRUE;
  SET_PREF_EXPAND_CLOSURES_FOR_STATE == FALSE
CONSTANTS map, square
PROPERTIES
   map : ((NATURAL<->NATURAL)*seq(NATURAL))<->seq(NATURAL) &
   map = 
    { ff,ii,zz | ii:seq(NATURAL) & zz: seq(NATURAL) & ff:NATURAL<->NATURAL &
           (ii=<>  => zz=<>) &
           (size(ii)>0 =>  zz =  ff(first(ii)) -> map(ff,tail(ii)) )
    } &
    square: NATURAL<->NATURAL &
    square = {ii,jj | ii:NATURAL & jj:NATURAL & jj=ii*ii}
VARIABLES ss
INVARIANT ss: seq(NATURAL)
INITIALISATION ss  := <>
OPERATIONS
   Add(nn)    = PRE nn:NATURAL  THEN ss := ss <- nn END;
   Square = BEGIN ss := map(square,ss) END
END

