MACHINE FunLaws
/* This machine is intended so that you can check whether ProB deals
   with basic laws associated to functions and relations. ProB should
   not find an invariant violation. */

SETS
   setX = {el1,el2}; setY = {y1,y2}
   
VARIABLES
  ff,gg
   
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

  ff: setX <-> setY & gg: setX <-> setY &
  
  law1 & law2 & law3 & law4 & law5 & 
  law6 & law7 & law8 & 
  
  law9 & law10 & law11 & law12 &
  
  (ff: setX --> setY  =>  ff: setX +-> setY) &
  (ff: setX >+> setY  =>  ff: setX +-> setY)  &
  (ff: setX >-> setY  =>  ff: setX >+> setY)  &
  (ff: setX >-> setY  =>  ff: setX --> setY)  &
  (ff: setX >->> setY  =>  ff: setX --> setY) &
  (ff: setX +->> setY  =>  ff: setX +-> setY)  &
  (ff: setX -->> setY  =>  (ff: setX --> setY & ff: setX +->> setY))
  
  &
  
  (ff: setX +->> setY => ran(ff) = setY) &
  (ran(ff) <<: setY => not(ff: setX +->> setY)) &
  
  (ff:setX-->>setY or not(ff:setX-->>setY)) &  not(ff:setX-->>setY & not(ff:setX-->>setY))&
  (ff:setX>+>setY or not(ff:setX>+>setY)) &  not(ff:setX>+>setY & not(ff:setX>+>setY))&
  (ff:setX>->setY or not(ff:setX>->setY)) &  not(ff:setX>->setY & not(ff:setX>->setY))&
  (ff:setX+->>setY or not(ff:setX+->>setY)) &  not(ff:setX+->>setY & not(ff:setX+->>setY))&
  (ff:setX>->>setY or not(ff:setX>->>setY)) &  not(ff:setX>->>setY & not(ff:setX>->>setY))&
  (ff:setX-->setY or not(ff:setX-->setY)) &  not(ff:setX-->setY & not(ff:setX-->setY))&
  (ff:setX+->setY or not(ff:setX+->setY)) &  not(ff:setX+->setY & not(ff:setX+->setY))&
  
  (!xx.(xx:setX & xx:dom(ff) => ff(xx):ran(ff))) &
   card(dom(ff))<3 &
   card(ran(ff))<3 &
  (dom(ff) = {xx | xx: setX & #yy.(yy:setY & xx|->yy:ff)}) &
  (ran(ff) = {yy | yy: setY & #xx.(xx:setX & xx|->yy:ff)}) &
  
  
  (ff: setX +-> setY => !(xx).(xx:setY & xx:ran(ff) => #ii.(ii:setX & ii:dom(ff) & ff(ii)=xx))) &

  ff[dom(ff)] = ran(ff) &

  (!xx.(xx:setX => ff[{xx}] = {yy | yy:setY & xx |->yy : ff}))&

  (!(xx,xx2).(xx:setX & xx2:setX => ff[{xx,xx2}] = {yy | yy:setY & ((xx |->yy : ff) or (xx2|->yy: ff)) })) &
  
  (ff~[ff[setX]] = dom(ff)) &
  
  (ff~[setY] = dom(ff)) &
  
  (ff[setX] = ran(ff)) &
  
  (ff: setX >+> setY  =>  !xx.(xx<:dom(ff) => ff~[ff[xx]] = xx))

INITIALISATION

  ff,gg := {},{}
  
OPERATIONS

  add_ff(xx,yy) = PRE xx:setX & yy:setY & xx |-> yy /: ff THEN
                     ff := ff \/ {xx |-> yy}
                  END;
  add_gg(xx,yy) = PRE xx:setX & yy:setY & xx |-> yy /: gg THEN
                     gg := gg \/ {xx |-> yy}
                  END;
                  
   ff_surj = PRE ff: setX +->> setY THEN skip END;
   ff_surj2 = PRE not(ran(ff)<<:setY) THEN skip END;
   notff_surj = PRE not(ff: setX +->> setY) THEN skip END;
   notff_surj2 = PRE ran(ff)<<:setY THEN skip END;
   ff_inj = PRE ff: setX >+> setY THEN skip END;
   ff_bij = PRE ff: setX >->> setY THEN skip END;
   notff_pf2 = PRE not(ff_is_pf) THEN skip END;
   res <-- get_card_dom_ff = BEGIN res := card({xx | xx: setX & #yy.(yy:setY & xx|->yy:ff)}) END;
   res <-- get_dom_ff = BEGIN res := {xx | xx: setX & #yy.(yy:setY & xx|->yy:ff)} END;
   in_dom_ff(aa) = PRE aa:setX & aa: {xx | xx: setX & #yy.(yy:setY & xx|->yy:ff)} THEN skip END
                  
END
