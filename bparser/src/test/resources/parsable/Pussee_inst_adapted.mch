MACHINE
   inst_adapted  /* adapted version of inst , adapted by mal */
   
DEFINITIONS
   WORD == (1..4 --> 0..1);
   CORRECT == ((1..3) -->  0..1)
   
VARIABLES
   mm, cm, mm_backup, change_allowed, cm_up_to_date, decoded
   
INVARIANT
   mm: WORD &
   cm: CORRECT &
   mm_backup: WORD & change_allowed: BOOL &
   cm_up_to_date : BOOL & decoded: BOOL &
   (  decoded = TRUE  =>  mm = mm_backup)
   
INITIALISATION
   mm := %x.(x:1..4| 0) ||
   mm_backup := %x.(x:1..4| 0) ||
   cm := %x.(x:1..3| 0) ||
   change_allowed := FALSE ||
   cm_up_to_date := TRUE ||
   decoded := FALSE
OPERATIONS
   setw(bitNr) =
   PRE bitNr: 1..4 THEN
     mm := {bitNr |-> ((mm(bitNr) + 1) mod 2)} \/
                    %x.(x:1..4 & x/=bitNr| mm(x)) ||
     mm_backup := %x.(x:1..4| 0) ||
     cm_up_to_date := FALSE  ||
     decoded := FALSE              
   END;
   
   computeCorrect =
   BEGIN
      mm_backup := mm ||
      change_allowed := TRUE ||
      cm := { 1|-> ((mm(2)+mm(3)+mm(4)) mod 2),
                      2|-> ((mm(1)+mm(3)+mm(4)) mod 2), /* y */
  	                  3|-> ((mm(1)+mm(2)+mm(4)) mod 2) } /* z */ ||
     cm_up_to_date := TRUE               
   END;
   
   changec(bitNr) =
   PRE bitNr: 1..3 & (change_allowed = TRUE) THEN
     cm := {bitNr |-> ((cm(bitNr) + 1) mod 2)} \/
                    %x.(x:1..4 & x/=bitNr| cm(x)) ||
     change_allowed := FALSE
   END;
   
   changew(bitNr) =
   PRE bitNr: 1..4 & (change_allowed = TRUE) THEN
     mm := {bitNr |-> ((mm(bitNr) + 1) mod 2)} \/
                    %x.(x:1..4 & x/=bitNr| mm(x))||
     change_allowed := FALSE ||
     decoded := FALSE
   END;
   
   decode(ii) =
   PRE cm_up_to_date = TRUE &
       ii: 0..7 & ii =  ((mm(1)+mm(3)+cm(1)+cm(3)) mod 2)
                  + 2 * ((mm(2)+mm(3)+cm(2)+cm(3)) mod 2)
                  + 4 * ((mm(4)+cm(1)+cm(2)+cm(3)) mod 2)
   THEN
     IF ii:1..4 THEN
       mm := mm<+{ii|-> (mm(ii) + 1) mod 2} || decoded := TRUE
     ELSE
       decoded := TRUE
     END
   END
   
   
   /* Stuff that is not needed:
   resetWord(bit) =
   PRE bit: 0..1 THEN
     mm := %x.(x:1..4| bit)
   END;
   setWordLowBit =
   BEGIN
     mm := {1 |-> 1} \/ %x.(x:2..4| mm(x))
   END;
   rotateWord =
   BEGIN
     mm := {4 |-> mm(1)} \/ %x.(x:1..3| mm(x+1))
   END;
   resetCorrect(bit) =
   PRE bit: 0..1 THEN
     cm := %x.(x:1..3| bit)
   END;
   */
END
   