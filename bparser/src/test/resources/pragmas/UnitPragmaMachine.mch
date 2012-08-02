/*@ unit_alias nameOfAlias km [0,h,-1] */

MACHINE m

VARIABLES
  /*@ unit [0,u,0] */ a,
  /*@ inferred_unit [0,u2,1] */ b

INVARIANT a:NAT & b:NAT

INITIALISATION a,b := 0,0


END