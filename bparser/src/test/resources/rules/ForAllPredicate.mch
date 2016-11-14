RULES_MACHINE ForAllPredicate
OPERATIONS
 RULE rule1 =
    SELECT 1=1
    THEN
      RULE_FORALL
        a,b
      WHERE
        a : 8..10 & b : 1..10
      EXPECT
        a > b
      COUNTEREXAMPLE
        STRING_FORMAT("CT: a=~w, b=~w", a, b)
      END
    END

END
