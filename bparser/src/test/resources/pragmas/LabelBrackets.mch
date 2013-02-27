MACHINE LabelBrackets
SETS
ID={aa,bb,cc}
CONSTANTS iv
PROPERTIES
 /*@label "(1)" 	*/ iv:{aa,bb} &
 /*@label (2) */ iv=bb &
 /*@label (3) */ {} <<: {aa,bb} &
 /*@label (4) */ {aa} <<: {aa,bb}
VARIABLES xx
INVARIANT
xx:ID
INITIALISATION xx:=iv
OPERATIONS
 Set(yy) = PRE yy:ID THEN xx:= yy END
END