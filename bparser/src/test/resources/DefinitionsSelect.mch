MACHINE DefinitionsSelect

ABSTRACT_CONSTANTS
  probleme_2

PROPERTIES
   probleme_2 = %xx.(xx : INTEGER | xx)


DEFINITIONS
   probleme_1(predicat) == SELECT predicat THEN skip END

OPERATIONS
   MyOp = probleme_1((2>1))
END 