MACHINE LatinSquares
DEFINITIONS
  IDX == (1..Order)
CONSTANTS Order
PROPERTIES
 Order:NATURAL
 /* Use Order=4 for the Graphical TK State Viewer */
VARIABLES LatinSquare
INVARIANT
 LatinSquare: (IDX * IDX) +-> IDX
INITIALISATION LatinSquare := {}
OPERATIONS
  Set(i,j,val) = PRE i:IDX & j:IDX & val:IDX & (i|->j) /: dom(LatinSquare) THEN LatinSquare(i,j) := val END;
  Solve = ANY sol WHERE sol: (IDX*IDX) --> IDX &
                !(i,j).(i:IDX & j:IDX & (i|->j):dom(LatinSquare) => sol(i,j) = LatinSquare(i,j)) &
                !(i,j1,j2).(i:IDX & j1:IDX & j2:IDX & j1 > j2
                          =>  ( sol(i,j1) /= sol(i,j2) & sol(j1,i) /= sol(j2,i)) ) THEN
      LatinSquare := sol
  END
END

