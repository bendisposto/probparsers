MACHINE TotalFunTest3
SETS
 ID={aa,bb,cc,dd,ee} 
CONSTANTS f
PROPERTIES
  f: POW(ID) -->>ID & !yy.(yy:ID => f({yy}) = yy)
VARIABLES xx
INVARIANT
 xx:ID 
INITIALISATION xx::ran(f)
OPERATIONS
  Set(yy) = PRE yy:POW(ID) & yy: dom(f) & card(yy)=1 THEN xx:= f(yy) END;
  Set2(yy) = PRE yy:POW(ID) & yy: dom(f)  THEN xx:= f(yy) END;
  is_xx(yy) = PRE yy:ID & yy=xx THEN skip END
END

