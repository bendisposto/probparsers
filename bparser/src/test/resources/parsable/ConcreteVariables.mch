MACHINE TestConcreteVariables
SETS
   ens
 
CONCRETE_VARIABLES
   var, x

ABSTRACT_VARIABLES
   z
 
INVARIANT
   var : ens  & x:NAT & z:POW(NAT)
 
INITIALISATION
   var :: ens || x:= 0 || z := {}
 
OPERATIONS
   res <-- fun(nn)=
      PRE
         nn : {0,2,5} & nn /: z
      THEN
         res :: ens || z := z \/ {nn}
      END
END