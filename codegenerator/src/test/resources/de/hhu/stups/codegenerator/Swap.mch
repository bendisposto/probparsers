MACHINE Swap

VARIABLES  xx,yy

INVARIANT  xx : INT & yy : INT

INITIALISATION xx := 0 || yy:= 1

OPERATIONS
    swap = xx := yy || yy := xx

END