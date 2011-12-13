MACHINE Rubik2

SETS
  COLOUR = {white,yellow,orange,blue,red,green} /* ;
  FACE = {ftop,fbot,fleft,fright,ffront,fback} */
  
VARIABLES
  ftop,fbot,fleft,fright,ffront,fback
  
DEFINITIONS

   rotate(x) == x
  
INVARIANT
  ftop:   ( (0..1) * (0..1) --> COLOUR) &
  fbot:   ( (0..1) * (0..1) --> COLOUR) &
  fleft:  ( (0..1) * (0..1) --> COLOUR) &
  fright: ( (0..1) * (0..1) --> COLOUR) &
  ffront: ( (0..1) * (0..1) --> COLOUR) &
  fback:  ( (0..1) * (0..1) --> COLOUR) &
  
  not( !pos.(pos: (0..1)*(0..1) => (ftop(pos) = yellow)))
  /*&
  
  not( !pos.(pos: (0..1)*(0..1) => (ftop(pos) = green))) &
  
  not( !pos.(pos: (0..1)*(0..1) => (ftop(pos) = white))) */
  
INITIALISATION

  ftop :=   { (0|->0) |-> yellow, (0|->1) |-> blue, (1|->0) |-> yellow, (1|->1) |-> blue} ||
  ffront :=  { (0|->0) |-> white, (0|->1) |-> white, (1|->0) |-> white,  (1|->1) |-> white }  ||
  fleft :=   { (0|->0) |-> orange, (0|->1) |-> orange, (1|->0) |-> yellow, (1|->1) |-> yellow} ||
  fright :=  { (0|->0) |-> blue, (0|->1) |-> green, (1|->0) |-> blue,  (1|->1) |-> green} ||
  fbot :=  {(0|->0) |-> orange, (0|->1) |-> green, (1|->0) |-> orange, (1|->1) |-> green} ||
  fback :=   { (0|->0) |-> red, (0|->1) |-> red,(1|->0) |-> red,  (1|->1) |-> red} 
  
  
  /*
  
  ftop :=    %pos . (pos: (0..1)*(0..1) | green) ||
  ffront :=  %pos . (pos: (0..1)*(0..1) | blue) ||
  fleft :=   %pos . (pos: (0..1)*(0..1) | red) ||
  fright :=  %pos . (pos: (0..1)*(0..1) | white) ||
  fbot :=    %pos . (pos: (0..1)*(0..1) | yellow) ||
  fback :=   %pos . (pos: (0..1)*(0..1) | orange)
  
  ftop :=   { (0|->0) |-> orange, (0|->1) |-> yellow, (1|->0) |-> white, (1|->1) |-> blue} ||
  ffront :=  { (0|->0) |-> red, (0|->1) |-> blue, (1|->0) |-> green,  (1|->1) |-> orange }  ||
  fleft :=   { (0|->0) |-> yellow, (0|->1) |-> white, (1|->0) |-> yellow, (1|->1) |-> white} ||
  fright :=  { (0|->0) |-> green, (0|->1) |-> yellow, (1|->0) |-> blue,  (1|->1) |-> green} ||
  fbot :=  {(0|->0) |-> red, (0|->1) |-> white, (1|->0) |-> blue, (1|->1) |-> orange} ||
  fback :=   { (0|->0) |-> orange, (0|->1) |-> green,(1|->0) |-> red,  (1|->1) |-> red} 
           */
 
 OPERATIONS
  
/*
   cc <-- test =
   BEGIN
    cc := %pos. (pos: (0..0)*(0..1) | fbot(pos))
   END;
   
   */
     
  RotateUp =
   BEGIN
     ANY rot WHERE  
                  rot = { (0|->0) |-> (0|->1), (0|->1) |-> (1|->1),
                          (1|->1) |-> (1|->0), (1|->0) |-> (0|->0)}
                  &  rot: ((0..1) * (0..1)) --> ((0..1) * (0..1))
     THEN
       fback := ftop  ||
       ftop := ffront ||
       ffront := fbot ||
       fbot := fback  ||
       fright := %pos . (pos: (0..1)*(0..1) | fright(rot(rot(rot(pos))))) ||
       fleft :=  %pos . (pos: (0..1)*(0..1) | fleft(rot(pos)))
             
      END
   END;
   
   
  RotateRight =
   BEGIN
     ANY rot WHERE  
                  rot = { (0|->0) |-> (0|->1), (0|->1) |-> (1|->1),
                          (1|->1) |-> (1|->0), (1|->0) |-> (0|->0)}
                  &  rot: ((0..1) * (0..1)) --> ((0..1) * (0..1))
     THEN
       fleft := %pos . (pos: (0..1)*(0..1) | fbot(rot(pos)))  ||
       ftop := fleft ||
       fright := ftop ||
       fbot :=   %pos . (pos: (0..1)*(0..1) | fright(rot(rot(rot(pos)))))  ||
       ffront := %pos . (pos: (0..1)*(0..1) | ffront(rot(rot(rot(pos))))) ||
       fback :=  %pos . (pos: (0..1)*(0..1) | fback(rot(pos)))
             
      END
   END;
   
   
   TwistUp =
   BEGIN
     ANY rot WHERE   rot = { (0|->0) |-> (0|->1), (0|->1) |-> (1|->1),
                          (1|->1) |-> (1|->0), (1|->0) |-> (0|->0)}
                  &  rot: ((0..1) * (0..1)) --> ((0..1) * (0..1))
     THEN
       fback :=  %pos. (pos: (0..0)*(0..1) | fback(pos)) \/  %pos. (pos: (1..1)*(0..1) | ftop(pos)) ||
       ftop :=   %pos. (pos: (0..0)*(0..1) | ftop(pos)) \/ %pos. (pos: (1..1)*(0..1) | ffront(pos)) ||
       ffront := %pos. (pos: (0..0)*(0..1) | ffront(pos)) \/ %pos. (pos: (1..1)*(0..1) | fbot(pos)) ||
       fbot :=   %pos. (pos: (0..0)*(0..1) | fbot(pos))   \/ %pos. (pos: (1..1)*(0..1) | fback(pos)) ||
       fright := %pos . (pos: (0..1)*(0..1) | fright(rot(rot(rot(pos))))) 
             
      END
   END 
   
 END