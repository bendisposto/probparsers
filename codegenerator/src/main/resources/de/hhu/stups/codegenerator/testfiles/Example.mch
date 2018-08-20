MACHINE Example

VARIABLES xx

INVARIANT xx : INT

INITIALISATION xx := 0

OPERATIONS

out <-- reset(pp) = PRE pp : INT THEN xx := pp; out := xx END

END