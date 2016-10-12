parser grammar BParser;

options { tokenVocab=BLexer; }

@header {
package files;
}
//import Blexer;
//options { tokenVocab=Blexer; } // this is currently not supported by gradle

start
  : parse_unit EOF
  ;

parse_unit
  : machine_x                                                                                               # MachineParserUnit
  | FORMULA_KEYWORD formula                                                                                 # FormulaParseUnit
  | EXPRESSION_KEYWORD expression                                                                           # ExpressionParseUnit
  | PREDICATE_KEYWORD predicate                                                                             # PredicateParseUnit
  | SUBSTITUTION_KEYWORD substitution                                                                       # SubstitutionParseUnit
  ;

machine_x
    : variant=(MACHINE|MODEL|SYSTEM|RULES_MACHINE) machine_header (clauses+=machine_clauses)* 'END'       # Machine
    | REFINEMENT Identifier machine_header REFINES Identifier (clauses+=machine_clauses)* 'END'           # Refinement
    | IMPLEMENTATION Identifier machine_header REFINES Identifier (clauses+=machine_clauses)* 'END'       # Implementation
    ;

machine_header
  : name=Identifier ('(' parameter_list=identifier_list ')')?
  ;

machine_clauses
  : DEFINITIONS defs+=definition (';' defs+=definition)* ';'?     # DefinitionClause
  | name=(CONSTRAINTS|PROPERTIES|INVARIANT) pred=predicate        # PredicateClause
  | name=(INCLUDES|EXTENDS|IMPORTS)
      (instances+=machine_instantiation)+                         # InstanceClause
  | name=(SEES|USES|PROMOTES)
      composed_identifier_list                                    # ReferenceClause
  | name=(CONSTANTS|ABSTRACT_CONSTANTS|CONCRETE_CONSTANTS|
          VARIABLES|ABSTRACT_VARIABLES|CONCRETE_VARIABLES)
          identifier_list                                         # DeclarationClause
  | INITIALISATION substitution                                   # InitialisationClause
  | name=('OPERATIONS'|'LOCAL_OPERATIONS')
      ops+=operation (';' ops+=operation)*                        # OperationsClause
  | VALUES values+=value_entry (';' values+=value_entry)*         # ValuesClause
  | ASSERTIONS preds+=predicate (';' pred+=predicate)*            # AssertionClause
  | SETS set_definition (SEMICOLON set_definition)*               # SetsClause
  ;

machine_instantiation
  : ident=composed_identifier ( '(' exprs+=expression (','  exprs+=expression)* ')' )?
  ;

set_definition
  : Identifier                                            # DeferredSet
  | Identifier EQUAL LEFT_BRACE identifier_list RIGHT_BRACE     # EnumeratedSet
  ;


value_entry
  : ident=Identifier EQUAL expression
  ;

operation
  : (output=identifier_list OUTPUT_PRAMETERS )? Identifier ('(' parameters=identifier_list ')')?  EQUAL substitution
  ;

composed_identifier_list
  : idents+=composed_identifier (',' idents+=composed_identifier)*
  ;

quantified_variables_list
  : identifier_list
  | LEFT_PAR identifier_list RIGHT_PAR
  ;

identifier_list
  : idents+=Identifier (',' idents+=Identifier)*
  ;

definition
  : name=Identifier ('(' parameters+=Identifier (',' parameters+=Identifier)* ')')? DOUBLE_EQUAL formula
  ;

formula
  : expression            #FormulaExpression
  | predicate             #FormulaPredicate
  | substitution          #FormulaSubstitution
  ;

//******************* Substitutions ****************************

