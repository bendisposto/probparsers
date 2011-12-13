MACHINE
   inst
DEFINITIONS
   WORD == (1..4 --> 0..1);
   CORRECT == ((1..3) -->  0..1)
CONSTANTS
  correct,  decode,  changew,changec, 
  calu, calv, calw, /* to evaluate (calculer in French sorry) u, v, w */ 
  calnot, /* the negation on bits */
  calpos /* to compute the position of the error */

PROPERTIES
  correct :   WORD -->  CORRECT &
  decode : WORD* CORRECT --> WORD &
  changew :  WORD <-> WORD &
  changec: CORRECT <-> CORRECT &

  !mm.(mm:WORD => correct(mm)(1)=((mm(2)+mm(3)+mm(4)) mod 2)) & /* x */
  !mm.(mm:WORD => correct(mm)(2)=((mm(1)+mm(3)+mm(4)) mod 2)) & /* y */
  !mm.(mm:WORD => correct(mm)(3)=((mm(1)+mm(2)+mm(4)) mod 2)) & /* z */

  !(m1,m2).(
       m1:WORD& m2:WORD&
      !(xx,yy).(
           xx:1..4&yy:1..4&m1(xx)/=m2(xx)&m1(yy)/=m2(yy) 
         => 
           xx=yy)
      =>
        (m1,m2):changew) &

  !(m1,m2).(
       m1:WORD& m2:WORD&(m1,m2):changew
      =>
       !(xx,yy).(xx:1..4&yy:1..4&m1(xx)/=m2(xx)&m1(yy)/=m2(yy) => xx=yy)) &

/* only one bit can change in the word */

  !(c1,c2).(
       c1:CORRECT& c2:CORRECT&
      !(xx,yy).(
           xx:1..3&yy:1..3&c1(xx)/=c2(xx)&c1(yy)/=c2(yy)
          =>
           xx=yy)
      =>
       (c1,c2):changec) &

  !(c1,c2).(
     c1:CORRECT& c2:CORRECT&(c1,c2):changec
   =>
    !(xx,yy).(xx:1..3&yy:1..3&c1(xx)/=c2(xx)&c1(yy)/=c2(yy) => xx=yy)) &


/* only one bit can change in the word */



  calu : WORD*CORRECT --> 0..1 &
  !(mm,cm).(
       mm:WORD&cm:CORRECT 
      =>
       (calu(mm,cm)=((mm(1)+mm(3)+cm(1)+cm(3)) mod 2))) &

  calv : WORD*CORRECT --> 0..1 &
  !(mm,cm).(
       mm:WORD&cm:CORRECT 
      =>
       (calv(mm,cm)=((mm(2)+mm(3)+cm(2)+cm(3)) mod 2))) &

  calw : WORD*CORRECT --> 0..1 &
  !(mm,cm).(
       mm:WORD&cm:CORRECT 
      => 
       (calw(mm,cm)=((mm(4)+cm(1)+cm(2)+cm(3)) mod 2))) &

   calnot : 0..1 -->  0..1 &
   calnot(0)=1 & 
   calnot(1)=0 &

   calpos: (0..1)* (0..1)* (0..1)-->0..7 &
   !(uu,vv,ww).(
        uu: 0..1&vv: 0..1&ww: 0..1 
       =>
        calpos(uu,vv,ww)=uu+2*vv+4*ww ) &

   decode : WORD * CORRECT --> WORD &
   !(mm,cm).(
        mm:WORD&cm:CORRECT&
        calpos(calu(mm,cm),calv(mm,cm),calw(mm,cm))=0 
       =>
         decode(mm,cm)=mm) &
 
  !(mm,cm,ii).(
       mm:WORD&cm:CORRECT&ii:1..4&
       calpos(calu(mm,cm),calv(mm,cm),calw(mm,cm))=ii
      =>
       decode(mm,cm)=mm<+{ii|->calnot(mm(ii))}) &

   !(mm,cm,ii).(
        mm:WORD&cm:CORRECT&ii:5..7&
        calpos(calu(mm,cm),calv(mm,cm),calw(mm,cm))=ii
       =>
         decode(mm,cm)=mm)
 
ASSERTIONS
  id(WORD)<:changew &
  id(CORRECT)<:changec&
  !(mm,m1).(
       mm:WORD&m1:changew[{mm}]
      =>
       (#ii.(ii:1..4& m1=mm<+{ii|->calnot(mm(ii))}) or m1=mm));

  !(cm,c1).(
       cm:CORRECT&c1:changec[{cm}]
      =>
       (#ii.(ii:1..3& c1=cm<+{ii|->calnot(cm(ii))}) or c1=cm));

  !(mm,ii).(mm:WORD & ii:1..4=> (mm(ii)=0 or mm(ii)=1)) &

  !ii.( ii:1..4=> ii=1 or ii=2 or ii=3 or ii=4)&
  !ii.(ii:1..3 => ii=1 or ii=2 or ii=3)&
  !mm.(mm:WORD => decode(mm,correct(mm))=mm) 
;

  !(mm,cm).(mm:WORD & cm:changec[{correct(mm)}] => decode(mm,cm)=mm) &
  !(mm,m1).(mm:WORD  & m1 :changew[{mm}] => decode(m1,correct(mm))=mm) 
END
