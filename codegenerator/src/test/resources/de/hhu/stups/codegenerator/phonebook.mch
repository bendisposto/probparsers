MACHINE phonebook

SETS
	Name; Code 

VARIABLES db

DEFINITIONS
    scope_Name == 1..3;
    scope_Code == 7..8;
    
    GOAL == (card(db)=3)
    
INVARIANT
		db : Name +-> Code

INITIALISATION  
	db := {}
	
OPERATIONS

add(nn,cc) =
	PRE
		nn:Name & cc:Code  &
		nn /: dom(db) 
	THEN
		db := db \/ { nn |-> cc}
	END;

cc <-- lookup(nn) =
	PRE
		nn : dom(db) & nn:Name
	THEN
		cc:=db(nn)
	END;


update(nn,cc) =
	PRE
		nn:Name & cc:Code  &
		nn : dom(db)
	THEN
		db(nn) := cc
	END;

reset = 
    PRE
       db: Name --> Code
    THEN
        db := {}
    END
END


