MACHINE IrregularConversions
VARIABLES
  seconds_inferred,
  /*@ unit "mins" */ minutes,
  minutes_inferred,
  /*@ unit "h" */ hours,
  hours_inferred,
  /*@ unit "d" */ days

DEFINITIONS
  /*@ conversion */ h_to_min(h) == (60 * h);
  /*@ conversion */ min_to_sec(m) == (60 * m);
  /*@ conversion */ d_to_h(d) == (24 * d)

INVARIANT
  seconds_inferred:NAT & minutes:NAT &
  minutes_inferred:NAT & hours:NAT &
  hours_inferred:NAT & days:NAT
INITIALISATION seconds_inferred,minutes,minutes_inferred,hours,hours_inferred,days:=0,0,0,0,0,0
OPERATIONS
  convert1 = seconds_inferred :=  min_to_sec(minutes);
  convert2 = minutes_inferred := h_to_min(hours);
  convert3 = hours_inferred := d_to_h(days)
END
