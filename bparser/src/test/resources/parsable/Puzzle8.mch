MACHINE Puzzle8
DEFINITIONS
  INV == (board: ((1..dim)*(1..dim)) -->> 0..nmax);
  GOAL2 == !(i,j).(i:1..dim & j:1..dim => board(i|->j) = (j+(i-1)*dim) mod dim*dim);
  GOAL == !(i,j).(i:1..dim & j:1..dim => board(i|->j) = j-1+(i-1)*dim)
CONSTANTS dim, nmax
PROPERTIES
 dim:NATURAL1 &  dim=3 &
 nmax:NATURAL1 & nmax = dim*dim-1
VARIABLES board
INVARIANT
 INV
INITIALISATION 
  board : (INV & GOAL)
OPERATIONS
  MoveDown(i,j,x) = PRE i:2..dim & j:1..dim & board(i|->j) = 0 & x:1..nmax & board(i-1|->j) = x  THEN
     board := board <+ {(i|->j)|->x, (i-1|->j)|->0}
  END;
  MoveUp(i,j,x) = PRE i:1..dim-1 & j:1..dim & board(i|->j) = 0 & x:1..nmax & board(i+1|->j) = x  THEN
     board := board <+ {(i|->j)|->x, (i+1|->j)|->0}
  END;
  MoveRight(i,j,x) = PRE i:1..dim & j:2..dim & board(i|->j) = 0 & x:1..nmax & board(i|->j-1) = x  THEN
     board := board <+ {(i|->j)|->x, (i|->j-1)|->0}
  END;
  MoveLeft(i,j,x) = PRE i:1..dim & j:1..dim-1 & board(i|->j) = 0 & x:1..nmax & board(i|->j+1) = x  THEN
     board := board <+ {(i|->j)|->x, (i|->j+1)|->0}
  END;
  Goal2(i,j) = PRE i:1..dim & j:1..dim & board(i|->j) = (j+(i-1)*dim) mod dim*dim THEN skip END
END

