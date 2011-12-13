MACHINE RecTest
SETS
 ID={aa,bb}
VARIABLES xx, bank
INVARIANT
 xx : struct(name : ID, balance : INT) & bank : struct(name : ID)
INITIALISATION bank := rec(name:bb) || xx:= rec(name:aa, balance:2)
OPERATIONS
  positive = PRE xx'balance > 0 THEN skip END;
  ok_withrdaw(amt) = PRE amt:NAT1 & (xx'balance >= amt) THEN
     skip
  END
END

