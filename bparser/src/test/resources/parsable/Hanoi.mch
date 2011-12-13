MACHINE Hanoi
SETS
 Stakes
DEFINITIONS
 GOAL == (!s.(s:Stakes & s/=dest => on(s) = <>));
/* 12.35 secs on PPC G4 1.25 GHz to find GOAL with hash_markers; 75 seconds on Alloy4 */
 SET_PREF_SYMBOLIC == FALSE;
 scope_Stakes == 1..3
CONSTANTS orig,dest,nrdiscs
PROPERTIES
 orig: Stakes & dest:Stakes & orig /= dest & nrdiscs = 5
VARIABLES on
INVARIANT
 on : Stakes --> seq(INTEGER)
INITIALISATION 
  on := %s.(s:Stakes & s /= orig | <>) \/ {orig |-> %x.(x:1..nrdiscs|x)}
OPERATIONS
  Move(from,to,disc) = PRE from:Stakes & on(from) /= <> &
                           to:Stakes & to /= from &
                           disc:NATURAL1 & disc = first(on(from)) &
                           (on(to) /= <> =>  first(on(to))> disc )
                       THEN
     on := on <+ { from |-> tail(on(from)), to |-> (disc -> on(to)) }
  END
END

