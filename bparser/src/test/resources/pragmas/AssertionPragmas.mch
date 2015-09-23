MACHINE AssertionPragmas
/* A test machine to show how labels can be associated with axioms and theorems using
 pragmas */
CONSTANTS a,b,c
PROPERTIES
 /*@ label axm1 */ a:1..10 &
 /*@ label axm2 */ b:1..10 &
 /*@ label axm3 */ c:7..10 &
 /*@ label axm4 */ a+b = c & 
 /*@ label "axm5 gt" */ /*@ label "a greater than b" */ /*@ label last */
 /*@ label a_gt_b */ /*@ label "a  *is*  greater than b" */ 
 /*@ label "this quote is not closed" */  a>b
ASSERTIONS
 /*@ label thm1 */ a=c-b &
 /*@ label thm2 */ b=c-a ;
 /*@ label thm3 */ (c mod 2 = 1 => a/=b)
END

