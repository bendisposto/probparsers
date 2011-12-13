MACHINE Counter
/* A machine manually constructed from a proof obligation inc/inv2/INV by the Rodin Tool
 for the counter Event B Machine which has one counter, an invariant inv2:c<100
 and an operation inc = c:=c+1 which is not guarded
 */
DEFINITIONS
 SET_PREF_MAXINT==101
CONSTANTS x0,x1
PROPERTIES
x0 : INTEGER & x1 : INTEGER & (x0 : NATURAL) & (x0 < 100)  &

not((x0 + 1) < 100)
END