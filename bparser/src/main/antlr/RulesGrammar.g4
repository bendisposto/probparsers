parser grammar RulesGrammar;

import BParser;

options {
  tokenVocab=RulesLexer;
}


parse_unit
  : machine_x    # MachineParserUnit
  ;

machine_x
  : variant=(RULES_MACHINE|MACHINE) machine_header (clauses+=machine_clauses)* 'END'                   # Machine
  ;

substitution_extension_point
  : FOR Identifier IN expression DO substitution END # ForLoop
  ;
