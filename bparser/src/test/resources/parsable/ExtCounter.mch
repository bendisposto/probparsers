MACHINE ExtCounter
EXTENDS Counter
OPERATIONS
  b <-- isZero = IF cnt=0 THEN b:= TRUE ELSE b:= FALSE END;
  r <-- GetVal = BEGIN r:= cnt END;
  reset = BEGIN cnt := 0 END
END