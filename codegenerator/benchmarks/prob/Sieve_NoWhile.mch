MACHINE Sieve_NoWhile // without While Loop for TLC
// For limit = 10000, ProB model checking time: 0.17 seconds, TLC: 0 seconds
// For limit = 100000, ProB model checking time: 2 seconds, TLC: 18 seconds
// For limit = 200000, ProB model checking time: 5 seconds, TLC: 109 seconds  [complexity seems quadratic for TLC]
VARIABLES numbers,cur, limit
INVARIANT
 numbers <: INTEGER & cur:NATURAL1 & limit:NATURAL1
INITIALISATION numbers := 2..10000 || cur := 2 || limit := 10000
OPERATIONS
  SieveStep =
    SELECT cur>1 & cur*cur<= limit THEN
        IF cur:numbers
        THEN
            numbers := numbers - ran(%n.(n:cur..limit/cur|cur*n)) // maybe the translation to TLA will not be ideal
        END ||
        cur := cur+1
      END; //SELECT
  res <-- ComputeNumberOfPrimes = SELECT cur*cur>limit THEN
      res := card(numbers)
      // || cur := 0 // leads to invariant violation; but means TLC MC time is 7 seconds for 10000 instead of 0 seconds
    END//SELECT
 // for 10000 we expect 1292, for 100000 we expect res=9592
END
