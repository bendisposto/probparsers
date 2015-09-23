MACHINE m

VARIABLES
  /*@ unit "m" */ xx,
  /*@ inferred_unit "x**2" */ yy

INVARIANT xx:NAT & yy:NAT

INITIALISATION xx,yy:=1,1


END