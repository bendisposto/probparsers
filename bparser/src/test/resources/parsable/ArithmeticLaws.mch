MACHINE ArithmeticTest
DEFINITIONS
 MAX_x == 50;
 MAX_y == 9;
 MAX_z == 4
VARIABLES x,y,z
INVARIANT
 x:NATURAL & y:NATURAL & z:NATURAL &

 x*y = y*x &
 x*(y+z) = x*y + x*z &
 x+y = y+x &
 x*1 = x &
 x*0 = 0 &
 (x+y)+z = x + (y+z) &
 (x*y)*z = x*(y*z) &
 2*x = x+x &
 x**2 = x*x &
 ((x / 2)*2 = x) <=> (x mod 2 = 0)
INITIALISATION x,y,z := 0,0,0
OPERATIONS
  IncX = PRE x<MAX_x THEN x := x+1 END;
  IncY = PRE y<MAX_y THEN y := y+1 END;
  IncZ = PRE z<MAX_z THEN z := z+1 END /* ;
  AddToZ(a) = PRE a:NATURAL THEN z := z+a END */
END

