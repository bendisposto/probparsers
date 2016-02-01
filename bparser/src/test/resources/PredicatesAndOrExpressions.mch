MACHINE test

DEFINITIONS
	pred_1(x,y) == (x < y)

VARIABLES
	x, y, z

INITIALISATION
	x:=1;
	y:=2

OPERATIONS
	result = pred_1(x,y);
	z := x < y

END