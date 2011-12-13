MACHINE	PRIZE 

SETS
		PRIZESET

VARIABLES
		PRIZEinstances

INVARIANT
		PRIZEinstances : POW(PRIZESET) &
		PRIZEinstances  <: PRIZESET

		
INITIALISATION
		PRIZEinstances := {}

OPERATIONS
result  <-- PRIZEcreate =
	PRE
		PRIZEinstances  /= PRIZESET	
	THEN
		ANY new
		WHERE
			new : PRIZESET - PRIZEinstances
		THEN
			PRIZEinstances := PRIZEinstances \/ {new }
			|| result := new
		END
	END


END


