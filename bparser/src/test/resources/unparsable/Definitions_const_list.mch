MACHINE Definitions_const_list
DEFINITIONS
  var_list == x, y;
  cst_list == 2, 2
VARIABLES
  var_list
INVARIANT
 x: NATURAL & y:NATURAL
INITIALISATION var_list := 2,2
OPERATIONS
 s <-- Sum = BEGIN s:= x+y END;
 Inc = x := x+1;
 Reset = SELECT x+y>6 THEN
   var_list := 2,2
 END
END