substitution
  : BEGIN substitution END                                                              #SubstitutionBlock
  | SKIP_SUB                                                                            #SubstitutionSkip
  | keyword=(PRE|ASSERT) predicate THEN substitution END                                #ConditionSubstitution
  | CHOICE substitution (SUBSTITUTION_OR substitution)* END                             #ChoiceSubstitution
  | SELECT pred=predicate THEN sub=substitution
      (WHEN when_pred+=predicate THEN when_sub+=substitution)*
      (ELSE else_sub=substitution)? END                                                 #SelectSubstitution
  | CASE expr=expression OF EITHER either=expression_list THEN sub=substitution
      (OR or_exprs+=expression_list THEN or_subs+=substitution)+
      (ELSE else_sub=substitution)? END                                                 #CaseSubstitution
  | ANY identifier_list WHERE predicate THEN substitution END                           #AnySubstitution
  | LET identifier_list BE predicate IN substitution END                                #LetSubstitution
  | identifier_list DOUBLE_COLON expression                                             #BecomesElementOfSubstitution
  | identifier_list ELEMENT_OF LEFT_PAR predicate RIGHT_PAR                             #BecomesSuchThatSubstitution
  | VAR identifier_list IN substitution END                                             #VarSubstitution
  | IF pred=predicate THEN thenSub=substitution
      (ELSIF elsifPred+=predicate THEN elsifSub+=substitution)*
      (ELSE elseSub=substitution)? END                                                  #SubstitutionIf
  | WHILE condition=predicate DO substitution INVARIANT invariant=predicate
      VARIANT variant=expression END                                                    #WhileSubstitution
  | identifier_or_function_or_record
      (',' identifier_or_function_or_record)*
      ':=' expression_list                                                              #AssignSubstitution
  | composed_identifier ('(' expression_list ')')?                                      #SubstitutionIdentifierCall
  | identifier_list OUTPUT_PRAMETERS composed_identifier ('(' expression_list ')')?     #SubstitutionOperationCall
  | left=substitution operator=(SEMICOLON | DOUBLE_VERTICAL_BAR) right=substitution     #SubstitutionCompositionOrParallel //P20
  | substitution_extension_point                                                        #SubstitutionExtensionPoint
  ;

substitution_extension_point
  : NOT_REACHABLE
  ;

identifier_or_function_or_record
  : Identifier                                                  # AssignSingleIdentifier
  | name=Identifier '(' arguments=expression_list ')'           # AssignFunctionIdentifier
  | name=Identifier (SINGLE_QUOTE attributes+=Identifier)+      # AssignRecordIdentifier
  ;

expression_list
  : expression_in_par (',' expression_in_par)*
  ;

predicate
  : left=predicate_p40 (operator=IMPLIES right=predicate_p40)   # Implication //p30
  | predicate_p40                                               # PredicateP30Next
  ;

predicate_p40
  : terms+=predicate_atomic ( operators+=(AND|OR) terms+=predicate_atomic)+    # AndOrList //p40
  | predicate_atomic                                                           # PredicateP40Next
  ;

predicate_atomic
  : '(' predicate ')'                                                                     # PredicateParenthesis
  | composed_identifier ('(' arguments+=expression (',' arguments+=expression)* ')')?     # PredicateIdentifierCall

  | 'IF' conditionPred=predicate 'THEN' thenPred=predicate
      'ELSE' elsePred=predicate 'END'                                                     # PredicateIf
  | NOT '(' predicate ')'                                                                 # PredicateNot
  | operator=(FOR_ANY|EXITS) quantified_variables_list
      DOT LEFT_PAR predicate RIGHT_PAR                                    # QuantifiedPredicate
  | left=expression
      predicate_expression_operator
    right=expression                                                      # PredicateBinExpression
  | left=predicate_atomic operator=EQUIVALENCE right=predicate_atomic                     # PredicateBinPredicateOperator //p60
  | LEFT_BRACKET identifier_list ASSIGN expression_list RIGHT_BRACKET predicate_atomic          # WeakestPreconditionPredicate
  ;

predicate_expression_operator
  : operator=
  ( EQUAL
  | ELEMENT_OF
  | INCLUSION
  | STRICT_INCLUSION
  | NON_INCLUSION
  | STRICT_NON_INCLUSION
  | NOT_EQUAL
  | NOT_BELONGING
  | LESS_EQUAL
  | LESS
  | GREATER_EQUAL
  | GREATER
  )
  ;


expression_in_par
  : left=expression_in_par operator=(SEMICOLON | DOUBLE_VERTICAL_BAR)
      right=expression_in_par                                             # CompositionOrParallelProduct //p20
  | expression                                                            # ExpressionInParNext
  ;

