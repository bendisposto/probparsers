MACHINE Pattern1Spec
SETS
    SESSION = {s1,s2} ; RECORD; ITEM

 /* DEFINITIONS
    fld1 == (prj1(ITEM,NAT));
    fld2 == prj2(ITEM,NAT) ;
    fld3 == prj1(ITEM,NAT) ;
    fld4 == prj2(ITEM,NAT) */
     
CONSTANTS
    REQUEST, RESPONSE ,
    fld1 ,fld2,fld3,fld4 

PROPERTIES
 /*   REQUEST <: RECORD &
    RESPONSE <: RECORD  &

    fld1: REQUEST-->ITEM &
    fld2: REQUEST-->NAT &
    ! (f1,f2). (f1 : ITEM & f2: NAT => 
    # rr . ( rr: REQUEST & fld1(rr)=f1 & fld2(rr)=f2)) &

    fld3: RESPONSE-->ITEM &
    fld4: RESPONSE-->NAT &
    ! (f3,f4). (f3 : ITEM & f4: NAT => 
    # rs . ( rs: RESPONSE & fld3(rs)=f3 & fld4(rs)=f4))
    */
 
 REQUEST = ITEM*NAT &
 RESPONSE = ITEM*NAT &
 
    fld1: REQUEST-->ITEM &
     fld1 = %rr.( rr:REQUEST | prj1(ITEM,NAT)(rr) )
     &
    fld2: REQUEST-->NAT &
     fld2 = %rr.( rr:REQUEST | prj2(ITEM,NAT)(rr) )
     &
    fld3: REQUEST-->ITEM &
     fld3 = %rr.( rr:REQUEST | prj1(ITEM,NAT)(rr) )
     &
    fld4: REQUEST-->NAT &
     fld4 = %rr.( rr:REQUEST | prj2(ITEM,NAT)(rr) )

      
VARIABLES
    db
     
INVARIANT

    db: ITEM --> NAT


INITIALISATION
    db := ITEM * {5}
    
OPERATIONS
  CreateSession(ss)  =       /* Server Operation */
    PRE ss : SESSION  THEN
      skip
    END;
    
  MakeRequest(ss,rq) =        /* Client Operation */
    PRE ss: SESSION & rq: REQUEST THEN
      skip
    END;

  rs<--ProcessRequest(ss,rq) =       /* Server Operation */
    PRE ss: SESSION & rq: REQUEST  THEN
       SELECT db(fld1(rq))>= fld2(rq) THEN
         rs := rq ||
         db:= db<+ {fld1(rq)|-> db(fld1(rq))-fld2(rq)}
       ELSE 
         ANY rsp WHERE  rsp: RESPONSE & fld3(rsp)= fld1(rq) &
                                    fld4(rsp)= db(fld1(rq)) THEN 
           rs := rsp    ||     
           db:= db<+ {fld1(rq)|-> 0}
         END
       END 
    END;
  
  GetResponse(ss,rs) =         /* Client Operation */
    PRE ss: SESSION & rs: RESPONSE  THEN
      skip
    END     
END
