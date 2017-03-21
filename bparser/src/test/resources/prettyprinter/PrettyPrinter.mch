MACHINE PrettyPrinter
SETS
  E_SET = {E1,E2,E3}
; DEFERRED_SET
ABSTRACT_CONSTANTS a_c, a_c2
CONSTANTS c1,c2
PROPERTIES
  a_c : INTEGER
& a_c2 : NATURAL
& c1 : INT
& c2 : NAT
VARIABLES v1, v2, v3
INVARIANT
  v1 : INTEGER
& v2 : INTEGER
& v3 : INTEGER
INITIALISATION
  v1 := 1
|| v2 := 2
|| v3 := 3
OPERATIONS
  out1, out2 <-- operation(p1,p2) =
    PRE
      p1 : INTEGER
      & p2 : INTEGER
    THEN
      out1 := 1 || out2 := 2
    END
ASSERTIONS
  rec(x: 1) : struct(x:{1})
END
