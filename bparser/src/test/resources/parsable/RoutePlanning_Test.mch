MACHINE RoutePlanning
/* by Michael Leuschel, August 2005 */
/* derived from Anthony Hall's Z specification 40_5 */

SETS
   RouteElement = {M4, A34, A40, w1, w2, i1, i2, f1, f2, Oxford, Newbury, Swindon, Bicester};
   PlaceOnRoad;
   Road
   
DEFINITIONS
   ProposedRoute == seq(RouteElement)
   
CONSTANTS
   ROAD,PLACE,
   
   /* from Geography: put into Constants as they do not seem to change */
   wholeRoads, finalRoads, initialRoads, namedRoads
PROPERTIES
    /* first the specific assignments for current example */
    ROAD = {M4, A34, A40, w1, w2, i1, i2, f1, f2} &
    PLACE = {Oxford, Newbury, Swindon, Bicester} & 
    namedRoads = {M4,A34,A40} &
    wholeRoads = {w1,w2} &
    finalRoads = {f1,f2} &
    initialRoads = {i1,i2} &
    
   /* RouteElement is partitioned into ROAD and PLACE */
   ROAD <: RouteElement & PLACE <: RouteElement &
   ROAD \/ PLACE = RouteElement &
   ROAD /\ PLACE = {} &
   ROAD /= {} & PLACE /= {}
   
    &
    
    /* wholeRoads, finalRoads, initialRoads, namedRoads are a Partition of ROAD */
   wholeRoads : FIN(ROAD) &
   finalRoads : FIN(ROAD) &
   initialRoads : FIN(ROAD) &
   namedRoads : FIN(ROAD) &
   wholeRoads  /\ finalRoads = {} &
   wholeRoads /\ initialRoads = {} &
   wholeRoads /\ namedRoads = {} &
   finalRoads /\ initialRoads = {} &
   finalRoads /\ namedRoads = {} &
   initialRoads /\ namedRoads = {} &
   wholeRoads \/ finalRoads \/ initialRoads \/ namedRoads = ROAD
   
   
VARIABLES
   validProposedRoute,
   place, connects, /* functions for schema PlaceOnRoad */
   placesOnRoad, /* function for schema Road */
   
   /* Geography schema */
   roads,places
   /* in Constants: wholeRoads, finalRoads, initialRoads, namedRoads */
INVARIANT
   validProposedRoute: ProposedRoute
   &
   
   place: PlaceOnRoad +-> PLACE &
   connects: PlaceOnRoad +-> FIN(RouteElement)
   &
   
   placesOnRoad: Road +-> iseq(PlaceOnRoad) &
   !(rr).(rr:dom(placesOnRoad) => card(placesOnRoad(rr))>1) 
   /* Missing:p1,p2 ... */
   &
   
   roads: ROAD >+> Road &
   places: FIN(PLACE)
   
INITIALISATION
   validProposedRoute := <> ||
   
   place := {} || connects := {} ||
   
   placesOnRoad := {} ||
   
   roads  := {} ||
   places := {} ||
   wholeRoads, finalRoads, initialRoads, namedRoads := {},{},{},{} 
OPERATIONS
   ExtendRoute(Section) = PRE Section:RouteElement THEN
      validProposedRoute := validProposedRoute <- Section
   END;
   
   SyntacticallyValid = PRE card(validProposedRoute)>1 &
     first(validProposedRoute) : PLACE &
     last(validProposedRoute) : PLACE &
     first(validProposedRoute) /= last(validProposedRoute)
     THEN skip
     END
 END