expression
  : Number                                                                  # Number
  | LEFT_PAR expression_in_par RIGHT_PAR                                    # Parenthesis
  | LEFT_PAR expression_in_par (MAPLET expression_in_par)+ RIGHT_PAR        # Tuple
  | LEFT_BRACE expression_list RIGHT_BRACE                                  # SetEnumeration
  | LEFT_BRACE RIGHT_BRACE                                                  # EmptySet
  | LESS GREATER                                                            # EmptySequence
  | LEFT_BRACKET RIGHT_BRACKET                                              # EmptySequence
  | composed_identifier DOLLAR_ZERO                                         # PrimedIdentifierExpression
  | composed_identifier                                                     # ExpressionIdentifier
  | expression_prefix_operator '(' expr=expression ')'                      # ExpressionPrefixOperator
  | expression_keyword                                                      # ExpressionKeyword
  | LAMBDA quantified_variables_list
      DOT LEFT_PAR predicate VERTICAL_BAR expression_in_par RIGHT_PAR                   # LambdaExpression
  | operator=(GENERALIZED_UNION|GENERALIZED_INTER) LEFT_PAR identifier_list
      RIGHT_PAR DOT LEFT_PAR predicate VERTICAL_BAR expression_in_par RIGHT_PAR         # QuantifiedExpression

  // operators with precedences
  | left=expression LEFT_BRACKET right=expression_in_par RIGHT_BRACKET                  # ImageExpression
  | expression TILDE                                                                    # ReverseExpression //p230
  | function=expression '(' arguments+=expression_in_par
      (',' arguments+=expression_in_par)* ')'                                           # ExpressionFunctionCall //?
  | MINUS expression                                                                    # UnaryMinus  //P210
  | <assoc=right> left=expression operator=POWER_OF right=expression                    # BinOperator //p200
  | left=expression operator=(MULT|DIVIDE) right=expression                             # BinOperator //p190
  | left=expression operator=(PLUS|MINUS) right=expression                              # BinOperator //p180
  | left=expression operator=INTERVAL right=expression                                  # BinOperator //p170
  | left=expression expressionOperatorP160 right=expression                             # BinOperatorP160 //p160
  | left=expression expression_bin_operator_p125 right=expression                       # ExpressionBinOperatorP125 //p125
  ;

expression_bin_operator_p125
  : operator=
  ( SET_RELATION
  | PARTIAL_FUNCTION
  | TOTAL_FUNCTION
  | TOTAL_INJECTION
  | PARTIAL_INJECTION
  | TOTAL_SURJECTION
  | PARTIAL_SURJECTION
  | TOTAL_BIJECTION
  | PARTIAL_BIJECTION
  )
  ;

expression_prefix_operator
  : operator=(
    BTREE
  | CARD
  | CLOSURE
  | CLOSURE1
  | CONC
  | DOM
  | FIRST
  | FNC
  | FRONT
  | ID
  | INFIX
  | ISEQ
  | ISEQ1
  | LAST
  | LEFT
  | MAX
  | MIN
  | MIRROR
  | PERM
  | POSTFIX
  | POW
  | PREFIX
  | RAN
  | REL
  | REV
  | RIGHT
  | SEQ
  | SEQ1
  | SIZE
  | SIZET
  | SONS
  | TAIL
  | TOP
  | TREE
  )
  ;

expression_keyword
  : operator=(NATURAL
  | NATURAL1
  | NAT
  | NAT1
  | INT
  | INTEGER
  | BOOL
  | PRED
  | SUCC
  )
  ;


expressionOperatorP160
  : operator=(
    OVERWRITE_RELATION
  | DIRECT_PRODUCT
  | CONCAT
  | DOMAIN_RESTRICTION
  | DOMAIN_SUBSTRACTION
  | RANGE_RESTRICTION
  | RANGE_SUBSTRATION
  | INSERT_FRONT
  | INSERT_TAIL
  | UNION
  | INTERSECTION
  | RESTRICT_FRONT
  | RESTRICT_TAIL
  | MAPLET
  )
  ;



composed_identifier
  : Identifier ('.' Identifier)*      # ComposedIdentifier
  ;

identifier
  : Identifier                        # IdentifierNode
  ;
