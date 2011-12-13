MACHINE SeqNaturalFoldr
CONSTANTS mul, foldr
PROPERTIES
    mul: (NATURAL*NATURAL)<->NATURAL &
    mul = {i,j,res | i:NATURAL & j:NATURAL & res:NATURAL & res=i*j} &

   foldr : (((NATURAL*NATURAL)<->NATURAL)*NATURAL*seq(NATURAL))<->NATURAL &
   foldr = 
    { f,base,i,res | i:seq(NATURAL) & base:NATURAL & 
                      res: NATURAL & f:(NATURAL*NATURAL)-->NATURAL &
           (i=<>  => res=base) &
           (size(i)>0 =>  res =  f(first(i),foldr(f,base,tail(i))) )
    } 
VARIABLES ss
INVARIANT ss: seq(NATURAL)
INITIALISATION ss  := <>
OPERATIONS
   Add(nn)    = PRE nn:NATURAL  THEN ss := ss <- nn END;
   FoldMul = BEGIN ss := foldr(mul,1,ss) -> ss  END
END