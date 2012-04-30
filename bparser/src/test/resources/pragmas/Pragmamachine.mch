MACHINE m

CONSTANTS 
  f, fib

PROPERTIES
    f = /*@ symbolic*/ %x.(x:A|x+c) &
  /*@ label axm2 */ f: A--> INTEGER &
 /*@ rec_let */ /*@ label axm3 */  fib = %x.(x:{0,1}|1) \/ %x.(x>2| fib(x-1)+fib(x-2)) &
  /*@ label axm4 */ fib(0) = fib(1)

ASSERTIONS
   /*@ label thm1 */  x<100;
  /*@ label thm2 */ y=3

VARIABLES access

INVARIANT /*@label inv1 */ access : USER <-> PRINTER

INITIALISATION access := {}


END