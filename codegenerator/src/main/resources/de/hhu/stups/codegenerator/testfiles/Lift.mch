MACHINE Lift

VARIABLES  floor

INVARIANT  floor : 0..99 /* NAT */

INITIALISATION floor := 0

OPERATIONS

	inc = PRE floor<99 THEN floor := floor + 1 END ;
	dec = PRE floor>0 THEN floor := floor - 1 END

END