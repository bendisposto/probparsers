MACHINE SurjEnumCst
CONSTANTS c 
PROPERTIES c=0
VARIABLES xx
INVARIANT
 xx: 1..9 +->> 0..7
INITIALISATION xx:(xx: 1..9 +->> 0..7 & xx(1)=5 & xx(9) =2 )
OPERATIONS
  y <-- get = y:=xx[{9}];
  y <-- get2 = y:= xx(1)
END