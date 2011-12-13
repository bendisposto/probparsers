MACHINE
   HCG
SETS
   ENTITE ={hotel, client, groom}; TYPE ={input, output}
VARIABLES
   solde, montant
INVARIANT
       montant : ENTITE * TYPE --> INTEGER &
   solde : ENTITE +-> INTEGER & /* typing added by mal */
   solde = %ee. (ee : ENTITE | montant(ee|-> input) - montant (ee |->
output)) &

   (solde(hotel) + solde(client) + solde (groom)) = 0
INITIALISATION
   /* solde, montant := ENTITE * {0},
    (ENTITE*TYPE) *  {0}*/
   solde := {(hotel |-> 0),( client |-> 0), (groom |-> 0)} ||
   montant := {((hotel |-> input)|-> 0), ((client |-> input) |-> 0),
((groom |-> input) |-> 0), ((hotel |-> output)|-> 0), ((client |->
output) |-> 0), ((groom |-> output) |-> 0)}

   OPERATIONS
   emission(emetteur, mt) =
   PRE
       emetteur: ENTITE &

       mt : INTEGER &

       ((mt - montant(emetteur |->output)) > MININT) &

       ((solde(emetteur) - mt) > MININT)
   THEN

       montant(emetteur |->output) := montant(emetteur |-> output)
        + mt

||        solde(emetteur):= solde(emetteur) -mt

   END;

   reception(recepteur, mt) =
   PRE
       recepteur : ENTITE &
       mt : INTEGER &
       ((mt + montant(recepteur |->input)) < MAXINT) &
       ((solde(recepteur)+mt )< MAXINT)

   THEN
        montant(recepteur |->input) := montant(recepteur |->input) + mt

||        solde(recepteur) := solde(recepteur) + mt
   END;

/* on est oblige de faire deux operations car si on ne faisait qu'une
operations transaction(emetteur, recepteur, mt), on serait conduit à
avoir deux mises à jour d'une même variable en parallèle . Autre
solution ci-après. On a volontairement écrit la substitution dans solde
avec une union et celle dans montant avec l'écrasement. Pour montrer ce
que fait l'écrasement*/

   transaction(emetteur, recepteur, mt) =

PRE
       recepteur : ENTITE &
       mt : INTEGER &
       ((mt + montant(recepteur |->input)) < MAXINT) &
       ((solde(recepteur)+mt )< MAXINT) &
   emetteur: ENTITE &
       ((mt - montant(emetteur |->output)) > MININT) &

       ((solde(emetteur) - mt) > MININT)
THEN
       solde := solde \/ {(emetteur |-> solde(emetteur) - mt),
(recepteur |-> solde(recepteur) + mt)} - {(emetteur |->
solde(emetteur)), (recepteur |-> solde(recepteur))} ||

       montant := montant <+ {((recepteur|-> input)
|->(montant(recepteur |->input) + mt)),((emetteur |->output) |->
(montant(emetteur |-> output)+ mt ))}
END




END