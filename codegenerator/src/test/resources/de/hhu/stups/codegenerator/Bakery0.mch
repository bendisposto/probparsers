MACHINE Bakery0

VARIABLES aa

INVARIANT aa:INT

INITIALISATION aa:=0

OPERATIONS

	enter1 = SELECT aa=0 THEN aa:=1 END ;
	enter2 = SELECT aa=0 THEN aa:=2 END ;
	leave1 = SELECT aa=1 THEN aa:=0 END ;
	leave2 = SELECT aa=2 THEN aa:=0 END ;
	try1 = skip;
	try2 = skip
END
