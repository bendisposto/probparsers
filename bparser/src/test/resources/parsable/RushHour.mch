MACHINE RushHour

DEFINITIONS
  INDEX == (1..dim);
  GOAL == (pos_hcar(red_hcar) >= dim-size_hcar(red_hcar)+1)
CONSTANTS
 vcars,hcars,dim, col_vcar, row_hcar, size_vcar, size_hcar,
 red_hcar
 
PROPERTIES

 /* The particular puzzle (nr. 40) */
 vcars=7 & hcars = 6 &
 col_vcar =  {1|->1, 2|->2, 3|->3, 4|->3, 5|->4, 6|->5, 7|->6} & 
 size_vcar = {1|->3, 2|->2, 3|->2, 4|->2, 5|->2, 6|->2, 7|->3} &
 row_hcar =  {1|->1, 2|->3, 3|->4, 4|->5, 5|->6, 6|->6} &
 size_hcar = {1|->2, 2|->2, 3|->3, 4|->2, 5|->2, 6|->2} &
 red_hcar = 2 /* red hcar */
 &

 dim = 6 &
 vcars : NATURAL1 & hcars: NATURAL1 &
 col_vcar: 1..vcars --> INDEX &
 row_hcar: 1..hcars --> INDEX &
 size_vcar: 1..vcars --> INDEX &
 size_hcar: 1..hcars --> INDEX &
 red_hcar : 1..hcars &
 
 /* vcars are in ascending in row order */
 !r.(r:1..(vcars-1) => col_vcar(r)<=col_vcar(r+1)) &
 /* hcars are in ascending in col order */
 !c.(c:1..(hcars-1) => row_hcar(c)<=row_hcar(c+1)) 
 
 
VARIABLES
  pos_vcar,
  pos_hcar
INVARIANT
  pos_vcar: 1..vcars --> INDEX &
  pos_hcar: 1..hcars --> INDEX
  
INITIALISATION
  pos_vcar := {1|->1, 2|->2, 3|->2, 4|->5, 5|->4, 6|->1, 7|->2 } ||
  pos_hcar := {1|->2, 2|->4, 3|->1, 4|->5, 5|->1, 6|->4}
OPERATIONS
  move_hcar_right(car) = 
    PRE car:1..hcars &
        pos_hcar(car)<=dim - size_hcar(car) & /* car not at extreme right */
       (car<hcars => (row_hcar(car) /= row_hcar(car+1) or
                     pos_hcar(car+1) > pos_hcar(car)+1)) &
       !cv.(cv:1..vcars & col_vcar(cv)=pos_hcar(car)+size_hcar(car) =>
             row_hcar(car) /: pos_vcar(cv)..pos_vcar(cv)+size_vcar(cv)-1)
       THEN
    pos_hcar(car) := pos_hcar(car)+1
  END;

  move_hcar_left(car) = 
    PRE car:1..hcars &
        pos_hcar(car)> 1 & /* car not at extreme left */
       (car>1 => (row_hcar(car) /= row_hcar(car-1) or
                  pos_hcar(car-1)+size_hcar(car-1) <= pos_hcar(car)+1))&
       !cv.(cv:1..vcars & col_vcar(cv)=pos_hcar(car)-1 =>
             row_hcar(car) /: pos_vcar(cv)..pos_vcar(cv)+size_vcar(cv)-1)
       THEN
    pos_hcar(car) := pos_hcar(car)-1
  END;
  
  
  move_vcar_down(car) = 
    PRE car:1..vcars &
        pos_vcar(car)<=dim - size_vcar(car) & /* car not at extreme bottom */
       (car<vcars => (col_vcar(car) /= col_vcar(car+1) or
                     pos_vcar(car+1) > pos_vcar(car)+1)) &
       !cv.(cv:1..hcars & row_hcar(cv)=pos_vcar(car)+size_vcar(car) =>
             col_vcar(car) /: pos_hcar(cv)..pos_hcar(cv)+size_hcar(cv)-1)
       THEN
    pos_vcar(car) := pos_vcar(car)+1
  END;

  move_vcar_up(car) = 
    PRE car:1..vcars &
        pos_vcar(car)> 1 & /* car not at extreme top */
       (car>1 => (col_vcar(car) /= col_vcar(car-1) or
                     pos_vcar(car-1)+size_vcar(car-1) <= pos_vcar(car)+1)) &
       !cv.(cv:1..hcars & row_hcar(cv)=pos_vcar(car)-1 =>
             col_vcar(car) /: pos_hcar(cv)..pos_hcar(cv)+size_hcar(cv)-1)
       THEN
    pos_vcar(car) := pos_vcar(car)-1
  END 
/*;
  block_vcar_up(car,cv) = 
    PRE car:1..vcars & 
        pos_vcar(car)> 1 &
       (car>1 => (col_vcar(car) /= col_vcar(car-1) or
                     pos_vcar(car-1)+size_vcar(car-1) <= pos_vcar(car)+1)) &
       (cv:1..hcars & row_hcar(cv)=pos_vcar(car)-1 &
             col_vcar(car) : pos_hcar(cv)..pos_hcar(cv)+size_hcar(cv)-1)
       THEN
    skip
  END
  */
  
END