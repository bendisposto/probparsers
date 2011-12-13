MACHINE Rubik2_twist

/* a version where one can only twist but not rotate */

SETS
  COLOUR = {white,yellow,orange,blue,red,green}
  
VARIABLES
  faceA,faceB,faceC,faceD,faceE,faceE
 
  
INVARIANT
  not(faceA = { 1 |-> yellow, 2|-> yellow, 3 |-> yellow, 4 |-> yellow}) &
  
  faceA:   ( (1..4) --> COLOUR) &
  faceB:   ( (1..4) --> COLOUR) &
  faceC:  ( (1..4) --> COLOUR) &
  faceD: ( (1..4) --> COLOUR) &
  faceE: ( (1..4) --> COLOUR) &
  faceF:  ( (1..4) --> COLOUR) 
  
  /*
  not( !pos.(pos: (0..4) => (faceA(pos) = yellow))) &
  not( !pos.(pos: (0..4) => (faceA(pos) = green))) &
  not( !pos.(pos: (0..4) => (faceA(pos) = white))) */
  
  
INITIALISATION

  faceA :=   { 1 |-> yellow, 2 |-> blue, 3 |-> yellow, 4 |-> blue} ||
  faceB :=   { 1 |-> white, 2 |-> white, 3 |-> white,  4 |-> white }  ||
  faceC :=   { 1 |-> orange, 2 |-> orange, 3 |-> yellow, 4 |-> yellow} ||
  faceD :=   { 1 |-> blue, 2 |-> green, 3 |-> blue,  4 |-> green} ||
  faceE :=   { 1 |-> orange, 2 |-> green, 3 |-> orange, 4 |-> green} ||
  faceF :=   { 1 |-> red, 2 |-> red, 3 |-> red,  4 |-> red} 
  
  
  
 OPERATIONS
 
  
  twist_up_rhs(A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4,F1,F2,F3,F4) =
   PRE
      faceA = { 1 |-> A1, 2|-> A2, 3 |-> A3, 4 |-> A4} &
      faceB = { 1 |-> B1, 2|-> B2, 3 |-> B3, 4 |-> B4} &
      faceC = { 1 |-> C1, 2|-> C2, 3 |-> C3, 4 |-> C4} &
      faceD = { 1 |-> D1, 2|-> D2, 3 |-> D3, 4 |-> D4} &
      faceF = { 1 |-> F1, 2|-> F2, 3 |-> F3, 4 |-> F4}
      /* cube(face(A1,A2,A3,A4),face(B1,B2,B3,B4),face(C1,C2,C3,C4),
           face(D1,D2,D3,D4),FE,face(F1,F2,F3,F4)) */
   THEN
      faceA := {1 |-> D1, 2|-> D2, 3 |-> A3, 4 |-> A4 } ||
      faceB := {1 |-> A1, 2|-> A2, 3 |-> B3, 4 |-> B4 } ||
      faceC := {1 |-> B1, 2|-> B2, 3 |-> C3, 4 |-> C4 } ||
      faceD := {1 |-> C1, 2|-> C2, 3 |-> D3, 4 |-> D4 } ||
      faceF := {1 |-> F2, 2|-> F4, 3 |-> F1, 4 |-> F3 }
      /* cube(face(D1,D2,A3,A4),face(A1,A2,B3,B4),face(B1,B2,C3,C4),
           face(C1,C2,D3,D4),FE,face(F2,F4,F1,F3)) */
   END;
   
   
  twist_right_bot(B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4,E1,E2,E3,E4,F1,F2,F3,F4) =
   PRE
      faceB = { 1 |-> B1, 2|-> B2, 3 |-> B3, 4 |-> B4} &
      faceC = { 1 |-> C1, 2|-> C2, 3 |-> C3, 4 |-> C4} &
      faceD = { 1 |-> D1, 2|-> D2, 3 |-> D3, 4 |-> D4} &
      faceE = { 1 |-> E1, 2|-> E2, 3 |-> E3, 4 |-> E4} &
      faceF = { 1 |-> F1, 2|-> F2, 3 |-> F3, 4 |-> F4}
      /* 
      cube(FA,face(B1,B2,B3,B4),face(C1,C2,C3,C4),
           face(D1,D2,D3,D4),face(E1,E2,E3,E4),face(F1,F2,F3,F4)), */
   THEN
      faceB := {1 |-> B1, 2|-> F1, 3 |-> B3, 4 |-> F2 } ||
      faceC := {1 |-> C2, 2|-> C4, 3 |-> C1, 4 |-> C3 } ||
      faceD := {1 |-> E3, 2|-> D2, 3 |-> E4, 4 |-> D4 } ||
      faceE := {1 |-> E1, 2|-> E2, 3 |-> B4, 4 |-> B2 } ||
      faceF := {1 |-> D3, 2|-> D1, 3 |-> F3, 4 |-> F4 }
      /* 
      cube(FA,face(B1,F1,B3,F2),face(C2,C4,C1,C3),
           face(E3,D2,E4,D4),face(E1,E2,B4,B2),face(D3,D1,F3,F4)) */
   END;
   
   
  twist_right_back(A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,E1,E2,E3,E4,F1,F2,F3,F4) =
   PRE
      faceA = { 1 |-> A1, 2|-> A2, 3 |-> A3, 4 |-> A4} &
      faceB = { 1 |-> B1, 2|-> B2, 3 |-> B3, 4 |-> B4} &
      faceC = { 1 |-> C1, 2|-> C2, 3 |-> C3, 4 |-> C4} &
      faceE = { 1 |-> E1, 2|-> E2, 3 |-> E3, 4 |-> E4} &
      faceF = { 1 |-> F1, 2|-> F2, 3 |-> F3, 4 |-> F4}
      /* 
      cube(face(A1,A2,A3,A4),face(B1,B2,B3,B4),face(C1,C2,C3,C4),
           FD,face(E1,E2,E3,E4),face(F1,F2,F3,F4)), */
   THEN
      faceA := {1 |-> A1, 2|-> E2, 3 |-> A3, 4 |-> E4 } ||
      faceB := {1 |-> B3, 2|-> B1, 3 |-> B4, 4 |-> B2 } ||
      faceC := {1 |-> F4, 2|-> C2, 3 |-> F2, 4 |-> C4 } ||
      faceE := {1 |-> E1, 2|-> C3, 3 |-> E3, 4 |-> C1 } ||
      faceF := {1 |-> F1, 2|-> A2, 3 |-> F3, 4 |-> A4 }
      /* 
      cube(face(A1,E2,A3,E4),face(B3,B1,B4,B2),face(F4,C2,F2,C4),
           FD,face(E1,C3,E3,C1),face(F1,A2,F3,A4)) */
   END
   
   
END