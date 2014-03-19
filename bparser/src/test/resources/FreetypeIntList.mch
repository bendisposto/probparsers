MACHINE FreetypeIntList
FREETYPES
  IntList = inil, icons(INTEGER*IntList)
VARIABLES
  x
INVARIANT
  x : IntList
INITIALISATION
  x := nil
OPERATIONS
  add(i) = PRE i:INTEGER THEN
    x := icons(i,x)
  END
END
