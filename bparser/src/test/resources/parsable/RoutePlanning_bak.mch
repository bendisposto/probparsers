MACHINE RoutePlanning
/* by Michael Leuschel, August 2005 */
/* derived from Anthony Hall's Z specification 40_5 */

SETS
   RouteElement = {M4, A34, A40, w1, w2, i1, i2, f1, f2, Oxford, Newbury, Swindon, Bicester};
   PlaceOnRoad = {o1,o2,n1,n2,s1,s2};
   Road = {M4r, A34r, A40r, w1r, w2r, i1r, i2r, f1r, f2r}
   
DEFINITIONS
   ProposedRoute == seq(RouteElement)
   
CONSTANTS
   ROAD,PLACE,
   
   /* from Geography: put into Constants as they do not seem to change */
   wholeRoads, finalRoads, initialRoads, namedRoads,
   
   road_id  /* function from schema road = road.id */
PROPERTIES
    /* first the specific assignments for current example */
    ROAD = {M4, A34, A40, w1, w2, i1, i2, f1, f2} &
    PLACE = {Oxford, Newbury, Swindon, Bicester} & 
    namedRoads = {M4,A34,A40} &
    wholeRoads = {w1,w2} &
    finalRoads = {f1,f2} &
    
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
   
   &
   
   road_id = {M4r |-> M4, A34r |-> A34, A40r |-> A40,
         w1r |-> w1, w2r |-> w2, i1r |-> i1,
         i2r |-> i2, f1r |-> f1, f2r |-> f2} &
   road_id: Road --> ROAD
   
   
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
   
   roads: ROAD >+> Road &  /* why is ROAD simply not a subset of Road ???! */
   !(rr).(rr:ran(roads) => !(pp).(pp: PlaceOnRoad & pp: ran(placesOnRoad(rr)) => place(pp): places))
   &
   places: FIN(PLACE)
   
INITIALISATION
   validProposedRoute := <> ||
   
   place := {o1 |-> Oxford, o2 |-> Oxford, n1 |-> Newbury, n2 |-> Newbury,
             s1 |-> Swindon, s2 |-> Swindon} ||
   connects := {o1 |->  {A34,A40}, o2 |-> {A34, Bicester} ,
                n1 |-> {}, n2 |-> {A34,M4},
                s1 |-> {A40,M4}, s2 |-> {Newbury}} ||
   
   placesOnRoad := {M4r |-> [n1,s2], A34r |-> [n1,o2],
                    A40r |-> [o1,s2], w1r |-> [n1,o2],
                    w2r |-> [o1,s2], i1r |-> [n1,s2], i2r |-> [o1,s2]} ||
   
   roads  := { M4 |-> M4r, A34 |-> A34r, A40 |-> A40r,
               w1 |-> w1r, w2 |-> w2r, i1 |-> i1r, i2 |-> i2r} ||
   places := {}
OPERATIONS
   ExtendRoute(Section) = PRE Section:RouteElement THEN
      validProposedRoute := validProposedRoute <- Section
   END;
   
   set_places = BEGIN
      places := UNION(pp).(pp: PlaceOnRoad &
                     pp: UNION(rr).(rr: Road & rr:ran(roads)|ran(placesOnRoad(rr)) ) |{place(pp)})
   END; 
   
   SyntacticallyValid = PRE card(validProposedRoute)>1 &
     first(validProposedRoute) : PLACE &
     last(validProposedRoute) : PLACE &
     first(validProposedRoute) /= last(validProposedRoute)
     THEN skip
     END
 END