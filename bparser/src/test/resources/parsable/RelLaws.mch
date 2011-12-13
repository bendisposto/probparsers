MACHINE RelLaws
/* This machine is intended so that you can check whether ProB deals
   with basic laws associated to functions and relations. ProB should
   not find an invariant violation. */

SETS
   setX = {el1,el2}; setY = {y1,y2}
   
VARIABLES
  ff,gg,rx,ry
   
DEFINITIONS
  law1 ==  (dom(ff\/gg) = dom(ff) \/ dom(gg));
  law2 ==  (ran(ff\/gg) = ran(ff) \/ ran(gg));
  law3 ==  (dom(ff/\gg) <: dom(ff) /\ dom(gg));
  law4 ==  (ran(ff/\gg) <: ran(ff) /\ ran(gg));
  law5 ==  ( (ff \/ gg)~ = ff~ \/ gg~);
  law6 ==  (dom((ff ; (gg~))) <: dom(ff));
  law7 ==  (!(xx,yy).(xx:setX & yy:setY & xx|->yy : ff  =>  yy: ran(gg))
              =>  (dom((ff ; (gg~))) = dom(ff)));
  law8 ==  (ff : setX --> setY  <=>  (ff: setX +-> setY & dom(ff) = setX)); 
  ff_is_pf == (!(xx,yy,zz).((xx:setX & yy:setY & zz:setY &
                    xx|->yy:ff & xx|->zz:ff) => (yy=zz)));
  law9 ==  (ff : setX +-> setY  <=> ff_is_pf);
  
  law10 == (ff : setX >->> setY  <=>  (ff : setX >-> setY  &  ff~: setY >-> setX));
  law11 == (ff : setX >+> setY  <=> (ff: setX +-> setY &
                                     !(xx,yy).(xx:setX & yy:setX & xx/=yy & xx:dom(ff) &
                                       yy: dom(ff)  => ff(xx)/=ff(yy)))) ;
                                       
  law12 == (ff : setX +->> setY  <=>  (ff: setX +-> setY &
                                       !yy.(yy:setY => yy: ran(ff))))
INVARIANT

  ff: setX <-> setY & gg: setX <-> setY &  rx: setX <-> setX & ry: setY <-> setY &
  
  
  (id(setX) ; ff) = ff &
  (id(setX) ; rx) = rx &
  (id(setX) ; gg) = gg &
  (id(setY) ; ry) = ry &
  
  (rx ; ff)~ = (ff~ ; rx~) &
  
  law1 & law2 & law3 & law4 & law5 & 
  law6 & law7 & law8

INITIALISATION

  ff,gg,rx,ry := {},{},{},{}
  
OPERATIONS

  add_ff(xx,yy) = PRE xx:setX & yy:setY & xx |-> yy /: ff THEN
                     ff := ff \/ {xx |-> yy}
                  END;
  add_gg(xx,yy) = PRE xx:setX & yy:setY & xx |-> yy /: gg THEN
                     gg := gg \/ {xx |-> yy}
                  END;
  add_rx(xx,yy) = PRE xx:setX & yy:setX & xx |-> yy /: rx THEN
                     rx := rx \/ {xx |-> yy}
                  END;
  add_ry(xx,yy) = PRE xx:setY & yy:setY & xx |-> yy /: ry THEN
                     ry := ry \/ {xx |-> yy}
                  END
END
