MACHINE TrafficLight

SETS colors = {red, redyellow, yellow, green}

VARIABLES tl_cars, tl_peds

INVARIANT tl_cars : colors &
	  tl_peds : {red, green} &
	  (tl_peds = red or
	  tl_cars = red)
              
INITIALISATION  tl_cars := red; tl_peds := red
	
OPERATIONS

cars_ry =
	SELECT tl_cars = red & tl_peds = red THEN
		tl_cars := redyellow
	END;

cars_y =
	SELECT tl_cars = green & tl_peds = red THEN
		tl_cars := yellow
	END;

cars_g =
	SELECT tl_cars = redyellow & tl_peds = red THEN
		tl_cars := green
	END;

cars_r =
	SELECT tl_cars = yellow & tl_peds = red THEN
		tl_cars := red
	END;

peds_r =
	SELECT tl_peds = green & tl_cars = red THEN
		tl_peds := red
	END;

peds_g =
	SELECT tl_peds = red & tl_cars = red THEN
		tl_peds := green
	END

      
END