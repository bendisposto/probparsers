MACHINE PlaceOnRoad
SETS
  PLACE = {Oxford, Newbury, Swindon, Bicester}; /* */
  ROAD  = {M4, A34, A40} /* , w1, w2, i1, i2, f1, f2} */
  
VARIABLES
  o1
  
DEFINITIONS

  PlaceOnRoad == PLACE * POW(ROAD) * POW(PLACE)
  
  
INVARIANT

  o1: PlaceOnRoad
  
INITIALISATION

  o1 := Oxford |-> {A34,A40} |-> {}
  

OPERATIONS

 cc <-- get_o1_place = ANY res,xx,pp WHERE 
                    res: PLACE & xx: POW(ROAD) & pp: POW(PLACE) &
                    o1 = res |-> xx |-> pp
                  THEN
                    cc := res
                  END;
                  
                  
  set_o1_place(newp) = PRE newp: PLACE THEN
                  ANY oldp,xx,pp WHERE 
                    oldp: PLACE & xx: POW(ROAD) & pp: POW(PLACE) &
                    o1 = oldp |-> xx |-> pp
                  THEN
                    o1 := newp |-> xx |-> pp
                  END
    END
 
END