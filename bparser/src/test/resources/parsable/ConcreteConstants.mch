MACHINE TestConcreteConstants
SETS
   ens
 
CONCRETE_CONSTANTS
   x

ABSTRACT_CONSTANTS
   z

PROPERTIES
   x:NAT & z:POW(NAT) & x=0 & z = {0,1}
 
CONCRETE_VARIABLES var

INVARIANT var : ens  
 
INITIALISATION
   var :: ens 
 
OPERATIONS
   res <-- fun(nn)=
      PRE
         nn : {0,2,5} & nn /: z
      THEN
         res := var
      END
END