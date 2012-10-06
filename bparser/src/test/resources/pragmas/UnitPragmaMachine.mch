/*@ unit_alias AliasName [1,x,3] [5,t,6] */
MACHINE m

VARIABLES
  /*@ unit [1,m,1] */ xx,
  /*@ inferred_unit [1,x,2] */ yy

INVARIANT xx:NAT & yy:NAT

INITIALISATION xx,yy:=1,1


END