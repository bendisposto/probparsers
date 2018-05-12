/* AbstractMachine
 * Author: 
 * Creation date: 13.04.18
 */
MACHINE
    AbstractMachine

VARIABLES xx
   
INVARIANT xx : INT
INITIALISATION xx := 1

OPERATIONS
out <-- Inc(pp) =
    PRE pp : 1..10 THEN xx := xx + pp || out := xx END

END
