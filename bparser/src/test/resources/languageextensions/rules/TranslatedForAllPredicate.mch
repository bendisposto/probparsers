MACHINE TranslatedForALLPredicate
VARIABLES rule1
INVARIANT rule1 : {"RULE_SUCCESS", "RULE_FAIL", "NOT_CHECKED"}
INITIALISATION
 rule1 := "NOT_CHECKED"
OPERATIONS

 rule1 =
    SELECT 1=1
    THEN
      LET ct
      BE ct = UNION(a,b).(a : 1..10 & b : 1..10 & not(a > b) | {"message"})
      IN
        IF ct = {}
        THEN
          rule1 := "RULE_SUCCESS"
        ELSE
          rule1 := "RULE_FAIL" // TO_STRING(message)
        END
      END
    END
END
