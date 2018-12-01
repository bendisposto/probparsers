MACHINE RelationExpressions

VARIABLES r, s, a, b

INVARIANT r : POW(INT * INT) & s : POW(INT) & a : POW(INT * INT) & b : POW(INT)

INITIALISATION
    r := {(1 |-> 2), (2 |-> 3), (3 |-> 4), (4 |-> 5)};
    s := {2,3};
    a := {};
    b := {}


OPERATIONS
    simulate =
        a := s <| r;
        a := s <<| r;
        a := r |> s;
        a := r |>> s;
        a := r~;
        a := s * s;
        b := dom(r);
        b := ran(r)

END