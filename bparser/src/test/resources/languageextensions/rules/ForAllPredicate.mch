RULES_MACHINE ForAllPredicate
DEFINITIONS
TO_STRING(S) == "0";
EXTERNAL_FUNCTION_TO_STRING(X) == (X --> STRING);
FORMAT_TO_STRING(S,List) == "0";
EXTERNAL_FUNCTION_FORMAT_TO_STRING(X) == ((STRING*seq(X)) --> STRING);
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
        FORMAT_TO_STRING("CT: a=~w, b=~w",[TO_STRING(a),TO_STRING(b)])
      END
    END

END
