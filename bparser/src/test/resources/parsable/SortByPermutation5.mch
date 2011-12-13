MACHINE SortByPermutation5
DEFINITIONS
  SET_PREF_MAXINT == 5
CONSTANTS sz
PROPERTIES sz:NATURAL & sz=5
VARIABLES a
INVARIANT
 a: 1..sz --> INTEGER
INITIALISATION a:= %j.(j:1..sz|sz-j)
OPERATIONS
  res <-- Get(i) = PRE i:1..sz THEN res:=a(i) END;
  Sort = ANY p WHERE p : perm(dom(a)) &
         !(i,j).(i :1..(sz-1) & j:2..sz & i<j =>   a(p(i)) <= a(p(j))) THEN
         a := (p;a)
    END
END

