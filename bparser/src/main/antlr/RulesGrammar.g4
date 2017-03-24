parser grammar RulesGrammar;

import BParser;

options {
  tokenVocab=RulesLexer;
}


start
  : parse_unit EOF  # ParseUnit
  ;

machine_x
  : variant=(MACHINE|RULES_MACHINE) machine_header (clauses+=machine_clause)* END                  # Machine
  | REFINEMENT machine_header REFINES identifier (clauses+=machine_clause)* END                    # Refinement
  | IMPLEMENTATION machine_header REFINES identifier (clauses+=machine_clause)* END                # Implementation
  ;

operation
  : (output=identifier_list OUTPUT_PRAMETERS )? IDENTIFIER ('(' parameters=identifier_list ')')?  EQUAL substitution   # BOperation
  | RULE IDENTIFIER operation_attributes* BODY substitution END                                                        # RuleOperation
  ;

operation_attributes
  : keyword=(DEPENDS_ON_RULE|DEPENDS_ON_COMPUTATION) identifier_list    # DependsOnAttribute
  | keyword=ERROR_TYPES Number                                          # ErrorTypesAttribute
  | keyword=(RULEID|CLASSIFICATION) expression                          # ExpressionAttribute
  | keyword=TAGS expression_list                                        # TagsAttribute
  | keyword=(PRECONDITION|ACTIVATION) predicate                         # PredicateAttribute
  ;

substitution_extension_point
  : RULE_FORALL identifier_list WHERE where_pred=predicate EXPECT expect_pred=predicate
      (ERROR_TYPE Number)? COUNTEREXAMPLE expression_in_par END                                           # RuleForAllSubstitution
  | RULE_ANY identifier_list WHERE predicate (ERROR_TYPE Number)? COUNTEREXAMPLE expression_in_par END    # RuleAnySubstitution
  | FOR IDENTIFIER IN expression_in_par DO substitution END                                               # ForLoopSubstitution
  | RULE_FAIL '(' expression_list ')'                                                                     # RuleFailSubstitution
  | DEFINE IDENTIFIER TYPE type_expr=expression_in_par
    (DUMMY_VALUE dummy_expr=expression_in_par)? VALUE value_expr=expression_in_par END                    # DefineSubstitution
  ;
