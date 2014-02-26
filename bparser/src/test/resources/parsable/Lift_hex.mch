MACHINE Lift

ABSTRACT_VARIABLES  floor

INVARIANT  floor : 0..0x63

INITIALISATION floor := 0x4

OPERATIONS

	inc = PRE floor<99 THEN floor := floor + 1 END ;
	
	dec = PRE floor /= 0x001 THEN floor := floor - 1 END 
	
END

