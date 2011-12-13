MACHINE RoutePlanning
/* by Michael Leuschel, August 2005 */
/* derived from Anthony Hall's Z specification 40_5 */

SETS
   Segment = {M4, A34, A40, w1, w2, i1, i2, f1, f2,
              Oxford, Newbury, Swindon, Bicester,
              M4r, A34r, A40r, w1r, w2r, i1r, i2r, f1r, f2r
              };
   PlaceOnRoad = {o1,o2,n1,n2,s1,s2}
   
DEFINITIONS
   ProposedRoute == seq(RouteElement);
   ExpandedRoute == seq(Segment);
   connectsAt == (
       %rr.(rr:Road  & rr: dom(placesOnRoad)|
         %ss.(ss: Segment |
              {nn| nn:NAT & nn>0 & nn <= size(placesOnRoad(rr))  &
                   (ss = place(placesOnRoad(rr)(nn)) or
                    ss: connects(placesOnRoad(rr)(nn)) ) } ) ) )
   
CONSTANTS
   ROAD,PLACE,  RouteElement, Road,
   
   /* from Geography: put into Constants as they do not seem to change */
   wholeRoads, finalRoads, initialRoads, namedRoads,
   
   road_id  /* function from schema road = road.id */
PROPERTIES
    /* first the specific assignments for current example */
    RouteElement = {M4, A34, A40, w1, w2, i1, i2, f1, f2, Oxford, Newbury, Swindon, Bicester} &
    RouteElement <: Segment &
    Road = {M4r, A34r, A40r, w1r, w2r, i1r, i2r, f1r, f2r} &
    Road <: Segment  &
    Road \/ RouteElement = Segment &
    Road /\ RouteElement = {} &
    Road /= {} & RouteElement /= {} &
    
    
    
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
   !(rr1).(rr1: Road & rr1:dom(placesOnRoad) => card(placesOnRoad(rr1))>1) 
   /* Missing:p1,p2 ... */
   &
   
   roads: ROAD >+> Road &  /* why is ROAD simply not a subset of Road ???! */
   !(rr2).(rr2:Road & rr2:ran(roads) => !(pp).(pp: PlaceOnRoad & pp: ran(placesOnRoad(rr2)) => place(pp): places))
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
   places := {Oxford, Newbury, Swindon}
OPERATIONS
   ExtendRoute(Section) = PRE Section:RouteElement THEN
      validProposedRoute := validProposedRoute <- Section
   END;
   
   set_places = BEGIN
      places := UNION(pp3).(pp3: PlaceOnRoad &
                 pp3: UNION(rr3).(rr3: Segment /* for some reason Road not accepted as type here */
                  & rr3:ran(roads)|ran(placesOnRoad(rr3)) ) |{place(pp3)})
   END; 
   
   SyntacticallyValid = PRE card(validProposedRoute)>1 &
     first(validProposedRoute) : PLACE &
     last(validProposedRoute) : PLACE &
     first(validProposedRoute) /= last(validProposedRoute)
     THEN skip
     END;

    /* Now a first implementation of connectsAt just as a B operation */
   connectsAtOp(rr,ss,nn,por) = PRE rr: Road & ss: Segment & nn: NAT &
       nn>0 & nn <= size(placesOnRoad(rr)) & rr: dom(placesOnRoad) & /* added by Mike */
     por = placesOnRoad(rr)(nn) & por: PlaceOnRoad &
      (ss = place(por) or
       ss: connects(por) )
   THEN
    skip
    END;
  /* Now define connectsTo by calling ConnectsAt; not strictly allowed in pure B
     as we call an operation in the same machine; but no problem for ProB */
  connectsToOp(rr,ss) = PRE rr: Road & ss: Segment THEN
    ANY nn,por WHERE nn: NAT & por: PlaceOnRoad THEN
      connectsAtOp(rr,ss,nn,por)
    END
   END;

   /* now: connectsAt using a proper lambda abstraction */   
   ns <-- connectsAtApply(fr,fs) = PRE fr: Road & fs: Segment THEN
     ns := (connectsAt)(fr)(fs)  /* brackets around connectsAt required for jbtools parser ! */
    END
 END
