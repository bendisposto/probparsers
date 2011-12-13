MACHINE
   PhilRing2
/* CSP_CONTROLLER Dining */
SETS
 Phil;
 Forks
CONSTANTS lFork,rFork
PROPERTIES
 lFork: Phil >->> Forks &
 rFork: Phil >->> Forks &
 card(Forks) = card(Phil)&
 !pp.(pp:Phil => lFork(pp) /= rFork(pp))   & 
!ss.(ss : POW(Phil)& ss /= Phil & ss /= {} => rFork~[lFork[ss]]/=ss)
/* (!ss.(ss: POW(Phil)& card(ss)>0 => 
	(not({pp|pp:Phil & #tt.(tt:ss&pp=rFork~(lFork(tt)))}=ss)))) */
VARIABLES ltaken,rtaken
INVARIANT
 ltaken : Phil +-> Forks &
 rtaken : Phil +-> Forks &
 !xx.(xx:dom(ltaken) => (xx = lFork~(ltaken(xx))))&
 !xx.(xx:dom(rtaken) => (xx = rFork~(rtaken(xx))))
INITIALISATION ltaken := {} || rtaken := {}
OPERATIONS
  TakeLeftFork(p,f) = PRE p:Phil & f:Forks & p/:dom(ltaken) & lFork(p)=f THEN
    ltaken(p) := f
  END;
  TakeRightFork(p,f) = PRE p:Phil & f:Forks & p/:dom(rtaken) & rFork(p)=f THEN
    rtaken(p) :=  f
  END;
  DropLeftFork(p,f) = PRE p:Phil & f:Forks & p:dom(ltaken) & f=ltaken(p) & lFork(p)=f  THEN
       ltaken := {p} <<| ltaken
  END;
  DropRightFork(p,f) = PRE p:Phil & f:Forks & p:dom(rtaken) & f=rtaken(p) & rFork(p)=f THEN
       rtaken := {p} <<| rtaken
  END
END
