MACHINE PartialFunOverRel
SETS
 ID={aa,bb,cc}
CONSTANTS f
PROPERTIES
 f:(ID-->ID) +->> ID & card(f) = 3
OPERATIONS
  res <-- Applyf(x,y) = PRE x:ID & y:ID THEN
      ANY TF WHERE TF:ID-->ID & TF(x) = y THEN
        res := f(TF)
      END
  END
END

