MACHINE Test

SETS colors = {red, redyellow, yellow, green}; colors2 = {a,b,c}

VARIABLES tl_cars, tl_peds, colors3

INVARIANT tl_cars : colors &
      colors3 : POW(colors) &
	  tl_peds : {red, green} &
	  (tl_peds = red or
	  tl_cars = red)

INITIALISATION  tl_cars := red; tl_peds := red; colors3 := colors /\ colors2

END
