/* RefinementMachine
 * Author: 
 * Creation date: 13.04.18
 */

IMPLEMENTATION RefinementMachine
REFINES Operation

CONCRETE_VARIABLES xx
   

INITIALISATION xx := 1


OPERATIONS
out <-- Inc(pp) = 
    BEGIN 
     VAR ii
     IN 
         ii := 1;
         WHILE ii <= pp
         DO
            xx:= xx + 1;
            ii:= ii + 1
         INVARIANT ii : 1..pp+1
         VARIANT pp-ii
         END
      END;
      out := xx
        //xx := xx + pp; out := xx 
    END

END