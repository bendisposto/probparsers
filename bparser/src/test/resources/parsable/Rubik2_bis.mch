MACHINE Rubik2

/* A first attempt - not functional yet; but contains some interesting
 functions and lambdas */

SETS
  COLOUR = {white,yellow,orange,blue,red,green};
  FACE = {ftop,fbot,fleft,fright,ffront,fback}
  
VARIABLES
  cube
  
DEFINITIONS

   rotate(x) == x
  
INVARIANT
  cube: FACE --> ( (0..1) * (0..1) --> COLOUR)
  
INITIALISATION
 cube := { ftop |->   { (0|->0) |-> orange, (1|->0) |-> white, (0|->1) |-> yellow, (1|->1) |-> blue},
            ffront |-> { (0|->0) |-> red, (1|->0) |-> green, (0|->1) |-> blue, (1|->1) |-> orange },
            fleft |->  { (0|->0) |-> white, (1|->0) |-> yellow, (0|->1) |-> white, (1|->1) |-> yellow},
            fright |-> { (0|->0) |-> blue, (1|->0) |-> green, (0|->1) |-> green, (1|->1) |-> yellow},
            fbot |-> {(0|->0) |-> red, (1|->0) |-> blue, (0|->1) |-> white, (1|->1) |-> orange},
            fback |->  { (0|->0) |-> red, (1|->0) |-> green, (0|->1) |-> red, (1|->1) |-> orange} 
           }
 
 OPERATIONS
  
  Test = skip;
 
 cc <-- RotatedFace(f) = PRE f: FACE THEN
     ANY rot_face WHERE  
                  rot_face = { (0|->0) |-> (0|->1), (0|->1) |-> (1|->1),
                          (1|->1) |-> (1|->0), (1|->0) |-> (0|->0)}
                  &
                  rot_face: ((0..1) * (0..1)) --> ((0..1) * (0..1))
             THEN
               cc := %pos . (pos: (0..1)*(0..1) | cube(f)(rot_face(pos)))
             END
    END;
 
  cc <-- Rot = 
    BEGIN
     ANY rot WHERE  
                  rot = { fback |-> ftop, ftop |-> ffront, ffront |-> fbot, fbot |-> fback}
                  &
                  rot: FACE +-> FACE
             THEN
         cc := %ff . (ff:FACE|cube(rot(ff)))
      END
   END;
   
  cc <-- Rot2 = 
    BEGIN
     ANY rot WHERE  
                  rot = { fback |-> ftop, ftop |-> ffront, ffront |-> fbot, fbot |-> fback}
                  &
                  rot: FACE +-> FACE
             THEN
         cc := cube(rot(ftop))
      END
   END;
     
  RotateUp =
   BEGIN
     ANY rot_face WHERE  
                  rot_face = { (0|->0) |-> (0|->1), (0|->1) |-> (1|->1),
                          (1|->1) |-> (1|->0), (1|->0) |-> (0|->0)}
                  &  rot_face: ((0..1) * (0..1)) --> ((0..1) * (0..1))
     THEN
         ANY rot WHERE rot: FACE +-> FACE 
                  & rot = { fback |-> ftop, ftop |-> ffront, ffront |-> fbot, fbot |-> fback}
             THEN
               cube :=  %ff  . (ff:dom(rot)|cube(rot(ff))) \/
                        { fright |-> %pos . (pos: (0..1)*(0..1) | cube(fright)(rot_face(pos))),
                          fleft |-> %pos . (pos: (0..1)*(0..1) | 
                                              cube(fleft)(rot_face(rot_face(rot_face(pos)))))
                        }
         END
      END
   END
   
 END